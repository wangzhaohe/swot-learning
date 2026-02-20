#@+leo-ver=5-thin
#@+node:swot.20260220144432.1: * @file key_mouse.py
#@@language python
#@+others
#@+node:swot.20260220151135.1: ** import
import json
import time
import os
import threading
from datetime import datetime
from pathlib import Path
from pynput import keyboard, mouse

#@+node:swot.20260220151847.1: ** var
# --- 核心配置 ---
SCRIPT_DIR = Path(__file__).parent.absolute()
SAVE_DIR = SCRIPT_DIR / "key_stats"
SAVE_INTERVAL = 60  # 1分钟自动存盘
HEARTBEAT_THRESHOLD = 300  # 5分钟心跳阈值（300秒），用于计算专注时长

#@+node:swot.20260220152237.1: ** class TrackerEngine
class TrackerEngine:
    #@+others
    #@+node:swot.20260220152820.1: *3* def __init__
    def __init__(self):
        SAVE_DIR.mkdir(parents=True, exist_ok=True)
        self.lock = threading.Lock()
        
        self.last_save_time = time.time()
        self.last_event_time = time.time() # 新增：记录上一次敲击的绝对时间
        self.has_unsaved_changes = False
        
        # 加载全局统计
        self.global_file = SAVE_DIR / "global_stats.json"
        self.global_data = self._load_json(self.global_file, {
            "keyboard": 170702, 
            "mouse": 16274, 
            "total": 186976,
            "key_details": {}
        })
        
        # 加载当天统计
        self.current_date = datetime.now().strftime("%Y-%m-%d")
        self.daily_data = self._load_json(SAVE_DIR / f"{self.current_date}.json", {
            "keyboard": {}, "mouse": 0, "total": 0, "hourly": {}, "focus_seconds": 0 # 新增：专注秒数
        })

    #@+node:swot.20260220152829.1: *3* def _load_json
    def _load_json(self, path, default_data):
        if path.exists():
            try:
                with open(path, "r", encoding="utf-8") as f:
                    return json.load(f)
            except Exception as e:
                print(f"读取 {path} 失败，使用默认值。错误: {e}")
        return default_data

    #@+node:swot.20260220152843.1: *3* def _atomic_save
    def _atomic_save(self, data, target_path):
        tmp_path = target_path.with_suffix('.tmp')
        try:
            with open(tmp_path, "w", encoding="utf-8") as f:
                json.dump(data, f, indent=4, ensure_ascii=False)
            os.replace(tmp_path, target_path)
        except Exception as e:
            print(f"保存失败: {e}")

    #@+node:swot.20260220152853.1: *3* def force_save
    def force_save(self):
        with self.lock:
            if self.has_unsaved_changes:
                self._atomic_save(self.daily_data, SAVE_DIR / f"{self.current_date}.json")
                self._atomic_save(self.global_data, self.global_file)
                self.has_unsaved_changes = False
                self.last_save_time = time.time()
                print(f"[{datetime.now().strftime('%H:%M:%S')}] 数据已安全存盘。")

    #@+node:swot.20260220152916.1: *3* def log_event
    def log_event(self, is_keyboard, key_name=None):
        now_dt = datetime.now()
        today = now_dt.strftime("%Y-%m-%d")
        current_hour = now_dt.strftime("%H")
        now_ts = time.time()

        with self.lock:
            # 1. 跨天检测
            if today != self.current_date:
                if self.has_unsaved_changes:
                    self._atomic_save(self.daily_data, SAVE_DIR / f"{self.current_date}.json")
                    self._atomic_save(self.global_data, self.global_file)
                
                self.current_date = today
                self.daily_data = self._load_json(SAVE_DIR / f"{self.current_date}.json", {
                    "keyboard": {}, "mouse": 0, "total": 0, "hourly": {}, "focus_seconds": 0
                })
                self.last_event_time = now_ts # 跨天重置事件时间

            # --- 新增：专注时长计算 (5分钟心跳法) ---
            delta = now_ts - self.last_event_time
            if delta < HEARTBEAT_THRESHOLD:
                # 只有距离上次操作小于 5 分钟，才认为是连贯工作
                self.daily_data["focus_seconds"] = self.daily_data.get("focus_seconds", 0) + delta
            self.last_event_time = now_ts
            # ---------------------------------------

            # 2. 数据累加
            if is_keyboard:
                self.daily_data["keyboard"][key_name] = self.daily_data["keyboard"].get(key_name, 0) + 1
                self.global_data["keyboard"] += 1
                
                if "key_details" not in self.global_data:
                    self.global_data["key_details"] = {}
                self.global_data["key_details"][key_name] = self.global_data["key_details"].get(key_name, 0) + 1
            else:
                self.daily_data["mouse"] += 1
                self.global_data["mouse"] += 1

            self.daily_data["total"] += 1
            self.global_data["total"] += 1
            
            # 3. 时段分布统计
            if "hourly" not in self.daily_data:
                self.daily_data["hourly"] = {}
            self.daily_data["hourly"][current_hour] = self.daily_data["hourly"].get(current_hour, 0) + 1

            self.has_unsaved_changes = True

            # 4. 间隔保存检测
            if now_ts - self.last_save_time > SAVE_INTERVAL:
                self._atomic_save(self.daily_data, SAVE_DIR / f"{self.current_date}.json")
                self._atomic_save(self.global_data, self.global_file)
                self.has_unsaved_changes = False
                self.last_save_time = now_ts
                
                # 终端反馈（如果觉得吵可以注释掉）
                focus_mins = int(self.daily_data.get('focus_seconds', 0) // 60)
                print(f"[{now_dt.strftime('%H:%M:%S')}] ⏳ 存盘成功。今日已专注: {focus_mins} 分钟。")

    #@-others
#@+node:swot.20260220152543.1: ** def on_release
def on_release(key):
    try:
        name = key.char if getattr(key, 'char', None) is not None else str(key)
    except Exception:
        name = str(key)
    engine.log_event(is_keyboard=True, key_name=name)

#@+node:swot.20260220152520.1: ** def on_click
def on_click(x, y, button, pressed):
    if not pressed:
        engine.log_event(is_keyboard=False)

#@-others

# --- 监听器初始化 ---
engine = TrackerEngine()

if __name__ == "__main__":
    print("🚀 专家级统计引擎已启动！(支持专注时长计算)")
    print(f"📁 数据存放目录: {SAVE_DIR}")
    print("--------------------------------------------------")
    try:
        with keyboard.Listener(on_release=on_release) as k_listener:
            with mouse.Listener(on_click=on_click) as m_listener:
                k_listener.join()
                m_listener.join()
    except KeyboardInterrupt:
        engine.force_save()
        print("\n👋 程序正常退出，最后的数据已原子化存盘。")

#@-leo

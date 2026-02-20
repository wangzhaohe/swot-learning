import json
import time
import os
import threading
from datetime import datetime
from pathlib import Path
from pynput import keyboard, mouse

# --- 核心配置：数据路径绝对化 ---
# 获取当前脚本所在目录的绝对路径，确保“数据跟着脚本走”
SCRIPT_DIR = Path(__file__).parent.absolute()
SAVE_DIR = SCRIPT_DIR / "key_stats"
SAVE_INTERVAL = 60  # 1分钟保存一次（有变动时）

class TrackerEngine:
    def __init__(self):
        SAVE_DIR.mkdir(parents=True, exist_ok=True)
        
        # 线程锁，防止键盘和鼠标线程冲突
        self.lock = threading.Lock()
        
        self.last_save_time = time.time()
        self.has_unsaved_changes = False
        
        # 加载全局统计 (首次运行会继承你的 18.6w 历史数据)
        self.global_file = SAVE_DIR / "global_stats.json"
        self.global_data = self._load_json(self.global_file, {
            "keyboard": 170702, 
            "mouse": 16274, 
            "total": 186976,
            "key_details": {}  # 用于存储全局按键详情
        })
        
        # 加载当天统计
        self.current_date = datetime.now().strftime("%Y-%m-%d")
        self.daily_data = self._load_json(SAVE_DIR / f"{self.current_date}.json", {
            "keyboard": {}, "mouse": 0, "total": 0
        })

    def _load_json(self, path, default_data):
        """通用读取方法"""
        if path.exists():
            try:
                with open(path, "r", encoding="utf-8") as f:
                    return json.load(f)
            except Exception as e:
                print(f"读取 {path} 失败，使用默认值。错误: {e}")
        return default_data

    def _atomic_save(self, data, target_path):
        """原子化保存：先写临时文件，再重命名，杜绝断电导致文件损坏"""
        tmp_path = target_path.with_suffix('.tmp')
        try:
            with open(tmp_path, "w", encoding="utf-8") as f:
                json.dump(data, f, indent=4, ensure_ascii=False)
            os.replace(tmp_path, target_path) # 原子操作，安全覆盖
        except Exception as e:
            print(f"保存失败: {e}")

    def force_save(self):
        """强制存盘（供退出时调用）"""
        with self.lock:
            if self.has_unsaved_changes:
                self._atomic_save(self.daily_data, SAVE_DIR / f"{self.current_date}.json")
                self._atomic_save(self.global_data, self.global_file)
                self.has_unsaved_changes = False
                self.last_save_time = time.time()
                print(f"[{datetime.now().strftime('%H:%M:%S')}] 数据已安全存盘。")

    def log_event(self, is_keyboard, key_name=None):
        """支持时段分布统计的逻辑（已彻底修复并发死锁隐患）"""
        now_dt = datetime.now()
        today = now_dt.strftime("%Y-%m-%d")
        current_hour = now_dt.strftime("%H") # 获取当前小时，例如 "14"
        now_ts = time.time()

        with self.lock:
            # 1. 跨天检测
            if today != self.current_date:
                # 【修复死锁】：因为当前已经持有 lock，绝对不能调用 self.force_save()
                # 直接调用底层的无锁保存逻辑
                if self.has_unsaved_changes:
                    self._atomic_save(self.daily_data, SAVE_DIR / f"{self.current_date}.json")
                    self._atomic_save(self.global_data, self.global_file)
                
                # 初始化新的一天
                self.current_date = today
                self.daily_data = self._load_json(SAVE_DIR / f"{self.current_date}.json", {
                    "keyboard": {}, "mouse": 0, "total": 0, "hourly": {} 
                })

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
            
            # --- 3. 时段分布统计 ---
            # 防御性编程：兼容你之前已经生成的、没有 hourly 字段的旧 JSON 文件
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
                
                # 打印一下本小时的战况
                print(f"[{now_dt.strftime('%H:%M:%S')}] ⏳ 存盘成功。本小时已点击: {self.daily_data['hourly'][current_hour]} 次")

# --- 监听器初始化 ---
engine = TrackerEngine()

def on_release(key):
    try:
        # 获取字母数字或特殊按键名称
        name = key.char if getattr(key, 'char', None) is not None else str(key)
    except Exception:
        name = str(key)
    engine.log_event(is_keyboard=True, key_name=name)

def on_click(x, y, button, pressed):
    if not pressed: # 仅释放时记录
        engine.log_event(is_keyboard=False)

# --- 启动 ---
if __name__ == "__main__":
    print(f"🚀 专家级统计引擎已启动！")
    print(f"📁 数据存放目录: {SAVE_DIR}")
    print(f"📊 当前全局总计: {engine.global_data['total']} (Key: {engine.global_data['keyboard']}, Mouse: {engine.global_data['mouse']})")
    print("--------------------------------------------------")

    try:
        with keyboard.Listener(on_release=on_release) as k_listener:
            with mouse.Listener(on_click=on_click) as m_listener:
                k_listener.join()
                m_listener.join()
    except KeyboardInterrupt:
        engine.force_save()
        print("\n👋 程序正常退出，最后的数据已原子化存盘。")


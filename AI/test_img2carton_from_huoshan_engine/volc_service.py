# coding:utf-8
# @+leo-ver=5-thin
# @+node:swot.20260227133640.1: * @file volc_service.py
# @@first
# @@language python
# @+<< import >>
# @+node:swot.20260227134231.1: ** << import >>
import os
import base64
import time
from dotenv import load_dotenv
from volcengine.visual.VisualService import VisualService

# @-<< import >>
# @+<< 初始化密钥 >>
# @+node:swot.20260227135918.1: ** << 初始化密钥 >>
# @@language python
load_dotenv()

# 全局初始化，避免被 main.py 调用时每次发起请求都重新建立实例
visual_service = VisualService()
visual_service.set_ak(os.getenv("VOLC_AK"))
visual_service.set_sk(os.getenv("VOLC_SK"))


# @-<< 初始化密钥 >>
# @+<< def submit_image_task >>
# @+node:swot.20260227145753.1: ** << def submit_image_task >>
# @@language python
def submit_image_task(submit_form: dict) -> dict:
    """提交异步图像任务"""
    return visual_service.cv_sync2async_submit_task(submit_form)


# @-<< def submit_image_task >>
# @+<< def get_task_result >>
# @+node:swot.20260227145817.1: ** << def get_task_result >>
# @@language python
def get_task_result(req_key: str, task_id: str) -> dict:
    """获取异步任务结果"""
    get_form = {"req_key": req_key, "task_id": task_id}
    return visual_service.cv_sync2async_get_result(get_form)


# @-<< def get_task_result >>
# @+<< def main >>
# @+node:swot.20260227145834.1: ** << def main >>
# @@language python
def main():
    # @+others
    # @+node:swot.20260227152125.1: *3* 提交任务
    # @@language python
    print(">>> 1. 准备调用 CVSync2AsyncSubmitTask 提交任务...")

    test_image_url = (
        "https://images.unsplash.com/photo-1544005313-94ddf0286df2?w=800&q=80"
    )
    req_key = "i2i_multi_style_zx2x"

    submit_form = {
        "req_key": req_key,
        # "template_id": "acrylic_ornaments",
        "template_id": "furry_dream_doll",
        "image_input1": test_image_url,
    }

    submit_resp = submit_image_task(submit_form)
    print(f"提交响应: {submit_resp}")

    if submit_resp.get("code") != 10000:
        print("提交任务失败，请检查上面打印的错误信息。")
        return

    task_id = submit_resp.get("data", {}).get("task_id")
    print(f"提交成功！拿到 Task ID: {task_id}")

    # @+node:swot.20260227152244.1: *3* 循环获取生成卡通图片
    # @@language python
    for i in range(30):
        time.sleep(2)
        print(f">>> 2. 第 {i + 1} 次查询结果...")
        result_resp = get_task_result(req_key, task_id)

        # 注意这里获取的是 'status' 字段
        status = result_resp.get("data", {}).get("status")

        # 兼容大小写
        if status and status.lower() == "done":
            images = result_resp.get("data", {}).get("binary_data_base64", [])
            if images:
                with open("img/result_angel_5.jpg", "wb") as f:
                    f.write(base64.b64decode(images[0]))
                print("任务完成！图片已保存为 result_angel.jpg")
            return
        elif status in ["FAILED", "FAIL"]:
            print(f"任务生成失败！服务端返回: {result_resp}")
            return

    print("超时未完成")

    # @-others


# @-<< def main >>

if __name__ == "__main__":
    main()
# @-leo

import os
import base64
import asyncio
import time
import traceback
from fastapi import FastAPI, UploadFile, File, HTTPException, Request
from fastapi.staticfiles import StaticFiles
from fastapi.responses import Response

# 引入自定义的火山引擎服务模块
from volc_service import submit_image_task, get_task_result

app = FastAPI(title="火山引擎图像特效 - URL中转版")

# ==================================================
# 创建一个本地临时目录，用于临时托管前端上传的图片
# ==================================================
TEMP_IMG_DIR = "temp_uploads"
os.makedirs(TEMP_IMG_DIR, exist_ok=True)

# 核心步骤：将该目录挂载为服务，允许通过公网 URL 访问里面的文件
app.mount("/static_images", StaticFiles(directory=TEMP_IMG_DIR), name="static_images")


@app.post("/generate/angel-figurine", summary="异步生成手办特效")
async def generate_angel_figurine(request: Request, file: UploadFile = File(...)):
    temp_filepath = ""
    try:
        # 确保 filename 即使为 None 也能安全处理
        original_filename = file.filename or "image.jpg"
        file_ext = (
            original_filename.split(".")[-1] if "." in original_filename else "jpg"
        )
        # 为了防止文件名冲突，使用时间戳+随机数
        temp_filename = f"img_{int(time.time())}_{os.urandom(4).hex()}.{file_ext}"
        temp_filepath = os.path.join(TEMP_IMG_DIR, temp_filename)

        # 1. 保存上传的文件到本地挂载的静态目录
        with open(temp_filepath, "wb") as buffer:
            buffer.write(await file.read())

        # 2. 构造火山引擎可访问的公网路径
        # NOTE: 本地开发必须配合 ngrok/localtunnel 等工具，确保 base_url 是公网地址
        image_url = f"{str(request.base_url)}static_images/{temp_filename}"
        print(f">>> 待处理图片 URL: {image_url}")

        # 3. 提交任务到火山引擎
        req_key = "i2i_multi_style_zx2x"
        submit_form = {
            "req_key": req_key,
            # "template_id": "felt_keychain",
            "template_id": "furry_dream_doll",
            "image_input1": image_url,
        }

        print(">>> 正在提交任务...")

        # 使用 asyncio.to_thread 防止同步函数阻塞事件循环
        submit_resp = await asyncio.to_thread(submit_image_task, submit_form)

        if submit_resp.get("code") != 10000:
            raise HTTPException(status_code=400, detail=f"提交任务失败: {submit_resp}")

        task_id = submit_resp.get("data", {}).get("task_id")
        if not task_id:
            raise HTTPException(status_code=500, detail="未能获取到 task_id")

        print(f">>> 任务提交成功，Task ID: {task_id}")

        # 4. 轮询查询结果
        max_retries = 20  # 增加轮询次数到 20 次，与测试用例看齐
        for attempt in range(max_retries):
            # 使用 asyncio.sleep 避免阻塞 FastAPI 的主事件循环
            await asyncio.sleep(2)
            print(f">>> 正在查询结果 (第 {attempt + 1} 次)...")

            # 查询接口也是同步的，同样丢进线程池
            result_resp = await asyncio.to_thread(get_task_result, req_key, task_id)

            if result_resp.get("code") != 10000:
                raise HTTPException(status_code=400, detail=f"查询报错: {result_resp}")

            data = result_resp.get("data", {})
            task_status = data.get("status")

            if task_status and task_status.lower() == "done":
                print(">>> 任务完成，正在返回图片！")
                images = data.get("binary_data_base64", [])

                # 任务成功，清理本地临时图片
                if os.path.exists(temp_filepath):
                    os.remove(temp_filepath)

                if images:
                    result_bytes = base64.b64decode(images[0])
                    return Response(content=result_bytes, media_type="image/jpeg")
                else:
                    raise HTTPException(
                        status_code=500, detail="任务完成但没有返回图片数据"
                    )

            elif task_status in ["FAILED", "FAIL"]:
                raise HTTPException(
                    status_code=400, detail="图片生成失败，服务端状态为 FAILED"
                )

        # 超时处理
        if os.path.exists(temp_filepath):
            os.remove(temp_filepath)
        raise HTTPException(status_code=504, detail="处理超时，请稍后再试")

    except HTTPException:
        # 兜底清理：调试期间，发生 HTTP 异常时不删除图片，方便排查
        # if temp_filepath and os.path.exists(temp_filepath):
        #     os.remove(temp_filepath)
        raise
    except Exception as e:
        # 调试期间：发生 HTTP 异常时不删除图片，方便排查
        # if temp_filepath and os.path.exists(temp_filepath):
        #     os.remove(temp_filepath)
        print("\n发生服务器内部错误，堆栈信息如下:")
        traceback.print_exc()  # 把完整的红字报错打在屏幕上
        print("\n")

        error_detail = e.decode("utf-8") if isinstance(e, bytes) else str(e)
        raise HTTPException(status_code=500, detail=f"服务器内部错误: {error_detail}")


if __name__ == "__main__":
    import uvicorn

    uvicorn.run(app, host="0.0.0.0", port=8000)

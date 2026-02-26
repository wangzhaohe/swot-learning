import os
import base64
from fastapi import FastAPI, UploadFile, File, HTTPException
from fastapi.responses import Response
from dotenv import load_dotenv
from volcengine.visual.VisualService import VisualService

# 1. 加载环境变量并初始化 FastAPI
load_dotenv()
app = FastAPI(title="火山引擎图像特效测试", description="测试天使形象手办风格化")

# 2. 初始化火山引擎 SDK
visual_service = VisualService()
visual_service.set_ak(os.getenv("VOLC_AK"))
visual_service.set_sk(os.getenv("VOLC_SK"))

@app.post("/generate/angel-figurine", summary="生成天使形象手办")
async def generate_angel_figurine(file: UploadFile = File(...)):
    """
    接收用户上传的图片，调用火山引擎接口生成“天使形象手办”风格，并直接返回图片文件。
    """
    try:
        # 读取上传的图片内容并转换为 Base64
        image_bytes = await file.read()
        image_base64 = base64.b64encode(image_bytes).decode("utf-8")
        
        # 组装火山引擎请求参数
        # req_key: face_cartoon (图像风格化通用key)
        # filter: angel_figurine (具体的天使手办风格)
        form = {
            "req_key": "face_cartoon",
            "binary_data_base64": [image_base64],
            "filter": "angel_figurine"
        }
        
        # 发起同步调用
        resp = visual_service.cv_process(form)
        
        # 解析返回结果
        if resp.get("code") == 10000:
            # 获取生成的图片 Base64 数据
            result_base64 = resp["data"]["binary_data_base64"][0]
            result_bytes = base64.b64decode(result_base64)
            
            # 直接返回图片流，方便在浏览器中直接预览
            return Response(content=result_bytes, media_type="image/jpeg")
        else:
            # 接口调用失败，返回火山引擎的错误信息
            error_msg = resp.get("message", "未知错误")
            raise HTTPException(status_code=400, detail=f"火山引擎调用失败: {error_msg}")
            
    except Exception as e:
        raise HTTPException(status_code=500, detail=f"服务器内部错误: {str(e)}")

if __name__ == "__main__":
    import uvicorn
    # 启动 Web 服务，监听 8000 端口
    uvicorn.run(app, host="0.0.0.0", port=8000)

#!/bin/bash

# 服务部署脚本
SERVICE_NAME=${PWD##*/}

case "$1" in
    "start")
        echo "启动 $SERVICE_NAME..."
        docker compose up -d
        ;;
    "stop")
        echo "停止 $SERVICE_NAME..."
        docker compose down
        ;;
    "restart")
        echo "重启 $SERVICE_NAME..."
        docker compose down
        docker compose up -d
        ;;
    "logs")
        echo "查看 $SERVICE_NAME 日志..."
        docker compose logs -f
        ;;
    "status")
        echo "检查 $SERVICE_NAME 状态..."
        docker compose ps
        ;;
    *)
        echo "使用方法: $0 {start|stop|restart|logs|status}"
        exit 1
        ;;
esac

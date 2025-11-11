#!/bin/bash

# Docker 构建脚本
SKIP_MAVEN=false

# 解析参数
while [[ $# -gt 0 ]]; do
    case $1 in
        --skip-maven)
            SKIP_MAVEN=true
            shift
            ;;
        *)
            echo "未知参数: $1"
            exit 1
            ;;
    esac
done

echo "开始构建 Docker 镜像..."

if [ "$SKIP_MAVEN" = false ]; then
    # 清理之前的构建
    echo "清理之前的构建..."
    mvn clean

    # 构建项目
    echo "构建项目..."
    mvn package -DskipTests

    # 检查构建是否成功
    if [ $? -eq 0 ]; then
        echo "项目构建成功！"
    else
        echo "项目构建失败！"
        exit 1
    fi
else
    echo "跳过 Maven 构建..."
fi

# 构建 Docker 镜像
echo "构建 Docker 镜像..."
IMAGE_NAME="spring-cloud-${PWD##*/}:1.0"
docker build -t "$IMAGE_NAME" .

if [ $? -eq 0 ]; then
    echo "Docker 镜像构建成功！"
    echo "镜像名称: $IMAGE_NAME"
else
    echo "Docker 镜像构建失败！"
    exit 1
fi

#!/bin/bash

# Spring Cloud 微服务统一部署管理脚本
# 这个脚本是 docker-deploy 目录中部署脚本的入口

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
DEPLOY_DIR="$SCRIPT_DIR/docker-deploy"

echo -e "\033[0;34mSpring Cloud 微服务统一部署管理脚本\033[0m"
echo -e "\033[1;33m部署文件位于: $DEPLOY_DIR\033[0m"
echo ""

# 检查部署目录是否存在
if [ ! -d "$DEPLOY_DIR" ]; then
    echo -e "\033[0;31m错误: 部署目录 $DEPLOY_DIR 不存在\033[0m"
    echo "请确保 docker-deploy 目录存在"
    exit 1
fi

# 切换到部署目录并执行主脚本
cd "$DEPLOY_DIR" && ./deploy.sh "$@"

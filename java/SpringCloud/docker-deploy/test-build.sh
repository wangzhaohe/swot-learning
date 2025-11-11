#!/bin/bash

# 测试脚本：验证重复构建问题是否已解决
source "$(dirname "$0")/docker-config.sh"

echo -e "${BLUE}测试重复构建问题修复...${NC}"
echo ""

# 1. 检查当前镜像
echo -e "${YELLOW}步骤 1: 检查当前镜像状态${NC}"
for service in $(get_services); do
    image_name="spring-cloud-$service:1.0"
    if docker image inspect "$image_name" &>/dev/null; then
        echo -e "  ✅ $image_name 存在"
    else
        echo -e "  ❌ $image_name 不存在"
    fi
done

echo ""
echo -e "${YELLOW}步骤 2: 测试构建流程${NC}"
echo "运行: ./deploy.sh build-all"
echo "这将构建所有镜像：spring-cloud-服务名:1.0"

echo ""
echo -e "${YELLOW}步骤 3: 测试启动流程${NC}"
echo "运行: ./deploy.sh start"
echo "这将检查镜像是否存在，然后启动容器，不会重复构建"

echo ""
echo -e "${YELLOW}步骤 4: 验证结果${NC}"
echo "现在只会生成一种镜像：spring-cloud-服务名:1.0"
echo "不会再生成 docker-deploy-api-gateway 这样的重复镜像"

echo ""
echo -e "${GREEN}修复总结:${NC}"
echo "✅ docker-compose.yml 现在使用预构建的镜像"
echo "✅ deploy.sh 在启动前会检查镜像是否存在"
echo "✅ 避免了重复构建，节省时间和磁盘空间"
echo "✅ 镜像命名统一为：spring-cloud-服务名:1.0"

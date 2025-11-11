#!/bin/bash

# 只构建 Docker 镜像脚本
# 这个脚本跳过 Maven 构建，只构建 Docker 镜像

source "$(dirname "$0")/docker-config.sh"

echo -e "${BLUE}开始只构建 Docker 镜像（跳过 Maven 构建）...${NC}"

for service in $(get_services); do
    echo -e "${YELLOW}构建服务: $service${NC}"
    if [ -d "../$service" ]; then
        (cd "../$service" && ./build-docker.sh --skip-maven) || echo -e "${RED}服务 $service 构建失败${NC}"
    else
        echo -e "${RED}服务目录 $service 不存在${NC}"
    fi
done

echo -e "${GREEN}所有 Docker 镜像构建完成！${NC}"

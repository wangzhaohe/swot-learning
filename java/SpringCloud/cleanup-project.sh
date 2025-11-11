#!/bin/bash

# Spring Cloud 项目清理脚本
# 删除所有Docker镜像和已存在的JAR打包文件
# 位置：项目根目录 /Users/swot/swot-learning/java/SpringCloud/

echo "=== Spring Cloud 项目清理脚本 ==="
echo "注意：此操作将删除所有Docker镜像和构建文件，请谨慎操作！"
echo

# 1. 停止并删除所有Docker容器和网络
echo "1. 停止并删除所有Docker容器和网络..."
cd docker-deploy
docker-compose down
cd ..

echo

# 2. 删除所有Docker镜像
echo "2. 删除所有Spring Cloud相关的Docker镜像..."
docker images | grep -E "(spring-cloud|config-server|eureka|gateway|service-item|service-order)" | awk '{print $3}' | xargs -r docker rmi -f

echo

# 3. 清理所有服务的target目录（JAR打包文件）
echo "3. 清理所有服务的target目录..."
services=("config-server" "eureka" "gateway" "service-item" "service-order")

for service in "${services[@]}"; do
    echo "清理 $service/target 目录..."
    if [ -d "$service/target" ]; then
        rm -rf "$service/target"
        echo "  ✓ $service/target 已删除"
    else
        echo "  - $service/target 目录不存在"
    fi
done

echo

# 4. 执行Maven清理（可选，更彻底的清理）
echo "4. 执行Maven清理..."
for service in "${services[@]}"; do
    echo "在 $service 中执行 mvn clean..."
    cd "$service"
    mvn clean > /dev/null 2>&1
    if [ $? -eq 0 ]; then
        echo "  ✓ $service Maven清理完成"
    else
        echo "  - $service Maven清理失败或不需要"
    fi
    cd ..
done

echo

# 5. 删除 ./deploy.sh init 创建的文件
echo "5. 删除 ./deploy.sh init 创建的文件..."
services=("config-server" "eureka" "gateway" "service-item" "service-order")

for service in "${services[@]}"; do
    echo "清理 $service 的部署文件..."
    
    # 删除 Dockerfile
    if [ -f "$service/Dockerfile" ]; then
        rm -f "$service/Dockerfile"
        echo "  ✓ $service/Dockerfile 已删除"
    else
        echo "  - $service/Dockerfile 不存在"
    fi
    
    # 删除 build-docker.sh
    if [ -f "$service/build-docker.sh" ]; then
        rm -f "$service/build-docker.sh"
        echo "  ✓ $service/build-docker.sh 已删除"
    else
        echo "  - $service/build-docker.sh 不存在"
    fi
    
    # 删除 deploy.sh
    if [ -f "$service/deploy.sh" ]; then
        rm -f "$service/deploy.sh"
        echo "  ✓ $service/deploy.sh 已删除"
    else
        echo "  - $service/deploy.sh 不存在"
    fi
done

echo

# 6. 检查剩余Docker镜像
echo "6. 检查剩余的Spring Cloud相关镜像..."
remaining_images=$(docker images | grep -E "(spring-cloud|config-server|eureka|gateway|service-item|service-order)" | wc -l)
if [ "$remaining_images" -eq 0 ]; then
    echo "  ✓ 所有Spring Cloud相关镜像已清理"
else
    echo "  ⚠ 仍有 $remaining_images 个相关镜像存在，可能需要手动清理"
    docker images | grep -E "(spring-cloud|config-server|eureka|gateway|service-item|service-order)"
fi

echo
echo "=== 清理完成 ==="
echo "项目已恢复到初始状态，可以重新构建和部署。"
echo
echo "重新构建命令："
echo "  ./deploy.sh init         # 初始化部署环境"
echo "  ./deploy.sh build-all    # 构建所有服务"
echo "  ./deploy.sh start        # 启动集群"

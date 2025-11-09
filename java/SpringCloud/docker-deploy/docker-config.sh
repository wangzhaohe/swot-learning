#!/bin/bash

# Docker 部署共享配置
# 这个文件包含所有 Docker 部署脚本的共享配置和函数

# ANSI 颜色定义
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# 服务列表和端口映射
# 使用函数替代关联数组（兼容 macOS bash）

# 获取服务端口
get_service_port() {
    local service=$1
    case $service in
        "config-server") echo "8888" ;;
        "eureka") echo "8761" ;;
        "gateway") echo "8080" ;;
        "service-item") echo "8081" ;;
        "service-order") echo "8082" ;;
        *) echo "8080" ;;
    esac
}

# 获取服务列表
get_services() {
    echo "config-server eureka gateway service-item service-order"
}

# 获取服务容器名称
get_container_name() {
    local service=$1
    case $service in
        "config-server") echo "spring-cloud-config-server" ;;
        "eureka") echo "eureka-server" ;;
        "gateway") echo "api-gateway" ;;
        "service-item") echo "service-item" ;;
        "service-order") echo "service-order" ;;
        *) echo "$service" ;;
    esac
}

# 检查服务是否运行
check_service_health() {
    local service=$1
    local port=$(get_service_port "$service")
    local container_name=$(get_container_name "$service")
    
    echo -e "${YELLOW}$container_name ($service):${NC}"
    curl -f "http://localhost:$port/actuator/health" && echo "" || echo -e "${RED}❌ 未运行${NC}"
}

# 创建标准构建脚本
create_build_script() {
    local service=$1
    cat > "$service/build-docker.sh" << 'EOF'
#!/bin/bash

# Docker 构建脚本
echo "开始构建 Docker 镜像..."

# 清理之前的构建
echo "清理之前的构建..."
mvn clean

# 构建项目
echo "构建项目..."
mvn package -DskipTests

# 检查构建是否成功
if [ $? -eq 0 ]; then
    echo "项目构建成功！"
    
    # 构建 Docker 镜像
    echo "构建 Docker 镜像..."
    docker build -t ${PWD##*/}:1.0 .
    
    if [ $? -eq 0 ]; then
        echo "Docker 镜像构建成功！"
        echo "镜像名称: ${PWD##*/}:1.0"
    else
        echo "Docker 镜像构建失败！"
        exit 1
    fi
else
    echo "项目构建失败！"
    exit 1
fi
EOF
    chmod +x "$service/build-docker.sh"
}

# 创建标准部署脚本
create_deploy_script() {
    local service=$1
    cat > "$service/deploy.sh" << EOF
#!/bin/bash

# 服务部署脚本
SERVICE_NAME=\${PWD##*/}

case "\$1" in
    "start")
        echo "启动 \$SERVICE_NAME..."
        docker-compose up -d
        ;;
    "stop")
        echo "停止 \$SERVICE_NAME..."
        docker-compose down
        ;;
    "restart")
        echo "重启 \$SERVICE_NAME..."
        docker-compose down
        docker-compose up -d
        ;;
    "logs")
        echo "查看 \$SERVICE_NAME 日志..."
        docker-compose logs -f
        ;;
    "status")
        echo "检查 \$SERVICE_NAME 状态..."
        docker-compose ps
        ;;
    *)
        echo "使用方法: \$0 {start|stop|restart|logs|status}"
        exit 1
        ;;
esac
EOF
    chmod +x "$service/deploy.sh"
}

# 创建独立 docker-compose.yml
create_standalone_compose() {
    local service=$1
    local port=$(get_service_port "$service")
    local container_name=$(get_container_name "$service")
    
    cat > "$service/docker-compose.yml" << EOF
version: '3.8'

services:
  $service:
    build:
      context: .
      dockerfile: Dockerfile
    container_name: $container_name
    ports:
      - "$port:$port"
    environment:
      - JAVA_OPTS=-Xmx512m -Xms256m
    restart: unless-stopped

EOF
}

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
        "service-order") echo "8091" ;;
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

# 获取服务镜像名称
get_image_name() {
    local service=$1
    echo "spring-cloud-$service:1.0"
}

# 检查镜像是否存在
check_image_exists() {
    local service=$1
    local image_name="spring-cloud-$service:1.0"
    if docker image inspect "$image_name" &>/dev/null; then
        return 0  # 镜像存在
    else
        return 1  # 镜像不存在
    fi
}

# 检查服务是否运行
check_service_health() {
    local service=$1
    local port=$(get_service_port "$service")
    local container_name=$(get_container_name "$service")
    
    echo -e "${YELLOW}$container_name ($service):${NC}"
    curl -f "http://localhost:$port/actuator/health" && echo "" || echo -e "${RED}❌ 未运行${NC}"
}

# 智能等待服务启动
wait_for_service() {
    local service=$1
    local port=$2
    local timeout=180  # 3分钟超时
    local start_time=$(date +%s)
    
    echo -e "${YELLOW}等待 $service 服务启动...${NC}"
    while ! curl -f "http://localhost:$port/actuator/health" &>/dev/null; do
        local current_time=$(date +%s)
        local elapsed=$((current_time - start_time))
        
        if [ $elapsed -gt $timeout ]; then
            echo -e "${RED}$service 启动超时 (超过 ${timeout} 秒)${NC}"
            return 1
        fi
        
        sleep 5
        echo "  已等待 ${elapsed} 秒，继续等待 $service 服务..."
    done
    echo -e "${GREEN}$service 服务已就绪 (耗时 ${elapsed} 秒)${NC}"
    return 0
}

# 等待关键基础设施服务
wait_for_infrastructure() {
    echo -e "${BLUE}等待基础设施服务启动...${NC}"
    
    # 等待配置服务器
    if ! wait_for_service "config-server" 8888; then
        echo -e "${RED}配置服务器启动失败，但继续等待其他服务...${NC}"
    fi
    
    # 等待Eureka服务器
    if ! wait_for_service "eureka-server" 8761; then
        echo -e "${RED}Eureka服务器启动失败，但继续等待其他服务...${NC}"
    fi
    
    # 给业务服务额外时间启动
    echo -e "${YELLOW}等待业务服务启动...${NC}"
    sleep 30
}

# 创建标准构建脚本
create_build_script() {
    local service=$1
    cat > "../$service/build-docker.sh" << EOF
#!/bin/bash

# Docker 构建脚本
SKIP_MAVEN=false

# 解析参数
while [[ \$# -gt 0 ]]; do
    case \$1 in
        --skip-maven)
            SKIP_MAVEN=true
            shift
            ;;
        *)
            echo "未知参数: \$1"
            exit 1
            ;;
    esac
done

echo "开始构建 Docker 镜像..."

if [ "\$SKIP_MAVEN" = false ]; then
    # 清理之前的构建
    echo "清理之前的构建..."
    mvn clean

    # 构建项目
    echo "构建项目..."
    mvn package -DskipTests

    # 检查构建是否成功
    if [ \$? -eq 0 ]; then
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
IMAGE_NAME="spring-cloud-\${PWD##*/}:1.0"
docker build -t "\$IMAGE_NAME" .

if [ \$? -eq 0 ]; then
    echo "Docker 镜像构建成功！"
    echo "镜像名称: \$IMAGE_NAME"
else
    echo "Docker 镜像构建失败！"
    exit 1
fi
EOF
    chmod +x "../$service/build-docker.sh"
}

# 创建标准部署脚本
create_deploy_script() {
    local service=$1
    cat > "../$service/deploy.sh" << EOF
#!/bin/bash

# 服务部署脚本
SERVICE_NAME=\${PWD##*/}

case "\$1" in
    "start")
        echo "启动 \$SERVICE_NAME..."
        docker compose up -d
        ;;
    "stop")
        echo "停止 \$SERVICE_NAME..."
        docker compose down
        ;;
    "restart")
        echo "重启 \$SERVICE_NAME..."
        docker compose down
        docker compose up -d
        ;;
    "logs")
        echo "查看 \$SERVICE_NAME 日志..."
        docker compose logs -f
        ;;
    "status")
        echo "检查 \$SERVICE_NAME 状态..."
        docker compose ps
        ;;
    *)
        echo "使用方法: \$0 {start|stop|restart|logs|status}"
        exit 1
        ;;
esac
EOF
    chmod +x "../$service/deploy.sh"
}

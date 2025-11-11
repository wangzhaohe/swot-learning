#!/bin/bash

# Spring Cloud 微服务统一部署管理脚本
source "$(dirname "$0")/docker-config.sh"

case "$1" in
    "init")
        echo -e "${BLUE}开始初始化 Docker 部署环境...${NC}"
        
        # 1. 为每个服务创建 Dockerfile
        echo -e "${YELLOW}步骤 1: 为每个服务创建 Dockerfile${NC}"
        for service in $(get_services); do
            if [ -f "../$service/Dockerfile" ]; then
                echo -e "  $service/Dockerfile 已存在，跳过"
            else
                echo -e "  创建 $service/Dockerfile"
                cp Dockerfile.template "../$service/Dockerfile"
                
                # 根据服务特性调整 Dockerfile
                port=$(get_service_port "$service")
                sed -i '' "s/COPY target\/\*\.jar app\.jar/COPY target\/$service-*.jar app.jar/" "../$service/Dockerfile"
                sed -i '' "s/EXPOSE 8080/EXPOSE $port/" "../$service/Dockerfile"
                
                # config-server 特殊处理：需要复制配置文件
                if [ "$service" = "config-server" ]; then
                    echo "COPY src/main/resources/config-repo /app/config-repo" >> "../$service/Dockerfile"
                fi
            fi
        done

        # 2. 为每个服务创建构建脚本
        echo -e "${YELLOW}步骤 2: 为每个服务创建构建脚本${NC}"
        for service in $(get_services); do
            if [ -f "../$service/build-docker.sh" ]; then
                echo -e "  $service/build-docker.sh 已存在，跳过"
            else
                echo -e "  创建 $service/build-docker.sh"
                create_build_script "$service"
            fi
        done

        # 3. 为每个服务创建部署脚本
        echo -e "${YELLOW}步骤 3: 为每个服务创建部署脚本${NC}"
        for service in $(get_services); do
            if [ -f "../$service/deploy.sh" ]; then
                echo -e "  $service/deploy.sh 已存在，跳过"
            else
                echo -e "  创建 $service/deploy.sh"
                create_deploy_script "$service"
            fi
        done


        # 5. 设置脚本执行权限
        echo -e "${YELLOW}步骤 5: 设置脚本执行权限${NC}"
        chmod +x deploy.sh
        for service in $(get_services); do
            if [ -f "../$service/build-docker.sh" ]; then
                chmod +x "../$service/build-docker.sh"
            fi
            if [ -f "../$service/deploy.sh" ]; then
                chmod +x "../$service/deploy.sh"
            fi
        done

        echo -e "${GREEN}Docker 部署环境初始化完成！${NC}"
        echo ""
        echo -e "${BLUE}下一步操作:${NC}"
        echo "1. 构建所有服务: ./deploy.sh build-all"
        echo "2. 启动集群: ./deploy.sh start"
        echo "3. 检查状态: ./deploy.sh status"
        ;;
    
    "build-all")
        echo -e "${BLUE}开始构建所有微服务...${NC}"
        for service in $(get_services); do
            echo -e "${YELLOW}构建服务: $service${NC}"
            cd "../$service" || exit 1
            if [ -f "build-docker.sh" ]; then
                ./build-docker.sh
            else
                echo -e "${RED}服务 $service 缺少构建脚本，请先运行: ./deploy.sh init${NC}"
            fi
            cd ../docker-deploy
        done
        echo -e "${GREEN}所有服务构建完成！${NC}"
        ;;
    
    "start")
        echo -e "${BLUE}启动 Spring Cloud 微服务集群...${NC}"
        
        # 检查是否所有镜像都已构建
        echo -e "${YELLOW}检查镜像是否存在...${NC}"
        all_images_exist=true
        for service in $(get_services); do
            image_name="spring-cloud-$service:1.0"
            if ! docker image inspect "$image_name" &>/dev/null; then
                echo -e "${RED}镜像 $image_name 不存在，请先运行: ./deploy.sh build-all${NC}"
                all_images_exist=false
            fi
        done
        
        if [ "$all_images_exist" = false ]; then
            exit 1
        fi
        
        echo -e "${GREEN}所有镜像检查通过，启动集群...${NC}"
        docker compose up -d
        
        # 使用智能等待基础设施服务
        wait_for_infrastructure
        
        echo -e "${BLUE}检查所有服务状态...${NC}"
        ./deploy.sh status
        ;;
    
    "stop")
        echo -e "${YELLOW}停止 Spring Cloud 微服务集群...${NC}"
        docker compose down
        echo -e "${GREEN}所有服务已停止${NC}"
        ;;
    
    "restart")
        echo -e "${YELLOW}重启 Spring Cloud 微服务集群...${NC}"
        docker compose down
        docker compose up -d
        echo -e "${GREEN}所有服务已重启${NC}"
        ;;
    
    "logs")
        echo -e "${BLUE}查看微服务集群日志...${NC}"
        echo "选择要查看的服务:"
        echo "1) config-server (配置中心)"
        echo "2) eureka-server (注册中心)"
        echo "3) api-gateway (API网关)"
        echo "4) service-item (商品服务)"
        echo "5) service-order (订单服务)"
        echo "6) 所有服务"
        read -p "请输入选择 (1-6): " choice
        
        case $choice in
            1) docker compose logs -f config-server ;;
            2) docker compose logs -f eureka-server ;;
            3) docker compose logs -f api-gateway ;;
            4) docker compose logs -f service-item ;;
            5) docker compose logs -f service-order ;;
            6) docker compose logs -f ;;
            *) echo -e "${RED}无效选择${NC}" ;;
        esac
        ;;
    
    "status")
        echo -e "${BLUE}检查微服务集群状态...${NC}"
        for service in $(get_services); do
            check_service_health "$service"
        done
        ;;
    
    "scale")
        if [ -z "$2" ] || [ -z "$3" ]; then
            echo -e "${RED}使用方法: $0 scale <服务名> <实例数量>${NC}"
            echo "可用服务: $(get_services)"
            exit 1
        fi
        echo -e "${BLUE}扩展服务 $2 到 $3 个实例...${NC}"
        docker compose up -d --scale "$2"="$3"
        ;;
    
    *)
        echo -e "${BLUE}Spring Cloud 微服务统一部署管理工具${NC}"
        echo ""
        echo -e "${GREEN}使用方法: $0 {init|build-all|start|stop|restart|logs|status|scale}${NC}"
        echo ""
        echo -e "${YELLOW}命令说明:${NC}"
        echo "  init       - 初始化部署环境（首次使用）"
        echo "  build-all  - 构建所有微服务"
        echo "  start      - 启动集群"
        echo "  stop       - 停止集群"
        echo "  restart    - 重启集群"
        echo "  logs       - 查看日志"
        echo "  status     - 检查状态"
        echo "  scale      - 扩展服务实例"
        echo ""
        echo -e "${YELLOW}部署流程:${NC}"
        echo "  1. 首次使用: ./deploy.sh init"
        echo "  2. 构建服务: ./deploy.sh build-all"
        echo "  3. 启动集群: ./deploy.sh start"
        echo "  4. 检查状态: ./deploy.sh status"
        echo ""
        echo -e "${YELLOW}示例:${NC}"
        echo "  $0 init          # 初始化部署环境"
        echo "  $0 build-all     # 构建所有服务"
        echo "  $0 start         # 启动集群"
        echo "  $0 scale service-item 2  # 扩展商品服务到2个实例"
        echo ""
        echo -e "${BLUE}服务端口映射:${NC}"
        for service in $(get_services); do
            port=$(get_service_port "$service")
            container_name=$(get_container_name "$service")
            echo "  $container_name: http://localhost:$port"
        done
        exit 1
        ;;
esac

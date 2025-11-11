# Spring Cloud 微服务 Docker 部署指南

## 项目架构概述

本项目是一个完整的 Spring Cloud 微服务架构，包含以下服务：

- **config-server** (8888) - 配置中心
- **eureka** (8761) - 服务注册中心
- **gateway** (8080) - API 网关
- **service-item** (8081) - 商品服务
- **service-order** (8091) - 订单服务

## 部署架构

### 统一部署

**优点：**
- 统一管理所有服务
- 服务间依赖关系清晰
- 一键启动/停止整个集群
- 便于服务发现和配置管理

**文件结构：**
```
SpringCloud/
├── deploy.sh                   # 主部署脚本（入口）
├── docker-deploy/              # 专门的部署文件目录
│   ├── deploy.sh              # 实际部署逻辑
│   ├── docker-config.sh       # 共享配置和函数
│   ├── Dockerfile.template    # Dockerfile 模板
│   ├── docker-compose.yml     # 服务编排配置
│   └── SPRING_CLOUD_DOCKER_DEPLOYMENT.md  # 部署文档
├── config-server/
│   ├── Dockerfile
│   ├── build-docker.sh
│   └── deploy.sh
├── eureka/
│   ├── Dockerfile
│   ├── build-docker.sh
│   └── deploy.sh
├── gateway/
│   ├── Dockerfile
│   ├── build-docker.sh
│   └── deploy.sh
├── service-item/
│   ├── Dockerfile
│   ├── build-docker.sh
│   └── deploy.sh
└── service-order/
    ├── Dockerfile
    ├── build-docker.sh
    └── deploy.sh
```

**模块化设计优势：**
- **清晰的目录结构**：所有 Docker 部署相关文件集中在 `docker-deploy` 目录
- **配置与逻辑分离**：共享配置在 `docker-config.sh`，部署逻辑在 `deploy.sh`
- **可复用的工具函数**：颜色定义、服务端口映射、容器命名等统一管理
- **标准化的脚本模板**：构建脚本、部署脚本、Dockerfile 模板化
- **易于维护和扩展**：新增服务只需在配置函数中添加，无需修改主逻辑
- **系统兼容性**：使用函数方式替代关联数组，确保在 macOS 等系统上正常运行

**核心配置函数：**
```bash
# 获取服务列表
get_services() {
    echo "config-server eureka gateway service-item service-order"
}

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
```

**构建脚本功能：**
- **完整构建**: 执行 Maven 清理、打包和 Docker 构建
- **只构建 Docker**: 使用 `--skip-maven` 参数跳过 Maven 构建，只构建 Docker 镜像
- **灵活部署**: 支持根据需求选择构建方式


## 快速开始

### 前提条件
- Docker 和 Docker Compose 已安装
- Maven 已安装（用于构建项目）

### 部署步骤

1. **初始化部署环境**
```bash
# 运行统一部署脚本的初始化命令
./deploy.sh init
```

2. **构建所有服务**
```bash
./deploy.sh build-all
```

3. **启动集群**
```bash
./deploy.sh start
```

4. **验证部署**
```bash
./deploy.sh status
```

## 服务配置说明

### 端口映射
- Config Server: `http://localhost:8888`
- Eureka Server: `http://localhost:8761`
- API Gateway: `http://localhost:8080`
- Service Item: `http://localhost:8081`
- Service Order: `http://localhost:8091`

### 环境变量配置
每个服务都配置了以下环境变量：
- `SPRING_PROFILES_ACTIVE=docker` - Docker 环境配置
- `JAVA_OPTS=-Xmx512m -Xms256m` - JVM 内存配置
- `SPRING_CLOUD_CONFIG_URI=http://config-server:8888` - 配置中心地址
- `EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://eureka-server:8761/eureka/` - 注册中心地址

### 服务依赖关系
```
config-server (最先启动)
    ↓
eureka-server
    ↓
api-gateway, service-item, service-order (并行启动)
```

## 单独服务构建指南

### 为什么需要单独构建

在开发过程中，经常需要单独构建某个服务，特别是：
- **修改配置后**：config-server 的配置文件更新
- **代码修改后**：某个服务的业务逻辑变更
- **快速测试**：只重新构建特定服务进行测试

### config-server 配置修改场景

当修改 config-server 的配置文件时，需要重新构建 Docker 镜像：

**配置文件位置**：
```
config-server/src/main/resources/config-repo/
├── app-order.yml
├── app-order-dev.yml
├── app-order-docker.yml
├── app-order-prod.yml
└── application.yml
```

**修改配置后的构建流程**：
1. 修改配置文件
2. 重新构建 config-server
3. 重启 config-server 容器
4. 业务服务通过 refresh 端点获取新配置

### 单独构建方法

#### 1. 使用 build-docker.sh（推荐）

**在服务目录下执行**：
```bash
# 完整构建（包含 Maven 打包）
cd config-server
./build-docker.sh

# 快速构建（跳过 Maven，只构建 Docker 镜像）
./build-docker.sh --skip-maven
```

#### 2. 使用部署脚本的单独构建

**在 docker-deploy 目录下**：
```bash
# 构建单个服务
cd docker-deploy
./build-docker-only.sh config-server

# 或者使用 deploy.sh 的批量构建（会构建所有服务）
./deploy.sh build-all
```

#### 3. 手动构建步骤

**如果 build-docker.sh 不存在**：
```bash
cd config-server

# 清理并构建项目
mvn clean package -DskipTests

# 构建 Docker 镜像
docker build -t spring-cloud-config-server:1.0 .
```

#### 4. 快速构建（跳过 Maven）

**如果只需要重新构建 Docker 镜像**：
```bash
cd config-server

# 直接构建 Docker 镜像（假设 JAR 文件已存在）
docker build -t spring-cloud-config-server:1.0 .
```

### config-server 配置更新完整流程

```bash
# 1. 修改配置文件
vi src/main/resources/config-repo/app-order-docker.yml

# 2. 重新构建 config-server（跳过 Maven 构建）
./build-docker.sh --skip-maven

# 3. 重启 config-server 容器
cd ../docker-deploy
docker compose up -d config-server

# 4. 等待配置服务器启动
sleep 10

# 5. 刷新业务服务配置（不重启业务服务）
curl -X POST http://localhost:8091/actuator/refresh

# 6. 验证配置更新
curl http://localhost:8091/actuator/env/resilience4j.circuitbreaker.configs.default.failure-rate-threshold
```

### 构建脚本功能对比

| 构建方式 | 命令 | 适用场景 | 优点 |
|---------|------|----------|------|
| 完整构建 | `./build-docker.sh` | 代码或配置修改 | 确保代码和配置同步 |
| 快速构建 | `./build-docker.sh --skip-maven` | 仅配置修改 | 速度快，节省时间 |
| 批量构建 | `./deploy.sh build-all` | 首次部署或全量更新 | 统一管理，确保一致性 |
| 单独构建 | `./build-docker-only.sh <service>` | 特定服务更新 | 灵活，针对性强 |

## 集群管理命令

### 使用统一管理脚本
```bash
# 构建所有服务
./deploy.sh build-all

# 只构建 Docker 镜像（跳过 Maven 构建）
for service in config-server eureka gateway service-item service-order; do
    cd "$service" && ./build-docker.sh --skip-maven && cd ..
done

# 启动集群
./deploy.sh start

# 停止集群
./deploy.sh stop

# 重启集群
./deploy.sh restart

# 查看日志
./deploy.sh logs

# 检查状态
./deploy.sh status

# 扩展服务实例
./deploy.sh scale service-item 2
```

### 使用 Docker Compose 直接管理
```bash
# 启动所有服务
docker-compose up -d

# 停止所有服务
docker-compose down

# 查看服务状态
docker-compose ps

# 查看服务日志
docker-compose logs [service-name]

# 扩展服务实例
docker-compose up -d --scale service-item=2
```


## 配置管理

### 配置中心
配置服务器使用本地文件模式，配置文件位于：
```
config-server/src/main/resources/config-repo/
```

### 服务配置
每个服务通过配置中心获取配置，支持多环境：
- 默认配置：`application.yml`
- 开发环境：`app-{service}-dev.yml`
- 生产环境：`app-{service}-prod.yml`

## 健康检查

所有服务都配置了健康检查，可以通过以下方式验证：

```bash
# 配置中心健康检查
curl http://localhost:8888/actuator/health

# 注册中心健康检查
curl http://localhost:8761/actuator/health

# 查看注册的服务实例
curl http://localhost:8761/eureka/apps
```

## 故障排除

### 常见问题

1. **服务启动顺序问题**
   - 确保 config-server 最先启动
   - 确保 eureka-server 在业务服务之前启动

2. **网络连接问题**
   - 检查 Docker 网络配置
   - 验证服务间网络连通性

3. **配置加载失败**
   - 检查配置中心是否正常运行
   - 验证配置文件格式是否正确

4. **服务注册失败**
   - 检查 Eureka 服务器状态
   - 验证服务配置中的 Eureka 地址

### 日志查看
```bash
# 查看所有服务日志
./deploy.sh logs

# 查看特定服务日志
docker-compose logs -f service-name

# 实时查看服务启动日志
docker-compose up --build

# 查看负载均衡分布（适用于多实例服务）
# 查看第一个实例
docker logs service-item | grep "Processing request"

# 查看第二个实例  
docker logs service-item-8082 | grep "Processing request"
```

## 开发建议

### 开发环境
- 使用统一部署便于整体测试
- 配置热加载便于开发调试
- 使用本地配置文件模式

### 生产环境
- 考虑使用外部配置仓库（如 Git）
- 配置适当的监控和告警
- 设置合理的资源限制
- 考虑服务的高可用部署

## 清理资源

### 容器管理命令对比

| 命令 | 作用 | 对数据的影响（未使用卷） | 对数据的影响（使用命名卷） |
|------|------|--------------------------|---------------------------|
| `docker-compose stop` | 停止容器 | 数据保留在已停止的容器内 | 数据安全地保存在卷中 |
| `docker-compose down` | 停止并删除容器、网络 | **数据丢失！** | 数据安全（卷不会被删除） |
| `docker-compose down -v` | down + 删除命名卷 | 数据丢失 | **数据丢失！** |

### 安全清理操作

```bash
# 停止并删除所有容器和网络（保留数据卷）- 推荐日常使用
docker-compose down

# 停止并删除所有容器、网络和数据卷（完全清理）- 谨慎使用
docker-compose down -v

# 手动清理构建文件（可选）- 谨慎使用
mvn clean
```

### 注意事项
- **镜像清理**：不建议自动删除镜像，建议手动确认后删除
- **构建文件**：使用 `mvn clean` 比直接删除 `target` 目录更安全
- **数据备份**：重要数据请提前备份

---

**最佳实践建议：**
1. 开发环境使用统一部署，便于整体测试
2. 生产环境考虑服务分离部署，提高可用性
3. 配置适当的资源限制和监控
4. 使用版本标签管理 Docker 镜像
5. 定期备份配置数据

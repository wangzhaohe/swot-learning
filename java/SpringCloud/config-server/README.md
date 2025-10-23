# Spring Cloud Config Server 配置中心

这是一个基于 Spring Cloud Config Server 的配置中心，用于演示微服务配置管理功能。

## 功能特性

- ✅ 本地文件系统配置仓库
- ✅ 多环境配置支持 (dev, prod)
- ✅ 服务特定配置
- ✅ 全局共享配置
- ✅ Actuator 健康检查
- ✅ RESTful API 接口

## 配置文件结构

```
config-repo/
├── application.yml          # 全局配置文件
├── service-order.yml        # service-order 默认配置
├── service-order-dev.yml    # service-order 开发环境配置
└── service-order-prod.yml   # service-order 生产环境配置（一般就是 git 的主分支）
```

## 分支与环境说明

- **prod 环境**：生产环境配置，性能优化（作为主分支）
- **dev 环境**：开发环境配置，包含新功能测试

## 启动配置中心

1. 在 IDEA 中打开 `config-server` 模块
2. 运行 `ConfigServerApplication` 主类
3. 配置中心将在 `http://localhost:8888` 启动
4. 使用 `native` profile 启用本地文件系统配置仓库

## 测试配置中心 API

### 1. 获取服务配置

```bash
# 获取 service-order 主分支配置
curl http://localhost:8888/service-order/master

# 获取 service-order 默认配置（等同于 master）
curl http://localhost:8888/service-order/default

# 获取 service-order 开发环境配置
curl http://localhost:8888/service-order/dev

# 获取 service-order 生产环境配置
curl http://localhost:8888/service-order/prod
```

### 2. 健康检查

```bash
# 检查配置中心健康状态
curl http://localhost:8888/actuator/health
```

## 配置示例

### 默认配置 (service-order.yml)
- 端口: 8081
- H2 内存数据库 (testdb)
- 开发友好的 JPA 设置
- 演示功能启用
- 版本: 1.0.0

### 开发环境配置 (service-order-dev.yml)
- 端口: 8082
- H2 内存数据库 (devdb)
- 调试模式启用
- 详细日志级别 (TRACE)
- 更宽松的限制
- 版本: 1.1.0-dev

### 生产环境配置 (service-order-prod.yml)
- 端口: 8080
- MySQL 数据库
- 生产级 JPA 设置
- 性能优化配置
- 版本: 1.0.0

## 在客户端使用配置中心

要在其他微服务中使用配置中心，需要在 `application.yml` 中添加：

```yaml
spring:
  cloud:
    config:
      uri: http://localhost:8888
      name: service-order    # 配置文件名
      profile: dev          # 环境配置
```

## 演示要点

1. **配置集中管理** - 所有微服务的配置都在配置中心统一管理
2. **环境隔离** - 不同环境使用不同的配置文件
3. **配置继承** - 服务配置继承全局配置
4. **动态刷新** - 支持配置的动态刷新（需要配合 @RefreshScope）
5. **版本控制** - 配置文件的版本管理

## 下一步

1. 启动配置中心服务
2. 在其他微服务中配置 config client
3. 测试配置获取和刷新功能
4. 演示多环境配置切换

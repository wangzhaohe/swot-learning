# Spring Boot 微服务 Dockerfile 模板
# 使用稳定的基础镜像
FROM alpine:latest

# 安装 OpenJDK 8
RUN apk add --no-cache openjdk8-jre

# 配置时区
RUN apk add --no-cache tzdata && \
    cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && \
    echo "Asia/Shanghai" > /etc/timezone && \
    apk del tzdata

# 设置工作目录
WORKDIR /app

# 复制构建好的 JAR 文件到容器中
# 注意：需要根据实际项目修改 JAR 文件名
COPY target/service-item-*.jar app.jar

# 暴露应用端口
# 注意：需要根据实际项目修改端口号
EXPOSE 8081

# 设置 JVM 参数
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# 运行应用
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

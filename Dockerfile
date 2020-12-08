#syntax=harbor.aigauss.com/docker/dockerfile:experimental
# 第一行必须保留
# 使用 docker 的扩展功能 build 镜像

# ---- build 阶段，主要用于 build node 项目 ---- #
# 使用 jdk 8  build 为 builder
FROM harbor.aigauss.com/base-images/maven:3-jdk-8-slim as builder

# 配置默认工作空间
WORKDIR /app
# copy pom.xml 文件
COPY ./pom.xml .
RUN mvn dependency:go-offline
# 复制所有文件到默认项目空间
# build 项目
COPY . .
RUN mvn dependency:purge-local-repository -DgroupId==com.st && \
    mvn -DskipTests clean package
RUN mv target/*.jar target/result.jar

# ---- 创建 nginx 镜像，为最终使用的镜像 ----#
FROM harbor.aigauss.com/base-images/openjdk:8 as app

ENV JAR_PATH=/opt/${PROJECT}/${PROJECT}.jar

#copy builder （上一个阶段的容器） 的 /app/target/result.jar  目录到当前镜像
COPY --from=builder /app/target/result.jar $JAR_PATH

EXPOSE 8080
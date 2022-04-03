#基础镜像
FROM java:8

#作者信息
MAINTAINER "ZenoFung zenofung911@gmail.com"

#添加一个存储空间
VOLUME /tmp


#暴露8080端口
EXPOSE 8080


#COPY *.jar /app.jar
ARG JAR_FILE=target/winegame.jar

#往容器添加jar
#ADD *.jar app.jar
ADD ${JAR_FILE} app.jar

#启动镜像运行程序
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]


#linux 命令
# docker build -t mywine .
# docker run -d -p8080:9000 --name myspringboot   容器id
#springboot 启动命令  https://blog.csdn.net/qq_35181790/article/details/105041699
# nohup java -jar syex-grpc.jar >nohup.log 2>&1 &
#  ps -ef |grep wine
#  kill -9   id
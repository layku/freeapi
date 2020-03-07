# 懒人接口项目
网络上免费的API接口调用方式

#### 介绍
网络上免费的API接口调用方式测试项目：主要是为了帮助大家更好的使用网上的免费资源

#### 软件打包命令
command : clean package
profile : -Dfile.encoding=UTF-8 -DskipTests

#### window软件运行命令
java -jar -Dspring.profiles.active=prod -Djasypt.encryptor.password=自己的加密盐值 freeapi.jar

#### linux软件运行命令
nohup java -jar -Dspring.profiles.active=prod -Djasypt.encryptor.password=自己的加密盐值 freeapi.jar >> freeapi.log &

#### 项目大纲
1. 发送Email邮件(QQ邮箱和ali的邮箱服务两种方式)........完成时间：2020-03-07
2. 

#### 本地启动参数配置

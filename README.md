# 懒人接口项目
搜集网络上免费的API接口调用方式,帮助大家更快找到自己需要的接口

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
2. 根据IP查询地址(百度地图方式和太平洋服务两种方式)......完成时间：2020-03-08
3. 获取2011-2020年所有假日和工作日(持续更新)...........完成时间：2020-03-11
4. 爬取国家统计局省市区街道社区数据....................完成时间：2020-03-14

#### 本地启动参数配置
![image](https://github.com/layku/freeapi/blob/master/readmeImg/configParam.png)

# xiaoyaoji_two_development
小幺鸡的二次开发   
用于自动化添加 返回参数的含义注释

## 使用技术
1. springboot;
2. mybatis;
3. maven
4. lombok
5. druid

## mybatis
1. 生成代码文件 :/Users/whuanghkl/code/mygit/demo/xiaoyaoji_two_development/src/main/resources/mybatis/MybatisGenerator.xml
2. springboot 配置文件:/Users/whuanghkl/code/mygit/demo/xiaoyaoji_two_development/src/main/resources/application.properties

## 数据库脚本
修改 表 Interface中的responseargs 类型为longText

## 启动/服务器部署

```
 nohup  java -Dfile.encoding=UTF-8 -Dspring.profiles.active=prod   -jar xiaoyaoji_two_development-0.0.1-SNAPSHOT.jar  --server.port=8083 &
```

## 停止springboot服务
curl -X POST  http://xyj.yhskyc.com/shutdown
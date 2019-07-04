[TOC]

### api-manager描述:

`api-manager`服务主要提供对API功能的操作及API市场的相关功能，主要包括API导入,创建API、部署API、订阅API、API列表相关查询等服务。

### 基础软件:

1. `git`
2. `jdk1.8`
3. `redis3.2`
4. `mysql5.1.6`

### 服务启动:

​    确保`ibp-config-repo`,`mysql`、`redis`都已经起来。

#### local profile配置文件:

```yaml
server:
  port: 9001 #服务端口[必须项但可修改]
spring:
  application:
    name: api-manager #服务名称[必须项但可修改]
  datasource: #数据源配置[必须项但可修改]
    hikari:
      idle-timeout: 60000
      maximum-pool-size: 10
      minimum-idle: 10
      connection-timeout: 20000
      connection-test-query: SELECT 1
      pool-name: HikariPool
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://cmb-ibp-dev.c2lighgovwg5.rds.cn-northwest-1.amazonaws.com.cn/ibp?useSSL=false&?useUnicode=true&characterEncoding=UTF-8&verifyServerCertificate=false&allowMultiQueries=true
    username: ibp
    password: bosicloud.com
  redis: #redis配置[必须项但可修改]
    host: 127.0.0.1
    port: 6379
    password:
  session:
    store-type: redis
  sleuth:
    sampler:
      percentage: 1
  zipkin: #zipkin配置
    base-url: http://ibp-dev-inner-dont-delete-1771010191.cn-northwest-1.elb.amazonaws.com.cn:9030
ibp: #程序中需要的动态属性配置[必须项但可修改]
  aws:
    region: cn-northwest-1
mapper: #Mapper框架配置[必须项但不可修改]
  mappers:
    - tk.mybatis.mapper.common.Mapper
  notEmpty: false
  identity: MYSQL
mybatis:
  configuration:
    map-underscore-to-camel-case: true
  mapper-locations: classpath*:META-INF/mapper/**/*.xml
hystrix: #健康检查配置[必须项但可修改]
  command:
    default:
      circuitBreaker:
        requestVolumeThreshold: 20
        sleepWindowInMilliseconds: 5000
        errorThresholdPercentage: 60
        enabled: true
      execution:
        isolation:
          thread:
            timeoutInMilliseconds: 50000
```



####    启动参数:

| 参数名                         | 描述                                         |
| :----------------------------- | :------------------------------------------- |
| config_active                  | ibp-config-repo服务中profile配置文件后缀     |
| config_url                     | ibp-config-repo服务的访问URL                 |
| config_sqs_request             | ibp系统中统一的请求队列名可自定义,但必须统一 |
| config_sqs_response_apimanager | apimanager服务监听返回的结果的队列名         |

#### spring-Boot命令启动:

```markdown
spring-boot:run 
-Dconfig_active=lvning 
-Dconfig_url=http://127.0.0.1:9010 
-Dconfig_sqs_request=request_test 
-Dconfig_sqs_response_apimanager=response_apimanager_test
-f pom.xml
```

  




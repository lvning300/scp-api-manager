
### scp-api-manager描述:

`scp-api-manager`服务主要提供对API功能的操作及API市场的相关功能，主要包括API导入,创建API、部署API、订阅API、API列表相关查询等服务。

### 基础软件:

1. `git`
2. `jdk1.8`
3. `redis3.2`
4. `mysql5.1.6`

### 服务启动:

​    确保`config-repo`,`mysql`、`redis`都已经起来。

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
    url: jdbc:mysql://localhost:3306/ibp?useSSL=false&?useUnicode=true&characterEncoding=UTF-8&verifyServerCertificate=false&allowMultiQueries=true
    username: root
    password: 123456
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
    base-url: http://localhost:9030
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
| config_active                  | config-repo服务中profile配置文件后缀     |
| config_url                     | config-repo服务的访问URL                 |
| config_sqs_request             | 系统中统一的请求队列名可自定义,但必须统一 |
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

```markdown

经常使用

✅autoCommit
此属性控制从池返回的连接的默认自动提交行为。它是一个布尔值。 默认值：true

⌚ connectionTimeout
此属性控制客户端（即您）等待池中连接的最大毫秒数。如果在没有连接可用的情况下超过此时间，则将抛出SQLException。最低可接受的连接超时为250毫秒。 默认值：30000（30秒）

⌚ idleTimeout
此属性控制允许连接在池中空闲的最长时间。 此设置仅在minimumIdle定义为小于时才适用maximumPoolSize。一旦池到达连接， 空闲连接将不会退出minimumIdle。连接是否空闲退出的最大变化为+30秒，平均变化为+15秒。在此超时之前，连接永远不会被空闲。值为0表示永远不会从池中删除空闲连接。允许的最小值为10000毫秒（10秒）。 默认值：600000（10分钟）

⌚ maxLifetime
此属性控制池中连接的最长生命周期。使用中的连接永远不会退役，只有当它关闭时才会被删除。在逐个连接的基础上，应用轻微的负衰减以避免池中的大量灭绝。 我们强烈建议设置此值，它应比任何数据库或基础结构强加的连接时间限制短几秒。 值0表示没有最大寿命（无限寿命），当然主题是idleTimeout设置。 默认值：1800000（30分钟）

🔠connectionTestQuery
如果您的驱动程序支持JDBC4，我们强烈建议您不要设置此属性。这适用于不支持JDBC4的“遗留”驱动程序Connection.isValid() API。这是在从池中给出连接之前执行的查询，以验证与数据库的连接是否仍然存在。再次尝试运行没有此属性的池，如果您的驱动程序不符合JDBC4，HikariCP将记录错误以通知您。 默认值：无

🔢minimumIdle
此属性控制HikariCP尝试在池中维护的最小空闲连接数。如果空闲连接低于此值并且池中的总连接数小于maximumPoolSize，则HikariCP将尽最大努力快速有效地添加其他连接。但是，为了获得最高性能和对峰值需求的响应，我们建议不要设置此值，而是允许HikariCP充当固定大小的连接池。 默认值：与maximumPoolSize相同

🔢maximumPoolSize
此属性控制允许池到达的最大大小，包括空闲和正在使用的连接。基本上，此值将确定数据库后端的最大实际连接数。对此的合理值最好由您的执行环境决定。当池达到此大小且没有空闲连接可用时，对getConnection（）的调用将connectionTimeout在超时前阻塞最多毫秒。请阅读有关游泳池尺寸的信息。 默认值：10

📈metricRegistry
此属性仅可通过编程配置或IoC容器获得。此属性允许您指定池使用的Codahale / Dropwizard 实例MetricRegistry来记录各种度量标准。有关 详细信息，请参阅度量维基页面。 默认值：无

📈healthCheckRegistry
此属性仅可通过编程配置或IoC容器获得。此属性允许您指定池使用的Codahale / Dropwizard 实例HealthCheckRegistry来报告当前的健康信息。有关 详细信息，请参阅运行状况检查维基页面。 默认值：无

🔠poolName
此属性表示连接池的用户定义名称，主要显示在日志记录和JMX管理控制台中，以标识池和池配置。 默认值：自动生成

```
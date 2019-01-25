# spring-boot-learning-log

日志框架有Java 原生的 `java.util.logging` 、Apache 的`log4j`、`log4j2`、`logback` 、`slf4j`等多种日志框架。下面从日志框架的设计以及各框架的优缺点，最后到Spring boot logging 对个框架的知识点进行简单的学习。

### Log4j

Log4j 是目前最流行的Java日志框架之一，虽然在2015年8月已经停滞发展，并逐渐被log4j2和logback等日志框架所代替，可以无法样式光辉历程及优良的设计。

#### 整体架构

![1548404330751](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1548404330751.png)

#### Log4j API

- 日志对象 `org.apache.log4j.Logger`
- 日志级别`org.apache.log4j.Level`
- 日志管理器`org.apache.log4j.LogManager`
- 日志仓库`org.apache.log4j.spi.LogRepository`
- 日志附加器`org.apache.log4j.Appender`
- 日志过滤器`org.apache.log4j.spi.Filter`
- 日志格式布局`org.apache.log4j.Layout`
- 日志事件`org.apache.log4j.LoggingEvent`
- 日志配置器`org.apache.log4j.Configurator`
- 日志诊断上下文`org.apache.log4j.NDC`和`org.apache.log4j.MDC`

#### Logger Methed

- 创建Logger
  - `Loggger.getLogger("")`
- 输出日志
  - `logger.fatal()`
  - `logger.error()`
  - `logger.warn()`
  - `logger.info()`
  - `logger.debug()`

####  Logger Level

- OFF
- FATAL
- ERROR
- WARN
- INFO
- DEBUG
- ALL

#### 日志管理器


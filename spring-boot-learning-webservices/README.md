# Spring Boot Learning Web Services

Web Services又称为 Web Service，是一种通过网络来支持相互协作的机器间交互的软件系统。它拥有被机器可处理格式所描述的接口（如：WSDL），规定使用SOAP消息方式与其它系统交互，典型的以HTTP进行传输、XML进行序列化以及联合其它Web相关标准。

​                                                                                                                    ----- W3C Web Services Glossary

#### 标准整合方式

- XML

  Extensible Markup Language 可标记性语言。XML设计宗旨为了传输数据，而非显示数据。它的标签没有被预定义，你需要自己进行定义。

- SOAP

  Simple Object Access Protocol，一种在计算机网络中实现的Web Services的交换结构化信息的协议规范，它的目的在与促使可扩展性、中立性以及独立性。SOAP使用XML信息集合作为它的消息格式，并依赖于应用层协议，最常见的HTTP或者SMTP，用于消息协商和传输。

  **主要特性**

  - 可扩张性： 安全和Web Services寻址

  - 中立性：  可操作在任何的通信应用层协议上，如: HTTP、SMTP、TCP等

    > SMTP  简单的邮件传输协议

  - 独立性： 允许使用任何编程模型

  **SOAP 数据封装**

  - SOAP消息： 在SOAP节点之间表达交换的信息
  - SOAP信封： 鉴定SOAP消息的XML封装元素
  - SOAP头块： SOAP头的基本单元
  - SOAP头：     一个或多个SOAP头块集合
  - SOAP主体： 交给接收端包含消息的主体
  - SOAP故障： SOAP 节点故障时处理消息

- WSDL

  Web Services Description Language 指网络服务描述语言。WSDL是一种使用了XML编写的文档。这种文档可以描述某个Web Service。它规定服务的位置以及服务提供的操作(或方法)。

- UDDL 

  统一描述、发现和继承协议（Universal Description，Discovery and Integration）技术标准。它是一个全球化、与平台无关、开放式的架构

#### [XML Schema ](http://www.w3school.com.cn/schema/schema_elements_ref.asp)

- `schema`定义schema的根元素， 只有一个根
- `complexType`  定义复杂类型
- `sequence`要求子元素必须顺序出现，每个子元素出现0到任意次
  - `maxOccurs`指元素出现的最大次数，默认1
  - `minOccurs`指元素出现的最小次数，默认1。设置为0表示可选
- `element` 定义元素
  - `name`必填项， 指定元素的名称
  - `type`指定元素的类型

#### Schema转换Java 

```cmd
xjc -d xxxx  -p xxxx    xx.xsd 
# -d 代表生成文件的输出目录
# -p 指定输出类的报名
```

#### Spring API使用

- `@Endpoint` 创建一个端点，在这个端点中可以创建一个或多个处理传入请求的方法。该类是一个特殊的`Component`，用于处理Spring-WS 中的XML信息。
- `@PayloadRoot`告诉Spring-WS方法适用于处理XML消息。
  - `namespace` 设置命名空间
  - `localPart`本地部分
- `@RequestPayload` 消息的有效负载作为DOM元素在此方法上传递
- `@ResponsePayload`表示返回值用作响应消息的有效负载

#### 自动WSDL暴露

- `MessageDispatcherServlet` 会自动检测到`WsdlDefinition`在容器中定义的Bean。
- `WsdlDefinitionHandlerAdapter` 所有的`WsdlDefinition`的处理器
- `WsdlDefinition` 将WSDL暴露给客户端
- `XsdSchema` xsd Schema 的抽象


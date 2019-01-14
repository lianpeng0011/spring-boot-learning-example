# spring-boot-learning-WebSocket



#### 为什么不用HTTP协议

- HTTP 起初被设计为文本传输协议
- 半双工协议
- HTTP的限制，实现实时、双向的Web通信非常麻烦
- HTTP请求头和响应头造成不必要的网络开销



#### HTTP 曾作出过的努力

- AJAX (Asynchronous JavaScript and XML)
  - 特征
    - 页面无需整体刷新
    - 用户体现上感觉延迟低
  - 不足
    - 采用数据拉的模式
    - 无法避免HTTP请求头负载的问题
- Polling
  - 特征
    - 近乎于实时
    - 使用Ajax模拟实时通讯
    - 浏览器发送规律的请求
  - 不足
    - 消息低频的情况下，太多的连接没必要的打开和关闭
- Long Polling （又称异步Polling）
  - 特征
    - 浏览器发送请求到服务器，服务器保持请求打开一段时间
  - 不足
    - HTTP请求头造成大量的网路阻塞
    - 消息高频的情况下造成连续的拉循环

#### WebSocket 协议

WebSocket是一种通信协议，通过单个的TCP连接提供完全多双工(full-duplex)通讯管道。

WebSocket协议被Web浏览器和Web服务器实现，它能用于任何的客户端和服务器。WebScoket协议是基于TCP的独立协议

##### 支持WebSocket的浏览器

- IE 10+
- Firefox（PC、Android）11 + 
- Chrome（PC、Mobile）16+
- Safari（Mac、ios）6+
- Opera（PC、Mobile）12.10+

####  WebSocket 协议握手

为建立一个WebSocket连接，客户端发送WebSocket握手请求，服务器返回握手的响应。建立连接时使用的是HTTP协议，握手成功后，服务器和客户端使用socket套接字进行数据交互

- 客户端请求：

```url
GET /text HTTP/1.1
Upgrade: WebSocket
Connection: Upgrade
Host: www.example.com
Origin: http://example.com
WebSocket-Protocol: sample
```

- 服务器响应：

```url
HTTP/1.1 101 WebSocket Protocol Handshake
Upgrade: WebSocket
Connection: Upgrade
WebSocket-Origin: http://example.com
WebSocket-Location: ws://example.com/demo
Sec-WebSocket-Accept: 1qVdfYHU9hPOl4JYYNXF623Gzn0=
Sec-WebSocket-Protocol: v10.stomp
```

#### Java Web Socket API  （JSR-356）

- 端点 （EndPoint） 提供WebSocket 服务能力 读写等
- 连接（Connection）点和点的连接
- 对点（Peer）
- 会话（Session）Scoket Session 有状态Session
  - 接收消息 `javax.websocket.MessageHandler`
  - 发送消息 `javax.websocket.RemoteEndPoint.Basic`
- 客户端端点、服务端端点
- lifecycle
  - `@OnOpen` 此事件发生在建立连接时并且在任何事件之前
    - `Session` （可选参数）
    - `EndpointConfig`（可选参数）
  - `@OnMessage` 接收另一端的发送消息时触发的事件
    - 文本消息
    - 二进制消息
    - `pong`消息
  - `@OnClose` 关闭事件，生命周期最后一个事件
    - `Session`（可选参数）
    - `CloseReason`（可选参数）
  - `@OnError` 错误事件
    - `Session`（可选参数）
    - `Throwable` （必选参数） 错误信息

- 配置
  - 服务端配置（`javax.websocket.ServerEndpointConfig`）
    - `URI`映射
    - 子协议协商
    - 扩展点修改
    - Origin检测
    - 握手修改
    - 自定义端点创建
  - 客户端配置（`javx.websocket.ClientEndpointConfig`）
    - 子协议
    - 扩展点
    - 客户端配置修改

#### 使用@ServerEndpoint创建WebSocket端点

如果使用`@ServerEndpoint`嵌入式的Spring Boot应用程序中使用，则必须使用ServerEndpointExporter 进行注册。如果使用独立的servlet容器，不需要使用ServerEndpointExporter

```java
@Bean
public ServerEndpointExporter serverEndpointExporter(){
    return new ServerEndpointExporter();
}
```




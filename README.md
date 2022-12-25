### RPC-Framework

RPC（Remote Procedure Call Protocol）远程过程调用协议。一个通俗的描述是：客户端在不知道调用细节的情况下，调用存在于远程计算机上的某个对象，就像调用本地应用程序中的对象一样。

比较正式的描述是：一种通过网络从远程计算机程序上请求服务，而不需要了解底层网络技术的协议。

### 启动

1. 启动 Nacos （需运行在本地 `8848` 端口）

2. 启动服务端 NettyTestServer（可启动多个Server，在不同端口提供服务）

3. 启动客户端 NettyTestClient，客户端将输出方法调用结果。

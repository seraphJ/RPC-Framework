package top.gxj.test;

import top.gxj.rpc.api.HelloService;
import top.gxj.rpc.provider.ServiceProviderImpl;
import top.gxj.rpc.provider.ServiceProvider;
import top.gxj.rpc.socket.server.SocketServer;

/**
 * @author gxj
 * @date 2022/12/20 17:03
 */
public class TestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ServiceProvider serviceProvider = new ServiceProviderImpl();
        serviceProvider.addServiceProvider(helloService, "HelloService");
        SocketServer socketServer = new SocketServer(serviceProvider);
        socketServer.start(9000);
    }
}


package top.gxj.test;

import top.gxj.rpc.api.HelloService;
import top.gxj.rpc.registry.DefaultServiceRegistry;
import top.gxj.rpc.registry.ServiceRegistry;
import top.gxj.rpc.socket.server.SocketServer;

/**
 * @author gxj
 * @date 2022/12/20 17:03
 */
public class TestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ServiceRegistry serviceRegistry = new DefaultServiceRegistry();
        serviceRegistry.register(helloService);
        SocketServer socketServer = new SocketServer(serviceRegistry);
        socketServer.start(9000);
    }
}


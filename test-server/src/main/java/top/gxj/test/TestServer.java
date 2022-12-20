package top.gxj.test;

import jdk.nashorn.internal.ir.CallNode;
import lombok.Builder;
import top.gxj.rpc.api.HelloService;
import top.gxj.rpc.registry.DefaultServiceRegistry;
import top.gxj.rpc.registry.ServiceRegistry;
import top.gxj.rpc.server.RpcServer;

/**
 * @author gxj
 * @date 2022/12/20 17:03
 */
public class TestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ServiceRegistry serviceRegistry = new DefaultServiceRegistry();
        serviceRegistry.register(helloService);
        RpcServer rpcServer = new RpcServer(serviceRegistry);
        rpcServer.start(9000);
    }
}

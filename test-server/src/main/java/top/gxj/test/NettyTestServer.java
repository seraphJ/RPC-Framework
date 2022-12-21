package top.gxj.test;

import top.gxj.rpc.api.HelloService;
import top.gxj.rpc.netty.server.NettyServer;
import top.gxj.rpc.registry.DefaultServiceRegistry;
import top.gxj.rpc.registry.ServiceRegistry;

/**
 * @author gxj
 * @date 2022/12/21 17:39
 */
public class NettyTestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ServiceRegistry registry = new DefaultServiceRegistry();
        registry.register(helloService);
        NettyServer server = new NettyServer();
        server.start(9999);
    }
}

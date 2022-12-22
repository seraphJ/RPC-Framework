package top.gxj.test;

import top.gxj.rpc.api.HelloService;
import top.gxj.rpc.netty.server.NettyServer;
import top.gxj.rpc.provider.ServiceProviderImpl;
import top.gxj.rpc.provider.ServiceProvider;
import top.gxj.rpc.serializer.KryoSerializer;

/**
 * @author gxj
 * @date 2022/12/21 17:39
 */
public class NettyTestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        NettyServer server = new NettyServer("127.0.0.1", 9999);
        server.setSerializer(new KryoSerializer());
        server.publishService(helloService, HelloService.class);
    }
}

package top.gxj.test;

import top.gxj.rpc.api.HelloService;
import top.gxj.rpc.netty.server.NettyServer;
import top.gxj.rpc.serializer.KryoSerializer;

/**
 * @author gxj
 * @date 2022/12/21 17:39
 */
public class NettyTestServer1 {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        NettyServer server = new NettyServer("127.0.0.1", 9000);
        server.setSerializer(new KryoSerializer());
        server.publishService(helloService, HelloService.class);
    }
}

package top.gxj.test;

import top.gxj.rpc.api.HelloObject;
import top.gxj.rpc.api.HelloService;
import top.gxj.rpc.client.RpcClient;
import top.gxj.rpc.client.RpcClientProxy;
import top.gxj.rpc.entity.RpcRequest;
import top.gxj.rpc.loadbalancer.RoundRobinLoadBalancer;
import top.gxj.rpc.netty.client.NettyClient;
import top.gxj.rpc.serializer.KryoSerializer;

/**
 * @author gxj
 * @date 2022/12/21 17:44
 */
public class NettyTestClient {
    public static void main(String[] args) {
        RpcClient client = new NettyClient(new RoundRobinLoadBalancer());
        client.setSerializer(new KryoSerializer());
        RpcClientProxy rpcClientProxy = new RpcClientProxy(client);
        HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(12, "Hello. This is a message.");

        for (int i = 0; i < 10; i++) {
            String res = helloService.hello(object);
            System.out.println(res);
        }

    }
}

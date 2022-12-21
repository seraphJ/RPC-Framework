package top.gxj.test;

import top.gxj.rpc.api.HelloObject;
import top.gxj.rpc.api.HelloService;
import top.gxj.rpc.client.RpcClientProxy;
import top.gxj.rpc.socket.client.SocketClient;

/**
 * @author gxj
 * @date 2022/12/20 17:06
 */
public class TestClient {
    public static void main(String[] args) {
        SocketClient client = new SocketClient("127.0.0.1", 9000);
        RpcClientProxy proxy = new RpcClientProxy(client);
        HelloService helloService = proxy.getProxy(HelloService.class);
        HelloObject helloObject = new HelloObject(12, "This is a message.");
        String res = helloService.hello(helloObject);
        System.out.println(res);
    }
}

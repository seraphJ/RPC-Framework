package top.gxj.rpc.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.gxj.rpc.entity.RpcRequest;
import top.gxj.rpc.entity.RpcResponse;
import top.gxj.rpc.socket.client.SocketClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author gxj
 * @date 2022/12/20 15:59
 */
public class RpcClientProxy implements InvocationHandler {
//    private String host;
//    private int port;

    private static final Logger logger = LoggerFactory.getLogger(RpcClientProxy.class);

    private final RpcClient client;

    public RpcClientProxy(RpcClient rpcClient) {
        this.client = rpcClient;
    }

    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logger.info("调用方法：{}#{}", method.getDeclaringClass().getName(), method.getName());
        RpcRequest rpcRequest = new RpcRequest(method.getDeclaringClass().getName(), method.getName(), args, method.getParameterTypes());
//        SocketClient socketClient = new SocketClient(host, port);
//        return ((RpcResponse) socketClient.sendRequest(rpcRequest)).getData();
        return client.sendRequest(rpcRequest);
    }
}

package top.gxj.rpc.server;

import top.gxj.rpc.serializer.CommonSerializer;

/**
 * @author gxj
 * @date 2022/12/21 11:50
 */
public interface RpcServer {
    void start();
    void setSerializer(CommonSerializer serializer);
    <T> void publishService(Object service, Class<T> serviceClass);
}

package top.gxj.rpc.registry;

/**
 * @author gxj
 * @date 2022/12/20 17:35
 */
public interface ServiceRegistry {
    <T> void register(T service);
    Object getService(String serviceName);
}

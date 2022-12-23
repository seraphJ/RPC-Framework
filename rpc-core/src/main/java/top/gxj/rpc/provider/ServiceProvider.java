package top.gxj.rpc.provider;

/**
 * @author gxj
 * @date 2022/12/20 17:35
 */
public interface ServiceProvider {
    <T> void addServiceProvider(T service, String serviceName);
    Object getServiceProvider(String serviceName);
}

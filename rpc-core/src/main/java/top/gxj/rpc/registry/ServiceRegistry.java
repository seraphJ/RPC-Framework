package top.gxj.rpc.registry;

import java.net.InetSocketAddress;

/**
 * @author gxj
 * @date 2022/12/22 12:23
 */
public interface ServiceRegistry {
    void register(String serviceName, InetSocketAddress inetSocketAddress);
    InetSocketAddress lookupService(String serviceName);
}


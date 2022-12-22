package top.gxj.rpc.loadbalancer;

import com.alibaba.nacos.api.naming.pojo.Instance;

import java.util.List;

/**
 * @author gxj
 * @date 2022/12/22 16:41
 */
public interface LoadBalancer {
    Instance select(List<Instance> instances);
}

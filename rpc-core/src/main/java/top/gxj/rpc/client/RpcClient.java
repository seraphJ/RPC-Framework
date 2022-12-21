package top.gxj.rpc.client;

import top.gxj.rpc.entity.RpcRequest;

/**
 * @author gxj
 * @date 2022/12/21 11:45
 */
public interface RpcClient {
    Object sendRequest(RpcRequest rpcRequest);
}

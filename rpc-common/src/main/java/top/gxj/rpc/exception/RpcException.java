package top.gxj.rpc.exception;

import top.gxj.rpc.enumeration.RpcError;

/**
 * @author gxj
 * @date 2022/12/20 17:45
 */
public class RpcException extends RuntimeException {

    public RpcException(RpcError error, String detail) {
        super(error.getMessage() + ": " + detail);
    }

    public RpcException(String message, Throwable cause) {
        super(message, cause);
    }

    public RpcException(RpcError error) {
        super(error.getMessage());
    }

}

package top.gxj.rpc.socket.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.gxj.rpc.client.RpcClient;
import top.gxj.rpc.entity.RpcRequest;
import top.gxj.rpc.entity.RpcResponse;
import top.gxj.rpc.serializer.CommonSerializer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author gxj
 * @date 2022/12/20 16:10
 */
public class SocketClient implements RpcClient {
    private static final Logger logger = LoggerFactory.getLogger(SocketClient.class);

    private String host;
    private int port;

    public SocketClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object sendRequest(RpcRequest rpcRequest) {
        try (Socket socket = new Socket(host, port)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();
            return ((RpcResponse) objectInputStream.readObject()).getData();
        } catch (IOException | ClassNotFoundException e) {
            logger.error("调用时有错误发生：", e);
            return null;
        }
    }

    @Override
    public void setSerializer(CommonSerializer serializer) {

    }
}


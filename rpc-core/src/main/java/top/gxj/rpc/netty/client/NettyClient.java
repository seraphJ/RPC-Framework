package top.gxj.rpc.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.gxj.rpc.client.RpcClient;
import top.gxj.rpc.codec.CommonDecoder;
import top.gxj.rpc.codec.CommonEncoder;
import top.gxj.rpc.entity.RpcRequest;
import top.gxj.rpc.entity.RpcResponse;
import top.gxj.rpc.enumeration.RpcError;
import top.gxj.rpc.exception.RpcException;
import top.gxj.rpc.registry.NacosServiceRegistry;
import top.gxj.rpc.registry.ServiceRegistry;
import top.gxj.rpc.serializer.CommonSerializer;
import top.gxj.rpc.serializer.JsonSerializer;
import top.gxj.rpc.serializer.KryoSerializer;

import java.net.InetSocketAddress;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author gxj
 * @date 2022/12/21 13:29
 */
public class NettyClient implements RpcClient {

    private static final Logger logger = LoggerFactory.getLogger(NettyClient.class);

//    private static final Bootstrap bootstrap;
    private final ServiceRegistry serviceRegistry;
    private CommonSerializer serializer;


    public NettyClient() {
        this.serviceRegistry = new NacosServiceRegistry();
    }

//    static {
//        EventLoopGroup group = new NioEventLoopGroup();
//        bootstrap = new Bootstrap();
//        bootstrap.group(group)
//                .channel(NioSocketChannel.class)
//                .option(ChannelOption.SO_KEEPALIVE, true);
//    }

    @Override
    public Object sendRequest(RpcRequest rpcRequest) {
        if (serializer == null) {
            logger.error("未设置序列化器");
            throw new RpcException(RpcError.SERIALIZER_NOT_FOUND);
        }
        AtomicReference<Object> result = new AtomicReference<>(null);
        try {
            InetSocketAddress inetSocketAddress = serviceRegistry.lookupService(rpcRequest.getInterfaceName());
            Channel channel = ChannelProvider.get(inetSocketAddress, serializer);
//            ChannelFuture future = bootstrap.connect(host, port).sync();
            logger.info("客户端连接到服务器 {}:{}", inetSocketAddress.getHostString(), inetSocketAddress.getPort());
//            Channel channel = future.channel();
            if (channel.isActive()) {
                channel.writeAndFlush(rpcRequest).addListener(future1 -> {
                    if (future1.isSuccess()) {
                        logger.info(String.format("客户端发送消息：%s", rpcRequest.toString()));
                    } else {
                        logger.error("发送消息时有错误发生：", future1.cause());
                    }
                });
                channel.closeFuture().sync();
                AttributeKey<RpcResponse> key = AttributeKey.valueOf("rpcResponse");
                RpcResponse rpcResponse = channel.attr(key).get();
                return rpcResponse.getData();
            }
        } catch (InterruptedException e) {
            logger.error("发送消息时有错误发生：", e);
        }
        return null;
    }

    @Override
    public void setSerializer(CommonSerializer serializer) {
        this.serializer = serializer;
    }
}

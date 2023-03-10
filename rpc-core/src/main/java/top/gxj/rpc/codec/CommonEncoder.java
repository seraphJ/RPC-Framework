package top.gxj.rpc.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.gxj.rpc.entity.RpcRequest;
import top.gxj.rpc.enumeration.PackageType;
import top.gxj.rpc.serializer.CommonSerializer;

/**
 * @author gxj
 * @date 2022/12/21 13:52
 */
public class CommonEncoder extends MessageToByteEncoder {

    private static final Logger logger = LoggerFactory.getLogger(CommonEncoder.class);

    private static final int MAGIC_NUMBER = 0xCAFEBABE;

    private final CommonSerializer serializer;

    public CommonEncoder(CommonSerializer serializer) {
        this.serializer = serializer;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        out.writeInt(MAGIC_NUMBER);
        if (msg instanceof RpcRequest) {
            out.writeInt(PackageType.REQUEST_PACK.getCode());
            logger.info("RpcRequest包 encode方法执行 serializer=" + serializer.getClass().getCanonicalName());
        } else {
            out.writeInt(PackageType.RESPONSE_PACK.getCode());
            logger.info("RpcResponse包 encode方法执行 serializer=" + serializer.getClass().getCanonicalName());
        }
        out.writeInt(serializer.getCode());
        byte[] bytes = serializer.serialize(msg);
        out.writeInt(bytes.length);
        out.writeBytes(bytes);
    }
}

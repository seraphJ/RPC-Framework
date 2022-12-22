package top.gxj.rpc.hook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.gxj.rpc.factory.ThreadPoolFactory;
import top.gxj.rpc.util.NacosUtil;

import java.util.concurrent.ExecutorService;

/**
 * @author gxj
 * @date 2022/12/22 14:35
 */
public class ShutdownHook {

    private static final Logger logger = LoggerFactory.getLogger(ShutdownHook.class);

    private final ExecutorService threadPool = ThreadPoolFactory.createDefaultThreadPool("shutdown-hook");
    private static final ShutdownHook shutdownHook = new ShutdownHook();

    private ShutdownHook() {
    }

    public static ShutdownHook getShutdownHook() {
        return shutdownHook;
    }

    public void addClearAllHook() {
        logger.info("关闭后将自动注销所有服务");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            NacosUtil.clearRegistry();
            threadPool.shutdown();
        }));
    }
}

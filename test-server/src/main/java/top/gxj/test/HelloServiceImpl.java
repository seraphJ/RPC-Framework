package top.gxj.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.gxj.rpc.annotation.Service;
import top.gxj.rpc.api.HelloObject;
import top.gxj.rpc.api.HelloService;
/**
 * @author gxj
 * @date 2022/12/20 15:27
 */
@Service
public class HelloServiceImpl implements HelloService {
    private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public String hello(HelloObject object) {
        logger.info("接收到：{}", object.getMessage());
        return "这是调用的返回值，id=" + object.getId();
    }
}

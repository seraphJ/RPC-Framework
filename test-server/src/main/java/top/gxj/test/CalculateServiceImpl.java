package top.gxj.test;

import top.gxj.rpc.annotation.Service;
import top.gxj.rpc.api.CalculateService;

/**
 * @author gxj
 * @date 2022/12/23 13:50
 */
@Service
public class CalculateServiceImpl implements CalculateService {
    @Override
    public String sum(int n1, int n2) {
        return "运算完成：" + n1 + " + " + n2 + " = " + (n1 + n2);
    }
}

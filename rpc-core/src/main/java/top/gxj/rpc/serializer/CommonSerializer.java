package top.gxj.rpc.serializer;

/**
 * @author gxj
 * @date 2022/12/21 14:19
 */
public interface CommonSerializer {

    byte[] serialize(Object obj);
    Object deserialize(byte[] bytes, Class<?> clazz);
    int getCode();

    static CommonSerializer getByCode(Integer code) {
        switch (code) {
            case 0:
                return new KryoSerializer();
            case 1:
                return new JsonSerializer();
            default:
                return null;
        }
    }
}

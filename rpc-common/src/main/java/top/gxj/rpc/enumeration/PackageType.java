package top.gxj.rpc.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author gxj
 * @date 2022/12/21 13:57
 */
@AllArgsConstructor
@Getter
public enum PackageType {

    REQUEST_PACK(0),
    RESPONSE_PACK(1);

    private final int code;

}

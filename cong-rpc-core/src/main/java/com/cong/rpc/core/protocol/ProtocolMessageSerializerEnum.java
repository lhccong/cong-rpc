package com.cong.rpc.core.protocol;

import cn.hutool.core.util.ObjectUtil;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 协议消息序列化程序枚举
 * 协议消息的序列化器枚举
 *
 * @author cong
 * @date 2024/03/10
 */
@Getter
public enum ProtocolMessageSerializerEnum {

    /**
     * jdk
     */
    JDK(0, "jdk"),
    /**
     * JSON
     */
    JSON(1, "json"),
    /**
     * KRYO
     */
    KRYO(2, "kryo"),
    /**
     * HESSIAN
     */
    HESSIAN(3, "hessian");

    private final int key;

    private final String value;

    ProtocolMessageSerializerEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return {@link List}<{@link String}>
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 按键获取枚举
     * 根据 key 获取枚举
     *
     * @param key 钥匙
     * @return {@link ProtocolMessageSerializerEnum}
     */
    public static ProtocolMessageSerializerEnum getEnumByKey(int key) {
        for (ProtocolMessageSerializerEnum anEnum : ProtocolMessageSerializerEnum.values()) {
            if (anEnum.key == key) {
                return anEnum;
            }
        }
        return null;
    }


    /**
     * 按值获取枚举
     * 根据 value 获取枚举
     *
     * @param value 价值
     * @return {@link ProtocolMessageSerializerEnum}
     */
    public static ProtocolMessageSerializerEnum getEnumByValue(String value) {
        if (ObjectUtil.isEmpty(value)) {
            return null;
        }
        for (ProtocolMessageSerializerEnum anEnum : ProtocolMessageSerializerEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }
}
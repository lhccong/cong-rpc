package com.cong.rpc.core.protocol;

import lombok.Getter;

/**
 * 协议消息状态枚举
 * 协议消息的状态枚举
 *
 * @author 86188
 * @date 2024/03/10
 */
@Getter
public enum ProtocolMessageStatusEnum {

    /**
     * 成功
     */
    OK("ok", 20),
    BAD_REQUEST("badRequest", 40),
    BAD_RESPONSE("badResponse", 50);

    private final String text;

    private final int value;

    ProtocolMessageStatusEnum(String text, int value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value 价值
     * @return {@link ProtocolMessageStatusEnum}
     */
    public static ProtocolMessageStatusEnum getEnumByValue(int value) {
        for (ProtocolMessageStatusEnum anEnum : ProtocolMessageStatusEnum.values()) {
            if (anEnum.value == value) {
                return anEnum;
            }
        }
        return null;
    }
}
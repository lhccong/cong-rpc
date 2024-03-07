package com.cong.rpc.core.serializer;

import java.io.IOException;

/**
 * 序列化器接口
 *
 * @author cong
 * @date 2024/03/07
 */
public interface Serializer {

    /**
     * 序列 化
     * 序列化
     *
     * @param object 对象
     * @return {@link byte[]}
     * @throws IOException IO异常
     */
    <T> byte[] serialize(T object) throws IOException;

    /**
     * 反序列化
     *
     * @param bytes 字节
     * @param type  类型
     * @return {@link T}
     * @throws IOException IO异常
     */
    <T> T deserialize(byte[] bytes, Class<T> type) throws IOException;
}

package com.cong.rpc.core.serializer.jdk;

import com.cong.rpc.core.serializer.Serializer;

import java.io.*;

/**
 * JDK 序列化器
 *
 * @author cong
 * @date 2024/03/07
 */
public class JdkSerializer implements Serializer {

    /**
     * 序列 化
     * 序列化
     *
     * @param object 对象
     * @return {@link byte[]}
     * @throws IOException IO异常
     */
    @Override
    public <T> byte[] serialize(T object) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.close();
        return outputStream.toByteArray();
    }

    /**
     * 反序列化
     *
     * @param bytes 字节
     * @param type  类型
     * @return {@link T}
     * @throws IOException IO异常
     */
    @Override
    public <T> T deserialize(byte[] bytes, Class<T> type) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        try {
            return (T) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            objectInputStream.close();
        }
    }
}

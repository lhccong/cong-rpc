package com.cong.rpc.core.serializer.fastjson;

import com.alibaba.fastjson.JSON;
import com.cong.rpc.core.serializer.Serializer;


/**
 * 快速 JSON 序列化工厂
 *
 * @author cong
 * @date 2024/03/07
 */
public class FastJsonSerializeFactory implements Serializer {

    @Override
    public <T> byte[] serialize(T t) {
        String jsonStr = JSON.toJSONString(t);
        return jsonStr.getBytes();
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        return JSON.parseObject(new String(data),clazz);
    }

}

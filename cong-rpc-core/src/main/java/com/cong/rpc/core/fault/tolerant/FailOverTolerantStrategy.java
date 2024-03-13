package com.cong.rpc.core.fault.tolerant;

import com.cong.rpc.core.model.RpcResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 转移到其他服务节点 - 容错策略
 *
 * @author cong
 * @date 2024/03/13
 */
@Slf4j
public class FailOverTolerantStrategy implements TolerantStrategy {

    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        //可自行扩展，获取其他服务节点并调用
        return null;
    }
}
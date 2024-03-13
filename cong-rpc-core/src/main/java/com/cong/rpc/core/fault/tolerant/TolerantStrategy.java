package com.cong.rpc.core.fault.tolerant;


import com.cong.rpc.core.model.RpcResponse;

import java.util.Map;

/**
 * 容错策略
 *
 * @author cong
 * @date 2024/03/13
 */
public interface TolerantStrategy {

    /**
     * 做宽容
     * 容错
     *
     * @param context 上下文，用于传递数据
     * @param e       异常
     * @return {@link RpcResponse}
     */
    RpcResponse doTolerant(Map<String, Object> context, Exception e);
}
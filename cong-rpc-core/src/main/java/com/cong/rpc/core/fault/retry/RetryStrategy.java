package com.cong.rpc.core.fault.retry;


import com.cong.rpc.core.model.RpcResponse;

import java.util.concurrent.Callable;

/**
 * 重试策略
 *
 * @author cong
 * @date 2024/03/12
 */
public interface RetryStrategy {

    /**
     * 重试
     *
     * @param callable 调用
     * @return {@link RpcResponse}
     * @throws Exception 例外
     */
    RpcResponse doRetry(Callable<RpcResponse> callable) throws Exception;
}
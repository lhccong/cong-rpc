package com.cong.rpc.core.fault.retry;

import com.cong.rpc.core.model.RpcResponse;
import com.github.rholder.retry.*;
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 随机延迟 - 重试策略
 *
 * @author cong
 * @date 2024/03/12
 */
@Slf4j
public class RandomDelayRetryStrategy implements RetryStrategy {

    /**
     * 重试
     *
     * @param callable 调用
     * @return {@link RpcResponse}
     * @throws ExecutionException 执行异常
     * @throws RetryException     重试异常
     */
    public RpcResponse doRetry(Callable<RpcResponse> callable) throws ExecutionException, RetryException {
        Retryer<RpcResponse> retryer = RetryerBuilder.<RpcResponse>newBuilder()
                .retryIfExceptionOfType(Exception.class)
                // 随机等待策略，最小等待时间1秒，最大等待时间5秒
                .withWaitStrategy(WaitStrategies.randomWait(1L, TimeUnit.SECONDS, 5L, TimeUnit.SECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(5))
                .withRetryListener(new RetryListener() {
                    @Override
                    public <V> void onRetry(Attempt<V> attempt) {
                        log.info("重试次数 {}, 距离第一次重试的延迟 {} 毫秒", attempt.getAttemptNumber(), attempt.getDelaySinceFirstAttempt());
                    }
                })
                .build();

        return retryer.call(callable);
    }
}

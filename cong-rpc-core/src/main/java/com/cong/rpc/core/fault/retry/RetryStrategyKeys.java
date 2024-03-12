package com.cong.rpc.core.fault.retry;

/**
 * 重试策略键名常量
 *
 * @author cong
 * @date 2024/03/12
 */
public interface RetryStrategyKeys {

    /**
     * 不重试
     */
    String NO = "no";

    /**
     * 固定时间间隔
     */
    String FIXED_INTERVAL = "fixedInterval";

    /**
     * 指数退避
     *
     */
    String EXPONENTIAL_BACKOFF = "exponentialBackoff";
    /**
     * 时间延迟
     */
    String RANDOM_DELAY = "randomDelay";


}
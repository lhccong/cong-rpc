package com.cong.rpc.core.fault.tolerant;

/**
 * 模拟服务
 *
 * @author cong
 * @date 2024/03/13
 */
public interface MockService {
    Object mock(Object input);
}
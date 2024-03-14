package com.cong.rpc.core.fault.tolerant;

import lombok.extern.slf4j.Slf4j;

/**
 * 默认模拟服务
 *
 * @author cong
 * @date 2024/03/13
 */
@Slf4j
public class DefaultMockService implements MockService {
    @Override
    public Object mock() {
        return null;
    }
}

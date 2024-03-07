package com.cong.rpc.core.utils;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.setting.dialect.Props;

/**
 * 配置工具类
 *
 * @author cong
 * @date 2024/03/07
 */
public class ConfigUtils {

    /**
     * 加载配置
     * 加载配置对象
     *
     * @param tClass T级
     * @param prefix 前缀
     * @return {@link T}
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix) {
        return loadConfig(tClass, prefix, "");
    }

    /**
     * 加载配置
     * 加载配置对象，支持区分环境
     *
     * @param tClass      T级
     * @param prefix      前缀
     * @param environment 环境
     * @return {@link T}
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix, String environment) {
        StringBuilder configFileBuilder = new StringBuilder("application");
        if (CharSequenceUtil.isNotBlank(environment)) {
            configFileBuilder.append("-").append(environment);
        }
        configFileBuilder.append(".properties");
        Props props = new Props(configFileBuilder.toString());
        return props.toBean(tClass, prefix);
    }
}
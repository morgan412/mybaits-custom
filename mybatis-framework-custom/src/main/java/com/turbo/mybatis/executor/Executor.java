package com.turbo.mybatis.executor;

import com.turbo.mybatis.config.Configuration;
import com.turbo.mybatis.config.MappedStatement;

import java.util.List;

/**
 * @author zouxq
 */
public interface Executor {

    /**
     * 执行查询
     * @param configuration   配置信息
     * @param mappedStatement 要执行的 MappedStatement 对象
     * @param parameter       查询语句参数
     * @param <T>             映射结果类型
     * @return 映射结果
     */
    <T> List<T> query(Configuration configuration, MappedStatement mappedStatement, Object parameter);
}

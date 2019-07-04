package com.turbo.mybatis.config;

/**
 * @author zouxq
 */
public interface SqlSource {

    /**
     * 获取解析后绑定参数的 sql
     * @return BoundSql
     */
    BoundSql getBoundSql();
}

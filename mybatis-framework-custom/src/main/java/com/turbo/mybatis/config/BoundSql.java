package com.turbo.mybatis.config;

import java.util.List;

/**
 * @author zouxq
 */
public class BoundSql {

    private final String sql;
    private final List<ParameterMapping> parameterMappings;

    public BoundSql(String sql, List<ParameterMapping> parameterMappings) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
    }

    public String getSql() {
        return sql;
    }

    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }
}

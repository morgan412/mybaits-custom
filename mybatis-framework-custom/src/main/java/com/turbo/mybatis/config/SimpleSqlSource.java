package com.turbo.mybatis.config;

import com.turbo.mybatis.util.GenericTokenParser;
import com.turbo.mybatis.util.ParameterMappingTokenHandler;

/**
 * @author zouxq
 */
public class SimpleSqlSource implements SqlSource {

    private final String sqlText;

    public SimpleSqlSource(String sqlText) {
        this.sqlText = sqlText;
    }

    @Override
    public BoundSql getBoundSql() {
        // 解析sql文本
        ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", tokenHandler);
        String sql = genericTokenParser.parse(sqlText);
        // 就是将解析之后的SQL语句，和解析出来的SQL参数使用组合模式绑定到一个类中
        return new BoundSql(sql, tokenHandler.getParameterMappings());
    }
}

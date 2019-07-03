package com.turbo.mybatis.session;

import com.turbo.mybatis.config.Configuration;
import com.turbo.mybatis.executor.Executor;
import com.turbo.mybatis.executor.SimpleExecutor;

import java.util.List;

/**
 * @author zouxq
 */
public class DefaultSqlSession implements SqlSession {

    private final Configuration configuration;
    private final Executor executor;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
        this.executor = new SimpleExecutor();
    }

    @Override
    public <T> T selectOne(String statementId, Object parameter) {
        // TODO
        return null;
    }

    @Override
    public <T> List<T> selectList(String statementId, Object parameter) {
        // TODO
        return null;
    }

    @Override
    public int insert(String statementId, Object parameter) {
        // TODO
        return 0;
    }

    @Override
    public int update(String statementId, Object parameter) {
        // TODO
        return 0;
    }

    @Override
    public int delete(String statementId, Object parameter) {
        // TODO
        return 0;
    }
}

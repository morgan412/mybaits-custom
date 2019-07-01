package com.turbo.mybatis.session;

/**
 * @author zouxq
 */
public interface SqlSessionFactory {

    /**
     * 获取 SqlSession
     * @return SqlSession
     */
    SqlSession openSession();
}

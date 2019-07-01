package com.turbo.mybatis.session;

import java.util.List;

/**
 * @author zouxq
 */
public interface SqlSession {

    /**
     * 根据 statementId 和参数查询单个记录
     * @param statementId 操作唯一标识
     * @param parameter   要传递的参数对象
     * @param <T>         返回结果类型
     * @return 映射结果对象
     */
    <T> T selectOne(String statementId, Object parameter);

    /**
     * 根据 statementId 和参数查询多个记录
     * @param statementId 操作唯一标识
     * @param parameter   要传递的参数对象
     * @param <T>         返回结果类型
     * @return 映射结果集
     */
    <T> List<T> selectList(String statementId, Object parameter);

    /**
     * 根据 statementId 和参数插入数据
     * @param statementId 操作唯一标识
     * @param parameter   要传递的参数对象
     * @return 影响行数
     */
    int insert(String statementId, Object parameter);

    /**
     * 根据 statementId 和参数更新数据
     * @param statementId 操作唯一标识
     * @param parameter   要传递的参数对象
     * @return 影响行数
     */
    int update(String statementId, Object parameter);

    /**
     * 根据 statementId 和参数删除数据
     * @param statementId 操作唯一标识
     * @param parameter   要传递的参数对象
     * @return 影响行数
     */
    int delete(String statementId, Object parameter);
}

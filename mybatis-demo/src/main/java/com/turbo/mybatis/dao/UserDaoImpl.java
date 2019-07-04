package com.turbo.mybatis.dao;

import com.turbo.mybatis.pojo.User;
import com.turbo.mybatis.session.SqlSession;
import com.turbo.mybatis.session.SqlSessionFactory;

/**
 * @author zouxq
 */
public class UserDaoImpl implements UserDao {

    private SqlSessionFactory sqlSessionFactory;

    public UserDaoImpl(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public User findUserById(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession.selectOne("test.findUserById", user);
    }
}

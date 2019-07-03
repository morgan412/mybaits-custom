package com.turbo.mybatis.test;

import com.turbo.mybatis.dao.UserDao;
import com.turbo.mybatis.dao.UserDaoImpl;
import com.turbo.mybatis.pojo.User;
import com.turbo.mybatis.session.SqlSessionFactory;
import com.turbo.mybatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

/**
 * @author zouxq
 */
public class UserDaoTest {

    private SqlSessionFactory sqlSessionFactory;

    private UserDao userDao;

    @BeforeAll
    public void init() {
        // 类加载器加载，指定类路径下的全局配置文件
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("SqlMapConfig.xml");
        // 构建者模式创建 SqlSessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 注入 sqlSessionFactory
        userDao = new UserDaoImpl(sqlSessionFactory);
    }

    @Test
    public void testFindUserById() {
        User param = new User();
        param.setId(1);
        User user = userDao.findUserById(param);
        System.out.println(user);
    }
}

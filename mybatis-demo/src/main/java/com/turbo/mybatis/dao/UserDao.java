package com.turbo.mybatis.dao;

import com.turbo.mybatis.pojo.User;

/**
 * @author zouxq
 */
public interface UserDao {

    /**
     * 根据id查询
     * @param user 查询参数
     * @return 用户
     */
    User findUserById(User user);
}

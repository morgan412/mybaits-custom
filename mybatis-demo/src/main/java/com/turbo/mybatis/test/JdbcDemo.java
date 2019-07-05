package com.turbo.mybatis.test;

import java.sql.*;

/**
 * @author zouxq
 */
public class JdbcDemo {

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // 加载数据库JDBC驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 通过驱动管理类获取数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?characterEncoding=utf-8", "root", "root");

            // sql语句，？占位符
            String sql = "select * from user where id = ?";

            // 预处理 statement
            preparedStatement = connection.prepareStatement(sql);

            // 设置参数(从1开始)
            preparedStatement.setInt(1, 1);

            // 执行查询，获取结果集
            resultSet = preparedStatement.executeQuery();

            // 获取结果（while 遍历结果集）
            if (resultSet.next()) {
                System.out.println(resultSet.getString("id") + " " + resultSet.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

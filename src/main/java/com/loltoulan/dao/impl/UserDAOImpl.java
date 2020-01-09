package com.loltoulan.dao.impl;

import com.loltoulan.dao.UserDAO;
import com.loltoulan.domain.User;
import com.loltoulan.util.JDBCUtil02;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * @Author LOL_toulan
 * @Time 2020/1/7 23:46
 * @Message
 */
public class UserDAOImpl implements UserDAO {
    @Override
    public User login(User user) throws SQLException {
        QueryRunner qr = new QueryRunner(JDBCUtil02.getDataSource());

        String sql = "select * from user where username = (?) and password = (?)";

        User query = qr.query(sql, new BeanHandler<>(User.class), user.getUsername(), user.getPassword());

        if (query != null) {
            return query;
        } else {
            return null;
        }
    }
}

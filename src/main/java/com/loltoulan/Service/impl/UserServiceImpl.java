package com.loltoulan.Service.impl;

import com.loltoulan.Service.UserService;
import com.loltoulan.dao.UserDAO;
import com.loltoulan.dao.impl.UserDAOImpl;
import com.loltoulan.domain.User;

import java.sql.SQLException;

/**
 * @Author LOL_toulan
 * @Time 2020/1/7 23:48
 * @Message
 */
public class UserServiceImpl implements UserService {
    @Override
    public User login(User user) throws SQLException {

        UserDAO dao = new UserDAOImpl();

        User login = dao.login(user);

        if (login != null) {
            return login;
        } else {
            return null;
        }
    }
}

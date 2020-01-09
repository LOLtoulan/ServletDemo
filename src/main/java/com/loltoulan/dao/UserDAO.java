package com.loltoulan.dao;

import com.loltoulan.domain.User;

import java.sql.SQLException;

/**
 * @Author LOL_toulan
 * @Time 2020/1/7 23:46
 * @Message
 */
public interface UserDAO {
    /**
     * 登录
     * @param user
     * @return
     */
    User login(User user) throws SQLException;
}

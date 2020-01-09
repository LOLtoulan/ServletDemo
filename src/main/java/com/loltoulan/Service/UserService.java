package com.loltoulan.Service;

import com.loltoulan.domain.User;

import java.sql.SQLException;

/**
 * @Author LOL_toulan
 * @Time 2020/1/7 23:47
 * @Message
 */
public interface UserService {
    /**
     * 登录
     * @param user
     * @return
     */
    User login(User user) throws SQLException;
}

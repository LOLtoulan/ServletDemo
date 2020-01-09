package com.loltoulan.filter;

import javax.servlet.http.Cookie;

/**
 * @Author LOL_toulan
 * @Time 2019/12/29 0:39
 * @Message
 */
public class CookieUtils {

    public static Cookie findCookkie(Cookie[] cookies, String name) {

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }

        return null;
    }
}

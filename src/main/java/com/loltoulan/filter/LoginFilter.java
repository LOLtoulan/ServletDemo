package com.loltoulan.filter;

import com.loltoulan.dao.UserDAO;
import com.loltoulan.dao.impl.UserDAOImpl;
import com.loltoulan.domain.User;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;


/**
 * >  过滤器的核心不是完成拦截不给 ， 还是放行显示。 它的核心是在放行之前，帮用户完成登录的功能。
 * <p>
 * * 实现思路
 * <p>
 * 1. 先判断session是否有效， 如果有效，就不用取cookie了，直接放行。
 * <p>
 * 2. 如果session失效了，那么就取 cookie。
 * <p>
 * 1. 没有cookie  放行
 * <p>
 * 2. 有cookie
 * <p>
 * 1. 取出来cookie的值，然后完成登录
 * 2. 把这个用户的值存储到session中
 * <p>
 * 3. 放行。
 */
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String uri = req.getRequestURI();

        if (uri.contains("/login.jsp") || uri.contains("/LoginServlet")|| uri.contains("/css")|| uri.contains("/js")|| uri.contains("CheckCodeServlet")) {
            chain.doFilter(request, response);
        } else {
            Object user = req.getSession().getAttribute("user");
            if (user != null) {
                chain.doFilter(request, response);
            } else {
                req.setAttribute("login_msg", "您还未登录，请登录！");
                req.getRequestDispatcher("login.jsp").forward(req, response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}

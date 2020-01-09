package com.loltoulan.servlet;

import com.loltoulan.Service.UserService;
import com.loltoulan.Service.impl.UserServiceImpl;
import com.loltoulan.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @Author LOL_toulan
 * @Time 2020/1/7 23:17
 * @Message
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //1.设置编码
            req.setCharacterEncoding("utf-8");

            //2.获取数据
            //2.1获取用户填写验证码
            String verifycode = req.getParameter("verifycode");
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            //3.验证码校验
            HttpSession session = req.getSession();
            String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
            session.removeAttribute("CHECKCODE_SERVER");//确保验证码一次性
            if(!checkcode_server.equalsIgnoreCase(verifycode)){
                //验证码不正确
                //提示信息
                req.setAttribute("login_msg","验证码错误！");
                //跳转登录页面
                req.getRequestDispatcher("login.jsp").forward(req,resp);

                return;
            }

            User user = new User();
            user.setPassword(password);
            user.setUsername(username);

            //5.调用Service查询
            UserService service = new UserServiceImpl();
            User loginUser = service.login(user);
            //6.判断是否登录成功
            if(loginUser != null){

                System.out.println(user);
                //登录成功
                //将用户存入session
                session.setAttribute("user",loginUser);
                //跳转页面
                resp.sendRedirect("index.jsp");
            }else{
                //登录失败
                //提示信息
                req.setAttribute("login_msg","用户名或密码错误！");
                //跳转登录页面
                req.getRequestDispatcher("login.jsp").forward(req,resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}

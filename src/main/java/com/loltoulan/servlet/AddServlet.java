package com.loltoulan.servlet;

import com.loltoulan.Service.StudentService;
import com.loltoulan.Service.impl.StudentServiceImpl;
import com.loltoulan.domain.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @Author LOL_toulan
 * @Time 2019/12/25 21:08
 * @Message
 */
public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");


        try {

            //1. 获取客户端提交上来的数据
            String sname = req.getParameter("sname"); //sname:zhangsan
            String gender = req.getParameter("gender");
            String phone = req.getParameter("phone");
            String birthday = req.getParameter("birthday"); // 1989-10-18
            String info = req.getParameter("info");
            //String hobby = request.getParameter("hobby");//hobby : 游泳，写字， 足球。
            String[] h = req.getParameterValues("hobby");
            //篮球，足球，写字] --- 篮球，足球，写字

            String hobby = Arrays.toString(h);
            hobby = hobby.substring(1, hobby.length() - 1);

            //2. 添加到数据库
            //string -- date
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);

            Student student = new Student(sname, gender, phone, hobby, info, date);
            StudentService service = new StudentServiceImpl();
            service.insert(student);

            //3. 跳转到列表页
            //再查一次数据库，然后再装到作用域中 ，然后再跳转。
            //这里是直接跳转到页面上， 那么这个页面会重新翻译一次，上面的那个request的请求存放的数据是没有了。
            //request.getRequestDispatcher("list.jsp").forward(request, response);

            //servlet除了能跳jsp之外。 还能跳servlet
            req.getRequestDispatcher("StudentListServlet").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

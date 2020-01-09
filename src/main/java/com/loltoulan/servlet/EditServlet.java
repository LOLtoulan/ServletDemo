package com.loltoulan.servlet;

import com.loltoulan.Service.StudentService;
import com.loltoulan.Service.impl.StudentServiceImpl;
import com.loltoulan.domain.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @Author LOL_toulan
 * @Time 2019/12/25 21:53
 * @Message
 */
public class EditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int sid = Integer.parseInt(req.getParameter("sid"));
            Student student = new Student();
            StudentService service = new StudentServiceImpl();
            Student s = service.findStudentById(sid);
            req.setAttribute("stu",s);
            req.getRequestDispatcher("edit.jsp").forward(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req, resp);
    }
}


/*
    int sid1 = s.getSid();
    String sname = s.getSname();
    String gender = s.getGender();
    Date birthday = s.getBirthday();
    String hobby = s.getHobby();
    String info = s.getInfo();
    String phone = s.getPhone();

    */

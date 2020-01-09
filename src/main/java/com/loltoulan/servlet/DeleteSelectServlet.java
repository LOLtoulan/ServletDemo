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
import java.util.List;

/**
 * @Author LOL_toulan
 * @Time 2020/1/6 11:52
 * @Message
 */
public class DeleteSelectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String[] uids = req.getParameterValues("uid");
            StudentService service = new StudentServiceImpl();

            service.delSelectedStu(uids);
            List<Student> list = service.findAll();

            req.setAttribute("list", list);

            req.getRequestDispatcher("StudentListServlet").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

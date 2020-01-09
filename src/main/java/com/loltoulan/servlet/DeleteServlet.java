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
 * @Time 2019/12/25 21:40
 * @Message
 */
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            StudentService service = new StudentServiceImpl();

            int sid = Integer.parseInt(req.getParameter("sid"));

            service.delete(sid);

            List<Student> list = service.findAll();

            req.setAttribute("list", list);

            req.getRequestDispatcher("StudentListServlet").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req, resp);
    }

}

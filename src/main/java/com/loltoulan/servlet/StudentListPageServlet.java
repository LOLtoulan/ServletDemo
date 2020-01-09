package com.loltoulan.servlet;

import com.loltoulan.Service.StudentService;
import com.loltoulan.Service.impl.StudentServiceImpl;
import com.loltoulan.domain.PageBean;
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
 * @Time 2019/12/26 0:54
 * @Message
 */
public class StudentListPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            int currentPage = Integer.parseInt(req.getParameter("currentPage"));
            StudentService service = new StudentServiceImpl();
            PageBean pageBean = service.findStudentByPage(currentPage);

            req.setAttribute("pageBean", pageBean);

            req.getRequestDispatcher("list_page.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req, resp);
    }
}

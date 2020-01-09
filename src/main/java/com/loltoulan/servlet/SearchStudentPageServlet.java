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
 * @Time 2019/12/25 23:08
 * @Message
 */
public class SearchStudentPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        try {
            int currentPage = Integer.parseInt(req.getParameter("currentPage"));
            String sname = req.getParameter("sname");
            String sgender = req.getParameter("sgender");
            StudentService service = new StudentServiceImpl();

            PageBean pageBean = service.searchStudentByPage(sname, sgender, currentPage);
            System.out.println(pageBean.getTotalSize());
            System.out.println(pageBean);

            req.setAttribute("pageBean", pageBean);
            //resp.sendRedirect("list_page.jsp");
            req.getRequestDispatcher("list_page.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

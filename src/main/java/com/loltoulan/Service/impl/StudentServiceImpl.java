package com.loltoulan.Service.impl;

import com.loltoulan.Service.StudentService;
import com.loltoulan.dao.StudentDAO;
import com.loltoulan.dao.impl.StudentDAOImpl;
import com.loltoulan.domain.PageBean;
import com.loltoulan.domain.Student;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author LOL_toulan
 * @Time 2019/12/25 20:34
 * @Message
 */
public class StudentServiceImpl implements StudentService {

    /**
     * 返回所有学生信息
     * @return
     * @throws SQLException
     */
    @Override
    public List<Student> findAll() throws SQLException {
        StudentDAO dao = new StudentDAOImpl();
        List<Student> studentList = dao.findAll();

        return studentList;
    }

    @Override
    public void insert(Student student) throws SQLException {
        StudentDAO dao = new StudentDAOImpl();
        dao.insert(student);
    }

    @Override
    public void delete(int sid) throws SQLException {
        StudentDAO dao = new StudentDAOImpl();
        dao.delete(sid);
    }

    @Override
    public Student findStudentById(int sid) throws SQLException {
        StudentDAO dao = new StudentDAOImpl();
        Student student = dao.findStudentById(sid);
        return student;
    }

    @Override
    public void update(Student student) throws SQLException {
        StudentDAO dao = new StudentDAOImpl();
        dao.update(student);
    }

    @Override
    public List<Student> searchStudent(String sname, String sgender) throws SQLException {
        StudentDAO dao = new StudentDAOImpl();
        List<Student> students = dao.searchStudent(sname, sgender);
        return students;
    }

    @Override
    public PageBean findStudentByPage(int currentPage) throws SQLException {
        //封装分页的该页数据
        PageBean<Student> pageBean = new PageBean<Student>();

        int pageSize = StudentDAO.PAGE_SIZE ;
        pageBean.setCurrentPage(currentPage); //设置当前页
        pageBean.setPageSize(pageSize); //设置每页显示多少记录

        StudentDAO dao = new StudentDAOImpl() ;
        List<Student> list =dao.findStudentByPage(currentPage);
        pageBean.setList(list); //设置这一页的学生数据

        //总的记录数， 总的页数。
        int count = dao.findCount();
        pageBean.setTotalSize(count); //设置总的记录数
        //200 ， 10 ==20   201 ， 10 = 21   201 % 10 == 0 ?201 / 10 :201 % 10 + 1
        pageBean.setTotalPage(count % pageSize==0 ? count / pageSize : (count / pageSize) + 1); //总页数
        return pageBean;
    }


    @Override
    public void delSelectedStu(String[] ids) throws SQLException {
        StudentDAO dao = new StudentDAOImpl();
        if (ids != null && ids.length > 0) {
            for (String id : ids) {
                dao.delete(Integer.parseInt(id));
            }
        }
    }

    @Override
    public PageBean searchStudentByPage(String sname, String sgender,int currentPage) throws SQLException {
        //封装分页的该页数据
        PageBean<Student> pageBean = new PageBean<Student>();

        int pageSize = StudentDAO.PAGE_SIZE ;
        pageBean.setCurrentPage(currentPage); //设置当前页
        pageBean.setPageSize(pageSize); //设置每页显示多少记录

        StudentDAO dao = new StudentDAOImpl() ;
        List<Student> list =dao.searchStudentByPage(currentPage,sname,sgender);
        pageBean.setList(list); //设置这一页的学生数据

        //总的记录数， 总的页数。
        int count = dao.findPageCount(sname,sgender,currentPage);
        pageBean.setTotalSize(count); //设置总的记录数
        //200 ， 10 ==20   201 ， 10 = 21   201 % 10 == 0 ?201 / 10 :201 % 10 + 1
        pageBean.setTotalPage(count % pageSize==0 ? count / pageSize : (count / pageSize) + 1); //总页数
        return pageBean;
    }

}

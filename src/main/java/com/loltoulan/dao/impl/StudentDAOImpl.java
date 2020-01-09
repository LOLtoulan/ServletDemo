package com.loltoulan.dao.impl;

import com.loltoulan.dao.StudentDAO;
import com.loltoulan.domain.Student;
import com.loltoulan.util.JDBCUtil02;
import com.loltoulan.util.TextUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author LOL_toulan
 * @Time 2019/12/25 20:03
 * @Message
 */
public class StudentDAOImpl implements StudentDAO {
    @Override
    public List<Student> findStudentByPage(int currentPage) throws SQLException {

        QueryRunner runner = new QueryRunner(JDBCUtil02.getDataSource());
        //第一个问号，代表一页返回多少条记录  ， 第二个问号， 跳过前面的多少条记录。
        //5 0 --- 第一页 (1-1)*5
        //5 5  --- 第二页 (2-1)*5
        //5 10  --- 第三页
        List<Student> query = runner.query("select * from stus limit ? offset ?",
                new BeanListHandler<Student>(Student.class), PAGE_SIZE, (currentPage - 1) * PAGE_SIZE);
        return query;
    }

    @Override
    public List<Student> findAll() throws SQLException {
        QueryRunner qr = new QueryRunner(JDBCUtil02.getDataSource());

        String sql = "select * from stus";

        List<Student> studentList = qr.query(sql, new BeanListHandler<Student>(Student.class));

        return studentList;
    }

    @Override
    public Student findStudentById(int sid) throws SQLException {

        QueryRunner qr = new QueryRunner(JDBCUtil02.getDataSource());

        String sql = "select * from stus where sid = (?)";

        Student student = qr.query(sql, new BeanHandler<Student>(Student.class), sid);

        return student;
    }

    /**
     * @param sname
     * @param sgender
     * @return
     * @throws SQLException
     */
    @Override
    public List<Student> searchStudent(String sname, String sgender) throws SQLException {

        QueryRunner qr = new QueryRunner(JDBCUtil02.getDataSource());

        String sql = "select * from stus where 1=1 ";
        List<String> list1 = new ArrayList<String>();

        //判断有没有姓名， 如果有，就组拼到sql语句里面
        if (!TextUtils.isEmpty(sname)) {
            sql = sql + "  and sname like ?";
            list1.add("%" + sname + "%");
        }

        //判断有没有性别，有的话，就组拼到sql语句里面。
        if (!TextUtils.isEmpty(sgender)) {
            sql = sql + " and gender = ?";
            list1.add(sgender);
        }


        List<Student> list = qr.query(sql, new BeanListHandler<Student>(Student.class), list1.toArray());
        System.out.println("sql" + sql);

        return list;
    }

    @Override
    public List<Student> searchStudentByPage(int currentPage, String sname, String sgender) throws SQLException {
        QueryRunner qr = new QueryRunner(JDBCUtil02.getDataSource());

        String sql = "select * from stus where 1=1 ";
        List<Object> list1 = new ArrayList<Object>();

        //判断有没有姓名， 如果有，就组拼到sql语句里面
        if (!TextUtils.isEmpty(sname)) {
            sql = sql + "  and sname like ?";
            list1.add("%" + sname + "%");
        }

        //判断有没有性别，有的话，就组拼到sql语句里面。
        if (!TextUtils.isEmpty(sgender)) {
            sql = sql + " and gender = ?";
            list1.add(sgender);
        }
        sql = sql + " limit ? offset ?";
        list1.add(PAGE_SIZE);
        list1.add((currentPage - 1) * PAGE_SIZE);
        List<Student> list = qr.query(sql, new BeanListHandler<Student>(Student.class), list1.toArray());
        System.out.println("sql" + sql);

        return list;
    }


    @Override
    public void insert(Student student) throws SQLException {

        QueryRunner qr = new QueryRunner(JDBCUtil02.getDataSource());


        qr.update("insert into stus(sname,gender,phone,birthday,hobby,info) values(?,?,?,?,?,?)",
                student.getSname(),
                student.getGender(),
                student.getPhone(),
                student.getBirthday(),
                student.getHobby(),
                student.getInfo()
        );

    }

    @Override
    public void delete(int sid) throws SQLException {
        QueryRunner qr = new QueryRunner(JDBCUtil02.getDataSource());
        String sql = "delete from stus where sid=(?)";
        qr.update(sql, sid);

    }

    @Override
    public void update(Student student) throws SQLException {

        QueryRunner qr = new QueryRunner(JDBCUtil02.getDataSource());


        qr.update("update stus set sname=?,gender=?,phone=?,birthday=?,hobby=?,info=? where sid = (?)",
                student.getSname(),
                student.getGender(),
                student.getPhone(),
                student.getBirthday(),
                student.getHobby(),
                student.getInfo(),
                student.getSid()
        );
    }

    @Override
    public int findCount() throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtil02.getDataSource());
        //用于处理 平均值 、 总的个数。
        Long result = (Long) runner.query("SELECT COUNT(*) FROM stus", new ScalarHandler());
        return result.intValue();
    }

    @Override
    public int findPageCount(String sname, String sgender, int currentPage) throws SQLException {

        QueryRunner qr = new QueryRunner(JDBCUtil02.getDataSource());

        String sql = "select count(*) from stus where 1=1 ";
        List<String> list1 = new ArrayList<String>();

        //判断有没有姓名， 如果有，就组拼到sql语句里面
        if (!TextUtils.isEmpty(sname)) {
            sql = sql + "  and sname like ?";
            list1.add("%" + sname + "%");
        }

        //判断有没有性别，有的话，就组拼到sql语句里面。
        if (!TextUtils.isEmpty(sgender)) {
            sql = sql + " and gender = ?";
            list1.add(sgender);
        }

        Integer count = qr.query(sql, new BeanHandler<Integer>(Integer.class), list1.toArray());
        System.out.println("sql" + sql);

        return count;
    }


}




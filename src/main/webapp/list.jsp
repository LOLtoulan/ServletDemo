<%--
  Created by IntelliJ IDEA.
  User: liyag
  Date: 2019/12/25
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>学生列表页面</title>

    <script type="text/javascript">

        function doDelete(sid) {
            /* 如果这里弹出的对话框，用户点击的是确定，就马上去请求Servlet。
            如何知道用户点击的是确定。
            如何在js的方法中请求servlet。 */
            var flag = confirm("是否确定删除?");
            if (flag) {
                //表明点了确定。 访问servlet。 在当前标签页上打开 超链接，
                //window.location.href="DeleteServlet?sid="+sid;
                location.href = "DeleteServlet?sid=" + sid;
            }
        }

        window.onload = function () {


            document.getElementById("delSelected").onclick = function () {
                if (confirm("您确定要删除选中条目吗？")) {

                    var flag = false;
                    //判断是否有选中条目
                    var cbs = document.getElementsByName("uid");
                    for (var i = 0; i < cbs.length; i++) {
                        if (cbs[i].checked) {
                            //有一个条目选中了
                            flag = true;
                            break;
                        }
                    }

                    //有条目被选中
                    if (flag == true) {
                        //表单提交
                        document.getElementById("form1").submit();
                    } else {
                        alert("您没有选中要删除的条目！");
                    }

                }
            };

            //全选，全不选
            document.getElementById("firstCb").onclick = function () {
                var name = document.getElementsByName("uid");
                for (var i = 0; i < name.length; i++) {
                    name[i].checked = this.checked;
                }
            };

        }
    </script>

</head>
<body>
    欢迎您, ${user.username }!
    <center>
        <br>
        <h3>学生列表</h3><br>

        <table border="1" width="900">
            <form action="SearchStudentServlet" method="post">
                <tr>
                    <td colspan="8">

                        按姓名查询:<input type="text" name="sname" />
                        &nbsp;
                        按性别查询:<select name="sgender">
                        <option value="">--请选择--
                        <option value="男">男
                        <option value="女">女
                    </select>
                        &nbsp;&nbsp;&nbsp;
                        <input type="submit" value="查询">
                        &nbsp;&nbsp;&nbsp;
                        <a href="add.jsp">添加</a>
                        &nbsp;&nbsp;&nbsp;
                        <a href="StudentListServlet">返回首页</a>
                        &nbsp;&nbsp;&nbsp;
                        <a href="javascript:void(0)" id="delSelected">删除选中</a>
                    </td>
                </tr>
            </form>
            <form id="form1" action="DeleteSelectServlet" method="post">
                <tr align="center">
                    <th><input type="checkbox" id="firstCb"></th>
                    <td>编号</td>
                    <td>姓名</td>
                    <td>性别</td>
                    <td>电话</td>
                    <td>生日</td>
                    <td>爱好</td>
                    <td>简介</td>
                    <td>操作</td>
                </tr>

                <c:forEach items="${list }" var="stu" varStatus="s">
                    <tr align="center">
                        <td><input type="checkbox" name="uid" value="${stu.sid}"></td>
                        <td>${s.count}</td>
                        <td>${stu.sname }</td>
                        <td>${stu.gender }</td>
                        <td>${stu.phone }</td>
                        <td>${stu.birthday }</td>
                        <td>${stu.hobby }</td>
                        <td>${stu.info }</td>
                        <td><a href="EditServlet?sid=${stu.sid }">更新</a> <a href="#"
                                                                            onclick="doDelete(${stu.sid})">删除</a></td>
                    </tr>
                </c:forEach>
            </form>
        </table>

    </center>




</body>
</html>
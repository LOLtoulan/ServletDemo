<%--
  Created by IntelliJ IDEA.
  User: liyag
  Date: 2020/1/7
  Time: 23:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        //切换验证码
        function refreshCode() {
            //1.获取验证码图片对象
            var vcode = document.getElementById("vcode");

            //2.设置其src属性，加时间戳
            vcode.src = "CheckCodeServlet?time=" + new Date().getTime();
        }
    </script>
</head>
<body>
<center>

    <h3>管理员登陆界面</h3>
    <form method="post" action="LoginServlet">
        <table border="1px">
            <tr>
                <td>账号：</td>
                <td colspan="2"><input type="text" name="username"></td>
            </tr>
            <tr>
                <td>密码：</td>
                <td colspan="2"><input type="password" name="password"><br></td>

            </tr>
            <tr>
                <td>验证码：</td>
                <td><input type="text" name="verifycode"/></td>
                <td>
                    <a href="javascript:refreshCode();">
                        <img src="CheckCodeServlet" title="看不清点击刷新" id="vcode"/>
                    </a>
                </td>

            </tr>
            <tr>
                <td><input type="submit" value="登录"></td>
            </tr>
        </table>
    </form>
    <div>
        <span style="color: red">${login_msg}</span>
    </div>
</center>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: wangshuxiao
  Date: 2019/11/5
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>register</title>
</head>
<body>
<form action="registeredServlet" method="post">
    <table>

        <tr>
            <td>用户名</td>
            <td><input name="username" placeholder="请填写用户名"></td>
        </tr>

        <tr>
            <td>密码</td>
            <td><input name="password" placeholder="请输入密码"></td>
        </tr>

        <tr>
            <td colspan="2"> <img id="checkcode" src="/wsx/checkCodeServlet"></td>
        </tr>
        <tr>
            <td>验证码</td>
            <td><input name="check" placeholder="请填写验证码"></td>
        </tr>

        <tr>
            <td colspan="2" align="center" ><input type="submit" value="提交"></td>

        </tr>

    </table>

</form>

</body>
</html>

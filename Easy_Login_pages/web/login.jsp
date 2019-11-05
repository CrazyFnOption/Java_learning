<%--
  Created by IntelliJ IDEA.
  User: wangshuxiao
  Date: 2019/11/5
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>

    <style>

        div{
            color: red;
        }
    </style>

    <script>
        window.onload = function (ev) {
            var img = document.getElementById("checkcode");
            img.onclick = function (ev1) {
                // 这里在Safari浏览器上面是可以换图片的，但是如果是在Chorme上面的话，需要加上时间戳
                // 不太清楚为什么时间戳这里比不过
                // 找到原因原来自己这里并没有返回的gettime 而返回的是getdate相应的函数
                var _time = new Date().getTime();
                img.src = "/wsx/checkCodeServlet?" + _time;
            }

        }
    </script>
</head>
<body>
Hello guys, welcome to become one of us....<br>
please input following form....<br><br><br>

    <form action="LoginServlet" method="post">
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

<div>

<%= request.getAttribute("cc_error") == null? "":request.getAttribute("cc_error")%>
<%= request.getAttribute("sp_error") == null? "":request.getAttribute("sp_error")%>
</div>
</body>
</html>

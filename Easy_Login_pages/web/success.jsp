<%@ page import="domain.User" %><%--
  Created by IntelliJ IDEA.
  User: wangshuxiao
  Date: 2019/11/5
  Time: 13:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>success</title>
</head>
<body>
        <h1><%=session.getAttribute("registered") == null ? "":session.getAttribute("registered")%></h1>
        <h1><%=session.getAttribute("user")%>，欢迎你回来，</h1>

        <%
            if (session.getAttribute("registered") != null) {
                session.removeAttribute("registered");
            }

        %>
</body>
</html>

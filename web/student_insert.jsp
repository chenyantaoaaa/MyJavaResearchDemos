<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2017/11/5
  Time: 1:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="StudentServlet"  method="post">
        姓名：<input type="text" name="name"><br>
        年龄：<input type="text" name="age"><br>
        成绩：<input type="text" name="score"><br>
        <input type="submit" value="输入">
        <input type="reset" value="重置">
    </form>
</body>
</html>

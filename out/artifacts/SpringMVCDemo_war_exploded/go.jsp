<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2017/3/26
  Time: 1:29
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Let'sGO</title>
</head>

<body>
${msg}
${test}
${locale}
<form action="redir" method="post" modelAttribute="model">
    <input name="name">
    <input name="color">
    <input type="submit" value="提交"/>
</form>
<script>

</script>
</body>
</html>

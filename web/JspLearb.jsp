<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2017/5/15
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fn"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    out.println("HelloWorld");
%>
<%
    String path=request.getServletPath();
    out.println(path);
    out.println("HelloWorld");
%>
<c:if test="${modle.name eq 'chen'}">    男</c:if>
<c:if test="${modle.name eq 'xiao'}">    女</c:if>
<c:forEach items="${List}" var="item">
    <tr><td>${item.name}</td><td>${item.color}</td></tr>
</c:forEach>
</body>
</html>

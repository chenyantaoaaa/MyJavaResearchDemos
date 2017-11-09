<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2017/3/26
  Time: 1:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="org.springframework.web.servlet.FlashMap" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
    String path = request.getContextPath();
    String basePath =
            request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Let'sGO</title>
</head>
<body>
<span>哈哈哈哈哈</span>
<%

    Enumeration en = request.getParameterNames();
    while(en.hasMoreElements())
        System.out.println(en.nextElement());
    List<FlashMap> list =(List<FlashMap>)session.getAttribute("org.springframework.web.servlet.support.SessionFlashMapManager.FLASH_MAPS");
    FlashMap fm = list.get(0);
    String msg=(String)fm.get("test5");

    String test1=request.getParameter("test1");
    String test2=request.getParameter("test2");
    String test3=request.getParameter("test3");
//    String test4=request.getParameter("test4");
    String test5=request.getParameter("test5");
%>
<p><%=test1%></p>
<p><%=test2%></p>
<p><%=test3%></p>
<p><%=test5%></p>
<p>${sessionScope['org.springframework.web.servlet.support.SessionFlashMapManager.FLASH_MAPS'][0]['test4']} </p>
<p><%=msg %></p>
<script>
</script>
</body>
</html>

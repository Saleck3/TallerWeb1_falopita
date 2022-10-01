<%--
  Created by IntelliJ IDEA.
  User: Shushu
  Date: 29/9/2022
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Suenio</title>
</head>
<body>
<h1>Welcome to the Dream World</h1>
<form:form action="suenio" method="post" modelAttribute="edadObtenida">
    Edad: <form:input path="edadObtenida" type="number"/>
    <button type="submit">Obtener Horas</button>
</form:form>
</body>
</html>

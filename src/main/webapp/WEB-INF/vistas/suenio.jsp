<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="es" class="min-vh-100 d-flex flex-column">
<head>
    <%@ include file="head.jsp" %>
    <title>Sueño</title>
</head>
<body class="min-vh-100 d-flex flex-column">
<%@ include file="header.jsp" %>
<h1>Welcome to the Dream World</h1>
<form:form action="suenio" method="post" modelAttribute="edadObtenida">
    Edad: <form:input path="edadObtenida" type="number"/>
    <button type="submit">Obtener Horas</button>
</form:form>
<%@ include file="footer.jsp" %>
</body>
</html>

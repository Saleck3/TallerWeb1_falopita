<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="es" class="min-vh-100 d-flex flex-column">
<head>
    <%@ include file="generales/head.jsp" %>
    <title>Sueño</title>
</head>
<body class="min-vh-100 d-flex flex-column">
<%@ include file="generales/header.jsp" %>
<div class="container">
    <form:form action="suenio" method="post" modelAttribute="edadObtenida">
        <div class="mb-3">
            <form:label path="edadObtenida" for="edadObtenida">Edad:</form:label>
            <form:input path="edadObtenida" id="edadObtenida" type="number"/>
        </div>
        <form:button class="btn btn-lg btn-primary ">Obtener Horas</form:button>

    </form:form>
</div>
<%@ include file="generales/footer.jsp" %>
</body>
</html>

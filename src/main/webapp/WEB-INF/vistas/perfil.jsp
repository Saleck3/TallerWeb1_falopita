<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="es" class="min-vh-100 d-flex flex-column">
<head>
    <%@ include file="head.jsp" %>
    <title>Perfil</title>
</head>
<body class="min-vh-100 d-flex flex-column">
<%@ include file="header.jsp" %>
<div class="container-md">
    <form:form modelAttribute="persona" action="perfil/modificar" method="POST">
        <div class="form-group">
            <form:label path="email" class="form-label">Email:</form:label>
            <form:input path="email" class="form-control" type="email"/>
            <c:if test="${not empty errorEmail}">
                <small class="text-bg-danger">${errorEmail}</small>
            </c:if>
        </div>
        <div class="form-group">
            <form:label path="password" class="form-label">Clave:</form:label>
            <form:input path="password" class="form-control" type="password"/>
            <c:if test="${not empty errorPassword}">
                <small class="text-bg-danger">${errorPassword}</small>
            </c:if>
        </div>
        <div class="form-group">
            <form:label path="nombre" class="form-label">Nombre:</form:label>
            <form:input path="nombre" class="form-control"/>
            <c:if test="${not empty errorNombre}">
                <small class="text-bg-danger">${errorNombre}</small>
            </c:if>
        </div>
        <div class="form-group">
            <form:label path="sexo" class="form-label">Sexo:</form:label>
            <form:input path="sexo" class="form-control"/>
            <c:if test="${not empty errorSexo}">
                <small class="text-bg-danger">${errorSexo}</small>
            </c:if>
        </div>
        <div class="form-group">
            <form:label path="edad" class="form-label">Edad:</form:label>
            <form:input path="edad" class="form-control"/>
            <c:if test="${not empty errorEdad}">
                <small class="text-bg-danger">${errorEdad}</small>
            </c:if>
        </div>
        <div class="form-group">
            <form:label path="peso" class="form-label">Peso:</form:label>
            <form:input path="peso" class="form-control"/>
            <c:if test="${not empty errorPeso}">
                <small class="text-bg-danger">${errorPeso}</small>
            </c:if>
        </div>
        <div class="form-group">
            <form:label path="altura" class="form-label">Altura:</form:label>
            <form:input path="altura" class="form-control"/>
            <c:if test="${not empty errorPeso}">
                <small class="text-bg-danger">${errorPeso}</small>
            </c:if>
        </div>

        <form:button type="submit" class="btn btn-primary">Modificar</form:button>
    </form:form>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
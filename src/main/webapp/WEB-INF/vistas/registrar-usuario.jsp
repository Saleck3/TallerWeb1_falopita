<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="es" class="min-vh-100 d-flex flex-column">
<head>
    <%@ include file="generales/head.jsp" %>
    <title>Registro de usuario</title>
</head>
<body class="min-vh-100 d-flex flex-column">
<%@ include file="generales/header.jsp" %>
<div class="container">
    <form:form action="registrar-usuario" method="POST" modelAttribute="persona">
        <h3 class="form-signin-heading">Nuevo Usuario</h3>
        <hr class="colorgraph">
        <br>

        ${mensaje}
        <div class="mb-3">
            <form:label path="nombre" for="nombre">Nombre:</form:label>
            <form:input path="nombre" id="nombre" class="form-control" required=""/>
            <form:label path="edad" for="edad">Edad:</form:label>
            <form:input type="number" path="edad" id="edad" class="form-control" required=""/>
            <form:label path="sexo" for="sexo">Sexo:</form:label>
            <form:select path="sexo" id="sexo" cssClass="form-control" required="">
                <form:option value="m">Masculino</form:option>
                <form:option value="f">Femenino</form:option>
                <form:option value="o">Otro</form:option>
            </form:select>
            <form:label path="email" for="email">Email:</form:label>
            <form:input path="email" id="email" class="form-control" required=""/>
            <form:label path="password" for="password">Password:</form:label>
            <form:input path="password" type="password" id="password" class="form-control" required=""/>
        </div>
        <form:button class="btn btn-lg btn-primary ">Registrarme</form:button>
        <a href="login" class="btn btn-lg btn-primary">Volver al inicio de sesion</a>

    </form:form>

    <c:if test="${not empty error}">
        <h4><span>${error}</span></h4>
        <br>
    </c:if>
</div>
<%@ include file="generales/footer.jsp" %>
</body>
</html>
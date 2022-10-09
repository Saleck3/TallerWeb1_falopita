<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="es" class="min-vh-100 d-flex flex-column">
<head>
    <%@ include file="head.jsp" %>
    <title>Login</title>
</head>
<body class="min-vh-100 d-flex flex-column">
<%@ include file="header.jsp" %>
<div class="container justify-content-center align-content-center">
    <form:form action="validar-login" method="POST" modelAttribute="datosLogin">
        <h3 class="form-signin-heading">Iniciar sesión</h3>
        <hr class="colorgraph">

        <form:label path="email" for="email">Email:</form:label>
        <form:input path="email" id="email" type="email" class="form-control"/>
        <form:label path="password" for="password">Password</form:label>
        <form:input path="password" type="password" id="password" class="form-control"/>

        <button class="btn btn-lg btn-primary btn-block" Type="Submit">Login</button>
    </form:form>
    <a href="registrar-usuario">Registrarme</a>
    <%--Bloque que es visible si el elemento error no esta vacio	--%>
    <c:if test="${not empty error}">
        <h4><span>${error}</span></h4>
        <br>
    </c:if>
    ${msg}
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
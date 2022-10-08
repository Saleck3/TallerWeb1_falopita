<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Perfil</title>
</head>
<body>
    <form:form modelAttribute="datosPerfil" action="/perfil" method="post">
        <label>Email:
            <form:input path="email"/>
        </label>
        <label>Clave:
            <form:password path="password" disabled="true"/>
        </label>
        <label>Nombre:
            <form:input path="nombre" disabled="true"/>
        </label>
        <label>Sexo:
            <form:input path="sexo" disabled="true"/>
        </label>
        <label>Edad:
            <form:input path="edad" disabled="true"/>
        </label>
        <label>Peso:
            <form:input path="peso" disabled="true"/>
        </label>
        <label>Altura:
            <form:input path="altura" disabled="true"/>
        </label>
    </form:form>
</body>
</html>

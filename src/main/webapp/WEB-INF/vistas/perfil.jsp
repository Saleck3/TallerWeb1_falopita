<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="es">
<head>
    <%@ include file="head.jsp" %>
    <title>Perfil</title>
</head>
<body>
    <%@ include file="header.jsp" %>
    <form:form modelAttribute="persona" action="perfil/modificar" method="POST" style="display:flex; flex-direction: column;">
        <label>Email:
            <form:input path="email"/>
        </label>
        <label>Clave:
            <form:input path="password" />
        </label>
        <label>Nombre:
            <form:input path="nombre" />
        </label>
        <label>Sexo:
            <form:input path="sexo" />
        </label>
        <label>Edad:
            <form:input path="edad" />
        </label>
        <label>Peso:
            <form:input path="peso" />
        </label>
        <label>Altura:
            <form:input path="altura" />
        </label>
        <form:button type="submit">Modificar</form:button>
    </form:form>
    <%@ include file="footer.jsp"%>
</body>
</html>
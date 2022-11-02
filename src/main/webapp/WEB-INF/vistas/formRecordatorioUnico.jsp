<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="es" class="min-vh-100 d-flex flex-column">
<head>
    <%@ include file="generales/head.jsp" %>
    <title>Creación de recordatorio</title>
</head>
    <body class="min-vh-100 d-flex flex-column">
        <%@ include file="generales/header.jsp" %>
        <main class="container">
            <form:form modelAttribute="datosRecordatorio" method="post" action="crear">
                <div class="mb-3">
                    <form:label path="contenido" class="form-form:label fs-5">Recordame...</form:label>
                    <form:input path="contenido" class="form-control"/>
                </div>
                <div class="mb-3">
                    <form:label path="fecha" class="form-label fs-5">En este dia...</form:label>
                    <form:input path="fecha" class="form-control" type="date"/>
                </div>
                <div class="mb-3">
                    <form:label path="hora" class="form-label fs-5">A esta hora...</form:label>
                    <form:input path="hora" class="form-control" type="time"/>
                </div>
                <form:button type="submit">Crear</form:button>
            </form:form>
        </main>
        <%@ include file="generales/footer.jsp" %>
    </body>
</html>
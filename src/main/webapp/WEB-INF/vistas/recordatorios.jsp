<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="es" class="min-vh-100 d-flex flex-column">
<head>
    <%@ include file="generales/head.jsp" %>
    <title>Recordatorios</title>
</head>
<body class="min-vh-100 d-flex flex-column">
    <!-- Header -->
    <%@ include file="generales/header.jsp" %>

    <!-- Main -->
    <main>
        <h1>Recordatorios</h1>
        <div>
            <form:form action="recordatorios/crear" modelAttribute="datosRecordatorio" method="post">
                <form:input path="contenido" type="text" placeholder="Cuestion a recordar..."/>

                <form:label path="strFecha">Fecha</form:label>
                <form:input path="strFecha" type="date"/>

                <form:label path="strHora">Hora</form:label>
                <form:input path="strHora" type="time"/>
                <form:button type="submit">Submit</form:button>
            </form:form>
        </div>
        <div>
            <c:forEach var="r" items="${recordatorios}">
                <p>${r.id}</p>
                <p>${r.contenido}</p>
                <p>${r.fechaNotificacion}</p>
            </c:forEach>
        </div>
    </main>

    <!-- Footer -->
    <%@ include file="generales/footer.jsp"%>
</body>
</html>

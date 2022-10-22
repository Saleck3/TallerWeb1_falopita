<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
            <form:form action="recordatorios/buscar" method="get">
                <form:input path="fechaFiltro" type="date" placeholder="Ingrese fecha filtro" />
                <form:button type="submit">Filtrar</form:button>
            </form:form>
        </div>
        <div>
            <form:form action="recordatorios/crear" modelAttribute="datosRecordatorio" method="post">
                <form:input path="contenido" type="text" placeholder="Cuestion a recordar..."/>

                <form:label path="fechaHora">Fecha y hora</form:label>
                <form:input path="fechaHora" type="datetime-local"/>

                <form:button type="submit">Recordame</form:button>
            </form:form>
        </div>
        <div>
            <c:forEach var="r" items="${recordatorios}">
            <div class="bg-secondary rounded d-inline-block">
                <div>
                    <a class="btn btn-primary" href="recordatorios/eliminar?id=${r.id}">Eliminar</a>
                    <a class="btn btn-primary" href="recordatorios/ocultar?id=${r.id}">Ocultar</a>
                </div>
                <div>
                    <h4>${r.contenido}</h4>
                    <h5><fmt:formatDate pattern = "dd-MM-yyyy 'a las' HH:mm" value="${r.fechaNotificacion}" /></h5>
                </div>
            </div>
            </c:forEach>
        </div>
    </main>

    <!-- Footer -->
    <%@ include file="generales/footer.jsp"%>
</body>
</html>

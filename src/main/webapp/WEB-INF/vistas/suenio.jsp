<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="es" class="min-vh-100 d-flex flex-column">
<head>
    <%@ include file="generales/head.jsp" %>
    <title>Sueño</title>
</head>
<body class="min-vh-100 d-flex flex-column">
<%@ include file="generales/header.jsp" %>
<div class="container">
    <h1>Recomendacion de horas de sueño</h1>

    <c:choose>
        <c:when test="${not empty error}">
            <h3 class="text-bg-danger">${error}</h3>
            <a href="perfil" class="btn btn-primary">Modificar datos</a>
            <br>
        </c:when>

        <c:otherwise>
            <h3> Segun sus datos registrados se recomienda que duerma de ${recomendacion.getMinimo()}
                a ${recomendacion.getMaximo()} horas</h3>
            <a href="perfil" class="btn btn-primary">Modificar datos</a>

            <form action="recordatorio-fin" method="post">
                <div class="mb-3">
                    <label path="recordatorio-fin"
                           for="hora-fin">Generar recordatorio para levantarse a las </label>
                    <input path="recordatorio-fin" id="hora-fin" name="hora-fin" type="number" disabled/>
                    <label path="recordatorio-fin" for="hora-fin">horas (proximamente)</label>
                </div>
            </form>

            <form action="recordatorio-inicio" method="post">
                <div class="mb-3">
                    <label path="recordatorio-inicio"
                           for="hora-inicio">Generar recordatorio para acostarse a las </label>
                    <input path="recordatorio-inicio" id="hora-inicio" name="hora-inicio" type="number" disabled/>
                    <label path="recordatorio-inicio" for="hora-inicio">horas (proximamente)</label>
                </div>
            </form>
        </c:otherwise>
    </c:choose>

</div>
<%@ include file="generales/footer.jsp" %>
</body>
</html>



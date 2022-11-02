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
    <main class="container py-4 my-auto">
        <h2>Recordatorios</h2>
        <a class="btn btn-primary" href="recordatorios/formCrear?opc=0">Crear recordatorio unico</a>
        <a class="btn btn-primary" href="recordatorios/formCrear?opc=1">Crear recordatorio recurrente</a>
        <div class="container">
            <div class="row">
                <form class="d-flex" method="get">
                    <input class="form-control" type="text" placeholder="Contenido...">
                    <input class="form-control" type="datetime-local">
                    <div class="dropdown">
                        <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">Tags</button>
                        <ul class="dropdown-menu">
                            <li class="dropdown-item">
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" id="tag1" name="tags">
                                    <label class="form-check-label" for="tag1">Tag 1</label>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <button class="btn btn-outline-primary" type="submit">Filtrar</button>
                </form>
            </div>
        </div>
        <div class="container">
            <c:forEach var="r" items="${recordatorios}">
            <div style="background-color: lightblue">
                <p>Id: ${r.id}</p>
                <p>Contenido: ${r.contenido}</p>
                <p>Tipo: ${r.tipo.name()}</p>
                <p>Fechas</p>
                <ul>
                    <c:forEach var="f" items="${r.fechas}">
                    <li>${f.fecha.toString()} a las ${f.hora.toString()}</li>
                    </c:forEach>
                </ul>
                <div>
                    <a class="btn btn-primary" href="recordatorios/eliminar?id=${r.id}">Eliminar</a>
                    <a class="btn btn-primary" href="recordatorios/ocultar?id=${r.id}">Ocultar</a>
                </div>
            </c:forEach>
        </div>
    </main>
    <!-- Footer -->
    <%@ include file="generales/footer.jsp"%>
</body>
</html>

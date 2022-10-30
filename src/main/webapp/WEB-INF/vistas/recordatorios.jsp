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
        <div class="container">
            <div class="row" style="min-height: 450px">
                <div class="col-8 ps-0">
                    <form class="container" method="get">
                        <div class="row">
                            <div class="col px-1">
                                <input class="form-control" type="text" placeholder="Contenido...">
                            </div>
                            <div class="col px-1">
                                <input class="form-control" type="datetime-local">
                            </div>
                            <div class="col px-1">
                                <div class="dropdown">
                                    <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                        Tags
                                    </button>
                                    <ul class="dropdown-menu">
                                        <li class="dropdown-item">
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" id="tag1" name="tags">
                                                <label class="form-check-label" for="tag1">Tag 1</label>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="col px-1 text-start">
                                <button class="btn btn-outline-primary" type="submit">Filtrar</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-4 py-2 pb-2 pt-0">
                    <form:form modelAttribute="datosRecordatorio" method="post">
                        <div class="mb-3">
                            <form:label path="contenido" class="form-form:label fs-5">Recordame...</form:label>
                            <form:input path="contenido" class="form-control"/>
                        </div>
                        <div class="mb-3">
                            <fieldset>
                                <legend class="fs-5">Y que se me va a recordar...</legend>
                                <div class="form-check">
                                    <form:radiobutton class="form-check-input" path="tipo" value="0" onclick="mostrarSoloUnaVez()"></form:radiobutton>
                                    <form:label path="tipo" class="form-check-label">Solo una vez</form:label>
                                </div>
                                <div class="form-check">
                                    <form:radiobutton class="form-check-input" path="tipo" value="1" onclick="mostrarVariasVeces()"></form:radiobutton>
                                    <form:label path="tipo" class="form-check-label">Varias veces</form:label>
                                </div>
                            </fieldset>
                        </div>
                        <div id="soloUnaVez" class="mb-3" style="display: none">
                            <div class="mb-3">
                                <form:label path="fecha" class="form-label fs-5">En este dia...</form:label>
                                <form:input path="fecha" class="form-control" type="date"/>
                            </div>
                            <div class="mb-3">
                                <form:label path="hora" class="form-label fs-5">A esta hora...</form:label>
                                <form:input path="hora" class="form-control" type="time"/>
                            </div>
                        </div>
                        <div id="variasVeces" class="mb-3" style="display: none">
                            <div class="mb-3">
                                <form:label path="frecuencia" class="form-label fs-5">Con una frecuencia...</form:label>
                                <form:select path="frecuencia" class="form-select">
                                    <option value="0">Diaria</option>
                                    <option value="1">Semanal</option>
                                    <option value="2">Mensual</option>
                                    <option value="3">Anual</option>
                                </form:select>
                            </div>
                            <div class="mb-3">
                                <form:label path="cantidadOcurrencias" class="form-form:label fs-5">Esta cantidad de veces...</form:label>
                                <form:input path="cantidadOcurrencias" class="form-control" type="number" />
                            </div>
                            <div class="mb-3">
                                <form:label path="fecha" class="form-form:label fs-5">A partir de...</form:label>
                                <form:input path="fecha" class="form-control" type="date"/>
                            </div>
                            <div class="mb-3">
                                <form:label path="hora" class="form-form:label fs-5">A las...</form:label>
                                <form:input path="hora" class="form-control" type="time"/>
                            </div>
                        </div>
                        <button class="btn btn-primary" type="submit">Recordarme</button>
                    </form:form>
                </div>
            </div>
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
    <script>
        function mostrarSoloUnaVez(){
            document.getElementById("soloUnaVez").style.display = 'block';
            document.getElementById("variasVeces").style.display = 'none';
        }

        function mostrarVariasVeces(){
            document.getElementById("soloUnaVez").style.display = 'none';
            document.getElementById("variasVeces").style.display = 'block';
        }
    </script>
</body>
</html>

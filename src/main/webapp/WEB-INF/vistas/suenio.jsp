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
        <c:when test="${not empty errorEdad}">
            <h3 class="text-bg-danger">${errorEdad}</h3>
            <a href="perfil" class="btn btn-primary">Modificar datos</a>
            <br>
        </c:when>

        <c:otherwise>
            <main class="row">
                <div class="card-columns col col-lg-9">
                    <h3> Segun sus datos registrados se recomienda que duerma de ${recomendacion.getMinimo()}
                        a ${recomendacion.getMaximo()} horas</h3>
                    <a href="perfil" class="btn btn-primary">Modificar datos</a>

                    <!-- TODO agregar mensaje de error-->


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
                            <input path="recordatorio-inicio" id="hora-inicio" name="hora-inicio" type="number"
                                   disabled/>
                            <label path="recordatorio-inicio" for="hora-inicio">horas (proximamente)</label>
                        </div>
                    </form>

                </div>
                <aside name="registroNuevo" class="col col-lg-3 ">
                    <form action="suenio/nuevoRegistro" method="POST" class="card">
                        <div class="card-body">
                            <label path="horaInicio" name="horaInicio" for="horaInicio"
                                   class="form-label">Hora inicio:</label>
                            <input path="horaInicio" required="true" type="datetime-local" name="horaInicio"
                                   id="horaInicio" class="form-control mb-3" />
                            <label path="horaFin" name="horaFin" for="horaFin"
                                   class="form-label">Hora fin:</label>
                            <input path="horaFin" type="datetime-local" name="horaFin" id="horaFin"
                                   class="form-control mb-3"/>
                            <button type="submit" class="btn btn-primary">Ingresar</button>
                        </div>
                    </form>
                </aside>
            </main>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">Hora Inicio</th>
                    <th scope="col">Hora fin</th>
                    <th scope="col">Cantidad de horas</th>
                    <th scope="col">Eliminar</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="registro" items="${registros}">
                    <tr>

                        <td>${registro.printHoraInicio()}</td>
                        <td>${registro.printHoraFin()}</td>
                        <td>${registro.cantidadHoras}</td>
                        <td><a href="suenio/eliminarRegistro?idRegistro=${registro.ID}"class="link-unstyled icon-button">X</a> </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>

</div>
<%@ include file="generales/footer.jsp" %>
</body>
</html>



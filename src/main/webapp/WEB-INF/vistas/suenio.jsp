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
                    <span>${recomendacion.getMensaje()}</span>
                    <h3> Segun sus datos registrados se recomienda que duerma de ${recomendacion.getMinimo()}
                        a ${recomendacion.getMaximo()} horas</h3>
                    <a href="perfil" class="btn btn-primary">Modificar datos</a>


                </div>
                <aside name="registroNuevo" class="col col-lg-3 ">
                    <form action="suenio/nuevoRegistro" method="POST" class="card">
                        <div class="card-body">
                            <label path="horaInicio" name="horaInicio" for="horaInicio"
                                   class="form-label">Hora inicio:</label>
                            <input path="horaInicio" required="true" type="datetime-local" name="horaInicio"
                                   id="horaInicio" class="form-control mb-3"/>
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
                        <td><a href="suenio/eliminarRegistro?idRegistro=${registro.ID}"
                               class="link-unstyled icon-button">X</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>

    <div id="container" style="width:100%; height:400px;"></div>
    <script src="https://code.highcharts.com/highcharts.js"></script>
    <script src="https://code.highcharts.com/modules/accessibility.js"></script>
    <script>
        Highcharts.chart('container', {

            title: {
                text: 'Tu sueño en la semana'
            },
            yAxis: {
                title: {
                    text: 'Horas'
                }
            },

            xAxis: {
                type: 'datetime',
                accessibility: {
                    rangeDescription: 'Dias en la ultima semana'
                }
            },

            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'middle'
            },

            plotOptions: {
                series: {
                    pointStart: Date.UTC(2022, 10, 3),
                    pointInterval: 24 * 3600 * 1000 // one day
                }
            },

            series: [{
                name: 'Horas registradas',
                data: [7, 8, 9, 7, 5, 10, 8]
            }, {
                name: 'Minima recomendada',
                data: [7]
            }, {
                name: 'Maxima recomendada',
                data: [9]
            }],


            responsive: {
                rules: [{
                    condition: {
                        maxWidth: 500
                    },
                    chartOptions: {
                        legend: {
                            layout: 'horizontal',
                            align: 'center',
                            verticalAlign: 'bottom'
                        }
                    }
                }]
            }

        });
    </script>
</div>
<%@ include file="generales/footer.jsp" %>
</body>
</html>



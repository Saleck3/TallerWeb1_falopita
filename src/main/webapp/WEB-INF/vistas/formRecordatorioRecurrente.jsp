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
                <form:button type="submit">Crear</form:button>
            </form:form>
        </main>
        <%@ include file="generales/footer.jsp" %>
    </body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: Alejandro
  Date: 13/10/22
  Time: 01:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="es" class="min-vh-100 d-flex flex-column">
<head>
    <%@ include file="generales/head.jsp" %>
    <title>Notas</title>
</head>
<body class="min-vh-100 d-flex flex-column">
<%@ include file="generales/header.jsp" %>
<div class="container-md mb-3">
    <div class="clearfix">
        <h3 class="form-signin-heading" style="display: inline-block">Notas</h3>
        <c:choose>
            <c:when test="${param.archivadas == true}">
                <a href="notas" class="link-unstyled float-end" style="font-size: 1.5em"><i
                        class="ri-mail-fill"></i></a>
            </c:when>
            <c:otherwise>
                <a href="notas?archivadas=true" class="link-unstyled float-end" style="font-size: 1.5em"><i
                        class="ri-mail-line"></i></a>
            </c:otherwise>
        </c:choose>

    </div>
    <hr class="colorgraph">
    <br>
    <main class="row">
        <div class="card-columns col col-lg-9">
            <div class="card-columns flex">
                <c:forEach var="nota" items="${notas}">
                    <c:choose>
                        <c:when test="${nota.estado == 'ACTIVO'}">
                            <div class="card mb-3 nota">
                                <div class="card-header clearfix">
                                    <span>${nota.titulo}</span>
                                    <div name="links" class="float-end grupo-links">
                                        <a href="anclarNota?idNota=${nota.ID}"><i class="ri-pushpin-line"></i></a>
                                        <a href="archivarNota?idNota=${nota.ID}"><i class="ri-download-2-line"></i></a>
                                        <a href="eliminarNota?idNota=${nota.ID}"><i class="ri-close-line"></i></a>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <p class="card-text">${nota.contenido}</p>
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${nota.estado == 'ANCLADO'}">
                            <div class="card mb-3 nota-anclada">
                                <div class="card-header clearfix">
                                    <span>${nota.titulo}</span>
                                    <div name="links" class="float-end grupo-links">
                                        <a href="desanclarNota?idNota=${nota.ID}"><i class="ri-pushpin-2-fill"></i></a>
                                        <a href="archivarNota?idNota=${nota.ID}"><i class="ri-download-2-line"></i></a>
                                        <a href="eliminarNota?idNota=${nota.ID}"><i class="ri-close-line"></i></a>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <p class="card-text">${nota.contenido}</p>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="card mb-3 nota-archivada">
                                <div class="card-header clearfix">
                                    <span>${nota.titulo}</span>
                                    <div name="links" class="float-end grupo-links">
                                        <a href="desarchivarNota?idNota=${nota.ID}"><i class="ri-upload-2-line"></i></a>
                                        <a href="eliminarNota?idNota=${nota.ID}"><i class="ri-close-line"></i></a>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <p class="card-text">${nota.contenido}</p>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
        </div>
        <aside name="notaNueva" class="col col-lg-3 ">
            <form:form modelAttribute="nuevaNota" action="notas/nuevaNota" method="POST" class="card">
                <div class="card-body">
                    <form:label path="titulo" name="titulo" for="titulo" class="form-label">Titulo:</form:label>
                    <form:input path="titulo" name="titulo" id="titulo" cssClass="form-control"/>
                    <form:label path="contenido" name="contenido" for="contenido"
                                class="form-label">Descripcion:</form:label>
                    <form:textarea path="contenido" name="contenido" id="contenido" cssStyle="min-height: 9em"
                                   class="form-control mb-3"/>
                    <form:button type="submit" class="btn btn-primary">Ingresar</form:button>
                </div>
            </form:form>
        </aside>
    </main>
</div>
<%@ include file="generales/footer.jsp" %>
</body>
</html>
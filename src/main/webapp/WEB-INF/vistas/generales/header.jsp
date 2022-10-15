<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="container-fluid bg-black bg-opacity-25">

    <nav class="navbar navbar-expand-lg bg-light ">
        <div class="container-fluid justify-content-between">
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo01"
                    aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <a class="navbar-brand" href="home"><h2>SmartTime</h2></a>
            <div class="collapse navbar-collapse justify-content-between" id="navbarTogglerDemo01">



                <ul class="navbar-nav mb-2 mb-lg-0 ">
                    <% if (request.getSession().getAttribute("ID") != null) {%>
                    <li class="nav-item active">
                        <a class="nav-link" href="suenio">Calcular suenio</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="notas">Ver notas</a>
                    </li>
                    <%}%>
                </ul>

                <ul class="navbar-nav mb-2 mb-lg-0 ">
                    <!-- Si no esta logueado -->
                    <% if (request.getSession().getAttribute("ID") == null) {%>
                    <li class="nav-item active">
                        <a class="nav-link" href="login">Login</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="registrar-usuario">Registrarse</a>
                    </li>
                    <%} else {%>
                    <!-- Si esta logueado -->
                    <li class="nav-item active">
                        <a class="nav-link" href="perfil">Perfil</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="cerrar-sesion">Cerrar sesion</a>
                    </li>
                    <%}%>
                </ul>
            </div>
        </div>
    </nav>
</header>
<%--
  Created by IntelliJ IDEA.
  User: Rolon
  Date: 11/10/2022
  Time: 21:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="es" class="min-vh-100 d-flex flex-column">
<head>

    <%@ include file="generales/head.jsp" %>
    <link rel="stylesheet" href="css\evo-calendar.min.css">
    <title>SmartTime</title>


</head>
<body class="min-vh-100 d-flex flex-column">
<%@ include file="generales/header.jsp" %>
<div class="container">
    <div id="calendar"></div>
</div>


<%@ include file="generales/footer.jsp" %>
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script src="js/evo-calendar.min.js"></script>
<script>
    // initialize your calendar, once the page's DOM is ready

    let i;
    $(document).ready(function () {
        let eventiList = [];
        let eventos = JSON.stringify();
        console.log(typeof eventos);
        console.log({eventos});

        eventos.forEach((evento) => {
            let newEvent = {
                id: evento.ID,
                name: eventos.name,
                date: eventos.date,
                description: eventos.description,
                type: eventos.type,
                everyYear: eventos.everyYear,
            };
            eventiList.push(newEvent);
        });


        console.log(eventiList);
        $('#calendar').evoCalendar({
            'calendarEvents': eventiList
        });

    })
</script>
</body>

</html>
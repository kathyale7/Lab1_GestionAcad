<%-- 
    Document   : registrarnotas
    Created on : 24 abr. 2022, 16:31:05
    Author     : ksand
                 rolan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Agregar Grupo</title>
        <link rel="stylesheet" type="text/css" href="estilo.css">
    </head>
    <body background="green">

        <div class="topnav">
            <a href="inicioprofesores.jsp">Inicio</a>
            <a href="index.jsp">Cerrar Sesion</a>
        </div>
        <div class="center11">

            <div class="space"></div>

            <Label>Actualizar Nota</Label>

            <div class="space"></div>

            <div>
                <form action="historial_alumno">
                    <%int cedula = Integer.parseInt((String) request.getAttribute("cedula"));%>
                    <input type="text" name="cedula" value ="<%=cedula%>" readonly>
                    <%int curso = Integer.parseInt((String) request.getAttribute("curso"));%>
                    <input type="text" name="curso" value ="<%=curso%>" readonly>
                    <%int grupo = Integer.parseInt((String) request.getAttribute("grupo"));%>
                    <input type="text" name="grupo" value ="<%=grupo%>" readonly>
                    <%int estado = Integer.parseInt((String) request.getAttribute("estado"));%>
                    <input type="text" name="estado" value ="<%=grupo%>" readonly>
                    <input type="number" name="nota" placeholder="Nota" required>
                    <button type="submit" name="accion" value="NotaNueva"> Actualizar</button>

                </form>
            </div>

            <div>


            </div>

        </div>
    </body>
</html>

<%-- 
    Document   : nuevamatricula
    Created on : 24 abr. 2022, 15:40:00
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
            <a href="inicioadmin.html">Inicio</a>
            <a href="index.html">Cerrar Sesion</a>
        </div>
        <div class="center10">

            <div class="space"></div>

            <Label>Agregar Matricula</Label>

            <div class="space"></div>

            <div>
                <form action="historial_alumno">
                    <%int id = Integer.parseInt((String) request.getAttribute("id_edit"));%>
                    
                    <input type="text" name="alumno_id" value ="<%=id%>" readonly>
                    <input type="text" name="curso_id" placeholder="Curso" required>
                    <input type="number" name="num_grupo" placeholder="N° Grupo" required>
                    <input type="number" name="estado" value ="102" readonly>
                     <input type="number" name="nota" value ="0" readonly>
                    <button type="submit" name="accion" value="Agregar"> Agregar</button>

                </form>
            </div>

            <div>


            </div>

        </div>
    </body>
</html>


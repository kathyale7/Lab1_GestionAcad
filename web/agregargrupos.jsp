<%-- 
    Document   : agregargrupos
    Created on : 23 abr. 2022, 22:57:04
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
        <div class="center12">

            <div class="space"></div>

            <Label>Agregar Grupo</Label>

            <div class="space"></div>

            <div>
                <form action="mantenimientogrupo">
                    <%int id = Integer.parseInt((String) request.getAttribute("id_edit"));%>
                    <input type="number" name="codigo" placeholder="Código" required>
                    <input type="text" name="curso" value ="<%=id%>" readonly>
                    <input type="number" name="ciclo" value="200" readonly>
                    <input type="number" name="num_grupo" placeholder="N° Grupo" required>
                    <input type="text" name="horario" placeholder="Horario" required>
                    <input type="number" name="profesor" placeholder="Profesor" required>
                    <button type="submit" name="accion" value="Agregar"> Agregar</button>

                </form>
            </div>

            <div>


            </div>

        </div>
    </body>
</html>

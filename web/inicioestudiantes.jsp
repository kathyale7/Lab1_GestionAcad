<%-- 
    Document   : inicioestudiantes
    Created on : 24 abr. 2022, 00:03:28
    Author     : ksand
--%>

<%@page import="java.util.Iterator"%>
<%@page import="Logica.matricula"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Inicio Estudiante</title>
        <link rel="stylesheet" type="text/css" href="estilo.css">
    </head>
    <body background="green">

        <div class="topnav">
            <%int id = 109381291;%>
            <a href="historial_alumno?accion=mi_historial&id_alumno=<%=id%>">Consulta historial</a>
            <a href="index.jsp">Cerrar Sesion</a>
        </div>
        



    </body>
</html>

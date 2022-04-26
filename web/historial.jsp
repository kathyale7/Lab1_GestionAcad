<%-- 
    Document   : historial
    Created on : 22 abr. 2022, 21:15:22
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
        <title>Historial</title>
        <link rel="stylesheet" type="text/css" href="estilo.css">
    </head>
    <body background="green">

        <div class="topnav">
            <a href="mantenimientoalumnos?accion=ver">Inicio</a>
            <a href="index.html">Cerrar Sesion</a>
        </div>

        <div class="tablaCursos4">
            <table>
                <thead>
                    <tr>
                        <th>Cedula</th>
                        <th>Curso</th>
                        <th>Grupo</th>
                        <th>Estado</th>
                        <th>Nota</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <%
                    List<matricula> historial = (List<matricula>) request.getAttribute("HistorialAlumno");
                    Iterator<matricula> iter;
                    iter = historial.iterator();
                    matricula m = null;
                    int alumno = 0;
                    while (iter.hasNext()) {

                        m = iter.next();


                %>

                <tbody>
                    <tr>
                        <td><%=m.getAlumno_id()%></td>
                        <td><%=m.getCurso_id()%></td>
                        <td><%=m.getGrupo_num()%></td>
                        <td><%=m.getEstado()%></td>
                        <td><%=m.getNota()%></td>

                        <td>
                            <a class=Opciones href="#">Eliminar Matricula</a>
                        </td>
                        <%}%>
                    </tr>

                </tbody>
            </table>
            <div class="NuevaBusqueda">
                <a href="historial_alumno?accion=nueva_matricula&alumno=<%=m.getAlumno_id()%>">Matricular nuevo curso</a>
            </div>
                <div class="space"></div>
            <div class="NuevaBusqueda">
                <a href="mantenimientoalumnos?accion=ver">Volver...</a>
            </div>

    </body>
</html>

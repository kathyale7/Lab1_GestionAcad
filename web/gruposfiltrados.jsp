<%-- 
    Document   : gruposfiltrados
    Created on : 24 abr. 2022, 16:47:14
    Author     : ksand
--%>

<%@page import="java.util.Iterator"%>
<%@page import="Logica.grupo"%>
<%@page import="Logica.grupo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Grupos</title>
        <link rel="stylesheet" type="text/css" href="estilo.css">
    </head>
    <body background="green">

        <div class="topnav">
            <a href="inicioprofesores.jsp">Inicio</a>
            <a href="index.jsp">Cerrar Sesion</a>
        </div>

        <div class="tablaCursos3">
            <table>
                <thead>
                    <tr>
                        <th>Codigo</th>
                        <th>Ciclo</th>
                        <th>N° Grupo</th>
                        <th>Curso</th>
                        <th>Horario</th>
                        <th>Profesor</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <%
                    List<grupo> grupos = (List<grupo>) request.getAttribute("ListaGrupos");
                    Iterator<grupo> iter;
                    iter = grupos.iterator();
                    grupo m = null;
                    while (iter.hasNext()) {

                        m = iter.next();


                %>

                <tbody>
                    <tr>
                        <td><%=m.getCODIGO()%></td>
                        <td><%=m.getCURSO_ID()%></td>
                        <td><%=m.getCICLO_ID()%></td>
                      
                        <td><%=m.getNUM_GRUPO()%></td>
                        <td><%=m.getHORARIO()%></td>
                        <td><%=m.getPROFESOR_ID()%></td>
                        <td> 
                            <a class=Opciones href=historial_alumno?accion=matricula_grupo&num_grupo=<%=m.getCODIGO()%>>Ver estudiantes</a>

                        </td>
                    </tr>
                    <%}%>

                </tbody>
            </table>

      
    </body>
</html>

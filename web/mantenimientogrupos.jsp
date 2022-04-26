<%-- 
    Document   : mantenimientogrupos
    Created on : 23 abr. 2022, 22:34:15
    Author     : ksand
                 Rolando
--%>

<%@page import="java.util.Iterator"%>
<%@page import="Logica.grupo"%>
<%@page import="java.util.List"%>
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
            <a href="inicioadmin.html">Inicio</a>
            <a href="index.jsp">Cerrar Sesion</a>
        </div>

        <div class="tablaCursos6">
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
                            <a class=Opciones href=#>Modificar</a>

                        </td>
                    </tr>
                    <%}%>

                </tbody>
            </table>
            <div class="NuevaBusqueda">
                <a href="ofertaacademica?accion=ver">Volver...</a>
            </div>
            

    </body>
</html>

<%-- 
    Document   : cursosfiltrados
    Created on : 23 abr. 2022, 21:59:06
    Author     : ksand
--%>

<%@page import="java.util.Iterator"%>
<%@page import="Logica.curso"%>
<%@page import="Logica.curso"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Oferta Academica</title>
        <link rel="stylesheet" type="text/css" href="estilo.css">
    </head>
    <body background="green">

        <div class="topnav">
            <a href="inicioadmin.html">Inicio</a>
            <a href="index.html">Cerrar Sesion</a>
        </div>

        <div class="">

            <div class="space"></div>

            

            



        </div >
                <div class="tablaCursos">
        <Label>Cursos de carrera</Label>
            <table>
                <thead>
                    <tr>
                        <th>Código</th>
                        <th>Curso</th>
                        <th>Creditos</th>
                        <th>Horas semanales</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <%

                    List<curso> cursos = (List<curso>) request.getAttribute("ListaCursos");

                    Iterator<curso> iter2;
                    iter2 = cursos.iterator();
                    curso m = null;
                    while (iter2.hasNext()) {

                        m = iter2.next();


                %>

                <tbody>
                    <tr>
                        <td><%=m.getCodigo()%></td>
                        <td><%=m.getNombre()%></td>
                        <td><%=m.getCreditos()%></td>
                        <td><%=m.getHoras_semanales()%></td>
                        <td> 

                            <a class=Opciones href="mantenimientogrupo?accion=Buscar_grupos&id_curso=<%=m.getCodigo()%>">Ver Grupos</a>
                            <a class=Opciones href="mantenimientogrupo?accion=formagregar&id_curso=<%=m.getCodigo()%>">Agregar Grupo</a>

                        </td>


                    </tr>
                    <%}%>
                </tbody>
            </table>
            <div class="NuevaBusqueda">
                <a href="ofertaacademica?accion=ver">Nueva busqueda</a>
            </div>
        </div>



    </body>
</html>

<%-- 
    Document   : cursosxcarrera
    Created on : 22 abr. 2022, 22:49:59
    Author     : ksand
--%>

<%@page import="java.util.Iterator"%>
<%@page import="Logica.curso"%>
<%@page import="Logica.curso"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Mantenimiento de carreras</title>
        <link rel="stylesheet" type="text/css" href="estilo.css">
    </head>
    <body background="green">

        <div class="topnav">
            <a href="inicioadmin.html">Inicio</a>
            <a href="index.html">Cerrar Sesion</a>
        </div>
        <div class="center2">

            <div class="space"></div>

            <Label>Agregar Curso</Label>

            <div class="space"></div>

            <div>
                <form action="mantenimientocurso">
                    <%int id = Integer.parseInt((String) request.getAttribute("id_edit"));%>
                    <input type="number" name="codigo" placeholder="Código" required>
                    <input type="text" name="curso" placeholder="Curso" required>
                    <input type="number" name="creditos" placeholder="Creditos" required>
                    <input type="number" name="horassemanales" placeholder="Horas Semanales" required>
                    <input type="number" name="carrera" value ="<%=id%>" readonly>
                    <input type="number" name="ciclo" value="200" readonly>
                    <button type="submit" name="accion" value="Agregar"> Agregar</button>

                </form>
            </div>

            <div>


            </div>





        </div>




        <div class="tablaCursos2">
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

                            <a class=Opciones href="mantenimientocurso?accion=eliminar&id_curso=<%=m.getCodigo()%>&id_carrera=<%=m.getCarrera_id()%>">Eliminar Curso</a>

                        </td>


                    </tr>
                    <%}%>
                </tbody>
            </table>
            <div class="NuevaBusqueda">
                <a href="mantenimientocarrera?accion=ver">Volver...</a>
            </div>
        </div>
</html>

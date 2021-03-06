<%-- 
    Document   : listarcursos
    Created on : 21 abr. 2022, 21:15:19
    Author     : ksand
--%>

<%@page import="java.util.Iterator"%>
<%@page import="Logica.curso"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Mantenimiento de Cursos</title>
        <link rel="stylesheet" type="text/css" href="estilo.css">
    </head>
    <body background="green">


        <div class="topnav">
            <a href="inicioadmin.html">Inicio</a>
            <a href="index.html">Cerrar Sesion</a>
        </div>

        <div class="center6">

            <div class="space"></div>

            <Label>Buscar Cursos</Label>

            <div class="space"></div>
            
            <div class="campoboton">
                <form action="mantenimientocurso">
                    <input type="text" name="codigo" placeholder ="Codigo" required>
                    <button type="submit" name="accion" value="Buscar_c">Buscar</button>
                </form>
            </div> 
            <div class="campoboton">
                <form action="mantenimientocurso">
                    
                    <input type="text" name="buscar" placeholder="Nombre" required>
                   <button type="submit" name="accion" value="Buscar_n">Buscar</button>
                    
                </form>
            </div>
            

            <div class="campoboton2">
                <form action="mantenimientocurso">
                   
                    <input type="text" name="carrera" placeholder="Carrera" required>
                   <button type="submit" name="accion" value="Buscar_ca">Buscar</button>
                </form>
                <div class="NuevaBusqueda">
                    <a href="mantenimientocurso?accion=ver">Listar todos los cursos</a>
                </div>
            </div>



        </div>
        <div class="center5">

            <table>
                <thead>
                    <tr>
                        <th>Codigo</th>
                        <th>Curso</th>
                        <th>Creditos</th>
                        <th>Horas Semanales</th>
                        <th>Carrera</th>
                        <th>Ciclo</th>

                    </tr>
                </thead>
                <%
                    List<curso> cursos = (List<curso>) request.getAttribute("ListaCursos");
                    Iterator<curso> iter;
                    iter = cursos.iterator();
                    curso m = null;
                    while (iter.hasNext()) {

                        m = iter.next();


                %>

                <tbody>
                    <tr>
                        <td><%=m.getCodigo()%></td>
                        <td><%=m.getNombre()%></td>
                        <td><%=m.getCreditos()%></td>
                        <td><%=m.getHoras_semanales()%></td>
                        <td><%=m.getCarrera_id()%></td>
                        <td><%=m.getCiclo_id()%></td>

                        
                    </tr>
                    <%}%>
                </tbody>
            </table>
        </div>



    </body>
</html>

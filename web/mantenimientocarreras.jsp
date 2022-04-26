<%-- 
    Document   : mantenimientocarreras
    Created on : 22 abr. 2022, 22:09:01
    Author     : ksand
                 rolan
--%>

<%@page import="Logica.carrera"%>
<%@page import="java.util.Iterator"%>
<%@page import="Logica.curso"%>
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

        <div class="buscarCarrera">

            <div class="space"></div>

            <Label>Buscar Carrera</Label>

            <div class="space"></div>

            <div class="campoboton">
                <form action="mantenimientocarrera">

                        <input type="text" name="buscar" placeholder="Nombre" required>
                        <button type="submit" name="accion" value="Buscar_n"> Buscar</button>

                    </form>
            </div>
                <div class="campoboton2">
                    <form action="mantenimientocarrera">

                        <input type="text" name="codigo" placeholder="Codigo" required>
                        <button type="submit" name="accion" value="Buscar_c"> Buscar</button>

                    </form>
                    <div class="NuevaBusqueda">
                        <a href="mantenimientocarrera?accion=ver">Listar todas las carreras</a>	
                    </div>
                </div>
            
            



        </div>
        <div class="tablaCarreras">
            <table>
                <thead>
                    <tr>
                        <th>Codigo</th>
                        <th>Nombre</th>
                        <th>Titulo</th>
                        <th>Acciones</th>


                    </tr>
                </thead>
                <%
                    List<carrera> carreras = (List<carrera>) request.getAttribute("ListaCarreras");
                    Iterator<carrera> iter;
                    iter = carreras.iterator();
                    carrera c = null;
                    while (iter.hasNext()) {

                        c = iter.next();


                %>

                <tbody>
                    <tr>
                        <td><%=c.getCodigo()%></td>
                        <td><%=c.getNombre()%></td>
                        <td><%=c.getTitulo()%></td>
                        <td> 
                            <a class=Opciones href="mantenimientocurso?accion=curso-carrera&carrera=<%=c.getCodigo()%>">Ver Cursos</a>
                            
                        </td>


                    </tr>
                    <%}%>
                </tbody>
            </table>
        </div>

       



    </body>
</html>
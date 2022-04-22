<%-- 
    Document   : mantenimientoalumnos
    Created on : 22 abr. 2022, 09:51:03
    Author     : ksand
--%>

<%@page import="java.util.Iterator"%>
<%@page import="Logica.alumno"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Mantenimiento de alumnos</title>
        <link rel="stylesheet" type="text/css" href="estilo.css">
    </head>
    <body background="green">

        <div class="topnav">
            <a href="inicioadmin.html">Inicio</a>
            <a href="index.html">Cerrar Sesion</a>
        </div>

        <div class="center2">

            <div class="space"></div>

            <Label>Buscar Alumno</Label>

            <div class="space"></div>

            <div>
                <form action="mantenimientoalumnos">

                    <input type="text" name="buscar" placeholder="Nombre" required>
                    <button type="submit" name="accion" value="Buscar_n"> Buscar</button>

                </form>
            </div>
            <div>
                <form action="mantenimientoalumnos">

                    <input type="text" name="codigo" placeholder="Cédula" required>
                    <button type="submit" name="accion" value="Buscar_c"> Buscar</button>

                </form>
            </div>
            <div>
                <form action="mantenimientoalumnos">
                    
                    <input type="text" name="carrera" placeholder="Carrera" required>
                    <button type="submit" name="accion" value="Buscar_ca"> Buscar</button>

                    

                </form>
                <a href="mantenimientoalumnos?accion=ver">Listar todos los alumnos</a>	
            </div>





        </div>
        <div class="tablaProfesores">
            <table>
                <thead>
                    <tr>
                        <th>Cédula</th>
                        <th>Nombre</th>
                        <th>Telefono</th>
                        <th>Email</th>
                        <th>Fecha Nacimiento</th>
                        <th>Carrera</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
<%
                    List<alumno> alumnos = (List<alumno>) request.getAttribute("ListaAlumnos");
                    Iterator<alumno> iter;
                    iter = alumnos.iterator();
                    alumno m = null;
                    while (iter.hasNext()) {

                        m = iter.next();


                %>

                <tbody>
                    <tr>
                        <td><%=m.getCedula()%></td>
                        <td><%=m.getNombre()%></td>
                        <td><%=m.getTelefono()%></td>
                        <td><%=m.getEmail()%></td>
                        <td><%=m.getFecha_nacimiento()%></td>
                        <td><%=m.getCarrera_id()%></td>
                        <td> 
                            <a class=Opciones href=#>Ver historial</a>
                            
                        </td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
        </div>



    </body>
</html>

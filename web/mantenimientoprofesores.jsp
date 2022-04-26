<%-- 
    Document   : mantenimientoprofesores
    Created on : 22 abr. 2022, 06:52:59
    Author     : ksand
                 rolan
--%>

<%@page import="java.util.Iterator"%>
<%@page import="Logica.profesor"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Mantenimiento de profesores</title>
		<link rel="stylesheet" type="text/css" href="estilo.css">
	</head>
	<body background="green">

		<div class="topnav">
			<a href="inicioadmin.html">Inicio</a>
			<a href="index.html">Cerrar Sesion</a>
		</div>

		<div class="center9">

			<div class="space"></div>

			<Label>Buscar Profesor</Label>

			<div class="space"></div>

			<div class="campoboton">
				<form action="mantenimientoprofesor">
                    
                    <input type="text" name="buscar" placeholder="Nombre" required>
                   <button type="submit" name="accion" value="Buscar_n"> Buscar</button>
                    
                </form>
			</div>

			<div class="campoboton2">
                <form action="mantenimientoprofesor">
                    
                    <input type="text" name="codigo" placeholder="Cédula" required>
                   <button type="submit" name="accion" value="Buscar_c"> Buscar</button>
                    
                </form>
                <div class="NuevaBusqueda">
                    <a href="mantenimientoprofesor?accion=ver">Listar todos los profesores</a>	
                </div>
			
			</div>
			
			

			

		</div>
		<div class="tablaProfesores2">
			<table>
                            <thead>
				<tr>
				<th>Cédula</th>
				<th>Nombre</th>
                                <th>Telefono</th>
                                <th>Email</th>
                                <th>Fecha Nacimiento</th>
                                <th>Acciones</th>
				</tr>
                            </thead>
                            <%
                    List<profesor> profesores = (List<profesor>) request.getAttribute("ListaProfesores");
                    Iterator<profesor> iter;
                    iter = profesores.iterator();
                    profesor m = null;
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

                        <td> 
                            <a class=Opciones href=#>NA</a>
                            
                        </td>
                    </tr>
                    <%}%>
                </tbody>
			</table>
	    </div>
		

		
	</body>
</html>
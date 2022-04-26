<%-- 
    Document   : matriculagrupos
    Created on : 24 abr. 2022, 16:38:09
    Author     : ksand
                 Rolando
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
		<title>Estudiantes por grupo</title>
		<link rel="stylesheet" type="text/css" href="estilo.css">
	</head>
	<body background="green">

		<div class="topnav">
			<a href="inicioprofesores.jsp">Inicio</a>
			<a href="index.jsp">Cerrar Sesion</a>
		</div>

		<div class="tablaCursos7">
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
                           <a href="historial_alumno?accion=notas&cedula=<%=m.getAlumno_id()%>&curso=<%=m.getCurso_id()%>&grupo=<%=m.getGrupo_num()%>&estado=<%=m.getEstado()%>">Cambiar nota</a>
                        </td>

                    </tr>
                    <%}%>
                </tbody>
			</table>
		

		
	</body>
</html>

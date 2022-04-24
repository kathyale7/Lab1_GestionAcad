<%-- 
    Document   : mantenimientociclos
    Created on : 23 abr. 2022, 18:14:15
    Author     : ksand
--%>

<%@page import="java.util.Iterator"%>
<%@page import="Logica.ciclo"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Mantenimiento de ciclos</title>
		<link rel="stylesheet" type="text/css" href="estilo.css">
	</head>
	<body background="green">

		<div class="topnav">
			<a href="inicioadmin.html">Inicio</a>
			<a href="index.html">Cerrar Sesion</a>
		</div>

		<div class="center2">

			<div class="space"></div>

			<Label>Buscar Ciclo</Label>

			<div class="space"></div>

			<form action="mantenimientociclo">

                    <input type="text" name="buscar" placeholder="Año" required>
                    <button type="submit" name="accion" value="Buscar_a"> Buscar</button>

                </form>

			<div>
				
				<a href="mantenimientociclo?accion=ver">Listar todos los ciclos</a>	
			</div>

			

		</div>
		<div class="tablaCursos2">
			<table>
                            <thead>
				<tr>
				<th>Codigo</th>
				<th>Año</th>
                                <th>Numero</th>
                                <th>Fecha Inicio</th>
                                <th>Fecha Fin</th>
                                <th>Activo</th>
                                <th>Acciones</th>
				</tr>
                                 </thead>
                <%
                    List<ciclo> ciclos = (List<ciclo>) request.getAttribute("ListaCiclos");
                    Iterator<ciclo> iter;
                    iter = ciclos.iterator();
                    ciclo c = null;
                    String d = "";
                    int current_default = 0;
                    while (iter.hasNext()) {

                        c = iter.next();
                        if(c.getES_DEFAULT()==1){
                        current_default = c.getCodigo();
                       d = "Sí";
                    } else {
                    d = "No";
                    }


                %>

                <tbody>
                    <tr>
                        <td><%=c.getCodigo()%></td>
                        <td><%=c.getANHO()%></td>
                        <td><%=c.getNUMERO()%></td>
                        <td><%=c.getFECHA_INICIO()%></td>
                        <td><%=c.getFECHA_FINAL()%></td>
                        <td><%=d%></td>
                        <td> 
                            <a class=Opciones href="mantenimientociclo?accion=actualizar_activo&codigo=<%=c.getCodigo()%>&anho=<%=c.getANHO()%>&numero=<%=c.getNUMERO()%>&inicio=<%=c.getFECHA_INICIO()%>&fin=<%=c.getFECHA_FINAL()%>">Cambiar a activo</a>
                            
                        </td>


                    </tr>
                    <%}%>
                </tbody>
			</table>
	    </div>
		

		
	</body>
</html>

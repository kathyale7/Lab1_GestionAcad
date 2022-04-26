<%-- 
    Document   : ofertaacademica
    Created on : 23 abr. 2022, 21:20:54
    Author     : ksand
                 rolan
--%>

<%@page import="Logica.ciclo"%>
<%@page import="java.util.Iterator"%>
<%@page import="Logica.carrera"%>
<%@page import="java.util.List"%>
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

        <div class="buscarCarrera2">

            <div class="space"></div>

            <Label>Seleccionar carrera y ciclo</Label>

            <div class="space"></div>

            <div>
                <form action="ofertaacademica">
                <%
                    List<carrera> carreras = (List<carrera>) request.getAttribute("ListaCarreras");
                    Iterator<carrera> iter;
                    iter = carreras.iterator();
                    carrera c = null;
                    


                %>

                
                    
                <label for="carreras">Carrera:</label>
                <select name="carreras" id="carreras">
                    <%while (iter.hasNext()) {

                        c = iter.next();
                    String nombre = c.getNombre(); %>
                    <option value="<%=c.getCodigo()%>"><%=nombre%></option>
                    
                    <%}%>
                </select>
                 <%
                    List<ciclo> ciclos = (List<ciclo>) request.getAttribute("ListaCiclos");
                    Iterator<ciclo> iter2;
                    iter2 = ciclos.iterator();
                    ciclo ci = null;
                    


                %>
                <label for="ciclos">Ciclo:</label>
                <select name="ciclos" id="ciclos">
                    <%while (iter2.hasNext()) {

                        ci = iter2.next();
                    String cicloc = ci.getANHO() + ", " + ci.getNUMERO(); %>
                    <option value="<%=ci.getCodigo()%>"><%=cicloc%></option>
                    
                    <%}%>
                </select>
                <button type="submit" name="accion" value="Buscar_ca"> Buscar</button>
                </form>
            </div>



            <div class="NuevaBusqueda">

                <a href="ofertaacademica?accion=ver">Nueva busqueda</a>
            </div>



        </div>

       



    </body>
</html>

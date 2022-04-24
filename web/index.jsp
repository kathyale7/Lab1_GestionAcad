<%-- 
    Document   : index
    Created on : 23 abr. 2022, 23:20:28
    Author     : ksand
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="estilo.css">
    </head>
    <body background="green">

        <div class="topnav">

            <Label>Bienvenido</Label>
        </div>

        <div class="center">

            <div class="space"></div>

            <Label>Iniciar Sesión</Label>
            <div class="space"></div>
            <form action="Ingresar" method="POST">

                <div class="container">

                    <input type="text" name="user" placeholder="Usuario" required>

                    <input type="password" placeholder="Contraseña" id="contrasena" name="contrasena" required>
                    <button class="boton" type="submit">Ingresar</button>

            </form>

        </div>



    </body>
</html>

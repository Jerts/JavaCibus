<%-- 
    Document   : Respuesta1
    Created on : 21/05/2018, 04:54:36 PM
    Author     : Luis
--%>

<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Set"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
         <!--Import Google Icon Font-->
         <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
         <!--Import materialize.css-->    
         <link rel="stylesheet" href="materialize/css/materialize.min.css" type="text/css"/>
         <link rel="stylesheet" href="materialize/css/materialize.css" type="text/css">




      <!--Let browser know website is optimized for mobile-->
      <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    </head>
    <body>
        <div>
            <%
                HttpSession s1= request.getSession();
                String salu2 = (String) s1.getAttribute("saludo");
                String sal = request.getParameter("nombre");
               out.println(sal);%>
        </div>
        <script type="text/javascript" src="materialize/js/materialize.min.js"></script>
    </body>
</html>

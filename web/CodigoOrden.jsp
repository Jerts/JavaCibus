<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%-- 
    Document   : mainMenu
    Created on : 28/05/2018, 04:00:04 PM
    Author     : Luis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="coneccion.Conect , java.sql.*"%>
<!DOCTYPE html>
<%    
    
    HttpSession s1 = request.getSession();
    String usuario = (String) s1.getAttribute("usuario");
    String email = (String) s1.getAttribute("email");
    String img = (String) s1.getAttribute("img");
    s1.setAttribute("carritoMapa", null);
    
%>
<html>
    <head>
        <title>Cibus</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
         <!--Import Google Icon Font-->
         <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
         <!--Import materialize.css-->
         <link rel="stylesheet" href="materialize/css/materialize.min.css" type="text/css"/>
         <link rel="stylesheet" href="materialize/css/materialize.css" type="text/css">
         <!--Awsome font ico-->
         <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
         <!--Estilos propios-->
         <link rel="stylesheet" href="styles.css" type="text/css">
         <link rel="stylesheet" href="materialize/css/styleTienda.css" media="screen" title="no title" charset="utf-8">
         
      <!--Let browser know website is optimized for mobile-->
      <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    </head>
    
    <body>
        <nav class="orange darken-2">
            <div class="row">
                <div class="col s2 m2 l2">
                    <div class="">

                         <a href="#" data-target="slide-out" class="sidenav-trigger"><i class="material-icons">menu</i></a>

                    </div>
                </div>
                <div class="col s7 m7 l7">
                    <div class="">

                        <a href="index.html" class="brand-logo center">Cibus</a>

                    </div>
                </div>
                <div class="col s2 m2 l2">
                    <div class="">
                         <a href="carritoComp.jsp" ><i class="material-icons">shopping_cart</i></a>

                    </div>
                </div>
                <div class="col s1 m1 l1">
                    <div class="">
                         <span id="contadorCarrito" class="badge orange"></span>
                    </div>
                </div>
                
            </div>
        </nav>
        
        <ul id="slide-out" class="sidenav">
            <li>
                <div class="user-view">
                    <div class="background">
                        <img src="img/backgroundSideNav.jpg" class="responsive-img">
                    </div>
                    <!--<a href="#user"><img class="circle" src="img/usuarios/jerts25.jpg"></a>-->
                    <a href="#user"><img class="circle" src="<%=img%>"></a>
                    <a href="#name"><span class="white-text name"><%=usuario%></span></a>
                    <a href="#email"><span class="white-text email"><%=email%></span></a>
                    
                </div>
            </li>
            <li><a class="subheader">Cuenta</a></li>
            <li><a href="#!">Configuración</a></li>
            <li><div class="divider"></div></li>
            <li><a class="waves-effect" href="Out"><i class="material-icons">highlight_off</i>Cerrar sesión</a></li>
            <li><a class="waves-effect" href="#!"><i class="material-icons">live_help</i>Ayuda</a></li>
            </ul>
                    
            <div class="row">
                <div class="col s12">
                    <h4>Gracias por tu compra </h4><i class="material-icons">check_circle</i>
                    <br>Usa este código para recoger tu pedido<br><br>
                </div>
                <div class="col s12 center-align">
                    <% 
                        out.println("<img class=\"responsive-img\" src=\"http://api.qrserver.com/v1/create-qr-code/?data="+s1.getAttribute("id_compra")+"!&size=100x100\">");
                    %> 
                    <br><br>
                </div>
                <div class="col s12 center-align">
                    <a class="waves-effect waves-light btn-large orange darken-2" href="mainMenu.jsp">Continuar comprando :)</a>
                </div>
            </div>
                    
                    
        <!--  Scripts (Siempre primero Jquery)-->
        <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script src="materialize/js/materialize.js"></script>
        <script src="JScript/divFun.js"></script>
        <script> $(document).ready(function(){
            $('.sidenav').sidenav();
          });
        </script>
        <script>
            $(document).ready(function(){
              $('.collapsible').collapsible();
            });
        </script>
     
    </body>
</html>

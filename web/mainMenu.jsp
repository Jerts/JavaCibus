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
    
    
    
    Conect c1 = new Conect("cibusv2","root","");
    Connection con = c1.getConneccion();
    String query = "SELECT * FROM `producto` WHERE `ID_TIPO`=\"0001\"";
    Statement stat = con.createStatement();
    ResultSet set = stat.executeQuery(query);
    
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
                <div class="col s1 m1 l1">
                    <div class="">

                         <a href="#" data-target="slide-out" class="sidenav-trigger"><i class="material-icons">menu</i></a>

                    </div>
                </div>
                <div class="col s10 m10 l10">
                    <div class="nav-wrapper left-align">

                        <a href="index.html" class="brand-logo center">Cibus</a>

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
        
        <ul class="collapsible">
            <li>
              <div class="collapsible-header"><i class="material-icons">add</i>Preparados</div>
              <div class="collapsible-body">
                  <div class="shopping-cart">
                    <!-- Product #1 -->
                    <% 
                        while(set.next()){
                            out.println("<div class=\"item\">"
                                        + "<div class=\"image\">"
                                            + "<img src=\""+set.getString("RUTA_IMG")+"\" alt=\"\" />"
                                        + "</div>"
                                        + "<div class=\"description\">"
                                            + "<span>"+set.getString("NOMBRE")+"</span>"
                                        + "</div>"
                                        + "<div class=\"quantity\">"
                                            + "<button class=\"plus-btn\" type=\"button\" name=\"button\">"
                                                + "<img src=\"img/plus.svg\" alt=\"\" />"
                                            + "</button>"
                                            + "<input type=\"text\" name=\"name\" value=\"1\">"
                                            + "<button class=\"minus-btn\" type=\"button\" name=\"button\">"
                                                + "<img src=\"img/minus.svg\" alt=\"\" />"
                                            + "</button>"
                                        + "</div>"
                                        + "<div class=\"total-price\">$"+set.getString("PRECIO")+"</div>"
                                       + "</div>");
                        }
                    %>
                    </div>
              </div>
            </li>
            <li>
              <div class="collapsible-header"><i class="material-icons">add</i>Bebidas</div>
              <div class="collapsible-body"><span>Lorem ipsum dolor sit amet.</span></div>
            </li>
            <li>
              <div class="collapsible-header"><i class="material-icons">add</i>Dulces</div>
              <div class="collapsible-body"><span>Lorem ipsum dolor sit amet.</span></div>
            </li>
        </ul>
        
        
        
        
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
        <script type="text/javascript">
      $('.minus-btn').on('click', function(e) {
    		e.preventDefault();
    		var $this = $(this);
    		var $input = $this.closest('div').find('input');
    		var value = parseInt($input.val());

    		if (value > 1) {
    			value = value - 1;
    		} else {
    			value = 0;
    		}

        $input.val(value);

    	});

    	$('.plus-btn').on('click', function(e) {
    		e.preventDefault();
    		var $this = $(this);
    		var $input = $this.closest('div').find('input');
    		var value = parseInt($input.val());

    		if (value < 100) {
      		value = value + 1;
    		} else {
    			value =100;
    		}

    		$input.val(value);
    	});

      $('.like-btn').on('click', function() {
        $(this).toggleClass('is-active');
      });
    </script>
          
    </body>
</html>

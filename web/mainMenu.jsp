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
    String creditos = (String) s1.getAttribute("creditos");
    
    
    Conect c1 = new Conect("cibusv2","root","");
    Connection con = c1.getConneccion();
    String queryPreparados = "SELECT * FROM `producto` WHERE `ID_TIPO`=\"0001\"";
    String queryBebidas = "SELECT * FROM `producto` WHERE `ID_TIPO`=\"0002\"";
    String queryFritura = "SELECT * FROM `producto` WHERE `ID_TIPO`=\"0003\"";
    String queryDulces = "SELECT * FROM `producto` WHERE `ID_TIPO`=\"0004\"";
    String queryGalletas_Pan = "SELECT * FROM `producto` WHERE `ID_TIPO`=\"0005\"";
    
    
    Statement statPreparados = con.createStatement();
    ResultSet setPreparados = statPreparados.executeQuery(queryPreparados);
    
    Statement statBebidas = con.createStatement();
    ResultSet setBebidas = statBebidas.executeQuery(queryBebidas);
    
    Statement statDulces = con.createStatement();
    ResultSet setDulces = statDulces.executeQuery(queryDulces);
    
    Statement statFritura = con.createStatement();
    ResultSet setFritura= statFritura.executeQuery(queryFritura);
    
    Statement statGalletas_pan = con.createStatement();
    ResultSet setGalletas = statGalletas_pan.executeQuery(queryGalletas_Pan);
    
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
            <li><a class="subheader">Créditos : $<%=creditos%></a></li>
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
                        while(setPreparados.next()){
                            out.println("<div class=\"item\">"
                                        + "<div class=\"image\">"
                                            + "<img src=\""+setPreparados.getString("RUTA_IMG")+"\" alt=\"\" />"
                                        + "</div>"
                                        + "<div class=\"description\">"
                                            + "<span>"+setPreparados.getString("NOMBRE")+"</span>"
                                        + "</div>"
                                        + "<div class=\"quantity\">"
                                            + "<button class=\"plus-btn\" type=\"button\" name=\"button\">"
                                                + "<img src=\"img/plus.svg\" alt=\"\" />"
                                            + "</button>"
                                            + "<input type=\"text\" id=\"p_"+setPreparados.getString("ID_PRODUCTO")+"\" name=\"name\" value=\"1\">"
                                            + "<button class=\"minus-btn\" type=\"button\" name=\"button\">"
                                                + "<img src=\"img/minus.svg\" alt=\"\" />"
                                            + "</button>"
                                        + "</div>"
                                        + "<div class=\"total-price\">$"+setPreparados.getString("PRECIO")+""
                                            + "<a class=\"waves-effect waves-teal btn-flat\" onclick=\"agregarCarrito('"+setPreparados.getString("ID_PRODUCTO")+"')\"><i class=\"material-icons\">add_shopping_cart</i></a>"
                                        + "</div>"
                                       + "</div>");
                        }
                    %>
                    </div>
              </div>
            </li>
            <li>
              <div class="collapsible-header"><i class="material-icons">add</i>Bebidas</div>
              <div class="collapsible-body">
                  <div class="shopping-cart">
                    <!-- Product #1 -->
                    <% 
                        while(setBebidas.next()){
                            out.println("<div class=\"item\">"
                                        + "<div class=\"image\">"
                                            + "<img src=\""+setBebidas.getString("RUTA_IMG")+"\" alt=\"\" />"
                                        + "</div>"
                                        + "<div class=\"description\">"
                                            + "<span>"+setBebidas.getString("NOMBRE")+"</span>"
                                        + "</div>"
                                        + "<div class=\"quantity\">"
                                            + "<button class=\"plus-btn\" type=\"button\" name=\"button\">"
                                                + "<img src=\"img/plus.svg\" alt=\"\" />"
                                            + "</button>"
                                            + "<input type=\"text\" id=\"p_"+setBebidas.getString("ID_PRODUCTO")+"\" name=\"name\" value=\"1\">"
                                            + "<button class=\"minus-btn\" type=\"button\" name=\"button\">"
                                                + "<img src=\"img/minus.svg\" alt=\"\" />"
                                            + "</button>"
                                        + "</div>"
                                        + "<div class=\"total-price\">$"+setBebidas.getString("PRECIO")+""
                                            + "<a class=\"waves-effect waves-teal btn-flat\" onclick=\"agregarCarrito('"+setBebidas.getString("ID_PRODUCTO")+"')\"><i class=\"material-icons\">add_shopping_cart</i></a>"
                                        + "</div>"
                                       + "</div>");
                        }
                    %>
                    </div>
              </div>
            </li>
            <li>
              <div class="collapsible-header"><i class="material-icons">add</i>Dulces</div>
              <div class="collapsible-body">
                  <div class="shopping-cart">
                    <!-- Product #1 -->
                    <% 
                        while(setDulces.next()){
                            out.println("<div class=\"item\">"
                                        + "<div class=\"image\">"
                                            + "<img src=\""+setDulces.getString("RUTA_IMG")+"\" alt=\"\" />"
                                        + "</div>"
                                        + "<div class=\"description\">"
                                            + "<span>"+setDulces.getString("NOMBRE")+"</span>"
                                        + "</div>"
                                        + "<div class=\"quantity\">"
                                            + "<button class=\"plus-btn\" type=\"button\" name=\"button\">"
                                                + "<img src=\"img/plus.svg\" alt=\"\" />"
                                            + "</button>"
                                            + "<input type=\"text\" id=\"p_"+setDulces.getString("ID_PRODUCTO")+"\" name=\"name\" value=\"1\">"
                                            + "<button class=\"minus-btn\" type=\"button\" name=\"button\">"
                                                + "<img src=\"img/minus.svg\" alt=\"\" />"
                                            + "</button>"
                                        + "</div>"
                                        + "<div class=\"total-price\">$"+setDulces.getString("PRECIO")+""
                                            + "<a class=\"waves-effect waves-teal btn-flat\" onclick=\"agregarCarrito('"+setDulces.getString("ID_PRODUCTO")+"')\"><i class=\"material-icons\">add_shopping_cart</i></a>"
                                        + "</div>"
                                       + "</div>");
                        }
                    %>
                    </div>
              </div>
            </li>
            <li>
              <div class="collapsible-header"><i class="material-icons">add</i>Frituras</div>
              <div class="collapsible-body">
                  <div class="shopping-cart">
                    <!-- Product #1 -->
                    <% 
                        while(setFritura.next()){
                            out.println("<div class=\"item\">"
                                        + "<div class=\"image\">"
                                            + "<img src=\""+setFritura.getString("RUTA_IMG")+"\" alt=\"\" />"
                                        + "</div>"
                                        + "<div class=\"description\">"
                                            + "<span>"+setFritura.getString("NOMBRE")+"</span>"
                                        + "</div>"
                                        + "<div class=\"quantity\">"
                                            + "<button class=\"plus-btn\" type=\"button\" name=\"button\">"
                                                + "<img src=\"img/plus.svg\" alt=\"\" />"
                                            + "</button>"
                                            + "<input type=\"text\" id=\"p_"+setFritura.getString("ID_PRODUCTO")+"\" name=\"name\" value=\"1\">"
                                            + "<button class=\"minus-btn\" type=\"button\" name=\"button\">"
                                                + "<img src=\"img/minus.svg\" alt=\"\" />"
                                            + "</button>"
                                        + "</div>"
                                        + "<div class=\"total-price\">$"+setFritura.getString("PRECIO")+""
                                            + "<a class=\"waves-effect waves-teal btn-flat\" onclick=\"agregarCarrito('"+setFritura.getString("ID_PRODUCTO")+"')\"><i class=\"material-icons\">add_shopping_cart</i></a>"
                                        + "</div>"
                                       + "</div>");
                        }
                    %>
                    </div>
              </div>
            </li>
            <li>
              <div class="collapsible-header"><i class="material-icons">add</i>Galletas y Pan Dulce</div>
              <div class="collapsible-body">
                  <div class="shopping-cart">
                    <!-- Product #1 -->
                    <% 
                        while(setGalletas.next()){
                            out.println("<div class=\"item\">"
                                        + "<div class=\"image\">"
                                            + "<img src=\""+setGalletas.getString("RUTA_IMG")+"\" alt=\"\" />"
                                        + "</div>"
                                        + "<div class=\"description\">"
                                            + "<span>"+setGalletas.getString("NOMBRE")+"</span>"
                                        + "</div>"
                                        + "<div class=\"quantity\">"
                                            + "<button class=\"plus-btn\" type=\"button\" name=\"button\">"
                                                + "<img src=\"img/plus.svg\" alt=\"\" />"
                                            + "</button>"
                                            + "<input type=\"text\" id=\"p_"+setGalletas.getString("ID_PRODUCTO")+"\" name=\"name\" value=\"1\">"
                                            + "<button class=\"minus-btn\" type=\"button\" name=\"button\">"
                                                + "<img src=\"img/minus.svg\" alt=\"\" />"
                                            + "</button>"
                                        + "</div>"
                                        + "<div class=\"total-price\">$"+setGalletas.getString("PRECIO")+""
                                            + "<a class=\"waves-effect waves-teal btn-flat\" onclick=\"agregarCarrito('"+setGalletas.getString("ID_PRODUCTO")+"')\"><i class=\"material-icons\">add_shopping_cart</i></a>"
                                        + "</div>"
                                       + "</div>");
                        }
                    %>
                    </div>
              </div>
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

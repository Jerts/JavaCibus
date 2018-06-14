/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import coneccion.Conect;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis
 */
public class PonerOrden extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            //Conexión a la base de datos
            Conect c = new Conect("cibusv2","root","");
            Connection con = c.getConneccion();
            Statement stat = con.createStatement();
            //Sesion
            HttpSession s1 = request.getSession();
            //Cosas necesarias para los insert
            String id_compra = genId(con,stat);
            String id_cliente = (String) s1.getAttribute("usuario");
            String hora = LocalTime.now().toString();
            String fecha = LocalDate.now().toString();
            //HashMap
            HashMap carrito = (HashMap)s1.getAttribute("carritoMapa");
            Map<String,Integer> map = (Map)carrito;
            
            String queryProductos = "";
            String nombre;
            String queryUpdate = "";
            String queryUpdateCliente = "";
            //Inserts
            for(Map.Entry<String,Integer> prod : map.entrySet()){
                //Query
                queryProductos = "SELECT * FROM `producto` WHERE `ID_PRODUCTO`=\""+prod.getKey()+"\"";
                String query = "INSERT INTO `compra` (ID_COMPRA,ID_PRODUCTO,ID_CLIENTE,CANTIDAD,HORA,FECHA) "
                               +"VALUES('"+id_compra+"','"+prod.getKey()+"','"+id_cliente+"','"+prod.getValue()+"','"+hora+"','"+fecha+"')";
                queryUpdate = "UPDATE `producto` SET `CANTIDAD`=`CANTIDAD`-"+prod.getValue()+" WHERE `ID_PRODUCTO`=\""+prod.getKey()+"\"";
                queryUpdateCliente = "UPDATE `cliente` SET `CREDITOS`=`CREDITOS`-"+(Float)s1.getAttribute("totalCompra")+" WHERE `ID_CLIENTE`=\""+s1.getAttribute("usuario")+"\"";
                System.out.println(query);
                System.out.println(queryUpdate);
                System.out.println(queryUpdateCliente);
                
                stat.executeUpdate(query);
                stat.executeUpdate(queryUpdate);
                stat.executeUpdate(queryUpdateCliente);
            }
            s1.setAttribute("id_compra", id_compra);
            s1.setAttribute("totalCompra", 0    );
            request.getRequestDispatcher("CodigoOrden.jsp").forward(request, response);
            
        } catch (SQLException ex) {
            Logger.getLogger(PonerOrden.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private String genId(Connection con,Statement stat){
        String ticket="";
        int numero;
        try {
            String queryTiketActual = "SELECT DISTINCT `ID_COMPRA` FROM `compra` ORDER BY `ID_COMPRA` DESC";
            ResultSet set = stat.executeQuery(queryTiketActual);
            set.next();
            ticket = set.getString("ID_COMPRA");
            System.out.println(ticket);
            ticket = ticket.substring(1,7);
            System.out.println(ticket);
            numero = Integer.parseInt(ticket)+1;
            System.out.println(numero);
            ticket = "A"+String.format("%06d", numero);
            System.out.println(ticket);
        } catch (SQLException ex) {
            Logger.getLogger(PonerOrden.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ticket;
    }
}

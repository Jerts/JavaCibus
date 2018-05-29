/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import coneccion.Conect;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;
/**
 *
 * @author Luis
 */
public class Registro extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        //Conexión a la base de datos
        Conect c = new Conect("cibusv2","root","");
        Connection con = c.getConneccion();
        //Obtencion de los parametros del form de Registro
        String usuario=request.getParameter("usuario");
        String pass=request.getParameter("password");
        String nombre=request.getParameter("nombre");
        String p_apellido=request.getParameter("p_apellido");
        String s_apellido=request.getParameter("s_apellido");
        String sexo=request.getParameter("sexo");
        
        pass = hashContra(pass);//Contraseña en Sha1
        Timestamp tmp = new Timestamp(System.currentTimeMillis());
        //Set de cookies para la toast en index.html
        Cookie galleta1 = new Cookie("registro","true");
        galleta1.setMaxAge(60);
        response.addCookie(galleta1);
        //Query para la inserción de un registro a la BD
        String query = "INSERT INTO `cliente` (ID_CLIENTE,NOMBRE,P_APELLIDO,SEXO,CONTRASENA,TIMESTAMP_REGISTRO) "
                                  +"VALUES('"+usuario+"','"+nombre+"','"+p_apellido+"','"+sexo+"','"+pass+"','"+tmp+"')";
        System.out.println(query);
        try {
            Statement stm = con.createStatement();
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        c.endConeccion();
        response.sendRedirect("index.html");
    }
    private String hashContra(String contra){
        
        contra = DigestUtils.sha1Hex(contra);
        return contra;
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

}

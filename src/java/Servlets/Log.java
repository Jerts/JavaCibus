/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import coneccion.Conect;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import org.apache.commons.codec.digest.DigestUtils;
/**
 *
 * @author Luis
 */
public class Log extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //Recibir parámetros del formilario
        String usuario = request.getParameter("user");
        String pass = request.getParameter("pass");
        
        if(usuario!=null && pass!=null){

            //conexión
            Conect c = new Conect("cibusv2","root","");
            Connection con = c.getConneccion();

            //SHA1 a la contra
            pass = this.hashContra(pass);

            String query = "SELECT `ID_CLIENTE` FROM `cliente` WHERE `ID_CLIENTE`=\""+usuario+"\" and `CONTRASENA`=\""+pass+"\"";
            System.out.println(query);
            try {

                //Creación del statement y ejecución del query
                Statement stm = con.createStatement();
                ResultSet res = stm.executeQuery(query);

                //Evaluar la existencia de una respuesta
                if(res.next()){

                    //Crear sesión con el nombre de usuario
                    HttpSession s1 = request.getSession(true);
                    s1.setAttribute("usuario", usuario);
                    //Redirigir al menu principal
                    request.getRequestDispatcher("mainMenu.jsp").forward(request, response);

                }else{

                    //Cookie de registro exitoso
                    Cookie galleta2 = new Cookie("registro","");
                    galleta2.setMaxAge(5);
                    response.addCookie(galleta2);
                    //Cookie de inicio de sesión fallido
                    Cookie galleta1 = new Cookie("login","false");
                    galleta1.setMaxAge(5);
                    response.addCookie(galleta1);

                }
                //Terminar conexión
                c.endConeccion();
                //Redirigir al inicio
                response.sendRedirect("index.html");

            } catch (SQLException ex) {
                Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
            }      
        }//Fin del if
        else{
            Cookie galleta2 = new Cookie("registro","");
            galleta2.setMaxAge(30);
            response.addCookie(galleta2);
            //Cookie de inicio de sesión fallido
            Cookie galleta1 = new Cookie("login","false");
            galleta1.setMaxAge(30);
            response.addCookie(galleta1);
            //Redirigir al inicio
            response.sendRedirect("index.html");
            
        }
             
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
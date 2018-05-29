/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
import coneccion.Conect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis
 */
public class GetUsuario extends HttpServlet {

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
            
            Conect c1 = new Conect("cibusv2","root","");
            Connection con = c1.getConneccion();
            String id = request.getParameter("nombre");
            
            String query = "SELECT `ID_CLIENTE` FROM `cliente` WHERE `ID_CLIENTE`= \""+id+"\" ";
            System.out.println(query);
            Statement stat = con.createStatement();
            ResultSet res = stat.executeQuery(query);
            if(res.next()){
                //response.setContentType("application/json");
                System.out.println(res.getString("ID_CLIENTE"));
                PrintWriter out = response.getWriter();
                //out.print("<span class=\"red-text text-darken-2\">Nombre de usuario ocupado</span>");
                out.println("{\"id_cliente\": \"ocupado\"}");
                out.flush();
            }else{
                //response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                //out.print("<span class=\"green-text \">Nombre de usuario disponible</span>");
                out.println("{\"id_cliente\":\"libre\"}");
                out.flush();
            }
          
            
            c1.endConeccion();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(GetUsuario.class.getName()).log(Level.SEVERE, null, ex);
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

}

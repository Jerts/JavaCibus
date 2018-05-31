/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Luis
 */
public class Out extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
            HttpSession s1 = request.getSession();
            
            if(s1.getAttribute("usuario")!=null){
                String nombre = (String) s1.getAttribute("usuario");
                System.out.println(nombre+": sesion terminada");
                s1.invalidate();
                //Cookie de registro exitoso
                Cookie galleta2 = new Cookie("registro","");
                galleta2.setMaxAge(5);
                response.addCookie(galleta2);
                //Cookie de inicio de sesión fallido
                Cookie galleta1 = new Cookie("login","");
                galleta1.setMaxAge(5);
                response.addCookie(galleta1);
                response.sendRedirect("index.html");
            }
            else{
                System.out.println("Sesión no encontrada");
                Cookie galleta2 = new Cookie("registro","");
                galleta2.setMaxAge(5);
                response.addCookie(galleta2);
                //Cookie de inicio de sesión fallido
                Cookie galleta1 = new Cookie("login","");
                galleta1.setMaxAge(5);
                response.addCookie(galleta1);
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

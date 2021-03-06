/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Luis
 */
public class Carrito extends HttpServlet {

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
        //Obtener los parametros de elemento y su cantidad
        String elemento = request.getParameter("elemento");
        String auxCantidad = request.getParameter("cantidad");
        int cantidad = Integer.parseInt(auxCantidad);
        
        HttpSession s1 = request.getSession();
        PrintWriter out = response.getWriter();
        //Si no existe el atributo de carrito lo crea
        if(s1.getAttribute("usuario")!= "" && s1.getAttribute("carritoMapa")==null){
            //Se crea la lista que guardará los identificadores de los elementos agregados
            HashMap<String,Integer> mapaCarrito = new HashMap<>(); 
            //Se añade el primer elemento
            mapaCarrito.put(elemento, cantidad);
            //Se crea el atributo carrito de tipo List<String>
            s1.setAttribute("carritoMapa", mapaCarrito);
            //Imprime los contenidos
            System.out.println(s1.getAttribute("carritoMapa"));
            //Envía la resupuesta en formato JSON
            out.println("{\"addSucces\": \"true\"}");
            out.flush();
        }else{
            //Se crea la lista Auxiliar que guardará el objeto regresado de la sesion
            HashMap<String,Integer> mapaCarrito = (HashMap<String,Integer>) s1.getAttribute("carritoMapa");
            //Se añade el elemento
            mapaCarrito.put(elemento, cantidad);
            //Se vuelve a setear el atributo en la sesión
            s1.setAttribute("carritoMapa", mapaCarrito);
            
            System.out.println(s1.getAttribute("carrito"));
            System.out.println(s1.getAttribute("carritoMapa"));
            
            out.println("{\"addSucces\": \"true\"}");
            out.flush();
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import AccesoDatos.GlobalException;
import AccesoDatos.NoDataException;
import AccesoDatos.ServiceCiclo;
import Logica.ciclo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ksand
 */
@WebServlet(name = "mantenimientociclo", urlPatterns = {"/mantenimientociclo", "/ciclos"})
public class controller_ciclo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     ServiceCiclo cDao = new ServiceCiclo();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet controller_ciclo</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet controller_ciclo at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
       response.setContentType("text/html");

     
        String action = request.getParameter("accion");

        List<ciclo> ciclo_oferta;
        if (action.equalsIgnoreCase("ver")) {
            try {

                ciclo_oferta = (List<ciclo>) cDao.listarCiclo();

                request.setAttribute("ListaCiclos", ciclo_oferta);
            } catch (SQLException | NoDataException | GlobalException ex) {
                Logger.getLogger(controller_cursos.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.getRequestDispatcher("mantenimientociclos.jsp").forward(request, response);

        } else if (action.equalsIgnoreCase("Buscar_a")) {

    

            String nombre = request.getParameter("buscar");


            try {
                ciclo_oferta = (List<ciclo>) cDao.buscarCiclo(nombre);

                request.setAttribute("ListaCiclos", ciclo_oferta);
            } catch (GlobalException | NoDataException ex) {
                Logger.getLogger(controller_cursos.class.getName()).log(Level.SEVERE, null, ex);
            }
           
           request.getRequestDispatcher("mantenimientociclos.jsp").forward(request, response);

        } else if (action.equalsIgnoreCase("actualizar_activo")) {

    

            int codigo = Integer.parseInt(request.getParameter("codigo"));
            String anho = request.getParameter("anho");
            int numero  = Integer.parseInt(request.getParameter("numero"));
            String inicio  = request.getParameter("inicio");
            String fin = request.getParameter("fin");
            int esdefault = 1;
            
            ciclo nuevo = new ciclo(codigo, anho, numero, inicio, fin, esdefault);
            
            try {
                
                cDao.modificarCiclo(nuevo);

            } catch (GlobalException | NoDataException  ex) {
                Logger.getLogger(controller_cursos.class.getName()).log(Level.SEVERE, null, ex);
            }
         
//            try {
//                cDao.modificarCiclo(actual);
////                nuevo = cDao.buscarCiclo_Codigo(nuevo_ciclo);
////                nuevo.setES_DEFAULT(1);
////                cDao.modificarCiclo(nuevo);
//
//            } catch (GlobalException | NoDataException  ex) {
//                Logger.getLogger(controller_cursos.class.getName()).log(Level.SEVERE, null, ex);
//            }


            
           
           request.getRequestDispatcher("mantenimientociclo?accion=ver").forward(request, response);

        }
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import AccesoDatos.GlobalException;
import AccesoDatos.NoDataException;
import AccesoDatos.ServiceCarrera;
import AccesoDatos.ServiceCiclo;
import AccesoDatos.ServiceCurso;
import Logica.carrera;
import Logica.ciclo;
import Logica.curso;
import Modelo.model_carrera;
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
@WebServlet(name = "ofertaacademica", urlPatterns = {"/ofertaacademica", "/oferta"})
public class controller_oferta extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    model_carrera model;
    ServiceCarrera cDao = new ServiceCarrera();
    ServiceCiclo ciDao = new ServiceCiclo();
    ServiceCurso cuDao = new ServiceCurso();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet controller_oferta</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet controller_oferta at " + request.getContextPath() + "</h1>");
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

        List<carrera> carreras_oferta;
        List<ciclo> ciclos_oferta;
        List<curso> cursos_oferta;
        if (action.equalsIgnoreCase("ver")) {
            try {

                carreras_oferta = (List<carrera>) cDao.listarCarrera();
                ciclos_oferta =(List<ciclo>) ciDao.listarCiclo();

                request.setAttribute("ListaCarreras", carreras_oferta);
                request.setAttribute("ListaCiclos", ciclos_oferta);
            } catch (SQLException | NoDataException | GlobalException ex) {
                Logger.getLogger(controller_cursos.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.getRequestDispatcher("ofertaacademica.jsp").forward(request, response);

        } else if (action.equalsIgnoreCase("Buscar_ca")) {

            int carrera = Integer.parseInt(request.getParameter("carreras"));
            int ciclo = Integer.parseInt(request.getParameter("ciclos"));

            try {
                cursos_oferta = (List<curso>) cuDao.buscarCURSO_carrera_ciclo(carrera, ciclo);

                request.setAttribute("ListaCursos", cursos_oferta);
            } catch (GlobalException | NoDataException ex) {
                Logger.getLogger(controller_cursos.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.getRequestDispatcher("cursosfiltrados.jsp").forward(request, response);

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

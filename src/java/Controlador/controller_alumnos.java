/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import AccesoDatos.GlobalException;
import AccesoDatos.NoDataException;
import AccesoDatos.ServiceAlumno;
import Logica.alumno;

import Modelo.model_alumno;
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
@WebServlet(name = "mantenimientoalumnos", urlPatterns = {"/mantenimientoalumnos", "/alumnos"})
public class controller_alumnos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    model_alumno model;
    ServiceAlumno aDao = new ServiceAlumno();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet controller_alumnos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet controller_alumnos at " + request.getContextPath() + "</h1>");
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

        List<alumno> alumnos_matriculados;
        if (action.equalsIgnoreCase("ver")) {
            try {

                alumnos_matriculados = (List<alumno>) aDao.listarAlumno();

                request.setAttribute("ListaAlumnos", alumnos_matriculados);
            } catch (SQLException | NoDataException | GlobalException ex) {
                Logger.getLogger(controller_cursos.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.getRequestDispatcher("mantenimientoalumnos.jsp").forward(request, response);

        } else if (action.equalsIgnoreCase("Buscar_n")) {

    

            String nombre = request.getParameter("buscar");


            try {
                alumnos_matriculados = (List<alumno>) aDao.buscarAlumno__nombre(nombre);

                request.setAttribute("ListaAlumnos", alumnos_matriculados);
            } catch (GlobalException | NoDataException ex) {
                Logger.getLogger(controller_cursos.class.getName()).log(Level.SEVERE, null, ex);
            }
           
           request.getRequestDispatcher("mantenimientoalumnos.jsp").forward(request, response);

        }else if (action.equalsIgnoreCase("Buscar_c")) {

    
            int codigo = Integer.parseInt(request.getParameter("codigo"));

            try {
                alumnos_matriculados = (List<alumno>) aDao.buscarAlumno_id(codigo);

                request.setAttribute("ListaAlumnos", alumnos_matriculados);
            } catch (GlobalException | NoDataException ex) {
                
                Logger.getLogger(controller_cursos.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           
           request.getRequestDispatcher("mantenimientoalumnos.jsp").forward(request, response);

        } else if (action.equalsIgnoreCase("Buscar_ca")) {

    
            int carrera = Integer.parseInt(request.getParameter("carrera"));

            try {
                alumnos_matriculados = (List<alumno>) aDao.buscarAlumno__carrera(carrera);

                request.setAttribute("ListaAlumnos", alumnos_matriculados);
            } catch (GlobalException | NoDataException ex) {
                
                Logger.getLogger(controller_cursos.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           
           request.getRequestDispatcher("mantenimientoalumnos.jsp").forward(request, response);

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

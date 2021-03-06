/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import AccesoDatos.GlobalException;
import AccesoDatos.NoDataException;
import AccesoDatos.ServiceCurso;
import AccesoDatos.ServiceMatricula;
import Logica.curso;
import Logica.matricula;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
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
@WebServlet(name = "historial_alumno", urlPatterns = {"/historial_alumno", "/historial"})
public class controller_matricula extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
   
    ServiceMatricula mDao = new ServiceMatricula();
    matricula m = new matricula();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet controller_matricula</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet controller_matricula at " + request.getContextPath() + "</h1>");
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
        PrintWriter pw = response.getWriter();

        response.setContentType("text/html");

        String acceso = "";
        String action = request.getParameter("accion");

        List<matricula> matricula_historial;
        if (action.equalsIgnoreCase("Buscar_alumno")) {
            int nombre = Integer.parseInt(request.getParameter("id_alumno"));


            try {
                matricula_historial = (List<matricula>) mDao.buscarMatricula_id(nombre);

                request.setAttribute("HistorialAlumno", matricula_historial);
            } catch (GlobalException | NoDataException ex) {
                Logger.getLogger(controller_cursos.class.getName()).log(Level.SEVERE, null, ex);
            }
           
           request.getRequestDispatcher("historial.jsp").forward(request, response);


        } else if (action.equalsIgnoreCase("mi_historial")) {
            int nombre = Integer.parseInt(request.getParameter("id_alumno"));


            try {
                matricula_historial = (List<matricula>) mDao.buscarMatricula_id(nombre);

                request.setAttribute("HistorialAlumno", matricula_historial);
            } catch (GlobalException | NoDataException ex) {
                Logger.getLogger(controller_cursos.class.getName()).log(Level.SEVERE, null, ex);
            }
           
           
           request.getRequestDispatcher("historial_propio.jsp").forward(request, response);

        } else if (action.equalsIgnoreCase("nueva_matricula")) {
             request.setAttribute("id_edit", request.getParameter("alumno"));

            

            request.getRequestDispatcher("nuevamatricula.jsp").forward(request, response);

        } else if (action.equalsIgnoreCase("Agregar")) {
//            request.setAttribute("id_edit", request.getParameter("id_curso"));

            int alumno_id = Integer.parseInt(request.getParameter("alumno_id"));
            int curso_id = Integer.parseInt(request.getParameter("curso_id"));
            int num_grupo = Integer.parseInt(request.getParameter("num_grupo"));
            int estado = Integer.parseInt(request.getParameter("estado"));
            int nota = Integer.parseInt(request.getParameter("nota"));
           

            m.setAlumno_id(alumno_id);
            m.setCurso_id(curso_id);
            m.setGrupo_num(num_grupo);
            m.setEstado(estado);
            m.setNota(nota);
            try {

                mDao.insertarMatricula(m);

            } catch (GlobalException | NoDataException e) {
            }
           
            request.getRequestDispatcher("historial_alumno?accion=Buscar_alumno&id_alumno="+alumno_id).forward(request, response);
        } else if (action.equalsIgnoreCase("eliminar")) {
            int alumno_id = Integer.parseInt(request.getParameter("alumno_id"));
            int curso_id = Integer.parseInt(request.getParameter("curso_id"));
            int num_grupo = Integer.parseInt(request.getParameter("num_grupo"));

            try {
                mDao.eliminarMatricula(alumno_id, num_grupo, curso_id);
//                cursos_oferta = (List<curso>) cDao.listarCurso();
//                request.setAttribute("ListaCursos", cursos_oferta);
            } catch (GlobalException | NoDataException  ex) {
                Logger.getLogger(controller_cursos.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.getRequestDispatcher("historial_alumno?accion=Buscar_alumno&id_alumno="+alumno_id).forward(request, response);
        
        } else if (action.equalsIgnoreCase("matricula_grupo")) {
            
            int num_grupo = Integer.parseInt(request.getParameter("num_grupo"));

            try {
                 matricula_historial = (List<matricula>) mDao.buscarMatricula_grupo(num_grupo);

                request.setAttribute("HistorialAlumno", matricula_historial);
            } catch (GlobalException | NoDataException ex) {
                Logger.getLogger(controller_cursos.class.getName()).log(Level.SEVERE, null, ex);
            }
           
           
           request.getRequestDispatcher("matriculagrupos.jsp").forward(request, response);
        } else if (action.equalsIgnoreCase("notas")) {
             request.setAttribute("cedula", request.getParameter("cedula"));
             request.setAttribute("curso", request.getParameter("curso"));
             request.setAttribute("grupo", request.getParameter("grupo"));
             request.setAttribute("estado", request.getParameter("estado"));



            request.getRequestDispatcher("registrarnotas.jsp").forward(request, response);

        }  else if (action.equalsIgnoreCase("NotaNueva")) {
//            request.setAttribute("id_edit", request.getParameter("id_curso"));

            int cedula = Integer.parseInt(request.getParameter("cedula"));
           int curso = Integer.parseInt(request.getParameter("curso"));
           int grupo = Integer.parseInt(request.getParameter("grupo"));
           int estado = Integer.parseInt(request.getParameter("estado"));
           int nota = Integer.parseInt(request.getParameter("nota"));

            m.setAlumno_id(cedula);
            m.setCurso_id(curso);
            m.setGrupo_num(grupo);
            if (nota > 67){
                estado = 101;
            } else {
                estado = 103;
            }
            if (nota == 0){
                estado = 102;
            }
            m.setEstado(estado);
            m.setNota(nota);
            try {

                mDao.modificarMatricula(m);

            } catch (GlobalException | NoDataException e) {
            }
           
            request.getRequestDispatcher("historial_alumno?accion=matricula_grupo&num_grupo="+grupo).forward(request, response);
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

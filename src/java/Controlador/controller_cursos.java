/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import AccesoDatos.GlobalException;
import AccesoDatos.NoDataException;
import AccesoDatos.ServiceCurso;
import Logica.curso;
import Modelo.model_curso;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(name = "mantenimientocurso", urlPatterns = {"/mantenimientocurso", "/cursos"})
public class controller_cursos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    model_curso model;
    ServiceCurso cDao = new ServiceCurso();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter pw = response.getWriter();

        response.setContentType("text/html");

        String acceso = "";
        String action = request.getParameter("accion");
//        pw.print("<link rel=\"stylesheet\" type=\"text/css\" href=\"estilo.css\">");
//        pw.print(" <iframe src=\"mantenimientocurso.html\" onload=\"this.before((this.contentDocument.body||this.contentDocument).children[0]);this.remove()\"></iframe>");
//        pw.print("<table><tr><th>Codigo </th><th>Curso </th><th>Creditos </th><th>Horas semanales </th><th>Carrera </th><th>Ciclo</th><th>Acciones </th></table>");
        List<curso> cursos_oferta;
        if (action.equalsIgnoreCase("ver")) {
            try {

//        List<curso> cursos_oferta = new ArrayList<>();
//        curso test = new curso(300, "FUNDAMENTOS DE INFORMATICA", 4, 4, 1000, 200);
//        cursos_oferta.add(test);
                cursos_oferta = (List<curso>) cDao.listarCurso();
                Iterator<curso> iter;
                iter = cursos_oferta.iterator();
                curso c = null;
//            while (iter.hasNext()) {
//                c = iter.next();
//                pw.print("<tr><td>");
//                pw.print(c.getCodigo());
//                pw.print("</td>");
//                pw.print("<td>");
//                pw.print(c.getNombre());
//                pw.print("</td>");
//                pw.print("<td>");
//                pw.print(c.getCreditos());
//                pw.print("</td>");
//                pw.print("<td>");
//                pw.print(c.getHoras_semanales());
//                pw.print("</td>");
//                pw.print("<td>");
//                pw.print(c.getCarrera_id());
//                pw.print("</td>");
//                pw.print("<td>");
//                pw.print(c.getCiclo_id());
//                pw.print("</td>");
//                pw.print("<td>");
//                pw.print("<a class=Opciones href=#>Agregar grupo </a>");
//                pw.print("<a class=Opciones href=#>Ver Grupos</a>");
//                pw.print("</td>");

                request.setAttribute("ListaCursos", cursos_oferta);
//
                request.getRequestDispatcher("listarcursos.jsp").forward(request, response);
//            }

            } catch (SQLException | NoDataException | GlobalException ex) {
                Logger.getLogger(controller_cursos.class.getName()).log(Level.SEVERE, null, ex);
            }
            pw.print("</table>");
//        request.getRequestDispatcher("mantenimientocurso.html").forward(request, response);
            pw.close();
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

        List<curso> cursos_oferta;
        if (action.equalsIgnoreCase("ver")) {
            try {

                cursos_oferta = (List<curso>) cDao.listarCurso();
                Iterator<curso> iter;
                iter = cursos_oferta.iterator();
                curso c = null;
                request.setAttribute("ListaCursos", cursos_oferta);
            } catch (SQLException | NoDataException | GlobalException ex) {
                Logger.getLogger(controller_cursos.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.getRequestDispatcher("listarcursos.jsp").forward(request, response);

        } else if (action.equalsIgnoreCase("Buscar_n")) {

    

            String nombre = request.getParameter("buscar");
//            int codigo = Integer.parseInt(request.getParameter("codigo"));
//            String carrera = request.getParameter("carrera");

            try {
                cursos_oferta = (List<curso>) cDao.buscarCurso_nombre(nombre);
//                cursos_oferta = (List<curso>) cDao.buscarCurso_id(codigo);
//                cursos_oferta = (List<curso>) cDao.buscarCurso_carrera(carrera);
                request.setAttribute("ListaCursos", cursos_oferta);
            } catch (GlobalException | NoDataException ex) {
                Logger.getLogger(controller_cursos.class.getName()).log(Level.SEVERE, null, ex);
            }
           
           request.getRequestDispatcher("listarcursos.jsp").forward(request, response);

        }else if (action.equalsIgnoreCase("Buscar_c")) {

    
            int codigo = Integer.parseInt(request.getParameter("codigo"));

            try {
                cursos_oferta = (List<curso>) cDao.buscarCurso_id(codigo);

                request.setAttribute("ListaCursos", cursos_oferta);
            } catch (GlobalException | NoDataException ex) {
                
                Logger.getLogger(controller_cursos.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           
           request.getRequestDispatcher("listarcursos.jsp").forward(request, response);

        }
        else if (action.equalsIgnoreCase("Buscar_ca")) {

    

         
        
            int carrera = Integer.parseInt(request.getParameter("carrera"));

            try {
                cursos_oferta = (List<curso>) cDao.buscarCurso_carrera(carrera);

                request.setAttribute("ListaCursos", cursos_oferta);
            } catch (GlobalException | NoDataException ex) {
                Logger.getLogger(controller_cursos.class.getName()).log(Level.SEVERE, null, ex);
            }
           
           request.getRequestDispatcher("listarcursos.jsp").forward(request, response);

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

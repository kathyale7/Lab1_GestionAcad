/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import AccesoDatos.GlobalException;
import AccesoDatos.NoDataException;
import AccesoDatos.ServiceGrupo;
import Logica.grupo;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "mantenimientogrupo", urlPatterns = {"/mantenimientogrupo", "/grupos"})
public class controller_grupos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    ServiceGrupo gDao = new ServiceGrupo();
    grupo g = new grupo();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet controller_grupos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet controller_grupos at " + request.getContextPath() + "</h1>");
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

        List<grupo> grupos_oferta;
        if (action.equalsIgnoreCase("Buscar_grupos")) {
            int id_curso = Integer.parseInt(request.getParameter("id_curso"));


            try {
                grupos_oferta = (List<grupo>) gDao.buscarGrupo_Ciclo(200, id_curso);

                request.setAttribute("ListaGrupos", grupos_oferta);
            } catch (GlobalException | NoDataException ex) {
                Logger.getLogger(controller_cursos.class.getName()).log(Level.SEVERE, null, ex);
            }
           
           request.getRequestDispatcher("mantenimientogrupos.jsp").forward(request, response);

        }  else if (action.equalsIgnoreCase("formagregar")) {
             request.setAttribute("id_edit", request.getParameter("id_curso"));

            
            

            request.getRequestDispatcher("agregargrupos.jsp").forward(request, response);

        } else if (action.equalsIgnoreCase("Agregar")) {
            int curso = Integer.parseInt(request.getParameter("curso"));

            int codigo = Integer.parseInt(request.getParameter("codigo"));
            int ciclo = 200;
            int num_grupo = Integer.parseInt(request.getParameter("num_grupo"));
            String horario = request.getParameter("horario");
            int profesor = Integer.parseInt(request.getParameter("profesor"));

            g.setCODIGO(codigo);
            g.setCICLO_ID(ciclo);
            g.setNUM_GRUPO(num_grupo);
            g.setCURSO_ID(curso);
            g.setHORARIO(horario);
            g.setPROFESOR_ID(profesor);
            try {

                gDao.insertarGrupo(g);

            } catch (GlobalException | NoDataException e) {
            }
           
            request.getRequestDispatcher("mantenimientogrupo?accion=Buscar_grupos&id_curso="+curso).forward(request, response);
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

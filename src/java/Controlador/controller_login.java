/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import AccesoDatos.GlobalException;
import AccesoDatos.NoDataException;
import AccesoDatos.ServiceAlumno;
import AccesoDatos.ServiceLogin;
import AccesoDatos.ServiceProfesor;
import Logica.alumno;
import Logica.profesor;
import Logica.usuario;
import Modelo.model_usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ksand
 */
@WebServlet(name = "Ingresar", urlPatterns = {"/Ingresar"})
public class controller_login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    model_usuario model;
    ServiceLogin uDao = new ServiceLogin();
    ServiceProfesor pDao = new ServiceProfesor();
    ServiceAlumno aDao = new ServiceAlumno();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String respuesta = "index.jsp";
        String cedula_request = request.getParameter("user");
        String contrasena_request = request.getParameter("contrasena");

        try {
            usuario u = uDao.login(new usuario(cedula_request, 1, contrasena_request));

        } catch (Exception ex) {

        }

        request.getRequestDispatcher(respuesta).forward(request, response);

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
        System.out.println("Entrando al servlet de ingresar");
        response.setContentType("text/html;charset=UTF-8");
        String respuesta = "";
        String cedula_request = request.getParameter("user");
        String contrasena_request = request.getParameter("contrasena");
        usuario u = null;
        try {
            u = uDao.login(new usuario(cedula_request, 1, contrasena_request));
        } catch (Exception ex) {

            if (u == null) {
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }
        HttpSession session = request.getSession(true);
        session.setAttribute("Usuario", u);
        if (u.getROL_ID() == 1 || u.getROL_ID() == 2) {
            //                administrador admin = Service.instance().buscar_administrador(u.getId());
//                System.out.println(admin.getNombre());
//                session.setAttribute("administrador", admin);
            respuesta = "inicioadmin.html";
        }
        if (u.getROL_ID() == 3) {
            try {
                profesor p = pDao.buscarProfesor_id_(Integer.parseInt(u.getUSUARIO_ID()));
                System.out.println(p.getNombre());
                session.setAttribute("profesor", p);
                respuesta = "inicioprofesores.jsp";
            } catch (Exception ex) {
                Logger.getLogger(controller_login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (u.getROL_ID() == 4) {
           
            try {
                alumno e = aDao.buscarAlumno_id_(Integer.parseInt(u.getUSUARIO_ID()));
//                session.setAttribute("Estudiante", e);
 request.setAttribute("id_edit", request.getParameter(u.getUSUARIO_ID()));
                respuesta = "inicioestudiantes.jsp";
            } catch (Exception ex) {
                Logger.getLogger(controller_login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        request.getRequestDispatcher(respuesta).forward(request, response);
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

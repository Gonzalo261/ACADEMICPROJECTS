/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controlador.GestorLogin;
import Modelo.DTOUsuario;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gonza
 */
@WebServlet(name = "Terminos", urlPatterns = {"/Terminos"})
public class Terminos extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String modo = request.getParameter("modo");
        GestorLogin gestorL = new GestorLogin();
        
        if(request.getSession().getAttribute("usr")!= null)
        {
            DTOUsuario dtoUsuario = (DTOUsuario) request.getSession().getAttribute("usr");
            if(gestorL.obtenerDescripcionUsuarioPorId(dtoUsuario.getId()).equals("ADMINISTRADOR"))
            {
                if(modo.equals("editar")){
                    request.setAttribute("modeloTermino",gestorL.obtenerUltimoTerminoYCondicion());
                    RequestDispatcher rd = request.getRequestDispatcher("/editarTerminosYCondiciones.jsp");
                    rd.forward(request, response);
                }
            }
        }
        if(modo.equals("preguntas")){
                RequestDispatcher rd = request.getRequestDispatcher("/preguntasFrecuentes.jsp");
                rd.forward(request, response);
            }
        
        if(modo.equals("leerTermino")){
            request.setAttribute("terminos",gestorL.obtenerUltimoTerminoYCondicion());
            RequestDispatcher rd = request.getRequestDispatcher("/terminosYCondiciones.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        DTOUsuario dtoUsuario = (DTOUsuario) request.getSession().getAttribute("usr");
        GestorLogin gestorL = new GestorLogin();
        if(gestorL.obtenerDescripcionUsuarioPorId(dtoUsuario.getId()).equals("ADMINISTRADOR"))
        {
                String terminos = (String)request.getParameter("terminos");
                gestorL.agregarTerminoYCondicion(terminos);
                request.setAttribute("usuario","ADMINISTRADOR");
                RequestDispatcher rd = request.getRequestDispatcher("/principal.jsp");
                rd.forward(request, response);
        }
        else
        {
            RequestDispatcher rd = request.getRequestDispatcher("/principal.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

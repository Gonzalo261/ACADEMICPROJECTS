/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controlador.GestorCategoria;
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
@WebServlet(name = "Principal", urlPatterns = {"/Principal"})
public class Principal extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        GestorLogin gestorL = new GestorLogin();   
        GestorCategoria gestorCat = new GestorCategoria();
        request.getSession().setAttribute("categoriaNav",gestorCat.obtenerCategoria());
        request.getSession().setAttribute("cliente",null);
        request.getSession().setAttribute("carrito",null);
        
        if(request.getSession().getAttribute("usr")== null)
        {
            RequestDispatcher rd = request.getRequestDispatcher("/principal.jsp");
            rd.forward(request, response);
        }
        else{
            DTOUsuario dtoUsuario = (DTOUsuario) request.getSession().getAttribute("usr");
            if(gestorL.obtenerDescripcionUsuarioPorId(dtoUsuario.getId())!=null)
            {
                request.setAttribute("usuario",gestorL.obtenerDescripcionUsuarioPorId(dtoUsuario.getId()));
                RequestDispatcher rd = request.getRequestDispatcher("/principal.jsp");
                rd.forward(request, response);
            }
            else
            {
                RequestDispatcher rd = request.getRequestDispatcher("/principal.jsp");
                rd.forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

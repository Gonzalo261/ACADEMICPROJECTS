/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controlador.GestorLogin;
import Modelo.DTOUsuario;
import Modelo.Dni;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "BuscarDni", urlPatterns = {"/BuscarDni"})
public class BuscarDni extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        GestorLogin gestorL = new GestorLogin ();
        ArrayList<Dni> dniLista = gestorL.obtenerDnis();
        
        DTOUsuario dtoUsuario = (DTOUsuario) request.getSession().getAttribute("usr");
        request.setAttribute("usuario",gestorL.obtenerDescripcionUsuarioPorId(dtoUsuario.getId()));

        if(!request.getParameter("dni").isEmpty())
        {
            boolean band = false;
            int dni = Integer.parseInt(request.getParameter("dni"));
            for (int i = 0; i < dniLista.size(); i++) 
            {
                if(dniLista.get(i).getDni()== dni && gestorL.obtenerUsuarioClientePorDni(dni)!=null)
                {
                    band = true;
                }
            }
            if(band)request.getSession().setAttribute("cliente", gestorL.obtenerUsuarioClientePorDni(dni));
            else
            {
                request.getSession().setAttribute("cliente",null);
                request.setAttribute("mensaje", "Cliente no encontrado");
            }
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

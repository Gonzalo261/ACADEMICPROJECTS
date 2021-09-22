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
@WebServlet(name = "Usuarios", urlPatterns = {"/Usuarios"})
public class Usuarios extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        GestorLogin gestorL = new GestorLogin ();
        String modo = (String) request.getParameter("modo");
        DTOUsuario dtoUsuario = (DTOUsuario) request.getSession().getAttribute("usr");
        String tipoUsuario = gestorL.obtenerDescripcionUsuarioPorId(dtoUsuario.getId());

        int idUsuario = dtoUsuario.getId();
        if(request.getSession().getAttribute("cliente")!=null)
            idUsuario = ((DTOUsuario)request.getSession().getAttribute("cliente")).getId();
        
        if(!tipoUsuario.equals("CLIENTE"))
        {
            if(modo == null || modo.isEmpty())
            {
                request.setAttribute("mensaje", null);
                request.setAttribute("seccion1", gestorL.obtenerUsuarioPorId(idUsuario));
                request.setAttribute("tipoUsuario", gestorL.obtenerDescripcionUsuarioPorId(idUsuario));
                RequestDispatcher rd = request.getRequestDispatcher("/listadoUsuarios.jsp");
                rd.forward(request, response);
            }
            else if(modo.equals("verCuentas"))
            {
                String descripcion= (String)request.getParameter("tipo");
                request.setAttribute("mensaje", null);
                request.setAttribute("seccion2", gestorL.obtenerUsuariosPorTipo(descripcion));
                request.setAttribute("tipoUsuario", gestorL.obtenerTipoUsuarios());
                RequestDispatcher rd = request.getRequestDispatcher("/listadoUsuarios.jsp");
                rd.forward(request, response);
            }
            else
            {
                        RequestDispatcher rd = request.getRequestDispatcher("/pricipal.jsp");
                        rd.forward(request, response);
            }       
        }
        else
        {
                    RequestDispatcher rd = request.getRequestDispatcher("/pricipal.jsp");
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

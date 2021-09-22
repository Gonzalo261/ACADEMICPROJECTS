/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controlador.GestorContrato;
import Controlador.GestorLogin;
import Modelo.DTOContrato;
import Modelo.DTOUsuario;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
@WebServlet(name = "Logins", urlPatterns = {"/Logins"})
public class Logins extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher rd = request.getRequestDispatcher("/logins.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String fechaHoy = dtf4.format(LocalDateTime.now());
        
        GestorContrato gestorCON = new GestorContrato ();
        
        //actualización de base de datos
        ArrayList <DTOContrato> lista = gestorCON.obtenerContratos();
        
        for (DTOContrato c : lista) {
            
            if(c.getDTOPresupuesto().getFechaEntrega().equals(fechaHoy))
                gestorCON.condicionContrato(c.getIdContrato(), true, 2);
            if(c.getDTOPresupuesto().getFechaRetiro().equals(fechaHoy))
                gestorCON.condicionContrato(c.getIdContrato(), true, 4);
        }
        

        String email = (String)(request.getParameter("txtEmail"));
        String contraseña = (String)(request.getParameter("txtPass"));
        
        boolean band = false;
        DTOUsuario dtoUsuario = null;
        
        GestorLogin gestorL = new GestorLogin ();
        ArrayList<DTOUsuario> log = gestorL.obtenerUsuarios();
        
        for (int i = 0; i < log.size(); i++) {
            if( contraseña.equals(log.get(i).getPass()))
            {
                dtoUsuario = log.get(i);
                band = true;
                break;
            }
        }
        
        if(band)
        {
            request.getSession().setAttribute("usr", dtoUsuario);
            request.getSession().setAttribute("usuario",gestorL.obtenerDescripcionUsuarioPorId(dtoUsuario.getId()));
            RequestDispatcher rd = request.getRequestDispatcher("/principal.jsp");
            rd.forward(request, response);
        }
        else
        {
                request.setAttribute("mensajeError", "Usuario o contraseña incorrectos");
                RequestDispatcher rd = request.getRequestDispatcher("/logins.jsp");
                rd.forward(request, response);
            } 
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

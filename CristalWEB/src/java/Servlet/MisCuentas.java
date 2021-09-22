/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controlador.GestorContrato;
import Controlador.GestorLogin;
import Controlador.GestorPersona;
import Modelo.DTOUsuario;
import Modelo.Persona;
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
@WebServlet(name = "MisCuentas", urlPatterns = {"/MisCuentas"})
public class MisCuentas extends HttpServlet {
    @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       if(request.getSession().getAttribute("usr")!=null)
       {
            String modo = request.getParameter("modo");
            GestorLogin gestorL = new GestorLogin();
            DTOUsuario dtoUsuario = (DTOUsuario) request.getSession().getAttribute("usr");
            String descripcionTipo = gestorL.obtenerDescripcionUsuarioPorId(dtoUsuario.getId());
            
            GestorContrato gestorCON = new GestorContrato();
            
            int idUsuario = dtoUsuario.getId();
            
            if(!descripcionTipo.equals("CLIENTE")){
              if(request.getParameter("idCliente")!=null) idUsuario = Integer.parseInt(request.getParameter("idCliente"));
            }
            
            request.setAttribute("cuenta",gestorL.obtenerUsuarioPorId(idUsuario));
            request.setAttribute("descripcionTipo",gestorL.obtenerDescripcionUsuarioPorId(idUsuario));
            
            if(modo == null || modo.isEmpty())
            {
                RequestDispatcher rd = request.getRequestDispatcher("/detalleCuenta.jsp");
                rd.forward(request, response);
            }
            else if(modo.equals("editarCuenta"))
            {
                request.setAttribute("accion", "Editar");
                request.setAttribute("modeloUsuario",dtoUsuario.getPersona());
                if(gestorL.obtenerDescripcionUsuarioPorId(dtoUsuario.getId()).equals("ADMINISTRADOR"))
                {
                    request.setAttribute("tiposUsuarios", gestorL.obtenerTipoUsuarios());
                    request.setAttribute("idTipo",dtoUsuario.getIdTipoUsuario());
                    RequestDispatcher rd = request.getRequestDispatcher("/editarUsuarioAdmin.jsp");
                    rd.forward(request, response);
                }
                else{
                    RequestDispatcher rd = request.getRequestDispatcher("/editarUsuario.jsp");
                    rd.forward(request, response);
                } 
            }
            else if(modo.equals("editarPassword"))
            {
                request.setAttribute("accion", "Editar Password");
                request.setAttribute("modeloUsuario",dtoUsuario);
                RequestDispatcher rd = request.getRequestDispatcher("/editarPassword.jsp");
                rd.forward(request, response);
            }
            else if(modo.equals("eliminarCuenta"))
            {
                gestorL.vigenciaCuenta(false, dtoUsuario.getId());
                request.getSession().setAttribute("usr", null);
                RequestDispatcher rd = request.getRequestDispatcher("/principal.jsp");
                rd.forward(request, response);
            }
            else if(modo.equals("informe"))
            {
                request.setAttribute("deuda",gestorCON.obtenerDeuda(idUsuario));
                request.setAttribute("historial",gestorCON.obtenerHistorialAlquileresCliente(idUsuario));
                RequestDispatcher rd = request.getRequestDispatcher("/informeCuenta.jsp");
                rd.forward(request, response);
            }
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
        
        GestorLogin gestorL = new GestorLogin();
        GestorPersona gestorP = new GestorPersona();
        
        if(request.getSession().getAttribute("usr")!=null)
        {
            DTOUsuario dtoUsuario = (DTOUsuario) request.getSession().getAttribute("usr");
            if(request.getParameter("nuevaPass")!=null)
            {
                String pass = (String)(request.getParameter("nuevaPass"));
                gestorL.actualizarPassword(pass, dtoUsuario.getId());
                
                request.getSession().setAttribute("cliente",null);
                request.getSession().setAttribute("carrito",null);
                request.getSession().setAttribute("usr",null);
                RequestDispatcher rd = request.getRequestDispatcher("/principal.jsp");
                rd.forward(request, response);
            }
            else
            {
                String nombre = (String)request.getParameter("nombre");
                String apellido = (String)(request.getParameter("apellido"));
                int celular = Integer.parseInt(request.getParameter("celular"));
                int dni = Integer.parseInt(request.getParameter("dni"));

                Persona persona = new Persona(dtoUsuario.getPersona().getId(),nombre,apellido,celular,dni);
                gestorP.actualizarPersona(persona);
                if(gestorL.obtenerDescripcionUsuarioPorId(dtoUsuario.getId()).equals("ADMINISTRADOR"))
                {
                    int tipoUsuario = Integer.parseInt(request.getParameter("tipoUsuario"));
                    gestorL.actualizarTipoUsuario(tipoUsuario, dtoUsuario.getId());
                }
                
                request.setAttribute("cuenta",gestorL.obtenerUsuarioPorId(dtoUsuario.getId()));
                request.setAttribute("descripcionTipo",gestorL.obtenerDescripcionUsuarioPorId(dtoUsuario.getId()));
                RequestDispatcher rd = request.getRequestDispatcher("/detalleCuenta.jsp");
                rd.forward(request, response);
            } 
        }
        else
        {
            request.getSession().setAttribute("cliente",null);
            request.getSession().setAttribute("carrito",null);
            request.getSession().setAttribute("usr",null);
            RequestDispatcher rd = request.getRequestDispatcher("/principal.jsp");
            rd.forward(request, response);
        }

    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

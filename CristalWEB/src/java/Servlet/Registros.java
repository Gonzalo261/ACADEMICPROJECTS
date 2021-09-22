
package Servlet;

import Controlador.GestorLogin;
import Controlador.GestorPersona;
import Modelo.DTOUsuario;
import Modelo.Persona;
import Modelo.Usuario;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Registros", urlPatterns = {"/Registros"})
public class Registros extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String modo = request.getParameter("modo");
        GestorLogin gestorL = new GestorLogin();
        if(modo == null || modo.isEmpty())
        {
            RequestDispatcher rd = request.getRequestDispatcher("/logins.jsp");
            rd.forward(request, response);
        }
        else if(modo.equals("alta"))
        {
            request.setAttribute("accion", "Alta");
            if(request.getSession().getAttribute("usr")!=null)
            {
                DTOUsuario dtoUsuario = (DTOUsuario) request.getSession().getAttribute("usr");
                String habilitado = gestorL.obtenerDescripcionUsuarioPorId(dtoUsuario.getId());
                if(habilitado.equals("ADMINISTRADOR"))
                {
                    request.setAttribute("tiposUsuarios", gestorL.obtenerTipoUsuarios());
                    RequestDispatcher rd = request.getRequestDispatcher("/nuevoUsuarioAdmin.jsp");
                    rd.forward(request, response);
                }
                else{
                    RequestDispatcher rd = request.getRequestDispatcher("/nuevoUsuario.jsp");
                    rd.forward(request, response);
                }
            }
            else{
                RequestDispatcher rd = request.getRequestDispatcher("/nuevoUsuario.jsp");
                rd.forward(request, response);
            }  
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
        
        GestorLogin gestorL = new GestorLogin();
        GestorPersona gestorP = new GestorPersona();
        

        String nombre = (String)request.getParameter("nombre");
        String apellido = (String)(request.getParameter("apellido"));
        int celular = Integer.parseInt(request.getParameter("celular"));
        int documento = Integer.parseInt(request.getParameter("documento"));

        String email = (String)(request.getParameter("email"));
        String pass = (String)(request.getParameter("pass"));

        Persona persona = new Persona(0,nombre,apellido,celular,documento);
        gestorP.agregarPersona(persona);
        
        int ultimaPersona = gestorP.obtenerIdUltimaPersonaCreada();

        if(request.getParameter("tipoUsuario") == null)
        {
            Usuario usuario = new Usuario (0,email,pass,ultimaPersona,4,true);
            gestorL.agregarUsuario(usuario);
        }
        else
        {
            int tipoUsuario = Integer.parseInt(request.getParameter("tipoUsuario"));
            Usuario usuario = new Usuario (0,email,pass,ultimaPersona,tipoUsuario,true);
            gestorL.agregarUsuario(usuario);
        }
        
        
        RequestDispatcher rd = request.getRequestDispatcher("/logins.jsp");
        rd.forward(request, response);

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

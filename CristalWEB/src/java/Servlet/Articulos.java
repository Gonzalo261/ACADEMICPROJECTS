/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controlador.GestorArticulo;
import Controlador.GestorCategoria;
import Controlador.GestorLogin;
import Modelo.Articulo;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Articulos", urlPatterns = {"/Articulos"})
public class Articulos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            String modo = request.getParameter("modo");
            GestorCategoria gestorCat = new GestorCategoria ();
            GestorArticulo gestorA = new GestorArticulo();
            if(request.getParameter("idCategoria")!=null)
            {
                int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
                request.setAttribute("categoria", gestorCat.obtenerDescripcionCategoria(idCategoria));
                request.setAttribute("art", gestorA.obtenerArticuloPorCategoria(idCategoria));
            }
            else request.setAttribute("art", gestorA.obtenerArticulos());

            if(modo == null || modo.isEmpty())
            {
                RequestDispatcher rd = request.getRequestDispatcher("/listadoArticulos.jsp");
                rd.forward(request, response);
            }
            else if(modo.equals("expandir"))
            {
                int id= Integer.parseInt(request.getParameter("idImagen"));
                request.setAttribute("imagen", gestorA.obtenerArticuloPorId(id).getImagen());
                RequestDispatcher rd = request.getRequestDispatcher("/expandirImagen.jsp");
                rd.forward(request, response);
            }
            if(request.getSession().getAttribute("usr")!= null)
            { 
                if(modo.equals("alta"))
                {
                    request.setAttribute("vistaON","true");
                    request.setAttribute("accion", "Alta");
                    RequestDispatcher rd = request.getRequestDispatcher("/nuevoArticulo.jsp");
                    rd.forward(request, response);
                }
                else if(modo.equals("editar"))
                {
                    int idArticulo = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("accion", "Edición");
                    request.setAttribute("modeloArticulo", gestorA.obtenerArticuloPorId(idArticulo));
                    request.setAttribute("categoria", gestorCat.obtenerCategoria());
                    RequestDispatcher rd = request.getRequestDispatcher("/editarArticulo.jsp");
                    rd.forward(request, response);
                }
                else if(modo.equals("eliminar"))
                {
                    int idArticulo = Integer.parseInt(request.getParameter("id"));
                    gestorA.eliminarArticulo(idArticulo);
                    RequestDispatcher rd = request.getRequestDispatcher("/listadoArticulos.jsp");
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
        
        GestorLogin gestorL = new GestorLogin();
        GestorArticulo gestor = new GestorArticulo();
        String nombre = (String)request.getParameter("nombre");
        String descripcion = (String)request.getParameter("descripcion");
        double precio = Double.parseDouble(request.getParameter("precio"));
        String imagen = "img/"+(String)request.getParameter("imagen");
        int stock = Integer.parseInt(request.getParameter("stock"));
        int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
        
        if(request.getParameter("idArticulo") == null)
        {
            Articulo art = new Articulo(0,nombre,descripcion,precio,imagen,stock,idCategoria);
            gestor.agregarArticulo(art);
        }
        else
        {
            int id = Integer.parseInt(request.getParameter("idArticulo"));
            Articulo articulo = new Articulo(id,nombre,descripcion,precio,imagen,stock,idCategoria);
            gestor.actualizarArticulo(articulo);
        } 
        
        request.setAttribute("art", gestor.obtenerArticulos());

        RequestDispatcher rd = request.getRequestDispatcher("/listadoArticulos.jsp");
        rd.forward(request, response);
  
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

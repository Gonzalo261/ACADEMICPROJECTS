/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controlador.GestorArticulo;
import Modelo.Articulo;
import Modelo.Carrito;
import Modelo.DTOUsuario;
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
@WebServlet(name = "Carritos", urlPatterns = {"/Carritos"})
public class Carritos extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        GestorArticulo gestorA = new GestorArticulo();
        if(request.getSession().getAttribute("usr")!= null)
        {
            int idCliente=0;
            if(request.getSession().getAttribute("cliente")!=null)
                idCliente=((DTOUsuario)request.getSession().getAttribute("cliente")).getId();
            
            String modo = request.getParameter("modo");
            request.setAttribute("error", null);
            
            if(modo == null || modo.isEmpty())
            {
                ArrayList<Carrito> listaCarrito= new ArrayList();
                request.getSession().setAttribute("carrito",listaCarrito);
                request.setAttribute("art", gestorA.obtenerArticulos());
                RequestDispatcher rd = request.getRequestDispatcher("/carritoPresupuesto.jsp");
                rd.forward(request, response);
            }
            else if(modo.equals("agregar"))
            {
                int cantidad = Integer.parseInt(request.getParameter("cantidad"));
                int idArticulo = Integer.parseInt(request.getParameter("idArticulo"));
                ArrayList<Carrito> listaCarrito=(ArrayList) request.getSession().getAttribute("carrito");
                boolean band = false;
                if(listaCarrito.size()>0)
                {
                    for(Carrito c: listaCarrito)
                    {
                        if(idArticulo == c.getArticulo().getId())
                        {
                            c.setCantidad(cantidad + c.getCantidad());
                            band = true;
                            break;
                        }
                    }
                }
                if(!band)
                {
                    listaCarrito.add(new Carrito (gestorA.obtenerArticuloPorId(idArticulo),cantidad));
                }
                request.getSession().setAttribute("carrito",listaCarrito);
                request.setAttribute("art", gestorA.obtenerArticulos());
                RequestDispatcher rd = request.getRequestDispatcher("/carritoPresupuesto.jsp");
                rd.forward(request, response);
            }
            else if(modo.equals("eliminar"))
            {
                request.setAttribute("vistaON","true");
                int idArticulo = Integer.parseInt(request.getParameter("idArticulo"));
                ArrayList<Carrito> listaCarrito=(ArrayList) request.getSession().getAttribute("carrito");
                for(Carrito c: listaCarrito)
                {
                    if(idArticulo == c.getArticulo().getId())
                    {
                        listaCarrito.remove(c);
                        break;
                    }
                }

                request.getSession().setAttribute("carrito",listaCarrito);
                request.setAttribute("art", gestorA.obtenerArticulos());
                RequestDispatcher rd = request.getRequestDispatcher("/carritoPresupuesto.jsp");
                rd.forward(request, response);
            }
            else if(modo.equals("filtrar"))
            {
                ArrayList<Articulo> lista = gestorA.obtenerArticulosPorFiltro((String)request.getParameter("descripcion"));
                if(!lista.isEmpty())
                {
                    request.setAttribute("error", null);
                    request.setAttribute("art", lista);
                }
                else 
                {
                    request.setAttribute("error", "No se encontro ningun producto con esa descripcion");
                    request.setAttribute("art", gestorA.obtenerArticulos());
                }
                request.setAttribute("idCliente", idCliente);
                RequestDispatcher rd = request.getRequestDispatcher("/carritoPresupuesto.jsp");
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
        
        
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

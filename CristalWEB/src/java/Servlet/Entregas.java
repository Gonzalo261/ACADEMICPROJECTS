/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controlador.GestorContrato;
import Controlador.GestorEntrega;
import Controlador.GestorLogin;
import Modelo.DTOUsuario;
import Modelo.Entrega;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Entregas", urlPatterns = {"/Entregas"})
public class Entregas extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if(request.getSession().getAttribute("usr")!= null)
        {
            GestorEntrega gestorE = new GestorEntrega();
            GestorLogin gestorL = new GestorLogin();
            GestorContrato gestorCON = new GestorContrato();
            String modo = request.getParameter("modo");
            DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            Calendar cal= Calendar.getInstance();
            String inicioAño= cal.get(Calendar.YEAR)+"-01-01";
            
            request.getSession().setAttribute("fechaSessionA", dtf4.format(LocalDateTime.now()));
            request.getSession().setAttribute("fechaSessionB", dtf4.format(LocalDateTime.now()));
            
            DTOUsuario dtoUsuario = (DTOUsuario) request.getSession().getAttribute("usr");
            
            String fechaA = (String)request.getParameter("fechaA");
            String fechaB = (String)request.getParameter("fechaB");
            
            if(fechaA!=null)request.getSession().setAttribute("fechaSessionA", fechaA);
            else fechaA=(String)request.getSession().getAttribute("fechaSessionA");
            
            if(fechaB!=null)request.getSession().setAttribute("fechaSessionB", fechaB);
            else fechaB=(String)request.getSession().getAttribute("fechaSessionB");

            int idContrato =0;
            if(request.getParameter("idContrato")!=null)
                idContrato = Integer.parseInt(request.getParameter("idContrato"));
            
            if(modo.equals("pendientesAnual"))
            {
                request.setAttribute("pedidosEntregar", gestorCON.obtenerPedidosParaEntregar(inicioAño,fechaB));
                RequestDispatcher rd = request.getRequestDispatcher("/listadoPedidosEntregar.jsp");
                rd.forward(request, response);
            }
            else if(modo.equals("pendientesAnualFiltro"))
            {
                request.setAttribute("pedidosEntregar", gestorCON.obtenerPedidosParaEntregar(fechaA,fechaB));
                RequestDispatcher rd = request.getRequestDispatcher("/listadoPedidosEntregar.jsp");
                rd.forward(request, response);
            }
            else if(modo.equals("alta"))
            {
                request.setAttribute("idContrato",idContrato);
                request.setAttribute("tipoUsuario",gestorL.obtenerDescripcionUsuarioPorId(dtoUsuario.getId()));
                RequestDispatcher rd = request.getRequestDispatcher("/nuevaEntrega.jsp");
                rd.forward(request, response);
            }
            else if(modo.equals("entregasRealizadasAnual"))
            {
                if(request.getSession().getAttribute("cliente")!=null)
                {
                    int idUsuarioCliente = ((DTOUsuario)request.getSession().getAttribute("cliente")).getId();
                    request.setAttribute("entregasRealizadas", gestorE.obtenerPedidosEntregadosPorCliente(idUsuarioCliente,inicioAño,fechaB));
                }
                else request.setAttribute("entregasRealizadas", gestorE.obtenerEntregas(inicioAño,fechaB));
                RequestDispatcher rd = request.getRequestDispatcher("/listadoEntregasRealizadas.jsp");
                rd.forward(request, response);
            }
            else if(modo.equals("entregasRealizadasFiltro"))
            {
                if(request.getSession().getAttribute("cliente")!=null)
                {
                    int idUsuarioCliente = ((DTOUsuario)request.getSession().getAttribute("cliente")).getId();
                    request.setAttribute("entregasRealizadas", gestorE.obtenerPedidosEntregadosPorCliente(idUsuarioCliente,fechaA,fechaB));
                }
                else request.setAttribute("entregasRealizadas", gestorE.obtenerEntregas(fechaA,fechaB));
                RequestDispatcher rd = request.getRequestDispatcher("/listadoEntregasRealizadas.jsp");
                rd.forward(request, response);
            }
            else
            {
                RequestDispatcher rd = request.getRequestDispatcher("/principal.jsp");
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
        
        GestorContrato gestorCON = new GestorContrato();
        GestorEntrega gestorE = new GestorEntrega();
        
        DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaYHoraEntrega = dtf4.format(LocalDateTime.now());
        
        DTOUsuario dtoUsuario = (DTOUsuario) request.getSession().getAttribute("usr");
        int idUsuarioEntrega = dtoUsuario.getId();
        
        int idContrato = Integer.parseInt(request.getParameter("idContrato"));
        String observaciones = (String)request.getParameter("observaciones");

        Entrega entrega = new Entrega (0,idContrato,fechaYHoraEntrega,idUsuarioEntrega,observaciones);
        gestorE.agregarEntrega(entrega);
        gestorCON.condicionContrato(idContrato,true,2);
        
        String fechaA=(String)request.getSession().getAttribute("fechaSessionA");
        String fechaB=(String)request.getSession().getAttribute("fechaSessionB");
        
        request.setAttribute("pedidosEntregar", gestorCON.obtenerPedidosParaEntregar(fechaA,fechaB));
        RequestDispatcher rd = request.getRequestDispatcher("/listadoPedidosEntregar.jsp");
        rd.forward(request, response);
  
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}



//
//GestorContrato gestorCON = new GestorContrato();
//            GestorEntrega gestorE = new GestorEntrega();
//            GestorLogin gestorL = new GestorLogin();
//            String modo = request.getParameter("modo");
//            DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("yyy-MM-dd");
//            
//            String fechaHoyA =dtf4.format(LocalDateTime.now());
//            String fechaHoyB =dtf4.format(LocalDateTime.now());
//            
//            DTOUsuario dtoUsuario = (DTOUsuario) request.getSession().getAttribute("usr");
//            String descripcionTipo = gestorL.obtenerDescripcionUsuarioPorId(dtoUsuario.getId());
//            int idUsuarioCliente = 0;
//            if(descripcionTipo.equals("ADMINISTRADOR")||descripcionTipo.equals("VENDEDOR")){
//                if(request.getSession().getAttribute("cliente")!=null)
//                {
//                    idUsuarioCliente = ((DTOUsuario)request.getSession().getAttribute("cliente")).getId();
//                    request.setAttribute("movimiento", gestorE.obtenerPedidosEntregadosPorCliente(idUsuarioCliente));
//                }
//                else request.setAttribute("movimiento", gestorE.obtenerEntregas());
//            }
//            else 
//            {
//                idUsuarioCliente = dtoUsuario.getId();
//                request.getSession().setAttribute("cliente",dtoUsuario);
//                request.setAttribute("movimiento", gestorE.obtenerPedidosEntregadosPorCliente(idUsuarioCliente));
//            }
//            
//            String fechaA = (String)request.getParameter("fechaA");
//            String fechaB = (String)request.getParameter("fechaB");
//            
//            if(fechaA!=null)request.getSession().setAttribute("fechaSessionA", fechaA);
//            else fechaA=(String)request.getSession().getAttribute("fechaSessionA");
//            
//            if(fechaB!=null)request.getSession().setAttribute("fechaSessionB", fechaB);
//            else fechaB=(String)request.getSession().getAttribute("fechaSessionB");
//            
//            
//            if(modo == null || modo.isEmpty())
//            {
//                request.setAttribute("pedidosEntregar", gestorCON.obtenerPedidosParaEntregar(fechaHoyA,fechaHoyB));
//                RequestDispatcher rd = request.getRequestDispatcher("/listadoPedidosEntregar.jsp");
//                rd.forward(request, response);
//            }
//            else if(modo.equals("entregasRealizadas"))
//            {
//                request.setAttribute("tipoMovimiento", "entregados");
//                RequestDispatcher rd = request.getRequestDispatcher("/listadoMovimientos.jsp");
//                rd.forward(request, response);
//            }
//            else if(modo.equals("alta"))
//            {
//                gestorCON.condicionContrato(Integer.parseInt(request.getParameter("idContrato")),true,2);
//                request.setAttribute("pedidosEntregar", gestorCON.obtenerPedidosParaEntregar(fechaA,fechaB));
//                RequestDispatcher rd = request.getRequestDispatcher("/listadoPedidosEntregar.jsp");
//                rd.forward(request, response);
//            }
//            else
//            {
//                RequestDispatcher rd = request.getRequestDispatcher("/principal.jsp");
//                rd.forward(request, response);
//            }
//        }
//        else
//        {
//            RequestDispatcher rd = request.getRequestDispatcher("/principal.jsp");
//            rd.forward(request, response);
//        }
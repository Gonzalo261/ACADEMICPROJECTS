/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controlador.GestorContrato;
import Controlador.GestorLogin;
import Controlador.GestorRetiro;
import Modelo.DTOUsuario;
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

@WebServlet(name = "Retiros", urlPatterns = {"/Retiros"})
public class Retiros extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if(request.getSession().getAttribute("usr")!= null)
        {
            GestorRetiro gestorR = new GestorRetiro();
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
                request.setAttribute("pedidosRetirar", gestorCON.obtenerPedidosParaRetirar(inicioAño,fechaB));
                RequestDispatcher rd = request.getRequestDispatcher("/listadoPedidosEntregar.jsp");
                rd.forward(request, response);
            }
            else if(modo.equals("pendientesAnualFiltro"))
            {
                request.setAttribute("pedidosRetirar", gestorCON.obtenerPedidosParaRetirar(fechaA,fechaB));
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
            else if(modo.equals("retirosRealizadosAnual"))
            {
                if(request.getSession().getAttribute("cliente")!=null)
                {
                    int idUsuarioCliente = ((DTOUsuario)request.getSession().getAttribute("cliente")).getId();
                    request.setAttribute("retirosRealizados", gestorR.obtenerPedidosRetiradosPorCliente(idUsuarioCliente,inicioAño,fechaB));
                }
                else request.setAttribute("retirosRealizados", gestorR.obtenerRetiros(inicioAño,fechaB));
                RequestDispatcher rd = request.getRequestDispatcher("/listadoEntregasRealizadas.jsp");
                rd.forward(request, response);
            }
            else if(modo.equals("retirosRealizadosFiltro"))
            {
                if(request.getSession().getAttribute("cliente")!=null)
                {
                    int idUsuarioCliente = ((DTOUsuario)request.getSession().getAttribute("cliente")).getId();
                    request.setAttribute("retirosRealizados", gestorR.obtenerPedidosRetiradosPorCliente(idUsuarioCliente,fechaA,fechaB));
                }
                else request.setAttribute("retirosRealizados", gestorR.obtenerRetiros(fechaA,fechaB));
                RequestDispatcher rd = request.getRequestDispatcher("/listadoRetirosRealizados.jsp");
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
  
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

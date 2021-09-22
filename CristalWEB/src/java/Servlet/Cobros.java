/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controlador.GestorCobro;
import Controlador.GestorContrato;
import Controlador.GestorFormasCobro;
import Controlador.GestorLogin;
import Modelo.Cobro;
import Modelo.DTOUsuario;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
@WebServlet(name = "Cobros", urlPatterns = {"/Cobros"})
public class Cobros extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if(request.getSession().getAttribute("usr")!= null)
        {
            GestorLogin gestorL = new GestorLogin();
            DTOUsuario dtoUsuario = (DTOUsuario) request.getSession().getAttribute("usr");
            String descripcionTipo = gestorL.obtenerDescripcionUsuarioPorId(dtoUsuario.getId());
            GestorCobro gestorC = new GestorCobro();
            GestorFormasCobro gestorFC = new GestorFormasCobro();
            GestorContrato gestorCON = new GestorContrato();
            String modo = request.getParameter("modo");
            
            int idCobro=0;
            if(request.getParameter("idCobro")!=null)
            idCobro = Integer.parseInt(request.getParameter("idCobro"));
            
            int idContrato=0;
            if(request.getParameter("idContrato")!=null)
            idContrato = Integer.parseInt(request.getParameter("idContrato"));
            
            int idUsuarioCliente = 0;
            if(descripcionTipo.equals("ADMINISTRADOR")||descripcionTipo.equals("CAJERO")||descripcionTipo.equals("VENDEDOR")){
                if(request.getSession().getAttribute("cliente")!=null)
                {
                    idUsuarioCliente = ((DTOUsuario)request.getSession().getAttribute("cliente")).getId();
                    request.setAttribute("listadoCobros", gestorC.obtenerCobrosPorId(idUsuarioCliente));
                }
                else request.setAttribute("listadoCobros", gestorC.obtenerCobros());
            }
            else 
            {
                idUsuarioCliente = dtoUsuario.getId();
                request.setAttribute("listadoCobros", gestorC.obtenerCobrosPorId(idUsuarioCliente));
            }
            
            String cobro = "";
            if(gestorL.obtenerDescripcionUsuarioPorId(dtoUsuario.getId()).equals("CLIENTE"))
            cobro="ONLINE";
            else cobro=gestorL.obtenerDescripcionUsuarioPorId(dtoUsuario.getId());
            
            if(modo == null || modo.isEmpty())
            {
                request.setAttribute("mensaje", "");
                request.getSession().setAttribute("cliente", gestorL.obtenerUsuarioPorId(idUsuarioCliente));
                RequestDispatcher rd = request.getRequestDispatcher("/listadoCobros.jsp");
                rd.forward(request, response);
            }
            else if(modo.equals("alta"))
            {
                request.setAttribute("vistaON","true");
                request.setAttribute("tipoCobro", cobro);
                request.setAttribute("contrato",gestorCON.obtenerDTOContratoPorId(idContrato) );
                request.setAttribute("formasCobro", gestorFC.obtenerFormasCobro());
                RequestDispatcher rd = request.getRequestDispatcher("/nuevoCobro.jsp");
                rd.forward(request, response);
            }
            else if(modo.equals("detalle"))
            {
                request.setAttribute("dtoCobro", gestorC.obtenerCobroPorId(idCobro));
                request.setAttribute("idCobro", idCobro);
                RequestDispatcher rd = request.getRequestDispatcher("/detalleCobro.jsp");
                rd.forward(request, response);
            }
            else if(modo.equals("eliminar"))
            {
                gestorC.eliminarCobro(idCobro);
                RequestDispatcher rd = request.getRequestDispatcher("/listadoCobros.jsp");
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
        
        GestorCobro gestorC = new GestorCobro();
        GestorLogin gestorL = new GestorLogin();
        GestorContrato gestorCON = new GestorContrato();
        DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        DTOUsuario dtoUsuario = (DTOUsuario) request.getSession().getAttribute("usr");
        
        int idUsuarioCajero = 0;
        if(gestorL.obtenerDescripcionUsuarioPorId(dtoUsuario.getId()).equals("CLIENTE"))
        idUsuarioCajero=gestorL.obtenerUsuarioPorNombre("ONLINE");
        else idUsuarioCajero=dtoUsuario.getId();
        
        int idContrato = Integer.parseInt(request.getParameter("idContrato"));
        String fechaCobrado = dtf4.format(LocalDateTime.now());
        int idFormaCobro =Integer.parseInt(request.getParameter("idFormasCobro")); 
        double montoCobrado = Double.parseDouble(request.getParameter("montoCobrado"));
        
        int numeroCheque=0;
        if(request.getParameter("numeroCheque")!=null)
             numeroCheque= Integer.parseInt(request.getParameter("numeroCheque")); 
        
        String fechaVencimientoCheque ="";
        if(request.getParameter("fechaVencimientoCheque")!=null)
             fechaVencimientoCheque= (String)(request.getParameter("fechaVencimientoCheque")); 

        Cobro cobro = new Cobro(0,idContrato,idUsuarioCajero,fechaCobrado,idFormaCobro,montoCobrado,numeroCheque,fechaVencimientoCheque,true);
        gestorC.agregarCobro(cobro);
        gestorCON.actualizarSaldoContrato(montoCobrado, idContrato);
        
        int idUsuarioCliente = 0;
        if(request.getSession().getAttribute("cliente")==null) idUsuarioCliente = dtoUsuario.getId();
        else idUsuarioCliente  = ((DTOUsuario)(request.getSession().getAttribute("cliente"))).getId();
        
        request.setAttribute("listadoCobros", gestorC.obtenerCobrosPorId(idUsuarioCliente));
        RequestDispatcher rd = request.getRequestDispatcher("/listadoCobros.jsp");
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

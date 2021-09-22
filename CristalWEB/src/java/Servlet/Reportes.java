/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controlador.GestorArticulo;
import Controlador.GestorLogin;
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

/**
 *
 * @author gonza
 */
@WebServlet(name = "Reportes", urlPatterns = {"/Reportes"})
public class Reportes extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        GestorArticulo gestorA = new GestorArticulo();
        GestorLogin gestorL = new GestorLogin ();
        DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Calendar cal= Calendar.getInstance();
        String inicioAño= cal.get(Calendar.YEAR)+"-01-01";
        
        String modo = request.getParameter("modo");

        if(modo == null || modo.isEmpty())
        {
            String fechaB = dtf4.format(LocalDateTime.now());
            request.setAttribute("clientesConTotalAlquilado",gestorL.obtenerClientesPorMontoAlquilado(inicioAño,fechaB));
            request.setAttribute("articuloMasDemandados",gestorA.obtenerArticulosMasAlquilados(inicioAño,fechaB));
            RequestDispatcher rd = request.getRequestDispatcher("/reporte.jsp");
            rd.forward(request, response);
        }
        else if(modo.equals("filtro"))
        {
            String fechaA = (String)request.getParameter("fechaA");
            String fechaB = (String)request.getParameter("fechaB");
            request.setAttribute("clientesConTotalAlquilado",gestorL.obtenerClientesPorMontoAlquilado(fechaA,fechaB));
            request.setAttribute("articuloMasDemandados",gestorA.obtenerArticulosMasAlquilados(fechaA,fechaB));
            RequestDispatcher rd = request.getRequestDispatcher("/reporte.jsp");
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

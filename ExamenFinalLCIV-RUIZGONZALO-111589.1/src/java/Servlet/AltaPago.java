/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controlador.GestorBD;
import Modelo.Contrato;
import Modelo.Pago;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "AltaPago", urlPatterns = {"/AltaPago"})
public class AltaPago extends HttpServlet {



    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            GestorBD gestor = new GestorBD ();
            request.setAttribute("accion", "AltaPago");
            request.setAttribute("contrato", gestor.obtenerContratos());
            RequestDispatcher rd = request.getRequestDispatcher("/altaPago.jsp");
            rd.forward(request, response);
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
        
        GestorBD gestor = new GestorBD();
        
        Pago p = new Pago();
        double importeContrato =0;
        
        int idContrato = Integer.parseInt(request.getParameter("idContrato"));
        int idMes = Integer.parseInt(request.getParameter("idMes"));
        int idAño = Integer.parseInt(request.getParameter("idAnio"));
        int demora = Integer.parseInt(request.getParameter("demora"));
        
        for (Contrato cont : gestor.obtenerContratos()) {
            if(cont.getIdContrato()==idContrato)
            {
                importeContrato = cont.getImporte();
                break;
            }
        }
        
        p.setIdContrato(idContrato);
        p.setMes(idMes);
        p.setAño(idAño);
        p.setDemora(demora);
        p.calcularImporte(importeContrato);
        
        gestor.agregarPago(p);

        RequestDispatcher rd = request.getRequestDispatcher("altaPago.jsp");
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

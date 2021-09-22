
<%-- 
    Proyecto   : CristalWEB
    Created on : 06/2021
    Author     : Gonzalo Ruiz
--%>
<%@page import="Modelo.DTOCobro"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pago</title>
        <%@include file="/navbarReporte.jsp" %>
    </head>
    <body>
         <c:choose>
            <c:when test="${not empty usr}">
        <center>
                    <div class="container fluid-5"> 
                        <div class="row">
                            <div class="col-12">
                                <h4 align="left">Ticket de Pago Nº: 001-0000${dtoCobro.idCobro}</h4>
                                <h4 align="left">Contrato Nº: 001-0000${dtoCobro.idContrato}</h4>
                                <h6 align="left">Cajero: ${dtoCobro.cajero}</h6>
                                <h6 align="left">Fecha Pago: ${dtoCobro.fechaCobrado}</h6>
                                <h6 align="left">Forma de Pago: ${dtoCobro.formaCobro}</h6>
                                <h6 align="left">Monto abonado: $ ${dtoCobro.montoCobrado}</h6>
                                <br>
                                 </div>
                        </div>     
                    </div>
                </center>
               </c:when>
                    <c:otherwise>
                        <%@include file="infoError.jsp" %>
            </c:otherwise>
         </c:choose>
    </body>
</html>

<%-- 
    Proyecto   : CristalWEB
    Created on : 06/2021
    Author     : Gonzalo Ruiz
--%>
<%@page import="Modelo.Articulo"%>
<%@page import="Modelo.DTOPresupuesto"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detalle Presupuesto</title>
        <%@include file="/navbarReporte.jsp" %>
    </head>
    <body>
         <c:choose>
            <c:when test="${not empty usr}">
                <center>
                    <div class="container fluid-5"> 
                        <div class="row">
                            <div class="col-12">
                        <h4 align="left"><b>Presupuesto Nº:</b> 001-0000${idPresupuesto}</h4>
                        <h6 align="left"><b>Cliente:</b> ${dTOPresupuesto.nombreCliente}</h6>
                        <h5 align="left"><b>Importe Total:</b> $ ${dTOPresupuesto.importeTotal}</h5>
                        <h6 align="left"><b>Inicio:</b> ${dTOPresupuesto.fechaInicioEvento}, 
                                        <b>Fin: </b>${dTOPresupuesto.fechaFinEvento}, 
                                        <b>Entrega:</b> ${dTOPresupuesto.fechaEntrega}, 
                                        <b>Retiro: </b> ${dTOPresupuesto.fechaRetiro}, 
                                        <b>Lugar Entrega:</b> ${dTOPresupuesto.lugarEntrega}
                        </h6>
                        <h6 align="left"><b>Comentarios/Agregados:</b> ${dTOPresupuesto.observaciones}</h6>
                        <br>
                            <table class="table table-hover table-active table-dark">
                                            <tr><th>Cantidad   </th><th>Descripcion   </th><th>Importe </th></tr>
                                            <tr><td>   </td><td>Costo del flete   </td><td> $ ${dTOPresupuesto.costoFlete}</td></tr>
                                            <c:forEach items="${ detalle }" var="item">
                                                <tr><td>${ item.cantidad }   </td><td>${ item.descripcion }</td> <td> $ ${ item.precio }</td> </tr>
                                            </c:forEach>
                                        </table>
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

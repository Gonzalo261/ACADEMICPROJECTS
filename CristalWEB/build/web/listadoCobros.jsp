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
        <title>Listado de cobros </title>
                <%@include file="/navbar.jsp" %>
    </head>
    <body>
         <center>
    <c:choose> 
         <c:when test="${not empty usr}">
               
               <div class="container fluid-5"> <br> 
                    <c:choose>
            <c:when test="${not empty cliente || usuario =='CLIENTE'}">
                <div class="row">
                    <div class="col-12">
                        <h4 align="left">Lista de pagos </h4><br>
                                    </div>
                        </div>
                                      </c:when>
            </c:choose>
                        <div class="row">
                        <c:choose>
                        <c:when test="${not empty listadoCobros}"><br> 
                        <table class="table table-hover table-active table-dark">
                                    <tr><th>Fecha Pago</th><th>Cajero  </th><th>Forma de Pago </th><th>Importe</th><th> Opciones </th></tr>
                                    <c:forEach items="${ listadoCobros }" var="item">
                                        <tr><td>${ item.fechaCobrado }</td><td>${ item.cajero }</td> <td> ${ item.formaCobro }</td><td> ${ item.montoCobrado }</td>
                                            <td>
                                                <button style="text-align:center; color:white " onclick="abrirVentana1(${ item.idCobro })" class="btn btn-group-sm">Comprobante</button>
                                                <button style="text-align:center; color:white " onclick="abrirVentana2(${ item.idContrato })" class="btn btn-group-sm">Contrato</button>
                                                <button style="text-align:center; color:green " onclick="window.location = 'Cobros?modo=alta&idContrato=${ item.idContrato }'" class="btn btn-group-sm">Nuevo Pago</button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                 </table>
                                     </c:when>
                            </c:choose>
                         </div>
                
                         </div>
                      
                  </c:when>
            <c:otherwise>
                <%@include file="infoError.jsp" %>
            </c:otherwise>
        </c:choose>
    </center>
    <%@include file="/footer.jsp" %>
    <script> 
function abrirVentana1 (id){ 
   window.open("http://localhost:8080/CristalWEB/Cobros?modo=detalle&idCobro="+id , "Detalle del Cobro" , "width=800,height=400,scrollbars=SI,left=300,top=100"); 
} 
</script>
<script> 
function abrirVentana2 (id){ 
   window.open("http://localhost:8080/CristalWEB/Contratos?modo=detalle&idContrato="+id , "Detalle del Contrato" , "width=800,height=400,scrollbars=SI,left=300,top=100"); 
} 
</script>
    </body>
</html>

<%-- 
    Proyecto   : CristalWEB
    Created on : 06/2021
    Author     : Gonzalo Ruiz
--%>
<%@page import="Modelo.DTOContrato"%>
<%@page import="Modelo.DTOPresupuesto"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pedidos para entregar</title>
        <%@include file="/navbar.jsp" %>
    </head>
    <body>
        <center><br>
            <div class="container fluid-5"> 
                <div class="row">
                    <div class="col-4">
                    <h4 align="left">Lista de Entregas pendientes</h4>
                                </div> 
                        <div class="col-8"> 
                            <b>desde</b> <input class="btn btn-group-sm my-2 my-sm-0" onChange="validarA()" value="${fechaSessionA}" name="fechaA" id="fechaA" type="date" >
                            <b>hasta</b> <input class="btn btn-group-sm my-2 my-sm-0"  onChange="validarB()" value="${fechaSessionB}" name="fechaB" id="fechaB" type="date" >
                            <button style="text-align:center; color:grey " class="btn btn-group-sm my-2 my-sm-0" onclick="filtrar(1)">Buscar</button>
                            <a class="btn-link" href="javascript:filtrar(2);">Pendientes de hoy</a>
                        </div>
                    </div><br>
                    <div class="row">
                            <div class="col-12">
                        <table class="table table-hover table-active table-secondary">
                            <tr><th>Fecha Entrega</th><th>Lugar</th><th>Cliente</th><th>Nº Contrato</th><th> Fecha Evento</th><th>Estado</th><th>Comentarios</th><th></th></tr>
                            <c:choose>
                                        <c:when test="${not empty pedidosEntregar}">  
                            <c:forEach items="${ pedidosEntregar }" var="item">
                                        <tr>
                                            <td>${ item.DTOPresupuesto.fechaEntrega } </td>
                                            <td> ${ item.DTOPresupuesto.lugarEntrega }</td>
                                            <td>${ item.DTOPresupuesto.nombreCliente } </td>
                                            <td>${ item.idContrato} </td>
                                            <td>${ item.DTOPresupuesto.fechaInicioEvento } </td>
                                            <td> ${ item.estado }</td>
                                            <td> ${ item.observaciones }</td>
                                            <td> <a style="text-align:center; color:green" onclick="window.location = 'Entregas?modo=alta&idContrato=${ item.idContrato }'" class="btn btn-group-sm">Entregar</a></td>
                                       </tr>
                                        </c:forEach>
                                           </c:when>
                                    <c:otherwise>
                                        <tr> <th colspan="8">Sin datos para mostrar. Seleccione otra fecha</th></tr> <br>
                                </c:otherwise>
                            </c:choose>
                                </table>
                                <br><br><br>
                         </div>
                </div>
            </div>
        </center>
                        <%@include file="/footer.jsp" %>
    <script>
        function filtrar(num)
        {
            if(num===1)
            {
                var fechaA = document.getElementById('fechaA').value;
                var fechaB = document.getElementById('fechaB').value;
                window.location = "Entregas?modo=pendientesAnualFiltro&fechaA="+ fechaA+"&fechaB="+ fechaB; 
            }
            else window.location = "Entregas?modo=pendientesAnual";   
        }
              
    </script>
    
    <script>
        function validarA()
        {
            var fechaA = document.getElementById("fechaA");
            var fechaB = document.getElementById("fechaB");
            
            fechaB.setAttribute("min", fechaA.value);
         }
    </script>
        <script>
        function validarB()
        {
            var fechaA = document.getElementById("fechaA");
            var fechaB = document.getElementById("fechaB");
            
            fechaA.setAttribute("max", fechaB.value);
         }
    </script>
    </body>
</html>

<%-- 
    Proyecto   : CristalWEB
    Created on : 06/2021
    Author     : Gonzalo Ruiz
--%>


<%@page import="Modelo.DTOContrato"%>
<%@page import="Modelo.DTOPresupuesto"%>
<%@page import="Modelo.DTODetallePresupuesto"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Informe y estadisticas</title>
        <%@include file="/navbar.jsp" %>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    </head>
    <body onload="fechaActual()">
         <c:choose>
            <c:when test="${not empty usr}">
        <center><br>
            <div class="container fluid-5"> 
                <div class="row">
                    <div class="col-3"></div>
                    <div class="col-9"> 
                        <b>desde</b> <input class="btn btn-group-sm my-2 my-sm-0" name="fechaA" id="fechaA" type="date" >
                        <b>hasta</b> <input class="btn btn-group-sm my-2 my-sm-0" name="fechaB" id="fechaB" type="date" >
                        <button style="text-align:center; color:grey " class="btn btn-group-sm my-2 my-sm-0" onclick="filtrar(1)">Filtrar</button>
                        <button style="text-align:center; color:grey " class="btn btn-group-sm my-2 my-sm-0" onclick="filtrar(2)">Anual</button>
                    </div>
                </div> <br><br>
                    <div class="row">
                    <div class="col-6">
                    <label>Articulos más alquilados</label> <br>
                    <table class="table table-hover table-active table-dark">
                        <tr><th>Cantidad   </th><th>Artículo   </th><th>Precio </th></tr>
                        <c:choose>
                            <c:when test="${not empty articuloMasDemandados}">
                                    <c:forEach items="${ articuloMasDemandados }" var="item">
                                        <tr><td>${ item.cantidad }</td><td>${ item.descripcion }</td> <td> $ ${ item.precio }</td> </tr>
                                    </c:forEach>
                            </c:when>
                                         <c:otherwise>
                                       <tr><td colspan="3"> Sin datos para mostrar. Seleccione otra fecha </td></tr>
                                </c:otherwise>
                           </c:choose>
                                </table>
                        <br>
                         </div>
                        <div class="col-1"> </div>
                        <div class="col-5">
                    <label>Clientes que más alquilaron </label> <br>
                    <table class="table table-hover table-active table-dark">
                        <tr><th>Cliente   </th><th>Total Alquilado  </th></tr>
                        <c:choose>
                            <c:when test="${not empty clientesConTotalAlquilado}">
                                    <c:forEach items="${ clientesConTotalAlquilado }" var="item">
                                        <tr>
                                            <td><a style="text-align:center; color:white" class="btn btn-group-sm my-2 my-sm-0" onclick="window.location = 'MisCuentas?idCliente=${item.idUsuario}'">${ item.nombre} ${item.apellido}</a></td>
                                            <td> $ ${ item.totalAlquilado }</td> 
                                        </tr>
                                    </c:forEach>
                               </c:when>
                               <c:otherwise>
                                   <tr><td colspan="3"> Sin datos para mostrar. Seleccione otra fecha </td></tr>
                                </c:otherwise>
                           </c:choose>
                                </table>
                    <br><br><br><br>
                        <br>
                         </div>
                </div> <br><br><br><br>
            </div>
                    <div class="container fluid-5"> 
                <div class="row">
                    <div class="col-12">
                        <div class="row">
                    <div class="col-5">
                        </div>
                            
                    </div>
                    </div>
                            
                </div>
                    
                    </div>
                
        </center>
       </c:when>
            <c:otherwise>
                <%@include file="infoError.jsp" %>
            </c:otherwise>
         </c:choose>

   <%@include file="/footer.jsp" %>
 
    
    <script>
        function fechaActual()
        {
            var fecha = new Date(); //Fecha actual
            var mes = fecha.getMonth()+1; //obteniendo mes
            var ano = fecha.getFullYear(); //obteniendo año
            var dia = fecha.getDate(); //obteniendo dia
            if(dia<10)
            dia='0'+dia; //agrega cero si el menor de 10
            if(mes<10)
              mes='0'+ mes; //agrega cero si es menor de 10
          
            var fechaActual = ano+"-"+mes+"-"+dia;
            document.getElementById("fechaB").value=fechaActual;
            document.getElementById("fechaB").setAttribute("max", fechaActual);
          }
              
    </script>
    <script>
        function filtrar(num)
        {
            if(num===1)
            {
                var fechaA = document.getElementById('fechaA').value;
                var fechaB = document.getElementById('fechaB').value;
                window.location = "Reportes?modo=filtro&fechaA="+ fechaA+"&fechaB="+ fechaB; 
            }
            else
                window.location = "Reportes"; 
        }
              
    </script>
    
    </body>
</html>

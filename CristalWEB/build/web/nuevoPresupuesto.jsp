<%-- 
    Proyecto   : CristalWEB
    Created on : 06/2021
    Author     : Gonzalo Ruiz
--%>
<%@page import="Modelo.Carrito"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Presupuesto</title>
        <%@include file="/navbar.jsp" %>
    </head>
    <body onload="actualizarFecha()">
        <c:choose>
           <c:when test="${vistaON}">
        <center>
             <form name="frmPresupuesto" action="Presupuestos" method="post" class="form-control">
            <div class="container fluid-3"> <br>
                <div class="row">
                        <input name="idUsuarioCliente" type="hidden" value="${ idCliente }" >
                    <div class="col-9"><h3 align="center">Datos del evento</h3></div>
                    <div class="col-3"><input style="text-align:center; color:blue " class="btn btn-group-sm my-2 my-sm-0" type="submit" value="Aceptar"></div>
                </div><br>
                <div class="row">
                        <br>
                        <table class="table table-hover table-active table-secondary">
                            <tr><th>Lugar
                                <select name="lugarEntrega" required class="btn btn-group-sm my-2 my-sm-0">
                                    <option selected="true" disabled>Indique la ciudad</option>
                                    <c:forEach items="${ ciud }" var="item">
                                        <option value="${ item.idCiudad }">${ item.descripcion }</option>
                                    </c:forEach>
                                </select>
                            </th>
                            <th>Inicio<input name="fechaInicioEvento" id="fechaInicioEvento" type="date" required onChange="modificar()" class="btn btn-group-sm my-2 my-sm-0"> </th>
                            <th>Fin<input name="fechaFinEvento" id="fechaFinEvento" type="date"  onChange="modificarB()" class="btn btn-group-sm my-2 my-sm-0"> </th>
                            <th>Entrega<input name="fechaEntrega" id="fechaEntrega" type="date"  required class="btn btn-group-sm my-2 my-sm-0"> </th>
                            <th>Retiro<input name="fechaRetiro" id="fechaRetiro" type="date"  required class="btn btn-group-sm my-2 my-sm-0"> </th></tr>
                            <tr><th colspan="5">Observaciones / Agregados<textarea name="observaciones"required class="form-control"></textarea></th></tr>
                            </table>
                   </div>
                   <div class="row">
                    <table class="table table-hover table-active table-dark">
                        <tr> 
                            <th>Nombre</th>
                             <th>Foto</th>
                             <th>Precio U.</th>
                             <th>Cantidad</th>
                             <th>Importe</th>
                        </tr>
                        <c:forEach items="${ carrito }" var="item">
                                        <tr>
                                            <td>${ item.articulo.descripcion }</td>
                                             <td>
                                                <a class="btn" onclick="expandir(${ item.articulo.id })">  
                                                <img src="${ item.articulo.imagen }" style="width:120px"></a>
                                            </td>
                                            <td>$ ${ item.articulo.precio }</td>
                                            <td>${ item.cantidad }</td>
                                            <th>${ item.articulo.precio * item.cantidad }</th>
                                       </tr>
                                       </c:forEach>
                                </table>
                </div>  <br>       
                    
                </div>
                 </form><br>
        </center>
       </c:when>
            <c:otherwise>
                <%@include file="infoError.jsp" %>
            </c:otherwise>
        </c:choose>
    <%@include file="/footer.jsp" %>
    <script> 
        function expandir(id)
        { 
           window.open("http://localhost:8080/CristalWEB/Articulos?modo=expandir&idImagen="+id , "Imagen" , "width=700,height=600,scrollbars=SI,left=350,top=150"); 
        } 
</script>
        <script>
        function actualizarFecha()
        {
            var fechaInicioEvento = document.getElementById("fechaInicioEvento");
            
            fechaInicioEvento.value = fechaActual();
            fechaInicioEvento.setAttribute("min", fechaActual());
            modificar();
        }
              
    </script>
    <script>
        function modificar()
        {
            var fechaInicioEvento = document.getElementById("fechaInicioEvento");
            var fechaFinEvento = document.getElementById("fechaFinEvento");
            var fechaEntrega = document.getElementById("fechaEntrega");
            var fechaRetiro = document.getElementById("fechaRetiro");
            
            fechaFinEvento.value= fechaInicioEvento.value;
            fechaFinEvento.setAttribute("min", fechaInicioEvento.value);
            
            fechaEntrega.value= fechaInicioEvento.value;
            fechaEntrega.setAttribute("min", fechaActual());
            fechaEntrega.setAttribute("max", fechaInicioEvento.value);
            fechaRetiro.value = fechaFinEvento.value;
            fechaRetiro.setAttribute("min", fechaFinEvento.value);
         }
    </script>
    <script>
        function modificarB()
        {
            var fechaFinEvento = document.getElementById("fechaFinEvento");
            var fechaRetiro = document.getElementById("fechaRetiro");

            fechaRetiro.value = fechaFinEvento.value;
            fechaRetiro.setAttribute("min", fechaFinEvento.value);
         }
    </script>
    
    <script>
        function fechaActual()
        {
            var fecha = new Date(); //Fecha actual
            var dia = fecha.getDate(); //obteniendo dia
            var mes = fecha.getMonth()+1; //obteniendo mes
            var ano = fecha.getFullYear(); //obteniendo año
            if(dia<10)
                dia='0'+dia; //agrega cero si el menor de 10    
            if(mes<10)
                  mes='0'+ mes; //agrega cero si es menor de 10

            var fechaHoy = ano+"-"+mes+"-"+dia;
            return fechaHoy;
         }
              
    </script>

    </body>
</html>




<%-- 


document.getElementById("dtpFechaE").value=fechaActual;
            document.getElementById("dtpFechaE").setAttribute("min", fechaActual);
            document.getElementById("dtpFechaE").setAttribute("min", fechaActual);



    <table class="table table-striped">
                                    <c:forEach items="${ art }" var="item">
                                        <tr>
                                            <td>${ item.descripcion } - $ ${ item.precio }
                                            <label><input type="checkbox" class="btn" id="${ item.id }"  value="${ item }" name="txtArticulos" >Agregar al carrito</label></td>
                                            <td rowspan="3">
                                                <a class="btn" onclick="expandir(${ item.id })">  
                                                <img src="${ item.imagen }" style="width:200px"></a>
                                                <c:choose>
                                                <c:when test="${usuario=='ADMINISTRADOR'}">
                                                    <button  onclick="window.location = 'Articulos?modo=editar&id=${ item.id }';" class="btn btn-secondary">Editar</button>
                                                    <button onclick="window.location = 'Articulos?modo=eliminar&id=${ item.id }';" class="btn btn-danger">Eliminar</button>
                                                 </c:when>
                                                </c:choose>
                                            </td>
                                       </tr>
                                       <tr>
                                            <td>
                                            <p> 
                                                PRECIO: sujeto a modificaciones; válido solo como alquiler; varía según la duración del evento: 2 días SE COBRA 1 (UNO)[EJ: TOTAL: $ ${ item.precio}](válido por 2 días);
                                                de a 3 días hasta 7 días SE COBRAN 2 (DOS) [EJ: TOTAL: $ ${ item.precio *2}] (contrato de 1(una) semana);
                                                mensual SE COBRAN 4 (CUATRO) [EJ: TOTAL: $ ${ item.precio *4}](contrato de 1 mes); 
                                                trimestral y superior SE COBRA 1(UNO) POR MES [EJ: POR MES: $ ${item.precio}](contrato minimo de 2 meses);
                                                Consultar disponibilidad por venta.
                                            </p>
                                            </td>
                                       </tr>
                                       <tr>
                                            <c:choose>
                                            <c:when test="${usuario=='ADMINISTRADOR'}">
                                                <td>Stock: ${ item.stock}</td>
                                            </c:when>
                                            </c:choose>
                                       </tr>
                                    </c:forEach>
                                </table>
--%>

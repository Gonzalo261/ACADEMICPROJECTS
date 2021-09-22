
<%-- 
    Proyecto   : CristalWEB
    Created on : 06/2021
    Author     : Gonzalo Ruiz
--%>
<%@page import="Modelo.Carrito"%>
<%@page import="Modelo.Articulo"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar al Carrito</title>
        <%@include file="/navbar.jsp" %>
    </head>
    <body>
    <center><br>
            <div class="container fluid-5"> 
                <div class="row">
                    <div class="col-2"></div>
                        <div class="col-5"> 
                           <label style="color:red;">${error}</label> <input name="descripcion" id="descripcion" class="form-control mr-sm-2" onkeyup = "if(event.keyCode === 13)filtrar()" type="search" placeholder="Que buscas?" required >
                        </div>
                    <div class="col-5"></div>
                    </div><br>
                <div class="row">
                    <div class="col-1"></div>
                    <div class="col-6">
                    <table class="table table-hover table-active table-secondary">
                        <c:forEach items="${ art }" var="item">
                                        <tr> <td rowspan="4" >
                                                <a class="btn" onclick="expandir(${ item.id })">  
                                                <img src="${ item.imagen }" style="width:70px"></a>
                                            </td></tr>
                                         <tr><td>${ item.nombre } - $ ${ item.precio } - Cantidad: <input  style="width : 50px;" min="1" max="100" type="number" value="1" id="cantidad" ></td></tr>
                                         <tr><td>${ item.descripcion }</td></tr>
                                         <tr><td><a style="text-align:center; color:green " class="btn btn-group-sm" onclick="agregar(${item.id})">Agregar al pedido</a></td>
                                        </tr>
                                       </c:forEach>
                                </table>
                        </div>
                    <div class="col-1"></div>
                    <div class="col-4">
                                <table class="table table-hover table-active table-success">
                                    <tr style="background-color:orange "> 
                                        <th >Cantidad</th>
                                        <th >Producto</th>
                                        <th ><c:choose><c:when test="${not empty carrito}">
                                                <a style="text-align:center; color:blue "  class="btn btn-group-sm" onclick="window.location='Presupuestos?modo=alta'" >Siguiente</a>
                                            </c:when></c:choose></th>
                                    </tr>
                                 <c:choose>
                                     <c:when test="${not empty carrito}">
                                    <c:forEach items="${ carrito }" var="item">
                                                
                                                <tr><input type="hidden" name="idArticulo" value="${ item.articulo.id }">
                                                    <td>${ item.cantidad }</td>
                                                    <td>${ item.articulo.nombre}</td>
                                                    <td><a style="text-align:center; color:red " class="btn btn-group-sm" onclick="window.location='Carritos?modo=eliminar&idArticulo=${ item.articulo.id }'">Eliminar</a></td>
                                                </tr>
                                               </c:forEach> 
                                 </c:when>
                                <c:otherwise>
                                    <tr><td><label> Agregue productos/servicios al pedido para continuar alquilando </label></td></tr>
                                </c:otherwise>
                                    </c:choose>
                                 </table>
                    </div>
                </div>
                </div>        
    </center>

    <script> 
        function expandir(id)
        { 
           window.open("http://localhost:8080/CristalWEB/Articulos?modo=expandir&idImagen="+id , "" , "width=700,height=600,scrollbars=SI,left=350,top=150"); 
        } 
</script>
    <script> 
        function filtrar()
        { 
          window.location='Carritos?modo=filtrar&descripcion='+document.getElementById('descripcion').value;
        } 
</script>
<script> 
        function agregar(idArticulo)
        { 
           window.location='Carritos?modo=agregar&idArticulo='+idArticulo+'&cantidad='+document.getElementById('cantidad').value;
        } 
</script>
   <%@include file="/footer.jsp" %>
    </body>
</html>


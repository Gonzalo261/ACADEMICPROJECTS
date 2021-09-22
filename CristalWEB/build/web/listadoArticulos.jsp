<%-- 
    Proyecto   : CristalWEB
    Created on : 06/2021
    Author     : Gonzalo Ruiz
--%>
<%@page import="Modelo.Articulo"%>
<%@page import="java.util.ArrayList"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Servicios</title>
        <%@include file="/navbar.jsp" %>
    </head>
    <body>
    <center> <br>
           <h1 class="mb-3">${categoria}</h1>
                                <hr class="teal accent-3 mb-4 mt-0 d-inline-block mx-auto" style="width: 800px;"> <br>
                            <div class="container fluid-5"> 
                <div class="row">
                    <div class="col-3"></div>
                            <div class="col-6">
                                
                        <table class="table table-hover table-active table-primary">
                                    <c:forEach items="${ art }" var="item">
                                        <tr>
                                            <td rowspan="4">
                                                <a class="btn" onclick="expandir(${ item.id })">  
                                                <img src="${ item.imagen }" style="width:70px"></a>
                                            </td>
                                        </tr>
                                        <tr><td> ${ item.nombre } - $ ${ item.precio }</td></tr>
                                        <tr><td> ${ item.descripcion } </td></tr>
                                        <tr><td>
                                                <c:choose>
                                                <c:when test="${usuario=='ADMINISTRADOR'}">
                                                    Stock: ${ item.stock} <br>
                                                    <button  onclick="window.location = 'Articulos?modo=editar&id=${ item.id }';" style="text-align:center; color:black" class="btn btn-group-sm my-2 my-sm-0">Editar</button>
                                                    <button onclick="window.location = 'Articulos?modo=eliminar&id=${ item.id }';" style="text-align:center; color:red" class="btn btn-group-sm my-2 my-sm-0">Eliminar</button>
                                                </c:when>
                                                </c:choose>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                         </div>
                    <div class="col-3"></div>
                </div>
            </div>
    </center>
                        <%@include file="/footer.jsp" %>
    <script> 
        function expandir(id)
        { 
           window.open("http://localhost:8080/CristalWEB/Articulos?modo=expandir&idImagen="+id , "Imagen" , "width=700,height=600,scrollbars=SI,left=350,top=150"); 
        } 
</script>
<script>
    function filtrar()
    {
        var idCategoria = "1";
        var categoria = document.getElementsByName('categoria');
        for(let i=0; i<categoria.length;i++){
            if(categoria[i].selectedIndex){
                idCategoria = categoria[i].value;
                break;
            }
        }
        window.location("Articulos?modo=filtrar&idCategoria="+ idCategoria); 
    }
</script>
    </body>
</html>

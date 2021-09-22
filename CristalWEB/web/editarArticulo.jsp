<%-- 
    Proyecto   : CristalWEB
    Created on : 06/2021
    Author     : Gonzalo Ruiz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar artículo</title>
        <%@include file="/navbar.jsp" %>
    </head>
    <body>
        <header><!-- Intro settings -->
                    <style>
                      /* Default height for small devices */
                      #intro-example {
                        height: 400px;
                      }

                      /* Height for devices larger than 992px */
                      @media (min-width: 992px) {
                        #intro-example {
                          height: 600px;
                        }
                      }
                    </style>
                    <!-- Background image -->
                    <div
                      id="intro-example"
                      class="p-5 text-center bg-image"
                      style="background-image: url('img/imgCarpa4.jpg');" >
                      <div class="mask" style="background-color: rgba(0, 0, 0, 0.7);">
                        <div class="d-flex justify-content-center align-items-center h-100">
                          <div class="text-white">
                            <c:choose>
            <c:when test="${usuario=='ADMINISTRADOR'}">
            <br><br><h3>${ accion } de Articulo</h3> <br>
            <form action="Articulos" method="post" class="form-control">
            <div class="container fluid-5"> 
                <div class="row">    
                    <div class="col-5">
                    <div class="form-group">
                        <input type="hidden" name="idArticulo" value="<jsp:getProperty name="modeloArticulo" property="id"></jsp:getProperty>" />
                        <label>Nombre</label>
                      <input name="nombre" required value="<jsp:getProperty name="modeloArticulo" property="nombre"></jsp:getProperty>" class="form-control" >
                      <label>Descripcion</label>
                      <input name="descripcion" required value="<jsp:getProperty name="modeloArticulo" property="descripcion"></jsp:getProperty>" class="form-control" >   
                    </div>
                    <div class="form-group">
                      <label>Precio</label>
                      <input  name="precio" value="<jsp:getProperty name="modeloArticulo" property="precio"></jsp:getProperty>" required class="form-control" >
                    </div>
                    </div>
                    <div class="col-2"></div>
                <div class="col-5">
                    <div class="form-group">
                        <label>Imagen </label>
                        <input type="file" accept="image/*" name="imagen" value="<jsp:getProperty name="modeloArticulo" property="imagen"></jsp:getProperty>" required class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label>Stock Inicial </label>
                        <input name="stock" value="<jsp:getProperty name="modeloArticulo" property="stock"></jsp:getProperty>" required class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label>Categoria </label>
                                <select name="idCategoria" required class="form-control">
                                    <option selected="true" disabled>Seleccione una categoria</option>
                                    <c:forEach items="${ categoria }" var="item">
                                        <option value="${ item.idCategoria }"<c:if test="${ item.idCategoria == modeloArticulo.idCategoria}">selected</c:if>>${ item.descripcion }</option>
                                    </c:forEach>
                                </select>
                    </div>
                    <button type="submit" <a style="text-align:center; color:blue " class="btn btn-group-sm">Aceptar</button>
                    <br>
                        </div>
                </div>
                </div>
                    </form>
            </div>
        </c:when>
            <c:otherwise>
                <%@include file="infoError.jsp" %>
            </c:otherwise>
        </c:choose>
                          </div>
                            
                        </div>
                          
                      </div>
                        
                    </div>
                    <!-- Background image -->
                  </header> 
        
                <%@include file="/footer.jsp" %>
    </body>
</html>


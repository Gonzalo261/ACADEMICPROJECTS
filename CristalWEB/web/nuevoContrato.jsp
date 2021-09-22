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
        <title>Nuevo Contrato</title>
        <%@include file="/navbar.jsp" %>
    </head>
    <body>
         <c:choose>
            <c:when test="${vistaON}">
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
                              <br><h3>Nuevo Alquiler</h3><br>
            <hr class="teal accent-3 mb-4 mt-0 d-inline-block mx-auto" style="width: 800px;"> <br>
                            <form name="frmContrato" action="Contratos" method="post" class="form-control">
            <div class="container fluid-5"> 
                <div class="row">
                    <div class="col-3"></div>
                    <div class="col-6">
                        <h3 align="left">Nuevo Contrato</h3>
                        <br>
                                <table class="table table-hover table-active table-secondary">
                                    <input name="idCliente" type="hidden" value="${idCliente}" >
                                    <tr><th>Venta: ${tipoVenta}</th></tr>
                                    <tr><th><input name="idPresupuesto" type="hidden" value="${idPresupuesto}" >Presupuesto: 001-0000${idPresupuesto}</th></tr>
                                   <tr><th><input name="saldo" type="hidden" value="${saldo}"class="form-control" >Saldo inicial: ${saldo}</th></tr>
                                   <tr><th>Estado inicial: INICIADO</th></tr>
                                   <tr><th>Comentario <input name="observaciones" type="textarea"class="form-control" ></th></tr>

                                    <tr><th><input  value="Aceptar" type="submit" style="text-align:center; color:blue " class="btn btn-group-sm my-2 my-sm-0"></th></tr>

                                    </table>
                        </div>
                    <div class="col-3"></div>
                </div>
                        
            </div>
        
                 </form>
                            
                        </div>
                          
                      </div>
                        
                    </div>
                    <!-- Background image -->
                  </header>
        <center>
             
        </center>
       </c:when>
            <c:otherwise>
                <%@include file="infoError.jsp" %>
            </c:otherwise>
        </c:choose>
<%@include file="/footer.jsp" %>
    </body>
</html>

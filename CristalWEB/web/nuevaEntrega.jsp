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
        
        <title>Nueva Entrega</title>
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
                              <br><h3>Nueva Entrega</h3><br>
                              <hr class="teal accent-3 mb-4 mt-0 d-inline-block mx-auto" style="width: 800px;"> <br>
                            <div class="container fluid-5"> 
                <div class="row">
                    <div class="col-2"></div>
                    <div class="col-8">
                        
                <form action="Entregas" method="post" class="form-control" style="background-color:grey ">
                    <div class="form-group">
                        Contrato Nº: <label name="idContrato">00-000${idContrato}</label>
                        <input name="idContrato" type="hidden" value="${idContrato}">
                    </div>
                    <div class="form-group">
                        Entregado por: <label >${tipoUsuario}: ${usr.persona.nombre}</label>
                    </div>
                    <div class="form-group">
                        <label>Observación </label>
                        <input name="observaciones"  type="text" required class="form-control"/>
                    </div>
                    <input type="submit" value="Aceptar" style="text-align:center; color:blue " class="btn btn-group-sm my-2 my-sm-0"><br> <br> 
                   </form>
                        </div>
                    <div class="col-2"></div>
                </div>
            </div>
                            
                        </div>
                          
                      </div>
                        
                    </div>
                    <!-- Background image -->
                  </header>
        <center>
            
            
                        </center>
    <%@include file="/footer.jsp" %>
    </body>
</html>

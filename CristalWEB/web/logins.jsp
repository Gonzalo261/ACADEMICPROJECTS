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
        <title>Mi Cuenta - Cristal</title>
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
                            <div class="container">
                            <div class="row">
                                <div class="col-12">
                                    <% if(request.getAttribute("mensajeError") != null && !request.getAttribute("mensajeError").equals("")) { %>
                                    <h4 class="form-text text-danger">${ mensajeError }</h4>
                                    <% } %>
                                    <div class="form-group">
                                        <form action="Logins" method="post" name="frmLogin">
                                            <div class="form-group">
                                              <label for="exampleInputEmail1">User</label>
                                              <input value="1" type="email" name="txtEmail" required  class="form-control" id="exampleInputEmail1" placeholder="Usuario">
                                              <small id="emailHelp" class="form-text text-danger">Nunca comparta usuarios ni contraseñas</small>
                                            </div>
                                            <div class="form-group">
                                              <label for="exampleInputPassword1">Password</label>
                                              <input type="password" name="txtPass" required onkeyup = "if(event.keyCode === 13)enviar(this.form)" class="form-control" id="exampleInputPassword1" placeholder="Password">
                                            </div>
                                            <input type="button" value="Aceptar" onclick="enviar(this.form)" style="text-align:center; color:white " class="btn btn-group-sm">
                                            </form>
                                           <br>
                                            <c:choose>
                                            <c:when test="${empty usr}">
                                           <a onclick="abrirVentana()"  class="btn btn-link">Registrarme</a>
                                           </c:when>
                                    </c:choose>
                                  </div>

                            </div>
                        </div>
                        </div>
                            
                        </div>
                          
                      </div>
                        
                    </div>
                    <!-- Background image -->
                  </header>
                        <%@include file="/footer.jsp" %>
           <script> 
        function abrirVentana(){ 
           window.location="Registros?modo=alta";
        } 
        </script>
          <script languaje="javascript">
            function enviar(form)
            {
                form.submit();
            }
          </script>
    </body>
  

</html>

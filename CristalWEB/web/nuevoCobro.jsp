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
        
        <title>Nuevo Pago</title>
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
                          <br><h3>Registrar Pago</h3><br>
            <hr class="teal accent-3 mb-4 mt-0 d-inline-block mx-auto" style="width: 800px;"> <br>
           <form name="frmCobro" action="Cobros" method="post" class="form-control">
                            <div class="container fluid-5"> 
                <div class="row">
                    <div class="col-6">
                    <div class="form-group">
                        <label>Cajero: ${tipoCobro} </label>
                    </div>
                    <div class="form-group">
                        <input name="idContrato" type="hidden" value="${contrato.idContrato}" >ContratoNº 001-0000${contrato.idContrato}
                    </div>
                    <div class="form-group">
                        <label>Abonar saldo?  </label>
                                           <input name="montoCobrado" type="number" max="${contrato.saldo}" value="${contrato.saldo}" required class="form-control">
                    </div>
                    </div>
                    <div class="col-6">
                        <label> Datos del cheque</label>
                    <div class="form-group">
                        <label>Numero</label>
                        <input name="numeroCheque" type="text" required class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Fecha Vencimiento </label>
                        <input name="fechaVencimientoCheque" type="date" required class="form-control">
                    </div>
                        </div>
                    <input  value="Aceptar" onclick="confirma();" style="text-align:center; color:blue " class="btn btn-group-sm my-2 my-sm-0">
                </div>
            </div>
                            </form>
                          </div>
                            
                        </div>
                          
                      </div>
                        
                    </div>
                    <!-- Background image -->
                  </header>
        <center>
           
            </c:when>
            <c:otherwise>
                <%@include file="infoError.jsp" %>
            </c:otherwise>
        </c:choose>
            <%@include file="/footer.jsp" %>            
        
        <script>
        function confirma()
        {
             document.frmCobro.submit();
        }
        </script>
        <script>
        function habilitar()
        {
            if(document.getElementById("idFormasCobro").value === 3)
             document.getElementById("myRow").style.display: 'none';
         else
             document.getElementById("myRow").style.display: 'block';
        }
        </script>
        
        <script src="https://sdk.mercadopago.com/js/v2"></script>
        
    </body>
</html>

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
        <title>Modificar Presupuesto</title>
        <%@include file="/navbar.jsp" %>
    </head>
    <body onload="fechaActual()">
         
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
            <c:when test="${vistaON}">
        <center>
            <br><h3>${ accion } datos del Evento</h3> <br>
            <hr class="teal accent-3 mb-4 mt-0 d-inline-block mx-auto" style="width: 800px;"> <br>
                    <form name="frmPresupuesto" action="Presupuestos" method="post" class="form-group">
            <div class="container fluid-5"> 
                <div class="row">
                    <div class="col-4"></div>
                    
                    <div class="col-4">
                        <br>
                        <table class="table table-hover table-active table-secondary">
                            <input name="idUsuarioCliente" type="hidden" value="<jsp:getProperty name="modeloPresupuesto" property="idUsuarioCliente"></jsp:getProperty>" >
                            <input name="idPresupuesto" type="hidden" value="<jsp:getProperty name="modeloPresupuesto" property="id"></jsp:getProperty>" >
                           
                           <tr><th>Lugar
                                <select name="lugarEntrega" required class="form-control">
                                    <option selected="true" disabled>Indique la ciudad</option>
                                    <c:forEach items="${ ciud }" var="item">
                                        <option value="${ item.idCiudad }" <c:if test="${ item.idCiudad == modeloPresupuesto.lugarEntrega}">selected</c:if>>${ item.descripcion }</option>
                                    </c:forEach>
                                </select>
                                   </th></tr>
                            <tr><th>Inicio Evento <input name="fechaInicioEvento" onChange="modificar()" id="fechaInicioEvento" type="date" required value="<jsp:getProperty name="modeloPresupuesto" property="fechaInicioEvento"></jsp:getProperty>" class="form-control"> </th></tr>
                            <tr><th>Fin Evento <input name="fechaFinEvento" onChange="modificarB()" id="fechaFinEvento" type="date" required value="<jsp:getProperty name="modeloPresupuesto" property="fechaFinEvento"></jsp:getProperty>" class="form-control"> </th></tr>
                            <tr><th>Entrega  <input name="fechaEntrega" id="fechaEntrega" type="date" required value="<jsp:getProperty name="modeloPresupuesto" property="fechaEntrega"></jsp:getProperty>" class="form-control"> </th></tr>
                            <tr><th>Retiro  <input name="fechaRetiro" id="fechaRetiro" type="date" required value="<jsp:getProperty name="modeloPresupuesto" property="fechaRetiro"></jsp:getProperty>" class="form-control"> </th></tr>
                            <tr><th>Comentario <input name="observaciones" required value="<jsp:getProperty name="modeloPresupuesto" property="observaciones"></jsp:getProperty>" class="form-control"> </th></tr>
                             
                            
                            <tr align="right"><th ><input  value="Aceptar" type="submit" style="text-align:center; color:blue " class="btn btn-group-sm"></th></tr>

                            </table>
                            </div>
                            <div class="col-4"> </div>
                </div>
                        
            </div>
        
                 </form>
        
        </center>
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
            fechaRetiro.setAttribute("max", sumarDias(2));
         }
    </script>
    <script>
        function modificarB()
        {
            var fechaFinEvento = document.getElementById("fechaFinEvento");
            var fechaRetiro = document.getElementById("fechaRetiro");

            fechaRetiro.value = fechaFinEvento.value;
            fechaRetiro.setAttribute("min", fechaFinEvento.value);
            fechaRetiro.setAttribute("max", sumarDias(2));
         }
    </script>
    <script>
        function sumarDias(dias)
        {
            var fechaFinEvento = document.getElementById("fechaFinEvento").value;
            document.getElementById("lblMostrar").innerHTML = fechaFinEvento;
            
            document.getElementById("lblMostrarB").innerHTML = fechaFinEvento.getDate() + dias;;
            return fechaFinEvento.getDate() + dias;
        }
        </script>
        <script>
        function restarDias(dias)
        {
            var fechaInicioEvento = document.getElementById("fechaInicioEvento").value;
            fechaInicioEvento.setDate(fechaInicioEvento.getDate() - dias);
            
            return fechaInicioEvento;
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
            document.getElementById("fechaInicioEvento").setAttribute("min", fechaHoy);
            modificar();
            modificarB();
            return fechaHoy;
         }
              
    </script>
    
    </body>
</html>


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
        
        <title>Nuevo Articulo</title>
        <%@include file="/navbar.jsp" %>
    </head>
    
    <body >
        <c:choose>
            <c:when test="${vistaON}">
        <center>
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
                            <br><h3>${ accion } Articulos</h3><br>
                            <hr class="teal accent-3 mb-4 mt-0 d-inline-block mx-auto" style="width: 800px;"> <br>
              <form action="Articulos" method="post" class="form-control" style="background-color:grey ">
                            <div class="container fluid-5"> 
                <div class="row">
                    <div class="col-6">
                    <div class="form-group">
                        <label>Nombre </label>
                        <input name="nombre" required id="idNombre" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label>Descripcion </label>
                        <input name="descripcion" required id="idDescripcion" class="form-control"/>
                    </div>
                   <div class="form-group">
                        <label>Precio </label>
                        $<input name="precio" required id="idPrecio" class="form-control"/>
                    </div>
                    </div>
                    <div class="col-6">
                    <div class="form-group">
                        <label>Imagen </label>
                        <input type="file" accept="image/*" name="imagen" required id="idImagen" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label>Stock Inicial </label>
                        <input name="stock" required id="idStock" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label>Categoria </label>
                                <select name="idCategoria" required class="form-control" id="idCategoria" >
                                    <option selected="true" disabled>Seleccione una categoria</option>
                                    <c:forEach items="${ categoriaNav }" var="item">
                                        <option value="${ item.idCategoria }">${ item.descripcion }</option>
                                    </c:forEach>
                                </select>
                    </div>
                        <label id="mensaje"> </label>
                    <input type="submit" id="idAceptar" value="Aceptar" style="text-align:center; color:blue " class="btn btn-group-sm my-2 my-sm-0"><br> <br> 
                   
                        </div>
                </div>
            </div>
                            </form>
                            
                        </div>
                          
                      </div>
                        
                    </div>  
                    </div>
                    <!-- Background image -->
                  </header>
            
                        </center>
            </c:when>
            <c:otherwise>
                <%@include file="infoError.jsp" %>
            </c:otherwise>
        </c:choose>
    <%@include file="/footer.jsp" %>
     <script>
            function validar(){
                var idNombre =  document.getElementById('idNombre');
                var idDescripcion = document.getElementById('idDescripcion');
                var idPrecio = document.getElementById('idPrecio');
                var idImagen = document.getElementById('idImagen');
                var idStock = document.getElementById('idStock');
                var idCategoria = document.getElementsByName('idCategoria');
                var idAceptar = document.getElementById('idAceptar');

                if(idNombre.value==="")
                    idNombre.setAttribute("style", "border-color:red;");
                    return false;
                }
 
                if(idDescripcion.value===""){
                    idDescripcion.setAttribute("style", "border-color:red;");
                    return false;
                }

                if(idPrecio.value==="" || isNaN(idPrecio.value)){
                    idPrecio.setAttribute("style", "border-color:red;");
                    return false;
                }
 
                if(idStock.value==="" || isNaN(idPrecio.value)){
                    idStock.setAttribute("style", "border-color:red;");
                    return false;
                }
                
                var cat = false;
                var combo = idCategoria;
                for(let i=0; i<combo.length;i++){
                    if(combo[i].selectedIndex){
                        cat = true;
                        break;
                    }
                }
                if(cat===false){
                    idCategoria.setAttribute("style", "border-color:red;");
                    return false;
                }
 
                return true;
            }
        </script>
        <script>
            function validar(){
                
                var idAceptar = document.getElementById('idAceptar');
                
                if(validar())
                {
                    idAceptar.disabled = true;
                    document.getElementById('idNombre').setAttribute("style", "border-color:gren;");
                    document.getElementById('idDescripcion').setAttribute("style", "border-color:gren;");
                    document.getElementById('idPrecio').setAttribute("style", "border-color:gren;");
                    document.getElementById('idImagen').setAttribute("style", "border-color:gren;");
                    document.getElementById('idStock').setAttribute("style", "border-color:gren;");
                    document.getElementById('idCategoria').setAttribute("style", "border-color:gren;");
                    document.getElementById('idAceptar').setAttribute("style", "border-color:gren;");
                }
                else 
                {
                    mensaje.disabled = false;
                    mensaje.innerHTML = "Complete todos los campos";
                }
            }
        </script>
        <script>
            function iniciar(){
                document.getElementById('idNombre').setAttribute("style", "border-color:red;");
                document.getElementById('idDescripcion').setAttribute("style", "border-color:red;");
                document.getElementById('idPrecio').setAttribute("style", "border-color:red;");
                document.getElementById('idImagen').setAttribute("style", "border-color:red;");
                document.getElementById('idStock').setAttribute("style", "border-color:red;");
                document.getElementById('idCategoria').setAttribute("style", "border-color:red;");
                document.getElementById('idAceptar').setAttribute("style", "border-color:red;");
            }
        </script>
    </body>
</html>

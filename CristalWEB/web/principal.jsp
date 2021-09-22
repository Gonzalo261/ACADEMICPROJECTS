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
        <title>Inicio</title>
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
                            <h1 class="mb-3">CRISTAL</h1>
                            <h5 class="mb-4">Somos una empresa dedicada al alquiler de insumos para eventos e industria</h5>
                            <hr class="teal accent-3 mb-4 mt-0 d-inline-block mx-auto" style="width: 800px;"> <br>
                            <a
                              style="text-align:center; color:white " 
                              class="btn btn-group-sm my-2 my-sm-0"
                              href="Articulos?idCategoria=1"
                              role="button"
                              rel="nofollow"
                              >CARPAS</a>
                            <a
                              style="text-align:center; color:white " 
                              class="btn btn-group-sm my-2 my-sm-0"
                              href="Articulos?idCategoria=4"
                              role="button"
                              rel="nofollow"
                              >COCINA</a>
                            <a
                              style="text-align:center; color:white " 
                              class="btn btn-group-sm my-2 my-sm-0"
                              href="Articulos?idCategoria3"
                              role="button"
                              >BAZAR</a>
                            <a
                              style="text-align:center; color:white " 
                              class="btn btn-group-sm my-2 my-sm-0"
                              href="Articulos?idCategoria=8"
                              role="button"
                              rel="nofollow"
                              >MOBILIARIO</a>
                          </div>
                            
                        </div>
                          
                      </div>
                        
                    </div>
                    <!-- Background image -->
                  </header>
                <!-- Footer -->
            <footer class="page-footer font-small mdb-color lighten-3 pt-4">

              <!-- Footer Elements -->
              <div class="container">

                <!--Grid row-->
                <div class="row">

                  <!--Grid column-->
                  <div class="col-lg-2 col-md-12 mb-4">

                    <!--Image-->
                    <div class="view overlay z-depth-1-half">
                      <img src="img/imgCarpa1.jpg" class="img-fluid"
                        alt="">
                    </div>

                  </div>
                  <!--Grid column-->

                  <!--Grid column-->
                  <div class="col-lg-2 col-md-6 mb-4">

                    <!--Image-->
                    <div class="view overlay z-depth-1-half">
                      <img src="img/imgCarpa2.jpg" class="img-fluid"
                        alt="">
                    </div>

                  </div>
                  <!--Grid column-->

                  <!--Grid column-->
                  <div class="col-lg-2 col-md-6 mb-4">

                    <!--Image-->
                    <div class="view overlay z-depth-1-half">
                      <img src="img/imgCarpa3.jpg" class="img-fluid"
                        alt="">
                    </div>

                  </div>
                  <!--Grid column-->

                  <!--Grid column-->
                  <div class="col-lg-2 col-md-12 mb-4">

                    <!--Image-->
                    <div class="view overlay z-depth-1-half">
                      <img src="img/imgCarpa4.jpg" class="img-fluid"
                        alt="">
                    </div>

                  </div>
                  <!--Grid column-->

                  <!--Grid column-->
                  <div class="col-lg-2 col-md-6 mb-4">

                    <!--Image-->
                    <div class="view overlay z-depth-1-half">
                      <img src="img/imgCarpa5.jpg" class="img-fluid"
                        alt="">
                    </div>

                  </div>
                  <!--Grid column-->

                  <!--Grid column-->
                  <div class="col-lg-2 col-md-6 mb-4">

                    <!--Image-->
                    <div class="view overlay z-depth-1-half">
                      <img src="img/silla.jpg" class="img-fluid"
                        alt="">
                    </div>

                  </div>
                  <!--Grid column-->

                </div>
                <!--Grid row-->

              </div>
              <!-- Footer Elements -->

            </footer>
            <!-- Footer -->
    <%@include file="/footer.jsp" %>
    </body>
</html>

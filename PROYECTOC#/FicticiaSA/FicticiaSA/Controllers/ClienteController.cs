using FicticiaSA.AccesoDatos;
using FicticiaSA.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace FicticiaSA.Controllers
{
    public class ClienteController : Controller
    {
        // GET: Cliente
        public ActionResult Index()
        {
            return View();
        }

        public ActionResult Alta()
        {
            GestorBD gestor = new GestorBD();
            VMNuevoRegistro vm = new VMNuevoRegistro();
            vm.generoVM = gestor.listadoGeneros();
            return View(vm);
        }

        [HttpPost]
        public ActionResult Alta(VMNuevoRegistro nuevo)
        {
            GestorBD gestor = new GestorBD();
            if (ModelState.IsValid)
            {
                gestor.agregarCliente(nuevo.clienteVM);

                nuevo.usuarioVM.idCliente = gestor.obtenerUltimoCliente();

                gestor.agregarUsuario(nuevo.usuarioVM);

                return View("Index");
            }
            else
            {
                nuevo.generoVM = gestor.listadoGeneros();
                return View(nuevo);
            }

        }

        public ActionResult AltaAdmin()
        {
            GestorBD gestor = new GestorBD();
            VMNuevoRegistro vm = new VMNuevoRegistro();
            vm.generoVM = gestor.listadoGeneros();
            return View(vm);
        }

        [HttpPost]
        public ActionResult AltaAdmin(VMNuevoRegistro nuevo)
        {
            GestorBD gestor = new GestorBD();
            if (ModelState.IsValid)
            {
                gestor.agregarCliente(nuevo.clienteVM);
                nuevo.usuarioVM.idCliente = gestor.obtenerUltimoCliente();
                gestor.agregarUsuario(nuevo.usuarioVM);

                return View("Index");
            }
            else
            {
                nuevo.generoVM = gestor.listadoGeneros();
                return View(nuevo);
            }
        }

        public ActionResult Editar(int idCliente)
        {
            GestorBD gestor = new GestorBD();
            var cliente = gestor.BuscarCliente(idCliente);
            return View(cliente);
        }


        [HttpPost]
        public ActionResult Editar(VMCliente cliente)
        {
            GestorBD gestor = new GestorBD();
            if (ModelState.IsValid)
            {
                gestor.updateCliente(cliente.clienteVM);
                return RedirectToAction("MisDatos", gestor.BuscarClienteDTO(cliente.clienteVM.idCliente));
            }
            else
            {
                cliente.generoVM = gestor.listadoGeneros();
                return View(cliente);
            }

        }

        public ActionResult EliminarUsuario(int idCliente)
        {
            GestorBD gestor = new GestorBD();
            gestor.bajaUsuario(idCliente, false);
            return RedirectToAction("CerrarSesion");
        }

        public ActionResult ListadoClientes()
        {
            GestorBD gestor = new GestorBD();
            var lista = gestor.listadoClientes();
            return View(lista);
        }

        public ActionResult MisDatos()
        {
            GestorBD gestor = new GestorBD();
            Usuario usuario = Session["usuario"] as Usuario;
            DTOCliente cliente = gestor.BuscarClienteDTO(usuario.idCliente);
            return View(cliente);
        }

        public ActionResult Login()
        {
            return View();
        }

        public ActionResult CerrarSesion()
        {
            Session["usuario"] = null;
            Session["admin"] = null;
            return View("Login");
        }

        [HttpPost]
        public ActionResult Login(Usuario usuario)
        {
            var band = false;
            var band2 = false;
            GestorBD gestor = new GestorBD();

            DTOCliente cliente = null;

            foreach (var user in gestor.obtenerUsuarios())
            {
                if (usuario.name == user.name && user.password == usuario.password)
                {
                    cliente = gestor.BuscarClienteDTO(user.idCliente);
                    if (cliente.estado)
                    {
                        band = true;
                        Session["usuario"] = user;
                        if (user.admin == true)
                            Session["admin"] = user;
                    }
                    else
                        band2 = true;
                }
            }

            if (band)
            {
                return View("MisDatos",cliente);
            }
            else
            {
                if(band2)
                    ViewBag.Message = "Usuario eliminado";
                else
                    ViewBag.Message = "Usuario o Password incorrectos";

                return View("Login");
            }
        }
    }
}
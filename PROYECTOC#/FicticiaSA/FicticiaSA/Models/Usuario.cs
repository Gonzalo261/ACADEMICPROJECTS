using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace FicticiaSA.Models
{
    public class Usuario
    {
        public int idUsuario { set; get; }
        [Required(ErrorMessage = "Debe ingresar usuario")]
        public string name { set; get; }
        [Required(ErrorMessage = "Debe ingresar Contraseña")]
        public string password { set; get; }
        public int idCliente { set; get; }
        public bool admin { set; get; }
    }
}
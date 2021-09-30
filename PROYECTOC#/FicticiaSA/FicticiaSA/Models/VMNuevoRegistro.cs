using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace FicticiaSA.Models
{
    public class VMNuevoRegistro
    {
        public Cliente clienteVM { set; get; }
        public Usuario usuarioVM { set; get; }
        public List<Genero> generoVM { set; get; }
    }
}
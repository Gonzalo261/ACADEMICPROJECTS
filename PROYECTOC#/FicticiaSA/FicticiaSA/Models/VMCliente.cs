using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace FicticiaSA.Models
{
    public class VMCliente
    {
        public Cliente clienteVM { set; get; }
        public List<Genero> generoVM { set; get; }
    }
}
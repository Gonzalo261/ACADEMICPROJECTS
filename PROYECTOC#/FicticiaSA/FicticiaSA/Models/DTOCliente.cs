using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace FicticiaSA.Models
{
    public class DTOCliente
    {
        public int idCliente { set; get; }
        public string nombre { set; get; }
        public int dni { set; get; }
        public int edad { set; get; }
        public string genero { set; get; }
        public bool estado { set; get; }
        public bool maneja { set; get; }
        public bool usaLentes { set; get; }
        public bool diabetico { set; get; }
        public string otraEnfermedad { set; get; }
    }
}
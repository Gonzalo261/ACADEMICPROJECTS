using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace FicticiaSA.Models
{
    public class Cliente
    {
        public int idCliente { set; get; }
        [Required(ErrorMessage = "Debe ingresar nombre")]
        public string nombre { set; get; }
        [Range(1, 99999999, ErrorMessage = "Ingrese un DNI max de 8 caracteres")]
        public int dni { set; get; }
        [Range(18, 85, ErrorMessage = "Entre 18 y 85 años")]
        public int edad { set; get; }
        public int idGenero { set; get; }
        public bool estado { set; get; }
        public bool maneja { set; get; }
        public bool usaLentes { set; get; }
        public bool diabetico { set; get; }
        [Required(ErrorMessage = "Si no posee otra enfermedad indique que NO")]
        public string otraEnfermedad { set; get; }
    }
} 
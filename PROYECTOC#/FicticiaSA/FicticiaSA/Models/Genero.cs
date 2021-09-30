using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace FicticiaSA.Models
{
    public class Genero
    {
        public int idGenero { set; get; }
        [Required]
        public string descripcion { set; get; }
    }
}
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Parcial1w32019
{
    class Vehiculo
    {
        string patente;
        string nombre;
        int tipo;
        int marca;
        DateTime fecha;

        public string Patente { get => patente; set => patente = value; }

        public Vehiculo(string patente, string nombre, int tipo, int marca, DateTime fecha)
        {
            this.Patente = patente;
            this.Nombre = nombre;
            this.Tipo = tipo;
            this.Marca = marca;
            this.Fecha = fecha;
        }

        public Vehiculo()
        {
            this.Patente = "";
            this.Nombre = "";
            this.Tipo = 0;
            this.Marca = 0;
            this.Fecha = DateTime.Today;
        }

        public string Nombre { get => nombre; set => nombre = value; }
        public int Tipo { get => tipo; set => tipo = value; }
        public int Marca { get => marca; set => marca = value; }
        public DateTime Fecha { get => fecha; set => fecha = value; }

        public string toString()
        {
            return patente + " " + nombre + " " + tipo + " " + marca + " " + fecha;
        }
    }
}

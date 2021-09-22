using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Data.OleDb;

namespace Parcial1w32019
{
    public partial class frmVehiculo : Form
    {
        OleDbCommand cmd ;
        OleDbConnection conexion ;
        string cadenaConexion = @"Provider=Microsoft.Jet.OLEDB.4.0;Data Source=C:\Users\GONZALO\Documents\TecnicaturaProgramacion\2ºCUAT\PR2\Parcial1w32019\dbfVehiculos.mdb";
        OleDbDataReader lector;
        DataTable tabla;
        const int tam = 100;
        Vehiculo[] v = new Vehiculo[tam];
        int contador;

        public frmVehiculo()
        {
            InitializeComponent();
            listarVehiculos();
            cargarCombo();
            limpiarCampos();
            habilitar(false,true,false,false,true);
        }

        public void cargarCombo()
        {
            conectar();
            cmd.CommandText = "SELECT * FROM Marca";
            tabla = new DataTable();
            tabla.Load(cmd.ExecuteReader());
            cboMarca.DataSource = tabla;
            cboMarca.ValueMember = tabla.Columns[0].ColumnName;
            cboMarca.DisplayMember = tabla.Columns[1].ColumnName;
            cboMarca.DropDownStyle = ComboBoxStyle.DropDownList;
            conexion.Close();
        }

        public void listarVehiculos()
        {
            conectar();
            cmd.CommandText = "SELECT * FROM Vehiculo";

            lector = cmd.ExecuteReader();
            contador = 0;
            while (lector.Read())
            {
                v[contador] = new Vehiculo();
                v[contador].Patente = lector.GetString(0);
                v[contador].Nombre = lector.GetString(1);
                v[contador].Tipo = lector.GetInt32(2);
                v[contador].Marca = lector.GetInt32(3);
                v[contador].Fecha = lector.GetDateTime(4);

                contador++;
            }
            conexion.Close();
            lector.Close();

            lstVehiculo.Items.Clear();

            for (int i = 0; i < contador; i++)
            {
                lstVehiculo.Items.Add(v[i].Nombre +" " + v[i].Patente);
            }
        }

        public void conectar()
        {
            conexion = new OleDbConnection(cadenaConexion);
            conexion.Open();
            cmd = new OleDbCommand();
            cmd.Connection = conexion;
            cmd.CommandType = CommandType.Text;
        }

        private void BtnNuevo_Click(object sender, EventArgs e)
        {
            limpiarCampos();
            habilitar(true, false, true, true, false);
        }

        public void habilitarTexBox(bool tb)
        {
            txtPatente.Enabled = tb;
            txtNombre.Enabled = tb;
            cboMarca.Enabled = tb;
            rbtAuto.Enabled = tb;
            rbtCamion.Enabled = tb;
            dtpFecha.Enabled = tb;
        }

        public void habilitar(bool tb, bool n, bool g, bool c, bool s)
        {
            habilitarTexBox(tb);
            btnNuevo.Enabled = n;
            btnGrabar.Enabled = g;
            btnCancelar.Enabled = c;
            btnSalir.Enabled = s;
        }

        private void BtnGrabar_Click(object sender, EventArgs e)
        {
            Vehiculo vi = new Vehiculo();
            if (validar() == true)
            {
                vi.Patente = txtPatente.Text;
                vi.Nombre = txtNombre.Text;
                vi.Marca = cboMarca.SelectedIndex;
                if (rbtAuto.Checked is true)
                    vi.Tipo = 1;
                else
                    vi.Tipo = 2;
                conectar();
                cmd.CommandText = "INSERT INTO Vehiculo (patente, nombre, marca, tipo, fecha) "
                                       + " VALUES ('" + vi.Patente + "','" + vi.Nombre + "'," + vi.Marca + "," + vi.Tipo + ",'" + vi.Fecha + "')";
                cmd.ExecuteNonQuery();
                conexion.Close();
                listarVehiculos();
                limpiarCampos();
                habilitar(false,true,false,false,true);
            }
        }

        private void BtnSalir_Click(object sender, EventArgs e)
        {
            Close();
        }

        public bool validar()
        {
            if (txtPatente.Text == "")
            {
                MessageBox.Show("Ingrese un codigo");
                txtPatente.Focus();
                return false;
            }
            if (cboMarca.SelectedIndex == -1)
            {
                MessageBox.Show("Seleccione una marca");
                cboMarca.Focus();
                return false;
            }
            if (rbtAuto.Checked == false && rbtCamion.Checked == false)
            {
                MessageBox.Show("Marque una opcion");
                rbtAuto.Focus();
                return false;
            }
            return true;

        }

        public void limpiarCampos()
        {
            txtPatente.Text = "";
            txtNombre.Text = "";
            cboMarca.SelectedIndex = -1;
            rbtAuto.Checked = false;
            rbtCamion.Checked = false;
            dtpFecha.Value = DateTime.Today;
        }

        private void BtnCancelar_Click(object sender, EventArgs e)
        {
            limpiarCampos();
            habilitar(false,true,false,false,true);
        }
    }

}

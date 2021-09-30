using FicticiaSA.Models;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace FicticiaSA.AccesoDatos
{
    public class GestorBD
    {
        private SqlConnection con;

        private void abrirConexion()
        {
            try
            {
                con = new SqlConnection(ConfigurationManager.ConnectionStrings["dbFicticiaSA"].ConnectionString);
            }
            catch (Exception exc)
            {
            }
        }

        private void cerrarConexion()
        {
            try
            {
                if (con != null)
                    con.Close();
            }
            catch (Exception exc)
            {
            }
        }

        public void agregarCliente(Cliente cliente)
        {
            try
            {
                abrirConexion();
                var sql = "INSERT INTO CLIENTE (NOMBRE, DNI, EDAD, GENERO, ESTADO, MANEJA, LENTES, DIABETICO, OTRA)VALUES(@NOMBRE,@DNI,@EDAD,@GENERO,1,@MANEJA,@LENTES,@DIABETICO,@OTRA)";
                con.Open();
                SqlCommand cmd = new SqlCommand(sql, con);

                cmd.Parameters.AddWithValue("@NOMBRE", cliente.nombre);
                cmd.Parameters.AddWithValue("@DNI", cliente.dni);
                cmd.Parameters.AddWithValue("@EDAD", cliente.edad);
                cmd.Parameters.AddWithValue("@GENERO", cliente.idGenero);
                cmd.Parameters.AddWithValue("@MANEJA", cliente.maneja);
                cmd.Parameters.AddWithValue("@LENTES", cliente.usaLentes);
                cmd.Parameters.AddWithValue("@DIABETICO", cliente.diabetico);
                cmd.Parameters.AddWithValue("@OTRA", cliente.otraEnfermedad);

                cmd.ExecuteNonQuery();
            }
            catch (Exception exc)
            {

            }
            finally
            {
                cerrarConexion();
            }
        }

        public void updateCliente(Cliente cliente)
        {

            abrirConexion();

            var sql = @"UPDATE CLIENTE SET NOMBRE = @NOMBRE, DNI = @DNI, EDAD = @EDAD, GENERO = @GENERO, MANEJA = @MANEJA, LENTES = @LENTES, DIABETICO = @DIABETICO, OTRA = @OTRA WHERE ID = @ID";

            con.Open();
            SqlCommand cmd = new SqlCommand(sql, con);

            cmd.Parameters.AddWithValue("@ID", cliente.idCliente);
            cmd.Parameters.AddWithValue("@NOMBRE", cliente.nombre);
            cmd.Parameters.AddWithValue("@DNI", cliente.dni);
            cmd.Parameters.AddWithValue("@EDAD", cliente.edad);
            cmd.Parameters.AddWithValue("@GENERO", cliente.idGenero);
            cmd.Parameters.AddWithValue("@MANEJA", cliente.maneja);
            cmd.Parameters.AddWithValue("@LENTES", cliente.usaLentes);
            cmd.Parameters.AddWithValue("@DIABETICO", cliente.diabetico);
            cmd.Parameters.AddWithValue("@OTRA", cliente.otraEnfermedad);

            cmd.ExecuteNonQuery();

            cerrarConexion();
        }

        public List<Genero> listadoGeneros()
        {
            var lista = new List<Genero>();

            var sql = @"SELECT IDGENERO, DESCRIPCION
                        FROM GENERO";
            abrirConexion();
            con.Open();
            SqlCommand cmd = new SqlCommand(sql, con);
            SqlDataReader dr = cmd.ExecuteReader();

            while (dr.Read())
            {
                Genero genero = new Genero();

                genero.idGenero = (int)dr["IDGENERO"];
                genero.descripcion = (string)dr["DESCRIPCION"];

                lista.Add(genero);
            }

            dr.Close();
            cerrarConexion();

            return lista;
        }


        public VMCliente BuscarCliente(int idCliente)
        {
            var sql = @"SELECT ID, NOMBRE, DNI, EDAD, GENERO, ESTADO, MANEJA, LENTES, DIABETICO, OTRA
                        FROM CLIENTE WHERE ID = @ID AND ESTADO = 1";
            abrirConexion();
            con.Open();
            SqlCommand cmd = new SqlCommand(sql, con);
            cmd.Parameters.AddWithValue("@ID", idCliente);

            SqlDataReader dr = cmd.ExecuteReader();
            dr.Read();

            Cliente cliente = new Cliente();

            cliente.idCliente = (int)dr["ID"];
            cliente.nombre = (string)dr["NOMBRE"];
            cliente.dni = (int)dr["DNI"];
            cliente.edad = (int)dr["EDAD"];
            cliente.idGenero = (int)dr["GENERO"];
            cliente.estado = (bool)dr["ESTADO"];
            cliente.maneja = (bool)dr["MANEJA"];
            cliente.usaLentes = (bool)dr["LENTES"];
            cliente.diabetico = (bool)dr["DIABETICO"];
            cliente.otraEnfermedad = (string)dr["OTRA"];

            VMCliente vmCliente = new VMCliente();

            vmCliente.clienteVM = cliente;
            vmCliente.generoVM = listadoGeneros();

            dr.Close();
            con.Close();

            return vmCliente;
        }

        public DTOCliente BuscarClienteDTO(int idCliente)
        {
            var sql = @"SELECT ID, NOMBRE, DNI, EDAD, G.DESCRIPCION, ESTADO, MANEJA, LENTES, DIABETICO, OTRA
                        FROM CLIENTE C
                        JOIN GENERO G ON C.GENERO = G.IDGENERO
                        WHERE ID = @ID AND C.ESTADO = 1";
            abrirConexion();
            con.Open();
            SqlCommand cmd = new SqlCommand(sql, con);
            cmd.Parameters.AddWithValue("@ID", idCliente);

            SqlDataReader dr = cmd.ExecuteReader();
            dr.Read();

            DTOCliente cliente = new DTOCliente();

            cliente.idCliente = (int)dr["ID"];
            cliente.nombre = (string)dr["NOMBRE"];
            cliente.dni = (int)dr["DNI"];
            cliente.edad = (int)dr["EDAD"];
            cliente.genero = (string)dr["DESCRIPCION"];
            cliente.estado = (bool)dr["ESTADO"];
            cliente.maneja = (bool)dr["MANEJA"];
            cliente.usaLentes = (bool)dr["LENTES"];
            cliente.diabetico = (bool)dr["DIABETICO"];
            cliente.otraEnfermedad = (string)dr["OTRA"];

            dr.Close();
            cerrarConexion();

            return cliente;
        }

        public List<DTOCliente> listadoClientes()
        {
            var lista = new List<DTOCliente>();

            var sql = @"SELECT ID, NOMBRE, DNI, EDAD, G.DESCRIPCION, ESTADO, MANEJA, LENTES, DIABETICO, OTRA
                          FROM CLIENTE C
                        JOIN GENERO G ON C.GENERO = G.IDGENERO WHERE ESTADO = 1";
            abrirConexion();
            con.Open();
            SqlCommand cmd = new SqlCommand(sql, con);
            SqlDataReader dr = cmd.ExecuteReader();

            while (dr.Read())
            {
                DTOCliente cliente = new DTOCliente();

                cliente.idCliente = (int)dr["ID"];
                cliente.nombre = (string)dr["NOMBRE"];
                cliente.dni = (int)dr["DNI"];
                cliente.edad = (int)dr["EDAD"];
                cliente.genero = (string)dr["DESCRIPCION"];
                cliente.estado = (bool)dr["ESTADO"];
                cliente.maneja = (bool)dr["MANEJA"];
                cliente.usaLentes = (bool)dr["LENTES"];
                cliente.diabetico = (bool)dr["DIABETICO"];
                cliente.otraEnfermedad = (string)dr["OTRA"];

                lista.Add(cliente);
            }

            dr.Close();
            cerrarConexion();

            return lista;
        }


        public List<Usuario> obtenerUsuarios()
        {
            var lista = new List<Usuario>();

            var sql = @"SELECT IDUSUARIO, NAME, PASS,IDCLIENTE,ADMINISTRADOR 
                            FROM USUARIO U 
                            JOIN CLIENTE C ON C.ID = U.IDCLIENTE
                            WHERE C.ESTADO = 1";
            abrirConexion();
            con.Open();
            SqlCommand cmd = new SqlCommand(sql, con);
            SqlDataReader dr = cmd.ExecuteReader();

            while (dr.Read())
            {
                Usuario usuario = new Usuario();

                usuario.name = (string)dr["NAME"];
                usuario.password = (string)dr["PASS"];
                usuario.idUsuario = (int)dr["IDUSUARIO"];
                usuario.idCliente = (int)dr["IDCLIENTE"];
                usuario.admin = (bool)dr["ADMINISTRADOR"];

                lista.Add(usuario);
            }

            dr.Close();
            cerrarConexion();

            return lista;
        }


        public void agregarUsuario(Usuario usuario)
        {
            try
            {
                abrirConexion();
                var sql = "INSERT INTO USUARIO (NAME, PASS, IDCLIENTE, ADMINISTRADOR)VALUES(@NAME,@PASSWORD,@IDCLIENTE, @ADMINISTRADOR)";
                con.Open();
                SqlCommand cmd = new SqlCommand(sql, con);

                cmd.Parameters.AddWithValue("@NAME", usuario.name);
                cmd.Parameters.AddWithValue("@PASSWORD", usuario.password);
                cmd.Parameters.AddWithValue("@IDCLIENTE", usuario.idCliente);
                cmd.Parameters.AddWithValue("@ADMINISTRADOR", usuario.admin);

                cmd.ExecuteNonQuery();
            }
            catch (Exception exc)
            {

            }
            finally
            {
                cerrarConexion();
            }
        }

        public void bajaUsuario(int idCliente, bool estado)
        {
            abrirConexion();

            var sql = @"UPDATE CLIENTE SET ESTADO = @ESTADO  WHERE ID = @ID";

            con.Open();
            SqlCommand cmd = new SqlCommand(sql, con);

            cmd.Parameters.AddWithValue("@ID", idCliente);
            cmd.Parameters.AddWithValue("@ESTADO", estado);

            cmd.ExecuteNonQuery();

            cerrarConexion();
        }

        public int obtenerUltimoCliente()
        {
            var sql = @"SELECT TOP 1 ID
                        FROM CLIENTE
						ORDER BY ID DESC";

            abrirConexion();
            con.Open();

            SqlCommand cmd = new SqlCommand(sql, con);
            SqlDataReader dr = cmd.ExecuteReader();
            dr.Read();

            int idCliente = (int)dr["ID"];

            dr.Close();
            cerrarConexion();

            return idCliente;
        }
    }
    
}
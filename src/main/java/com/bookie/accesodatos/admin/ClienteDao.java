package com.bookie.accesodatos.admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bookie.accesodatos.AccesoDatosException;
import com.bookie.modelos.admin.Cliente;

public class ClienteDao {

	private static final String URL_BD = "jdbc:mysql://localhost:3306/bookie";
	private static final String USUARIO_BD = "root";
	private static final String PASSWORD_BD = "";

	private static Connection obtenerConexion() {
		try {
			return DriverManager.getConnection(URL_BD, USUARIO_BD, PASSWORD_BD);
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha encontrado la conexion a la base de datos", e);
		}
	}

	// COMPROBACIÓN DEL DRIVER
	static {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("No se ha encontrado el driver de conexión con la base de datos");
		}
	}

	public static Iterable<Cliente> todosClientes() {

		try (Connection con = obtenerConexion();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM clientes")) {
			ArrayList<Cliente> clientes = new ArrayList<Cliente>();

			while (rs.next()) {
				clientes.add(new Cliente(rs.getLong("id"), rs.getString("nombre"), rs.getString("apellidos"),
						rs.getString("email"), rs.getString("telefono"), rs.getString("sexo"),
						rs.getString("domicilio"), rs.getString("ciudad"), rs.getString("pais"),
						rs.getDate("fecha_nacimiento")));
			}
			return clientes;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido obtener los clientes", e);
		}
	}

	public static Cliente porIdCliente(Long id) {

		try {
			Connection con = obtenerConexion();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM clientes WHERE id = ?");
			ps.setLong(1, id);

			ResultSet rs = ps.executeQuery();

			Cliente cliente = null;

			while (rs.next()) {
				cliente = new Cliente(rs.getLong("id"), rs.getString("nombre"), rs.getString("apellidos"),
						rs.getString("email"), rs.getString("telefono"), rs.getString("sexo"),
						rs.getString("domicilio"), rs.getString("ciudad"), rs.getString("pais"),
						rs.getDate("fecha_nacimiento"));
			}

			return cliente;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha encontrado el cliente con id: " + id, e);
		}

	}

	public static Cliente porIdUsuario(Long id) {

		try {
			Connection con = obtenerConexion();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM clientes WHERE usuarios_id = ?");
			ps.setLong(1, id);

			ResultSet rs = ps.executeQuery();

			Cliente cliente = null;

			while (rs.next()) {
				cliente = new Cliente(rs.getLong("id"), rs.getString("nombre"), rs.getString("apellidos"),
						rs.getString("email"), rs.getString("telefono"), rs.getString("sexo"),
						rs.getString("domicilio"), rs.getString("ciudad"), rs.getString("pais"),
						rs.getDate("fecha_nacimiento"));
			}

			return cliente;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha encontrado el cliente con id: " + id, e);
		}

	}

	public static Cliente porNombreCliente(String nombre) {
		try {
			Connection con = obtenerConexion();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM clientes WHERE nombre = ?");
			ps.setString(1, nombre);

			ResultSet rs = ps.executeQuery();

			Cliente cliente = null;

			while (rs.next()) {
				cliente = new Cliente(rs.getLong("id"), rs.getString("nombre"), rs.getString("apellidos"),
						rs.getString("email"), rs.getString("telefono"), rs.getString("sexo"),
						rs.getString("domicilio"), rs.getString("ciudad"), rs.getString("pais"),
						rs.getDate("fecha_nacimiento"));
			}

			return cliente;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha encontrado el cliente con nombre: " + nombre, e);
		}

	}

	public static Cliente porEmailCliente(String email) {

		try {
			Connection con = obtenerConexion();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM clientes WHERE email = ?");
			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();

			Cliente cliente = null;

			while (rs.next()) {
				cliente = new Cliente(rs.getLong("id"), rs.getString("nombre"), rs.getString("apellidos"),
						rs.getString("email"), rs.getString("telefono"), rs.getString("sexo"),
						rs.getString("domicilio"), rs.getString("ciudad"), rs.getString("pais"),
						rs.getDate("fecha_nacimiento"));
			}
			return cliente;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha encontrado el cliente con email: " + email, e);
		}

	}

	public static void añadirCliente(Cliente cliente) {
		try (Connection con = obtenerConexion();
				PreparedStatement ps = con.prepareStatement(
						"INSERT INTO clientes (nombre, apellidos, fecha_nacimiento, telefono, email, sexo, domicilio, ciudad, pais, usuarios_id) VALUES(?,?,?,?,?,?,?,?,?,?)")) {
			ps.setString(1, cliente.getNombre());
			ps.setString(2, cliente.getApellidos());
			ps.setDate(3, cliente.getFecha_nacimiento());
			ps.setString(4, cliente.getTelefono());
			ps.setString(5, cliente.getEmail());
			ps.setString(6, cliente.getSexo());
			ps.setString(7, cliente.getDomicilio());
			ps.setString(8, cliente.getCiudad());
			ps.setString(9, cliente.getPais());
			ps.setLong(10, cliente.getUsuario().getId());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido añadir el cliente: " + cliente, e);
		}

	}

	public static void editarCliente(Cliente cliente) {

		try (Connection con = obtenerConexion();
				PreparedStatement ps = con.prepareStatement(
						"UPDATE usuarios SET nombre=?, apellidos=?, fecha_nacimiento=?, telefono=?, email=?, sexo=?, domicilio=?, ciudad=?, pais, usuarios_id=? WHERE id=?")) {
			ps.setString(1, cliente.getNombre());
			ps.setString(2, cliente.getApellidos());
			ps.setDate(3, cliente.getFecha_nacimiento());
			ps.setString(4, cliente.getTelefono());
			ps.setString(5, cliente.getEmail());
			ps.setString(6, cliente.getSexo());
			ps.setString(7, cliente.getDomicilio());
			ps.setString(8, cliente.getCiudad());
			ps.setString(9, cliente.getPais());
			ps.setLong(10, cliente.getUsuario().getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido modificar el cliente " + cliente, e);
		}

	}

	public static void archivarCliente(Long id) {

		try (Connection con = obtenerConexion();
				PreparedStatement ps = con.prepareStatement("UPDATE clientes SET usuarios_id = 1 WHERE usuarios_id = ?")) {
			ps.setLong(1, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido archivar el cliente con id:  " + id, e);
		}

	}

	public static void borrarCliente(Long id) {

		try (Connection con = obtenerConexion();
				PreparedStatement ps = con.prepareStatement("DELETE FROM clientes WHERE id=?")) {
			ps.setLong(1, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido borrar el ciente con id: " + id, e);
		}

	}

}

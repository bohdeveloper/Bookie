package com.bookie.accesodatos.admin;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bookie.accesodatos.AccesoDatosException;
import com.bookie.modelos.admin.RolUsuario;
import com.bookie.modelos.admin.Usuario;

public class UsuarioDao {

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

	public static Iterable<Usuario> todosUsuarios() {

		try (Connection con = obtenerConexion();
				Statement st = con.createStatement();
				ResultSet rs = st
						.executeQuery("SELECT * FROM usuarios u JOIN roles r ON r.id = u.roles_id ORDER BY r.id")) {
			ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

			Usuario usuario;

			while (rs.next()) {
				usuario = new Usuario(rs.getLong("u.id"), rs.getString("u.nombre_usuario"), rs.getString("u.email"),
						rs.getString("u.password"), rs.getString("u.sexo"),
						rs.getBigDecimal("u.saldo"));
				usuario.setRolUsuario(
						new RolUsuario(rs.getLong("r.id"), rs.getString("r.nombre"), rs.getString("r.descripcion")));
				usuarios.add(usuario);
			}
			return usuarios;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido obtener los usuarios", e);
		}
	}

	public static Usuario porIdUsuario(Long id) {

		try {
			Connection con = obtenerConexion();
			PreparedStatement ps = con
					.prepareStatement("SELECT * FROM usuarios u JOIN roles r ON r.id = u.roles_id WHERE u.id = ?");
			ps.setLong(1, id);

			ResultSet rs = ps.executeQuery();

			Usuario usuario = null;

			while (rs.next()) {
				usuario = new Usuario(rs.getLong("u.id"), rs.getString("u.nombre_usuario"), rs.getString("u.email"),
						rs.getString("u.password"), rs.getString("u.sexo"),
						rs.getBigDecimal("u.saldo"));
				usuario.setRolUsuario(
						new RolUsuario(rs.getLong("r.id"), rs.getString("r.nombre"), rs.getString("r.descripcion")));
			}

			return usuario;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha encontrado el usuario con id: " + id, e);
		}

	}

	public static Usuario porNombreUsuario(String nombre) {
		try {
			Connection con = obtenerConexion();
			PreparedStatement ps = con.prepareStatement(
					"SELECT * FROM usuarios u JOIN roles r ON r.id = u.roles_id WHERE u.nombre_usuario = ?");
			ps.setString(1, nombre);

			ResultSet rs = ps.executeQuery();

			Usuario usuario = null;

			while (rs.next()) {
				usuario = new Usuario(rs.getLong("u.id"), rs.getString("u.nombre_usuario"), rs.getString("u.email"),
						rs.getString("u.password"), rs.getString("u.sexo"),
						rs.getBigDecimal("u.saldo"));
				usuario.setRolUsuario(
						new RolUsuario(rs.getLong("r.id"), rs.getString("r.nombre"), rs.getString("r.descripcion")));
			}

			return usuario;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha encontrado el usuario con nombre: " + nombre, e);
		}

	}

	public static Usuario porEmailUsuario(String email) {

		try {
			Connection con = obtenerConexion();
			PreparedStatement ps = con
					.prepareStatement("SELECT * FROM usuarios u JOIN roles r ON r.id = u.roles_id WHERE u.email = ?");
			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();

			Usuario usuario = null;

			while (rs.next()) {
				usuario = new Usuario(rs.getLong("u.id"), rs.getString("u.nombre_usuario"), rs.getString("u.email"),
						rs.getString("u.password"), rs.getString("u.sexo"),
						rs.getBigDecimal("u.saldo"));
				usuario.setRolUsuario(
						new RolUsuario(rs.getLong("r.id"), rs.getString("r.nombre"), rs.getString("r.descripcion")));
			}

			return usuario;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha encontrado el usuario con email: " + email, e);
		}

	}

	public static Usuario porIdUsuarioSaldo(Long id) {

		try {
			Connection con = obtenerConexion();
			PreparedStatement ps = con.prepareStatement("SELECT saldo FROM usuarios WHERE id = ?");
			ps.setLong(1, id);

			ResultSet rs = ps.executeQuery();

			Usuario usuario = null;

			while (rs.next()) {
				usuario = new Usuario(null, null, null, null, null, rs.getBigDecimal("saldo"));
			}

			return usuario;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha encontrado el saldo del usuario con id: " + id, e);
		}

	}

	public static void añadirUsuario(Usuario usuario) {
		try (Connection con = obtenerConexion();
				PreparedStatement ps = con.prepareStatement(
						"INSERT INTO usuarios (nombre_usuario, email, password, saldo, sexo, roles_id) VALUES(?,?,?,?,?,?)")) {
			ps.setString(1, usuario.getNombre_usuario());
			ps.setString(2, usuario.getEmail());
			ps.setString(3, usuario.getPassword());
			ps.setBigDecimal(4, usuario.getSaldo());
			ps.setString(5, usuario.getSexo());
			ps.setLong(6, usuario.getRolUsuario().getId());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido añadir el usuario: " + usuario, e);
		}

	}

	public static void añadirSaldoUsuario(Usuario usuario, BigDecimal saldo) {

		try (Connection con = obtenerConexion();
				PreparedStatement ps = con.prepareStatement("UPDATE usuarios SET saldo=? WHERE id=?")) {
			ps.setBigDecimal(1, saldo);
			ps.setLong(2, usuario.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido añadir el saldo " + saldo + " al usuario " + usuario, e);
		}

	}

	public static void editarUsuario(Usuario usuario) {

		try (Connection con = obtenerConexion();
				PreparedStatement ps = con.prepareStatement(
						"UPDATE usuarios SET nombre_usuario=?,email=?,password=?,saldo=?,sexo=?,roles_id=? WHERE id=?")) {
			ps.setString(1, usuario.getNombre_usuario());
			ps.setString(2, usuario.getEmail());
			ps.setString(3, usuario.getPassword());
			ps.setBigDecimal(4, usuario.getSaldo());
			ps.setString(5, usuario.getSexo());
			ps.setLong(6, usuario.getRolUsuario().getId());
			ps.setLong(7, usuario.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido modificar el usuario " + usuario, e);
		}

	}

	public static void borrarUsuario(Long id) {

		try (Connection con = obtenerConexion();
				PreparedStatement ps = con.prepareStatement("DELETE FROM usuarios WHERE id=?")) {
			ps.setLong(1, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido borrar el usuario con id: " + id, e);
		}

	}

}

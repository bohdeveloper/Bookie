package com.bookie.accesodatos.admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bookie.accesodatos.AccesoDatosException;
import com.bookie.modelos.admin.RolUsuario;

public class RolUsuarioDao {

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

	public static Iterable<RolUsuario> todosRolUsuarios() {

		try (Connection con = obtenerConexion();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM roles")) {
			ArrayList<RolUsuario> roles = new ArrayList<RolUsuario>();

			while (rs.next()) {
				roles.add(new RolUsuario(rs.getLong("id"), rs.getString("nombre"), rs.getString("descripcion")));
			}

			return roles;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido obtener los roles de usuarios", e);
		}
	}

	public static RolUsuario porIdRolUsuario(Long id) {

		try {
			Connection con = obtenerConexion();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM roles WHERE id = ?");
			ps.setLong(1, id);

			ResultSet rs = ps.executeQuery();

			RolUsuario rolUsuario = null;

			while (rs.next()) {
				rolUsuario = new RolUsuario(rs.getLong("id"), rs.getString("nombre"), rs.getString("descripcion"));
			}

			return rolUsuario;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha encontrado el rol de usuario con id: " + id, e);
		}

	}

	public static RolUsuario porNombreRolUsuario(String nombre) {

		try {
			Connection con = obtenerConexion();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM roles WHERE nombre = ?");
			ps.setString(1, nombre);

			ResultSet rs = ps.executeQuery();

			RolUsuario rolUsuario = null;

			while (rs.next()) {
				rolUsuario = new RolUsuario(rs.getLong("id"), rs.getString("nombre"), rs.getString("descripcion"));
			}

			return rolUsuario;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha encontrado el rol de usuario con nombre: " + nombre, e);
		}

	}

	public static void añadirRolUsuario(RolUsuario rolUsuario) {

		try (Connection con = obtenerConexion();
				PreparedStatement ps = con
						.prepareStatement("INSERT INTO roles (nombre, descripcion) VALUES(?,?)")) {
			ps.setString(1, rolUsuario.getNombre());
			ps.setString(2, rolUsuario.getDescripcion());



			ps.executeUpdate();
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido añadir el rol de usuario: " + rolUsuario, e);
		}

	}

	public static void editarRolUsuario(RolUsuario rolUsuario) {

		try (Connection con = obtenerConexion();
				PreparedStatement ps = con
						.prepareStatement("UPDATE roles SET nombre=?,descripcion=? WHERE id=?")) {
			ps.setString(1, rolUsuario.getNombre());
			ps.setString(2, rolUsuario.getDescripcion());
			ps.setLong(3, rolUsuario.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido modificar el rol de usuario: " + rolUsuario, e);
		}

	}

	public static void borrarRolUsuario(Long id) {

		try (Connection con = obtenerConexion();
				PreparedStatement ps = con.prepareStatement("DELETE FROM roles WHERE id=?")) {
			ps.setLong(1, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido borrar el rol de usuario con id: " + id, e);
		}

	}

}

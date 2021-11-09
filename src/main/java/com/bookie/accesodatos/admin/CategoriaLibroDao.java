package com.bookie.accesodatos.admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bookie.accesodatos.AccesoDatosException;
import com.bookie.modelos.admin.CategoriaLibro;

public class CategoriaLibroDao {

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

	public static Iterable<CategoriaLibro> todasCategorias() {

		try (Connection con = obtenerConexion();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM categorias")) {
			ArrayList<CategoriaLibro> categorias = new ArrayList<CategoriaLibro>();

			while (rs.next()) {
				categorias.add(new CategoriaLibro(rs.getLong("id"), rs.getString("nombre"), rs.getString("descripcion")));
			}

			return categorias;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido obtener las categorias de los libros", e);
		}
	}

	public static CategoriaLibro porIdCategoriaLibro(Long id) {

		try {
			Connection con = obtenerConexion();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM categorias WHERE id = ?");
			ps.setLong(1, id);

			ResultSet rs = ps.executeQuery();

			CategoriaLibro categoriaLibro = null;

			while (rs.next()) {
				categoriaLibro = new CategoriaLibro(rs.getLong("id"), rs.getString("nombre"), rs.getString("descripcion"));
			}

			return categoriaLibro;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha encontrado la categoría del libro con id: " + id, e);
		}

	}

	public static CategoriaLibro porNombreCategoriaLibro(String nombre) {

		try {
			Connection con = obtenerConexion();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM categorias WHERE nombre = ?");
			ps.setString(1, nombre);

			ResultSet rs = ps.executeQuery();

			CategoriaLibro categoriaLibro = null;

			while (rs.next()) {
				categoriaLibro = new CategoriaLibro(rs.getLong("id"), rs.getString("nombre"), rs.getString("descripcion"));
			}

			return categoriaLibro;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha encontrado a categoría del libro con nombre: " + nombre, e);
		}

	}

	public static void añadirCategoriaLibro(CategoriaLibro categoriaLibro) {

		try (Connection con = obtenerConexion();
				PreparedStatement ps = con
						.prepareStatement("INSERT INTO categorias (nombre, descripcion) VALUES(?,?)")) {
			ps.setString(1, categoriaLibro.getNombre());
			ps.setString(2, categoriaLibro.getDescripcion());



			ps.executeUpdate();
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido añadir la categoria de libro: " + categoriaLibro, e);
		}

	}

	public static void editarCategoriaLibro(CategoriaLibro categoriaLibro) {

		try (Connection con = obtenerConexion();
				PreparedStatement ps = con
						.prepareStatement("UPDATE categorias SET nombre=?,descripcion=? WHERE id=?")) {
			ps.setString(1, categoriaLibro.getNombre());
			ps.setString(2, categoriaLibro.getDescripcion());
			ps.setLong(3, categoriaLibro.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido modificar la categoria de libro: " + categoriaLibro, e);
		}

	}

	public static void borrarCategoriaLibro(Long id) {

		try (Connection con = obtenerConexion();
				PreparedStatement ps = con.prepareStatement("DELETE FROM categorias WHERE id=?")) {
			ps.setLong(1, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido borrar la categoría del libro con id: " + id, e);
		}

	}

}

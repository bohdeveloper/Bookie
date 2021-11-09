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
import com.bookie.modelos.admin.Libro;

public class LibroDao {

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

	public static Iterable<Libro> todosLibros() {

		try (Connection con = obtenerConexion();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM libros l JOIN categorias c ON c.id = l.categorias_id")) {

			ArrayList<Libro> libros = new ArrayList<Libro>();

			Libro libro;

			while (rs.next()) {
				libro = new Libro(rs.getLong("l.id"), rs.getString("l.sku"), rs.getString("l.nombre"),
						rs.getString("l.descripcion"), rs.getString("l.autor"), rs.getString("l.imagen"),
						rs.getBigDecimal("l.precio"), rs.getInt("l.descuento"));
				libro.setCategoriaLibro(new CategoriaLibro(rs.getLong("c.id"), rs.getString("c.nombre"),
						rs.getString("c.descripcion")));
				libros.add(libro);
			}
			return libros;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido obtener los libros", e);
		}
	}

	public static Libro porIdLibro(Long id) {

		try {
			Connection con = obtenerConexion();
			PreparedStatement ps = con.prepareStatement(
					"SELECT * FROM libros l JOIN categorias c ON c.id = l.categorias_id WHERE l.id = ?");
			ps.setLong(1, id);

			ResultSet rs = ps.executeQuery();

			Libro libro = null;

			while (rs.next()) {
				libro = new Libro(rs.getLong("l.id"), rs.getString("l.sku"), rs.getString("l.nombre"),
						rs.getString("l.descripcion"), rs.getString("l.autor"), rs.getString("l.imagen"),
						rs.getString("l.precio"), rs.getString("l.descuento"));
				libro.setCategoriaLibro(new CategoriaLibro(rs.getLong("c.id"), rs.getString("c.nombre"),
						rs.getString("c.descripcion")));
			}

			return libro;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha encontrado el libro con id: " + id, e);
		}

	}

	public static Libro porSkuLibro(String sku) {

		try {
			Connection con = obtenerConexion();
			PreparedStatement ps = con.prepareStatement(
					"SELECT * FROM libros l JOIN categorias c ON c.id = l.categorias_id  WHERE l.sku = ?");
			ps.setString(1, sku);

			ResultSet rs = ps.executeQuery();

			Libro libro = null;

			while (rs.next()) {
				libro = new Libro(rs.getLong("l.id"), rs.getString("l.sku"), rs.getString("l.nombre"),
						rs.getString("l.descripcion"), rs.getString("l.autor"), rs.getString("l.imagen"),
						rs.getString("l.precio"), rs.getString("l.descuento"));
				libro.setCategoriaLibro(new CategoriaLibro(rs.getLong("c.id"), rs.getString("c.nombre"),
						rs.getString("c.descripcion")));
			}

			return libro;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha encontrado el libro con id: " + sku, e);
		}

	}

	public static Libro porNombreLibro(String nombre) {

		try {
			Connection con = obtenerConexion();
			PreparedStatement ps = con.prepareStatement(
					"SELECT * FROM libros l JOIN categorias c ON c.id = l.categorias_id  WHERE l.nombre = ?");
			ps.setString(1, nombre);

			ResultSet rs = ps.executeQuery();

			Libro libro = null;

			while (rs.next()) {
				libro = new Libro(rs.getLong("l.id"), rs.getString("l.sku"), rs.getString("l.nombre"),
						rs.getString("l.descripcion"), rs.getString("l.autor"), rs.getString("l.imagen"),
						rs.getString("l.precio"), rs.getString("l.descuento"));
				libro.setCategoriaLibro(new CategoriaLibro(rs.getLong("c.id"), rs.getString("c.nombre"),
						rs.getString("c.descripcion")));
			}

			return libro;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha encontrado el libro con id: " + nombre, e);
		}

	}

	public static void añadirLibro(Libro libro) {

		try (Connection con = obtenerConexion();
				PreparedStatement ps = con.prepareStatement(
						"INSERT INTO libros (sku, nombre, descripcion, autor, imagen, precio, descuento, categorias_id) VALUES(?,?,?,?,?,?,?,?)")) {
			ps.setString(1, libro.getSku());
			ps.setString(2, libro.getNombre());
			ps.setString(3, libro.getDescripcion());
			ps.setString(4, libro.getAutor());
			ps.setString(5, libro.getImagen());
			ps.setBigDecimal(6, libro.getPrecio());
			ps.setInt(7, libro.getDescuento());
			ps.setLong(8, libro.getCategoriaLibro().getId());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido añadir el libro: " + libro, e);
		}

	}

	public static void editarLibro(Libro libro) {

		try (Connection con = obtenerConexion();
				PreparedStatement ps = con.prepareStatement(
						"UPDATE libros SET sku=?,nombre=?,descripcion=?,autor=?,imagen=?,precio=?,descuento=?,categorias_id=? WHERE id=?")) {
			ps.setString(1, libro.getSku());
			ps.setString(2, libro.getNombre());
			ps.setString(3, libro.getDescripcion());
			ps.setString(4, libro.getAutor());
			ps.setString(5, libro.getImagen());
			ps.setBigDecimal(6, libro.getPrecio());
			ps.setInt(7, libro.getDescuento());
			ps.setLong(8, libro.getCategoriaLibro().getId());
			ps.setLong(9, libro.getId());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido editar el libro: " + libro, e);
		}

	}

	public static void borrarLibro(Long id) {

		try (Connection con = obtenerConexion();
				PreparedStatement ps = con.prepareStatement("DELETE FROM libros WHERE id=?")) {
			ps.setLong(1, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido borrar el libro con id: " + id, e);
		}

	}

}

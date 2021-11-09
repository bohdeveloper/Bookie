package com.bookie.accesodatos.admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bookie.accesodatos.AccesoDatosException;
import com.bookie.modelos.admin.ImagenUsuario;

public class ImagenUsuarioDao {

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

	public static Iterable<ImagenUsuario> todasImagenesUsuarios() {

		try (Connection con = obtenerConexion();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM imagenes_usuarios")) {
			ArrayList<ImagenUsuario> imgUsuarios = new ArrayList<ImagenUsuario>();

			ImagenUsuario imgUsuario;

			while (rs.next()) {
				imgUsuario = new ImagenUsuario(rs.getString("nombre"), rs.getString("foto"));
				imgUsuarios.add(imgUsuario);
			}
			return imgUsuarios;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido obtener las imagenes de usuarios", e);
		}
	}

	public static void añadirImagenUsuario(ImagenUsuario imgUsuario) {
		try (Connection con = obtenerConexion();
				PreparedStatement ps = con
						.prepareStatement("INSERT INTO imagenes_usuarios (nombre, foto) VALUES(?,?)")) {
			ps.setString(1, imgUsuario.getNombre());
			ps.setString(2, imgUsuario.getFoto());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido añadir la imagen de usuario: " + imgUsuario, e);
		}

	}

	public static void borrarImagenUsuario(Long id) {
		try (Connection con = obtenerConexion();
				PreparedStatement ps = con.prepareStatement("DELETE FROM imagenes_usuarios WHERE id=?")) {
			ps.setLong(1, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido borrar la imagen de usuario con id: " + id, e);
		}

	}

}

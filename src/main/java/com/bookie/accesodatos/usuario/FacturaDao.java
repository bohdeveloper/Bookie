package com.bookie.accesodatos.usuario;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.bookie.accesodatos.AccesoDatosException;
import com.bookie.modelos.admin.Cliente;
import com.bookie.modelos.admin.Libro;
import com.bookie.modelos.usuario.Factura;

public class FacturaDao {

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

	public static Iterable<Factura> todasFacturas() {

		try (Connection con = obtenerConexion();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM facturas f JOIN clientes  c ON c.id = f.clientes_id")) {

			ArrayList<Factura> facturas = new ArrayList<Factura>();

			Factura factura;

			while (rs.next()) {
				factura = new Factura(rs.getLong("f.id"), rs.getTimestamp("f.fecha_exp"),
						rs.getBigDecimal("f.importe"));
				factura.setCliente(new Cliente(rs.getLong("c.id"), rs.getString("c.nombre"),
						rs.getString("c.apellidos"), rs.getString("c.email"), rs.getString("c.telefono"),
						rs.getString("c.domicilio"), rs.getString("c.ciudad"), rs.getString("c.pais")));
			}
			return facturas;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido obtener las facturas", e);
		}
	}

	public static Factura porIdFactura(Long id) {

		try {
			Connection con = obtenerConexion();
			PreparedStatement ps = con.prepareStatement(
					"SELECT * FROM facturas f JOIN clientes  c ON c.id = f.clientes_id WHERE f.id = ?");
			ps.setLong(1, id);

			ResultSet rs = ps.executeQuery();

			Factura factura = null;

			while (rs.next()) {
				factura = new Factura(rs.getLong("f.id"), rs.getTimestamp("f.fecha_exp"),
						rs.getBigDecimal("f.importe"));
				factura.setCliente(new Cliente(rs.getLong("c.id"), rs.getString("c.nombre"),
						rs.getString("c.apellidos"), rs.getString("c.email"), rs.getString("c.telefono"),
						rs.getString("c.domicilio"), rs.getString("c.ciudad"), rs.getString("c.pais")));
			}

			return factura;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha encontrado la factura con id: " + id, e);
		}

	}

	public static void añadirFactura(Timestamp fecha_exp, BigDecimal total, Long clienteID, Factura factura) {

		try (Connection con = obtenerConexion();) {
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO facturas (fecha_exp, importe, clientes_id) VALUES (?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			ps.setTimestamp(1, fecha_exp);
			ps.setBigDecimal(2, total);
			ps.setLong(3, clienteID);

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			rs.next();

			Long facturaID = rs.getLong(1);

			ps = con.prepareStatement("INSERT INTO libros_has_facturas (facturas_id, libros_id) VALUES (?,?)");

			ps.setLong(1, facturaID);

			for (Libro libro : factura.getLibrosFactura()) {
				ps.setLong(2, libro.getId());

				ps.executeUpdate();
			}

		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido añadir la factura: " + factura, e);
		}

	}

	public static void borrarFactura(Long id) {

		try (Connection con = obtenerConexion();
				PreparedStatement ps = con.prepareStatement("DELETE FROM facturas WHERE id=?")) {
			ps.setLong(1, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido borrar la factura con id: " + id, e);
		}

	}

}

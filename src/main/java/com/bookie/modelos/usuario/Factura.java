package com.bookie.modelos.usuario;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import com.bookie.modelos.admin.Cliente;
import com.bookie.modelos.admin.Libro;

public class Factura {

	static TreeMap<Long, Libro> librosFactura = new TreeMap<>();
	private Long id;
	private Timestamp fecha_exp;
	private BigDecimal importe;
	private Cliente cliente;

	public Factura(Long id, Timestamp fecha_exp, BigDecimal importe) {
		super();
		setId(id);
		this.fecha_exp = fecha_exp;
		this.importe = importe;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Timestamp getFecha_exp() {
		return fecha_exp;
	}

	public void setFecha_exp(Timestamp fecha_exp) {
		this.fecha_exp = fecha_exp;
	}

	public BigDecimal getImporte() {
		BigDecimal total = BigDecimal.ZERO;

		for (Libro libro : librosFactura.values()) {
			if (libro.getPrecio() != null) {
				total = total.add(libro.getPrecio());
			}
		}

		return total;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public Iterable<Libro> getLibrosFactura() {
		return librosFactura.values();
	}

	public void añadirLibroFactura(Libro libro) {
		librosFactura.put(libro.getId(), libro);
	}

	public static void borrarLibroFactura(Long id, HttpSession session) {

		librosFactura.remove(id);
		if (librosFactura.size() == 0) {
			session.removeAttribute("factura");
			session.removeAttribute("totalFactura");
			session.removeAttribute("cuantosLibrosFactura");
		}
	}

	public int contarLibrosFactura() {
		return librosFactura.size();
	}

	public void romperFactura() {
		librosFactura.clear();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha_exp == null) ? 0 : fecha_exp.hashCode());
		result = prime * result + ((importe == null) ? 0 : importe.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Factura other = (Factura) obj;
		if (fecha_exp == null) {
			if (other.fecha_exp != null)
				return false;
		} else if (!fecha_exp.equals(other.fecha_exp))
			return false;
		if (importe == null) {
			if (other.importe != null)
				return false;
		} else if (!importe.equals(other.importe))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Factura [fecha_exp=" + fecha_exp + ", importe=" + importe + "]";
	}

}

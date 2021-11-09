package com.bookie.modelos.admin;

import java.util.TreeMap;

public class RolUsuario {

	private Long id;
	private String nombre, descripcion;

	private TreeMap<String, String> errores = new TreeMap<>();

	public RolUsuario(Long id, String nombre, String descripcion) {
		super();
		setId(id);
		setNombre(nombre);
		setDescripcion(descripcion);
	}

	public TreeMap<String, String> getErrores() {
		return errores;
	}

	public void setErrores(TreeMap<String, String> errores) {
		this.errores = errores;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre == null || nombre.trim().length() == 0) {
			errores.put("nombre", "No se admite un nombre vacío");
		}
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		if (descripcion == null || descripcion.trim().length() == 0) {
			errores.put("descripcion", "No se admite una descripción vacía");
		}
		this.descripcion = descripcion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		RolUsuario other = (RolUsuario) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RolUsuario [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}

}

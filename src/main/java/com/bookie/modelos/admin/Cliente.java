package com.bookie.modelos.admin;

import java.sql.Date;
import java.util.TreeMap;

public class Cliente {

	private Long id;
	private String nombre, apellidos, email, telefono, sexo, domicilio, ciudad, pais;
	private Date fecha_nacimiento;
	private Usuario usuario;

	private TreeMap<String, String> errores = new TreeMap<>();

	public Cliente(Long id, String nombre, String apellidos, String email, String telefono, String domicilio, String ciudad, String pais) {
		super();
		setId(id);
		setNombre(nombre);
		setApellidos(apellidos);
		setEmail(email);
		setTelefono(telefono);
		setDomicilio(domicilio);
		setCiudad(ciudad);
		setPais(pais);
	}
	
	public Cliente(Long id, String nombre, String apellidos, String email, String telefono, String sexo,
			String domicilio, String ciudad, String pais, String sFecha_nacimiento) {
		super();
		setId(id);
		setNombre(nombre);
		setApellidos(apellidos);
		setEmail(email);
		setTelefono(telefono);
		setSexo(sexo);
		setDomicilio(domicilio);
		setCiudad(ciudad);
		setPais(pais);
		setFecha_nacimiento(sFecha_nacimiento);
	}

	public Cliente(Long id, String nombre, String apellidos, String email, String telefono, String sexo,
			String domicilio, String ciudad, String pais, Date fecha_nacimiento) {
		super();
		setId(id);
		setNombre(nombre);
		setApellidos(apellidos);
		setEmail(email);
		setTelefono(telefono);
		setSexo(sexo);
		setDomicilio(domicilio);
		setCiudad(ciudad);
		setPais(pais);
		setFecha_nacimiento(fecha_nacimiento);
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
			return;
		}
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		if (apellidos == null || apellidos.trim().length() == 0) {
			errores.put("apellidos", "No se admiten los apellidos vacíos");
			return;
		}
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		if (domicilio == null || domicilio.trim().length() == 0) {
			errores.put("domicilio", "No se admite el domicilio vacío");
			return;
		}
		this.domicilio = domicilio;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		if (ciudad == null || ciudad.trim().length() == 0) {
			errores.put("ciudad", "No se admite la ciudad vacía");
			return;
		}
		this.ciudad = ciudad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		if (pais == null || pais.trim().length() == 0) {
			errores.put("pais", "No se admite el país vacío");
			return;
		}
		this.pais = pais;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(String sFecha_nacimiento) {
		if (sFecha_nacimiento == null || sFecha_nacimiento.trim().length() == 0) {
			errores.put("fecha_nacimiento", "No se admite una fecha de nacimiento vacía");
			return;
		}
		Date fecha_nacimiento = Date.valueOf(sFecha_nacimiento);
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		if (fecha_nacimiento == null) {
			errores.put("fecha_nacimiento", "No se admite una fecha de nacimiento vacía");
			return;
		}
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result + ((ciudad == null) ? 0 : ciudad.hashCode());
		result = prime * result + ((domicilio == null) ? 0 : domicilio.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fecha_nacimiento == null) ? 0 : fecha_nacimiento.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Cliente other = (Cliente) obj;
		if (apellidos == null) {
			if (other.apellidos != null)
				return false;
		} else if (!apellidos.equals(other.apellidos))
			return false;
		if (ciudad == null) {
			if (other.ciudad != null)
				return false;
		} else if (!ciudad.equals(other.ciudad))
			return false;
		if (domicilio == null) {
			if (other.domicilio != null)
				return false;
		} else if (!domicilio.equals(other.domicilio))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fecha_nacimiento == null) {
			if (other.fecha_nacimiento != null)
				return false;
		} else if (!fecha_nacimiento.equals(other.fecha_nacimiento))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		if (sexo == null) {
			if (other.sexo != null)
				return false;
		} else if (!sexo.equals(other.sexo))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email + ", telefono=" + telefono
				+ ", sexo=" + sexo + ", domicilio=" + domicilio + ", ciudad=" + ciudad + ", pais=" + pais
				+ ", fecha_nacimiento=" + fecha_nacimiento + ", usuario=" + usuario + "]";
	}

}

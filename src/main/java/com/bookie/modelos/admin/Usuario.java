package com.bookie.modelos.admin;

import java.math.BigDecimal;
import java.util.TreeMap;

public class Usuario {

	private Long id;
	private String nombre_usuario, email, password, password1, password2, sexo;
	private BigDecimal saldo;
	private RolUsuario rolUsuario;

	private TreeMap<String, String> errores = new TreeMap<>();

	public Usuario(String email, String password) {
		super();
		setEmail(email);
		setPassword(password);
	}

	public Usuario(Long id, String nombre_usuario, String email, String password, String sexo,
			BigDecimal saldo) {
		super();
		setId(id);
		setNombre_usuario(nombre_usuario);
		setEmail(email);
		setPassword(password);
		setSexo(sexo);
		setSaldo(saldo);
	}

	public Usuario(Long id, String nombre_usuario, String email, String password1, String password2,
			String sexo, BigDecimal saldo) {
		super();
		setId(id);
		setNombre_usuario(nombre_usuario);
		setEmail(email);
		setPassword1(password1);
		setPassword2(password2);
		setSexo(sexo);
		setSaldo(saldo);
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

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		if (nombre_usuario == null || nombre_usuario.trim().length() == 0) {
			errores.put("nombre_usuario", "No se admite un nombre de usuario vacío");
			return;
		}
		this.nombre_usuario = nombre_usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email == null || email.trim().length() == 0) {
			errores.put("email", "No se admite un correo electrónico vacío");
			return;
		}
		this.email = email;
	}

	public void setPassword1(String password1) {
		if (password1 == null || password1.trim().length() == 0) {
			errores.put("password1", "No se admite una contraseña vacía");
			return;
		}
		this.password1 = password1;
	}

	public void setPassword2(String password2) {
		if (password2 == null || password2.trim().length() == 0) {
			errores.put("password1", "No se admite una contraseña vacía");
			errores.put("password2", "Tienes que repetir la contraseña");
			return;
		}
		if (password1.equals(password2)) {
			if (errores.size() != 0) {
				errores.put("password1", "Vuelve a añadir tu contraseña");
				errores.put("password2", "Vuelve a repetir tu contraseña");
			} else {
				this.password2 = password2;
				this.password = password2;
				return;
			}
		} else {
			errores.put("password1", "Las contraseñas no coinciden");
			errores.put("password2", "Las contraseñas no coinciden");
			return;
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public RolUsuario getRolUsuario() {
		return rolUsuario;
	}

	public void setRolUsuario(RolUsuario rolUsuario) {
		this.rolUsuario = rolUsuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((errores == null) ? 0 : errores.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre_usuario == null) ? 0 : nombre_usuario.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((password1 == null) ? 0 : password1.hashCode());
		result = prime * result + ((password2 == null) ? 0 : password2.hashCode());
		result = prime * result + ((rolUsuario == null) ? 0 : rolUsuario.hashCode());
		result = prime * result + ((saldo == null) ? 0 : saldo.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
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
		Usuario other = (Usuario) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (errores == null) {
			if (other.errores != null)
				return false;
		} else if (!errores.equals(other.errores))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre_usuario == null) {
			if (other.nombre_usuario != null)
				return false;
		} else if (!nombre_usuario.equals(other.nombre_usuario))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (password1 == null) {
			if (other.password1 != null)
				return false;
		} else if (!password1.equals(other.password1))
			return false;
		if (password2 == null) {
			if (other.password2 != null)
				return false;
		} else if (!password2.equals(other.password2))
			return false;
		if (rolUsuario == null) {
			if (other.rolUsuario != null)
				return false;
		} else if (!rolUsuario.equals(other.rolUsuario))
			return false;
		if (saldo == null) {
			if (other.saldo != null)
				return false;
		} else if (!saldo.equals(other.saldo))
			return false;
		if (sexo == null) {
			if (other.sexo != null)
				return false;
		} else if (!sexo.equals(other.sexo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre_usuario=" + nombre_usuario + ", email=" + email + ", password1="
				+ password1 + ", password2=" + password2 + ", saldo=" + saldo
				+ ", rolUsuario=" + rolUsuario + "]";
	}

}

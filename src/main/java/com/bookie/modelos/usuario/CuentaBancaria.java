package com.bookie.modelos.usuario;

import java.sql.Timestamp;

import com.bookie.modelos.admin.Cliente;

public class CuentaBancaria {

	private String titular, numero_cuenta;
	private int numero_tarjeta, cvc;
	private Timestamp fecha_caducidad;
	private Cliente cliente;

	public CuentaBancaria(String titular, String numero_cuenta, int numero_tarjeta, int cvc,
			Timestamp fecha_caducidad) {
		super();
		setTitular(titular);
		setNumero_cuenta(numero_cuenta);
		setNumero_tarjeta(numero_tarjeta);
		setCvc(cvc);
		setFecha_caducidad(fecha_caducidad);
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getNumero_cuenta() {
		return numero_cuenta;
	}

	public void setNumero_cuenta(String numero_cuenta) {
		this.numero_cuenta = numero_cuenta;
	}

	public int getNumero_tarjeta() {
		return numero_tarjeta;
	}

	public void setNumero_tarjeta(int numero_tarjeta) {
		this.numero_tarjeta = numero_tarjeta;
	}

	public int getCvc() {
		return cvc;
	}

	public void setCvc(int cvc) {
		this.cvc = cvc;
	}

	public Timestamp getFecha_caducidad() {
		return fecha_caducidad;
	}

	public void setFecha_caducidad(Timestamp fecha_caducidad) {
		this.fecha_caducidad = fecha_caducidad;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + cvc;
		result = prime * result + ((fecha_caducidad == null) ? 0 : fecha_caducidad.hashCode());
		result = prime * result + ((numero_cuenta == null) ? 0 : numero_cuenta.hashCode());
		result = prime * result + numero_tarjeta;
		result = prime * result + ((titular == null) ? 0 : titular.hashCode());
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
		CuentaBancaria other = (CuentaBancaria) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (cvc != other.cvc)
			return false;
		if (fecha_caducidad == null) {
			if (other.fecha_caducidad != null)
				return false;
		} else if (!fecha_caducidad.equals(other.fecha_caducidad))
			return false;
		if (numero_cuenta == null) {
			if (other.numero_cuenta != null)
				return false;
		} else if (!numero_cuenta.equals(other.numero_cuenta))
			return false;
		if (numero_tarjeta != other.numero_tarjeta)
			return false;
		if (titular == null) {
			if (other.titular != null)
				return false;
		} else if (!titular.equals(other.titular))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CuentaBancaria [titular=" + titular + ", numero_cuenta=" + numero_cuenta + ", numero_tarjeta="
				+ numero_tarjeta + ", cvc=" + cvc + ", fecha_caducidad=" + fecha_caducidad + ", cliente=" + cliente
				+ "]";
	}

}

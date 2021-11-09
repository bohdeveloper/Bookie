package com.bookie.modelos.admin;

import java.math.BigDecimal;
import java.util.TreeMap;

public class Libro {

	private Long id;
	private String sku, nombre, descripcion, autor, imagen;
	private BigDecimal precio;
	private int descuento;
	private CategoriaLibro categoriaLibro;

	private TreeMap<String, String> errores = new TreeMap<>();

	public Libro(Long id, String sku, String nombre, String descripcion, String autor, String imagen, String precio,
			String descuento) {
		super();
		setId(id);
		setSku(sku);
		setNombre(nombre);
		setDescripcion(descripcion);
		setAutor(autor);
		setImagen(imagen);
		setPrecio(precio);
		setDescuento(descuento);
	}

	public CategoriaLibro getCategoriaLibro() {
		return categoriaLibro;
	}

	public void setCategoriaLibro(CategoriaLibro categoriaLibro) {
		if(categoriaLibro.getId() == 0) {
			errores.put("categoria", "Elige una categoría para el libro");
		}
		this.categoriaLibro = categoriaLibro;
	}

	public Libro(Long id, String sku, String nombre, String descripcion, String autor, String imagen, BigDecimal precio,
			int descuento) {
		super();
		setId(id);
		setSku(sku);
		setNombre(nombre);
		setDescripcion(descripcion);
		setAutor(autor);
		setImagen(imagen);
		setPrecio(precio);
		setDescuento(descuento);
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

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		String tSku = sku.trim();
		if (tSku == null || tSku.length() == 0) {
			errores.put("sku", "No se admite un SKU vacío");
		} else if (tSku.length() < 7 || tSku.length() > 7) {
			errores.put("sku", "El SKU debe tener 7 caracteres");
		}
		this.sku = tSku.toUpperCase();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		String tNombre = nombre.trim();
		if (tNombre == null || tNombre.trim().length() == 0) {
			errores.put("nombre", "No se admite un nombre vacío");
		} else if (tNombre.length() < 2 || tNombre.length() > 50) {
			errores.put("nombre", "El nombre debe tener entre 2 y 50 caracteres");
		}
		this.nombre = tNombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		String tDescripcion = descripcion.trim();
		if (tDescripcion == null || tDescripcion.trim().length() == 0) {
			errores.put("descripcion", "No se admite una descripción vacía");
		} else if (tDescripcion.length() < 2 || tDescripcion.length() > 700) {
			errores.put("descripcion", "La descripción debe tener entre 2 y 700 caracteres");
		}
		this.descripcion = tDescripcion;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		String tAutor = autor.trim();
		if (tAutor == "" || tAutor.equals("")) {
			errores.put("autor", "No se admite un autor/a vacío/a");
		}
		this.autor = tAutor;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		String tImagen = imagen.trim();
		if (tImagen == null || tImagen.trim().length() == 0) {
			errores.put("imagen", "No se admite una imagen vacía");
		}
		this.imagen = tImagen;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		String tPrecio = precio.trim();
		try {
			setPrecio(new BigDecimal(tPrecio));
		} catch (Exception e) {
			errores.put("precio", "El precio debe ser un número con o sin decimales");
		}
	}

	public void setPrecio(BigDecimal precio) {
		if (precio != null && precio.compareTo(BigDecimal.ZERO) <= 0) {
			errores.put("precio", "El precio debe ser mayor que 0");
		}
		this.precio = precio;
	}

	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(String descuento) {
		String tDescuento = descuento.trim();
		int nDescuento = Integer.parseInt(tDescuento);
		if (nDescuento > 100) {
			errores.put("descuento", "El valor del descuento debe oscilar entre 1 y 100");
		}
		String sDescuento = String.valueOf(nDescuento);

		this.descuento = Integer.parseInt(sDescuento);
	}

	public void setDescuento(int descuento) {
		if (descuento > 100) {
			errores.put("descuento", "El valor del descuento debe oscilar entre 1 y 100");
		}
		this.descuento = descuento;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		result = prime * result + ((categoriaLibro == null) ? 0 : categoriaLibro.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + descuento;
		result = prime * result + ((errores == null) ? 0 : errores.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((imagen == null) ? 0 : imagen.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((precio == null) ? 0 : precio.hashCode());
		result = prime * result + ((sku == null) ? 0 : sku.hashCode());
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
		Libro other = (Libro) obj;
		if (autor == null) {
			if (other.autor != null)
				return false;
		} else if (!autor.equals(other.autor))
			return false;
		if (categoriaLibro == null) {
			if (other.categoriaLibro != null)
				return false;
		} else if (!categoriaLibro.equals(other.categoriaLibro))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (descuento != other.descuento)
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
		if (imagen == null) {
			if (other.imagen != null)
				return false;
		} else if (!imagen.equals(other.imagen))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (precio == null) {
			if (other.precio != null)
				return false;
		} else if (!precio.equals(other.precio))
			return false;
		if (sku == null) {
			if (other.sku != null)
				return false;
		} else if (!sku.equals(other.sku))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", sku=" + sku + ", nombre=" + nombre + ", descripcion=" + descripcion + ", autor="
				+ autor + ", imagen=" + imagen + ", precio=" + precio + ", descuento=" + descuento + "]";
	}

}

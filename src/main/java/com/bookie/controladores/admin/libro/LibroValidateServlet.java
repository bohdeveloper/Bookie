package com.bookie.controladores.admin.libro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookie.accesodatos.admin.CategoriaLibroDao;
import com.bookie.accesodatos.admin.LibroDao;
import com.bookie.modelos.admin.CategoriaLibro;
import com.bookie.modelos.admin.Libro;

@WebServlet("/libro/validate")
public class LibroValidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String EDITARLIBRO_JSP = "/WEB-INF/vistas/login-access/admin/editarLibro.jsp";
	private static final String CRUDLIBRO_JSP = "/WEB-INF/vistas/login-access/admin/crudLibro.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String sku = request.getParameter("sku");
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		String autor = request.getParameter("autor");
		String precio = request.getParameter("precio");
		String descuento = request.getParameter("descuento");
		String imagen = request.getParameter("imagen");
		String sCategoria = request.getParameter("categoria");
		Long categoria = Long.parseLong(sCategoria);

		String editado = request.getParameter("editado");

		if (editado != null) {

//			EDITADO CON ERRORES
			String sId = request.getParameter("id");
			Long id = Long.parseLong(sId);

			Libro libroEditado = new Libro(id, sku, nombre, descripcion, autor, imagen, precio, descuento);
			libroEditado.setCategoriaLibro(new CategoriaLibro(categoria, null, null));

			if (libroEditado.getErrores().size() != 0) {
				request.setAttribute("libro", libroEditado);
				request.setAttribute("libros", LibroDao.todosLibros());
				request.setAttribute("categorias", CategoriaLibroDao.todasCategorias());
				request.setAttribute("error", "Tienes fallos en el formulario");
				request.setAttribute("tipo", "danger");
				request.getRequestDispatcher(EDITARLIBRO_JSP).forward(request, response);
				return;

			} else {

//				EDITADO SIN ERRORES
				request.setAttribute("libro", libroEditado);
				LibroDao.editarLibro(libroEditado);
				request.setAttribute("libros", LibroDao.todosLibros());
				request.setAttribute("categorias", CategoriaLibroDao.todasCategorias());
				request.setAttribute("correcto", "El libro se ha editado correctamente");
				request.setAttribute("tipo", "success");
				request.getRequestDispatcher(CRUDLIBRO_JSP).forward(request, response);
				return;

			}

		} else {

//			NUEVO LIBRO CON ERRORES
			Libro libro = new Libro(null, sku, nombre, descripcion, autor, imagen, precio, descuento);
			libro.setCategoriaLibro(new CategoriaLibro(categoria, null, null));
			
			Libro libroComprobado = combrobarLibro(libro);

			if (libro.getErrores().size() != 0) {
				request.setAttribute("libro", libro);
				request.setAttribute("libros", LibroDao.todosLibros());
				request.setAttribute("categorias", CategoriaLibroDao.todasCategorias());
				request.setAttribute("error", "Tienes fallos en el formulario");
				request.setAttribute("tipo", "danger");
				request.getRequestDispatcher(CRUDLIBRO_JSP).forward(request, response);
				return;

			} else {

//				NUEVO LIBRO SIN ERRORES
				if (libroComprobado == null) {
					request.setAttribute("libro", libro);
					LibroDao.añadirLibro(libro);
					request.setAttribute("libros", LibroDao.todosLibros());
					request.setAttribute("categorias", CategoriaLibroDao.todasCategorias());
					request.setAttribute("correcto", "El libro se ha añadido a la biblioteca");
					request.setAttribute("tipo", "success");
					request.getRequestDispatcher(CRUDLIBRO_JSP).forward(request, response);
					return;

				} else {
					request.setAttribute("libro", libro);
					request.setAttribute("libros", LibroDao.todosLibros());
					request.setAttribute("categorias", CategoriaLibroDao.todasCategorias());
					request.setAttribute("error", "El libro ya existe");
					request.setAttribute("tipo", "warning");
					request.getRequestDispatcher(CRUDLIBRO_JSP).forward(request, response);
					return;
				}
			}
		}
	}

	private Libro combrobarLibro(Libro libro) {
		Libro libroEncontrado = LibroDao.porSkuLibro(libro.getSku());

		if (libroEncontrado != null && libroEncontrado.getSku().equals(libro.getSku())) {
			return libroEncontrado;
		} else {
			return null;
		}

	}

}

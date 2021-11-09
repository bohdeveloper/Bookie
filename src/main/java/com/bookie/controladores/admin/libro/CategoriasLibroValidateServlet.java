package com.bookie.controladores.admin.libro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookie.accesodatos.admin.CategoriaLibroDao;
import com.bookie.modelos.admin.CategoriaLibro;

@WebServlet("/libro/categoria/validate")
public class CategoriasLibroValidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String EDITARCATEGORIALIBRO_JSP = "/WEB-INF/vistas/login-access/admin/editarCategoriaLibro.jsp";
	private static final String CRUDCATEGORIASLIBRO_JSP = "/WEB-INF/vistas/login-access/admin/crudCategoriasLibro.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");

		String editado = request.getParameter("editado");

		if (editado != null) {

			String sId = request.getParameter("id");
			Long id = Long.parseLong(sId);
			
//			EDITADO CON ERRORES

			CategoriaLibro categoriaLibroEditada = new CategoriaLibro(id, nombre, descripcion);

			if (categoriaLibroEditada.getErrores().size() != 0) {
				request.setAttribute("categoria", categoriaLibroEditada);
				request.setAttribute("categorias", CategoriaLibroDao.todasCategorias());
				request.setAttribute("error", "Tienes fallos en el formulario");
				request.setAttribute("tipo", "danger");
				request.getRequestDispatcher(EDITARCATEGORIALIBRO_JSP).forward(request, response);
				return;
			} else {

//				EDITADO SIN ERRORES
				request.setAttribute("categoria", categoriaLibroEditada);
				CategoriaLibroDao.editarCategoriaLibro(categoriaLibroEditada);
				request.setAttribute("categorias", CategoriaLibroDao.todasCategorias());
				request.setAttribute("correcto", "La categoría se ha editado correctamente");
				request.setAttribute("tipo", "success");
				request.getRequestDispatcher(CRUDCATEGORIASLIBRO_JSP).forward(request, response);
				return;
			}

		} else {

//			NUEVO ROL CON ERRORES
			CategoriaLibro categoriaLibro = new CategoriaLibro(null, nombre, descripcion);

			CategoriaLibro categoriaLibroComprobada = combrobarCategoria(categoriaLibro);

			if (categoriaLibro.getErrores().size() != 0) {
				request.setAttribute("categoria", categoriaLibro);
				request.setAttribute("categorias", CategoriaLibroDao.todasCategorias());
				request.setAttribute("error", "Tienes fallos en el formulario");
				request.setAttribute("tipo", "danger");
				request.getRequestDispatcher(CRUDCATEGORIASLIBRO_JSP).forward(request, response);
				return;

			} else {
				
//				NUEVO ROL SIN ERRORES
				if (categoriaLibroComprobada == null) {
					request.setAttribute("categoria", categoriaLibro);
					CategoriaLibroDao.añadirCategoriaLibro(categoriaLibro);
					request.setAttribute("categorias", CategoriaLibroDao.todasCategorias());
					request.setAttribute("correcto", "La categoría se ha añadido correctamente");
					request.setAttribute("tipo", "success");
					request.getRequestDispatcher(CRUDCATEGORIASLIBRO_JSP).forward(request, response);
					return;

				} else {
					request.setAttribute("categoria", categoriaLibro);
					request.setAttribute("categorias", CategoriaLibroDao.todasCategorias());
					request.setAttribute("error", "La categoría ya existe");
					request.setAttribute("tipo", "warning");
					request.getRequestDispatcher(CRUDCATEGORIASLIBRO_JSP).forward(request, response);
					return;
				}
			}
		}
	}

	private CategoriaLibro combrobarCategoria(CategoriaLibro categoriaLibro) {
		CategoriaLibro categoriaLibroEncontrada = CategoriaLibroDao.porNombreCategoriaLibro(categoriaLibro.getNombre());

		if (categoriaLibroEncontrada != null && categoriaLibroEncontrada.getNombre().equals(categoriaLibro.getNombre())) {
			return categoriaLibroEncontrada;
		} else {
			return null;
		}

	}

}

package com.bookie.controladores.admin.libro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookie.accesodatos.admin.CategoriaLibroDao;

@WebServlet("/admin/borrar/categorias/libro")
public class CategoriasLibroBorrarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sId = request.getParameter("id");

		Long id = Long.parseLong(sId);

		CategoriaLibroDao.borrarCategoriaLibro(id);

		request.setAttribute("categorias", CategoriaLibroDao.todasCategorias());
		
		request.setAttribute("mensaje", "La categor�a  se a borrado correctamente");
		request.setAttribute("tipo", "success");
		request.getRequestDispatcher("/WEB-INF/vistas/login-access/admin/crudCategoriasLibro.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

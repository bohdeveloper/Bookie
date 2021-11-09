package com.bookie.controladores.admin.libro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookie.accesodatos.admin.CategoriaLibroDao;
import com.bookie.modelos.admin.CategoriaLibro;

@WebServlet("/admin/editar/categorias/libro")
public class CategoriasLibroEditarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sId = request.getParameter("id");
		
		Long id = Long.parseLong(sId);

		CategoriaLibro categoriaLibro;

		categoriaLibro = CategoriaLibroDao.porIdCategoriaLibro(id);
		
		boolean volver = true;
		request.setAttribute("volver", volver);

		request.setAttribute("categoria", categoriaLibro);

		request.getRequestDispatcher("/WEB-INF/vistas/login-access/admin/editarCategoriasLibro.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

package com.bookie.controladores.admin.libro;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookie.accesodatos.admin.LibroDao;

@WebServlet("/admin/listado/libros")
public class ListadoLibrosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean volver = true;
		request.setAttribute("volver", volver);
		request.setAttribute("libros", LibroDao.todosLibros());
		request.getRequestDispatcher("/WEB-INF/vistas/login-access/admin/listadoLibros.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

package com.bookie.controladores.admin.libro;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookie.accesodatos.admin.LibroDao;
import com.bookie.modelos.admin.Libro;

@WebServlet("/info/libro")
public class LibroInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String sId = request.getParameter("id");

		Long id = Long.parseLong(sId);

		Libro libro;

		libro = LibroDao.porIdLibro(id);

		request.setAttribute("libro", libro);

		boolean volver = true;
		request.setAttribute("volver", volver);

		request.getRequestDispatcher("/WEB-INF/vistas/login-access/admin/infoLibro.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

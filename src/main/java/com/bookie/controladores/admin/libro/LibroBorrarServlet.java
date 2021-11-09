package com.bookie.controladores.admin.libro;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookie.accesodatos.admin.LibroDao;

@WebServlet("/admin/borrar/libro")
public class LibroBorrarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sId = request.getParameter("id");

		Long id = Long.parseLong(sId);

		LibroDao.borrarLibro(id);

		request.setAttribute("libros", LibroDao.todosLibros());
		
		request.setAttribute("mensaje", "El libro se a borrado correctamente");
		request.setAttribute("tipo", "success");
		request.getRequestDispatcher("/WEB-INF/vistas/login-access/admin/crudLibro.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

package com.bookie.controladores.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookie.accesodatos.admin.LibroDao;
import com.bookie.modelos.usuario.Factura;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Factura factura = (Factura) session.getAttribute("factura");
		if (factura != null) {
			factura.romperFactura();
		}
		request.getSession().invalidate();

		request.setAttribute("libros", LibroDao.todosLibros());

		request.setAttribute("mensaje", "Tu sesión ha finalizado");
		request.setAttribute("tipo", "warning");

		boolean logout = true;
		request.setAttribute("logout", logout);
		request.getRequestDispatcher("/WEB-INF/vistas/index.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

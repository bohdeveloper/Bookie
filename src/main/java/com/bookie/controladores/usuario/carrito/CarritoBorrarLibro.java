package com.bookie.controladores.usuario.carrito;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookie.accesodatos.admin.LibroDao;
import com.bookie.modelos.usuario.Factura;

@WebServlet("/carrito/borrar/libro")
public class CarritoBorrarLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sId = request.getParameter("id");

		Long id = Long.parseLong(sId);

		HttpSession session = request.getSession();
		Factura.borrarLibroFactura(id, session);

		Factura factura = (Factura) session.getAttribute("factura");

		if (factura != null) {
			session.setAttribute("cuantosLibrosFactura", factura.contarLibrosFactura());
			session.setAttribute("totalFactura", factura.getImporte());

			request.setAttribute("mensaje", "El libro se a eliminado del carrito");
			request.setAttribute("tipo", "success");
			request.getRequestDispatcher("/WEB-INF/vistas/login-access/usuario/carrito.jsp").forward(request, response);
		} else {
			request.setAttribute("libros", LibroDao.todosLibros());
			request.setAttribute("mensaje", "No tienes libros en el carrito");
			request.setAttribute("tipo", "success");
			request.getRequestDispatcher("/WEB-INF/vistas/index.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

package com.bookie.controladores.usuario.carrito;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookie.accesodatos.admin.LibroDao;
import com.bookie.modelos.admin.Usuario;
import com.bookie.modelos.usuario.Factura;

@WebServlet("/comprar/libro")
public class ComprarLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");

		HttpSession session = request.getSession();

		Factura factura = (Factura) session.getAttribute("factura");

		if (factura == null) {
			Usuario usuario = (Usuario) session.getAttribute("usuario");
			if (usuario == null) {
				request.setAttribute("error", "No puedes comprar si no eres un usuario registrado");
				request.setAttribute("tipo", "danger");
				request.getRequestDispatcher("/WEB-INF/vistas/login.jsp").forward(request, response);
				return;
			}

			factura = new Factura(null, null, null);
			session.setAttribute("factura", factura);
		}

		factura.añadirLibroFactura(LibroDao.porIdLibro(Long.parseLong(id)));
		session.setAttribute("cuantosLibrosFactura", factura.contarLibrosFactura());
		request.setAttribute("cuantosLibrosFactura", factura.contarLibrosFactura());
		session.setAttribute("totalFactura", factura.getImporte());
		request.setAttribute("mensaje", "El libro se a añadido al carrito");
		request.setAttribute("tipo", "success");
		request.getRequestDispatcher("/WEB-INF/vistas/login-access/usuario/carrito.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

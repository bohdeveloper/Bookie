package com.bookie.controladores.registro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookie.accesodatos.admin.ClienteDao;
import com.bookie.modelos.admin.Cliente;
import com.bookie.modelos.admin.Usuario;

@WebServlet("/registro/cliente")
public class RegistroClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String REGISTRO_JSP = "/WEB-INF/vistas/login-access/usuario/registroCliente.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		Cliente clienteEnBD = buscarCliente(usuario.getId());

		if (clienteEnBD != null) {
			request.getSession().setAttribute("cliente", clienteEnBD);
			response.sendRedirect(request.getContextPath() + "/pagar/factura");
		} else {
			request.setAttribute("mensaje", "Añade los datos de facturación");
			request.setAttribute("tipo", "info");
			request.getRequestDispatcher(REGISTRO_JSP).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private Cliente buscarCliente(Long usuarioID) {
		Cliente clienteEncontrado = null;
		clienteEncontrado = ClienteDao.porIdUsuario(usuarioID);

		if (clienteEncontrado != null) {
			return clienteEncontrado;
		} else {
			return null;
		}
	}
}

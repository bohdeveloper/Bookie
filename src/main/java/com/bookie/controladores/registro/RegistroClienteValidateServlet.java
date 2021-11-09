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

@WebServlet("/registro/cliente/validate")
public class RegistroClienteValidateServlet extends HttpServlet {
	private static final String REGISTRO_JSP = "/WEB-INF/vistas/login-access/usuario/registroCliente.jsp";
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String sNombre = request.getParameter("nombre");
		String sApellidos = request.getParameter("apellidos");
		String sTelefono = request.getParameter("telefono");
		String sCiudad = request.getParameter("ciudad");
		String sPais = request.getParameter("pais");
		String sDomicilio = request.getParameter("domicilio");

		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		Cliente cliente = new Cliente(null, sNombre, sApellidos, usuario.getEmail(), sTelefono, sDomicilio, sCiudad,
				sPais);

		cliente.setUsuario(new Usuario(usuario.getId(), null, null, null, null, null, null));

		if (cliente.getErrores().size() != 0) {
			request.setAttribute("clienteFail", cliente);
			request.getRequestDispatcher(REGISTRO_JSP).forward(request, response);
			return;
		} else {
			ClienteDao.añadirCliente(cliente);
			request.setAttribute("mensaje", "Tus datos se han guardado correctamente");
			request.setAttribute("tipo", "success");
			response.sendRedirect(request.getContextPath() + "/pagar/factura");
		}
	}

}

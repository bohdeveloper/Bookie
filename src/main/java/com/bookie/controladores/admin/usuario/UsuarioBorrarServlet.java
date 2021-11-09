package com.bookie.controladores.admin.usuario;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookie.accesodatos.admin.ClienteDao;
import com.bookie.accesodatos.admin.RolUsuarioDao;
import com.bookie.accesodatos.admin.UsuarioDao;
import com.bookie.modelos.admin.Cliente;

@WebServlet("/admin/borrar/usuario")
public class UsuarioBorrarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String sId = request.getParameter("id");
		Long id = Long.parseLong(sId);

		Cliente cliente = ClienteDao.porIdUsuario(id);

		if (cliente != null) {
			System.out.println(cliente);
			System.out.println(id);
			ClienteDao.archivarCliente(id);
		}

		UsuarioDao.borrarUsuario(id);

		request.setAttribute("usuarios", UsuarioDao.todosUsuarios());
		request.setAttribute("roles", RolUsuarioDao.todosRolUsuarios());

		request.setAttribute("mensaje", "El usuario se a borrado correctamente");
		request.setAttribute("tipo", "success");
		request.getRequestDispatcher("/WEB-INF/vistas/login-access/admin/crudUsuario.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

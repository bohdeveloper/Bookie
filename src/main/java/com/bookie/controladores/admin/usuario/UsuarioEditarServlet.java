package com.bookie.controladores.admin.usuario;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookie.accesodatos.admin.RolUsuarioDao;
import com.bookie.accesodatos.admin.UsuarioDao;
import com.bookie.modelos.admin.Usuario;

@WebServlet("/admin/editar/usuario")
public class UsuarioEditarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sId = request.getParameter("id");

		Long id = Long.parseLong(sId);

		Usuario usuario;

		usuario = UsuarioDao.porIdUsuario(id);
		
		boolean volver = true;
		request.setAttribute("volver", volver);

		request.setAttribute("usuario", usuario);
		request.setAttribute("roles", RolUsuarioDao.todosRolUsuarios());

		request.getRequestDispatcher("/WEB-INF/vistas/login-access/admin/editarUsuario.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

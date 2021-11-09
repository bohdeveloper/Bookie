package com.bookie.controladores.admin.usuario;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookie.accesodatos.admin.RolUsuarioDao;
import com.bookie.accesodatos.admin.UsuarioDao;

@WebServlet("/admin/crud/usuario")
public class CrudUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean volver = true;
		request.setAttribute("volver", volver);
		request.setAttribute("roles", RolUsuarioDao.todosRolUsuarios());
		request.setAttribute("usuarios", UsuarioDao.todosUsuarios());
		request.getRequestDispatcher("/WEB-INF/vistas/login-access/admin/crudUsuario.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

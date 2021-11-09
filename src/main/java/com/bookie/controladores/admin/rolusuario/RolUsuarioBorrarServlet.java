package com.bookie.controladores.admin.rolusuario;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookie.accesodatos.admin.RolUsuarioDao;

@WebServlet("/admin/borrar/roles/usuario")
public class RolUsuarioBorrarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sId = request.getParameter("id");

		Long id = Long.parseLong(sId);

		RolUsuarioDao.borrarRolUsuario(id);

		request.setAttribute("roles", RolUsuarioDao.todosRolUsuarios());
		
		request.setAttribute("mensaje", "El rol se a borrado correctamente");
		request.setAttribute("tipo", "success");
		request.getRequestDispatcher("/WEB-INF/vistas/login-access/admin/crudRolesUsuario.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

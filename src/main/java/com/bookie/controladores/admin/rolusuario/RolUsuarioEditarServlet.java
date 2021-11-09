package com.bookie.controladores.admin.rolusuario;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookie.accesodatos.admin.RolUsuarioDao;
import com.bookie.modelos.admin.RolUsuario;

@WebServlet("/admin/editar/roles/usuario")
public class RolUsuarioEditarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sId = request.getParameter("id");
		
		Long id = Long.parseLong(sId);

		RolUsuario rolUsuario;

		rolUsuario = RolUsuarioDao.porIdRolUsuario(id);
		
		boolean volver = true;
		request.setAttribute("volver", volver);

		request.setAttribute("rol", rolUsuario);

		request.getRequestDispatcher("/WEB-INF/vistas/login-access/admin/editarRolUsuario.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

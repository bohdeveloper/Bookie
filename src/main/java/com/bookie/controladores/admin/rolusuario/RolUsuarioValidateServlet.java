package com.bookie.controladores.admin.rolusuario;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookie.accesodatos.admin.RolUsuarioDao;
import com.bookie.modelos.admin.RolUsuario;

@WebServlet("/usuario/rol/validate")
public class RolUsuarioValidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String EDITAROLUSUARIO_JSP = "/WEB-INF/vistas/login-access/admin/editarRolUsuario.jsp";
	private static final String CRUDROLES_JSP = "/WEB-INF/vistas/login-access/admin/crudRolesUsuario.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");

		String editado = request.getParameter("editado");

		if (editado != null) {

			String sId = request.getParameter("id");
			Long id = Long.parseLong(sId);
			
//			EDITADO CON ERRORES

			RolUsuario rolUsuarioEditado = new RolUsuario(id, nombre, descripcion);

			if (rolUsuarioEditado.getErrores().size() != 0) {
				request.setAttribute("rol", rolUsuarioEditado);
				request.setAttribute("roles", RolUsuarioDao.todosRolUsuarios());
				request.setAttribute("error", "Tienes fallos en el formulario");
				request.setAttribute("tipo", "danger");
				request.getRequestDispatcher(EDITAROLUSUARIO_JSP).forward(request, response);
				return;
			} else {

//				EDITADO SIN ERRORES
				request.setAttribute("rol", rolUsuarioEditado);
				RolUsuarioDao.editarRolUsuario(rolUsuarioEditado);
				request.setAttribute("roles", RolUsuarioDao.todosRolUsuarios());
				request.setAttribute("correcto", "El rol se ha editado correctamente");
				request.setAttribute("tipo", "success");
				request.getRequestDispatcher(CRUDROLES_JSP).forward(request, response);
				return;
			}

		} else {

//			NUEVO ROL CON ERRORES
			RolUsuario rolUsuario = new RolUsuario(null, nombre, descripcion);

			RolUsuario rolComprobado = combrobarRol(rolUsuario);

			if (rolUsuario.getErrores().size() != 0) {
				request.setAttribute("rol", rolUsuario);
				request.setAttribute("roles", RolUsuarioDao.todosRolUsuarios());
				request.setAttribute("error", "Tienes fallos en el formulario");
				request.setAttribute("tipo", "danger");
				request.getRequestDispatcher(CRUDROLES_JSP).forward(request, response);
				return;

			} else {
				
//				NUEVO ROL SIN ERRORES
				if (rolComprobado == null) {
					request.setAttribute("rol", rolUsuario);
					RolUsuarioDao.añadirRolUsuario(rolUsuario);
					request.setAttribute("roles", RolUsuarioDao.todosRolUsuarios());
					request.setAttribute("correcto", "El rol se ha añadido correctamente");
					request.setAttribute("tipo", "success");
					request.getRequestDispatcher(CRUDROLES_JSP).forward(request, response);
					return;

				} else {
					request.setAttribute("rol", rolUsuario);
					request.setAttribute("roles", RolUsuarioDao.todosRolUsuarios());
					request.setAttribute("error", "El rol ya existe");
					request.setAttribute("tipo", "warning");
					request.getRequestDispatcher(CRUDROLES_JSP).forward(request, response);
					return;
				}
			}
		}
	}

	private RolUsuario combrobarRol(RolUsuario rolUsuario) {
		RolUsuario rolEncontrado = RolUsuarioDao.porNombreRolUsuario(rolUsuario.getNombre());

		if (rolEncontrado != null && rolEncontrado.getNombre().equals(rolUsuario.getNombre())) {
			return rolEncontrado;
		} else {
			return null;
		}

	}

}

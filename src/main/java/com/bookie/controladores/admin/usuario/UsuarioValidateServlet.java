package com.bookie.controladores.admin.usuario;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookie.accesodatos.admin.RolUsuarioDao;
import com.bookie.accesodatos.admin.UsuarioDao;
import com.bookie.modelos.admin.RolUsuario;
import com.bookie.modelos.admin.Usuario;

@WebServlet("/usuario/validate")
public class UsuarioValidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String EDITARUSUARIO_JSP = "/WEB-INF/vistas/login-access/admin/editarUsuario.jsp";
	private static final String CRUDUSUARIO_JSP = "/WEB-INF/vistas/login-access/admin/crudUsuario.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String nombre_usuario = request.getParameter("nombre_usuario");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String sSaldo = request.getParameter("saldo");
		String sSexo = request.getParameter("sexo");
		BigDecimal saldo = new BigDecimal(sSaldo);
		String sRol = request.getParameter("rol");
		Long rol = Long.parseLong(sRol);

		String editado = request.getParameter("editado");

		if (editado != null) {

//			EDITADO CON ERRORES
			String sId = request.getParameter("id");
			Long id = Long.parseLong(sId);

			Usuario usuarioEditado = new Usuario(id, nombre_usuario, email, password, null, sSexo, saldo);
			usuarioEditado.setRolUsuario(new RolUsuario(rol, null, null));

			if (usuarioEditado.getErrores().size() != 0) {
				request.setAttribute("usuario", usuarioEditado);
				request.setAttribute("usuarios", UsuarioDao.todosUsuarios());
				request.setAttribute("roles", RolUsuarioDao.todosRolUsuarios());
				request.setAttribute("error", "Tienes fallos en el formulario");
				request.setAttribute("tipo", "danger");
				request.getRequestDispatcher(EDITARUSUARIO_JSP).forward(request, response);
				return;
			} else {
//				EDITADO SIN ERRORES
				request.setAttribute("usuario", usuarioEditado);
				UsuarioDao.editarUsuario(usuarioEditado);
				request.setAttribute("usuarios", UsuarioDao.todosUsuarios());
				request.setAttribute("roles", RolUsuarioDao.todosRolUsuarios());
				request.setAttribute("correcto", "El usuario se ha editado correctamente");
				request.setAttribute("tipo", "success");
				request.getRequestDispatcher(CRUDUSUARIO_JSP).forward(request, response);
				return;
			}

		} else {

//			NUEVO USUARIO CON ERRORES
			Usuario usuario = new Usuario(null, nombre_usuario, email, password, null, sSexo, saldo);
			usuario.setRolUsuario(new RolUsuario(rol, null, null));

			Usuario usuarioComprobado = comprobarUsuario(usuario);

			if (usuario.getErrores().size() != 0) {
				request.setAttribute("usuario", usuario);
				request.setAttribute("usuarios", UsuarioDao.todosUsuarios());
				request.setAttribute("roles", RolUsuarioDao.todosRolUsuarios());
				request.setAttribute("error", "Tienes fallos en el formulario");
				request.setAttribute("tipo", "danger");
				request.getRequestDispatcher(CRUDUSUARIO_JSP).forward(request, response);
				return;

			} else {

//				NUEVO USUARIO SIN ERRORES
				if (usuarioComprobado == null) {
					request.setAttribute("usuario", usuario);
					UsuarioDao.añadirUsuario(usuario);
					request.setAttribute("usuarios", UsuarioDao.todosUsuarios());
					request.setAttribute("roles", RolUsuarioDao.todosRolUsuarios());
					request.setAttribute("correcto", "El usuario se ha añadido correctamente");
					request.setAttribute("tipo", "success");
					request.getRequestDispatcher(CRUDUSUARIO_JSP).forward(request, response);
					return;

				} else {
					request.setAttribute("usuario", usuario);
					request.setAttribute("usuarios", UsuarioDao.todosUsuarios());
					request.setAttribute("roles", RolUsuarioDao.todosRolUsuarios());
					request.setAttribute("error", "El usuario ya existe");
					request.setAttribute("tipo", "warning");
					request.getRequestDispatcher(CRUDUSUARIO_JSP).forward(request, response);
					return;
				}
			}
		}
	}

	private Usuario comprobarUsuario(Usuario usuario) {
		Usuario usuarioEncontrado = UsuarioDao.porNombreUsuario(usuario.getNombre_usuario());

		if (usuarioEncontrado != null && usuarioEncontrado.getNombre_usuario().equals(usuario.getNombre_usuario())) {
			return usuarioEncontrado;
		} else {
			return null;
		}

	}

}

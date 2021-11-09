package com.bookie.controladores.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookie.accesodatos.admin.LibroDao;
import com.bookie.accesodatos.admin.UsuarioDao;
import com.bookie.modelos.admin.Usuario;

@WebServlet("/login/validate")
public class LoginValidateServlet extends HttpServlet {
	private static final String ADMIN_JSP = "/WEB-INF/vistas/login-access/admin/admin.jsp";
	private static final String USER_JSP = "/WEB-INF/vistas/login-access/usuariobsc/index.jsp";
	private static final String USERPRO_JSP = "/WEB-INF/vistas/login-access/usuariopro/index.jsp";
	private static final String LOGIN_JSP = "/WEB-INF/vistas/login.jsp";
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		Usuario usuario = new Usuario(email, password);

		Usuario usuarioValidado = validarUsuario(usuario);

		if (usuarioValidado != null) {
			if (usuarioValidado.getRolUsuario().getId() == 1) {

//				ADMINISTRADOR VALIDADO CON ERRORES
				if (usuario.getErrores().size() != 0) {
					request.setAttribute("usuarioFail", usuario);
					request.setAttribute("error", "Correo electrónico o contraseña incorrectos");
					request.setAttribute("tipo", "danger");
					request.getRequestDispatcher(LOGIN_JSP).forward(request, response);

//				ADMINISTRADOR VALIDADO
				} else {
					request.getSession().setAttribute("usuario", usuarioValidado);
					boolean admin = true;
					request.getSession().setAttribute("admin", admin);
					request.setAttribute("mensaje", "Has iniciado sesíon correctamente");
					request.setAttribute("tipo", "success");
					request.setAttribute("libros", LibroDao.todosLibros());
					request.getRequestDispatcher(ADMIN_JSP).forward(request, response);
				}

			} else if (usuarioValidado.getRolUsuario().getId() == 2) {

//				USUARIO BÁSICO VALIDADO CON ERRORES
				if (usuario.getErrores().size() != 0) {
					request.setAttribute("usuarioFail", usuario);
					request.setAttribute("error", "Correo electrónico o contraseña incorrectos");
					request.setAttribute("tipo", "danger");
					request.getRequestDispatcher(LOGIN_JSP).forward(request, response);

//				USUARIO BÁSICO VALIDADO
				} else {
					request.getSession().setAttribute("usuario", usuarioValidado);
					boolean user = true;
					request.getSession().setAttribute("user", user);
					request.setAttribute("mensaje", "Has iniciado sesíon correctamente");
					request.setAttribute("tipo", "success");
					request.setAttribute("libros", LibroDao.todosLibros());
					request.getRequestDispatcher(USER_JSP).forward(request, response);
				}

			} else if (usuarioValidado.getRolUsuario().getId() == 3) {

//				USUARIO PRO VALIDADO CON ERRORES
				if (usuario.getErrores().size() != 0) {
					request.setAttribute("usuarioFail", usuario);
					request.setAttribute("error", "Correo electrónico o contraseña incorrectos");
					request.setAttribute("tipo", "danger");
					request.getRequestDispatcher(LOGIN_JSP).forward(request, response);

//				USUARIO PRO VALIDADO
				} else {
					request.getSession().setAttribute("usuario", usuarioValidado);
					boolean userpro = true;
					request.getSession().setAttribute("userpro", userpro);
					request.setAttribute("mensaje", "Has iniciado sesíon correctamente");
					request.setAttribute("tipo", "success");
					request.setAttribute("libros", LibroDao.todosLibros());
					request.getRequestDispatcher(USERPRO_JSP).forward(request, response);
				}
			} else {
				request.setAttribute("error", "Fallo en la declaración de los roles");
				request.setAttribute("tipo", "danger");
				request.getRequestDispatcher(LOGIN_JSP).forward(request, response);
			}

//		USUARIO NO VALIDADO
		} else {
			request.setAttribute("usuarioFail", usuario);
			request.setAttribute("error",
					"Te has equivocado de usuario o tu usuario no existe, registrate para tener acceso");
			request.setAttribute("tipo", "danger");
			request.getRequestDispatcher(LOGIN_JSP).forward(request, response);
		}

	}

	private Usuario validarUsuario(Usuario usuario) {
		Usuario usuarioEncontrado = null;
		usuarioEncontrado = UsuarioDao.porEmailUsuario(usuario.getEmail());

		if (usuarioEncontrado != null && usuarioEncontrado.getPassword().equals(usuario.getPassword())) {
			return usuarioEncontrado;
		} else {
			return null;
		}

	}

}

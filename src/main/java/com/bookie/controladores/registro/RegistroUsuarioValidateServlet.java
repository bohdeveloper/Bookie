package com.bookie.controladores.registro;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookie.accesodatos.admin.ImagenUsuarioDao;
import com.bookie.accesodatos.admin.UsuarioDao;
import com.bookie.modelos.admin.ImagenUsuario;
import com.bookie.modelos.admin.RolUsuario;
import com.bookie.modelos.admin.Usuario;

@MultipartConfig
@WebServlet("/registro/usuario/validate")
public class RegistroUsuarioValidateServlet extends HttpServlet {
	private static final String REGISTRO_JSP = "/WEB-INF/vistas/login-access/usuario/registroUsuario.jsp";
	private static final String LOGIN_JSP = "/WEB-INF/vistas/login.jsp";
	private static final long serialVersionUID = 1L;

	private String rutaImg = "C:\\Users\\ohb_1\\git\\Bookie\\Bookie\\src\\main\\webapp\\img\\user\\users_photo";
	private File subidas = new File(rutaImg);
	private String[] extensiones = { ".ico", ".png", ".jpg", "jpeg" };

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String sNombre_usuario = request.getParameter("nombre_usuario");
		String sEmail = request.getParameter("email");
		String sPassword1 = request.getParameter("password1");
		String sPassword2 = request.getParameter("password2");
		String sSexo = request.getParameter("sexo");
		String sSaldo = request.getParameter("saldo");
		String sRol = request.getParameter("rol");

		BigDecimal saldo = new BigDecimal(sSaldo);
		Usuario usuario = new Usuario(null, sNombre_usuario, sEmail, sPassword1, sPassword2, sSexo, saldo);

		Long rol = Long.parseLong(sRol);
		usuario.setRolUsuario(new RolUsuario(rol, null, null));

		if (usuario.getErrores().size() != 0) {
			request.setAttribute("usuarioFail", usuario);
			request.getRequestDispatcher(REGISTRO_JSP).forward(request, response);
			return;
		} else {
			UsuarioDao.añadirUsuario(usuario);
			javax.servlet.http.Part part = request.getPart("imagen_usuario");
			if (isExtension(part.getSubmittedFileName(), extensiones)) {
				String foto = guardarArchivo(part, subidas);
				System.out.println("FOTO: "+foto);
				ImagenUsuario imagenUsuario = new ImagenUsuario(sNombre_usuario, foto);
				ImagenUsuarioDao.añadirImagenUsuario(imagenUsuario);
			}
			request.setAttribute("mensaje", "Te has registrado correctamente, ahora inicia sesión en tu nueva cuenta");
			request.setAttribute("tipo", "success");
			request.getRequestDispatcher(LOGIN_JSP).forward(request, response);
		}

	}

	private String guardarArchivo(javax.servlet.http.Part part, File rutaDescargas) {
		String rutaAbsoluta = "";

		try {
			Path path = Paths.get(part.getSubmittedFileName());
			String nombreArchivo = path.getFileName().toString();
			InputStream entrada = part.getInputStream();

			if (entrada != null) {
				File archivo = new File(rutaDescargas, nombreArchivo);
				rutaAbsoluta = archivo.getAbsolutePath();
				Files.copy(entrada, archivo.toPath());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rutaAbsoluta;
	}

	private boolean isExtension(String nombreArchivo, String[] extensiones) {
		for (String et : extensiones) {
			if (nombreArchivo.toLowerCase().endsWith(et)) {
				return true;
			}
		}
		return false;
	}

}

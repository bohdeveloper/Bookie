package com.bookie.controladores.usuario.saldo;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookie.accesodatos.admin.UsuarioDao;
import com.bookie.modelos.admin.Usuario;

@WebServlet("/usuario/sumar/saldo")
public class SumarSaldoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		String sSaldo = request.getParameter("saldo");

		BigDecimal saldoAsumar = new BigDecimal(sSaldo);

		BigDecimal saldoActual = UsuarioDao.porIdUsuarioSaldo(usuario.getId()).getSaldo();

		saldoActual = saldoActual.add(saldoAsumar);

		usuario.setSaldo(saldoActual);
		UsuarioDao.añadirSaldoUsuario(usuario, saldoActual);

		if (usuario.getRolUsuario().getId() == 2) {
			request.setAttribute("mensaje", "El saldo se a agregado a tu cuenta");
			request.setAttribute("tipo", "success");
			request.getRequestDispatcher("/WEB-INF/vistas/login-access/usuariobsc/index.jsp").forward(request,
					response);
			return;
		} else if (usuario.getRolUsuario().getId() == 3) {
			request.setAttribute("mensaje", "El saldo se a agregado a tu cuenta");
			request.setAttribute("tipo", "success");
			request.getRequestDispatcher("/WEB-INF/vistas/login-access/usuariopro/index.jsp").forward(request,
					response);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

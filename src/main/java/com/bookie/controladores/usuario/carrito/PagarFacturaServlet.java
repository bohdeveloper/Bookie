package com.bookie.controladores.usuario.carrito;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookie.accesodatos.admin.ClienteDao;
import com.bookie.accesodatos.usuario.FacturaDao;
import com.bookie.modelos.admin.Cliente;
import com.bookie.modelos.admin.Usuario;
import com.bookie.modelos.usuario.Factura;

@WebServlet("/pagar/factura")
public class PagarFacturaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		Cliente cliente = (Cliente) session.getAttribute("cliente");
		Factura factura = (Factura) session.getAttribute("factura");
		Date date = new Date();
		Timestamp fecha_exp = new Timestamp(date.getTime());
		BigDecimal total = (BigDecimal) session.getAttribute("totalFactura");

		if (cliente == null) {
			Cliente clienteEnBD = buscarCliente(usuario.getId());
			request.getSession().setAttribute("cliente", clienteEnBD);
//			System.out.println("Cliente en sesión: " + clienteEnBD);
//			System.out.println("Cliente ID: " + clienteEnBD.getId());
			
			FacturaDao.añadirFactura(fecha_exp, total, clienteEnBD.getId(), factura);
			factura.romperFactura();
			session.removeAttribute("factura");
			session.removeAttribute("totalFactura");
			session.removeAttribute("cuantosLibrosFactura");
			response.sendRedirect(request.getContextPath() + "/factura/pagada");
		} else {
			FacturaDao.añadirFactura(fecha_exp, total, cliente.getId(), factura);
			factura.romperFactura();
			session.removeAttribute("factura");
			session.removeAttribute("totalFactura");
			session.removeAttribute("cuantosLibrosFactura");
			response.sendRedirect(request.getContextPath() + "/factura/pagada");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private Cliente buscarCliente(Long usuarioID) {
		Cliente clienteEncontrado = null;
		clienteEncontrado = ClienteDao.porIdUsuario(usuarioID);

		if (clienteEncontrado != null) {
			return clienteEncontrado;
		} else {
			return null;
		}
	}

}

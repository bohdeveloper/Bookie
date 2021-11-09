package com.bookie.filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookie.modelos.admin.Usuario;

@WebFilter("/usuariobsc/*")
public class UserFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		if(usuario == null || usuario.getRolUsuario().getId() != 2) {
			request.setAttribute("mensaje", "No tienes acceso a este contenido");
			request.setAttribute("tipo", "danger");
			request.getRequestDispatcher("/index").forward(req, res);
			// res.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		chain.doFilter(request, response);
	}

}

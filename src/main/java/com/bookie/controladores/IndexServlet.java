package com.bookie.controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookie.accesodatos.admin.LibroDao;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if(request.getSession().getAttribute("admin") != null) {
			boolean admin = true;
			request.getSession().setAttribute("admin", admin);
			request.setAttribute("libros", LibroDao.todosLibros());
			request.getRequestDispatcher("/WEB-INF/vistas/index.jsp").forward(request, response);
			return;
			
		}else if(request.getSession().getAttribute("user") != null) {
			boolean user = true;
			request.getSession().setAttribute("user", user);
			request.setAttribute("libros", LibroDao.todosLibros());
			request.getRequestDispatcher("/WEB-INF/vistas/index.jsp").forward(request, response);
			return;
			
		}else if(request.getSession().getAttribute("userpro") != null) {
			boolean userpro = true;
			request.getSession().setAttribute("userpro", userpro);
			request.setAttribute("libros", LibroDao.todosLibros());
			request.getRequestDispatcher("/WEB-INF/vistas/index.jsp").forward(request, response);
			return;
			
		}  else {
			boolean index = true;
			request.setAttribute("index", index);
			request.setAttribute("libros", LibroDao.todosLibros());
			request.getRequestDispatcher("/WEB-INF/vistas/index.jsp").forward(request, response);
			return;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

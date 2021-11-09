package com.bookie.controladores.usuario.carrito;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.bookie.modelos.Mensajero;

@WebServlet("/factura/pagada")
public class PagadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		    String destinatario =  "ohb.seven@gmail.com"; 
//		    String asunto = "Correo de prueba enviado desde Java";
//		    String cuerpo = "Esta es una prueba de correo...";
//
//		   Mensajero.enviarConGMail(destinatario, asunto, cuerpo);
	
		request.getRequestDispatcher("/WEB-INF/vistas/login-access/usuario/pagado.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

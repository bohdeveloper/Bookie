<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/inc/header.jsp"%>

<article class="mb-5 mt-5 overflow">
	<table class="table table-striped">
		<thead>
			<tr>
				<th scope="col">Sku</th>
				<th scope="col">Titulo</th>
				<th scope="col">Descripción</th>
				<th scope="col">Autor/a</th>
				<th scope="col">Imagen</th>
				<th scope="col">Precio</th>
				<th scope="col">Descuento</th>
				<th scope="col">Opciones</th>
			</tr>
		</thead>
		<c:forEach items="${libros}" var="l">
			<tbody>
				<tr>
					<th scope="row">${l.sku}</th>
					<td>${l.nombre}</td>
					<td>${l.descripcion}</td>
					<td>${l.autor}</td>
					<td>${l.imagen}</td>
					<td>${l.precio}€</td>
					<td>${l.descuento}%</td>
					<td>
						<form action="admin/editar/libro" method="get">
							<input type="hidden" name="id" value="${l.id}">
							<button type="submit" class="btn btn-warning w-100">Editar</button>
						</form>
						<form action="admin/borrar/libro" method="get">
							<input type="hidden" name="id" value="${l.id}">
							<button type="submit" class="btn btn-danger w-100"
								onclick="javascript: return confirm('¿Estas seguro que quieres borrar este libro?')">Borrar</button>
						</form>
					</td>
				</tr>
			</tbody>
		</c:forEach>
	</table>
</article>
<%@ include file="/WEB-INF/vistas/inc/footer.jsp"%>

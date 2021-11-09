<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/inc/header.jsp"%>


<article class="mb-5 mt-5 overflow">
	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th scope="col">Nombre</th>
				<th scope="col">Descripción</th>
				<th scope="col">Opciones</th>
			</tr>
		</thead>
		<c:forEach items="${categorias}" var="c">
			<tbody>
				<tr>
					<td>${c.nombre}</td>
					<td>${c.descripcion}</td>
					<td>
						<div class="btn-group" role="group"
							aria-label="Basic mixed styles example">
							<button type="button" class="btn btn-info w-30">Info</button>
							<form action="admin/editar/categorias/libro" method="get">
								<input type="hidden" name="id" value="${c.id}">
								<button type="submit" class="btn btn-warning w-30">Editar</button>
							</form>
							<form action="admin/borrar/categorias/libro" method="get">
								<input type="hidden" name="id" value="${c.id}">
								<button type="submit" class="btn btn-danger w-30"
									onclick="javascript: return confirm('¿Estas seguro que quieres borrar este usuario?')">Borrar</button>
							</form>
						</div>
					</td>
				</tr>
			</tbody>
		</c:forEach>
	</table>
</article>

<article class="container-fluid article">
	<h2 class="mb-4 pt-5 border-top">Añade una nueva categoría para los libros</h2>
	<form action="libro/categoria/validate" method="post" class="row g-3"
		novalidate>
		<div class="col-md-4">
			<label for="nombre" class="form-label">Nombre</label> <input
				type="text"
				class="form-control ${categoria.errores.nombre != null ? 'is-invalid' : '' }"
				id="nombre" maxlength="50" name="nombre" value="${categoria.nombre}"
				required>
			<div class="invalid-feedback">${categoria.errores.nombre}</div>
		</div>
		<div class="col-md-12">
			<label for="descripcion" class="form-label">Descripción</label>
			<textarea
				class="form-control ${categoria.errores.descripcion != null ? 'is-invalid' : '' }"
				id="descripcion" maxlength="1000" name="descripcion" rows="3"
				required>${categoria.descripcion}</textarea>
			<div class="invalid-feedback">${categoria.errores.descripcion}</div>
		</div>
		<div class="d-grid gap-2 d-md-flex justify-content-md-end">
			<button type="submit" class="btn btn-primary me-md-2 mt-4">Añadir
				categoría</button>
		</div>
	</form>
</article>

<%@ include file="/WEB-INF/vistas/inc/footer.jsp"%>

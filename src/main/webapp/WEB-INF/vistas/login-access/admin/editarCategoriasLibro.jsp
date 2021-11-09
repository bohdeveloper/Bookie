<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/inc/header.jsp"%>

<article class="container-fluid article">
	<h2 class="text-center">Editar categoría: <span class="text-primary">${categoria.nombre}</span></h2>
	<form action="libro/categoria/validate" method="post" class="row g-3 p-5"
		novalidate>
		
		<input type="hidden" name="editado"> 
		
		<input type="hidden" name="id" value="${categoria.id}">
		
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
			<a href="javascript: history.go(-1)" class="btn btn-danger me-md-2 mt-4">Cancelar
				operación</a>

			<button type="submit" class="btn btn-success me-md-2 mt-4">Aceptar
				cambios</button>
		</div>
	</form>
</article>
<%@ include file="/WEB-INF/vistas/inc/footer.jsp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/inc/header.jsp"%>

<article class="container-fluid article">
	<h2 class="text-center">
		Editar libro: <span class="text-primary">${libro.nombre}</span>
	</h2>
	<form action="libro/validate" method="post" class="row g-3 p-5"
		novalidate>
		<input type="hidden" name="editado"> <input type="hidden"
			name="id" value="${libro.id}">
		<div class="col-md-2">
			<label for="sku" class="form-label">Sku</label> <input type="text"
				class="form-control ${libro.errores.sku != null ? 'is-invalid' : '' }"
				id="sku" name="sku" maxlength="7" value="${libro.sku}" required>
			<div class="invalid-feedback">${libro.errores.sku}</div>
		</div>
		<div class="col-md-10">
			<label for="nombre" class="form-label">Nombre</label> <input
				type="text"
				class="form-control ${libro.errores.nombre != null ? 'is-invalid' : '' }"
				id="nombre" maxlength="50" name="nombre" value="${libro.nombre}"
				required>
			<div class="invalid-feedback">${libro.errores.nombre}</div>
		</div>
		<div class="col-md-12">
			<label for="descripcion" class="form-label">Descripción</label>
			<textarea
				class="form-control ${libro.errores.descripcion != null ? 'is-invalid' : '' }"
				id="descripcion" maxlength="1000" name="descripcion" rows="3"
				required>${libro.descripcion}</textarea>
			<div class="invalid-feedback">${libro.errores.descripcion}</div>
		</div>
		<div class="col-md-6">
			<label for="autor" class="form-label">Autor</label> <input
				class="form-control ${libro.errores.autor != null ? 'is-invalid' : '' }"
				id="autor" value="${libro.autor}" name="autor" required>
			<div class="invalid-feedback">${libro.errores.autor}</div>
		</div>
		<div class="col-md-6">
			<label for="precio" class="form-label">Precio</label> <input
				type="number" min="0.00"
				class="form-control ${libro.errores.precio != null ? 'is-invalid' : '' }"
				id="precio" name="precio"
				value="${libro.precio != null ? libro.precio : 0.00}" required>
			<div class="invalid-feedback">${libro.errores.precio}</div>
		</div>
		<div class="col-md-12">
			<label for="imagen" class="form-label">URL - Imagen del libro</label>
			<input
				class="form-control ${libro.errores.imagen != null ? 'is-invalid' : '' }"
				type="text" id="imagen" name="imagen" value="${libro.imagen}"
				required>
			<div class="invalid-feedback">${libro.errores.imagen}</div>
		</div>
		<div class="col-md-6">
			<label for="descuento" class="form-label">Descuento</label>
			<div class="d-flex">
				<input type="range" min="0" max="100"
					oninput="this.nextElementSibling.value = this.value"
					value="${libro.descuento != null ? libro.descuento : 0}"
					class="form-control ${libro.errores.descuento != null ? 'is-invalid' : '' }"
					id="descuento" name="descuento" required>
				<output class="p-2">${libro.descuento != null ? libro.descuento : 0}</output>
			</div>
			<div class="invalid-feedback">${libro.errores.descuento}</div>

			<!--<label for="descuento" class="form-label">Descuento</label> <input
				type="text" min="0" max="100"
				value="${libro.descuento != null ? libro.descuento : 0}"
				class="form-control ${libro.errores.descuento != null ? 'is-invalid' : '' }"
				id="descuento" name="descuento" required>
			<div class="invalid-feedback">${libro.errores.descuento}</div>-->
		</div>
		<div class="col-md-6">
			<label for="categoria" class="form-label">Categoría</label> <select
				class="form-select ${libro.errores.categoria != null ? 'is-invalid' : '' }"
				name="categoria" id="categoria">
				<c:if test="${libro.categoriaLibro != null}">
					<option value="${libro.categoriaLibro.id}">${libro.categoriaLibro.nombre}</option>
				</c:if>
				<c:if test="${libro.categoriaLibro == null}">
					<option value="0">Elige una opción</option>
				</c:if>
				<c:forEach items="${categorias}" var="c">
					<option value="${c.id}">${c.nombre}</option>
				</c:forEach>
			</select>
			<div class="invalid-feedback">${libro.errores.categoria}</div>
		</div>
		<div class="d-grid gap-2 d-md-flex justify-content-md-end">
			<a href="javascript: history.go(-1)"
				class="btn btn-danger me-md-2 mt-4">Cancelar operación</a>

			<button type="submit" class="btn btn-success me-md-2 mt-4">Aceptar
				cambios</button>
		</div>
	</form>
</article>
<%@ include file="/WEB-INF/vistas/inc/footer.jsp"%>

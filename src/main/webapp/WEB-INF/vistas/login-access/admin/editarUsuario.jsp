<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/inc/header.jsp"%>

<article class="container-fluid article">
	<h2 class="text-center">
		Editar usuario: <span class="text-primary">${usuario.nombre_usuario}</span>
	</h2>
	<form action="usuario/validate" method="post" class="row g-3 p-5"
		novalidate>
		<input type="hidden" name="editado"> <input type="hidden"
			name="id" value="${usuario.id}">
		<div class="col-md-6">
			<label for="nombre" class="form-label">Nombre de usuario</label> <input
				type="text"
				class="form-control ${usuario.errores.nombre_usuario != null ? 'is-invalid' : '' }"
				id="nombre_usuario" maxlength="50" name="nombre_usuario"
				value="${usuario.nombre_usuario}" required>
			<div class="invalid-feedback">${usuario.errores.nombre_usuario}</div>
		</div>
		<div class="col-md-6">
			<label for="email" class="form-label">Correo electrónico</label> <input
				type="email"
				class="form-control ${usuario.errores.email != null ? 'is-invalid' : '' }"
				id="email" value="${usuario.email}" name="email" required>
			<div class="invalid-feedback">${usuario.errores.email}</div>
		</div>
		<div class="col-md-6">
			<label for="password" class="form-label">Password</label> <input
				type="text" value="${usuario.password}"
				class="form-control ${usuario.errores.password != null ? 'is-invalid' : '' }"
				id="password" name="password" required>
			<div class="invalid-feedback">${usuario.errores.password}</div>
		</div>
		<div class="col-md-6">
			<label for="rol" class="form-label">Rol de usuario</label> <select
				class="form-select" name="rol" id="rol">
				<option value="${usuario.rolUsuario.id}">${usuario.rolUsuario.nombre}</option>
				<c:forEach items="${roles}" var="r">
					<option value="${r.id}">${r.nombre}</option>
				</c:forEach>
			</select>
		</div>
		<div class="col-sm-6">
			<label for="sexo" class="form-label">Sexo</label>
			<div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="sexo"
						id="inlineRadio1" value="hombre"
						${usuario.sexo == 'hombre' ? 'checked' : '' }> <label
						class="form-check-label" for="inlineRadio1">Hombre</label>
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="sexo"
						id="inlineRadio2" value="mujer"
						${usuario.sexo == 'mujer' ? 'checked' : '' }> <label
						class="form-check-label" for="inlineRadio2">Mujer</label>
				</div>
			</div>
		</div>
		<input type="hidden" name="saldo" value="${usuario.saldo}">
		<div class="d-grid gap-2 d-md-flex justify-content-md-end">
			<a href="javascript: history.go(-1)"
				class="btn btn-danger me-md-2 mt-4">Cancelar operación</a>

			<button type="submit" class="btn btn-success me-md-2 mt-4">Aceptar
				cambios</button>
		</div>
	</form>
</article>
<%@ include file="/WEB-INF/vistas/inc/footer.jsp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/inc/header.jsp"%>

<article class="mb-5 mt-5 overflow">
	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th scope="col">Nombre de usuario</th>
				<th scope="col">Email</th>
				<th scope="col">Password</th>
				<th scope="col">Sexo</th>
				<th scope="col">Rol</th>
				<th scope="col">Opciones</th>

			</tr>
		</thead>
		<c:forEach items="${usuarios}" var="u">
			<tbody>
				<tr>
					<td>${u.nombre_usuario}</td>
					<td>${u.email}</td>
					<td>${u.password}</td>
					<td>${u.sexo}</td>
					<td>${u.rolUsuario.nombre}</td>

					<td>
						<div class="btn-group" role="group"
							aria-label="Basic mixed styles example">
							<button type="button" class="btn btn-info w-30">Info</button>
							<form action="admin/editar/usuario" method="get">
								<input type="hidden" name="id" value="${u.id}">
								<button type="submit" class="btn btn-warning w-30">Editar</button>
							</form>
							<form action="admin/borrar/usuario" method="get">
								<input type="hidden" name="id" value="${u.id}">
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
	<h2 class="mb-4 pt-5 border-top">Añade un nuevo usuario</h2>
	<form action="usuario/validate" method="post" class="row g-3"
		novalidate>

		<div class="col-md-6">
			<label for="nombre_usuario" class="form-label">Nombre de
				usuario</label> <input type="text"
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
				type="text"
				class="form-control ${usuario.errores.password != null ? 'is-invalid' : '' }"
				id="password" name="password" required>
			<div class="invalid-feedback">${usuario.errores.password}</div>
		</div>
		<div class="col-md-6">
			<label for="rol" class="form-label">Rol de usuario</label> <select
				class="form-select" name="rol" id="rol">
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
						id="inlineRadio1" value="hombre" checked> <label
						class="form-check-label" for="inlineRadio1">Hombre</label>
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="sexo"
						id="inlineRadio2" value="mujer"> <label
						class="form-check-label" for="inlineRadio2">Mujer</label>
				</div>
			</div>
		</div>
		<input type="hidden" name="saldo" value="0.00">
		<div class="d-grid gap-2 d-md-flex justify-content-md-end">
			<button type="submit" class="btn btn-primary me-md-2 mt-4">Añadir
				usuario</button>
		</div>
	</form>
</article>

<%@ include file="/WEB-INF/vistas/inc/footer.jsp"%>

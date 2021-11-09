<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/inc/header.jsp"%>


<article class="article-login container-fluid p-5 article">
	<form action="registro/usuario/validate" method="post"
		enctype="multipart/form-data" novalidate>
		<div class="row">
			<div class="col-1 col-sm-3 col-lg-3"></div>
			<div class="col-sm-8 col-lg-6">
				<div class="mb-3 row">
					<div class="col-sm-12 col-xl-4">
						<label for="nombre_usuario" class="form-label">Nombre de
							usuario</label> <input type="text"
							class="form-control  ${usuarioFail.errores.nombre_usuario != null || error != null ? 'is-invalid' : '' }"
							value="${usuarioFail.nombre_usuario}" id="nombre_usuario"
							name="nombre_usuario">
						<div class="invalid-feedback">${usuarioFail.errores.nombre_usuario}</div>
					</div>
					<div class="col-sm-6 col-xl-4">
						<label for="password1" class="form-label">Contraseña</label> <input
							type="password"
							class="form-control  ${usuarioFail.errores.password1 != null ||usuarioFail.errores.password1 != null || error != null ? 'is-invalid' : '' }"
							value="" id="password1" name="password1">
						<div class="invalid-feedback">${usuarioFail.errores.password1}</div>

					</div>
					<div class="col-sm-6 col-xl-4">
						<label for="password2" class="form-label">Repite la
							contraseña</label> <input type="password"
							class="form-control  ${usuarioFail.errores.password2 != null || error != null ? 'is-invalid' : '' }"
							value="" id="password2" name="password2">
						<div class="invalid-feedback">${usuarioFail.errores.password2}</div>
					</div>
				</div>
				<div class="mb-3 row">
					<div class="col-sm-6">
						<label for="email" class="form-label">Correo electrónico</label> <input
							type="email"
							class="form-control  ${usuarioFail.errores.email != null || error != null ? 'is-invalid' : '' }"
							value="${usuarioFail.email}" id="email" name="email">
						<div class="invalid-feedback">${usuarioFail.errores.email}</div>
					</div>
					<div class="col-md-6">
						<label for="imagen_usuario" class="form-label">Foto de
							perfil</label> <input type="file"
							class="form-control  ${usuarioFail.errores.imagen_usuario != null || error != null ? 'is-invalid' : '' }"
							value="${usuarioFail.imagen_usuario}" id="imagen_usuario"
							name="imagen_usuario">
						<div class="invalid-feedback">${usuarioFail.errores.imagen_usuario}</div>
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
				</div>
				<input type="hidden" name="saldo" value="0.00"> <input
					type="hidden" name="rol" value="2">
				<div class="d-grid gap-2 d-md-flex justify-content-md-end">
					<button class="btn btn-info me-md-2 mt-3" type="submit">Enviar</button>
				</div>
			</div>
			<div class="col-1 col-sm-12"></div>
		</div>
	</form>
</article>
<%@ include file="/WEB-INF/vistas/inc/footer.jsp"%>

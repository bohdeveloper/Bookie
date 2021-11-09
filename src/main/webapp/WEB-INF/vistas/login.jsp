<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/inc/header.jsp"%>


<article class="article-login container-fluid p-5 article">
	<form action="login/validate" method="post" novalidate>
		<div class="row">
			<div class="col-1 col-sm-3"></div>
			<div class="col-10 col-sm-6">
				<div class="mb-3 row">
					<div class="col-sm-12">
						<label for="email" class="form-label">Correo electrónico</label> <input
							type="text"
							class="form-control ${usuarioFail.errores.email != null || error != null ? 'is-invalid' : '' }"
							id="email" name="email" value="${usuarioFail.email}" required>
						<div class="invalid-feedback">${usuarioFail.errores.email}</div>
					</div>
				</div>
				<div class="mb-3 row">
					<div class="col-sm-12">
						<label for="password" class="form-label">Password</label> <input
							type="password"
							class="form-control ${usuarioFail.errores.password != null || error != null ? 'is-invalid' : '' }"
							id="password" name="password" required>
						<div class="invalid-feedback">${usuarioFail.errores.password}</div>
						<c:if test="${error != null}">
							<div class="error">${error}</div>
						</c:if>
					</div>
				</div>

				<a href="registro/usuario" class="text-info">Quiero registrarme</a>
				<div class="d-grid gap-2 d-md-flex justify-content-md-end">
					<button class="btn btn-info me-md-2 mt-3" type="submit">Iniciar
						sesión</button>
				</div>
			</div>
			<div class="col-1 col-sm-12"></div>
		</div>
	</form>
</article>
<%@ include file="/WEB-INF/vistas/inc/footer.jsp"%>

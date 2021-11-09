<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/inc/header.jsp"%>


<article class="article-login container-fluid p-5 article">
	<form action="registro/cliente/validate" method="post" novalidate>
		<div class="row">
			<div class="col-1 col-sm-3"></div>
			<div class="col-10 col-sm-6">
				<div class="mb-3 row">
					<div class="col-sm-3">
						<label for="nombre" class="form-label">Nombre</label> <input
							type="text"
							class="form-control  ${clienteFail.errores.nombre != null || error != null ? 'is-invalid' : '' }"
							value="${clienteFail.nombre}" id="nombre" name="nombre">
						<div class="invalid-feedback">${clienteFail.errores.nombre}</div>
					</div>
					<div class="col-sm-4">
						<label for="apellidos" class="form-label">Apellidos</label> <input
							type="text"
							class="form-control  ${clienteFail.errores.apellidos != null || error != null ? 'is-invalid' : '' }"
							value="${clienteFail.apellidos}" id="apellidos" name="apellidos">
						<div class="invalid-feedback">${clienteFail.errores.apellidos}</div>
					</div>
					<div class="col-sm-5">
						<label for="telefono" class="form-label">Telefono</label>
						<div class="input-group">
							<input type="number"
								class="form-control  ${clienteFail.errores.telefono != null || error != null ? 'is-invalid' : '' }"
								value="${clienteFail.telefono}"
								placeholder="Ayuda en la entrega" id="telefono" name="telefono">
							<div class="invalid-feedback">${clienteFail.errores.telefono}</div>
							<div class="input-group-append">
								<span class="input-group-text"><i class="fas fa-phone"></i></span>
							</div>
						</div>
					</div>
				</div>
				<div class="mb-3 row">
					<div class="col-md-6">
						<label for="ciudad" class="form-label">Ciudad</label> <input
							type="text"
							class="form-control  ${clienteFail.errores.ciudad != null || error != null ? 'is-invalid' : '' }"
							value="${clienteFail.ciudad}" id="ciudad" name="ciudad">
						<div class="invalid-feedback">${clienteFail.errores.ciudad}</div>
					</div>
					<div class="col-md-6">
						<label for="pais" class="form-label">Pais</label> <input
							type="text"
							class="form-control  ${clienteFail.errores.pais != null || error != null ? 'is-invalid' : '' }"
							value="${clienteFail.pais}" id="pais" name="pais">
						<div class="invalid-feedback">${clienteFail.errores.pais}</div>
					</div>
				</div>
				<div class="mb-3 row">
					<div class="col-md-12">
						<label for="domicilio" class="form-label">Domicilio</label> <input
							type="text"
							class="form-control  ${clienteFail.errores.domicilio != null || error != null ? 'is-invalid' : '' }"
							value="${clienteFail.domicilio}" id="domicilio" name="domicilio">
						<div class="invalid-feedback">${clienteFail.errores.domicilio}</div>
					</div>
				</div>
				<div class="d-grid gap-2 d-md-flex justify-content-md-end">
					<button class="btn btn-info me-md-2 mt-3" type="submit">Usar
						estos datos</button>
				</div>
			</div>
			<div class="col-1 col-sm-12"></div>
		</div>
	</form>
</article>
<%@ include file="/WEB-INF/vistas/inc/footer.jsp"%>

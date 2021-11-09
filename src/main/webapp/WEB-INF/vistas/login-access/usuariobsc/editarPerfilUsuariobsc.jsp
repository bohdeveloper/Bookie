<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ include file="/WEB-INF/vistas/inc/header.jsp" %>
		<!-- USUARIO BÁSICO -->

		<!-- Button trigger modal -->


		<!-- Modal subir imagen -->
		<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Elige la foto que quieres añadir</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">

						<form action="usuario/añadir/foto" method="post" enctype="multipart/form-data" novalidate>
							<label for="imagen_usuario" class="form-label">Foto de
								perfil</label> <input type="file"
								class="form-control  ${usuarioFail.errores.imagen_usuario != null || error != null ? 'is-invalid' : '' }"
								value="${usuarioFail.imagen_usuario}" id="imagen_usuario" name="imagen_usuario">

							<div class="modal-footer">
								<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
								<button type="submit" class="btn btn-primary">Guargar cambios</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

		<div class="w-100 bg-white border rounded d-flex justify-content-between">
			<div class="card" style="width: 18rem;">
				<img src="img/user/standar/${usuario.sexo == 'hombre' ? 'male' : 'female'}.png" class="card-img-top p-5"
					alt="foto de perfil">

				<div class="card-body text-center">
					<h2 class="card-title text-primary mb-5">${usuario.nombre_usuario}</h2>
					<p>
						<a href="#" class="link-primary fs-5" data-bs-toggle="modal"
							data-bs-target="#exampleModal">Cambiar
							foto de perfil</a>
					</p>
					<p>
						<a href="#" class="link-primary fs-5">Cambiar nombre de usuario</a>
					</p>
					<p>
						<a href="#" class="link-primary fs-5">Quiero ser PRO</a>
					</p>
				</div>
			</div>
		</div>
		<%@ include file="/WEB-INF/vistas/inc/footer.jsp" %>
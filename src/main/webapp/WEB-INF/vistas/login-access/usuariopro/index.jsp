<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/inc/header.jsp"%>
<!-- USUARIO PRO -->
<div class="row p-5">
	<div class="col-sm-6 col-md-9">
		<div class="w-100 bg-white border rounded d-flex justify-content-between">
			<span class="badge badge-warning m-3 fs-3">PRO</span>
			<span class="badge badge-warning m-3 fs-3">${usuario.saldo} €</span>
		</div>
		<div class="w-100 d-flex justify-content-between">
			<p class="hidden"></p>
			<a href="usuario/recargar/saldo">Recargar monedero</a>
		</div>
	</div>
	<div class="col-sm-6 col-md-3">
		<div class="card" style="width: 18rem;">
			<img
				src="img/user/standar/${usuario.sexo == 'hombre' ? 'male' : 'female'}.png"
				class="card-img-top p-5" alt="foto de perfil">
			<div class="card-body text-center">
				<h2 class="card-title text-primary">Hola,
					${usuario.nombre_usuario}</h2>
				<p class="card-text">¿Que desas hacer?</p>
				<p>
					<a href="#">Editar mi perfil</a>
				</p>
				<p>
					<a href="#">Seguridad</a>
				</p>
				<p>
					<a href="#">Configuración</a>
				</p>
			</div>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/vistas/inc/footer.jsp"%>

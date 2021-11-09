<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/inc/header.jsp"%>

<div class="row ps-5 w-100">
	<div class="col-sm-6 col-md-5 col-lg-4 col-xl-3 pb-5">
		<div class="card" style="width: 18rem;">
			<img src="img/admin/usuarios.jpg" class="card-img-top p-5"
				alt="usuarios">
			<div class="card-body">
				<h5 class="card-title">Mantenimiento<br>de usuarios</h5>
				<p class="card-text">Aquí podrás administrar la creación,
					edición y el borrado de nuestros usuarios.</p>
				<a href="admin/crud/usuario"><span class="badge bg-info fs-5 p-3 w-100">Acceder</span></a>
			</div>
		</div>
	</div>
	<div class="col-sm-6 col-md-5 col-lg-4 col-xl-3 pb-5">
		<div class="card" style="width: 18rem;">
			<img src="img/admin/roles.png" class="card-img-top p-5" alt="libros">
			<div class="card-body">
				<h5 class="card-title">Mantenimiento<br>de roles</h5>
				<p class="card-text">Aquí podrás administrar la creación,
					edición y el borrado de los roles de usuario.</p>
				<a href="admin/crud/roles/usuario"><span class="badge bg-info fs-5 p-3 w-100">Acceder</span></a>
			</div>
		</div>
	</div>
	<div class="col-sm-6 col-md-5 col-lg-4 col-xl-3 pb-5">
		<div class="card" style="width: 18rem;">
			<img src="img/admin/libros.png" class="card-img-top p-5" alt="libros">
			<div class="card-body">
				<h5 class="card-title">Mantenimiento<br>de libros</h5>
				<p class="card-text">Aquí podrás administrar la creación,
					edición y el borrado de nuestros libros.</p>
				<a href="admin/crud/libro"><span class="badge bg-info fs-5 p-3 w-100">Acceder</span></a>
			</div>
		</div>
	</div>
	<div class="col-sm-6 col-md-5 col-lg-4 col-xl-3 pb-5">
		<div class="card" style="width: 18rem;">
			<img src="img/admin/categorias.jpg" class="card-img-top p-5" alt="categorias de libros">
			<div class="card-body">
				<h5 class="card-title">Mantenimiento<br>categorias de libros</h5>
				<p class="card-text">Aquí podrás administrar la creación,
					edición y el borrado de las categorias de nuestros libros.</p>
				<a href="admin/crud/categorias/libro"><span class="badge bg-info fs-5 p-3 w-100">Acceder</span></a>
			</div>
		</div>
	</div>
</div>


<%@ include file="/WEB-INF/vistas/inc/footer.jsp"%>

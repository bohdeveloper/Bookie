<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">

<head>
<base href="${pageContext.request.contextPath}/" />
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/main.css" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css"
	rel="stylesheet" />
<script src="js/bootstrap.bundle.min.js"></script>
<script src="js/home.js"></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<title>Biblioteca Bookie</title>

</head>

<body class="bg-light">

	<c:if test="${error != null}">
		<div
			class="alert alert-${tipo} alert-dismissible fade show text-center"
			role="alert">
			${error}
			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>
	</c:if>

	<c:if test="${correcto != null}">
		<div
			class="alert alert-${tipo} alert-dismissible fade show text-center"
			role="alert">
			${correcto}
			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>
	</c:if>

	<c:if test="${mensaje != null}">
		<div
			class="alert alert-${tipo} alert-dismissible fade show text-center"
			role="alert">
			${mensaje}
			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>
	</c:if>



	<c:if test="${sessionScope.usuario == null}">
		<div class="p-1 bg-dark sticky-top d-flex justify-content-between">

			<div>
				<c:if test="${sessionScope.usuario != null}">
					<i class="fas fa-user pr-2 fs-4 text-info user"></i>
					<span class="navbar-text fs-4 text-info user">${sessionScope.usuario.nombre_usuario}</span>
				</c:if>
			</div>

			<div>
				<a href="index"><i class="fas fa-book fs-3 text-info p-2"></i></a> <a
					class="disabled" href="#"><i
					class="fas fa-phone fs-3 text-info p-2"></i></a> <a class="disabled"
					href="#"><i class="fas fa-file-signature fs-3 text-info p-2"></i></a>
				<a href="login"><i class="fas fa-sign-in-alt fs-3 text-info p-2"></i></a>
			</div>

		</div>
	</c:if>

	<c:if test="${sessionScope.usuario != null}">
		<div class="ps-3 bg-dark sticky-top d-flex justify-content-between">
			<div>
				<i class="fas fa-user pr-2 fs-5 text-info user"></i> <span
					class="navbar-text fs-5 text-info user">${sessionScope.usuario.nombre_usuario}</span>
			</div>
			<div class="pt-1">
				<c:if test="${volver != null}">
					<a href="javascript: history.go(-1)"><i
						class="fas fa-chevron-left fs-3 text-danger p-2"></i></a>
				</c:if>
				<c:if
					test="${sessionScope.admin != null && sessionScope.user == null && sessionScope.userpro == null}">
					<a href="admin"><i class="fas fa-home fs-3 text-info p-2"></i></a>
					<a href="index"><i class="fas fa-book fs-3 text-info p-2"></i></a>
				</c:if>
				<c:if
					test="${sessionScope.user != null && sessionScope.admin == null && sessionScope.userpro == null}">
					<a href="usuariobsc/index"><i
						class="fas fa-home fs-3 text-info p-2"></i></a>
					<a href="index"><i class="fas fa-book fs-3 text-info p-2"></i></a>
				</c:if>
				<c:if
					test="${sessionScope.userpro != null && sessionScope.admin == null && sessionScope.user == null}">
					<a href="usuariopro/index"><i
						class="fas fa-home fs-3 text-info p-2"></i></a>
					<a href="index"><i class="fas fa-book fs-3 text-info p-2"></i></a>
				</c:if>
				<c:if
					test="${sessionScope.user != null || sessionScope.userpro != null}">
					<c:if test="${sessionScope.factura != null}">
						<span class="badge bg-danger position-absolute">${cuantosLibrosFactura}</span>
					</c:if>
					<a href="carrito"><i
						class="fas fa-cash-register fs-3 text-info p-2"></i></a>

				</c:if>
				<a href="logout"><i
					class="fas fa-power-off fs-3 text-danger p-2"></i></a>
			</div>
		</div>
	</c:if>

	<header class="contailer-fluid bg-dark">
		<div class="logo">
			<img src="img/libros.png" alt="Biblioteca" id="logo" />
		</div>
	</header>
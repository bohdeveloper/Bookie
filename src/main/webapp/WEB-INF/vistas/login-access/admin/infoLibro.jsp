<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/inc/header.jsp"%>

<article class="container-fluid article">
	<div class="row">
		<div class="col-12 d-flex">
			<p class="fs-4">${libro.sku}</p>
			<h2 class="ms-3">- ${libro.nombre}</h2>
		</div>

		<div class="col-md-3 col-lg-4 col-xl-3 mt-5">
			<div class="p-2">
				<img src="${libro.imagen}" alt="${libro.nombre}" id="infoImg">
				<div>
					<span class="fs-5">${libro.descuento}% -</span> <span class="fs-5"><b>${libro.precio}€</b></span>
				</div>
			</div>
		</div>

		<div class="col-md-9 col-lg-8 col-xl-9 mt-4 p-5">
			<h4>${libro.categoriaLibro.nombre}</h4>
			<h4>Escrito por ${libro.autor}</h4>
			<p class="fs-5 p-3">${libro.descripcion}</p>
		</div>
	</div>
</article>
<%@ include file="/WEB-INF/vistas/inc/footer.jsp"%>

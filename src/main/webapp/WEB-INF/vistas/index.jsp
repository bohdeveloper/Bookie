<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/inc/header.jsp"%>

<c:if
	test="${index != null && admin == null && user == null && userpro == null && logout == null}">
	<article class="container-fluid mb-5">
		<h3 class="mb-5 text-secondary pb-2 border-bottom">Libros disponibles</h3>
		<div class="row">
			<c:forEach items="${libros}" var="l">
				<div class="col-12 col-sm-6 col-md-4 col-xl-2">
					<div class="card text-center">
						<img src="${l.imagen}" class="card-img-top"
							alt="Portada - ${l.sku}" />
						<div class="card-body">
							<h5 class="card-title fw-bolder">${l.nombre}</h5>
							<p class="card-subtitle text-secondary">${l.autor}</p>
							<p class="card-text text-secondary">
								<small class="text-muted">${l.categoriaLibro.nombre}</small>
							</p>
							<div id="a${l.id}" class="collapse">
								<p class="card-text text-secondary">${l.descripcion}</p>
							</div>
							<div class="control-libro">
								<button type="button" class="btn btn-secondary mt-5 w-100"
									data-toggle="collapse" data-target="#a${l.id}">
									Descripción</button>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</article>
</c:if>

<c:if test="${admin != null}">
	<article class="container-fluid mb-5">
		<h1 class="mb-5 text-secondary pb-5 border-bottom">Listado de
			libros</h1>
		<div class="row">
			<c:forEach items="${libros}" var="l">
				<div class="col-12 col-sm-6 col-md-4 col-xl-2">
					<div class="card text-center">
						<img src="${l.imagen}" class="card-img-top"
							alt="Portada - ${l.sku}" />
						<div class="card-body">
							<h5 class="card-title fw-bolder">${l.nombre}</h5>
							<p class="card-subtitle text-secondary">${l.autor}</p>
							<p class="card-text text-secondary">
								<small class="text-muted">${l.categoriaLibro.nombre}</small>
							</p>
							<div id="a${l.id}" class="collapse">
								<p class="card-text text-secondary">${l.descripcion}</p>
							</div>
							<div class="control-libro">
								<button type="button" class="btn btn-secondary mt-5 w-100"
									data-toggle="collapse" data-target="#a${l.id}">
									Descripción</button>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</article>
</c:if>



<c:if test="${user != null || userpro != null}">

	<article class="container-fluid mb-5">
		<h1 class="mb-5 text-secondary pb-5 border-bottom">Listado de
			libros</h1>
		<div class="row">
			<c:forEach items="${libros}" var="l">
				<div class="col-12 col-sm-6 col-md-4 col-xl-2">
					<div class="card text-center">
						<img src="${l.imagen}" class="card-img-top"
							alt="Portada - ${l.sku}" />
						<div class="card-body">

							<h5 class="card-title fw-bolder">${l.nombre}</h5>
							<p class="card-subtitle text-secondary">${l.autor}</p>
							<p class="card-text text-secondary">
								<small class="text-muted">${l.categoriaLibro.nombre}</small>
							</p>

							<div class="d-flex justify-content-between">
								<div>
									<h5 class="badge text-primary fs-5">${l.precio}€</h5>
									<h5 class="badge text-success fs-6">${l.descuento}%</h5>
								</div>
								<i class="fas fa-heart text-secondary p-2 fs-2"></i>
							</div>
							<div id="a${l.id}" class="collapse">
								<p class="card-text text-secondary">${l.descripcion}</p>
							</div>
							<div class="btn-group">
								<button type="button" class="btn btn-secondary mt-5"
									data-toggle="collapse" data-target="#a${l.id}">
									Descripción</button>
								<a href="comprar/libro?id=${l.id}"
									class="btn btn-primary mt-5 me-3">Comprar</a>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</article>
</c:if>

<c:if
	test="${logout != null && admin == null && user == null && userpro == null}">
	<article class="container-fluid mb-5">
		<h1 class="mb-5 text-secondary pb-5 border-bottom">Listado de
			libros</h1>
		<div class="row">
			<c:forEach items="${libros}" var="l">
				<div class="col-12 col-sm-6 col-md-4 col-xl-2">
					<div class="card text-center">
						<img src="${l.imagen}" class="card-img-top"
							alt="Portada - ${l.sku}" />
						<div class="card-body">
							<h5 class="card-title fw-bolder">${l.nombre}</h5>
							<p class="card-subtitle text-secondary">${l.autor}</p>
							<p class="card-text text-secondary">
								<small class="text-muted">${l.categoriaLibro.nombre}</small>
							</p>
							<div id="a${l.id}" class="collapse">
								<p class="card-text text-secondary">${l.descripcion}</p>
							</div>
							<div class="control-libro">
								<button type="button" class="btn btn-secondary mt-5 w-100"
									data-toggle="collapse" data-target="#a${l.id}">
									Descripción</button>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</article>
</c:if>

<%@ include file="/WEB-INF/vistas/inc/footer.jsp"%>

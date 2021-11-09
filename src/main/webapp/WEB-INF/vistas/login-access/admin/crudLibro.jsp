<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/inc/header.jsp"%>

<div class="carousel">
	<button class="carousel-control-prev" type="button"
		data-bs-target="#carouselExampleFade" data-bs-slide="prev">
		<span class="carousel-control-prev-icon carrousel-dark"
			aria-hidden="true"></span>
	</button>
	<div class="alert alert-secondary text-center" role="alert">
		Puedes cambiar el formato del listado</div>
	<button class="carousel-control-next" type="button"
		data-bs-target="#carouselExampleFade" data-bs-slide="next">
		<span class="carousel-control-next-icon" aria-hidden="true"></span>
	</button>
</div>

<div id="carouselExampleFade" class="carousel carousel-dark slide"
	data-bs-touch="false" data-bs-interval="false" data-bs-ride="carousel">
	<div class="carousel-inner">
		<div class="carousel-item active">
			<article class="mb-5 mt-5">
				<div class="row">
					<c:forEach items="${libros}" var="l">
						<div class="col-sm-6 col-md-4 col-xl-2">
							<div class="card">
								<img src="${l.imagen}" class="card-img-top"
									alt="Portada - ${l.sku}" />
								<div class="card-body">
									<form action="info/libro" method="get">
										<input type="hidden" name="id" value="${l.id}">
										<button type="submit" class="btn btn-info w-100">Información</button>
									</form>
									<div class="control-libro mt-1">
										<form action="admin/editar/libro" method="get">
											<input type="hidden" name="id" value="${l.id}">
											<button type="submit" class="btn btn-warning">Editar</button>
										</form>
										<form action="admin/borrar/libro" method="get">
											<input type="hidden" name="id" value="${l.id}">
											<button type="submit" class="btn btn-danger"
												onclick="javascript: return confirm('¿Estas seguro que quieres borrar este libro?')">Borrar</button>
										</form>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</article>
		</div>

		<div class="carousel-item ">
			<article class="mb-5 mt-5 overflow">
				<table class="table table-striped">
					<thead>
						<tr>
							<th scope="col">Sku</th>
							<th scope="col">Titulo</th>
							<th scope="col">Descripción</th>
							<th scope="col">Autor/a</th>
							<th scope="col">Imagen</th>
							<th scope="col">Precio</th>
							<th scope="col">Descuento</th>
							<th scope="col">Opciones</th>
						</tr>
					</thead>
					<c:forEach items="${libros}" var="l">
						<tbody>
							<tr>
								<th scope="row">${l.sku}</th>
								<td>${l.nombre}</td>
								<td>${l.descripcion}</td>
								<td>${l.autor}</td>
								<td>${l.imagen}</td>
								<td>${l.precio}€</td>
								<td>${l.descuento}%</td>
								<td>
									<form action="admin/editar/libro" method="get">
										<input type="hidden" name="id" value="${l.id}">
										<button type="submit" class="btn btn-warning w-100">Editar</button>
									</form>
									<form action="admin/borrar/libro" method="get">
										<input type="hidden" name="id" value="${l.id}">
										<button type="submit" class="btn btn-danger w-100"
											onclick="javascript: return confirm('¿Estas seguro que quieres borrar este libro?')">Borrar</button>
									</form>
								</td>
							</tr>
						</tbody>
					</c:forEach>
				</table>
			</article>
		</div>
	</div>
</div>
<article class="container-fluid article">
	<h2 class="mb-4 pt-5 border-top" id="nuevoLibroTarget">Añade un
		nuevo libro</h2>
	<form action="libro/validate" method="post" class="row g-3" novalidate>
		<div class="col-md-2">
			<label for="sku" class="form-label">Sku</label> <input type="text"
				class="form-control ${libro.errores.sku != null ? 'is-invalid' : '' }"
				id="sku" name="sku" maxlength="7" value="${libro.sku}" required>
			<div class="invalid-feedback">${libro.errores.sku}</div>
		</div>
		<div class="col-md-10">
			<label for="nombre" class="form-label">Nombre</label> <input
				type="text"
				class="form-control ${libro.errores.nombre != null ? 'is-invalid' : '' }"
				id="nombre" maxlength="50" name="nombre" value="${libro.nombre}"
				required>
			<div class="invalid-feedback">${libro.errores.nombre}</div>
		</div>
		<div class="col-md-12">
			<label for="descripcion" class="form-label">Descripción</label>
			<textarea
				class="form-control ${libro.errores.descripcion != null ? 'is-invalid' : '' }"
				id="descripcion" maxlength="1000" name="descripcion" rows="3"
				required>${libro.descripcion}</textarea>
			<div class="invalid-feedback">${libro.errores.descripcion}</div>
		</div>
		<div class="col-md-6">
			<label for="autor" class="form-label">Autor/a</label> <input
				class="form-control ${libro.errores.autor != null ? 'is-invalid' : '' }"
				id="autor" value="${libro.autor}" name="autor" required>
			<div class="invalid-feedback">${libro.errores.autor}</div>
		</div>
		<div class="col-md-6">
			<label for="precio" class="form-label">Precio</label> <input
				type="number" min="0.00"
				class="form-control ${libro.errores.precio != null ? 'is-invalid' : '' }"
				id="precio" name="precio"
				value="${libro.precio != null ? libro.precio : 0.00}" required>
			<div class="invalid-feedback">${libro.errores.precio}</div>
		</div>
		<div class="col-md-12">
			<label for="imagen" class="form-label">URL - Imagen del libro</label>
			<input
				class="form-control ${libro.errores.imagen != null ? 'is-invalid' : '' }"
				type="text" id="imagen" name="imagen" value="${libro.imagen}"
				required>
			<div class="invalid-feedback">${libro.errores.imagen}</div>
		</div>
		<div class="col-md-6">
			<label for="descuento" class="form-label">Descuento</label>
			<div class="d-flex">
				<input type="range" min="0" max="100"
					oninput="this.nextElementSibling.value = this.value"
					value="${libro.descuento != null ? libro.descuento : 0}"
					class="form-control ${libro.errores.descuento != null ? 'is-invalid' : '' }"
					id="descuento" name="descuento" required>
				<output class="p-2">${libro.descuento != null ? libro.descuento : 0}</output>
			</div>
			<div class="invalid-feedback">${libro.errores.descuento}</div>

			<!--<label for="descuento" class="form-label">Descuento</label> <input
				type="text" min="0" max="100"
				value="${libro.descuento != null ? libro.descuento : 0}"
				class="form-control ${libro.errores.descuento != null ? 'is-invalid' : '' }"
				id="descuento" name="descuento" required>
			<div class="invalid-feedback">${libro.errores.descuento}</div>-->
		</div>

		<div class="col-md-6">
			<label for="categoria" class="form-label">Categoría</label> <select
				class="form-select ${libro.errores.categoria != null ? 'is-invalid' : '' }"
				name="categoria" id="categoria">
				<c:if test="${libro.categoriaLibro != null}">
					<option value="${libro.categoriaLibro.id}">${libro.categoriaLibro.nombre}</option>
				</c:if>
				<c:if test="${libro.categoriaLibro == null}">
					<option value="0">Elige una opción</option>
				</c:if>
				<c:forEach items="${categorias}" var="c">
					<option value="${c.id}">${c.nombre}</option>
				</c:forEach>
			</select>
			<div class="invalid-feedback">${libro.errores.categoria}</div>
		</div>
		<div class="d-grid gap-2 d-md-flex justify-content-md-end">
			<a href="admin/listado/libros"><button type="button"
					class="btn btn-info me-md-2 mt-4">Listado de libros</button></a>
			<button type="submit" class="btn btn-primary me-md-2 mt-4">Añadir
				libro</button>
		</div>
	</form>
</article>
<%@ include file="/WEB-INF/vistas/inc/footer.jsp"%>

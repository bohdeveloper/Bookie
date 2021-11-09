<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/inc/header.jsp"%>

<div class="row">
	<div class="col-lg-4">
		<c:if test="${factura != null}">
			<article class="mb-5">
				<a href="registro/cliente"><span
					class="badge bg-primary p-3 fs-4 w-100">Tramitar pedido</span></a>
				<h2 class="p-3">
					Subtotal (${cuantosLibrosFactura} productos) <span
						class="badge bg-warning">${factura.importe} €</span>
				</h2>
			</article>
		</c:if>
	</div>
	<div class="col-lg-8 border">
		<c:if test="${factura != null}">
			<article class="container-fluid mb-5">
				<h2 class="mb-5 text-dark">
					<i class="fas fa-shopping-cart mt-5 me-3 fs-2"></i>Carrito <br>
					<a href="index" class="fs-4">Seguir comprando</a>
				</h2>
				<div class="row">
					<c:forEach items="${factura.librosFactura}" var="l">
						<div class="col-12 col-sm-6 col-md-4 col-xl-3">
							<div class="card">
								<img src="${l.imagen}" class="card-img-top"
									alt="Portada - ${l.sku}" />
								<div class="card-body">
									<h5 class="card-title fw-bolder">${l.nombre}</h5>
									<p class="card-subtitle text-secondary">${l.autor}</p>
									<p class="card-text text-secondary">
										<small class="text-muted">${l.categoriaLibro.nombre}</small>
									</p>
									<h5 class="badge text-primary fs-5">${l.precio}€</h5>
									<h5 class="badge text-success fs-6">${l.descuento}%</h5>

									<div class="control-libro mt-1">
										<form action="carrito/borrar/libro" method="get">
											<input type="hidden" name="id" value="${l.id}">
											<button type="submit" class="btn btn-danger"
												onclick="javascript: return confirm('¿Estas seguro que quieres quitar este libro?')">Quitar
												del carrito</button>
										</form>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<a href="index" class="fs-4">Seguir comprando</a>
				<h2 class="p-3">
					Subtotal (${cuantosLibrosFactura} productos) <span
						class="badge bg-warning">${factura.importe} €</span>
				</h2>
				<a href="registro/cliente"><span
					class="badge bg-primary p-3 fs-4 w-100">Tramitar pedido</span></a>
			</article>
		</c:if>
		<c:if test="${factura == null}">
			<article class="container-fluid vacio">
				<h2 class="mb-5 text-dark">
					<i class="fas fa-shopping-cart mt-5 me-3 fs-2"></i>El carrito esta
					vacío <br> <a href="index" class="fs-4">Comprar libros</a>
				</h2>
			</article>
		</c:if>
	</div>
</div>


<%@ include file="/WEB-INF/vistas/inc/footer.jsp"%>

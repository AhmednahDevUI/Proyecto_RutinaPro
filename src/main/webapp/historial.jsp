<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="model.HistorialItem"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Rutina Pro | Historial</title>

<!-- incluimos el favicons -->
<link rel="icon"
	href="<%=request.getContextPath()%>/img/favicon/favicon.ico?v=2"
	sizes="any">
<link rel="icon" type="image/png" sizes="32x32"
	href="<%=request.getContextPath()%>/img/favicon/favicon-32x32.png?v=2">
<link rel="icon" type="image/png" sizes="16x16"
	href="<%=request.getContextPath()%>/img/favicon/favicon-16x16.png?v=2">

<!-- CSS específico del historial -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/historial.css?v=2">
</head>

<body class="cuerpo-historial">

	<!-- boton de ayuda -->
<a class="ayuda-flotante"
	href="mailto:ahmednahdesarrollador@gmail.com?subject=Ayuda%20Rutina%20Pro">
	ayuda </a>

<!-- cabecera full width pero centrada por ancho -->
<header class="cabecera-historial">
	<div class="ancho-historial">
		<h1 class="titulo-historial">Historial de entrenamientos</h1>
		<p class="subtitulo-historial">Aquí tienes todo lo que ya
			registraste: rutinas, entrenamientos y ejercicios. Esto es lo que te
			permite medir progreso de verdad.</p>

		<section class="acciones-historial">
			<a class="boton-secundario"
				href="<%=request.getContextPath()%>/Panel">Volver al panel</a> <a
			class="boton-salida"
			href="<%=request.getContextPath()%>/CerrarSesion">Cerrar sesión</a>
	</section>

	<%
	String msgOk = (String) session.getAttribute("msgOk");
	String msgError = (String) session.getAttribute("msgError");

	if (msgOk != null) {
	%>
	<div class="alerta alerta-ok"><%=msgOk%></div>
	<%
	session.removeAttribute("msgOk");
	}

	if (msgError != null) {
	%>
	<div class="alerta alerta-error"><%=msgError%></div>
	<%
	session.removeAttribute("msgError");
	}

	String errorGeneral = (String) request.getAttribute("errorGeneral");
	if (errorGeneral != null) {
	%>
	<div class="alerta alerta-error"><%=errorGeneral%></div>
	<%
	}
	%>
	</div>
</header>

<!-- contenido -->
<main class="contenido-historial">
	<div class="ancho-historial">

		<%
	@SuppressWarnings("unchecked")
	List<HistorialItem> items = (List<HistorialItem>) request.getAttribute("historialItems");

	if (items == null || items.isEmpty()) {
	%>

	<section class="estado-vacio">
		<h2 class="titulo-vacio">Aún no tienes historial</h2>
		<p class="texto-vacio">Cuando registres tu primer entrenamiento,
			aparecerá aquí con sus ejercicios. Empieza creando una rutina y
			registrando una sesión.</p>
		<a class="boton-principal"
			href="<%=request.getContextPath()%>/Rutina">Crear mi primera
			rutina</a>
	</section>

	<%
	} else {

	int rutinaActual = -1;
	int entrenamientoActual = -1;

	boolean ulAbierto = false;
	boolean entrenamientoAbierto = false;
	boolean rutinaAbierta = false;

	for (int i = 0; i < items.size(); i++) {

		HistorialItem it = items.get(i);

		if (it.getRutinaId() != rutinaActual) {
			rutinaActual = it.getRutinaId();
			entrenamientoActual = -1;

			if (ulAbierto) {
	%>
	</ul>
	<%
	ulAbierto = false;
	}
	if (entrenamientoAbierto) {
	%>
</div>
<%
	entrenamientoAbierto = false;
	}
	if (rutinaAbierta) {
	%>
	</section>
	<%
	rutinaAbierta = false;
	}
%>

<section class="bloque-rutina">
	<div class="cabecera-rutina">
		<div class="rutina-info">
			<p class="rutina-etiqueta">Rutina</p>
			<h2 class="rutina-titulo"><%=it.getRutinaNombre()%></h2>
			<p class="rutina-tipo"><%=it.getRutinaTipo()%></p>
		</div>



<div class="acciones-rutina">

	<form class="form-accion"
		action="<%=request.getContextPath()%>/SeleccionarRutina"
method="post">
<input type="hidden" name="rutinaId" value="<%=it.getRutinaId()%>">
	<input type="hidden" name="from" value="historial"> <input
		type="hidden" name="returnTo" value="historial">
	<button class="boton-accion boton-accion--principal" type="submit">
		Añadir entrenamiento</button>
</form>

<form class="form-accion"
	action="<%=request.getContextPath()%>/RutinaEliminar"
method="post">
<input type="hidden" name="rutinaId" value="<%=it.getRutinaId()%>">
<button class="boton-accion boton-accion--peligro" type="submit"
	onclick="return confirm('¿Seguro que quieres eliminar esta rutina? Se borrarán también sus entrenamientos y ejercicios.');">
			Eliminar</button>
	</form>

</div>

			</div>

			<%
	rutinaAbierta = true;
	}

	if (it.getEntrenamientoId() != entrenamientoActual) {
	entrenamientoActual = it.getEntrenamientoId();

	if (ulAbierto) {
	%>
	</ul>
	<%
	ulAbierto = false;
	}
	if (entrenamientoAbierto) {
	%>
	</div>
	<%
	entrenamientoAbierto = false;
	}
	%>

<div class="bloque-entrenamiento">

	<!-- CABECERA ENTRENAMIENTO (plegable) -->
<div class="cabecera-entrenamiento" role="button" tabindex="0">

	<!-- Info entrenamiento -->
<div class="entrenamiento-info">
	<h3 class="entrenamiento-titulo">Entrenamiento</h3>

	<p class="entrenamiento-meta">
		Nombre: <%=it.getEntrenamientoNombre()%>
	</p>

	<p class="entrenamiento-meta">
		Fecha: <%=it.getEntrenamientoFecha()%>
		| Duración: <%=it.getEntrenamientoDuracionTotal()%> min
	</p>
</div>

<!-- Acciones a la derecha: BOTÓN + flecha -->
<div class="acciones-entrenamiento">

<!-- BOTÓN AÑADIR EJERCICIOS A ESTE ENTRENAMIENTO -->
<form class="form-accion"
	action="<%=request.getContextPath()%>/SeleccionarEntrenamiento"
	method="post">

	<input type="hidden" name="entrenamientoId"
		value="<%=it.getEntrenamientoId()%>"> <input
		type="hidden" name="from" value="historial"> <input
		type="hidden" name="returnTo" value="historial">

	<button class="boton-accion boton-accion--principal"
		type="submit" onclick="event.stopPropagation();">
		Añadir ejercicios</button>
</form>

<form class="form-accion"
	action="<%=request.getContextPath()%>/EntrenamientoEliminar"
	method="post"
	onsubmit="return confirm('¿Seguro que quieres eliminar este entrenamiento? Se borrarán también sus ejercicios.');">

	<input type="hidden" name="entrenamientoId"
		value="<%=it.getEntrenamientoId()%>">

	<button class="boton-accion boton-accion--peligro" type="submit"
		onclick="event.stopPropagation();">Eliminar</button>
	</form>

	<span class="flecha" aria-hidden="true">▾</span>
</div>

</div>

	<%
entrenamientoAbierto = true;

if (it.getEntrenamientoNotas() != null && !it.getEntrenamientoNotas().trim().isEmpty()) {
%>
<p class="entrenamiento-notas">
	<b>Notas:</b>
	<%=it.getEntrenamientoNotas()%></p>
<%
}
%>

<ul class="lista-ejercicios">
	<%
	ulAbierto = true;
	}

	if (it.getEjercicioId() != 0) {
	%>
	<li class="item-ejercicio">
  <span class="ejercicio-nombre"><%=it.getEjercicioNombre()%></span>

	  <span class="ejercicio-detalles">
	    <% if (it.getEjercicioRepeticiones() != null) { %>
	      <span class="chip">Reps: <%=it.getEjercicioRepeticiones()%></span>
	    <% } %>
	    <% if (it.getEjercicioDuracionSegundos() != null) { %>
	      <span class="chip">Duración: <%=it.getEjercicioDuracionSegundos()%>s</span>
	    <% } %>
	
	    <form class="form-eliminar-ejercicio"
	          action="<%=request.getContextPath()%>/EjercicioEliminar"
	          method="post"
	          onsubmit="return confirm('¿Quieres eliminar este ejercicio?');">
	      <input type="hidden" name="ejercicioId" value="<%=it.getEjercicioId()%>">
	      <button type="submit" class="btn-x" title="Eliminar">✕</button>
	    </form>
	  </span>
	</li>
	<%
	}
	}

	if (ulAbierto) {
	%>
</ul>
<%
}
if (entrenamientoAbierto) {
%>
</div>
<%
}
if (rutinaAbierta) {
%>
</section>
<%
}
}
%>

	<p id="pistaHistorial" class="pista-historial"></p>

	</div>
</main>

<!-- footer full width -->
<footer class="pie-historial">
	<div class="ancho-historial pie-contenido">
		<p class="pie-marca" id="marcaPie">© 2026 Rutina Pro</p>

		<div class="pie-redes">
			<a class="pie-link" href="https://github.com/AhmednahDevUI"
				target="_blank" rel="noopener">
				<img alt="Github" src="<%=request.getContextPath()%>/svg/Github.svg">
		</a>

		<a class="pie-link"
			href="https://www.linkedin.com/in/ahmednah-mohamed-saleh-87361a36b?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app"
			target="_blank" rel="noopener">
			<img alt="Linkedin" src="<%=request.getContextPath()%>/svg/Linkedine.svg">
		</a>

		<a class="pie-link"
			href="https://www.instagram.com/ahmednahdesarrollador/"
			target="_blank">
			<img alt="Instagram" src="<%=request.getContextPath()%>/svg/Instagram.svg">
			</a>
		</div>
	</div>
</footer>

<script src="<%=request.getContextPath()%>/js/historial.js?v=2"></script>
</body>
</html>
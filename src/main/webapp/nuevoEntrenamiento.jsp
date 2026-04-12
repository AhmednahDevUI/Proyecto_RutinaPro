<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Rutina Pro | Nuevo Entrenamiento</title>

<!-- incluimos el favicons -->
<link rel="icon"
	href="<%=request.getContextPath()%>/img/favicon/favicon.ico?v=2"
	sizes="any">
<link rel="icon" type="image/png" sizes="32x32"
	href="<%=request.getContextPath()%>/img/favicon/favicon-32x32.png?v=2">
<link rel="icon" type="image/png" sizes="16x16"
	href="<%=request.getContextPath()%>/img/favicon/favicon-16x16.png?v=2">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/entrenamiento.css?v=2">
</head>

<body class="cuerpo-entrenamiento">

	<!-- boton de ayuda -->
	<a class="ayuda-flotante"
		href="mailto:ahmednahdesarrollador@gmail.com?subject=Ayuda%20Rutina%20Pro">
		ayuda </a>

	<main class="contenedor-entrenamiento">

		<!-- cabecera -->
		<header class="cabecera-entrenamiento">
			<h1 class="titulo-entrenamiento">Crear entrenamiento</h1>
			<p class="subtitulo-entrenamiento">Registra la fecha, la duración
				y unas notas si lo necesitas. Así tu historial queda completo y
				claro.</p>
		</header>

		<%
		String errorGeneral = (String) request.getAttribute("errorGeneral");
		if (errorGeneral != null) {
		%>
		<div class="alerta alerta-error"><%=errorGeneral%></div>
		<%
		}
		%>

		<!-- envio al servlet /Entrenamiento -->
		<form class="formulario-entrenamiento"
			action="<%=request.getContextPath()%>/Entrenamiento" method="post"
			novalidate>


			<!-- Nombre -->
			<label>Nombre del entrenamiento:</label><input type="text"
				name="nombre" required
				value="<%=request.getAttribute("nombrePrevio") != null ? request.getAttribute("nombrePrevio") : ""%>">
			
			<%
			String errorNombre = (String) request.getAttribute("errorNombre");
			if (errorNombre != null) {
			%>
			<small style="color: red;"><%=errorNombre%></small>
			<%
			}
			%>
		
			<!-- fecha -->
			<div class="campo">
				<label for="fecha">Fecha</label> <input id="fecha" type="date"
					name="fecha" required
					value="<%=request.getAttribute("fechaPrevio") != null ? request.getAttribute("fechaPrevio") : ""%>">
				<%
				String errorFecha = (String) request.getAttribute("errorFecha");
				if (errorFecha != null) {
				%>
				<small class="error-campo"><%=errorFecha%></small>
				<%
				}
				%>
			</div>

			<!-- duracion -->
			<div class="campo">
				<label for="duracion_total">Duración total (minutos)</label> <input
					id="duracion_total" type="number" name="duracion_total" step="1"
					min="0" required min="0"
					value="<%=request.getAttribute("duracionPrevio") != null ? request.getAttribute("duracionPrevio") : ""%>">
				<%
				String errorDuracion = (String) request.getAttribute("errorDuracion");
				if (errorDuracion != null) {
				%>
				<small class="error-campo"><%=errorDuracion%></small>
				<%
				}
				%>
			</div>

			<!-- notas -->
			<div class="campo">
				<label for="notas">Notas (opcional)</label>
				<textarea id="notas" name="notas" rows="4"><%=request.getAttribute("notasPrevio") != null ? request.getAttribute("notasPrevio") : ""%></textarea>
			</div>

			<!-- acciones -->
			<div class="acciones">
				<button class="boton-principal" type="submit">Crear
					entrenamiento</button>
				<a class="boton-secundario" href="<%=request.getContextPath()%>/Volver">Volver</a>
			</div>

			<p id="pistaEntrenamiento" class="pista-entrenamiento"></p>
		</form>

	</main>

	<!-- footer entrenamiento -->
	<footer class="pie-entrenamiento">
		<div class="pie-entrenamiento-contenido">
			<p class="pie-entrenamiento-marca" id="marcaPie">© 2026 Rutina
				Pro</p>

			<div class="pie-entrenamiento-derecha">
				<a class="pie-entrenamiento-link"
					href="https://github.com/AhmednahDevUI" target="_blank"
					rel="noopener"> <img alt="Github"
					src="<%=request.getContextPath()%>/svg/Github.svg">
				</a> <a class="pie-entrenamiento-link"
					href="https://www.linkedin.com/in/ahmednah-mohamed-saleh-87361a36b?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app"
					target="_blank" rel="noopener"> <img alt="Linkedin"
					src="<%=request.getContextPath()%>/svg/Linkedine.svg">
				</a> <a class="pie-entrenamiento-link"
					href="https://www.instagram.com/ahmednahdesarrollador/"
					target="_blank"> <img alt="Instagram"
					src="<%=request.getContextPath()%>/svg/Instagram.svg">
				</a>
			</div>
		</div>
	</footer>

	<script src="<%=request.getContextPath()%>/js/entrenamiento.js?v=2"></script>
</body>
</html>

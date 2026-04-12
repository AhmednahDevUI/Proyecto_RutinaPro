<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Rutina Pro | Nueva Rutina</title>

<!-- incluimos el favicons -->
<link rel="icon"
	href="<%=request.getContextPath()%>/img/favicon/favicon.ico?v=2"
	sizes="any">
<link rel="icon" type="image/png" sizes="32x32"
	href="<%=request.getContextPath()%>/img/favicon/favicon-32x32.png?v=2">
<link rel="icon" type="image/png" sizes="16x16"
	href="<%=request.getContextPath()%>/img/favicon/favicon-16x16.png?v=2">

<!-- CSS específico -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/rutina.css?v=3">
</head>

<body class="cuerpo-rutina">

	<!-- Boton de ayuda -->
	<a class="ayuda-flotante"
		href="mailto:ahmednahdesarrollador@gmail.com?subject=Ayuda%20Rutina%20Pro">
		ayuda </a>

	<main class="contenedor-rutina">

		<!-- Cabecera de la pantalla -->
		<header class="cabecera-rutina">
			<h1 class="titulo-rutina">Crear rutina</h1>
			<p class="subtitulo-rutina">Aquí empieza tu progreso real. Crea
				tu primera rutina y define la base de tu entrenamiento. Una buena
				estructura hoy significa mejores resultados mañana..</p>
		</header>

		<%
		String errorGeneral = (String) request.getAttribute("errorGeneral");
		if (errorGeneral != null) {
		%>
		<div class="alerta alerta-error"><%=errorGeneral%></div>
		<%
		}
		%>

		<!-- Envío al servlet /Rutina -->
		<form class="formulario-rutina"
			action="<%=request.getContextPath()%>/Rutina" method="post"
			novalidate>

			<!-- Campo nombre -->
			<div class="campo">
				<label for="nombre">Nombre de rutina</label> <input id="nombre"
					type="text" name="nombre" required
					value="<%=request.getAttribute("nombrePrevio") != null ? request.getAttribute("nombrePrevio") : ""%>">
				<%
				String errorNombre = (String) request.getAttribute("errorNombre");
				if (errorNombre != null) {
				%>
				<small class="error-campo"><%=errorNombre%></small>
				<%
				}
				%>
			</div>

			<!-- Campo tipo -->
			<div class="campo">
				<label for="tipo">Tipo</label>
				<%
				String tipoPrevio = (String) request.getAttribute("tipoPrevio");
				%>

				<div class="contenedor-select">
					<select id="tipo" name="tipo" required>
						<option value="GIMNASIO"
							<%="GIMNASIO".equals(tipoPrevio) ? "selected" : ""%>>Gimnasio</option>
						<option value="BOXEO"
							<%="BOXEO".equals(tipoPrevio) ? "selected" : ""%>>Boxeo</option>
						<option value="FUTBOL"
							<%="FUTBOL".equals(tipoPrevio) ? "selected" : ""%>>Fútbol</option>
						<option value="TENIS"
							<%="TENIS".equals(tipoPrevio) ? "selected" : ""%>>Tenis</option>
						<option value="CICLISMO"
							<%="CICLISMO".equals(tipoPrevio) ? "selected" : ""%>>Ciclismo</option>
						<option value="BASKET"
							<%="BASKET".equals(tipoPrevio) ? "selected" : ""%>>Básquet</option>
					</select>
				</div>

				<%
				String errorTipo = (String) request.getAttribute("errorTipo");
				if (errorTipo != null) {
				%>
				<small class="error-campo"><%=errorTipo%></small>
				<%
				}
				%>
			</div>

			<!-- Acciones -->
			<div class="acciones">
				<button class="boton-principal" type="submit">Crear rutina</button>
				<a class="boton-secundario"
					href="<%=request.getContextPath()%>/Panel">Volver</a>
			</div>

			<p id="pistaRutina" class="pista-rutina"></p>
		</form>

	</main>

	<!-- Footer de rutina-->
	<footer class="pie-rutina">
		<div class="pie-rutina-contenido">
			<p class="pie-rutina-marca" id="marcaPie">© 2026 Rutina Pro</p>

			<div class="pie-rutina-derecha">
				<a class="pie-rutina-link" href="https://github.com/AhmednahDevUI"
					target="_blank" rel="noopener"> <img alt="Github"
					src="<%=request.getContextPath()%>/svg/Github.svg">
				</a> <a class="pie-rutina-link"
					href="https://www.linkedin.com/in/ahmednah-mohamed-saleh-87361a36b?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app"
					target="_blank" rel="noopener"> <img alt="Linkedin"
					src="<%=request.getContextPath()%>/svg/Linkedine.svg">
				</a> <a class="pie-rutina-link"
					href="https://www.instagram.com/ahmednahdesarrollador/"
					target="_blank"> <img alt="Instagram"
					src="<%=request.getContextPath()%>/svg/Instagram.svg">
				</a>
			</div>
		</div>
	</footer>

	<script src="<%=request.getContextPath()%>/js/rutina.js?v=3"></script>
</body>
</html>

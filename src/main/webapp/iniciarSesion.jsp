<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Rutina Pro | Iniciar sesión</title>
<!-- incluimos el favicons -->
<link rel="icon" href="<%=request.getContextPath()%>/img/favicon/favicon.ico?v=2" sizes="any">
<link rel="icon" type="image/png" sizes="32x32"
      href="<%=request.getContextPath()%>/img/favicon/favicon-32x32.png?v=2">
<link rel="icon" type="image/png" sizes="16x16"
      href="<%=request.getContextPath()%>/img/favicon/favicon-16x16.png?v=2">




<!-- Forzamos cargar el CSS/JS nuevo para que no se quede nada guardao en la cahe -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/iniciarSesion.css?v=2">


</head>

<body class="cuerpo-login">
	<main class="contenedor-login">

		<header class="cabecera-login">
			<h1 class="titulo-login">Iniciar sesión</h1>
			<p class="subtitulo-login">Accede para ver tu panel e historial
				de entrenamientos.</p>
		</header>

		<%
		String errorGeneral = (String) request.getAttribute("errorGeneral");
		if (errorGeneral != null) {
		%>
		<div class="alerta alerta-error"><%=errorGeneral%></div>
		<%
		}
		%>

		<%
		String errorEmail = (String) request.getAttribute("errorEmail");
		if (errorEmail != null) {
		%>
		<small class="error-campo"><%=errorEmail%></small>
		<%
		}
		%>

		<!-- Mantener action con contextPath para no romper backend -->
		<form class="formulario-login"
			action="<%=request.getContextPath()%>/IniciarSesion" method="post"
			novalidate>

			<div class="campo">
				<label for="email">Email</label> <input id="email" type="email"
					name="email" required
					value="<%=request.getAttribute("email") != null ? request.getAttribute("email") : ""%>">
			</div>

			<div class="campo">
				<label for="password">Password</label>
				<div class="fila-password">
					<input id="password" type="password" name="password" required>
					<button class="boton-password" type="button"
						aria-label="Mostrar u ocultar contraseña">Mostrar</button>
				</div>

				<%
				String errorPassword = (String) request.getAttribute("errorPassword");
				if (errorPassword != null) {
				%>
				<small class="error-campo"><%=errorPassword%></small>
				<%
				}
				%>
			</div>

			<button class="boton-principal" type="submit">Entrar</button>
			<p id="pistaLogin" class="pista-login"></p>
		</form>

		<footer class="pie-login">
			<a class="enlace" href="<%=request.getContextPath()%>/Registrarse">Registrarse</a>
			<span class="punto-separador">•</span> <a class="enlace"
				href="<%=request.getContextPath()%>/index.jsp">Volver al inicio</a>
		</footer>

	</main>


	<!-- Forzamos cargar el CSS/JS nuevo para que no se quede nada guardao en la cahe -->
	<script src="<%=request.getContextPath()%>/js/iniciarSesion.js?v=2"></script>
</body>
</html>

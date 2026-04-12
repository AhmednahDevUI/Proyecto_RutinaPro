<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Rutina Pro | Guardado</title>

<!-- incluimos el favicons -->
<link rel="icon"
	href="<%=request.getContextPath()%>/img/favicon/favicon.ico?v=2"
	sizes="any">
<link rel="icon" type="image/png" sizes="32x32"
	href="<%=request.getContextPath()%>/img/favicon/favicon-32x32.png?v=2">
<link rel="icon" type="image/png" sizes="16x16"
	href="<%=request.getContextPath()%>/img/favicon/favicon-16x16.png?v=2">

<!-- CSS específico de éxito -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/exito.css?v=1">
</head>

<body class="cuerpo-exito">

	<!-- boton de ayuda -->
	<a class="ayuda-flotante"
		href="mailto:ahmednahdesarrollador@gmail.com?subject=Ayuda%20Rutina%20Pro">
		ayuda </a>

	<main class="contenedor-exito">

		<!-- cabecera -->
		<header class="cabecera-exito">
			<div class="badge-ok">Guardado correcto</div>

			<h1 class="titulo-exito">¡Buen trabajo!</h1>

			<p class="subtitulo-exito">Tu entrenamiento y sus ejercicios se
				han guardado perfectamente. Esto es progreso real: registrar, medir
				y repetir. Mañana será más fácil mejorar porque hoy lo dejaste
				anotado.</p>
		</header>

		<!-- bloque motivacional -->
		<section class="bloque-exito">
			<h2 class="titulo-bloque">Siguiente paso recomendado</h2>
			<p class="texto-bloque">Ve al historial y revisa lo que hiciste.
				Con un seguimiento constante, tu rutina deja de ser improvisación y
				se convierte en evolución.</p>

			<ul class="lista-beneficios">
				<li>Más claridad sobre tus entrenamientos.</li>
				<li>Progreso medible semana a semana.</li>
				<li>Menos dudas: más constancia.</li>
			</ul>
		</section>

		<!-- acciones -->
		<section class="acciones-exito">
			<a class="tarjeta-accion tarjeta-principal"
				href="<%=request.getContextPath()%>/Historial">
				<h3>Ver historial</h3>
				<p>Revisa tu progreso y lo que ya has registrado.</p>
			</a>

			
			<!-- Nuevo entrenamiento para la rutina actual -->
			<a class="tarjeta-accion tarjeta-secundaria"
			   href="<%=request.getContextPath()%>/Entrenamiento">
			  <h3>Añadir más entrenamientos</h3>
			  <p>Registra otra sesión dentro de esta rutina y sigue acumulando progreso.</p>
			</a>
			
			<!-- Más ejercicios para el entrenamiento actual -->
			<a class="tarjeta-accion tarjeta-secundaria"
			   href="<%=request.getContextPath()%>/Ejercicio?from=exito">
			  <h3>Añadir más ejercicios</h3>
			  <p>Completa el entrenamiento actual con detalles extra si lo necesitas.</p>
			</a> 
			
			
			<a class="tarjeta-accion tarjeta-secundaria"
				href="<%=request.getContextPath()%>/Panel">
				<h3>Volver al panel</h3>
				<p>Crear otra rutina o seguir gestionando tu cuenta.</p>
			</a>
		</section>


		<div class="zona-salida">
			<a class="boton-salida"
				href="<%=request.getContextPath()%>/CerrarSesion">Cerrar sesión</a>
			<p id="pistaExito" class="pista-exito"></p>
		</div>

	</main>

	<!-- footer -->
	<footer class="pie-exito">
		<div class="pie-exito-contenido">
			<p class="pie-exito-marca" id="marcaPie">© 2026 Rutina Pro</p>

			<div class="pie-exito-derecha">
				<a class="pie-exito-link" href="https://github.com/AhmednahDevUI"
					target="_blank" rel="noopener"> <img alt="Github"
					src="<%=request.getContextPath()%>/svg/Github.svg">
				</a> <a class="pie-exito-link"
					href="https://www.linkedin.com/in/ahmednah-mohamed-saleh-87361a36b?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app"
					target="_blank" rel="noopener"> <img alt="Linkedin"
					src="<%=request.getContextPath()%>/svg/Linkedine.svg">
				</a> <a class="pie-exito-link" href="#" aria-label="Instagram"> <img
					alt="Instagram"
					src="<%=request.getContextPath()%>/svg/Instagram.svg">
				</a>
			</div>
		</div>
	</footer>

	<!-- JS específico de éxito -->
	<script src="<%=request.getContextPath()%>/js/exito.js?v=1"></script>
</body>
</html>

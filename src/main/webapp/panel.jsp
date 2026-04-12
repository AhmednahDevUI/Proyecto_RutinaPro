<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Rutina Pro | Panel</title>

<!-- incluimos el favicons -->
<link rel="icon" href="<%=request.getContextPath()%>/img/favicon/favicon.ico?v=2" sizes="any">
<link rel="icon" type="image/png" sizes="32x32"
      href="<%=request.getContextPath()%>/img/favicon/favicon-32x32.png?v=2">
<link rel="icon" type="image/png" sizes="16x16"
      href="<%=request.getContextPath()%>/img/favicon/favicon-16x16.png?v=2">

<!-- Forzamos cargar el CSS/JS nuevo para que no se quede nada guardao en la cahe -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/panel.css?v=2">


</head>

<body class="cuerpo-panel">

	<!-- Boton de ayuda -->
	<a class="ayuda-flotante"
		href="mailto:ahmednahdesarrollador@gmail.com?subject=Ayuda%20Rutina%20Pro">
		ayuda</a>


	<!-- Contenedor principal del panel -->
	<main class="contenedor-panel">

		<!-- Cabecera principal -->
		<header class="cabecera-panel">
			<h1 class="titulo-panel">Panel de control</h1>
			<p class="subtitulo-panel">Este es tu espacio de gestión dentro
				de Rutina Pro. Desde aquí organizas tu entrenamiento, registras tu
				progreso y controlas tu evolución día a día.</p>
		</header>

		<!-- Bloque explicativo del panel -->
		<section class="bloque-info">
			<p>Cada acción que realices aquí quedará registrada en tu
				historial. La constancia y el seguimiento estructurado son la base
				del progreso real en el entrenamiento.</p>
		</section>

		<!-- Acciones principales del usuario -->
		<section class="acciones-panel">

			<!-- Crear rutina -->
			<a class="tarjeta-accion tarjeta-destacada"
				href="<%=request.getContextPath()%>/Rutina">
				<h2>Crear rutina</h2>
				<p>Define tu estructura de entrenamiento y empieza a registrar
					ejercicios.</p>
			</a>

			<!-- Ver historial -->
			<a class="tarjeta-accion tarjeta-destacada"
				href="<%=request.getContextPath()%>/Historial">
				<h2>Ver historial</h2>
				<p>Consulta tus entrenamientos anteriores y revisa tu progreso.</p>
			</a>

			<!-- Cerrar sesión -->
			<a class="tarjeta-accion tarjeta-secundaria"
				href="<%=request.getContextPath()%>/CerrarSesion">
				<h2>Cerrar sesión</h2>
				<p>Finaliza tu sesión actual de forma segura.</p>
			</a>

		</section>

		<p id="pistaPanel" class="pista-panel"></p>

	</main>

	<footer class="pie-panel">
		<div class="pie-panel-contenido">
			<p class="pie-panel-marca" id="marcaPie">© 2026 Rutina Pro</p>

			<div class="pie-panel-derecha">
				<a class="pie-panel-link" href="https://github.com/AhmednahDevUI"
					target="_blank"><img alt="Github" src="<%=request.getContextPath()%>/svg/Github.svg"></a> <a
					class="pie-panel-link"
					href="https://www.linkedin.com/in/ahmednah-mohamed-saleh-87361a36b?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app"
					target="_blank"> <img alt="Linkedin" src="<%=request.getContextPath()%>/svg/Linkedine.svg">
				</a> <a class="pie-panel-link" href="https://www.instagram.com/ahmednahdesarrollador/" target="_blank"> <img
					alt="Instagram" src="<%=request.getContextPath()%>/svg/Instagram.svg"></a>
			</div>
		</div>
	</footer>


	<!-- Forzamos cargar el CSS/JS nuevo para que no se quede nada guardao en la cahe -->
	<script src="<%=request.getContextPath()%>/js/panel.js?v=2"></script>

</body>
</html>

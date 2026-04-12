<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Rutina Pro</title>
<!-- incluimos el favicons -->
<link rel="icon" href="<%=request.getContextPath()%>/img/favicon/favicon.ico?v=2" sizes="any">
<link rel="icon" type="image/png" sizes="32x32"
      href="<%=request.getContextPath()%>/img/favicon/favicon-32x32.png?v=2">
<link rel="icon" type="image/png" sizes="16x16"
      href="<%=request.getContextPath()%>/img/favicon/favicon-16x16.png?v=2">





<!-- Forzamos cargar el CSS/JS nuevo para que no se quede nada guardao en la cahe -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/index.css?v=2">


</head>
<body class="cuerpo-inicio">

<!-- Boton de ayuda -->
	<a class="ayuda-flotante"
   		href="mailto:ahmednahdesarrollador@gmail.com?subject=Ayuda%20Rutina%20Pro">
  		ayuda</a>

	<!-- Contenedor principal centrado -->
	<main class="contenedor-inicio">

		<h1 class="titulo-inicio">Entrena con método progresa con datos.</h1>
		<p class="eslogan-inicio">Crea rutinas, registra entrenamientos y
			controla tu progreso.</p>

		<p class="descripcion-inicio">Rutina Pro es tu espacio digital
			para entrenar con estructura. Crea rutinas, registra cada
			entrenamiento y consulta tu progreso cuando quieras. Menos
			improvisación. Más constancia. Más resultados.</p>

		<section class="seccion-funciones">
			<h2 class="titulo-funciones">Lo que puedes hacer</h2>
			<ul class="lista-funciones">
				<li>Crear rutinas y entrenamientos asociados.</li>
				<li>Registrar ejercicios con detalle en cada sesión.</li>
				<li>Consultar tu historial para ver lo que hiciste días atrás.</li>
			</ul>
		</section>

		<ul class="acciones-inicio">
			<!-- Rutas del backend: no modificar -->
			<li><a class="boton-principal" href="<%=request.getContextPath()%>/Registrarse">Registrarse</a></li>
			<li><a class="boton-secundario" href="<%=request.getContextPath()%>/IniciarSesion">Iniciar sesión</a></li>
		</ul>

		<p id="pistaJs" class="pista-inicio"></p>
	</main>

	<footer class="pie-inicio">
		<div class="pie-contenido">
			<p class="pie-marca" id="marcaPie">© 2026 Rutina Pro</p>
			<p class="pie-autor" id="pie-autor">Desarrollado por Ahmednah Mohamed Saleh —
				Frontend &amp; Backend Junior</p>
		</div>
	</footer>

	<!-- Forzamos cargar el CSS/JS nuevo para que no se quede nada guardao en la cahe -->
	<script src="<%=request.getContextPath()%>/js/index.js?v=2"></script>

</body>
</html>

<%@ page contentType="text/html; charset=UTF-8"%>
<%
HttpSession sesion = request.getSession(false);

// Si no hay sesión o no hay usuario autenticado ➡️ login
if (sesion == null || sesion.getAttribute("usuarioId") == null) {
	response.sendRedirect(request.getContextPath() + "/IniciarSesion");
	return;
}

// Si no es nuevo ➡️ panel (evitar entrar por URL)
Boolean esNuevo = (Boolean) sesion.getAttribute("esNuevo");
if (esNuevo == null || !esNuevo) {
	response.sendRedirect(request.getContextPath() + "/panel.jsp");
	return;
}
%>



<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Rutina Pro | Bienvenida</title>

<!-- incluimos el favicons -->
<link rel="icon" href="<%=request.getContextPath()%>/img/favicon/favicon.ico?v=2" sizes="any">
<link rel="icon" type="image/png" sizes="32x32"
      href="<%=request.getContextPath()%>/img/favicon/favicon-32x32.png?v=2">
<link rel="icon" type="image/png" sizes="16x16"
      href="<%=request.getContextPath()%>/img/favicon/favicon-16x16.png?v=2">

<!-- Forzamos cargar el CSS/JS nuevo para que no se quede nada guardao en la cahe -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bienvenida.css?v=2">

</head>

<!-- Boton de ayuda -->
<body class="cuerpo-bienvenida">


	<!-- Boton de ayuda -->
	<a class="ayuda-flotante"
   		href="mailto:ahmednahdesarrollador@gmail.com?subject=Ayuda%20Rutina%20Pro">
  		ayuda</a>

	<main class="contenedor-bienvenida">

		<header class="cabecera-bienvenida">
			<p class="etiqueta">Cuenta creada</p>
			<h1 class="titulo-bienvenida">Bienvenido a Rutina
				Pro</h1>
			<p class="subtitulo-bienvenida">Has dado el primer paso para
				entrenar con método. Rutina Pro te permite organizar tus sesiones,
				registrar cada ejercicio y construir un historial real de tu
				progreso. La constancia no es motivación es estructura. Y aquí
				empiezas a crearla.</p>
		</header>

		<section class="bloque-pasos">
			<h2 class="titulo-pasos">Primer paso recomendado</h2>
			<p class="texto-pasos">Cuando crees tu primera rutina podrás
				añadir entrenamientos y ejercicios asociados. Cada acción quedará
				registrada en tu historial para que puedas analizar tu evolución con
				datos reales, no con memoria.</p>

			<ul class="lista-beneficios">
				<li>Rutinas organizadas por entrenamientos y ejercicios.</li>
				<li>Historial para consultar lo que hiciste días atrás.</li>
				<li>Progreso claro: menos improvisación, más constancia.</li>
			</ul>
		</section>

		<div class="acciones-bienvenida">
			<!-- Dirigir a una rutina" -->
			<a class="boton-principal" href="nuevaRutina.jsp">Crear mi primera rutina</a>

			
		</div>

		<p id="pistaBienvenida" class="pista-bienvenida"></p>

	</main>

	<!-- Pie de página -->
	<footer class="pie-bienvenida">
		<div class="pie-contenido">
			<p class="pie-marca" id="marcaPie">© 2026 Rutina Pro</p>
			<p class="pie-autor" id="autorPie">Desarrollado por Ahmednah
				Mohamed Saleh — Frontend &amp; Backend Junior</p>
		</div>
	</footer>


	<!-- Forzamos cargar el CSS/JS nuevo para que no se quede nada guardao en la cahe -->
	<script src="<%=request.getContextPath()%>/js/bienvenida.js?v=2"></script>

</body>
</html>

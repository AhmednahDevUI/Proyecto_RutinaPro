<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Rutina Pro | Registro</title>
  <!-- incluimos el favicons -->
<link rel="icon" href="<%=request.getContextPath()%>/img/favicon/favicon.ico?v=2" sizes="any">
<link rel="icon" type="image/png" sizes="32x32"
      href="<%=request.getContextPath()%>/img/favicon/favicon-32x32.png?v=2">
<link rel="icon" type="image/png" sizes="16x16"
      href="<%=request.getContextPath()%>/img/favicon/favicon-16x16.png?v=2">
  
  <!-- Forzamos cargar el CSS/JS nuevo para que no se quede nada guardao en la cahe -->
  <link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/registrar.css?v=2">
  

</head>

<body class="cuerpo-registro">
  <main class="contenedor-registro">

    <header class="cabecera-registro">
      <h1 class="titulo-registro">Regístrate</h1>
      <p class="subtitulo-registro">Crea tu cuenta para empezar a guardar rutinas y entrenamientos.</p>
    </header>

    <%
      String errorGeneral = (String) request.getAttribute("errorGeneral");
      if (errorGeneral != null) {
    %>
      <div class="alerta alerta-error"><%=errorGeneral%></div>
    <%
      }
    %>

    <!-- Mantener action para no romper backend -->
    <form class="formulario-registro" action="<%=request.getContextPath()%>/Registrarse" method="post" novalidate>

      <div class="campo">
        <label for="nombre">Nombre</label>
        <input
          id="nombre"
          type="text"
          name="nombre"
          required
          value="<%=request.getAttribute("nombrePrevio") != null ? request.getAttribute("nombrePrevio") : ""%>"
        >
        <%
          String errorNombre = (String) request.getAttribute("errorNombre");
          if (errorNombre != null) {
        %>
          <small class="error-campo"><%=errorNombre%></small>
        <%
          }
        %>
      </div>

      <div class="campo">
        <label for="email">Email</label>
        <input
          id="email"
          type="email"
          name="email"
          required
          value="<%=request.getAttribute("emailPrevio") != null ? request.getAttribute("emailPrevio") : ""%>"
        >
        <%
          String errorEmail = (String) request.getAttribute("errorEmail");
          if (errorEmail != null) {
        %>
          <small class="error-campo"><%=errorEmail%></small>
        <%
          }
        %>
      </div>

      <div class="campo">
        <label for="password">Password</label>
        <div class="fila-password">
          <input id="password" type="password" name="password" required>
          <button class="boton-password" type="button" aria-label="Mostrar u ocultar contraseña">Mostrar</button>
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

      <button class="boton-principal" type="submit">Crear</button>

      <p id="pistaFormulario" class="pista-formulario"></p>
    </form>

    <footer class="pie-registro">
      <p class="texto-pie">¿Ya tienes cuenta?</p>
			<a class="enlace" href="<%=request.getContextPath()%>/IniciarSesion">Inicia
				sesión</a> <span class="punto-separador">•</span> <a class="enlace"
				href="<%=request.getContextPath()%>/index.jsp">Volver al inicio</a>
	</footer>

  </main>

<!-- Forzamos cargar el CSS/JS nuevo para que no se quede nada guardao en la cahe -->
<script src="<%=request.getContextPath()%>/js/registrar.js?v=2"></script>
</body>
</html>

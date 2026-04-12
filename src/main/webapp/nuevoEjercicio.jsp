<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Rutina Pro | Añadir ejercicio</title>

  <!-- favicons -->
  <link rel="icon" href="<%=request.getContextPath()%>/img/favicon/favicon.ico?v=2" sizes="any">
  <link rel="icon" type="image/png" sizes="32x32"
        href="<%=request.getContextPath()%>/img/favicon/favicon-32x32.png?v=2">
  <link rel="icon" type="image/png" sizes="16x16"
        href="<%=request.getContextPath()%>/img/favicon/favicon-16x16.png?v=2">

  <!-- CSS de ejercicio -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/ejercicio.css?v=2">
</head>

<body class="cuerpo-ejercicio">

  <!-- boton de ayuda (si quieres mantenerlo como en otros JSP) -->
  <a class="ayuda-flotante"
     href="mailto:ahmednahdesarrollador@gmail.com?subject=Ayuda%20Rutina%20Pro">
    ayuda
  </a>

  <main class="contenedor-ejercicio">

    <header class="cabecera-ejercicio">
      <h1 class="titulo-ejercicio">Añadir ejercicio</h1>
      <p class="subtitulo-ejercicio">
        Selecciona el ejercicio y, si quieres, añade repeticiones o duración. Así tu entrenamiento queda completo y fácil de revisar.
      </p>
    </header>

    <%
      String errorGeneral = (String) request.getAttribute("errorGeneral");
      if (errorGeneral != null) {
    %>
      <div class="alerta alerta-error"><%=errorGeneral%></div>
    <%
      }

      String errorNombre = (String) request.getAttribute("errorNombre");
      String errorReps = (String) request.getAttribute("errorRepeticiones");
      String errorDur = (String) request.getAttribute("errorDuracion");

      String nombrePrevio = (String) request.getAttribute("nombrePrevio");
      String repsPrevio = (String) request.getAttribute("repeticionesPrevio");
      String durPrevio = (String) request.getAttribute("duracionPrevio");
    %>

    <form action="<%=request.getContextPath()%>/Ejercicio" method="post" class="formulario-ejercicio">

      <!-- NOMBRE (SELECT) -->
      <div class="campo">
        <label for="nombre">Nombre del ejercicio</label>

        <select id="nombre" name="nombre" class="campo-select" required>
  <option value="" disabled <%= (nombrePrevio == null || nombrePrevio.trim().isEmpty()) ? "selected" : "" %>>
    Selecciona un ejercicio…
  </option>

  <!-- ========================= GIMNASIO ========================= -->
  <optgroup label="Gimnasio">
    <option disabled>──────── PIERNAS ────────</option>
    <option value="Sentadilla" <%= "Sentadilla".equals(nombrePrevio) ? "selected" : "" %>>Sentadilla</option>
    <option value="Sentadilla búlgara" <%= "Sentadilla búlgara".equals(nombrePrevio) ? "selected" : "" %>>Sentadilla búlgara</option>
    <option value="Prensa de pierna" <%= "Prensa de pierna".equals(nombrePrevio) ? "selected" : "" %>>Prensa de pierna</option>
    <option value="Extensiones de cuádriceps" <%= "Extensiones de cuádriceps".equals(nombrePrevio) ? "selected" : "" %>>Extensiones de cuádriceps</option>
    <option value="Curl femoral" <%= "Curl femoral".equals(nombrePrevio) ? "selected" : "" %>>Curl femoral</option>
    <option value="Peso muerto" <%= "Peso muerto".equals(nombrePrevio) ? "selected" : "" %>>Peso muerto</option>
    <option value="Peso muerto rumano" <%= "Peso muerto rumano".equals(nombrePrevio) ? "selected" : "" %>>Peso muerto rumano</option>
    <option value="Hip thrust" <%= "Hip thrust".equals(nombrePrevio) ? "selected" : "" %>>Hip thrust</option>
    <option value="Zancadas" <%= "Zancadas".equals(nombrePrevio) ? "selected" : "" %>>Zancadas</option>
    <option value="Elevaciones de gemelo" <%= "Elevaciones de gemelo".equals(nombrePrevio) ? "selected" : "" %>>Elevaciones de gemelo</option>

    <option disabled>──────── PECHO ────────</option>
    <option value="Press banca" <%= "Press banca".equals(nombrePrevio) ? "selected" : "" %>>Press banca</option>
    <option value="Press inclinado" <%= "Press inclinado".equals(nombrePrevio) ? "selected" : "" %>>Press inclinado</option>
    <option value="Press declinado" <%= "Press declinado".equals(nombrePrevio) ? "selected" : "" %>>Press declinado</option>
    <option value="Aperturas con mancuernas" <%= "Aperturas con mancuernas".equals(nombrePrevio) ? "selected" : "" %>>Aperturas con mancuernas</option>
    <option value="Fondos en paralelas" <%= "Fondos en paralelas".equals(nombrePrevio) ? "selected" : "" %>>Fondos en paralelas</option>

    <option disabled>──────── ESPALDA ────────</option>
    <option value="Dominadas" <%= "Dominadas".equals(nombrePrevio) ? "selected" : "" %>>Dominadas</option>
    <option value="Jalón al pecho" <%= "Jalón al pecho".equals(nombrePrevio) ? "selected" : "" %>>Jalón al pecho</option>
    <option value="Remo con barra" <%= "Remo con barra".equals(nombrePrevio) ? "selected" : "" %>>Remo con barra</option>
    <option value="Remo en máquina" <%= "Remo en máquina".equals(nombrePrevio) ? "selected" : "" %>>Remo en máquina</option>
    <option value="Pulóver con mancuerna" <%= "Pulóver con mancuerna".equals(nombrePrevio) ? "selected" : "" %>>Pulóver con mancuerna</option>

    <option disabled>──────── HOMBROS ────────</option>
    <option value="Press militar" <%= "Press militar".equals(nombrePrevio) ? "selected" : "" %>>Press militar</option>
    <option value="Elevaciones laterales" <%= "Elevaciones laterales".equals(nombrePrevio) ? "selected" : "" %>>Elevaciones laterales</option>
    <option value="Elevaciones frontales" <%= "Elevaciones frontales".equals(nombrePrevio) ? "selected" : "" %>>Elevaciones frontales</option>
    <option value="Elevaciones posteriores" <%= "Elevaciones posteriores".equals(nombrePrevio) ? "selected" : "" %>>Elevaciones posteriores</option>

    <option disabled>──────── BÍCEPS ────────</option>
    <option value="Curl bíceps con barra" <%= "Curl bíceps con barra".equals(nombrePrevio) ? "selected" : "" %>>Curl bíceps con barra</option>
    <option value="Curl martillo" <%= "Curl martillo".equals(nombrePrevio) ? "selected" : "" %>>Curl martillo</option>
    <option value="Curl en banco inclinado" <%= "Curl en banco inclinado".equals(nombrePrevio) ? "selected" : "" %>>Curl en banco inclinado</option>

    <option disabled>──────── TRÍCEPS ────────</option>
    <option value="Extensión de tríceps en polea" <%= "Extensión de tríceps en polea".equals(nombrePrevio) ? "selected" : "" %>>Extensión de tríceps en polea</option>
    <option value="Press francés" <%= "Press francés".equals(nombrePrevio) ? "selected" : "" %>>Press francés</option>
    <option value="Fondos en banco" <%= "Fondos en banco".equals(nombrePrevio) ? "selected" : "" %>>Fondos en banco</option>
    <option value="Patada de tríceps" <%= "Patada de tríceps".equals(nombrePrevio) ? "selected" : "" %>>Patada de tríceps</option>

    <option disabled>──────── ABDOMEN ────────</option>
    <option value="Plancha" <%= "Plancha".equals(nombrePrevio) ? "selected" : "" %>>Plancha</option>
    <option value="Crunch abdominal" <%= "Crunch abdominal".equals(nombrePrevio) ? "selected" : "" %>>Crunch abdominal</option>
    <option value="Elevaciones de piernas" <%= "Elevaciones de piernas".equals(nombrePrevio) ? "selected" : "" %>>Elevaciones de piernas</option>
  </optgroup>

  <!-- ========================= BOXEO ========================= -->
  <optgroup label="Boxeo">
    <option disabled>──────── TÉCNICA ────────</option>
    <option value="Sombra" <%= "Sombra".equals(nombrePrevio) ? "selected" : "" %>>Sombra</option>
    <option value="Jab-Cross" <%= "Jab-Cross".equals(nombrePrevio) ? "selected" : "" %>>Jab-Cross</option>
    <option value="Ganchos" <%= "Ganchos".equals(nombrePrevio) ? "selected" : "" %>>Ganchos</option>
    <option value="Uppercuts" <%= "Uppercuts".equals(nombrePrevio) ? "selected" : "" %>>Uppercuts</option>
    <option value="Combinaciones" <%= "Combinaciones".equals(nombrePrevio) ? "selected" : "" %>>Combinaciones</option>

    <option disabled>──────── CARDIO ────────</option>
    <option value="Comba" <%= "Comba".equals(nombrePrevio) ? "selected" : "" %>>Comba</option>
    <option value="Golpes al saco" <%= "Golpes al saco".equals(nombrePrevio) ? "selected" : "" %>>Golpes al saco</option>

    <option disabled>──────── DEFENSA Y MOVILIDAD ────────</option>
    <option value="Defensa" <%= "Defensa".equals(nombrePrevio) ? "selected" : "" %>>Defensa</option>
    <option value="Desplazamientos" <%= "Desplazamientos".equals(nombrePrevio) ? "selected" : "" %>>Desplazamientos</option>
    <option value="Esquivas" <%= "Esquivas".equals(nombrePrevio) ? "selected" : "" %>>Esquivas</option>
  </optgroup>

  <!-- ========================= FÚTBOL ========================= -->
  <optgroup label="Fútbol">
    <option disabled>──────── CALENTAMIENTO ────────</option>
    <option value="Movilidad" <%= "Movilidad".equals(nombrePrevio) ? "selected" : "" %>>Movilidad</option>
    <option value="Activación" <%= "Activación".equals(nombrePrevio) ? "selected" : "" %>>Activación</option>

    <option disabled>──────── VELOCIDAD ────────</option>
    <option value="Sprints" <%= "Sprints".equals(nombrePrevio) ? "selected" : "" %>>Sprints</option>
    <option value="Cambios de ritmo" <%= "Cambios de ritmo".equals(nombrePrevio) ? "selected" : "" %>>Cambios de ritmo</option>
    <option value="Aceleraciones" <%= "Aceleraciones".equals(nombrePrevio) ? "selected" : "" %>>Aceleraciones</option>

    <option disabled>──────── TÉCNICA ────────</option>
    <option value="Pases" <%= "Pases".equals(nombrePrevio) ? "selected" : "" %>>Pases</option>
    <option value="Conducción" <%= "Conducción".equals(nombrePrevio) ? "selected" : "" %>>Conducción</option>
    <option value="Tiros a puerta" <%= "Tiros a puerta".equals(nombrePrevio) ? "selected" : "" %>>Tiros a puerta</option>

    <option disabled>──────── AGILIDAD ────────</option>
    <option value="Circuito de conos" <%= "Circuito de conos".equals(nombrePrevio) ? "selected" : "" %>>Circuito de conos</option>
    <option value="Saltos" <%= "Saltos".equals(nombrePrevio) ? "selected" : "" %>>Saltos</option>
  </optgroup>

  <!-- ========================= TENIS ========================= -->
  <optgroup label="Tenis">
    <option disabled>──────── GOLPES ────────</option>
    <option value="Saque" <%= "Saque".equals(nombrePrevio) ? "selected" : "" %>>Saque</option>
    <option value="Derecha" <%= "Derecha".equals(nombrePrevio) ? "selected" : "" %>>Derecha</option>
    <option value="Revés" <%= "Revés".equals(nombrePrevio) ? "selected" : "" %>>Revés</option>
    <option value="Volea" <%= "Volea".equals(nombrePrevio) ? "selected" : "" %>>Volea</option>

    <option disabled>──────── DESPLAZAMIENTO ────────</option>
    <option value="Desplazamientos laterales" <%= "Desplazamientos laterales".equals(nombrePrevio) ? "selected" : "" %>>Desplazamientos laterales</option>
    <option value="Split step" <%= "Split step".equals(nombrePrevio) ? "selected" : "" %>>Split step</option>

    <option disabled>──────── COORDINACIÓN ────────</option>
    <option value="Trabajo de pies" <%= "Trabajo de pies".equals(nombrePrevio) ? "selected" : "" %>>Trabajo de pies</option>
  </optgroup>

  <!-- ========================= CICLISMO ========================= -->
  <optgroup label="Ciclismo">
    <option disabled>──────── RESISTENCIA ────────</option>
    <option value="Rodaje suave" <%= "Rodaje suave".equals(nombrePrevio) ? "selected" : "" %>>Rodaje suave</option>
    <option value="Rodaje medio" <%= "Rodaje medio".equals(nombrePrevio) ? "selected" : "" %>>Rodaje medio</option>

    <option disabled>──────── INTENSIDAD ────────</option>
    <option value="Intervalos" <%= "Intervalos".equals(nombrePrevio) ? "selected" : "" %>>Intervalos</option>
    <option value="Series cortas" <%= "Series cortas".equals(nombrePrevio) ? "selected" : "" %>>Series cortas</option>

    <option disabled>──────── SUBIDAS ────────</option>
    <option value="Subidas" <%= "Subidas".equals(nombrePrevio) ? "selected" : "" %>>Subidas</option>
    <option value="Fuerza en subida" <%= "Fuerza en subida".equals(nombrePrevio) ? "selected" : "" %>>Fuerza en subida</option>

    <option disabled>──────── TÉCNICA ────────</option>
    <option value="Cadencia" <%= "Cadencia".equals(nombrePrevio) ? "selected" : "" %>>Cadencia</option>
    <option value="Rodillo" <%= "Rodillo".equals(nombrePrevio) ? "selected" : "" %>>Rodillo</option>
  </optgroup>

  <!-- ========================= BÁSQUET ========================= -->
  <optgroup label="Básquet">
    <option disabled>──────── TIRO ────────</option>
    <option value="Tiro" <%= "Tiro".equals(nombrePrevio) ? "selected" : "" %>>Tiro</option>
    <option value="Tiro en suspensión" <%= "Tiro en suspensión".equals(nombrePrevio) ? "selected" : "" %>>Tiro en suspensión</option>
    <option value="Tiros libres" <%= "Tiros libres".equals(nombrePrevio) ? "selected" : "" %>>Tiros libres</option>

    <option disabled>──────── ATAQUE ────────</option>
    <option value="Entradas" <%= "Entradas".equals(nombrePrevio) ? "selected" : "" %>>Entradas</option>
    <option value="Finalizaciones" <%= "Finalizaciones".equals(nombrePrevio) ? "selected" : "" %>>Finalizaciones</option>

    <option disabled>──────── MANEJO ────────</option>
    <option value="Manejo de balón" <%= "Manejo de balón".equals(nombrePrevio) ? "selected" : "" %>>Manejo de balón</option>
    <option value="Cambios de mano" <%= "Cambios de mano".equals(nombrePrevio) ? "selected" : "" %>>Cambios de mano</option>

    <option disabled>──────── DEFENSA Y FÍSICO ────────</option>
    <option value="Defensa" <%= "Defensa".equals(nombrePrevio) ? "selected" : "" %>>Defensa</option>
    <option value="Rebote" <%= "Rebote".equals(nombrePrevio) ? "selected" : "" %>>Rebote</option>
    <option value="Suicides" <%= "Suicides".equals(nombrePrevio) ? "selected" : "" %>>Suicides</option>
  </optgroup>
</select>

        <%
          if (errorNombre != null) {
        %>
          <small class="error-campo"><%=errorNombre%></small>
        <%
          }
        %>
      </div>

      <!-- REPETICIONES -->
      <div class="campo">
        <label for="repeticiones">Repeticiones (opcional)</label>
        <input id="repeticiones" type="number" name="repeticiones" min="0"
               value="<%= (repsPrevio != null) ? repsPrevio : "" %>">

        <%
          if (errorReps != null) {
        %>
          <small class="error-campo"><%=errorReps%></small>
        <%
          }
        %>
      </div>

      <!-- DURACIÓN -->
      <div class="campo">
        <label for="duracion_segundos">Duración en segundos (opcional)</label>
        <input id="duracion_segundos" type="number" name="duracion_segundos" min="0"
               value="<%= (durPrevio != null) ? durPrevio : "" %>">

        <%
          if (errorDur != null) {
        %>
          <small class="error-campo"><%=errorDur%></small>
        <%
          }
        %>
      </div>

			<div class="acciones">
				<button class="boton-principal" type="submit">Añadir
					ejercicio</button>
				<a class="boton-secundario" href="<%=request.getContextPath()%>/Volver">Volver</a>
			</div>

			<p id="pistaEjercicio" class="pista-ejercicio"></p>

    </form>

  </main>

  <!-- JS de ejercicio -->
  <script src="<%=request.getContextPath()%>/js/ejercicio.js?v=1"></script>
</body>
</html>
// JS del inicio: saludo por hora + animación + mensajes al pasar por botones
document.addEventListener("DOMContentLoaded", () => {
	
	//Obtenemos los elementos del DOM
  const pista = document.getElementById("pistaJs");
  const contenedor = document.querySelector(".contenedor-inicio");
  const contenedorfoter = document.querySelector(".pie-contenido");

  const botonRegistro = document.querySelector('a[href="Registrarse"]');
  const botonLogin = document.querySelector('a[href="IniciarSesion"]');

  //Gestionamos un saludo depende de la hora
  const hora = new Date().getHours();
  const saludo =
    (hora >= 6 && hora < 14) ? "Buenos días" :
    (hora >= 14 && hora < 21) ? "Buenas tardes" :
    "Buenas noches";

	//Este es otro saludo pero no cambia (estatico por asi decirlo)
  const mensajeBase = `${saludo}. Regístrate o inicia sesión para comenzar.`;
  if (pista) pista.textContent = mensajeBase;

  // Animación de entrada sin librerías
  if (contenedor) {
    contenedor.style.opacity = "0";
    contenedor.style.transform = "translateY(10px)";
    setTimeout(() => {
      contenedor.style.transition = "opacity 0.35s ease, transform 0.35s ease";
      contenedor.style.opacity = "1";
      contenedor.style.transform = "translateY(0)";
    }, 60);
  }

  // Mensajes en hover, si estas encima de el boton registrar te dice lo siguente
  if (botonRegistro && pista) {
    botonRegistro.addEventListener("mouseenter", () => {
      pista.textContent = "Crea tu cuenta y organiza tu rutina desde el primer día.";
    });
    botonRegistro.addEventListener("mouseleave", () => {
      pista.textContent = mensajeBase;
    });
  }

  //Encambio si estas en el de iniciar sesion te dice otro distinto
  if (botonLogin && pista) {
    botonLogin.addEventListener("mouseenter", () => {
      pista.textContent = "Accede para ver tu panel e historial de entrenamientos.";
    });
    botonLogin.addEventListener("mouseleave", () => {
      pista.textContent = mensajeBase;
    });
  }
  
  //Operador ternario, para que parpadear suavemente el texto de la marca
  const maca = document.getElementById("marcaPie");
  console.log(maca);
  if(maca){
	let encendido = false;
	setInterval(() =>{
		//Interuptor on/of
		encendido = !encendido;
		maca.style.transition = "color 0.35s ease";
		maca.style.color = encendido ? "#FFDD00" : "#e9e9f3";
	}, 1200);
   }
});

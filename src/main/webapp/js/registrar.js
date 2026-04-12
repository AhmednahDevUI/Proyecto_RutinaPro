// JS de registro: ayuda visual + mostrar/ocultar contraseña (el backend valida de verdad)
document.addEventListener("DOMContentLoaded", () => {
	
 //Seleccionamos los elementos del formulario
  const pista = document.getElementById("pistaFormulario");
  const contenedor = document.querySelector(".contenedor-registro"); 
  const nombre = document.querySelector('input[name="nombre"]');
  const email = document.querySelector('input[name="email"]');
  const password = document.querySelector('input[name="password"]');

  const botonPassword = document.querySelector(".boton-password");

  if (pista) pista.textContent = "Consejo: usa un email real y una contraseña segura.";

  //Marcamos el estado si los valores del campo son correctos o no
  const marcarEstado = (elemento, correcto) => {
    if (!elemento) return;
    elemento.style.borderColor = correcto
      ? "rgba(255, 221, 0, 0.75)"
      : "rgba(255, 92, 92, 0.75)";
  };

  //Obligamos que introduzva un email valido
  const emailValido = (valor) => /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/.test(valor);

  //validamos mientras escribe (En tiempo real)
  if (nombre) nombre.addEventListener("input", () => marcarEstado(nombre, nombre.value.trim().length >= 2));
  if (email) email.addEventListener("input", () => marcarEstado(email, emailValido(email.value.trim())));
  if (password) password.addEventListener("input", () => marcarEstado(password, password.value.length >= 8));

  
  //Mostramos o ocultamos contrseña
  if (botonPassword && password) {
    botonPassword.addEventListener("click", () => {
      const oculto = password.getAttribute("type") === "password";
      password.setAttribute("type", oculto ? "text" : "password");
      botonPassword.textContent = oculto ? "Ocultar" : "Mostrar";
    });
  }
  
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
  
});

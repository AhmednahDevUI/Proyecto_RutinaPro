// JS de login: micro-UX + mostrar/ocultar contraseña (backend valida de verdad)
document.addEventListener("DOMContentLoaded", () => {
 //Seleccionamos los elementos del DOM
  const pista = document.getElementById("pistaLogin");
  const password = document.querySelector('input[name="password"]');
  const botonPassword = document.querySelector(".boton-password");
  const contenedor = document.querySelector(".contenedor-login");
  //Mostramos un mesaje
  if (pista) pista.textContent = "Introduce tus credenciales para acceder.";

  //Esto es el boton para mostra o cultar el contenido de la password
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

// JS exito animacion entrada mensaje y redes
document.addEventListener("DOMContentLoaded", () => {

  const contenedor = document.querySelector(".contenedor-exito");
  const pista = document.getElementById("pistaExito");

  // hago que la tarjeta entre suave al cargar
  if(contenedor){
    contenedor.style.opacity="0";
    contenedor.style.transform="translateY(12px)";
    setTimeout(()=>{
      contenedor.style.transition="opacity .4s ease, transform .4s ease";
      contenedor.style.opacity="1";
      contenedor.style.transform="translateY(0)";
    },80);
  }

  // pongo un mensaje corto de refuerzo
  if(pista){
    pista.textContent="Tip mañana repite y registra otra sesion para mantener constancia y ver tu progreso.";
  }

  // hago que las redes del footer brillen una por una
  const redes = document.querySelectorAll(".pie-exito-link");
  if(redes.length){
    let i = 0;
    setInterval(() => {
      redes.forEach(r => r.classList.remove("parpadeo-red"));
      redes[i].classList.add("parpadeo-red");
      i = (i + 1) % redes.length;
    },1200);
  }

});

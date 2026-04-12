// JS entrenamiento animacion entrada guia y redes
document.addEventListener("DOMContentLoaded", () => {

  const contenedor = document.querySelector(".contenedor-entrenamiento");
  const pista = document.getElementById("pistaEntrenamiento");

  const inputFecha = document.querySelector('input[name="fecha"]');
  const inputDuracion = document.querySelector('input[name="duracion_total"]');
  const inputNotas = document.querySelector('textarea[name="notas"]');

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

  // pongo un consejo inicial
  if(pista){
    pista.textContent="Consejo registra la duracion real y si quieres deja una nota corta para recordar el entrenamiento.";
  }

  // marco fecha como valida cuando el usuario elige una
  if(inputFecha){
    inputFecha.addEventListener("change", () => {
      const ok = !!inputFecha.value;
      inputFecha.style.borderColor = ok ? "rgba(255,221,0,0.75)" : "rgba(255,92,92,0.75)";
    });
  }

  // valido la duracion y pongo borde segun sea correcto
  if(inputDuracion){
    inputDuracion.addEventListener("input", () => {
      const v = Number(inputDuracion.value);
      const ok = Number.isFinite(v) && v >= 0;
      inputDuracion.style.borderColor = ok ? "rgba(255,221,0,0.75)" : "rgba(255,92,92,0.75)";
    });
  }

  // si escribe notas le doy un mensaje corto
  if(inputNotas && pista){
    inputNotas.addEventListener("input", () => {
      const n = inputNotas.value.trim().length;
      if(n >= 8){
        pista.textContent="Perfecto esas notas te van a ayudar cuando revises el historial.";
      }else{
        pista.textContent="Puedes dejar una nota corta por ejemplo sensaciones peso o tecnica.";
      }
    });
  }

  // hago que las redes del footer brillen una por una
  const redes = document.querySelectorAll(".pie-entrenamiento-link");
  if(redes.length){
    let i = 0;
    setInterval(() => {
      redes.forEach(r => r.classList.remove("parpadeo-red"));
      redes[i].classList.add("parpadeo-red");
      i = (i + 1) % redes.length;
    },1200);
  }

});

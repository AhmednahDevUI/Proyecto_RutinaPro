// JS ejercicio animacion entrada guia y redes
document.addEventListener("DOMContentLoaded", () => {

  const contenedor = document.querySelector(".contenedor-ejercicio");
  const pista = document.getElementById("pistaEjercicio");

  const inputNombre = document.querySelector('input[name="nombre"]');
  const inputReps = document.querySelector('input[name="repeticiones"]');
  const inputDur = document.querySelector('input[name="duracion_segundos"]');

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
    pista.textContent="Consejo pon un nombre claro y si quieres añade reps o la duracion en segundos, para que el historial sea mas completo.";
  }

  // valido el nombre mientras escribe
  if(inputNombre){
    inputNombre.addEventListener("input", () => {
      const ok = inputNombre.value.trim().length >= 3;
      inputNombre.style.borderColor = ok ? "rgba(255,221,0,0.75)" : "rgba(255,92,92,0.75)";
    });
  }

  // si mete reps le doy un mensaje corto
  if(inputReps && pista){
    inputReps.addEventListener("input", () => {
      const v = Number(inputReps.value);
      if(Number.isFinite(v) && v > 0){
        pista.textContent="Bien reps registradas asi veras progreso en fuerza.";
      }else{
        pista.textContent="Reps es opcional si es cardio puedes dejarlo vacio.";
      }
    });
  }

  // si mete duracion le doy mensaje
  if(inputDur && pista){
    inputDur.addEventListener("input", () => {
      const v = Number(inputDur.value);
      if(Number.isFinite(v) && v > 0){
        pista.textContent="Perfecto duracion registrada para ejercicios de tiempo o cardio.";
      }else{
        pista.textContent="Duracion es opcional si es un ejercicio de reps puedes dejarlo vacio.";
      }
    });
  }

  // hago que las redes del footer brillen una por una
  const redes = document.querySelectorAll(".pie-ejercicio-link");
  if(redes.length){
    let i = 0;
    setInterval(() => {
      redes.forEach(r => r.classList.remove("parpadeo-red"));
      redes[i].classList.add("parpadeo-red");
      i = (i + 1) % redes.length;
    },1200);
  }

});

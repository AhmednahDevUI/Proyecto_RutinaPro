// JS crear rutina animacion entrada guia y efecto redes
document.addEventListener("DOMContentLoaded", () => {

  const pista = document.getElementById("pistaRutina");

  const inputNombre = document.querySelector('input[name="nombre"]');
  const selectTipo = document.querySelector('select[name="tipo"]');
  
  // pongo un consejo abajo
  if(pista){
    pista.textContent="Consejo usa un nombre claro ejemplo Fuerza Torso y elige bien el tipo.";
  }

  // valido el nombre mientras escribe y cambio color borde
  if(inputNombre){
    inputNombre.addEventListener("input", () => {
      const ok = inputNombre.value.trim().length >= 5;
      inputNombre.style.borderColor = ok 
        ? "rgba(255,221,0,0.75)" 
        : "rgba(255,92,92,0.75)";
    });
  }

  // cambio el mensaje segun el tipo que seleccione
  if(selectTipo && pista){
    selectTipo.addEventListener("change", () => {
      pista.textContent = (selectTipo.value === "BOXEO")
        ? "Boxeo registra rondas tecnica y cardio de forma constante."
        : "Gym organiza ejercicios por grupo muscular y aplica progresion.";
    });
  }

  // hago que las redes del footer brillen una por una
  const redes = document.querySelectorAll(".pie-rutina-link");
  if(redes.length){
	//Despues de recorer redes les giardo en i.
    let i = 0;
    setInterval(() => {
      redes.forEach(r => r.classList.remove("parpadeo-red"));
      redes[i].classList.add("parpadeo-red");
      i = (i + 1) % redes.length;
    },1200);
  }

});

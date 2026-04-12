// JS historial entrada suave plegado entrenos y parpadeo redes
document.addEventListener("DOMContentLoaded", () => {

  const pista = document.getElementById("pistaHistorial");

  // dejo un tip rapido
  if(pista){
    pista.textContent="Tip haz clic en la cabecera del entrenamiento para plegar o desplegar sus ejercicios.";
  }

  // plegado simple
  const cabeceras = document.querySelectorAll(".cabecera-entrenamiento");
  cabeceras.forEach(c => {
    c.addEventListener("click", () => {
      const tarjeta = c.closest(".bloque-entrenamiento");
      const lista = tarjeta ? tarjeta.querySelector(".lista-ejercicios") : null;
      if(!lista) return;

      const oculto = lista.style.display === "none";
      lista.style.display = oculto ? "grid" : "none";

      const flecha = c.querySelector(".flecha");
      if(flecha) flecha.textContent = oculto ? "▾" : "▸";
    });

    c.addEventListener("keydown", (e) => {
      if(e.key === "Enter" || e.key === " "){
        e.preventDefault();
        c.click();
      }
    });
  });

  // parpadeo en redes
  const redes = document.querySelectorAll(".pie-link");
  if(redes.length){
    let i = 0;
    setInterval(() => {
      redes.forEach(r => r.classList.remove("parpadeo-red"));
      redes[i].classList.add("parpadeo-red");
      i = (i + 1) % redes.length;
    },1200);
  }

});

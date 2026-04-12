
document.addEventListener("DOMContentLoaded", () => {

	//Seleccionamos los elementos del formulario
  const contenedor = document.querySelector(".contenedor-panel");
  const pista = document.getElementById("pistaPanel");

  //Animacion 
  if(contenedor){
    contenedor.style.opacity="0";
    contenedor.style.transform="translateY(12px)";
    setTimeout(()=>{
      contenedor.style.transition="opacity .4s ease, transform .4s ease";
      contenedor.style.opacity="1";
      contenedor.style.transform="translateY(0)";
    },80);
  }

  //Mesaje estrategico
  if(pista){
    pista.textContent="El progreso real comienza cuando registras tus entrenamientos. Empieza creando una rutina.";
  }

  //Operador ternario, para que parpadear suavemente el texto de la marca
    const marca = document.getElementById("marcaPie");
    if (!marca) return;

    let encendido = false;
    setInterval(() => {
      encendido = !encendido;
      marca.style.transition = "color 0.35s ease";
      marca.style.color = encendido ? "#FFDD00" : "#e9e9f3";
    }, 1200);
 

});

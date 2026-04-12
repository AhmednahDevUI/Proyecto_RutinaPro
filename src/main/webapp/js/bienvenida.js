// JS de bienvenida: animación + texto corto para guiar al usuario
document.addEventListener("DOMContentLoaded", () => {
	//Obtenemos los elementos del DOM
	const pista = document.getElementById("pistaBienvenida");

	//Mensaje para el usuario
	if (pista) {
		pista.textContent = "Consejo: crea tu primera rutina y después registra tu primer entrenamiento para que aparezca en el historial.";
	}

	// Efecto sutil en la marca del pie
	const marca = document.getElementById("marcaPie");
	if (marca) {
		let encendido = false;
		setInterval(() => {
			encendido = !encendido;
			marca.style.transition = "color 0.35s ease";
			marca.style.color = encendido ? "#FFDD00" : "#e9e9f3";
		}, 1200);
	}


	//creamos una variable raiz para escribir css
	const raiz = document.documentElement;
	//tiempo preciso en milisegundos
	let start = performance.now();

	//estas son las cordenadas les voy a llamar tx y ty (trail) y (cola)
	let tx = 30, ty = 20;
	//Definimos una funcion
	function animarLuz(t) {
		//Cuanto timpo a paso desde que empezo la animacion
		const s = (t - start) / 1000; // segundos

		//controla la velocidad y cuando se muve asi a los lados
		const lx = 50 + Math.sin(s * 0.28) * 38; // 50% ± 38%
		//igual que arriba pero que el movimiento este desfasado 
		const ly = 35 + Math.cos(s * 0.22) * 22; // 35% ± 22%

		//Que el color principar del body siga al amarillo para asi se genra el efecto
		tx += (lx - tx) * 0.04;
		ty += (ly - ty) * 0.04;

		//hacemos que la intensidad respire y controlamos la velocidad
		const breathe = 0.5 + 0.5 * Math.sin(s * 0.9); // 0..1
		//La intensidad del foco principal
		const i1 = 0.14 + breathe * 0.10; // 0.14..0.24
		const i2 = 0.06 + breathe * 0.06; // 0.06..0.12

		//Aqui vamos a pasar los valores al css
		raiz.style.setProperty("--lx", `${lx}%`);
		raiz.style.setProperty("--ly", `${ly}%`);
		raiz.style.setProperty("--tx", `${tx}%`);
		raiz.style.setProperty("--ty", `${ty}%`);
		raiz.style.setProperty("--i1", i1.toFixed(3));
		raiz.style.setProperty("--i2", i2.toFixed(3));

		//Esto le dice al navegado vuelve a ejecutar animarLuz en el siguiente frame.
		requestAnimationFrame(animarLuz);
	}

	requestAnimationFrame(animarLuz);



});

package service;

import dao.DataAccessException;
import dao.RutinaDAO;

/**
 * RutinaService Lógica de negocio para crear rutinas (validación + acceso a
 * DAO).
 */
public class RutinaService {
	// Mensajes reutilizables
	private static final String MSG_NOMBRE_OBLIGATORIO = "El nombre de la rutina es obligatorio.";
	private static final String MSG_NOMBRE_LARGO = "El nombre es demasiado largo (máx 100 caracteres).";
	private static final String MSG_NOMBRE_SIN_LETRAS = "Introduce un nombre válido (debe contener letras).";
	private static final String MSG_TIPO_OBLIGATORIO = "Debes seleccionar el tipo de rutina.";
	private static final String MSG_TIPO_INVALIDO = "Tipo de rutina inválido.";
	private static final String MSG_ERROR_TECNICO = "Disculpa, hay un fallo técnico en el sistema. Inténtalo más tarde.";
	private static final String MSG_ERROR_CREACION = "Disculpa, hay un fallo técnico en el sistema. Inténtalo más tarde.";

	public static class CrearRutinaResultado {
		public boolean ok;
		public Integer rutinaId;
		public String errorGeneral;
		public String errorNombre;
		public String errorTipo;
		public String nombreNormalizado;
		public String tipoNormalizado;
	}

	public CrearRutinaResultado crearRutina(int usuarioId, String nombre, String tipo) {
		CrearRutinaResultado r = new CrearRutinaResultado();

		// Normalización de inputs
		String n = (nombre == null) ? "" : nombre.trim();
		String t = (tipo == null) ? "" : tipo.trim();

		// Log para ver los datos antes de intentar insertar en la base de datos
		System.out.println("Intentando insertar la rutina con nombre: " + n + " y tipo: " + t);


		// Valores para repintar el formulario
		r.nombreNormalizado = n;
		r.tipoNormalizado = t;

		// Validación: nombre
		if (n.isEmpty()) {
			r.errorNombre = MSG_NOMBRE_OBLIGATORIO;
		} else if (n.length() > 100) {
			r.errorNombre = MSG_NOMBRE_LARGO;
		} else if (!contieneLetra(n)) {
			r.errorNombre = MSG_NOMBRE_SIN_LETRAS;
		}

		// Validación: tipo (solo valores válidos del ENUM en BD)
		if (t.isEmpty()) {
			r.errorTipo = MSG_TIPO_OBLIGATORIO;
		} else if (!esTipoValido(t)) {
			r.errorTipo = MSG_TIPO_INVALIDO;
		}

		// Si hay errores, no se toca la BD
		if (r.errorNombre != null || r.errorTipo != null) {
			r.ok = false;
			return r;
		}

		// Intentamos insertar en la base de datos
		try {
			// Log antes de insertar
			System.out.println("Intentando insertar la rutina en la base de datos...");

			// Log para ver los datos antes de insertar en la base de datos
			System.out.println("Creando rutina con nombre: " + n + " y tipo: " + t + " para el usuario: " + usuarioId);

			RutinaDAO dao = new RutinaDAO();
			int rutinaId = dao.crearRutinaYDevolverId(n, t, usuarioId);
			

			// Log después de la inserción
			if (rutinaId <= 0) {
				r.ok = false;
				r.errorGeneral = MSG_ERROR_CREACION;
				System.out.println("Error al crear rutina, ID no válido.");
				return r;
			}

			r.ok = true;
			r.rutinaId = rutinaId;
			System.out.println("Rutina creada con éxito, ID: " + rutinaId);

			return r;

		} catch (DataAccessException ex) {
			// Log de excepción
			System.err.println("Excepción al crear rutina: " + ex.getMessage());
			r.ok = false;
			r.errorGeneral = MSG_ERROR_TECNICO;
			return r;
		}
	}

	// Método para verificar el tipo de rutina
	private boolean esTipoValido(String tipo) {
		return "GIMNASIO".equals(tipo) || "BOXEO".equals(tipo) || "FUTBOL".equals(tipo) || "TENIS".equals(tipo)
				|| "CICLISMO".equals(tipo) || "BASKET".equals(tipo);
	}

	// Método que verifica si el nombre contiene al menos una letra
	private boolean contieneLetra(String s) {
		return s != null && s.matches(".*\\p{L}.*");
	}

	 /**
     * Método para eliminar una rutina del usuario.
     */
    public CrearRutinaResultado eliminarRutina(int usuarioId, int rutinaId) {
        CrearRutinaResultado r = new CrearRutinaResultado();

        if (rutinaId <= 0) {
            r.ok = false;
            r.errorGeneral = "Rutina inválida.";
            return r;
        }

        try {
            RutinaDAO dao = new RutinaDAO();

            // Verificar si la rutina pertenece al usuario
            if (!dao.existeRutinaDeUsuario(rutinaId, usuarioId)) {
                r.ok = false;
                r.errorGeneral = "Rutina no encontrada o no autorizada para este usuario.";
                return r;
            }

            // Eliminar la rutina de la base de datos
            boolean eliminada = dao.eliminarRutinaDeUsuario(rutinaId, usuarioId);

            if (eliminada) {
                r.ok = true;
            } else {
                r.ok = false;
                r.errorGeneral = "No se pudo eliminar la rutina.";
            }

            return r;

        } catch (DataAccessException ex) {
            r.ok = false;
            r.errorGeneral = "Error técnico al eliminar rutina.";
            return r;
        }
    }
}


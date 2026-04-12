package service;

import dao.DataAccessException;
import dao.EjercicioDAO;
import model.Ejercicio;

/**
 * EjercicioService
 * Validación y creación de ejercicios asociados a un entrenamiento.
 */
public class EjercicioService {

    private static final String MSG_NOMBRE_OBLIGATORIO = "El nombre del ejercicio es obligatorio.";
    private static final String MSG_NOMBRE_SIN_LETRAS = "Introduce un nombre válido (debe contener letras).";
    private static final String MSG_REPS_NEGATIVAS = "Las repeticiones no pueden ser negativas.";
    private static final String MSG_REPS_NUMERO = "Repeticiones inválidas (debe ser un número).";
    private static final String MSG_DUR_NEGATIVA = "La duración no puede ser negativa.";
    private static final String MSG_DUR_NUMERO = "Duración inválida (debe ser un número).";
    private static final String MSG_ERROR_CREACION = "No se pudo crear el ejercicio. Inténtalo más tarde.";
    private static final String MSG_ERROR_TECNICO = "Disculpa, hay un fallo técnico en el sistema. Inténtalo más tarde.";

    public static class CrearEjercicioResultado {
        public boolean ok;
        public Integer ejercicioId;

        public String errorGeneral;
        public String errorNombre;
        public String errorRepeticiones;
        public String errorDuracion;

        public String nombreNormalizado;
        public String repeticionesNormalizadas;
        public String duracionNormalizada;
    }

    /**
     * Crea un ejercicio asociado al entrenamientoId recibido (FK entrenamiento_id).
     */
    public CrearEjercicioResultado crearEjercicio(int entrenamientoId, String nombre, String repeticionesStr, String duracionStr) {

        CrearEjercicioResultado r = new CrearEjercicioResultado();

        // Normalización de inputs para repintar el formulario
        String n = (nombre == null) ? "" : nombre.trim();
        String repsTxt = (repeticionesStr == null) ? "" : repeticionesStr.trim();
        String durTxt = (duracionStr == null) ? "" : duracionStr.trim();

        r.nombreNormalizado = n;
        r.repeticionesNormalizadas = repsTxt;
        r.duracionNormalizada = durTxt;

        // Validación: nombre
        if (n.isEmpty()) {
            r.errorNombre = MSG_NOMBRE_OBLIGATORIO;
        } else if (!contieneLetra(n)) {
            r.errorNombre = MSG_NOMBRE_SIN_LETRAS;
        }

        // Validación: repeticiones (opcional)
        Integer reps = null;
        if (!repsTxt.isEmpty()) {
            reps = parseEntero(repsTxt);
            if (reps == null) r.errorRepeticiones = MSG_REPS_NUMERO;
            else if (reps < 0) r.errorRepeticiones = MSG_REPS_NEGATIVAS;
        }

        // Validación: duración en segundos (opcional)
        Integer dur = null;
        if (!durTxt.isEmpty()) {
            dur = parseEntero(durTxt);
            if (dur == null) r.errorDuracion = MSG_DUR_NUMERO;
            else if (dur < 0) r.errorDuracion = MSG_DUR_NEGATIVA;
        }

        // Si hay errores, no se toca la BD
        if (r.errorNombre != null || r.errorRepeticiones != null || r.errorDuracion != null) {
            r.ok = false;
            return r;
        }

        // Construcción del modelo para persistencia
        Ejercicio e = new Ejercicio();
        e.setNombre(n);
        e.setRepeticiones(reps);
        e.setDuracionSegundos(dur);
        e.setEntrenamientoId(entrenamientoId);

        // Inserción en BD
        try {
            EjercicioDAO dao = new EjercicioDAO();
            int nuevoId = dao.crearEjercicioYDevolverId(e);

            if (nuevoId <= 0) {
                r.ok = false;
                r.errorGeneral = MSG_ERROR_CREACION;
                return r;
            }

            r.ok = true;
            r.ejercicioId = nuevoId;
            return r;

        } catch (DataAccessException ex) {
            r.ok = false;
            r.errorGeneral = MSG_ERROR_TECNICO;
            return r;
        }
    }

    // True si contiene al menos una letra
    private boolean contieneLetra(String s) {
        return s != null && s.matches(".*\\p{L}.*");
    }

    // Parse de enteros devuelve null si no es número
    private Integer parseEntero(String s) {
        try { return Integer.parseInt(s); } catch (Exception e) { 
        	return null; }
    }
}

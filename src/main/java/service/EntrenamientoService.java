package service;

import dao.EntrenamientoDAO;
import dao.RutinaDAO;
import model.Entrenamiento;

public class EntrenamientoService {

    // Mensajes de error
    private static final String MSG_NOMBRE_OBLIGATORIO = "El nombre del entrenamiento es obligatorio.";
    private static final String MSG_NOMBRE_LARGO = "El nombre es demasiado largo (máx 100 caracteres).";
    private static final String MSG_NOMBRE_SIN_LETRAS = "Introduce un nombre válido (debe contener letras).";

    private static final String MSG_FECHA_OBLIGATORIA = "La fecha es obligatoria.";
    private static final String MSG_FECHA_INVALIDA = "Formato de fecha inválido (YYYY-MM-DD).";

    private static final String MSG_DURACION_OBLIGATORIA = "La duración es obligatoria.";
    private static final String MSG_DURACION_NUMERO = "La duración debe ser un número.";
    private static final String MSG_DURACION_NEGATIVA = "La duración no puede ser negativa.";

    private static final String MSG_RUTINA_INVALIDA = "Rutina inválida o no autorizada.";
    private static final String MSG_ERROR_CREACION = "No se pudo crear el entrenamiento. Inténtalo más tarde.";
    private static final String MSG_ERROR_TECNICO = "Disculpa, hay un fallo técnico en el sistema. Inténtalo más tarde.";

    public static class CrearEntrenamientoResultado {
        public boolean ok;
        public Integer entrenamientoId;

        public String errorGeneral;
        public String errorNombre;
        public String errorFecha;
        public String errorDuracion;

        public String nombreNormalizado;
        public String fechaNormalizada;
        public String notasNormalizadas;
        public String duracionNormalizada;
    }

    /**
     * Crea un entrenamiento verificando que la rutina pertenece al usuario.
     */
    public CrearEntrenamientoResultado crearEntrenamiento(
            int usuarioId,
            int rutinaId,
            String nombre,
            String fecha,
            String notas,
            String duracionStr) {

        CrearEntrenamientoResultado r = new CrearEntrenamientoResultado();

        // Normalización de inputs (para validar y repintar el formulario)
        String nom = (nombre == null) ? "" : nombre.trim();
        String f = (fecha == null) ? "" : fecha.trim();
        String n = (notas == null) ? "" : notas.trim();
        String d = (duracionStr == null) ? "" : duracionStr.trim();

        r.nombreNormalizado = nom;
        r.fechaNormalizada = f;
        r.notasNormalizadas = n;
        r.duracionNormalizada = d;

        boolean hayErrores = false;

        // Validación nombre (nuevo campo)
        if (nom.isEmpty()) {
            r.errorNombre = MSG_NOMBRE_OBLIGATORIO;
            hayErrores = true;
        } else if (nom.length() > 100) {
            r.errorNombre = MSG_NOMBRE_LARGO;
            hayErrores = true;
        } else if (!nom.matches(".*\\p{L}.*")) {
            r.errorNombre = MSG_NOMBRE_SIN_LETRAS;
            hayErrores = true;
        }

        // Validación de fecha (se mantiene)
        if (f.isEmpty()) {
            r.errorFecha = MSG_FECHA_OBLIGATORIA;
            hayErrores = true;
        } else if (!f.matches("\\d{4}-\\d{2}-\\d{2}")) {
            r.errorFecha = MSG_FECHA_INVALIDA;
            hayErrores = true;
        }

        // Validación de duración
        Integer duracion = parseDuracion(d);
        if (duracion == null) {
            r.errorDuracion = d.isEmpty() ? MSG_DURACION_OBLIGATORIA : MSG_DURACION_NUMERO;
            hayErrores = true;
        } else if (duracion < 0) {
            r.errorDuracion = MSG_DURACION_NEGATIVA;
            hayErrores = true;
        }

        // Si hay errores, no tocamos BD
        if (hayErrores) {
            r.ok = false;
            return r;
        }

        // Comprobamos que la rutina pertenece al usuario (seguridad)
        RutinaDAO rutinaDAO = new RutinaDAO();
        if (!rutinaDAO.existeRutinaDeUsuario(rutinaId, usuarioId)) {
            r.ok = false;
            r.errorGeneral = MSG_RUTINA_INVALIDA;
            return r;
        }

        // Creamos el objeto Entrenamiento para el DAO
        Entrenamiento ent = new Entrenamiento();
        ent.setNombre(nom);                 // IMPORTANTE: guardar nombre
        ent.setFecha(f);
        ent.setNotas(n.isEmpty() ? null : n);
        ent.setDuracionTotal(duracion);
        ent.setRutinaId(rutinaId);

        // Insert en BD
        try {
            EntrenamientoDAO dao = new EntrenamientoDAO();
            int nuevoId = dao.crearEntrenamientoYDevolverId(ent);

            if (nuevoId <= 0) {
                r.ok = false;
                r.errorGeneral = MSG_ERROR_CREACION;
                return r;
            }

            r.ok = true;
            r.entrenamientoId = nuevoId;
            return r;

        } catch (Exception ex) {
            r.ok = false;
            r.errorGeneral = MSG_ERROR_TECNICO;
            return r;
        }
    }

    private Integer parseDuracion(String d) {
        if (d == null || d.isEmpty()) return null;
        try { return Integer.parseInt(d); } catch (Exception e) { return null; }
    }
}

package model;

/**
 * HistorialItem:
 * Objeto de apoyo para mostrar el historial con datos combinados (JOIN).
 * NO es una tabla. Es un "resultado" de una consulta.
 */
public class HistorialItem {

    // Datos de Rutina
    private int rutinaId;
    private String rutinaNombre;
    private String rutinaTipo;

    // Datos de Entrenamiento
    private int entrenamientoId;
    private String entrenamientoFecha;
    private int entrenamientoDuracionTotal;
    private String entrenamientoNotas;

    // Datos de Ejercicio
    private int ejercicioId;
    private String ejercicioNombre;
    private Integer ejercicioRepeticiones;   // puede ser null
    private Integer ejercicioDuracionSegundos; // puede ser null

    // Getters / Setters
    public int getRutinaId() { return rutinaId; }
    public void setRutinaId(int rutinaId) { this.rutinaId = rutinaId; }

    public String getRutinaNombre() { return rutinaNombre; }
    public void setRutinaNombre(String rutinaNombre) { this.rutinaNombre = rutinaNombre; }

    public String getRutinaTipo() { return rutinaTipo; }
    public void setRutinaTipo(String rutinaTipo) { this.rutinaTipo = rutinaTipo; }

    public int getEntrenamientoId() { return entrenamientoId; }
    public void setEntrenamientoId(int entrenamientoId) { this.entrenamientoId = entrenamientoId; }
    private String entrenamientoNombre;

    public String getEntrenamientoNombre() {
        return entrenamientoNombre;
    }

    public void setEntrenamientoNombre(String entrenamientoNombre) {
        this.entrenamientoNombre = entrenamientoNombre;
    }


    public String getEntrenamientoFecha() { return entrenamientoFecha; }
    public void setEntrenamientoFecha(String entrenamientoFecha) { this.entrenamientoFecha = entrenamientoFecha; }

    public int getEntrenamientoDuracionTotal() { return entrenamientoDuracionTotal; }
    public void setEntrenamientoDuracionTotal(int entrenamientoDuracionTotal) { this.entrenamientoDuracionTotal = entrenamientoDuracionTotal; }

    public String getEntrenamientoNotas() { return entrenamientoNotas; }
    public void setEntrenamientoNotas(String entrenamientoNotas) { this.entrenamientoNotas = entrenamientoNotas; }

    public int getEjercicioId() { return ejercicioId; }
    public void setEjercicioId(int ejercicioId) { this.ejercicioId = ejercicioId; }

    public String getEjercicioNombre() { return ejercicioNombre; }
    public void setEjercicioNombre(String ejercicioNombre) { this.ejercicioNombre = ejercicioNombre; }

    public Integer getEjercicioRepeticiones() { return ejercicioRepeticiones; }
    public void setEjercicioRepeticiones(Integer ejercicioRepeticiones) { this.ejercicioRepeticiones = ejercicioRepeticiones; }

    public Integer getEjercicioDuracionSegundos() { return ejercicioDuracionSegundos; }
    public void setEjercicioDuracionSegundos(Integer ejercicioDuracionSegundos) { this.ejercicioDuracionSegundos = ejercicioDuracionSegundos; }
}

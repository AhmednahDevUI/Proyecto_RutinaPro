package model;

/**
 * Clase Ejercicio.
 * Representa un registro de la tabla "ejercicio".
 *
 * Un ejercicio pertenece a un entrenamiento (entrenamiento_id).
 * repeticiones y duracionSegundos son opcionales (pueden ser null).
 */
public class Ejercicio {

    // PK del ejercicio
    private int id;

    // Nombre del ejercicio (ej: "Flexiones", "Saco", "Sentadilla")
    private String nombre;

    // Repeticiones (opcional)
    private Integer repeticiones;

    // Duración en segundos (opcional)
    private Integer duracionSegundos;

    // FK al entrenamiento
    private int entrenamientoId;

    // Constructor vacío
    public Ejercicio() {}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getRepeticiones() {
        return repeticiones;
    }
    public void setRepeticiones(Integer repeticiones) {
        this.repeticiones = repeticiones;
    }

    public Integer getDuracionSegundos() {
        return duracionSegundos;
    }
    public void setDuracionSegundos(Integer duracionSegundos) {
        this.duracionSegundos = duracionSegundos;
    }

    public int getEntrenamientoId() {
        return entrenamientoId;
    }
    public void setEntrenamientoId(int entrenamientoId) {
        this.entrenamientoId = entrenamientoId;
    }
}

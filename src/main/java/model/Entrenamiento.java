package model;

/**
 * Clase Entrenamiento.
 * Representa un registro de la tabla "entrenamiento".
 */
public class Entrenamiento {

    // PK del entrenamiento
    private int id;

    //Nombre del entrenamiento
    private String nombre;

  
    // Fecha del entrenamiento
    private String fecha;

    // Notas opcionales
    private String notas;

    // Duración total en minutos o segundos 
    private int duracionTotal;

    // FK a rutina
    private int rutinaId;

    // Constructor vacío
    public Entrenamiento() {}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    //Nombre del entrenamiento
    public String getNombre() { 
        return nombre;
        }
        
        public void setNombre(String nombre) {
        this.nombre = nombre;
        }


    public String getNotas() {
        return notas;
    }
    public void setNotas(String notas) {
        this.notas = notas;
    }

    public int getDuracionTotal() {
        return duracionTotal;
    }
    public void setDuracionTotal(int duracionTotal) {
        this.duracionTotal = duracionTotal;
    }

    public int getRutinaId() {
        return rutinaId;
    }
    public void setRutinaId(int rutinaId) {
        this.rutinaId = rutinaId;
    }
}

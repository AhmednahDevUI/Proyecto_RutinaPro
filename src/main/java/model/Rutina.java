package model;

/**
 * Clase Rutina.
 * Representa un registro de la tabla "rutina".
 */
public class Rutina {

    // PK de la rutina
    private int id;

    // Nombre de la rutina
    private String nombre;

    // Tipo (GYM o BOXEO)
    private String tipo;

    // FK: usuario propietario
    private int usuarioId;

    // Constructor vacío
    public Rutina() {}

    // Getter/Setter id
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    // Getter/Setter nombre
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    // Getter/Setter tipo
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    // Getter/Setter usuarioId
    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }
}


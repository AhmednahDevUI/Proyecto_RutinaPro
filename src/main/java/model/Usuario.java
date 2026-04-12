package model;

/**
 * Clase Usuario.
 * Representa un registro de la tabla "usuario" en la base de datos.
 * Es un POJO: solo datos + getters/setters.
 */
public class Usuario {

    // Identificador único del usuario (clave primaria en BD)
    private int id;

    // Nombre del usuario
    private String nombre;

    // Email del usuario
    private String email;

    // Password guardada en BD (columna password_hash)
    private String passwordHash;

    // Constructor vacío obligatorio
    public Usuario() {}

    // Getter del id
    public int getId() {
        return id;
    }

    // Setter del id
    public void setId(int id) {
        this.id = id;
    }

    // Getter del nombre
    public String getNombre() {
        return nombre;
    }

    // Setter del nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter del email
    public String getEmail() {
        return email;
    }

    // Setter del email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter del passwordHash
    public String getPasswordHash() {
        return passwordHash;
    }

    // Setter del passwordHash
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}

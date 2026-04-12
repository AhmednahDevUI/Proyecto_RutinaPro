package service;

import dao.UsuarioDAO;
import org.mindrot.jbcrypt.BCrypt;



public class RegistrarService {

    // Resultado estándar para no devolver "mil cosas sueltas"
    public static class RegistroResultado {
        public boolean ok;
        public Integer usuarioId;        // si ok=true, aquí viene el id generado
        public String errorGeneral;      // error técnico
        public String errorNombre;       // error para el campo nombre
        public String errorEmail;        // error para el campo email
        public String errorPassword;     // error para el campo password

        public String nombreNormalizado; // para repintar el formulario
        public String emailNormalizado;  // para repintar el formulario
    }

    public RegistroResultado registrarUsuario(String nombre, String email, String password) {

        RegistroResultado r = new RegistroResultado();

        // Normalizamos (trim) para evitar que "   " pase como válido
        String n = (nombre == null) ? "" : nombre.trim();
        String e = (email == null) ? "" : email.trim();
        String p = (password == null) ? "" : password.trim();

        // Guardamos valores normalizados para que el formulario no pierda datos
        r.nombreNormalizado = n;
        r.emailNormalizado = e;

        // Validaciones de negocio mínimas
        boolean hayErrores = false;

     // Validación del nombre del usuario
        if (n.isEmpty()) {
            r.errorNombre = "El nombre es obligatorio.";
            hayErrores = true;
        }
        
        
     // Validación del email del usuario
        if (e.isEmpty()) {
            r.errorEmail = "El email es obligatorio.";
            hayErrores = true;
        } else if (e.contains(" ")) {
            r.errorEmail = "El email no puede contener espacios.";
            hayErrores = true;
        }
        
     // Validación de la password del usuario
        if (p.isEmpty()) {
            r.errorPassword = "La contraseña es obligatoria.";
            hayErrores = true;
        } else if (p.length() < 8) {
            r.errorPassword = "La contraseña debe tener al menos 8 caracteres.";
            hayErrores = true;
        }
        
        // El nombre debe tener al menos una letra (evita nombres solo numéricos)
        if (!n.isEmpty() && !n.matches(".*\\p{L}.*")) {
            r.errorNombre = "Introduce un nombre válido (debe contener letras).";
            hayErrores = true;
        }


        // Si hay errores de validación, no tocamos la BD
        if (hayErrores) {
            r.ok = false;
            return r;
        }

     // Generamos un hash seguro con BCrypt (incluye salt automáticamente) paque se incrupte la password
        String passwordHash = BCrypt.hashpw(p, BCrypt.gensalt(10));



        // Llamamos al DAO para insertar y obtener el ID
        UsuarioDAO dao = new UsuarioDAO();
        int nuevoId = dao.crearUsuarioYDevolverId(n, e, passwordHash);

        // Interpretamos códigos del DAO
        if (nuevoId == -2) {
            r.ok = false;
            r.errorEmail = "Este correo ya está en uso. Prueba con otro.";
            return r;
        }
        //controlo los errores tectico
        if (nuevoId <= 0) {
            r.ok = false;
            r.errorGeneral = "Disculpa, hay un fallo técnico en el sistema. Inténtalo más tarde.";
            return r;
        }

        // Éxito
        r.ok = true;
        r.usuarioId = nuevoId;
        return r;
    }
    

}

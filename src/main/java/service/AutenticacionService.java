package service;

import dao.DataAccessException;
import dao.UsuarioDAO;
import model.Usuario;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Servicio de autenticación: valida credenciales y verifica contraseña con BCrypt.
 * Importante: NO debe revelar si un email existe o no.
 */
public class AutenticacionService {

    // Mensajes reutilizables
    private static final String MSG_EMAIL_OBLIGATORIO = "El email es obligatorio.";
    private static final String MSG_EMAIL_SIN_ESPACIOS = "El email no puede contener espacios.";
    private static final String MSG_PASSWORD_OBLIGATORIA = "La contraseña es obligatoria.";
    private static final String MSG_CREDENCIALES_INCORRECTAS = "Credenciales incorrectas.";
    private static final String MSG_ERROR_TECNICO = "Disculpa, hay un fallo técnico en el sistema. Inténtalo más tarde.";

    /**
     * Resultado del login (DTO).
     * Es una clase interna: por eso no aparece como archivo separado.
     */
    public static class LoginResultado {
        public boolean ok;
        public Integer usuarioId;

        public String errorGeneral;
        public String errorEmail;
        public String errorPassword;

        public String emailNormalizado;
    }

    public LoginResultado login(String email, String password) {
        LoginResultado r = new LoginResultado();

        // 1) Normalización de inputs
        final String emailNorm = normalizarEmail(email);
        final String passwordRaw = (password == null) ? "" : password;

        r.emailNormalizado = emailNorm;

        // 2) Validación (si falla, salimos sin consultar BD)
        if (validarFormulario(emailNorm, passwordRaw, r)) {
            r.ok = false;
            return r;
        }

        // 3) Acceso a BD + 4) verificación de contraseña
        try {
            UsuarioDAO dao = new UsuarioDAO();
            Usuario u = dao.getUsuarioPorEmail(emailNorm);

            // Credenciales genéricas (no revelar si existe o no)
            if (u == null || u.getPasswordHash() == null) {
                r.ok = false;
                r.errorEmail = MSG_CREDENCIALES_INCORRECTAS;
                return r;
            }

            if (!BCrypt.checkpw(passwordRaw, u.getPasswordHash())) {
                r.ok = false;
                r.errorEmail = MSG_CREDENCIALES_INCORRECTAS;
                return r;
            }

            // Éxito
            r.ok = true;
            r.usuarioId = u.getId();
            return r;

        } catch (DataAccessException ex) {
            // Aquí entra cuando la BD está apagada / falla conexión / SQL problem
            r.ok = false;
            r.errorGeneral = MSG_ERROR_TECNICO;
            return r;
        }
    }

    private String normalizarEmail(String email) {
        return (email == null) ? "" : email.trim().toLowerCase();
    }

    /**
     * Devuelve true si hay errores (y deja los mensajes cargados en r).
     */
    private boolean validarFormulario(String emailNorm, String passwordRaw, LoginResultado r) {
        boolean hayErrores = false;

        if (emailNorm.isEmpty()) {
            r.errorEmail = MSG_EMAIL_OBLIGATORIO;
            hayErrores = true;
        } else if (emailNorm.contains(" ")) {
            r.errorEmail = MSG_EMAIL_SIN_ESPACIOS;
            hayErrores = true;
        }

        if (passwordRaw.isEmpty()) {
            r.errorPassword = MSG_PASSWORD_OBLIGATORIA;
            hayErrores = true;
        }

        return hayErrores;
    }
}

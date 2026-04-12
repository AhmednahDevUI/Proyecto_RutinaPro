package servlet;

import java.io.IOException;

import dao.EntrenamientoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/SeleccionarEntrenamiento")
public class SeleccionarEntrenamientoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String SESSION_USUARIO_ID = "usuarioId";
    private static final String SESSION_ENTRENAMIENTO_ID = "entrenamientoId";

    // Volver inmediato (Ejercicio -> Historial) cuando entras desde historial
    private static final String SESSION_BACK_1 = "back1";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Si no hay sesión o no está logueado, mandamos a login
        if (session == null || session.getAttribute(SESSION_USUARIO_ID) == null) {
            response.sendRedirect(request.getContextPath() + "/IniciarSesion");
            return;
        }

        int usuarioId = (int) session.getAttribute(SESSION_USUARIO_ID);

        // Leemos entrenamientoId del formulario
        String entIdStr = request.getParameter("entrenamientoId");
        int entrenamientoId;

        try {
            entrenamientoId = Integer.parseInt(entIdStr);
        } catch (NumberFormatException e) {
            session.setAttribute("msgError", "Entrenamiento inválido.");
            response.sendRedirect(request.getContextPath() + "/Historial");
            return;
        }

        // Seguridad: comprobar que el entrenamiento pertenece al usuario
        EntrenamientoDAO dao = new EntrenamientoDAO();
        if (!dao.existeEntrenamientoDeUsuario(entrenamientoId, usuarioId)) {
            session.setAttribute("msgError", "No tienes permiso para usar ese entrenamiento.");
            response.sendRedirect(request.getContextPath() + "/Historial");
            return;
        }

        // Guardamos el entrenamiento en sesión para que EjercicioServlet sepa dónde insertar
        session.setAttribute(SESSION_ENTRENAMIENTO_ID, entrenamientoId);

        // Como venimos desde Historial, el "Volver" desde Ejercicio debe regresar a Historial
        session.setAttribute(SESSION_BACK_1, "/Historial");

        // Entramos al formulario de Ejercicio
        response.sendRedirect(request.getContextPath() + "/Ejercicio");
    }
}
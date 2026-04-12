package servlet;

import java.io.IOException;

import dao.RutinaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/SeleccionarRutina")
public class SeleccionarRutinaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String SESSION_USUARIO_ID = "usuarioId";
    private static final String SESSION_RUTINA_ID = "rutinaId";

    // Volver inmediato (Ejercicio -> Entrenamiento o Historial)
    private static final String SESSION_BACK_1 = "back1";

    // Volver al origen (Entrenamiento -> Rutina/Historial/Exito)
    private static final String SESSION_BACK_2 = "back2";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Seguridad: si no hay login, a iniciar sesión
        if (session == null || session.getAttribute(SESSION_USUARIO_ID) == null) {
            response.sendRedirect(request.getContextPath() + "/IniciarSesion");
            return;
        }

        int usuarioId = (int) session.getAttribute(SESSION_USUARIO_ID);

        // Leemos la rutina seleccionada
        String rutinaIdStr = request.getParameter("rutinaId");
        int rutinaId;

        try {
            rutinaId = Integer.parseInt(rutinaIdStr);
        } catch (NumberFormatException e) {
            session.setAttribute("msgError", "Rutina inválida.");
            response.sendRedirect(request.getContextPath() + "/Historial");
            return;
        }

        // Seguridad: comprobar que la rutina pertenece al usuario
        RutinaDAO dao = new RutinaDAO();
        if (!dao.existeRutinaDeUsuario(rutinaId, usuarioId)) {
            session.setAttribute("msgError", "No tienes permiso para usar esa rutina.");
            response.sendRedirect(request.getContextPath() + "/Historial");
            return;
        }

        // Guardamos rutinaId para crear entrenamientos dentro de esa rutina
        session.setAttribute(SESSION_RUTINA_ID, rutinaId);

        // Este flujo viene del historial: el “volver” desde Entrenamiento debe ir a Historial
        session.setAttribute(SESSION_BACK_2, "/Historial");

        // Aún no estamos en Ejercicio: limpiamos el “volver inmediato” por seguridad
        session.removeAttribute(SESSION_BACK_1);

        // Entramos al formulario de Entrenamiento
        response.sendRedirect(request.getContextPath() + "/Entrenamiento");
    }
}
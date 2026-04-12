package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Volver")
public class VolverServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Volver inmediato (Ejercicio -> Entrenamiento)
    private static final String SESSION_BACK_1 = "back1";

    // Volver al origen (Entrenamiento -> Rutina/Historial/Exito)
    private static final String SESSION_BACK_2 = "back2";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Si no hay sesión, volvemos a inicio
        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        String ctx = request.getContextPath();

        // Primer nivel: si existe, se consume y vuelve ahí
        String back1 = (String) session.getAttribute(SESSION_BACK_1);
        if (back1 != null && !back1.isBlank()) {
            session.removeAttribute(SESSION_BACK_1);
            response.sendRedirect(ctx + back1);
            return;
        }

        // Segundo nivel: si existe, se consume y vuelve al origen
        String back2 = (String) session.getAttribute(SESSION_BACK_2);
        if (back2 != null && !back2.isBlank()) {
            session.removeAttribute(SESSION_BACK_2);
            response.sendRedirect(ctx + back2);
            return;
        }

        // Por defecto, Rutina
        response.sendRedirect(ctx + "/Rutina");
    }
}
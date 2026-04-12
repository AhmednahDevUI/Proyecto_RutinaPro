package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * ExitoServlet
 * Muestra la pantalla de éxito tras crear ejercicios.
 */
@WebServlet("/exito")
public class ExitoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String JSP_EXITO = "/exito.jsp";
    private static final String SESSION_ENTRENAMIENTO_ID = "entrenamientoId";

    // Volver inmediato (se limpia al llegar a éxito)
    private static final String SESSION_BACK_1 = "back1";

    // Volver al origen (desde aquí, el origen debe ser Éxito)
    private static final String SESSION_BACK_2 = "back2";

    private static final String RUTA_PANEL = "/Panel";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Si no hay flujo de entrenamiento activo, se vuelve al panel
        if (session == null || session.getAttribute(SESSION_ENTRENAMIENTO_ID) == null) {
            response.sendRedirect(request.getContextPath() + RUTA_PANEL);
            return;
        }

        // Desde Éxito, cualquier "Volver" debe volver a Éxito (y no arrastrar retornos viejos)
        session.removeAttribute(SESSION_BACK_1);
        session.setAttribute(SESSION_BACK_2, "/exito");

        request.getRequestDispatcher(JSP_EXITO).forward(request, response);
    }
}
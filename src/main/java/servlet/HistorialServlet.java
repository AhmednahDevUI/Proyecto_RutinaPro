package servlet;

import java.io.IOException;
import java.util.List;

import dao.DataAccessException;
import dao.HistorialDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.HistorialItem;

/**
 * HistorialServlet
 * Muestra el historial del usuario autenticado.
 */
@WebServlet("/Historial")
public class HistorialServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String SESSION_USUARIO_ID = "usuarioId";
    private static final String JSP_HISTORIAL = "/historial.jsp";
    private static final String RUTA_LOGIN = "/IniciarSesion";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // getSession(false): no crea sesión nueva; exige usuario autenticado
        HttpSession session = request.getSession(false);

        // Si no hay sesión, no se permite acceder al historial
        if (session == null || session.getAttribute(SESSION_USUARIO_ID) == null) {
            response.sendRedirect(request.getContextPath() + RUTA_LOGIN);
            return;
        }

        int usuarioId = (int) session.getAttribute(SESSION_USUARIO_ID);

        // Consulta del historial (JOINs)
        try {
            HistorialDAO dao = new HistorialDAO();
            List<HistorialItem> items = dao.getHistorialPorUsuario(usuarioId);

            request.setAttribute("historialItems", items);
            request.getRequestDispatcher(JSP_HISTORIAL).forward(request, response);

        } catch (DataAccessException ex) {
            request.setAttribute("errorGeneral", "Disculpa, hay un fallo técnico en el sistema. Inténtalo más tarde.");
            request.getRequestDispatcher(JSP_HISTORIAL).forward(request, response);
        }
    }
}

package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * PanelServlet
 * Muestra el menú principal del usuario autenticado.
 */
@WebServlet("/Panel")
public class PanelServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String SESSION_USUARIO_ID = "usuarioId";
    private static final String RUTA_LOGIN = "/IniciarSesion";
    private static final String JSP_PANEL = "/panel.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // getSession(false): exige sesión existente (no crea nueva)
        HttpSession session = request.getSession(false);

        // Acceso restringido a usuarios autenticados
        if (session == null || session.getAttribute(SESSION_USUARIO_ID) == null) {
            response.sendRedirect(request.getContextPath() + RUTA_LOGIN);
            return;
        }

        // Mostrar vista del panel
        request.getRequestDispatcher(JSP_PANEL).forward(request, response);
    }
}

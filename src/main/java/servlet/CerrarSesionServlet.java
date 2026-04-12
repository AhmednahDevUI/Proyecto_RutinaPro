package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * CerrarSesionServlet
 * Invalida la sesión activa y redirige al inicio.
 */
@WebServlet("/CerrarSesion")
public class CerrarSesionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String RUTA_INICIO = "/index.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // getSession(false): obtiene sesión solo si existe
        HttpSession session = request.getSession(false);

        // invalidate(): elimina todos los atributos de sesión (usuarioId, rutinaId, etc.)
        if (session != null) {
            session.invalidate();
        }

        // Redirige al inicio tras cerrar sesión
        response.sendRedirect(request.getContextPath() + RUTA_INICIO);
    }
}

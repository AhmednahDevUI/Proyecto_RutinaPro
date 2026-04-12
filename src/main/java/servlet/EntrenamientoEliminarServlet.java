package servlet;

import java.io.IOException;

import dao.EntrenamientoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/EntrenamientoEliminar")
public class EntrenamientoEliminarServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String SESSION_USUARIO_ID = "usuarioId";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Si no está logueado, fuera
        if (session == null || session.getAttribute(SESSION_USUARIO_ID) == null) {
            response.sendRedirect(request.getContextPath() + "/IniciarSesion");
            return;
        }

        int usuarioId = (int) session.getAttribute(SESSION_USUARIO_ID);

        int entrenamientoId;
        try {
            entrenamientoId = Integer.parseInt(request.getParameter("entrenamientoId"));
        } catch (NumberFormatException e) {
            session.setAttribute("msgError", "Entrenamiento inválido.");
            response.sendRedirect(request.getContextPath() + "/Historial");
            return;
        }

        EntrenamientoDAO dao = new EntrenamientoDAO();

        // Seguridad: ese entrenamiento debe pertenecer al usuario
        if (!dao.existeEntrenamientoDeUsuario(entrenamientoId, usuarioId)) {
            session.setAttribute("msgError", "No tienes permiso para eliminar ese entrenamiento.");
            response.sendRedirect(request.getContextPath() + "/Historial");
            return;
        }

        boolean ok = dao.eliminarEntrenamiento(entrenamientoId);

        if (ok) session.setAttribute("msgOk", "Entrenamiento eliminado.");
        else session.setAttribute("msgError", "No se pudo eliminar el entrenamiento.");

        response.sendRedirect(request.getContextPath() + "/Historial");
    }
}
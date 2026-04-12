package servlet;

import java.io.IOException;

import dao.EjercicioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/EjercicioEliminar")
public class EjercicioEliminarServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String SESSION_USUARIO_ID = "usuarioId";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // No creamos sesión nueva si no existe
        HttpSession session = request.getSession(false);

        // Si no hay login, fuera
        if (session == null || session.getAttribute(SESSION_USUARIO_ID) == null) {
            response.sendRedirect(request.getContextPath() + "/IniciarSesion");
            return;
        }

        int usuarioId = (int) session.getAttribute(SESSION_USUARIO_ID);

        // Leemos el id con el MISMO nombre que mandas desde el JSP
        String ejercicioIdStr = request.getParameter("ejercicioId");
        int ejercicioId;

        // Si viene vacío o no es número, devolvemos error
        try {
            ejercicioId = Integer.parseInt(ejercicioIdStr);
        } catch (Exception e) {
            session.setAttribute("msgError", "Datos inválidos.");
            response.sendRedirect(request.getContextPath() + "/Historial");
            return;
        }

        EjercicioDAO dao = new EjercicioDAO();

        // Seguridad: el ejercicio debe pertenecer al usuario logueado
        if (!dao.existeEjercicioDeUsuario(ejercicioId, usuarioId)) {
            session.setAttribute("msgError", "No tienes permiso para eliminar ese ejercicio.");
            response.sendRedirect(request.getContextPath() + "/Historial");
            return;
        }

        // Eliminamos (siempre por id, la cascada la gestiona MySQL)
        boolean ok = dao.eliminarEjercicio(ejercicioId);

        if (ok) {
            session.setAttribute("msgOk", "Ejercicio eliminado.");
        } else {
            session.setAttribute("msgError", "No se pudo eliminar el ejercicio.");
        }

        // Volvemos al historial para ver el cambio
        response.sendRedirect(request.getContextPath() + "/Historial");
    }
}
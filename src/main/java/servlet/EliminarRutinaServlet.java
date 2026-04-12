package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.RutinaService;
import service.RutinaService.CrearRutinaResultado;

/**
 * RutinaEliminarServlet Elimina una rutina seleccionada desde el historial.
 */
@WebServlet("/RutinaEliminar")
public class EliminarRutinaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String SESSION_USUARIO_ID = "usuarioId";
	private static final String RUTA_LOGIN = "/IniciarSesion";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    HttpSession session = request.getSession(false);

	    if (session == null || session.getAttribute(SESSION_USUARIO_ID) == null) {
	        response.sendRedirect(request.getContextPath() + RUTA_LOGIN);
	        return;
	    }

	    int usuarioId = (int) session.getAttribute(SESSION_USUARIO_ID);

	    // Asegúrate de convertir correctamente el rutinaId a int
	    String rutinaIdStr = request.getParameter("rutinaId");
	    int rutinaId = 0;

	    try {
	        rutinaId = Integer.parseInt(rutinaIdStr);  // Convertir a int
	    } catch (NumberFormatException e) {
	        session.setAttribute("msgError", "El ID de la rutina no es válido.");
	        response.sendRedirect(request.getContextPath() + "/Historial");
	        return;
	    }

	    RutinaService service = new RutinaService();
	    CrearRutinaResultado r = service.eliminarRutina(usuarioId, rutinaId);

	    // Verifica que `r` no sea null antes de acceder a `r.ok`
	    if (r != null && r.ok) {
	        session.setAttribute("msgOk", "Rutina eliminada correctamente.");
	    } else {
	        // Si `r` es null, se muestra un mensaje de error
	        session.setAttribute("msgError", r != null ? r.errorGeneral : "Hubo un error al eliminar la rutina.");
	    }

	    response.sendRedirect(request.getContextPath() + "/Historial");
	}
}

package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.EjercicioService;

/**
 * EjercicioServlet
 * Controlador para crear ejercicios asociados al entrenamiento en sesión.
 */
@WebServlet("/Ejercicio")
public class EjercicioServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String JSP_NUEVO_EJERCICIO = "/nuevoEjercicio.jsp";

    private static final String SESSION_ENTRENAMIENTO_ID = "entrenamientoId";

    // Ruta a la que volvemos si no hay entrenamiento en el flujo
    private static final String RUTA_FALLBACK = "/Rutina";

    // Ruta de éxito (ajústala al nombre real que tengas)
    private static final String RUTA_EXITO = "/exito";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // getSession(false): no crea sesión nueva; solo permite continuar si existe
        HttpSession session = request.getSession(false);

        // Si no hay entrenamiento en sesión, se rompe el flujo y volvemos a Rutina
        if (session == null || session.getAttribute(SESSION_ENTRENAMIENTO_ID) == null) {
            response.sendRedirect(request.getContextPath() + RUTA_FALLBACK);
            return;
        }
        //Si vine del arcivo exito sera ejecutada si no sera null
        String from = request.getParameter("from");
        if ("exito".equals(from)) {
            session.setAttribute("back1", "/exito");
        }
        
        request.setAttribute("from", from);

        // Mostrar formulario de ejercicio
        request.getRequestDispatcher(JSP_NUEVO_EJERCICIO).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // getSession(false): no crea sesión nueva; solo permite continuar si existe
        HttpSession session = request.getSession(false);

        // Si no hay entrenamiento en sesión, se rompe el flujo y volvemos a Rutina
        if (session == null || session.getAttribute(SESSION_ENTRENAMIENTO_ID) == null) {
            response.sendRedirect(request.getContextPath() + RUTA_FALLBACK);
            return;
        }

        // Entrenamiento actual del flujo (FK ejercicio.entrenamiento_id)
        int entrenamientoId = (int) session.getAttribute(SESSION_ENTRENAMIENTO_ID);

        // Parámetros del formulario
        String nombre = request.getParameter("nombre");
        String repeticiones = request.getParameter("repeticiones");
        String duracion = request.getParameter("duracion_segundos");

        // Service: valida e inserta
        EjercicioService service = new EjercicioService();
        EjercicioService.CrearEjercicioResultado r =
                service.crearEjercicio(entrenamientoId, nombre, repeticiones, duracion);

        // Errores: repintar formulario
        if (!r.ok) {
            request.setAttribute("errorGeneral", r.errorGeneral);
            request.setAttribute("errorNombre", r.errorNombre);
            request.setAttribute("errorRepeticiones", r.errorRepeticiones);
            request.setAttribute("errorDuracion", r.errorDuracion);

            request.setAttribute("nombrePrevio", r.nombreNormalizado);
            request.setAttribute("repeticionesPrevio", r.repeticionesNormalizadas);
            request.setAttribute("duracionPrevio", r.duracionNormalizada);

            request.getRequestDispatcher(JSP_NUEVO_EJERCICIO).forward(request, response);
            return;
        }

        // PRG: evita reenvío del POST al refrescar
        response.sendRedirect(request.getContextPath() + RUTA_EXITO);
    }
}

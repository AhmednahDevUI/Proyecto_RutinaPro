package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.EntrenamientoService;

@WebServlet("/Entrenamiento")
public class EntrenamientoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String JSP_NUEVO_ENTRENAMIENTO = "/nuevoEntrenamiento.jsp";

    private static final String SESSION_USUARIO_ID = "usuarioId";
    private static final String SESSION_RUTINA_ID = "rutinaId";
    private static final String SESSION_ENTRENAMIENTO_ID = "entrenamientoId";

    // Volver inmediato (Ejercicio -> Entrenamiento)
    private static final String SESSION_BACK_1 = "back1";

    private static final String RUTA_LOGIN = "/IniciarSesion";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Si no hay login, a iniciar sesión
        if (session == null || session.getAttribute(SESSION_USUARIO_ID) == null) {
            response.sendRedirect(request.getContextPath() + RUTA_LOGIN);
            return;
        }

        // Si no hay rutina seleccionada, volvemos a Rutina
        if (session.getAttribute(SESSION_RUTINA_ID) == null) {
            response.sendRedirect(request.getContextPath() + "/Rutina");
            return;
        }

        // El botón "Volver" lo gestiona /Volver leyendo back1/back2 desde sesión
        request.getRequestDispatcher(JSP_NUEVO_ENTRENAMIENTO).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Si no hay login, a iniciar sesión
        if (session == null || session.getAttribute(SESSION_USUARIO_ID) == null) {
            response.sendRedirect(request.getContextPath() + RUTA_LOGIN);
            return;
        }

        // Si no hay rutina seleccionada, volvemos a Rutina
        if (session.getAttribute(SESSION_RUTINA_ID) == null) {
            response.sendRedirect(request.getContextPath() + "/Rutina");
            return;
        }

        int usuarioId = (int) session.getAttribute(SESSION_USUARIO_ID);
        int rutinaId = (int) session.getAttribute(SESSION_RUTINA_ID);

        // Campos del formulario
        String nombre = request.getParameter("nombre");
        String fecha = request.getParameter("fecha");
        String notas = request.getParameter("notas");
        String duracion = request.getParameter("duracion_total");

        // Creamos el entrenamiento (valida + guarda en BD)
        EntrenamientoService service = new EntrenamientoService();
        EntrenamientoService.CrearEntrenamientoResultado r =
                service.crearEntrenamiento(usuarioId, rutinaId, nombre, fecha, notas, duracion);

        // Si hay errores, repintamos el formulario
        if (!r.ok) {
            request.setAttribute("errorGeneral", r.errorGeneral);
            request.setAttribute("errorNombre", r.errorNombre);
            request.setAttribute("errorFecha", r.errorFecha);
            request.setAttribute("errorDuracion", r.errorDuracion);

            request.setAttribute("nombrePrevio", r.nombreNormalizado);
            request.setAttribute("fechaPrevio", r.fechaNormalizada);
            request.setAttribute("notasPrevio", r.notasNormalizadas);
            request.setAttribute("duracionPrevio", r.duracionNormalizada);

            request.getRequestDispatcher(JSP_NUEVO_ENTRENAMIENTO).forward(request, response);
            return;
        }

        // Guardamos el id del entrenamiento para que Ejercicio sepa dónde insertar
        session.setAttribute(SESSION_ENTRENAMIENTO_ID, r.entrenamientoId);

        // Si estamos en Ejercicio y pulsamos "Volver", debe volver a Entrenamiento
        session.setAttribute(SESSION_BACK_1, "/Entrenamiento");

        // Entramos a Ejercicio (sin parámetros, el volver lo decide /Volver)
        response.sendRedirect(request.getContextPath() + "/Ejercicio");
    }
}
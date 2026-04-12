package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.RutinaService;

/**
 * RutinaServlet
 * Controlador para crear rutinas (GET formulario, POST procesar creación).
 */
@WebServlet("/Rutina")
public class RutinaServlet extends HttpServlet {

    /**
	 * Identificador de versión para serialización del servlet
	 */
	private static final long serialVersionUID = 1L;
	
	
	private static final String JSP_NUEVA_RUTINA = "/nuevaRutina.jsp";
    private static final String SESSION_USUARIO_ID = "usuarioId";
    private static final String SESSION_RUTINA_ID = "rutinaId";

    // Ruta a la que mandamos si no hay sesión (ajústala si tu login está en otra URL)
    private static final String RUTA_LOGIN = "/IniciarSesion";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Requiere sesión iniciada
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(SESSION_USUARIO_ID) == null) {
            response.sendRedirect(request.getContextPath() + RUTA_LOGIN);
            return;
        }
        
        
        
        // Mostrar formulario
        request.getRequestDispatcher(JSP_NUEVA_RUTINA).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Requiere sesión iniciada
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(SESSION_USUARIO_ID) == null) {
            response.sendRedirect(request.getContextPath() + RUTA_LOGIN);
            return;
        }

        int usuarioId = (int) session.getAttribute(SESSION_USUARIO_ID);

        // Datos del formulario
        String nombre = request.getParameter("nombre");
        String tipo = request.getParameter("tipo");
        
     // Log para ver los datos recibidos en el Servlet
        System.out.println("Datos recibidos en el Servlet: nombre = " + nombre + ", tipo = " + tipo);


        // Validación + creación
        RutinaService service = new RutinaService();
        RutinaService.CrearRutinaResultado r = service.crearRutina(usuarioId, nombre, tipo);
        
        // Log para ver si la creación de la rutina fue exitosa o no
        System.out.println("Resultado de crear rutina: " + r.ok);

        // Errores: repintar formulario
        if (!r.ok) {
            request.setAttribute("errorGeneral", r.errorGeneral);
            request.setAttribute("errorNombre", r.errorNombre);
            request.setAttribute("errorTipo", r.errorTipo);

            request.setAttribute("nombrePrevio", r.nombreNormalizado);
            request.setAttribute("tipoPrevio", r.tipoNormalizado);

            request.getRequestDispatcher(JSP_NUEVA_RUTINA).forward(request, response);
            return;
        }

        // Si la creación fue exitosa, guardamos el ID de la rutina
        session.setAttribute(SESSION_RUTINA_ID, r.rutinaId);

     // Log de éxito, antes de redirigir
        System.out.println("Rutina creada con éxito, redirigiendo a Entrenamiento");
        System.out.println("Rutina ID guardada en sesión: " + r.rutinaId);
        
        session.setAttribute("back2", "/Rutina");
        session.removeAttribute("back1");
        response.sendRedirect(request.getContextPath() + "/Entrenamiento");
    }
}

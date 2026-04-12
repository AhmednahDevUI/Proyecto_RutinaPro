package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.AutenticacionService;

/**
 * Controlador de inicio de sesión.
 *  GET: muestra formulario
 *  POST: valida credenciales usando AutenticacionService y crea sesión
 */
@WebServlet("/IniciarSesion")
public class InicioSesionServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Vista del formulario (evita repetir el string en varios forwards)
    private static final String LOGIN_JSP = "/iniciarSesion.jsp";

    // Clave de sesión: usuario autenticado
    private static final String SESSION_USUARIO_ID = "usuarioId";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Muestra el formulario de login
        request.getRequestDispatcher(LOGIN_JSP).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1) Leer parámetros del formulario
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // 2) Ejecutar lógica de autenticación (validación + BD + BCrypt)
        AutenticacionService auth = new AutenticacionService();
        AutenticacionService.LoginResultado r = auth.login(email, password);

        // 3) Si falla: volver al formulario con mensajes (incluye error técnico)
        if (!r.ok) {
            request.setAttribute("errorGeneral", r.errorGeneral);
            request.setAttribute("errorEmail", r.errorEmail);
            request.setAttribute("errorPassword", r.errorPassword);

            // Email normalizado para repintar el campo y evitar que el usuario lo reescriba
            request.setAttribute("email", r.emailNormalizado);

            request.getRequestDispatcher(LOGIN_JSP).forward(request, response);
            return;
        }

        // 4) Si ok: crear sesión y redirigir al panel
        HttpSession session = request.getSession(true);
        session.setAttribute(SESSION_USUARIO_ID, r.usuarioId);

        response.sendRedirect(request.getContextPath() + "/Panel");

    }
}

package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.RegistrarService;

/**
 * UsuarioServlet
 * Controlador para registro de usuarios (GET formulario, POST registro).
 */
@WebServlet("/Registrarse")
public class RegistrarServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String JSP_REGISTRO = "/registrar.jsp";
    private static final String SESSION_USUARIO_ID = "usuarioId";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Mostrar formulario de registro
        request.getRequestDispatcher(JSP_REGISTRO).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Parámetros del formulario
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Service: valida y registra
        RegistrarService service = new RegistrarService();
        RegistrarService.RegistroResultado r = service.registrarUsuario(nombre, email, password);

        // Errores: repintar formulario
        if (!r.ok) {
            request.setAttribute("errorGeneral", r.errorGeneral);
            request.setAttribute("errorNombre", r.errorNombre);
            request.setAttribute("errorEmail", r.errorEmail);
            request.setAttribute("errorPassword", r.errorPassword);

            request.setAttribute("nombrePrevio", r.nombreNormalizado);
            request.setAttribute("emailPrevio", r.emailNormalizado);

            request.getRequestDispatcher(JSP_REGISTRO).forward(request, response);
            return;
        }

        // Crear sesión y guardar usuario autenticado
        HttpSession session = request.getSession(true);
        session.setAttribute(SESSION_USUARIO_ID, r.usuarioId);

     // Marcar que es usuario nuevo (solo para mostrar bienvenida una vez)
        session.setAttribute("esNuevo", true);

        response.sendRedirect(request.getContextPath() + "/bienvenida.jsp");
    }
}

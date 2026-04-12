package servlet;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class FiltroAutenticacion implements Filter {

    private static final String SESSION_USUARIO_ID = "usuarioId";

    @Override
    public void doFilter(jakarta.servlet.ServletRequest req, jakarta.servlet.ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String uri = request.getRequestURI();
        String ctx = request.getContextPath();

        // Públicos + estáticos
        if (uri.startsWith(ctx + "/css")
                || uri.startsWith(ctx + "/js")
                || uri.startsWith(ctx + "/img")
                || uri.startsWith(ctx + "/svg")
                || uri.equals(ctx + "/")
                || uri.endsWith("/index.jsp")
                || uri.startsWith(ctx + "/IniciarSesion")
                || uri.startsWith(ctx + "/Registrarse")) { 

            chain.doFilter(req, res);
            return;
        }

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(SESSION_USUARIO_ID) == null) {
            response.sendRedirect(ctx + "/IniciarSesion");
            return;
        }

        chain.doFilter(req, res);
    }
}
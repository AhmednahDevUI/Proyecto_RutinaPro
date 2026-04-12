package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Ejercicio;
import util.DBConnection;

/**
 * EjercicioDAO
 * Acceso a datos de la tabla ejercicio.
 */
public class EjercicioDAO {

    /**
     * Inserto un ejercicio y devuelve su ID autogenerado.
     */
    public int crearEjercicioYDevolverId(Ejercicio e) {

        // Sentencia SQL (evita inyección SQL)
        final String sql = "INSERT INTO ejercicio (nombre, repeticiones, duracion_segundos, entrenamiento_id) "
                         + "VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); // Obtiene conexión a BD
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) { 
             // PreparedStatement precompila SQL y permite recuperar ID generado

            stmt.setString(1, e.getNombre()); // Nombre obligatorio

            // Campos opcionales si son null se guardan como null en BD
            if (e.getRepeticiones() != null) stmt.setInt(2, e.getRepeticiones());
            else stmt.setNull(2, java.sql.Types.INTEGER);

            if (e.getDuracionSegundos() != null) stmt.setInt(3, e.getDuracionSegundos());
            else stmt.setNull(3, java.sql.Types.INTEGER);

            stmt.setInt(4, e.getEntrenamientoId()); // FK ➡️ entrenamiento.id

            int filas = stmt.executeUpdate(); // Ejecuta INSERT y devuelve filas afectadas
            if (filas == 0) return -1; // Si no, no insertó nada

            // ResultSet contiene el ID autogenerado por la BD
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1); // Obtiene primera columna (ID generado)
            }

            return -1; // Insert ejecutado pero no se pudo recuperar ID

        } catch (Exception ex) {
            throw new DataAccessException("Error al crear ejercicio", ex); // Error técnico
        }
    }
    
 // Elimina un ejercicio concreto del entrenamiento indicado (borra la fila de la tabla ejercicio)
    public boolean existeEjercicioDeUsuario(int ejercicioId, int usuarioId) {
        final String sql =
            "SELECT 1 " +
            "FROM ejercicio e " +
            "JOIN entrenamiento en ON en.id = e.entrenamiento_id " +
            "JOIN rutina r ON r.id = en.rutina_id " +
            "WHERE e.id = ? AND r.usuario_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, ejercicioId);
            ps.setInt(2, usuarioId);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminarEjercicio(int ejercicioId) {
        String sql = "DELETE FROM ejercicio WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, ejercicioId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }
    }
}

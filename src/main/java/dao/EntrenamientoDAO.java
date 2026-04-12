package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Entrenamiento;
import util.DBConnection;

public class EntrenamientoDAO {

    /**
     * Crea un entrenamiento y devuelve el ID autogenerado.
     */
    public int crearEntrenamientoYDevolverId(Entrenamiento entrenamiento) {
        Connection conn = null;
        final String sql = "INSERT INTO entrenamiento (Nombre, fecha, duracion_total, notas, rutina_id) VALUES (?, ?, ?, ?, ?)";

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                // Parámetros en el MISMO orden que el INSERT
                stmt.setString(1, entrenamiento.getNombre());
                stmt.setString(2, entrenamiento.getFecha());
                stmt.setInt(3, entrenamiento.getDuracionTotal());   // duracion_total (NOT NULL)
                stmt.setString(4, entrenamiento.getNotas());        // notas (puede ser null)
                stmt.setInt(5, entrenamiento.getRutinaId());        // FK rutina_id

                int filas = stmt.executeUpdate();
                System.out.println("Filas afectadas: " + filas);

                if (filas == 0) {
                    conn.rollback();
                    return -1;
                }

                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        conn.commit();
                        return rs.getInt(1);
                    }
                }

                conn.rollback();
                return -1;

            } catch (SQLException e) {
                System.err.println("Error al crear el entrenamiento: " + e.getMessage());
                e.printStackTrace();
                conn.rollback();
                return -1;
            }

        } catch (SQLException e) {
            System.err.println("Error al establecer la conexión o ejecutar la transacción: " + e.getMessage());
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                System.err.println("Error al hacer rollback: " + ex.getMessage());
            }
            return -1;

        } finally {
            try {
                if (conn != null && !conn.isClosed()) conn.close();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
        
        
    }
    
    //Este metodo eviata que el usuario meta el Id entrenamieto a mano
    public boolean existeEntrenamientoDeUsuario(int entrenamientoId, int usuarioId) {

        final String sql =
            "SELECT 1 " +
            "FROM entrenamiento e " +
            "JOIN rutina r ON r.id = e.rutina_id " +
            "WHERE e.id = ? AND r.usuario_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

        	//Entrenamiento que queremos validar
            stmt.setInt(1, entrenamientoId);
            stmt.setInt(2, usuarioId);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (Exception ex) {
            throw new DataAccessException("Error al verificar entrenamiento", ex);
        }
    }
    
    public boolean eliminarEntrenamiento(int entrenamientoId) {
        String sql = "DELETE FROM entrenamiento WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, entrenamientoId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }
    }
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Rutina;
import util.DBConnection;

/**
 * RutinaDAO Acceso a datos de la tabla "rutina" Solo contiene operaciones SQL
 * relacionadas con esta entidad.
 */
public class RutinaDAO {

    /**
     * Inserta una rutina y devuelve su ID autogenerado.
     */
    public int crearRutinaYDevolverId(String nombre, String tipo, int usuarioId) {

        // Verificar la conexión antes de ejecutar la consulta
        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                System.out.println("Conexión exitosa a la base de datos");
            } else {
                System.out.println("Error: No se pudo establecer conexión con la base de datos.");
            }
        } catch (SQLException e) {
            System.err.println("Error en la conexión a la base de datos: " + e.getMessage());
        }

        // SQL para evitar inyección
        final String sql = "INSERT INTO rutina (nombre, tipo, usuario_id) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Asegurarnos de que no se utilice auto-commit
            conn.setAutoCommit(false); // Usamos transacciones manuales para asegurarnos de que la rutina se inserte correctamente

            // Log antes de ejecutar la consulta SQL
            System.out.println("Ejecutando consulta SQL: " + sql);
            System.out.println("Datos insertados -> Nombre: " + nombre + ", Tipo: " + tipo + ", Usuario ID: " + usuarioId);

            // Asignación de parámetros
            stmt.setString(1, nombre);
            stmt.setString(2, tipo); // enum (Gimnasio, boxeo, futobol etc..)
            stmt.setInt(3, usuarioId); // FK ➡️usuario.id

            int filas = stmt.executeUpdate();
            System.out.println("Filas afectadas: " + filas);  // Log para ver cuántas filas se afectan

            // Si no se insertó ninguna fila
            if (filas == 0) {
                conn.rollback();  // Realizamos un rollback en caso de error
                return -1;
            }

            // Verificar la existencia de la rutina antes de devolver el ID
            String verifyRutinaQuery = "SELECT COUNT(*) FROM rutina WHERE nombre = ? AND tipo = ? AND usuario_id = ?";
            try (PreparedStatement verifyStmt = conn.prepareStatement(verifyRutinaQuery)) {
                verifyStmt.setString(1, nombre);
                verifyStmt.setString(2, tipo);
                verifyStmt.setInt(3, usuarioId);
                ResultSet rs = verifyStmt.executeQuery();
                
                if (rs.next()) {
                    int count = rs.getInt(1);
                    if (count == 0) {
                        System.out.println("Error: La rutina con el nombre " + nombre + " no se insertó correctamente.");
                        conn.rollback();  // Realizamos un rollback si no existe
                        return -1;  // Si la rutina no existe, no insertamos el entrenamiento
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Error al verificar la rutina: " + ex.getMessage());
                conn.rollback();  // Realizamos un rollback en caso de error en la consulta
                return -1;  // En caso de error en la consulta
            }

            // Recuperar ID generado automáticamente
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    conn.commit();  // Hacemos commit para confirmar la transacción
                    return rs.getInt(1);  // Devuelve el ID generado
                }
            }
            // Con este -1 indico si algo ha salido mal
            conn.rollback();  // En caso de error, se hace un rollback
            return -1;

        } catch (Exception e) {
            System.err.println("Excepción al crear rutina: " + e.getMessage());
            e.printStackTrace();  // Esto imprimirá el stack trace completo del error.
            throw new DataAccessException("Error al crear rutina", e);
        }
    }

    // Eliminar una rutina del usuario (seguridad: solo borra si usuario_id coincide)
    public boolean eliminarRutinaDeUsuario(int rutinaId, int usuarioId) {

        final String sql = "DELETE FROM rutina WHERE id = ? AND usuario_id = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, rutinaId);
            stmt.setInt(2, usuarioId);

            // executeUpdate devuelve cuantas filas se eliminaron
            return stmt.executeUpdate() > 0;

        } catch (Exception ex) {
            throw new DataAccessException("Error al eliminar rutina", ex);
        }
    }

    // Inserta rutina devolviendo true si fue creada correctamente.
    public boolean crearRutina(String nombre, String tipo, int usuarioId) {
        return crearRutinaYDevolverId(nombre, tipo, usuarioId) > 0;
    }

    /**
     * Obtiene todas las rutinas de un usuario.
     */
    public List<Rutina> getRutinasPorUsuario(int usuarioId) {

        final List<Rutina> rutinas = new ArrayList<>();
        final String sql = "SELECT id, nombre, tipo, usuario_id FROM rutina WHERE usuario_id = ? ORDER BY id DESC";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Rutina r = new Rutina();
                    r.setId(rs.getInt("id"));
                    r.setNombre(rs.getString("nombre"));
                    r.setTipo(rs.getString("tipo"));
                    r.setUsuarioId(rs.getInt("usuario_id"));
                    rutinas.add(r);
                }
            }

            return rutinas;

        } catch (Exception e) {
            throw new DataAccessException("Error al obtener rutinas", e);
        }
    }

    /**
     * Compruebo si una rutina pertenece al usuario. Se usa como control de
     * seguridad.
     */
    public boolean existeRutinaDeUsuario(int rutinaId, int usuarioId) {

        final String sql = "SELECT 1 FROM rutina WHERE id = ? AND usuario_id = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, rutinaId);  // Usamos el rutinaId que pasamos como parámetro
            stmt.setInt(2, usuarioId);  // Usamos el usuarioId que pasamos como parámetro

            System.out.println("Verificando existencia de rutina con ID: " + rutinaId + " para usuario ID: " + usuarioId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("La rutina con ID " + rutinaId + " y usuario ID " + usuarioId + " existe.");
                    return true;  // Rutina existe
                } else {
                    System.out.println("La rutina con ID " + rutinaId + " no se encuentra para el usuario ID " + usuarioId);
                    return false;  // Rutina no existe
                }
            }
        } catch (Exception e) {
            System.out.println("Error al verificar rutina: " + e.getMessage());
            e.printStackTrace();
            throw new DataAccessException("Error al verificar rutina", e);
        }

    }
}

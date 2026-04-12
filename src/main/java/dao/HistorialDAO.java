package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.HistorialItem;
import util.DBConnection;

/**
 * HistorialDAO Consulta el historial del usuario (rutina → entrenamiento →
 * ejercicio).
 */
public class HistorialDAO {

	/**
	 * Devuelve el historial del usuario mediante JOINs.
	 */
	public List<HistorialItem> getHistorialPorUsuario(int usuarioId) {

		List<HistorialItem> lista = new ArrayList<>();

		// LEFT JOIN en ejercicio: muestra entrenamientos aunque no tengan ejercicios
		final String sql = "SELECT " + " r.id AS rutina_id, r.nombre AS rutina_nombre, r.tipo AS rutina_tipo, "
				+ " e.id AS entrenamiento_id, e.nombre AS entrenamiento_nombre, e.fecha AS entrenamiento_fecha, "
				+ " e.duracion_total AS entrenamiento_duracion, e.notas AS entrenamiento_notas, "
				+ " x.id AS ejercicio_id, x.nombre AS ejercicio_nombre, x.repeticiones AS ejercicio_repeticiones, x.duracion_segundos AS ejercicio_duracion "
				+ "FROM rutina r " + "JOIN entrenamiento e ON e.rutina_id = r.id "
				+ "LEFT JOIN ejercicio x ON x.entrenamiento_id = e.id " + "WHERE r.usuario_id = ? "
				+ "ORDER BY r.id DESC, e.id DESC, x.id ASC";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			// Filtramos por el usuario logueado
			stmt.setInt(1, usuarioId);

			// ResultSet: filas resultantes del JOIN
			try (ResultSet rs = stmt.executeQuery()) {

				while (rs.next()) {
					HistorialItem item = new HistorialItem();

					// Datos de rutina
					item.setRutinaId(rs.getInt("rutina_id"));
					item.setRutinaNombre(rs.getString("rutina_nombre"));
					item.setRutinaTipo(rs.getString("rutina_tipo"));

					// Datos de entrenamiento
					item.setEntrenamientoId(rs.getInt("entrenamiento_id"));
					item.setEntrenamientoNombre(rs.getString("entrenamiento_nombre")); // <-- NUEVO
					item.setEntrenamientoFecha(rs.getString("entrenamiento_fecha"));
					item.setEntrenamientoDuracionTotal(rs.getInt("entrenamiento_duracion"));
					item.setEntrenamientoNotas(rs.getString("entrenamiento_notas"));

					// Datos de ejercicio (puede no existir por LEFT JOIN)
					int ejercicioId = rs.getInt("ejercicio_id");
					if (rs.wasNull()) {
						item.setEjercicioId(0);
						item.setEjercicioNombre(null);
						item.setEjercicioRepeticiones(null);
						item.setEjercicioDuracionSegundos(null);
					} else {
						item.setEjercicioId(ejercicioId);
						item.setEjercicioNombre(rs.getString("ejercicio_nombre"));

						Integer reps = (Integer) rs.getObject("ejercicio_repeticiones");
						Integer dur = (Integer) rs.getObject("ejercicio_duracion");

						item.setEjercicioRepeticiones(reps);
						item.setEjercicioDuracionSegundos(dur);
					}

					lista.add(item);
				}
			}

			return lista;

		} catch (Exception ex) {
			throw new DataAccessException("Error al obtener historial del usuario", ex);
		}
	}
}

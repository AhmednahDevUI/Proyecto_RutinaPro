package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Usuario;
import util.DBConnection;

/**
 * DAO de Usuario: acceso exclusivo a base de datos.
 *
 */
public class UsuarioDAO {

	// Códigos de retorno para crearUsuarioYDevolverId
	public static final int ERROR_SIN_ID = -1;
	public static final int ERROR_EMAIL_DUPLICADO = -2;
	public static final int ERROR_TECNICO = -3;

	/**
	 * Inserta un usuario y devuelve su ID autogenerado.
	 *
	 */
	public int crearUsuarioYDevolverId(String nombre, String email, String passwordHash) {
		final String sql = "INSERT INTO usuario (nombre, email, password_hash) VALUES (?, ?, ?)";

		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			// Parámetros del INSERT (en el orden de los ?)
			stmt.setString(1, nombre);
			stmt.setString(2, email);
			stmt.setString(3, passwordHash);

			int filas = stmt.executeUpdate();
			if (filas == 0) {
				return ERROR_SIN_ID;
			}

			// Recuperar el ID autogenerado por la BD
			try (ResultSet rs = stmt.getGeneratedKeys()) {
				if (rs.next()) {
					return rs.getInt(1);
				}
			}

			return ERROR_SIN_ID;

		} catch (java.sql.SQLIntegrityConstraintViolationException e) {
			// Email duplicado (UNIQUE)
			return ERROR_EMAIL_DUPLICADO;

		} catch (Exception e) {
			// Fallo técnico (BD caída, SQL error, etc.)
			return ERROR_TECNICO;
		}
	}

	/**
	 * Busca un usuario por email.
	*
	 */
	public Usuario getUsuarioPorEmail(String email) {
		final String sql = "SELECT id, nombre, email, password_hash FROM usuario WHERE email = ?";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, email);

			// El ResultSet también se cierra con try-with-resources
			try (ResultSet rs = stmt.executeQuery()) {

				// Caso normal: no existe usuario con ese email
				if (!rs.next()) {
					return null;
				}

				// Construcción del modelo Usuario a partir de la fila
				Usuario u = new Usuario();
				u.setId(rs.getInt("id"));
				u.setNombre(rs.getString("nombre"));
				u.setEmail(rs.getString("email"));
				u.setPasswordHash(rs.getString("password_hash"));
				return u;
			}

		} catch (Exception e) {
			// Importante: NO devolvemos null aquí.
			// null significa no existe" esto es un fallo técnico real.
			throw new DataAccessException("Error al buscar usuario por email", e);
		}
		
		
		
	}
	
	//Compribamos la existencia del usuario
	public boolean existeUsuarioPorId(int usuarioId) {
	    final String sql = "SELECT 1 FROM usuario WHERE id = ? LIMIT 1";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, usuarioId);
	        try (ResultSet rs = stmt.executeQuery()) {
	            return rs.next();
	        }

	    } catch (Exception ex) {
	        throw new DataAccessException("Error al comprobar existencia de usuario", ex);
	    }
	}
}

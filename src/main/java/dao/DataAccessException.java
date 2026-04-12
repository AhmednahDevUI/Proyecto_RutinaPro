package dao;

/**
 * Excepción de acceso a datos: indica fallos técnicos al hablar con la base de datos.
 * Se usa para diferenciar "no hay datos" (null) de "no puedo acceder a BD" (error).
 */
public class DataAccessException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}


//El objetivo de este archivo es la conexion de java con BDD
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	//usamos las variables de entorno para no revelar nuetras credenciales
    private static final String URL = System.getenv("DB_URL");
    private static final String USER = System.getenv("DB_USER");
    private static final String PASSWORD = System.getenv("DB_PASSWORD");
    
    

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se pudo cargar el driver JDBC", e);
        }

        if (URL == null || USER == null || PASSWORD == null) {
            throw new SQLException("Variables de entorno de BD no definidas");
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
    

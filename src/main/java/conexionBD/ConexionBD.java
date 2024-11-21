package conexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import conexionBD.ConexionBD;

public class ConexionBD {
	private static String driver = "com.mysql.jdbc.Driver"; // Conector Mysql que esta en Referenced Libraries, permite
	private static ConexionBD instance;
	private Connection connection;
	private static String usuario = "root"; // Usuario por default
	private static String password = "12345"; // Clave
	private static String url = "jdbc:mysql://localhost:3306/veterinaria"; // Indica que la conexion es local(localhost)
																			// y

	private ConexionBD() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // Carga del driver
			this.connection = DriverManager.getConnection(url, usuario, password);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static ConexionBD getInstance() {
		if (instance == null) {
			instance = new ConexionBD();
		} else {
			try {
				// Verifica si la conexión sigue abierta
				if (instance.getConnection().isClosed()) {
					instance = new ConexionBD(); // Si la conexión está cerrada, crea una nueva instancia
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}



	public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/veterinaria", "root", "12345");
    }
}

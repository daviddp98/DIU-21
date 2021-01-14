package address.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * Clase que gestiona la conexi�n con la BD de la aplicaci�n.
 *
 * @author David Delgado
 */
public class ConexionJDBC {

	Connection conn = null;

	/**
	 * Constructor de la clase. Crea una nueva conexi�n del tipo JDBC, en nuestro
	 * caso, utilizando el driver de mySQL.
	 */
	public ConexionJDBC() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/bdagenda", "root", "");
			System.out.println("X");
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * @return La conexi�n creada.
	 */
	public Connection getConnection() {
		return conn;
	}

	/**
	 * Borra la conexi�n creada.
	 */
	public void desconectar() {
		conn = null;
	}
}
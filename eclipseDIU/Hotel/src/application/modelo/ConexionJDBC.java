package application.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Desde esta clase haremos la conexi�n y desconexi�n con la base de datos
 *
 * @author David Delgado
 * 
 */
public class ConexionJDBC {

	/**
	 * Mediante este m�todo realizaremos la conexi�n con la BBDD
	 *
	 * @return objeto tipo Connection con la conexi�n
	 * @throws ClassNotFoundException se lanzar� esta excepci�n cuando el
	 *                                ClassLoader busque informaci�n sobre una clase
	 *                                y �sta no sea encontrada
	 */
	public Connection conectarBD() throws ClassNotFoundException {
		try {
			// creamos la conexi�n
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/bdhotel", "root", "");
			
			return conn;
		} catch (SQLException ex) {
			// lanzamos mensaje con excepci�n en caso de error
			System.out.println("\n--- SQLException capturada ---\n");
			while (ex != null) {
				System.out.println("Mensaje:   " + ex.getMessage());
				System.out.println("SQLState:  " + ex.getSQLState());
				System.out.println("ErrorCode: " + ex.getErrorCode());
				ex = ex.getNextException();
			}
		}
		return null;
	}

	/**
	 * Mediante este m�todo cerraremos la conexi�n con la BBDD
	 *
	 * @param conn variable del tipo Connection con la conexi�n para ser cerrada
	 */
	public void desconectarBD(Connection conn) {
		try {
			// cerramos la conexion
			conn.close();
		} catch (SQLException ex) {
			// lanzamos mensaje con excepci�n en caso de error
			System.out.println("\n--- SQLException capturada ---\n");
			while (ex != null) {
				System.out.println("Mensaje:   " + ex.getMessage());
				System.out.println("SQLState:  " + ex.getSQLState());
				System.out.println("ErrorCode: " + ex.getErrorCode());
				ex = ex.getNextException();
				System.out.println("");
			}
		}
	}
}
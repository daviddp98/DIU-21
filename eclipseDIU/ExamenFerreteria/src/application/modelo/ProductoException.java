package application.modelo;

/**
 *
 * Clase de tipo excepci�n personalizada para la aplicaci�n.
 *
 * @author David Delgado
 */

public class ProductoException extends Exception {

	private final String title;
	private final String header;
	private final String message;

	public String getTitle() {
		return title;
	}

	public String getHeader() {
		return header;
	}

	public String getMessage() {
		return message;
	}

	/**
	 * Constructor
	 */
	public ProductoException() {
		super();

		this.title = "Error de ejecuci�n";
		this.header = "La aplicaci�n ha sufrido un error:";
		this.message = "Se ha producido un error durante la ejecuci�n.";
	}

	/**
	 * Constructor de la excepci�n. Llama al m�todo "super()" de la clase Excepci�n,
	 * de la que extiende.
	 *
	 * @param ms Mensaje de error personalizado que se le pasa de forma opcional,
	 *           para que lo muestre al realizar el "throw".
	 */
	public ProductoException(String ms) {
		this.title = "Se ha producido un error";
		this.header = "La aplicaci�n ha sufrido un error:";
		this.message = ms;
	}

	public ProductoException(String title, String header, String ms) {
		this.title = title;
		this.header = header;
		this.message = ms;
	}

	public ProductoException(int sqlErrorcode) {

		switch (sqlErrorcode) {
		case 0:
			this.title = "Error en la conexi�n";
			this.header = "Hay un error en el proceso de conexi�n:";
			this.message = "No se ha podido establecer conexi�n con la base de datos.";

			break;

		default:
			this.title = "Error en el manejo de datos";
			this.header = "La aplicaci�n ha encontrado un problema:";
			this.message = "Se ha prodducido un error en el manejo de datos de la aplicaci�n.";

			break;
		}
	}
}
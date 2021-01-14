package application.modelo;

/**
 *
 * Clase de tipo excepción personalizada para la aplicación.
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

		this.title = "Error de ejecución";
		this.header = "La aplicación ha sufrido un error:";
		this.message = "Se ha producido un error durante la ejecución.";
	}

	/**
	 * Constructor de la excepción. Llama al método "super()" de la clase Excepción,
	 * de la que extiende.
	 *
	 * @param ms Mensaje de error personalizado que se le pasa de forma opcional,
	 *           para que lo muestre al realizar el "throw".
	 */
	public ProductoException(String ms) {
		this.title = "Se ha producido un error";
		this.header = "La aplicación ha sufrido un error:";
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
			this.title = "Error en la conexión";
			this.header = "Hay un error en el proceso de conexión:";
			this.message = "No se ha podido establecer conexión con la base de datos.";

			break;

		default:
			this.title = "Error en el manejo de datos";
			this.header = "La aplicación ha encontrado un problema:";
			this.message = "Se ha prodducido un error en el manejo de datos de la aplicación.";

			break;
		}
	}
}
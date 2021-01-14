package address.model;

/**
 *
 * Clase de tipo excepci�n personalizada para la aplicaci�n.
 *
 * @author David Delgado
 */

public class AgendaException extends Exception {

	/**
	 * Constructor de la excepci�n. Llama al m�todo "super()" de la clase Excepci�n,
	 * de la que extiende.
	 */
	public AgendaException() {
		super();
	}

	/**
	 * Constructor de la excepci�n. Llama al m�todo "super()" de la clase Excepci�n,
	 * de la que extiende.
	 *
	 * @param ms Mensaje de error personalizado que se le pasa de forma opcional,
	 *           para que lo muestre al realizar el "throw".
	 */
	public AgendaException(String ms) {
		super(ms);
	}
}
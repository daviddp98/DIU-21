package application.controlador;

import application.MainApp;
import application.modelo.ModeloFerreteria;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * Controlador del RootLayout
 * 
 * @author David Delgado
 */
public class RootLayoutController {

	// Reference to the main application
	private MainApp mainApp;
	private ModeloFerreteria modelo;

	public void setMainApp(MainApp mainApp, ModeloFerreteria m) {
		this.mainApp = mainApp;
		this.modelo = m;
	}

	/**
	 * Cierra la aplicación
	 */
	@FXML
	private void botonSalir() {
		System.exit(0);
	}

	/**
	 * Abre el JavaDoc de la aplicacion
	 */
	@FXML
	private void botonWebWiew() {
		WebView wv= new WebView();
		WebEngine we = wv.getEngine();

		we.load(getClass().getResource("/doc/index.html").toString());
	}
}
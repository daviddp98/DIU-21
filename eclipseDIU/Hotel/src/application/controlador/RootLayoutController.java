package application.controlador;

import application.MainApp;
import application.modelo.HotelModelo;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Clase correspondiente al controlador de nuestro menú
 * 
 * @author David Delgado
 *
 */
public class RootLayoutController {
	private MainApp main;
	private HotelModelo modelo;

	/**
	 * Gracias a este método asignamos a la clase el main y modelo creados al
	 * arrancar la aplicación
	 * 
	 * @param main   objeto clase Main
	 * @param modelo objeto clase Modelo
	 */
	public void setMainModelo(MainApp main, HotelModelo modelo) {
		this.main = main;
		this.modelo = modelo;
	}

	@FXML
	private void handleAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("About");
		alert.setHeaderText("Aplicación de David Delgado");
		alert.setContentText("Aplicación desarrollada por David Delgado Pineda.");
		alert.showAndWait();
	}

	/**
	 * Método que abre el web view
	 */
	@FXML
	private void WebView() {
		main.AbrirWebView();
	}
}
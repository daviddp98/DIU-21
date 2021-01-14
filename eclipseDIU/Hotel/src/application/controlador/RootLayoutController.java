package application.controlador;

import application.MainApp;
import application.modelo.HotelModelo;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Clase correspondiente al controlador de nuestro men�
 * 
 * @author David Delgado
 *
 */
public class RootLayoutController {
	private MainApp main;
	private HotelModelo modelo;

	/**
	 * Gracias a este m�todo asignamos a la clase el main y modelo creados al
	 * arrancar la aplicaci�n
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
		alert.setHeaderText("Aplicaci�n de David Delgado");
		alert.setContentText("Aplicaci�n desarrollada por David Delgado Pineda.");
		alert.showAndWait();
	}

	/**
	 * M�todo que abre el web view
	 */
	@FXML
	private void WebView() {
		main.AbrirWebView();
	}
}
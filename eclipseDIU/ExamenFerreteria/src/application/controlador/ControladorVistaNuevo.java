package application.controlador;

import application.modelo.Producto;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Dialogo para crear datos de un elemento moneda
 * 
 * @author David Delgado
 */
public class ControladorVistaNuevo {
	@FXML
	private TextField nombre;
	@FXML
	private TextField descripcion;
	@FXML
	private TextField precio;
	@FXML
	private TextField cantidad;

	private Stage dialogStage;
	private Producto p;
	private boolean okClicked = false;

	@FXML
	private void initialize() {

	}

	/**
	 * Sets the stage of this dialog.
	 * 
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
		this.dialogStage.initModality(Modality.NONE);
	}

	/**
	 * Inicializa un producto para crearlo nuevo o modificarlo
	 * 
	 * @param person
	 */
	public void setElementoEditable(Producto p) {
		this.p = p;

		nombre.setText(p.getNombre());
		descripcion.setText(p.getDescripcion());
		precio.setText(Float.toString(p.getPrecio()));
		cantidad.setText(Integer.toString(p.getCantidad()));
	}

	/**
	 * Devuelve True si el boton a sido pulsado
	 * 
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	/**
	 * Pone los datos introducidos del producto y lo devuelve al main
	 * 
	 */
	@FXML
	private void botonOk() {
		if (isInputValid()) {
			p.setNombre(nombre.getText());
			p.setDescripcion(descripcion.getText());
			p.setPrecio(Float.parseFloat(precio.getText()));
			p.setCantidad(Integer.parseInt(cantidad.getText()));

			okClicked = true;
			dialogStage.close();
		}
	}

	/**
	 * Cierra la ventana
	 */
	@FXML
	private void botonCancelar() {
		dialogStage.close();
	}

	/**
	 * Controla los Errores
	 * 
	 * @return true si las entradas son válidas
	 */
	private boolean isInputValid() {
		String errorMessage = "";

		if (nombre.getText() == null || nombre.getText().length() == 0) {
			errorMessage += "No es un nombre válido\n";
		}
		if (precio.getText() == null || precio.getText().length() == 0) {
			errorMessage += "No es un precio válido\n";
		} else {
			try {
				Float n = Float.parseFloat(precio.getText());
				if (n <= 0f)
					errorMessage += "No es un precio válido\n";
			} catch (NumberFormatException e) {
				errorMessage += "El precio debe de ser positivo\n";
			}
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Datos Incorrectos");
			alert.setHeaderText("Debes de introducir bien los datos:");
			alert.setContentText(errorMessage);

			alert.showAndWait();
			return false;
		}
	}
}
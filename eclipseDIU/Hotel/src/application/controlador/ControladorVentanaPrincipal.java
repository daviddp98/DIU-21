package application.controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import application.MainApp;
import application.modelo.ClienteVO;
import application.modelo.HotelModelo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControladorVentanaPrincipal {

	@FXML
	private Button btneditar, btncancelar, btnnuevareserva, btnaceptar, btnreservas;
	@FXML
	private TableView<ClienteVO> Tablaclientes;
	@FXML
	private TableColumn<ClienteVO, String> colNombre;
	@FXML
	private TableColumn<ClienteVO, String> colapellido;
	@FXML
	private AnchorPane pane1, pane2;
	@FXML
	private SplitPane split;
	@FXML
	private TextField textdireccion, textdni, textlocalidad, textprovincia, textapellido, textnombre;

	private MainApp main;
	private HotelModelo modelo;
	private ClienteVO c, clientte;
	private int index;
	private ArrayList<ClienteVO> listaclientes;

	public void setMainModelo(MainApp main, HotelModelo modelo) {
		this.main = main;
		this.modelo = modelo;
		OrdenarLista();
	}

	@FXML
	private void initialize() {
		colNombre.setCellValueFactory(cellData -> cellData.getValue().getNombreSP());
		colapellido.setCellValueFactory(cellData -> cellData.getValue().getApellidoSP());

		MostrarDatosCliente(null);

		Tablaclientes.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> MostrarDatosCliente(newValue));

		split.setDividerPositions(0.4);
		pane1.maxWidthProperty().bind(split.widthProperty().multiply(0.4));
		pane1.minWidthProperty().bind(split.widthProperty().multiply(0.4));
	}

	/**
	 * Asignamos los valores de los textfields con los valores de los datos del
	 * ClienteVO
	 * 
	 * @param cliente ClienteVO seleccionado
	 */
	private void MostrarDatosCliente(ClienteVO cliente) {
		if (cliente != null) {
			textdni.setText(cliente.getDni());
			textnombre.setText(cliente.getNombre());
			textapellido.setText(cliente.getApellido());
			textdireccion.setText(cliente.getDireccion());
			textlocalidad.setText(cliente.getLocalidad());
			textprovincia.setText(cliente.getProvincia());

		} else {
			textdni.setText("");
			textnombre.setText("");
			textapellido.setText("");
			textdireccion.setText("");
			textlocalidad.setText("");
			textprovincia.setText("");

		}
	}

	private ClienteVO ObtenerCliente() {
		ClienteVO clientemodificado = new ClienteVO(textdni.getText(), textnombre.getText(), textapellido.getText(),
				textdireccion.getText(), textlocalidad.getText(), textprovincia.getText());

		return clientemodificado;
	}

	/**
	 * Método que nos permite habilitar y deshabilitar los campos para hacer la
	 * edición de los campos
	 */
	@FXML
	private void EditarDatos() {
		if (main.getPersonData().size() > 0) {
			index = Tablaclientes.getSelectionModel().getSelectedIndex();
			clientte = Tablaclientes.getSelectionModel().getSelectedItem();
			if (clientte != null) {
				textnombre.setDisable(false);
				textapellido.setDisable(false);
				textdireccion.setDisable(false);
				textlocalidad.setDisable(false);
				textprovincia.setDisable(false);
				btnaceptar.setVisible(true);
				btncancelar.setVisible(true);
				btneditar.setVisible(false);
				btnreservas.setVisible(false);
				btnnuevareserva.setVisible(false);
				Tablaclientes.setDisable(true);
				textdni.setDisable(true);
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("WARNING!!!");
				alert.setHeaderText("Selección inválida");
				alert.setContentText("Debe seleccionar un elemento de la tabla.");
				alert.showAndWait();
			}
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("INFORMATION!!!");
			alert.setHeaderText("Tabla vacía");
			alert.setContentText("Actualmente la base de datos se encuentra vacía, pruebe más tarde.");
			alert.showAndWait();
		}
	}

	/**
	 * Método que vuelve a habilitar y deshabilitar los campos una vez el usuario
	 * pulsa en guardar cambios
	 */
	@FXML
	private void guardar() {
		textnombre.setDisable(true);
		textapellido.setDisable(true);
		textdireccion.setDisable(true);
		textlocalidad.setDisable(true);
		textprovincia.setDisable(true);
		btnaceptar.setVisible(false);
		btncancelar.setVisible(false);
		btneditar.setVisible(true);
		btnreservas.setVisible(true);
		btnnuevareserva.setVisible(true);
		Tablaclientes.setDisable(false);
		textdni.setDisable(false);

		if (ComprobarDatos()) {
			if (clientte.toString().equals(ObtenerCliente().toString())) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("INFORMATION!!!");
				alert.setHeaderText("Valores iguales");
				alert.setContentText("No ha modificado ningún dato del cliente.");
				alert.showAndWait();
			} else {
				Modificar(clientte);
			}
		} else {
			MostrarDatosCliente(clientte);
		}
	}

	/**
	 * Método que vuelve a habilitar y deshabilitar los campos una vez el usuario
	 * pulsa en cancelar cambios
	 */
	@FXML
	private void Cancelar() {
		textnombre.setDisable(true);
		textapellido.setDisable(true);
		textdireccion.setDisable(true);
		textlocalidad.setDisable(true);
		textprovincia.setDisable(true);
		btnaceptar.setVisible(false);
		btncancelar.setVisible(false);
		btneditar.setVisible(true);
		btnreservas.setVisible(true);
		btnnuevareserva.setVisible(true);
		Tablaclientes.setDisable(false);
		textdni.setDisable(false);

		MostrarDatosCliente(clientte);
	}

	/**
	 * Método que nos abre la vista de NuevaReserva cuando pulsamos en el botón
	 * Realizar reserva
	 */
	@FXML
	private void RealizarReserva() {
		ClienteVO cliente = null;

		if (modelo.ExisteDNI(textdni.getText())) {
			for (int i = 0; i < listaclientes.size(); i++) {
				if (listaclientes.get(i).getDni().equalsIgnoreCase((textdni.getText()))) {
					cliente = listaclientes.get(i);
				}
			}
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Nueva Reserva");
			alert.setHeaderText("");
			alert.setContentText("¿Desea realizar la reserva con los datos del cliente " + cliente.getNombre() + " "
					+ cliente.getApellido() + "?");
			alert.showAndWait();

			if (alert.getResult().getText().equals("Aceptar")) {
				for (int i = 0; i < listaclientes.size(); i++) {
					if (listaclientes.get(i).getDni().equals(textdni.getText())) {
						cliente = listaclientes.get(i);
					}
				}
			} else {
				cliente = null;
			}
		}

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/NuevaReserva.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Datos Persona");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(main.getPrimaryStage());
			dialogStage.setResizable(false);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			ControladorNuevaReserva controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setMainModelo(main, modelo, cliente, this);
			dialogStage.showAndWait();
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR!!!");
			alert.setHeaderText("IOException");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
	}

	/**
	 * Método que nos permite abrir la vista ventanaReservas cuando el usuario
	 * selecciona un cliente y pulsa en ver reservas
	 */
	@FXML
	private void VerReservas() {
		if (modelo.ExisteDNI(textdni.getText())) {
			for (int i = 0; i < listaclientes.size(); i++) {
				if (listaclientes.get(i).getDni().equals(textdni.getText())) {
					c = listaclientes.get(i);
				}
			}
			main.AbrirVistaReservas(c);
		} else if (textdni.getText().equals("")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("INFORMATION!!!");
			alert.setHeaderText("DNI no válido");
			alert.setContentText("Debe introducir un DNI en la caja de texto.");
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("WARNING!!!");
			alert.setHeaderText("DNI no válido");
			alert.setContentText("Debe introducir un DNI registrado para ver las reservas de ese cliente.");
			alert.showAndWait();
			textdni.setText("");
		}
	}

	/**
	 * Método que realiza las comprobaciones de los datos cuando el usuario modifica
	 * un cliente y, en caso de no haber ningun error, se realizan los cambios en la
	 * tabla y en la base de datos
	 * 
	 * @param c ClienteVO con los datos introducidos en la modificación
	 */
	private void Modificar(ClienteVO c) {
		ClienteVO mod;
		mod = ObtenerCliente();

		if (modelo.ExisteDNI(mod.getDni()) && !mod.getDni().equals(c.getDni())) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR!!!");
			alert.setHeaderText("DNI duplicado");
			alert.setContentText("No se pudo modificar los datos del cliente por duplicidad en el DNI");
			alert.showAndWait();
			MostrarDatosCliente(c);
		} else {
			if (modelo.Modificar(c, mod, c.getDni())) {
				MostrarDatosCliente(mod);
				main.getPersonData().set(index, mod);
				OrdenarLista();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("INFORMATION!!!");
				alert.setHeaderText("Modificado con éxito");
				alert.setContentText("Se han registrado exitosamente los cambios del cliente " + mod.getNombre() + " "
						+ mod.getApellido());
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("ERROR!!!");
				alert.setHeaderText("Fallo en la modificación");
				alert.setContentText("Un error hizo que los cambios del cliente " + mod.getNombre() + " "
						+ mod.getApellido() + " no se puedan llevar a cabo.");
				alert.showAndWait();
			}
		}
	}

	/**
	 * Este método nos permite comprobar los datos introducidos en la modificación
	 * 
	 * @return booleano con el resultado de la comprobación
	 */
	public boolean ComprobarDatos() {
		String error = "";

		if (textdni.getText() == null || textdni.getText().length() == 0 || textdni.getText().length() > 9) {
			error += "Dni\n";
		}
		if (textnombre.getText() == null || textnombre.getText().length() == 0 || textnombre.getText().length() > 20) {
			error += "Nombre\n";
		}
		if (textapellido.getText() == null || textapellido.getText().length() == 0
				|| textapellido.getText().length() > 20) {
			error += "Apellido\n";
		}
		if (textdireccion.getText() == null || textdireccion.getText().length() == 0
				|| textdireccion.getText().length() > 50) {
			error += "Direccion\n";
		}
		if (textlocalidad.getText() == null || textlocalidad.getText().length() == 0
				|| textlocalidad.getText().length() > 20) {
			error += "Localidad\n";
		}
		if (textprovincia.getText() == null || textprovincia.getText().length() == 0
				|| textprovincia.getText().length() > 20) {
			error += "Provincia\n";
		}

		if (error.length() == 0) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("WARNING!!!");
			alert.setHeaderText("Campos inválidos");
			alert.setContentText("Debe rellenar correctamente los siguientes campos: \n" + error);
			alert.showAndWait();
			return false;
		}
	}

	/**
	 * Este método nos permite ordenar la lista cada vez que realizamos una
	 * modificación
	 */
	public void OrdenarLista() {
		listaclientes = modelo.CargarPersonas();
		main.getPersonData().clear();
		Collections.sort(listaclientes);
		for (int i = 0; i < listaclientes.size(); i++) {
			main.getPersonData().add(listaclientes.get(i));
		}
		Tablaclientes.setItems(main.getPersonData());
	}
}
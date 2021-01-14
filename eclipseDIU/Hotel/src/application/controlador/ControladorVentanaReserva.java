package application.controlador;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import application.MainApp;
import application.modelo.ClienteVO;
import application.modelo.HotelModelo;
import application.modelo.ReservaVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControladorVentanaReserva {

	@FXML
	private Button guardar, cancelar, cancelarmod, volver, nuevareserva, modificar;
	@FXML
	private DatePicker fechasalida, fechaentrada;
	@FXML
	private ComboBox<Integer> habitaciones;
	@FXML
	private CheckBox fumador;
	@FXML
	private Label labeltabaco;
	@FXML
	private ToggleGroup radiogroup;
	@FXML
	private SplitPane split;
	@FXML
	private AnchorPane pane1;
	@FXML
	private TableColumn<ReservaVO, String> Colfecha, Colcodigo;
	@FXML
	private RadioButton regdesayuno, regmedia, regpension;
	@FXML
	private ComboBox<String> tipohabitacion;
	@FXML
	private TableView<ReservaVO> TablaReservas;

	private MainApp main;
	private HotelModelo modelo;
	private ClienteVO cliente;
	private ReservaVO reservva;
	private ArrayList<ReservaVO> listareservas;
	private int index;

	public void setMainModelo(MainApp main, HotelModelo modelo, ClienteVO cliente) {
		this.main = main;
		this.modelo = modelo;
		TablaReservas.setItems(main.getBookingData());
		this.cliente = cliente;
		listareservas = new ArrayList<>(main.getBookingData());
	}

	private ClienteVO cliente() {
		return cliente;
	}

	@FXML
	private void initialize() {
		split.setDividerPositions(0.4);
		pane1.maxWidthProperty().bind(split.widthProperty().multiply(0.4));
		pane1.minWidthProperty().bind(split.widthProperty().multiply(0.4));

		MostrarDatosReserva(null);
		TablaReservas.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> MostrarDatosReserva(newValue));
		radiogroup = new ToggleGroup();
		regdesayuno.setToggleGroup(radiogroup);
		regmedia.setToggleGroup(radiogroup);
		regpension.setToggleGroup(radiogroup);
		tipohabitacion.getItems().clear();
		tipohabitacion.getItems().addAll("Doble de uso individual", "Doble", "Junior Suite", "Suite");
		habitaciones.getItems().clear();
		habitaciones.getItems().addAll(1, 2, 3, 4);

		Colcodigo.setCellValueFactory(cellData -> cellData.getValue().getCod());
		Colfecha.setCellValueFactory(cellData -> cellData.getValue().getFecha());
	}

	@SuppressWarnings({ "deprecation" })
	private ReservaVO ObtenerReserva() {
		Date entrada = Date.from(fechaentrada.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date salida = Date.from(fechasalida.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

		int numhab = (int) habitaciones.getValue();

		String tipo = tipohabitacion.getValue();
		int n= habitaciones.getValue();

		RadioButton selectedRadioButton = (RadioButton) radiogroup.getSelectedToggle();
		String regimen = selectedRadioButton.getText();
		String fum, estado;
		if (fumador.isSelected()) {
			fum = "Si";
		} else {
			fum = "No";
		}
		Date hoy = new Date();
		if (entrada.getYear() == hoy.getYear() && entrada.getMonth() == hoy.getMonth()
				&& entrada.getDay() == hoy.getDay()) {
			estado = "en curso";
		} else {
			estado = "pendiente";
		}

		ReservaVO reservamodificada = new ReservaVO(reservva.getCodigo(), reservva.getDni(), entrada, salida, numhab,
				tipo, regimen, fum, estado);

		return reservamodificada;
	}

	/**
	 * Método que muestra en sus respectivos campos los valores de la Reserva
	 * seleccionada
	 * 
	 * @param reserva ReservaVO seleccionada en la tabla de reservas
	 */
	private void MostrarDatosReserva(ReservaVO reserva) {
		if (reserva == null) {
			fumador.setSelected(false);
			regdesayuno.setSelected(false);
			regmedia.setSelected(false);
			regpension.setSelected(false);
			tipohabitacion.getItems().clear();
			tipohabitacion.getItems().addAll("Doble de uso individual", "Doble", "Junior Suite", "Suite");
			habitaciones.getItems().clear();
			habitaciones.getItems().addAll(1, 2, 3, 4);
			fechaentrada.setValue(null);
			fechasalida.setValue(null);
			habitaciones.setValue(1);
			labeltabaco.setText("");
		} else {
			if (reserva.getFumador().get().equals("Si")) {
				fumador.setSelected(true);
				labeltabaco.setText(
						"En virtud de la ley de sanidad se informa a los clientes de que solo podrán fumar en las habitaciones reservadas para tal fin.");
			} else {
				fumador.setSelected(false);
			}
			switch (reserva.getTipohabitacion().get()) {

			case "Doble de uso individual":
				tipohabitacion.getSelectionModel().select(0);
				break;
			case "Doble":
				tipohabitacion.getSelectionModel().select(1);
				break;
			case "Junior Suite":
				tipohabitacion.getSelectionModel().select(2);
				break;
			case "Suite":
				tipohabitacion.getSelectionModel().select(3);
				break;
			}
			switch (reserva.getHabitaciones()) {

			case 1:
				habitaciones.getSelectionModel().select(0);
				break;
			case 2:
				habitaciones.getSelectionModel().select(1);
				break;
			case 3:
				habitaciones.getSelectionModel().select(2);
				break;
			case 4:
				habitaciones.getSelectionModel().select(3);
				break;
			}

			switch (reserva.getRegimen().get()) {
			case "Desayuno":
				regdesayuno.setSelected(true);
				break;

			case "Media pensión":
				regmedia.setSelected(true);
				break;

			case "Pensión completa":
				regpension.setSelected(true);
				break;
			}

			habitaciones.setValue(reserva.getHabitaciones());
			Calendar cal = Calendar.getInstance();
			cal.setTime(reserva.getFechaentrada());
			int year = cal.get(Calendar.YEAR);
			int month = 1 + cal.get(Calendar.MONTH);
			int day = cal.get(Calendar.DAY_OF_MONTH);
			fechaentrada.setValue(LocalDate.of(year, month, day));
			cal.setTime(reserva.getFechasalida());
			year = cal.get(Calendar.YEAR);
			month = 1 + cal.get(Calendar.MONTH);
			day = cal.get(Calendar.DAY_OF_MONTH);
			fechasalida.setValue(LocalDate.of(year, month, day));
		}
	}

	@FXML
	private void Fumador() {
		if (fumador.isSelected()) {
			labeltabaco.setText(
					"En virtud de la ley de sanidad se informa a los clientes de que solo podrán fumar en las habitaciones reservadas para tal fin.");
		} else {
			labeltabaco.setText("");
		}
	}

	/**
	 * Método que habilita y deshabilita los campor para editar una reserva
	 */
	@FXML
	private void EditarReserva() {
		if (main.getBookingData().size() > 0) {
			index = TablaReservas.getSelectionModel().getSelectedIndex();
			reservva = TablaReservas.getSelectionModel().getSelectedItem();
			if (reservva != null) {
				// if reserva estado = finalziada
				if (reservva.getEstado().get().equals("finalizada")) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("INFORMATION!!!");
					alert.setHeaderText("Reserva finalizada");
					alert.setContentText("No se puede editar una reserva finalizada");
					alert.showAndWait();
				} else if (reservva.getEstado().get().equals("cancelada")) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("INFORMATION!!!");
					alert.setHeaderText("Reserva cancelada");
					alert.setContentText("No se puede editar una reserva cancelada");
					alert.showAndWait();
				} else if (reservva.getEstado().get().equals("en curso")) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("INFORMATION!!!");
					alert.setHeaderText("Reserva en curso");
					alert.setContentText("No se puede editar una reserva en curso");
					alert.showAndWait();
				} else {
					fechaentrada.setDisable(false);
					fechasalida.setDisable(false);
					habitaciones.setDisable(false);
					tipohabitacion.setDisable(false);
					regdesayuno.setDisable(false);
					regmedia.setDisable(false);
					regpension.setDisable(false);
					fumador.setDisable(false);
					TablaReservas.setDisable(true);
					guardar.setVisible(true);
					cancelarmod.setVisible(true);
					cancelar.setVisible(false);
					volver.setVisible(false);
					nuevareserva.setVisible(false);
					modificar.setVisible(false);
				}
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
			alert.setContentText("Actualmente el cliente con DNI " + cliente.getDni()
					+ " no tiene ninguna reserva, pruebe más tarde.");
			alert.showAndWait();
		}
	}

	/**
	 * Método que nos permite abrir la vista de NuevaReserva cuando selecciona el
	 * usuario el botópn de nueva reserva
	 */
	@FXML
	void NuevaReserva() {
		ClienteVO c;

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Nueva Reserva");
		alert.setHeaderText("");
		alert.setContentText("¿Desea realizar la reserva con los datos del cliente " + cliente.getNombre() + " "
				+ cliente.getApellido() + "?");
		alert.showAndWait();

		if (alert.getResult().getText().equals("Aceptar")) {
			c = cliente();
		} else {
			c = null;
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

			controller.setModeloMain(main, modelo, c, this);
			dialogStage.showAndWait();
		} catch (IOException e) {
			Alert a = new Alert(AlertType.ERROR);
			a.setTitle("ERROR!!!");
			a.setHeaderText("IOException");
			a.setContentText(e.getMessage());
			a.showAndWait();
		}
	}

	/**
	 * Método que nos permite cambiar cancelar una reserva
	 */
	@FXML
	void CancelarReserva() {
		ReservaVO r;

		if (main.getBookingData().size() > 0) {
			r = TablaReservas.getSelectionModel().getSelectedItem();
			if (r != null) {
				if (r.getEstado().get().equals("cancelada")) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("INFORMATION!!!");
					alert.setHeaderText("Reserva ya cancelada");
					alert.setContentText(
							"La reserva con código " + r.getCodigo() + " estaba cancelada con anterioridad");
					alert.showAndWait();
				} else if (r.getEstado().get().equals("en curso")) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("INFORMATION!!!");
					alert.setHeaderText("Reserva en curso");
					alert.setContentText("La reserva con código " + r.getCodigo()
							+ " no puede cancelarse ya que está actualmente en curso");
					alert.showAndWait();
				} else if (r.getEstado().get().equals("finalizada")) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("INFORMATION!!!");
					alert.setHeaderText("Reserva finalizada");
					alert.setContentText(
							"La reserva con código " + r.getCodigo() + " no puede cancelarse ya que está finalizada");
					alert.showAndWait();
				}
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
			alert.setContentText("Actualmente el cliente con DNI " + cliente.getDni()
					+ " no tiene ninguna reserva, pruebe más tarde.");
			alert.showAndWait();
		}
	}

	/**
	 * Método que nos permite volver a la vista principal
	 */
	@FXML
	private void Volver() {
		main.AbrirVistaPrincipal();
	}

	/**
	 * Método que vuelve a habilitar y deshabilitar los campos cuando pulsa el
	 * usuario el boton aceptar
	 * 
	 */
	@FXML
	private void Aceptar() {
		fechaentrada.setDisable(true);
		fechasalida.setDisable(true);
		habitaciones.setDisable(true);
		tipohabitacion.setDisable(true);
		regdesayuno.setDisable(true);
		regmedia.setDisable(true);
		regpension.setDisable(true);
		fumador.setDisable(true);
		TablaReservas.setDisable(false);
		guardar.setVisible(false);
		cancelarmod.setVisible(false);
		cancelar.setVisible(true);
		volver.setVisible(true);
		nuevareserva.setVisible(true);
		modificar.setVisible(true);
		ReservaVO r = ObtenerReserva();

		if (reservva.toString().equals(ObtenerReserva().toString())) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("INFORMATION!!!");
			alert.setHeaderText("Valores iguales");
			alert.setContentText("No ha modificado ningún dato de la reserva.");
			alert.showAndWait();
		} else {
			if (ComprobarDatos()
					&& ComprobarDisponibilidad(r.getCodigo(), r.getHabitaciones(), r.getTipoHabitacion())) {
				if (modelo.ModificarReserva(reservva, r)) {
					main.getBookingData().set(index, r);
					OrdenarLista(r.getDni());
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("INFORMATION!!!");
					alert.setHeaderText("Cambios realizados");
					alert.setContentText(
							"Se han realizado exitosamente los cambios en la reserva registrada con codigo "
									+ r.getCodigo());
					alert.showAndWait();
				} else {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("WARNING!!!");
					alert.setHeaderText("Error en modificación");
					alert.setContentText("No se pudieron llevar a cabo las modificaciones para la reserva con codigo "
							+ r.getCodigo());
					alert.showAndWait();
				}
			} else {
				MostrarDatosReserva(reservva);
			}
		}
	}

	/**
	 * Método que vuelve a habilitar y deshabilitar los campos cuando pulsa el
	 * usuario el boton cancelar
	 * 
	 */
	@FXML
	private void Cancelar() {
		fechaentrada.setDisable(true);
		fechasalida.setDisable(true);
		habitaciones.setDisable(true);
		tipohabitacion.setDisable(true);
		regdesayuno.setDisable(true);
		regmedia.setDisable(true);
		regpension.setDisable(true);
		fumador.setDisable(true);
		TablaReservas.setDisable(false);
		guardar.setVisible(false);
		cancelarmod.setVisible(false);
		cancelar.setVisible(true);
		volver.setVisible(true);
		nuevareserva.setVisible(true);
		modificar.setVisible(true);
		MostrarDatosReserva(reservva);
	}

	/**
	 * Método que nos permite ordenar la lista de reservas
	 * 
	 * @param dni dni del cliente para obtener la lista de reservas con esa persona
	 */
	public void OrdenarLista(String dni) {
		listareservas = modelo.CargarReservasPorPersona(dni);
		main.getBookingData().clear();
		Collections.sort(listareservas);
		for (int i = 0; i < listareservas.size(); i++) {
			main.getBookingData().add(listareservas.get(i));
		}
		TablaReservas.setItems(main.getBookingData());
	}

	/**
	 * Método que nos permite comprobar los datos introducidos cuando el usuario
	 * modifica una reserva
	 * 
	 * @return boolean con el resultado de la comprobacion
	 */
	public boolean ComprobarDatos() {
		boolean f1 = false, f2 = false;
		String error = "";

		if (fechaentrada.getValue() == null) {
			error += "Fecha entrada\n";
		} else {
			f1 = true;
		}

		if (fechasalida.getValue() == null) {
			error += "Fecha salida\n";
		} else {
			f2 = true;
		}
		if (tipohabitacion.getSelectionModel().getSelectedItem() == null) {
			error += "Tipo de habitación\n";
		}
		if (habitaciones.getSelectionModel().getSelectedItem() == null) {
			error += "Nº de Habitaciones\n";
		}
		if (regdesayuno.isSelected() || regmedia.isSelected() || regpension.isSelected()) {

		} else {
			error += "Régimen de alojamiento\n";
		}

		if (f1 && f2) {
			LocalDate ent;
			LocalDate sal;
			LocalDate hoy = LocalDate.now();
			ent = fechaentrada.getValue();
			sal = fechasalida.getValue();
			if (ent.equals(sal)) {
				fechaentrada.setValue(null);
				error += "Fecha de entrada y fecha de salida no pueden coincidir\n";
			} else if (sal.isBefore(ent)) {
				if (sal.isBefore(hoy)) {
					error += "Fecha de entrada no puede ser posterior a la fecha de salida\n";
					error += "Fecha actual no puede ser posterior a la fecha de salida\n";
					fechaentrada.setValue(null);
					fechasalida.setValue(null);
				} else {
					error += "Fecha de entrada no puede ser posterior a la fecha de salida\n";
					fechaentrada.setValue(null);
				}
			} else if (ent.isBefore(hoy)) {
				if (sal.isBefore(hoy)) {
					error += "La fecha actual no puede ser posterior a la fecha de entrada y de salida\n";
					fechaentrada.setValue(null);
					fechasalida.setValue(null);
				} else {
					error += "La fecha actual no puede ser posterior a la fecha de entrada\n";
					fechaentrada.setValue(null);
				}
			}
		}

		if (error.length() == 0) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("WARNING!!!");
			alert.setHeaderText("Campos inválidos");
			alert.setContentText("Complete los siguientes datos correctamente: \n" + error);
			alert.showAndWait();
			return false;
		}
	}

	/**
	 * Método que nos permite comprobar la disponibilidad de las habitaciones a la
	 * hora de realizar una nueva reserva
	 * 
	 * @param numhab entero con el numero de habitaciones a reservar por el usuario
	 * @param tipo   String con el tipo de habitacion
	 * @param codigo entero con el codigo de la reserva
	 * 
	 * @return booleano con el resultdo de la comprobación
	 */
	public boolean ComprobarDisponibilidad(int codigo, int numhab, String tipo) {
		ArrayList<ReservaVO> listareservas = modelo.CargarReservas();
		int habitaciones = 0;

		switch (tipo) {
		case "Doble de uso individual":
			habitaciones = modelo.HabitacionDobleIndividual();
			break;
		case "Doble":
			habitaciones = modelo.HabitacionDobleCompartida();
			break;
		case "JuniorSuite":
			habitaciones = modelo.HabitacionSuiteJr();
			break;
		case "Suite":
			habitaciones = modelo.HabitacionSuite();
			break;
		}

		LocalDate entradaform = fechaentrada.getValue();
		LocalDate salidafomr = fechasalida.getValue();
		for (int i = 0; i < listareservas.size(); i++) {
			ReservaVO r = listareservas.get(i);
			if (r.getCodigo() != codigo) {
				if (r.getTipoHabitacion().equals(tipo) && !r.getEstado().get().equals("cancelada")) {
					LocalDate entradalista, salidalista;

					Calendar cal = Calendar.getInstance();
					cal.setTime(r.getFechaentrada());
					int year = cal.get(Calendar.YEAR);
					int month = 1 + cal.get(Calendar.MONTH);
					int day = cal.get(Calendar.DAY_OF_MONTH);
					entradalista = LocalDate.of(year, month, day);

					cal.setTime(r.getFechasalida());
					year = cal.get(Calendar.YEAR);
					month = 1 + cal.get(Calendar.MONTH);
					day = cal.get(Calendar.DAY_OF_MONTH);
					salidalista = LocalDate.of(year, month, day);
				}
			}
		}

		if (habitaciones >= 0) {
			if (habitaciones >= numhab) {
				return true;
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("WARNING!!!");
				alert.setHeaderText("Disponibilidad insuficiente");
				alert.setContentText("Actualmente disponemos de " + habitaciones + " habitaciones tipo " + tipo);
				alert.showAndWait();
				return false;
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("WARNING!!!");
			alert.setHeaderText("Disponibilidad insuficiente");
			alert.setContentText("Actualmente disponemos de 0 habitaciones tipo " + tipo);
			alert.showAndWait();
			return false;
		}
	}
}
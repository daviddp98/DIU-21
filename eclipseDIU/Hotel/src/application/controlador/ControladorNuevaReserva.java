package application.controlador;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import application.MainApp;
import application.modelo.ClienteVO;
import application.modelo.HotelModelo;
import application.modelo.ReservaVO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * Clase controladora de la vista en la cual crearemos las reservas de los
 * clientes
 * 
 * @author David Delgado
 *
 */
public class ControladorNuevaReserva {
	@FXML
	private Button btndni;
	@FXML
	private DatePicker fechasalida, fechaentrada;
	@FXML
	private Label labeltabaco;
	@FXML
	private TextField textdireccion, textdni, textlocalidad, textprovincia, textapellido, textnombre;
	@FXML
	private ComboBox<Integer> habitaciones;
	@FXML
	private CheckBox fumador;
	@FXML
	private ComboBox<String> tipohabitacion;
	@FXML
	private ToggleGroup radiogroup;
	@FXML
	private RadioButton regdesayuno, regmedia, regpension;

	private ArrayList<ClienteVO> listaclientes;
	private Stage dialogStage;
	private MainApp main;
	private HotelModelo modelo;
	private ControladorVentanaPrincipal contvp;
	private ControladorVentanaReserva contvr;

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	@FXML
	private void initialize() {
		textdni.requestFocus();
		radiogroup = new ToggleGroup();
		regdesayuno.setToggleGroup(radiogroup);
		regmedia.setToggleGroup(radiogroup);
		regpension.setToggleGroup(radiogroup);
		regdesayuno.setSelected(false);
		regmedia.setSelected(false);
		regpension.setSelected(false);
		tipohabitacion.getItems().clear();
		tipohabitacion.getItems().addAll("Doble de uso individual", "Doble", "Junior Suite", "Suite");
		fechaentrada.setValue(null);
		fechasalida.setValue(null);
		habitaciones.setValue(1);
		textdni.setText("");
		textnombre.setText("");
		textapellido.setText("");
		textdireccion.setText("");
		textlocalidad.setText("");
		textprovincia.setText("");
		textnombre.setDisable(false);
		textapellido.setDisable(false);
		textdireccion.setDisable(false);
		textlocalidad.setDisable(false);
		textprovincia.setDisable(false);
		textdni.setDisable(false);
		btndni.setDisable(false);
		fumador.setSelected(false);
		labeltabaco.setText("");
	}

	public void setMainModelo(MainApp main, HotelModelo modelo, ClienteVO cliente, ControladorVentanaPrincipal contvp) {
		this.main = main;
		this.modelo = modelo;
		this.contvp = contvp;
		if (cliente != null) {
			SetCliente(cliente);
		}
	}

	public void setModeloMain(MainApp main, HotelModelo modelo, ClienteVO cliente, ControladorVentanaReserva contvr) {
		this.main = main;
		this.modelo = modelo;
		this.contvr = contvr;
		if (cliente != null) {
			SetCliente(cliente);
		}
	}

	private void SetCliente(ClienteVO cliente) {
		textdni.setText(cliente.getDni());
		textnombre.setText(cliente.getNombre());
		textapellido.setText(cliente.getApellido());
		textdireccion.setText(cliente.getDireccion());
		textlocalidad.setText(cliente.getLocalidad());
		textprovincia.setText(cliente.getProvincia());
		textnombre.setDisable(true);
		textapellido.setDisable(true);
		textdireccion.setDisable(true);
		textlocalidad.setDisable(true);
		textprovincia.setDisable(true);
		textdni.setDisable(true);
		btndni.setDisable(true);
	}

	/**
	 * Este método nos permititrá saber si el DNI introducido por el usuario existe
	 * en nuestra lista de clientes y cargar sus datos en nuestra vista
	 */
	@FXML
	private void ExisteDNI() {
		String dni = textdni.getText();
		if (dni.length() > 0) {
			if (modelo.ExisteDNI(dni.toUpperCase())) {
				ClienteVO cliente = null;
				listaclientes = new ArrayList<>(main.getPersonData());
				for (int i = 0; i < listaclientes.size(); i++) {
					if (listaclientes.get(i).getDni().equalsIgnoreCase(textdni.getText())) {
						cliente = listaclientes.get(i);
					}
				}
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("INFORMATION!!!");
				alert.setHeaderText("DNI existente");
				alert.setContentText("Se cargarán los datos del cliente con DNI " + dni + ".");
				alert.showAndWait();
				SetCliente(cliente);
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("INFORMATION!!!");
				alert.setHeaderText("DNI inexistente");
				alert.setContentText("El dni " + dni + " no se encuentra registrado.");
				alert.showAndWait();
			}
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("INFORMATION!!!");
			alert.setHeaderText("DNI vacío");
			alert.setContentText("Introduzca un valor válido en el campo DNI para comprobar su existencia.");
			alert.showAndWait();
		}
	}

	/**
	 * Este método nos permite iniciar todos los campos de nuestra vista cuando el
	 * usuario decide borrar todos los datos
	 */
	@FXML
	private void Limpiar() {
		initialize();
	}

	/**
	 * Cuando e usuario pulsa en aceptar procedemos a registrar el cliente (si no
	 * existe previamente) y la reserva creada por el usuario
	 */
	@SuppressWarnings("deprecation")
	@FXML
	private void Aceptar() {
		if (ComprobarDatos()) {
			ClienteVO cliente;
			int codigo, numhab;
			String dni, tipo, regimen, fum, estado;
			Date entrada, salida;
			if (modelo.ExisteDNI(textdni.getText())) {
				cliente = null;
			} else {
				cliente = new ClienteVO(textdni.getText(), textnombre.getText(), textapellido.getText(),
						textdireccion.getText(), textlocalidad.getText(), textprovincia.getText());
			}
			codigo = modelo.Codigo() + 1;
			dni = textdni.getText();
			entrada = Date.from(fechaentrada.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			salida = Date.from(fechasalida.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			numhab = (int) habitaciones.getValue();
			tipo = tipohabitacion.getValue();
			RadioButton selectedRadioButton = (RadioButton) radiogroup.getSelectedToggle();
			regimen = selectedRadioButton.getText();
			if (fumador.isSelected()) {
				fum = "Si";
			} else {
				fum = "No";
			}
			Date hoy = new Date();
			if (entrada.getYear() == hoy.getYear() && entrada.getMonth() == hoy.getMonth()
					&& entrada.getDate() == hoy.getDate()) {
				estado = "en curso";
			} else {
				estado = "pendiente";
			}
			if (ComprobarDisponibilidad(numhab, tipo, entrada, salida)) {
				ReservaVO reserva = new ReservaVO(codigo, dni, entrada, salida, numhab, tipo, regimen, fum, estado);
				if (modelo.AñadirReserva(cliente, reserva)) {
					if (cliente != null) {
						main.getPersonData().add(cliente);
						contvp.OrdenarLista();
					}
					main.getBookingData().add(reserva);
					if (contvr != null) {
						contvr.OrdenarLista(reserva.getDni());
					}
				}
				dialogStage.close();
			}
		}
	}

	/**
	 * Este método permite mostrar el mensaje sobre las habitaciones de fumadores en
	 * caso de estar el tick activado
	 */
	@FXML
	private void CheckFumador() {
		if (fumador.isSelected()) {
			labeltabaco.setText(
					"En virtud de la ley de sanidad se informa a los clientes de que solo podrán fumar en las habitaciones reservadas para tal fin.");
		} else {
			labeltabaco.setText("");
		}
	}

	/**
	 * Método que cierra la ventana cuando el usuario pulsa en canclar
	 */
	@FXML
	private void Cancelar() {
		dialogStage.close();
	}

	/**
	 * Método que nos permite comprobar la disponibilidad de las habitaciones a la
	 * hora de realizar una nueva reserva
	 * 
	 * @param numhab  entero con el numero de habitaciones a reservar por el usuario
	 * @param tipo    String con el tipo de habitacion
	 * @param entrada Date con la fecha de entrada
	 * @param salida  Date con la fecha de salida
	 * @return boolean con el valor de la disponibilidad
	 */
	public boolean ComprobarDisponibilidad(int numhab, String tipo, Date entrada, Date salida) {
		ArrayList<ReservaVO> listareservas = modelo.CargarReservas();
		int habitaciones = 0;

		switch (tipo) {
		case "Doble de uso individual":
			habitaciones = modelo.HabitacionDobleIndividual();
			break;
		case "Doble":
			habitaciones = modelo.HabitacionDobleCompartida();
			break;
		case "Junior Suite":
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

	/**
	 * Mediante este método comprobaremos todos los datos introducidos por el
	 * usuario para comprobar que se han introducido los datos de forma correcta
	 * 
	 * @return boolean con el resultado de la comprobacion
	 */
	public boolean ComprobarDatos() {
		boolean f1 = false, f2 = false;
		String error = "";

		if (textdni.getText() == null || textdni.getText().length() == 0 || textdni.getText().length() > 9) {
			error += "Dni\n";
			textdni.setText("");
		}
		if (textnombre.getText() == null || textnombre.getText().length() == 0 || textnombre.getText().length() > 20) {
			error += "Nombre\n";
			textnombre.setText("");
		}
		if (textapellido.getText() == null || textapellido.getText().length() == 0
				|| textapellido.getText().length() > 20) {
			error += "Apellido\n";
			textapellido.setText("");
		}
		if (textdireccion.getText() == null || textdireccion.getText().length() == 0
				|| textdireccion.getText().length() > 50) {
			error += "Direccion\n";
			textdireccion.setText("");
		}
		if (textlocalidad.getText() == null || textlocalidad.getText().length() == 0
				|| textlocalidad.getText().length() > 20) {
			error += "Ciudad\n";
			textlocalidad.setText("");
		}
		if (textprovincia.getText() == null || textprovincia.getText().length() == 0
				|| textprovincia.getText().length() > 20) {
			error += "Ciudad\n";
			textprovincia.setText("");
		}
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
}
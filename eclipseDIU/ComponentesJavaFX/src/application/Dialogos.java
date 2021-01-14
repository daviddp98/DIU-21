package application;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Dialogos extends Application {

	private RadioButton rbInformacion, rbInformacionSinCabecera, rbAdvertencia, rbError, rbConfirmacion,
			rbConfirmacionPersonalizado, rbEntradaTexto, rbEleccion, rbPersonalizado;
	private Label lbElige;
	private ToggleGroup grupo;
	private Button btMostrar;
	private TextField tfPoder;

	private void mostrarDialogoInformacion() {
		Alert dialogo = new Alert(AlertType.INFORMATION);
		dialogo.setTitle("Di�logo informaci�n");
		dialogo.setHeaderText("Esta es la vista de un di�logo de informaci�n");
		dialogo.setContentText("Aqu� informamos al usuario de algo");

		dialogo.showAndWait();
	}

	private void mostrarDialogoInformacionSinCabecera() {
		Alert dialogo = new Alert(AlertType.INFORMATION);
		dialogo.setTitle("Di�logo informaci�n");
		dialogo.setHeaderText(null);
		dialogo.setContentText("Aqu� informamos al usuario de algo");

		dialogo.showAndWait();
	}

	private void mostrarDialogoAdvertencia() {
		Alert alerta = new Alert(AlertType.WARNING);
		alerta.setTitle("Di�logo advertencia");
		alerta.setHeaderText(null);
		alerta.setContentText("Aqu� advertimos al usuario de algo");

		alerta.showAndWait();
	}

	private void mostrarDialogError() {
		Alert dialogo = new Alert(AlertType.ERROR);
		dialogo.setTitle("Di�logo error");
		dialogo.setHeaderText(null);
		dialogo.setContentText("Advertimos al usuario de alg�n error");

		dialogo.showAndWait();
	}

	private void mostrarDialogoConfirmacion() {
		Alert dialogo = new Alert(AlertType.CONFIRMATION);
		dialogo.setTitle("Di�logo de confirmaci�n");
		dialogo.setHeaderText(null);
		dialogo.setContentText("�Est�s seguro?");

		Optional<ButtonType> respuesta = dialogo.showAndWait();
		if (respuesta.get() == ButtonType.OK) {
			System.out.println("Has aceptado el di�logo de confirmaci�n");
		} else {
			System.out.println("Has cancelado el di�logo de confirmaci�n");
		}
	}

	private void mostrarDialogoConfirmacionPersonalizado() {
		Alert dialogo = new Alert(AlertType.CONFIRMATION);
		dialogo.setTitle("Di�logo de confirmaci�n personalizado");
		dialogo.setHeaderText(null);
		dialogo.setContentText("Elige una de las opciones");

		ButtonType bttUno = new ButtonType("Opci�n 1");
		ButtonType bttDos = new ButtonType("Opci�n 2");
		ButtonType bttTres = new ButtonType("Opci�n 3");
		ButtonType bttCancelar = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);

		dialogo.getButtonTypes().setAll(bttUno, bttDos, bttTres, bttCancelar);

		Optional<ButtonType> respuesta = dialogo.showAndWait();
		if (respuesta.get() == bttUno)
			System.out.println("Has elegido la opci�n 1 del di�logo de confirmaci�n personalizado");
		else if (respuesta.get() == bttDos)
			System.out.println("Has elegido la opci�n 2 del di�logo de confirmaci�n personalizado");
		else if (respuesta.get() == bttTres)
			System.out.println("Has elegido la opci�n 3 del di�logo de confirmaci�n personalizado");
		else
			System.out.println("Has cancelado el di�logo de confirmaci�n personalizado");
	}

	private void mostrarDialogoEntradaTexto() {
		TextInputDialog dialogo = new TextInputDialog("Bob Esponja");
		dialogo.setTitle("Di�logo de entrada de texto");
		dialogo.setHeaderText(null);
		dialogo.setContentText("Elige tu personaje favorito:");

		Optional<String> respuesta = dialogo.showAndWait();
		if (respuesta.isPresent())
			System.out.println("Tu personaje favorito es: " + respuesta.get());
		else
			System.out.println("Has cancelado el di�logo de entrada de texto");
	}

	private void mostrarDialogoEleccion() {
		List<String> opciones = new ArrayList<>();
		opciones.add("Bob Esponja");
		opciones.add("Patricio");
		opciones.add("Calamardo");

		ChoiceDialog<String> dialogo = new ChoiceDialog<>("Bob Esponja", opciones);
		dialogo.setTitle("Di�logo de elecci�n");
		dialogo.setHeaderText(null);
		dialogo.setContentText("Elige tu personaje favorito:");

		Optional<String> respuesta = dialogo.showAndWait();
		if (respuesta.isPresent())
			System.out.println("Tu personaje favorito es: " + respuesta.get());
		else
			System.out.println("Has cancelado el di�logo de elecci�n");
	}

	private void mostrarDialogoPersonalizado() {
		Dialog<Personaje> dialogo = new Dialog<>();
		dialogo.setTitle("Nuevo personaje");
		dialogo.setHeaderText("Introduce los datos del nuevo personaje");

		ButtonType bttAnadir = new ButtonType("A�adir", ButtonData.OK_DONE);
		dialogo.getDialogPane().getButtonTypes().addAll(bttAnadir, ButtonType.CANCEL);

		GridPane gpDatosPersonaje = new GridPane();
		gpDatosPersonaje.setHgap(10);
		gpDatosPersonaje.setVgap(10);
		gpDatosPersonaje.setPadding(new Insets(20, 150, 10, 10));

		TextField tfNombre = new TextField();
		tfPoder = new TextField("0");
		tfPoder.textProperty().addListener((observable, oldValue, newValue) -> compruebaNumero(oldValue));
		CheckBox cbSuperpoder = new CheckBox();
		ChoiceBox<Estrategia> cbEstrategia = new ChoiceBox<Estrategia>(
				FXCollections.observableArrayList(Estrategia.values()));
		cbEstrategia.getSelectionModel().select(0);

		gpDatosPersonaje.add(new Label("Nombre:"), 0, 0);
		gpDatosPersonaje.add(tfNombre, 1, 0);
		gpDatosPersonaje.add(new Label("Poder:"), 0, 1);
		gpDatosPersonaje.add(tfPoder, 1, 1);
		gpDatosPersonaje.add(new Label("�Tiene superpoder?:"), 0, 2);
		gpDatosPersonaje.add(cbSuperpoder, 1, 2);
		gpDatosPersonaje.add(new Label("Estrategia:"), 0, 3);
		gpDatosPersonaje.add(cbEstrategia, 1, 3);

		Node btAnadir = dialogo.getDialogPane().lookupButton(bttAnadir);
		btAnadir.setDisable(true);

		tfNombre.textProperty().addListener((ov, oldValue, newValue) -> {
			btAnadir.setDisable(newValue.trim().isEmpty());
		});

		dialogo.getDialogPane().setContent(gpDatosPersonaje);

		Platform.runLater(() -> tfNombre.requestFocus());

		dialogo.setResultConverter(botonPulsado -> {
			if (botonPulsado == bttAnadir) {
				String poder = tfPoder.getText();
				poder = (poder.equals("")) ? "0" : poder;
				Personaje personaje = new Personaje(tfNombre.getText(), Integer.valueOf(poder),
						cbSuperpoder.isSelected(), cbEstrategia.getValue());
				return personaje;
			}
			return null;
		});

		Optional<Personaje> respuesta = dialogo.showAndWait();

		if (respuesta.isPresent()) {
			Personaje personaje = respuesta.get();
			System.out.println("Nombre=" + personaje.getNombre() + ", Poder=" + personaje.getPoder() + ", Superpoder="
					+ personaje.isSuperpoder() + ", Estrategia=" + personaje.getEstrategia());
		} else {
			System.out.println("Has cancelado el di�logo personalizado");
		}
	}

	private void compruebaNumero(String viejoPoder) {
		String texto = tfPoder.getText();
		if (texto.matches("[0-9]*(\\.[0-9]*)?")) {
			if (!texto.equals("")) {
				int poder = Integer.valueOf(texto).intValue();
				if (poder < 0 || poder > 100) {
					tfPoder.setText(viejoPoder);
					Toolkit.getDefaultToolkit().beep();
				}
			}
		} else {
			tfPoder.setText(viejoPoder);
			Toolkit.getDefaultToolkit().beep();
		}
	}

	private void mostrarDialogo() {
		RadioButton rbSeleccionado = (RadioButton) grupo.getSelectedToggle();
		if (rbSeleccionado == rbInformacion)
			mostrarDialogoInformacion();
		else if (rbSeleccionado == rbInformacionSinCabecera)
			mostrarDialogoInformacionSinCabecera();
		else if (rbSeleccionado == rbAdvertencia)
			mostrarDialogoAdvertencia();
		else if (rbSeleccionado == rbError)
			mostrarDialogError();
		else if (rbSeleccionado == rbConfirmacion)
			mostrarDialogoConfirmacion();
		else if (rbSeleccionado == rbConfirmacionPersonalizado)
			mostrarDialogoConfirmacionPersonalizado();
		else if (rbSeleccionado == rbEntradaTexto)
			mostrarDialogoEntradaTexto();
		else if (rbSeleccionado == rbEleccion)
			mostrarDialogoEleccion();
		else if (rbSeleccionado == rbPersonalizado)
			mostrarDialogoPersonalizado();
	}

	private void mostrarDialogoSalir(Stage escenario, WindowEvent e) {
		Alert dialogo = new Alert(AlertType.CONFIRMATION);
		dialogo.setTitle("Salir de la aplicaci�n");
		dialogo.setHeaderText(null);
		dialogo.setContentText("�Est�s seguro de que deseas salir?");

		Optional<ButtonType> respuesta = dialogo.showAndWait();
		if (respuesta.get() == ButtonType.OK) {
			escenario.close();
		} else {
			e.consume();
		}
	}

	@Override
	public void start(Stage escenarioPrincipal) {
		try {
			VBox raiz = new VBox(20);
			raiz.setPadding(new Insets(20));
			raiz.setAlignment(Pos.CENTER);

			VBox vbOpciones = new VBox(10);
			vbOpciones.setPadding(new Insets(10));

			lbElige = new Label("Elige el tipo de di�logo a mostrar:");
			lbElige.setFont(Font.font(20));
			rbInformacion = new RadioButton("Informaci�n");
			rbInformacion.setSelected(true);
			rbInformacionSinCabecera = new RadioButton("Informaci�n sin cabecera");
			rbAdvertencia = new RadioButton("Advertencia");
			rbError = new RadioButton("Error");
			rbConfirmacion = new RadioButton("Confirmaci�n");
			rbConfirmacionPersonalizado = new RadioButton("Confirmaci�n personalizado");
			rbEntradaTexto = new RadioButton("Entrada Texto");
			rbEleccion = new RadioButton("Elecci�n");
			rbPersonalizado = new RadioButton("Personalizado");
			Insets margen = new Insets(0, 0, 0, 20);
			VBox.setMargin(rbInformacion, margen);
			VBox.setMargin(rbInformacionSinCabecera, margen);
			VBox.setMargin(rbAdvertencia, margen);
			VBox.setMargin(rbError, margen);
			VBox.setMargin(rbConfirmacion, margen);
			VBox.setMargin(rbConfirmacionPersonalizado, margen);
			VBox.setMargin(rbEntradaTexto, margen);
			VBox.setMargin(rbEleccion, margen);
			VBox.setMargin(rbPersonalizado, margen);
			grupo = new ToggleGroup();
			rbInformacion.setToggleGroup(grupo);
			rbInformacionSinCabecera.setToggleGroup(grupo);
			rbAdvertencia.setToggleGroup(grupo);
			rbError.setToggleGroup(grupo);
			rbConfirmacion.setToggleGroup(grupo);
			rbConfirmacionPersonalizado.setToggleGroup(grupo);
			rbEntradaTexto.setToggleGroup(grupo);
			rbEleccion.setToggleGroup(grupo);
			rbPersonalizado.setToggleGroup(grupo);
			vbOpciones.getChildren().addAll(lbElige, rbInformacion, rbInformacionSinCabecera, rbAdvertencia, rbError,
					rbConfirmacion, rbConfirmacionPersonalizado, rbEntradaTexto, rbEleccion, rbPersonalizado);

			btMostrar = new Button("Mostrar di�logo");
			btMostrar.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
			btMostrar.setOnAction(e -> mostrarDialogo());

			raiz.getChildren().addAll(vbOpciones, btMostrar);

			Scene escena = new Scene(raiz, 400, 400);
			escenarioPrincipal.setOnCloseRequest(e -> mostrarDialogoSalir(escenarioPrincipal, e));
			escenarioPrincipal.setTitle("Mostrar di�logo");
			escenarioPrincipal.setScene(escena);
			escenarioPrincipal.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import application.controlador.ControladorVentanaPrincipal;
import application.controlador.ControladorVentanaReserva;
import application.controlador.RootLayoutController;
import application.modelo.ClienteVO;
import application.modelo.HotelModelo;
import application.modelo.ReservaVO;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private HotelModelo modelo;

	private ObservableList<ClienteVO> listaclientes = FXCollections.observableArrayList();
	private ObservableList<ReservaVO> listareservas = FXCollections.observableArrayList();

	// SET GLOBAL TIME_ZONE="+3:00";

	/**
	 * Método que nos devolverá la Lista Observable de ClienteVO
	 * 
	 * @return lista observable de clientes
	 */
	public ObservableList<ClienteVO> getPersonData() {
		return listaclientes;
	}

	/**
	 * Constructor
	 */
	public MainApp() {
		modelo = new HotelModelo();
	}

	/**
	 * Método que nos devolverá la List Observable de ReservaVO
	 * 
	 * @return lista observable de reservas
	 */
	public ObservableList<ReservaVO> getBookingData() {
		return listareservas;
	}

	/**
	 * Metodo que nos devolverá el PrimaryStage
	 * 
	 * @return primaryStage
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	/**
	 * Método que iniciará la aplicación
	 */
	@Override
	public void start(Stage primaryStage) {
		if (modelo.Conexion() == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR!!!");
			alert.setHeaderText("Conexión local");
			alert.setContentText("Se ha producido un error con la conexión, será imposibe iniciar la aplicación");
			alert.showAndWait();
			System.exit(0);
		} else {
			this.primaryStage = primaryStage;
			this.primaryStage.setTitle("Gestion Hotel");
			
			initRootLayout();
			AbrirVistaPrincipal();
		}
	}

	/**
	 * Método que abre y posiciona la vista BorderPane del menú
	 */
	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			RootLayoutController controller = loader.getController();
			controller.setMainModelo(this, modelo);

			Collections.sort(modelo.CargarPersonas());

			for (int i = 0; i < modelo.CargarPersonas().size(); i++) {
				getPersonData().add(modelo.CargarPersonas().get(i));
			}
			primaryStage.show();

		} catch (IOException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR!!!");
			alert.setHeaderText("IOException");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
	}

	/**
	 * Método que abre y posiciona la VistaPrincipal en nuestro primaryStage.
	 */
	public void AbrirVistaPrincipal() {
		try {
			// Cargamos la URL del fxml
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/VentanaPrincipal.fxml"));

			AnchorPane VistaPrincipal = (AnchorPane) loader.load();

			primaryStage.setTitle("Clientes");

			rootLayout.setCenter(VistaPrincipal);
			primaryStage.setResizable(false);
			rootLayout.setCenter(VistaPrincipal);
			ControladorVentanaPrincipal controlador = loader.getController();
			controlador.setMainModelo(this, modelo);

		} catch (IOException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR!!!");
			alert.setHeaderText("IOException");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
	}

	/**
	 * Método que abre y posiciona la VistaReservas en nuestro primaryStage.
	 * 
	 * @param cliente ClienteVO del cual vamos a ver sus reservas
	 */
	public void AbrirVistaReservas(ClienteVO cliente) {
		try {
			// Cargamos la URL del fxml
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/VentanaReservas.fxml"));
			AnchorPane VistaPrincipal = (AnchorPane) loader.load();

			rootLayout.setCenter(VistaPrincipal);
			primaryStage.setTitle("Reservas");
			
			primaryStage.setResizable(false);
			ControladorVentanaReserva controlador = loader.getController();

			ArrayList<ReservaVO> listar = modelo.CargarReservasPorPersona(cliente.getDni());
			getBookingData().clear();
			Collections.sort(listar);

			for (int i = 0; i < listar.size(); i++) {
				getBookingData().add(listar.get(i));
			}
			controlador.setMainModelo(this, modelo, cliente);
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR!!!");
			alert.setHeaderText("IOException");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
	}

	/**
	 * Método que abre nuestro webview.
	 */
	public void AbrirWebView() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("vista/WebView.fxml"));

		Stage dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(getPrimaryStage());
		dialogStage.setResizable(false);

	}
	

	public static void main(String[] args) {
		launch(args);
	}
}
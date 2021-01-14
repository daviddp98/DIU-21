package application;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Main extends Application {

	DoubleProperty contGeneral = new SimpleDoubleProperty(0);

	@Override
	public void start(Stage escenarioPrincipal) {
		try {
			FXMLLoader raiz = new FXMLLoader();
			raiz.setLocation(Main.class.getResource("Vista.fxml"));
			Pane pane = (Pane) raiz.load();

			Controlador controlador = raiz.getController();

			controlador.setContLocal(contGeneral);
			controlador.progreso();

			Scene escena = new Scene(pane);
			escenarioPrincipal.setTitle("Actividad %");
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
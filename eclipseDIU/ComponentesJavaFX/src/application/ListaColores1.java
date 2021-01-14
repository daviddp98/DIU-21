package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ListaColores1 extends Application {

	private ListView<String> lvColores;
	private ObservableList<String> colores = FXCollections.observableArrayList("Red", "Maroon", "Pink", "PaleVioletRed",
			"Moccasin", "Orange", "Wheat", "Peru", "SaddleBrown", "LightYellow", "Gold", "LawnGreen", "Green",
			"Aquamarine", "Teal", "CornflowerBlue", "MidnightBlue", "Violet", "DarkMagenta", "Indigo");
	private Label lbElige;
	private Pane panel;
	private final String estiloPanel = "-fx-border-color: gray; -fx-border-radius: 5; -fx-background-radius: 5; ";

	private void muestraColor(String color) {
		panel.setStyle(estiloPanel + "-fx-background-color: " + color + ";");
	}

	@Override
	public void start(Stage escenarioPrincipal) {
		try {
			HBox raiz = new HBox(20);
			raiz.setPadding(new Insets(30));
			raiz.setAlignment(Pos.BOTTOM_CENTER);

			VBox hbOpciones = new VBox(20);
			raiz.setAlignment(Pos.CENTER);

			lbElige = new Label("Elige el color");
			lbElige.setFont(Font.font(20));
			lvColores = new ListView<String>(colores);
			lvColores.getSelectionModel().select("chocolate");
			lvColores.getSelectionModel().selectedItemProperty().addListener((ov, viejo, nuevo) -> muestraColor(nuevo));
			hbOpciones.getChildren().addAll(lbElige, lvColores);

			panel = new Pane();
			panel.setMinSize(140, 140);
			panel.setMaxSize(140, 140);
			panel.setStyle(estiloPanel + "-fx-background-color: chocolate;");

			raiz.getChildren().addAll(hbOpciones, panel);

			Scene escena = new Scene(raiz, 430, 200);
			escenarioPrincipal.setTitle("Elige un color");
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
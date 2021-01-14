package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ListaColores3 extends Application {

	class CeldaColor extends ListCell<String> {
		@Override
		public void updateItem(String color, boolean vacio) {
			super.updateItem(color, vacio);
			Pane colorMostrado = new Pane();
			colorMostrado.setMinSize(70, 20);
			colorMostrado.setMaxSize(70, 20);
			colorMostrado.setStyle(estiloPanel);
			if (color != null) {
				colorMostrado.setStyle(estiloPanel + "-fx-background-color: " + color + ";");
				setGraphic(colorMostrado);
			} else {
				setGraphic(null);
			}
		}
	}

	private ListView<String> lvColores;
	private ObservableList<String> colores = FXCollections.observableArrayList("Red", "Maroon", "Pink", "PaleVioletRed",
			"Moccasin", "Orange", "Wheat", "Peru", "SaddleBrown", "LightYellow", "Gold", "LawnGreen", "Green",
			"Aquamarine", "Teal", "CornflowerBlue", "MidnightBlue", "Violet", "DarkMagenta", "Indigo");
	private Label lbElige;
	private Pane panel;
	private Color nuevoColor = Color.WHITE;
	private Button btAnadirColor, btBorrarColor;

	private final String estiloPanel = "-fx-border-color: gray; -fx-border-radius: 5; -fx-background-radius: 5; ";

	private void muestraColor(String color) {
		if (color != null) {
			panel.setStyle(estiloPanel + "-fx-background-color: " + color + ";");
			lvColores.getSelectionModel().select(color);
		}
	}

	private void anadirColor() {
		String color = nuevoColor.toString().replace("0x", "#");
		if (color != null && !colores.contains(color)) {
			colores.add(0, color);
			muestraColor(color);
		}
	}

	private void borrarColor() {
		String color = lvColores.getSelectionModel().getSelectedItem();
		colores.remove(color);
		if (colores.size() == 0)
			panel.setStyle(estiloPanel);
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
			lvColores.setPrefWidth(80);
			lvColores.getSelectionModel().select("Red");
			lvColores.getSelectionModel().selectedItemProperty().addListener((ov, viejo, nuevo) -> muestraColor(nuevo));
			lvColores.setCellFactory((ListView<String> l) -> new CeldaColor());

			HBox hbAnadir = new HBox(10);
			ColorPicker cp = new ColorPicker();
			cp.setMinWidth(230);
			cp.setOnAction(e -> {
				nuevoColor = cp.getValue();
			});
			btAnadirColor = new Button("Añadir");
			btAnadirColor.setOnAction(e -> anadirColor());
			hbAnadir.getChildren().addAll(cp, btAnadirColor);

			VBox vbAcciones = new VBox(10);
			btBorrarColor = new Button("Borrar");
			btBorrarColor.setMinWidth(300);
			btBorrarColor.setOnAction(e -> borrarColor());
			vbAcciones.getChildren().addAll(hbAnadir, btBorrarColor);

			hbOpciones.getChildren().addAll(lbElige, lvColores, vbAcciones);

			panel = new Pane();
			panel.setMinSize(300, 300);
			panel.setMaxSize(300, 300);
			panel.setStyle(estiloPanel + "-fx-background-color: Red;");

			raiz.getChildren().addAll(hbOpciones, panel);

			Scene escena = new Scene(raiz, 650, 350);
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
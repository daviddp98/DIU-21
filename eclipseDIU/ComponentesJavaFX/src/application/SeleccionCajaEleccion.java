package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SeleccionCajaEleccion extends Application {

	private ChoiceBox<String> cbOpciones;
	private Label lbElige;
	private ImageView ivIcono;

	private Image imgCerveza = new Image(getClass().getResourceAsStream("../imagenes/iconoCerveza.png"), 100, 100, true,
			true);
	private Image imgCaca = new Image(getClass().getResourceAsStream("../imagenes/iconoCaca.png"), 100, 100, true,
			true);
	private Image imgApagar = new Image(getClass().getResourceAsStream("../imagenes/iconoApagar.png"), 100, 100, true,
			true);

	private void muestraIcono() {
		String seleccion = cbOpciones.valueProperty().getValue();
		if (seleccion.equals("Cerveza"))
			ivIcono.setImage(imgCerveza);
		else if (seleccion.equals("Caca"))
			ivIcono.setImage(imgCaca);
		else if (seleccion.equals("Apagar"))
			ivIcono.setImage(imgApagar);
	}

	@Override
	public void start(Stage escenarioPrincipal) {
		try {
			VBox raiz = new VBox(20);
			raiz.setPadding(new Insets(20));
			raiz.setAlignment(Pos.CENTER);

			HBox hbOpciones = new HBox(10);
			raiz.setAlignment(Pos.TOP_CENTER);
			hbOpciones.setPadding(new Insets(10));

			lbElige = new Label("Elige el icono a mostrar:");
			lbElige.setFont(Font.font(20));
			cbOpciones = new ChoiceBox<>(FXCollections.observableArrayList("Cerveza", "Caca", "Apagar"));
			cbOpciones.getSelectionModel().select("Caca");
			cbOpciones.valueProperty().addListener((observable, oldValue, newValue) -> muestraIcono());
			hbOpciones.getChildren().addAll(lbElige, cbOpciones);

			ivIcono = new ImageView();
			ivIcono.setImage(imgCaca);

			raiz.getChildren().addAll(hbOpciones, ivIcono);

			Scene escena = new Scene(raiz, 430, 220);
			escenarioPrincipal.setTitle("Elige un icono");
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
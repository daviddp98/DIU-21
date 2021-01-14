package application;

import java.awt.Toolkit;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CampoTextoLongitudMaxima1 extends Application {

	private Label lbTexto, lbInfo;
	private TextField tfTexto;
	private final int MAX_CARACTERES = 10;

	private void controlaTamanoTexto(KeyEvent e) {
		String texto = tfTexto.getText();
		int longitud = texto.length();
		if (longitud < MAX_CARACTERES) {
			longitud = (Character.isISOControl(e.getCharacter().charAt(0)) ? longitud : longitud + 1);
			lbInfo.setText("Longitud: " + longitud + " caracteres");
		} else {
			e.consume();
			Toolkit.getDefaultToolkit().beep();
		}
	}

	@Override
	public void start(Stage escenarioPrincipal) {
		try {
			VBox raiz = new VBox(20);
			raiz.setPadding(new Insets(10));
			raiz.setAlignment(Pos.CENTER);

			HBox hbTexto = new HBox(30);
			hbTexto.setPadding(new Insets(10));
			hbTexto.setAlignment(Pos.CENTER);

			lbTexto = new Label("Introduce texto \n(máximo " + MAX_CARACTERES + " caracteres)");
			lbTexto.setWrapText(true);
			lbTexto.setFont(Font.font("Arial", 14));
			tfTexto = new TextField();
			tfTexto.setOnKeyTyped(e -> controlaTamanoTexto(e));
			hbTexto.getChildren().addAll(lbTexto, tfTexto);

			lbInfo = new Label("Longitud: 0 caracteres");
			lbInfo.setFont(Font.font("Arial", 24));

			raiz.getChildren().addAll(hbTexto, lbInfo);

			Scene escena = new Scene(raiz, 450, 150);
			escenarioPrincipal.setTitle("Texto con tamaño máximo");
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
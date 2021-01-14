package application;

import java.awt.Toolkit;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CampoTextoNumerico2 extends Application {

	private Label lbTexto, lbInfo;
	private TextField tfNumerico;

	private void compruebaNumero(String oldValue) {
		String texto = tfNumerico.getText();
		if (texto.matches("[0-9]*(\\.[0-9]*)?")) {
			lbInfo.setText("Longitud: " + texto.length() + " caracteres");
		} else {
			tfNumerico.setText(oldValue);
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

			lbTexto = new Label("Introduce un número\n(entero o decimal)");
			lbTexto.setWrapText(true);
			lbTexto.setFont(Font.font("Arial", 14));
			tfNumerico = new TextField();
			tfNumerico.textProperty().addListener((observable, oldValue, newValue) -> compruebaNumero(oldValue));
			hbTexto.getChildren().addAll(lbTexto, tfNumerico);

			lbInfo = new Label("Longitud: 0 caracteres");
			lbInfo.setFont(Font.font("Arial", 24));

			raiz.getChildren().addAll(hbTexto, lbInfo);

			Scene escena = new Scene(raiz, 450, 150);
			escenarioPrincipal.setTitle("Campo numérico");
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
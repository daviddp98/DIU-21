package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PosicionRaton extends Application {

	private Pane panel;
	private Label lbPosX, lbPosY;

	private void muestraValor(MouseEvent e) {
		double valorX = e.getX();
		double valorY = e.getY();
		lbPosX.setText("Posición X: " + formateNumero(valorX));
		lbPosY.setText("Posición Y: " + formateNumero(valorY));
	}

	private void limpiaValor() {
		lbPosX.setText("Posición X: ");
		lbPosY.setText("Posicion Y: ");
	}

	private String formateNumero(Number valor) {
		String valorTexto = valor.toString();
		String parteEntera = valorTexto.split("\\.")[0];
		String parteDecimal = valorTexto.split("\\.")[1];
		if (parteDecimal != null) {
			if (parteDecimal.length() > 2)
				parteDecimal = parteDecimal.substring(0, 2);
			else
				parteDecimal = parteDecimal.substring(0, 1);
			valorTexto = parteEntera + "." + parteDecimal;
		} else {
			valorTexto = parteEntera;
		}
		return valorTexto;
	}

	@Override
	public void start(Stage escenarioPrincipal) {
		try {
			VBox raiz = new VBox(30);
			raiz.setPadding(new Insets(20));
			raiz.setAlignment(Pos.CENTER);

			panel = new Pane();
			panel.setMinSize(150, 150);
			panel.setMaxSize(150, 150);
			panel.setStyle("-fx-border-color: blue; " + "-fx-border-radius: 5; " + "-fx-background-color: #cce6ff;"
					+ "-fx-background-radius: 5");
			panel.setOnMouseMoved(e -> muestraValor(e));
			panel.setOnMouseExited(e -> limpiaValor());

			HBox hbValores = new HBox(10);
			hbValores.setAlignment(Pos.CENTER);
			lbPosX = new Label();
			lbPosX.setText("Posición X: ");
			lbPosY = new Label();
			lbPosY.setText("Posición Y:");
			hbValores.getChildren().addAll(lbPosX, lbPosY);

			raiz.getChildren().addAll(panel, hbValores);

			Scene escena = new Scene(raiz, 350, 250);
			escenarioPrincipal.setTitle("Posición ratón");
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
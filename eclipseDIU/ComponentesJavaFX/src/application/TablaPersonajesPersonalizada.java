package application;

import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class TablaPersonajesPersonalizada extends Application {

	private class ConvierteEnteroCadena extends IntegerStringConverter {
		@Override
		public Integer fromString(String value) {
			try {
				return Integer.valueOf(value);
			} catch (Exception e) {
				return Integer.MIN_VALUE;
			}
		}
	}

	private TableView<Personaje> tvPersonajes;
	final ObservableList<Personaje> personajes = FXCollections.observableArrayList(
			new Personaje("Bob Esponja", 10, false, Estrategia.RISA),
			new Personaje("Mortadelo", 60, true, Estrategia.RISA), new Personaje("Goku", 90, true, Estrategia.ATAQUE),
			new Personaje("El Malo", 0, false, Estrategia.MALHUMOR), new Personaje("Gro", 100, false, Estrategia.RISA));

	TableColumn<Personaje, String> cNombre = new TableColumn<Personaje, String>("Nombre");
	TableColumn<Personaje, Integer> cPoder = new TableColumn<Personaje, Integer>("Poder");
	TableColumn<Personaje, Boolean> cSuperpoder = new TableColumn<Personaje, Boolean>("Super Poder");
	TableColumn<Personaje, Estrategia> cEstrategia = new TableColumn<Personaje, Estrategia>("Estrategia");

	private void modificaPoder(CellEditEvent<Personaje, Integer> t) {
		int poder;
		try {
			poder = Integer.valueOf(t.getNewValue().toString());
			poder = (poder >= 0 && poder <= 100) ? poder : Integer.valueOf(t.getOldValue());
		} catch (Exception e) {
			poder = Integer.valueOf(t.getOldValue());
		}
		int indice = t.getTablePosition().getRow();
		Personaje personaje = (Personaje) t.getTableView().getItems().get(indice);
		personaje.setPoder(poder);
		tvPersonajes.refresh();
	}

	@Override
	public void start(Stage escenarioPrincipal) {
		try {
			VBox raiz = new VBox();
			raiz.setPadding(new Insets(40));
			raiz.setSpacing(10);
			raiz.setAlignment(Pos.CENTER);

			Label lbPersonajes = new Label("Personajes");
			lbPersonajes.setFont(Font.font(20));
			tvPersonajes = new TableView<Personaje>();
			tvPersonajes.getColumns().add(cNombre);
			tvPersonajes.getColumns().add(cPoder);
			tvPersonajes.getColumns().add(cSuperpoder);
			tvPersonajes.getColumns().add(cEstrategia);
			tvPersonajes.setEditable(true);
			cNombre.setMinWidth(100);
			cNombre.setCellValueFactory(new PropertyValueFactory<Personaje, String>("nombre"));
			cNombre.setCellFactory(TextFieldTableCell.forTableColumn());
			cPoder.setMinWidth(20);
			cPoder.setCellValueFactory(new PropertyValueFactory<Personaje, Integer>("poder"));
			cPoder.setCellFactory(TextFieldTableCell.<Personaje, Integer>forTableColumn(new ConvierteEnteroCadena()));
			cPoder.setOnEditCommit(poder -> modificaPoder(poder));
			cSuperpoder.setMinWidth(40);
			cSuperpoder
					.setCellValueFactory(superPoder -> new SimpleBooleanProperty(superPoder.getValue().isSuperpoder()));
			cSuperpoder.setCellFactory(superPoder -> new CheckBoxTableCell<>());
			cEstrategia.setMinWidth(60);
			cEstrategia.setCellValueFactory(new PropertyValueFactory<Personaje, Estrategia>("estrategia"));
			cEstrategia
					.setCellFactory(estrategia -> new ChoiceBoxTableCell<Personaje, Estrategia>(Estrategia.values()));

			tvPersonajes.setItems(personajes);

			raiz.getChildren().addAll(lbPersonajes, tvPersonajes);

			Scene escena = new Scene(raiz, 455, 250);
			escenarioPrincipal.setTitle("Tabla personajes");
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
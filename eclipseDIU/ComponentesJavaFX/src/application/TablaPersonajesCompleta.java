package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class TablaPersonajesCompleta extends Application {

	class CeldaSuperpoder extends TableCell<Personaje, Boolean> {

		private CheckBox superpoderMostrado = new CheckBox();

		public CeldaSuperpoder() {
			super();
			superpoderMostrado.selectedProperty().addListener((ov, oldValue, newValue) -> {
				if (getTableRow() != null) {
					Personaje personaje = (Personaje) getTableRow().getItem();
					if (personaje != null) {
						personaje.setSuperpoder(newValue);
						filaCambiada(personaje);
					}
				}
			});
			setAlignment(Pos.CENTER);
		}

		@Override
		public void updateItem(Boolean superpoder, boolean vacio) {
			super.updateItem(superpoder, vacio);

			if (superpoder != null) {
				superpoderMostrado.setSelected(superpoder);
				setGraphic(superpoderMostrado);
			} else {
				setGraphic(null);
			}
		}

	}

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
	private ListView<Personaje> lvPersonajes;
	private Button btBorrar, btAnadir;

	final ObservableList<Personaje> personajes = FXCollections.observableArrayList(
			new Personaje("Bob Esponja", 10, false, Estrategia.RISA),
			new Personaje("Mortadelo", 60, true, Estrategia.RISA), new Personaje("Goku", 90, true, Estrategia.ATAQUE),
			new Personaje("El Malo", 0, false, Estrategia.MALHUMOR), new Personaje("Gro", 100, false, Estrategia.RISA));

	TableColumn<Personaje, String> cNombre = new TableColumn<Personaje, String>("Nombre");
	TableColumn<Personaje, Integer> cPoder = new TableColumn<Personaje, Integer>("Poder");
	TableColumn<Personaje, Boolean> cSuperpoder = new TableColumn<Personaje, Boolean>("Super Poder");
	TableColumn<Personaje, Estrategia> cEstrategia = new TableColumn<Personaje, Estrategia>("Estrategia");

	private void modificaNombre(CellEditEvent<Personaje, String> t) {
		int indice = t.getTablePosition().getRow();
		Personaje personaje = (Personaje) t.getTableView().getItems().get(indice);
		personaje.setNombre(t.getNewValue());
		filaCambiada(personaje);
	}

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
		t.getTableView().getItems().set(indice, personaje);
		filaCambiada(personaje);
	}

	private void modificaEstrategia(CellEditEvent<Personaje, Estrategia> t) {
		int indice = t.getTablePosition().getRow();
		Personaje personaje = (Personaje) t.getTableView().getItems().get(indice);
		personaje.setEstrategia(t.getNewValue());
		filaCambiada(personaje);
	}

	private void filaCambiada(Personaje personaje) {
		if (personaje != null) {
			lvPersonajes.refresh();
			System.out.println("Fila cambiada: " + personaje);
		}
	}

	private void filaSeleccionada(Personaje personaje) {
		if (personaje != null) {
			lvPersonajes.getSelectionModel().select(personaje);
			System.out.println("Fila seleccionada: " + personaje);
		}
	}

	private void borrarFilaSeleccionada() {
		Personaje personaje = tvPersonajes.getSelectionModel().getSelectedItem();
		if (personaje != null) {
			personajes.remove(personaje);
			System.out.println("Fila borrada: " + personaje);
		}
	}

	private void anadirPersonajeVacio() {
		Personaje personaje = new Personaje("Cámbiame", 0, false, Estrategia.MALHUMOR);
		personajes.add(personaje);
		System.out.println("Personaje vacío añadido: " + personaje);
	}

	@Override
	public void start(Stage escenarioPrincipal) {
		try {
			VBox raiz = new VBox(10);
			raiz.setPadding(new Insets(20));
			raiz.setAlignment(Pos.CENTER);

			Label lbPersonajes = new Label("Personajes");
			lbPersonajes.setFont(Font.font("Arial", 30));

			HBox hbPersonajes = new HBox(20);
			hbPersonajes.setPadding(new Insets(10));

			tvPersonajes = new TableView<Personaje>();
			tvPersonajes.getColumns().add(cNombre);
			tvPersonajes.getColumns().add(cPoder);
			tvPersonajes.getColumns().add(cSuperpoder);
			tvPersonajes.getColumns().add(cEstrategia);
			tvPersonajes.setEditable(true);
			tvPersonajes.setMinWidth(360);
			cNombre.setMinWidth(100);
			cNombre.setCellValueFactory(new PropertyValueFactory<Personaje, String>("nombre"));
			cNombre.setCellFactory(TextFieldTableCell.forTableColumn());
			cNombre.setOnEditCommit(nombre -> modificaNombre(nombre));
			cPoder.setMinWidth(20);
			cPoder.setCellValueFactory(new PropertyValueFactory<Personaje, Integer>("poder"));
			cPoder.setCellFactory(TextFieldTableCell.<Personaje, Integer>forTableColumn(new ConvierteEnteroCadena()));
			cPoder.setOnEditCommit(poder -> modificaPoder(poder));
			cSuperpoder.setMinWidth(40);
			cSuperpoder.setCellValueFactory(new PropertyValueFactory<Personaje, Boolean>("superpoder"));
			cSuperpoder.setCellFactory(superPoder -> new CeldaSuperpoder());
			cEstrategia.setMinWidth(60);
			cEstrategia.setCellValueFactory(new PropertyValueFactory<Personaje, Estrategia>("estrategia"));
			cEstrategia
					.setCellFactory(estrategia -> new ChoiceBoxTableCell<Personaje, Estrategia>(Estrategia.values()));
			cEstrategia.setOnEditCommit(estrategia -> modificaEstrategia(estrategia));
			tvPersonajes.setItems(personajes);
			tvPersonajes.getSelectionModel().selectedItemProperty()
					.addListener((ov, oldValue, newValue) -> filaSeleccionada(ov.getValue()));

			lvPersonajes = new ListView<Personaje>(personajes);
			lvPersonajes.setMinWidth(500);

			hbPersonajes.getChildren().addAll(tvPersonajes, lvPersonajes);

			btBorrar = new Button("Borrar fila seleccionada");
			btBorrar.setStyle("-fx-font: 20 arial; -fx-base: #dc143c;");
			btBorrar.setOnAction(e -> borrarFilaSeleccionada());
			btAnadir = new Button("Añadir personaje");
			btAnadir.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
			btAnadir.setOnAction(e -> anadirPersonajeVacio());

			raiz.getChildren().addAll(lbPersonajes, hbPersonajes, btBorrar, btAnadir);

			Scene escena = new Scene(raiz, 950, 450);
			escenarioPrincipal.setTitle("Tabla personajes completa");
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
package address.view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;

import address.MainApp;
import address.model.AgendaException;
import address.model.AgendaModelo;
import address.model.AgendaVO;
import address.util.DateUtil;

public class PersonOverviewController {
	@FXML
	private TableView<AgendaVO> personTable;
	@FXML
	private TableColumn<AgendaVO, String> firstNameColumn;
	@FXML
	private TableColumn<AgendaVO, String> lastNameColumn;

	@FXML
	private Label firstNameLabel;
	@FXML
	private Label lastNameLabel;
	@FXML
	private Label streetLabel;
	@FXML
	private Label postalCodeLabel;
	@FXML
	private Label cityLabel;
	@FXML
	private Label birthdayLabel;

	// Reference to the main application.
	private MainApp mainApp;
	private AgendaModelo agendaM;

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public PersonOverviewController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

		// Clear person details.
		showPersonDetails(null);

		// Listen for selection changes and show the person details when changed.
		personTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp, AgendaModelo agendaM) {
		this.mainApp = mainApp;
		this.agendaM = agendaM;

		try {
			mainApp.setPersonData((ObservableList<AgendaVO>) agendaM.cargarListaPersona());
			personTable.setItems(mainApp.getPersonData());
			mainApp.setBdConnetionStatus(true);
		} catch (AgendaException e) {
			mainApp.setBdConnetionStatus(false);
			Alert dialogo = new Alert(AlertType.ERROR);
			dialogo.setTitle("No Selection");
			dialogo.setHeaderText("No Person Selected");
			dialogo.setContentText("Please select a person in the table.");

			dialogo.showAndWait();
		}
	}

	/**
	 * Fills all text fields to show details about the person. If the specified
	 * person is null, all text fields are cleared.
	 * 
	 * @param person the person or null
	 */
	private void showPersonDetails(AgendaVO person) {
		if (person != null) {
			// Fill the labels with info from the person object.
			firstNameLabel.setText(person.getFirstName());
			lastNameLabel.setText(person.getLastName());
			streetLabel.setText(person.getStreet());
			postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
			cityLabel.setText(person.getCity());
			birthdayLabel.setText(DateUtil.format(person.getBirthday()));
		} else {
			// Person is null, remove all the text.
			firstNameLabel.setText("");
			lastNameLabel.setText("");
			streetLabel.setText("");
			postalCodeLabel.setText("");
			cityLabel.setText("");
			birthdayLabel.setText("");
		}
	}

	/**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	private void handleDeletePerson() {
		int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			List<AgendaVO> aux = new ArrayList<AgendaVO>();
			aux.add(personTable.getSelectionModel().getSelectedItem());

			try {
				agendaM.eliminarPersona(aux);
				personTable.getItems().remove(selectedIndex);
			} catch (AgendaException e) {
				Alert dialogo = new Alert(AlertType.ERROR);
				dialogo.setTitle("No Selection");
				dialogo.setHeaderText("No Person Selected");
				dialogo.setContentText("Please select a person in the table.");

				dialogo.showAndWait();
			}
		} else {
			// Nothing selected.
			// Dialogs.create()
			// .title("No Selection")
			// .masthead("No Person Selected")
			// .message("Please select a person in the table.")
			// .showWarning();

			Alert dialogo = new Alert(AlertType.ERROR);
			dialogo.setTitle("No Selection");
			dialogo.setHeaderText("No Person Selected");
			dialogo.setContentText("Please select a person in the table.");

			dialogo.showAndWait();
		}
	}

	/**
	 * Called when the user clicks the new button. Opens a dialog to edit details
	 * for a new person.
	 */
	@FXML
	private void handleNewPerson() {
		AgendaVO tempPerson = new AgendaVO();
		boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
		if (okClicked) {
			List<AgendaVO> aux = new ArrayList<AgendaVO>();
			aux.add(tempPerson);

			try {
				agendaM.guardarPersona(aux);
				personTable.getItems().add(aux.get(0));
			} catch (AgendaException e) {
				Alert dialogo = new Alert(AlertType.ERROR);
				dialogo.setTitle("No Selection");
				dialogo.setHeaderText("No Person Selected");
				dialogo.setContentText("Please select a person in the table.");

				dialogo.showAndWait();
			}
		}
	}

	/**
	 * Called when the user clicks the edit button. Opens a dialog to edit details
	 * for the selected person.
	 */
	@FXML
	private void handleEditPerson() {
		AgendaVO selectedPerson = personTable.getSelectionModel().getSelectedItem();
		if (selectedPerson != null) {
			boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
			if (okClicked) {
				List<AgendaVO> aux = new ArrayList<AgendaVO>();
				aux.add(selectedPerson);

				try {
					agendaM.actualizarPersona(aux);
					showPersonDetails(selectedPerson);
				} catch (AgendaException e) {
					Alert dialogo = new Alert(AlertType.ERROR);
					dialogo.setTitle("No Selection");
					dialogo.setHeaderText("No Person Selected");
					dialogo.setContentText("Please select a person in the table.");

					dialogo.showAndWait();
				}
			}

		} else {
			// Nothing selected.
			// Dialogs.create().title("No Selection").masthead("No Person Selected")
			// .message("Please select a person in the table.").showWarning();

			Alert dialogo = new Alert(AlertType.ERROR);
			dialogo.setTitle("No Selection");
			dialogo.setHeaderText("No Person Selected");
			dialogo.setContentText("Please select a person in the table.");

			dialogo.showAndWait();
		}
	}
}
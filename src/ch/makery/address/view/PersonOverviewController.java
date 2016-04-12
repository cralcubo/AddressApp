package ch.makery.address.view;

import ch.makery.address.MainApp;
import ch.makery.address.model.Person;
import ch.makery.address.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PersonOverviewController {
	@FXML
	private TableView<Person> personTable;
	@FXML
	private TableColumn<Person, String> firstNameColumn;
	@FXML
	private TableColumn<Person, String> lastNameColumn;
	
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
    
    private MainApp mainApp;
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().getFirstName());
    	lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().getLastName());
    	
    	// Clear person details
    	showPersonDetails(null);
    	
    	// Listen for selection changes
    	personTable.getSelectionModel().selectedItemProperty().addListener((observable, oldVal, newVal) -> showPersonDetails(newVal));
    }
    
    @FXML
    private void handleDeletePerson() {
    	int selIndex = personTable.getSelectionModel().getSelectedIndex();
    	if(selIndex > 0) {
    		personTable.getItems().remove(selIndex);
    	} else {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.initOwner(mainApp.getPrimaryStage());
    		alert.setTitle("No Selection");
    		alert.setHeaderText("No Person Selected");
    		alert.setContentText("Please Select a Person to delete it.");
    		alert.showAndWait();
    	}
    }
    
    @FXML
    private void handleNewPerson() {
    	Person p = new Person();
    	boolean okClicked = mainApp.showPersonEditDialog(p);
    	if(okClicked) {
    		mainApp.getPersonData().add(p);
    	}
    }
    
    @FXML
    private void handleEditPerson() {
    	Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
    	if(selectedPerson != null) {
    		boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
    		if(okClicked) {
    			showPersonDetails(selectedPerson);
    		}
    	} else {
    		// Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
    	}
    }
	
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
    	this.mainApp = mainApp;
    	
    	personTable.setItems(mainApp.getPersonData());
    }
    
    private void showPersonDetails(Person person) {
    	if(person != null) {
    		firstNameLabel.setText(person.getFirstName().getValue());
    		lastNameLabel.setText(person.getLastName().getValue());
    		streetLabel.setText(person.getStreet().getValue());
    		postalCodeLabel.setText(String.valueOf(person.getPostalCode().getValue()));
    		cityLabel.setText(person.getCity().getValue());
    		birthdayLabel.setText(DateUtil.format(person.getBirthday().get()));
    	} else {
    		firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
    	}
    }

}

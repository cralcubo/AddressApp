package ch.makery.address.view;

import ch.makery.address.model.Person;
import ch.makery.address.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PersonEditDialogController {
	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField streetField;
	@FXML
	private TextField cityField;
	@FXML
	private TextField postalCodeField;
	@FXML
	private TextField birthdayField;
	
	private Stage dialogStage;
	private Person person;
	private boolean okClicked = false;
	
	/**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
	@FXML
	private void initialize() {
	}
	
	@FXML
	private void handleOk() {
		if(isInputValid()) {
			person.setFirstName(firstNameField.getText());
            person.setLastName(lastNameField.getText());
            person.setStreet(streetField.getText());
            person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
            person.setCity(cityField.getText());
            person.setBirthday(DateUtil.parse(birthdayField.getText()));
            okClicked = true;
            dialogStage.close();
		}
	}
	
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
	
	
	private boolean isInputValid() {
		String errorMessage = "";
		if(!validText(firstNameField.getText())) {
			errorMessage += "No valid first name!\n"; 
		}
		if(!validText(lastNameField.getText())) {
			errorMessage += "No valid last name!\n"; 
		}
		if(!validText(streetField.getText())) {
			errorMessage += "No valid street name!\n"; 
		}
		if(!validText(postalCodeField.getText()) || !validNumber(postalCodeField.getText())) {
			errorMessage += "No valid postal code";
		}
		if(!validText(cityField.getText())) {
			errorMessage += "No valid city name!\n"; 
		}
		if(!validText(birthdayField.getText()) || !DateUtil.validDate(birthdayField.getText())) {
			errorMessage += "No valid birthday!\n";
		}
		
		if(errorMessage.isEmpty()) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);
			
			alert.showAndWait();
			return false;
		}
	}

	private boolean validNumber(String text) {
		try {
			Integer.parseInt(text);
			return true;
		}catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean validText(String text) {
		return text != null && !text.isEmpty();
	}

	public void setDialogStage(Stage stage) {
		this.dialogStage = stage;
	}
	
	public void setPerson(Person person) {
		this.person = person;
		
		firstNameField.setText(person.getFirstName().get());
		lastNameField.setText(person.getLastName().get());
		streetField.setText(person.getStreet().get());
		postalCodeField.setText(String.valueOf(person.getPostalCode().get()));
		cityField.setText(person.getCity().get());
		birthdayField.setText(DateUtil.format(person.getBirthday().get()));
		birthdayField.setPromptText("dd.mm.yyyy");
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}
	
	
	

}

package ch.makery.address.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
	
	private final StringProperty firstName;
	private final StringProperty lastName;
	private final StringProperty street;
	private final StringProperty city;
	private final IntegerProperty postalCode;
	private final ObjectProperty<LocalDate> birthday;
	
	public Person(String firstName, String lastName) {
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.street = new SimpleStringProperty("aStreet");
		this.city = new SimpleStringProperty("aCity");
		this.postalCode = new SimpleIntegerProperty(1234);
		this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(1981, 02, 17));
	}
	
	public Person() {
		this(null, null);
	}

	public StringProperty getFirstName() {
		return firstName;
	}

	public StringProperty getLastName() {
		return lastName;
	}

	public StringProperty getStreet() {
		return street;
	}

	public StringProperty getCity() {
		return city;
	}

	public IntegerProperty getPostalCode() {
		return postalCode;
	}

	public ObjectProperty<LocalDate> getBirthday() {
		return birthday;
	}

}

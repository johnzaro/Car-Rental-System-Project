package carRentalSystem;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Customer
{
	Customer(String irsNumber, String socialSecurityNumber, String firstName, String lastName,
			 String driverLicense, String firstRegistration, String city, String postalCode, String street, String streetNumber)
	{
		setIrsNumber(irsNumber);
		setSocialSecurityNumber(socialSecurityNumber);
		setFirstName(firstName);
		setLastName(lastName);
		setDriverLicense(driverLicense);
		setFirstRegistration(firstRegistration);
		setCity(city);
		setPostalCode(postalCode);
		setStreet(street);
		setStreetNumber(streetNumber);
	}
	
	Customer(int customer_id, String irsNumber, String socialSecurityNumber, String firstName, String lastName,
			 String driverLicense, String firstRegistration, String city, String postalCode, String street, String streetNumber)
	{
		setCustomer_id(customer_id);
		setIrsNumber(irsNumber);
		setSocialSecurityNumber(socialSecurityNumber);
		setFirstName(firstName);
		setLastName(lastName);
		setDriverLicense(driverLicense);
		setFirstRegistration(firstRegistration);
		setCity(city);
		setPostalCode(postalCode);
		setStreet(street);
		setStreetNumber(streetNumber);
	}
	
	private int customer_id;
	public int getCustomer_id()
	{
		return customer_id;
	}
	public void setCustomer_id(int customer_id)
	{
		this.customer_id = customer_id;
	}
	
	private StringProperty irsNumber;
	public void setIrsNumber(String value) { irsNumberProperty().set(value); }
	public String getIrsNumber() { return irsNumberProperty().get(); }
	public StringProperty irsNumberProperty() {
		if (irsNumber == null) irsNumber = new SimpleStringProperty(this, "irsNumber");
		return irsNumber;
	}
	
	private StringProperty socialSecurityNumber;
	public void setSocialSecurityNumber(String value) { socialSecurityNumberProperty().set(value); }
	public String getSocialSecurityNumber() { return socialSecurityNumberProperty().get(); }
	public StringProperty socialSecurityNumberProperty() {
		if (socialSecurityNumber == null) socialSecurityNumber = new SimpleStringProperty(this, "socialSecurityNumber");
		return socialSecurityNumber;
	}
	
	private StringProperty firstName;
	public void setFirstName(String value) { firstNameProperty().set(value); }
	public String getFirstName() { return firstNameProperty().get(); }
	public StringProperty firstNameProperty() {
		if (firstName == null) firstName = new SimpleStringProperty(this, "firstName");
		return firstName;
	}
	
	private StringProperty lastName;
	public void setLastName(String value) { lastNameProperty().set(value); }
	public String getLastName() { return lastNameProperty().get(); }
	public StringProperty lastNameProperty() {
		if (lastName == null) lastName = new SimpleStringProperty(this, "lastName");
		return lastName;
	}
	
	private StringProperty driverLicense;
	public void setDriverLicense(String value) { driverLicenseProperty().set(value); }
	public String getDriverLicense() { return driverLicenseProperty().get(); }
	public StringProperty driverLicenseProperty() {
		if (driverLicense == null) driverLicense = new SimpleStringProperty(this, "driverLicense");
		return driverLicense;
	}
	
	private StringProperty firstRegistration;
	public void setFirstRegistration(String value) { firstRegistrationProperty().set(value); }
	public String getFirstRegistration() { return firstRegistrationProperty().get(); }
	public StringProperty firstRegistrationProperty() {
		if (firstRegistration == null) firstRegistration = new SimpleStringProperty(this, "firstRegistration");
		return firstRegistration;
	}
	
	private StringProperty city;
	public void setCity(String value) { cityProperty().set(value); }
	public String getCity() { return cityProperty().get(); }
	public StringProperty cityProperty() {
		if (city == null) city = new SimpleStringProperty(this, "city");
		return city;
	}
	
	private StringProperty postalCode;
	public void setPostalCode(String value) { postalCodeProperty().set(value); }
	public String getPostalCode() { return postalCodeProperty().get(); }
	public StringProperty postalCodeProperty() {
		if (postalCode == null) postalCode = new SimpleStringProperty(this, "postalCode");
		return postalCode;
	}
	
	private StringProperty street;
	public void setStreet(String value) { streetProperty().set(value); }
	public String getStreet() { return streetProperty().get(); }
	public StringProperty streetProperty() {
		if (street == null) street = new SimpleStringProperty(this, "street");
		return street;
	}
	
	private StringProperty streetNumber;
	public void setStreetNumber(String value) { streetNumberProperty().set(value); }
	public String getStreetNumber() { return streetNumberProperty().get(); }
	public StringProperty streetNumberProperty() {
		if (streetNumber == null) streetNumber = new SimpleStringProperty(this, "streetNumber");
		return streetNumber;
	}
}

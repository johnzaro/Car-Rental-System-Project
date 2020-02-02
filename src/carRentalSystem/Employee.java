package carRentalSystem;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Employee
{
	Employee(String firstName, String lastName, String irsNumber, String socialSecurityNumber,
			 String driverLicense, String city, String postalCode, String street, String streetNumber)
	{
		setFirstName(firstName);
		setLastName(lastName);
		setIrsNumber(irsNumber);
		setSocialSecurityNumber(socialSecurityNumber);
		setDriverLicense(driverLicense);
		setCity(city);
		setPostalCode(postalCode);
		setStreet(street);
		setStreetNumber(streetNumber);
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
	
	private StringProperty driverLicense;
	public void setDriverLicense(String value) { driverLicenseProperty().set(value); }
	public String getDriverLicense() { return driverLicenseProperty().get(); }
	public StringProperty driverLicenseProperty() {
		if (driverLicense == null) driverLicense = new SimpleStringProperty(this, "driverLicense");
		return driverLicense;
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

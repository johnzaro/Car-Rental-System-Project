package carRentalSystem;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Store
{
	Store(int storeID, String city, String postalCode, String street, String streetNumber, String[] phoneNumbers, String[] emailAddresses)
	{
		setStoreID(storeID);
		setCity(city);
		setPostalCode(postalCode);
		setStreet(street);
		setStreetNumber(streetNumber);
		setPhoneNumbers(phoneNumbers);
		setEmailAddresses(emailAddresses);
	}
	
	private int storeID;
	public int getStoreID()
	{
		return storeID;
	}
	public void setStoreID(int storeID)
	{
		this.storeID = storeID;
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
	
	private String[] phoneNumbers;
	public String[] getPhoneNumbers()
	{
		return phoneNumbers;
	}
	public void setPhoneNumbers(String[] phoneNumbers)
	{
		this.phoneNumbers = phoneNumbers;
	}
	
	private String[] emailAddresses;
	public String[] getEmailAddresses()
	{
		return emailAddresses;
	}
	public void setEmailAddresses(String[] emailAddresses)
	{
		this.emailAddresses = emailAddresses;
	}
}

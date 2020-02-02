package carRentalSystem;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EmploymentData
{
	EmploymentData(String firstName, String lastName, String irsNumber, String startDate, String finishDate, String position, String city)
	{
		setFirstName(firstName);
		setLastName(lastName);
		setIrsNumber(irsNumber);
		setStartDate(startDate);
		setFinishDate(finishDate);
		setPosition(position);
		setCity(city);
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
	private StringProperty startDate;
	public void setStartDate(String value) { startDateProperty().set(value); }
	public String getStartDate() { return startDateProperty().get(); }
	
	public StringProperty startDateProperty() {
		if (startDate == null) startDate = new SimpleStringProperty(this, "startDate");
		return startDate;
	}
	
	private StringProperty finishDate;
	public void setFinishDate(String value) { finishDateProperty().set(value); }
	public String getFinishDate() { return finishDateProperty().get(); }
	public StringProperty finishDateProperty() {
		if (finishDate == null) finishDate = new SimpleStringProperty(this, "finishDate");
		return finishDate;
	}
	
	private StringProperty position;
	public void setPosition(String value) { positionProperty().set(value); }
	public String getPosition() { return positionProperty().get(); }
	public StringProperty positionProperty() {
		if (position == null) position = new SimpleStringProperty(this, "position");
		return position;
	}
	
	private StringProperty city;
	public void setCity(String value) { cityProperty().set(value); }
	public String getCity() { return cityProperty().get(); }
	
	public StringProperty cityProperty() {
		if (city == null) city = new SimpleStringProperty(this, "city");
		return city;
	}
}

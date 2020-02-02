package carRentalSystem;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Reserve
{
	Reserve(String startDate, String finishDate, String startLocation, String finishLocation, boolean paid, int customerID, String customerName, String licensePlate, String vehicleName)
	{
		setStartDate(startDate);
		setFinishDate(finishDate);
		setStartLocation(startLocation);
		setFinishLocation(finishLocation);
		setPaid(paid);
		
		setCustomerID(customerID);
		setCustomerName(customerName);
		
		setLicensePlate(licensePlate);
		setVehicleName(vehicleName);
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
	
	private StringProperty startLocation;
	public void setStartLocation(String value) { startLocationProperty().set(value); }
	public String getStartLocation() { return startLocationProperty().get(); }
	public StringProperty startLocationProperty() {
		if (startLocation == null) startLocation = new SimpleStringProperty(this, "startLocation");
		return startLocation;
	}
	
	private StringProperty finishLocation;
	public void setFinishLocation(String value) { finishLocationProperty().set(value); }
	public String getFinishLocation() { return finishLocationProperty().get(); }
	public StringProperty finishLocationProperty() {
		if (finishLocation == null) finishLocation = new SimpleStringProperty(this, "finishLocation");
		return finishLocation;
	}
	
	private BooleanProperty paid;
	public void setPaid(boolean value) { paidProperty().set(value); }
	public boolean getPaid() { return paidProperty().get(); }
	public BooleanProperty paidProperty() {
		if (paid == null) paid = new SimpleBooleanProperty(this, "paid");
		return paid;
	}
	
	private int customerID;
	public int getCustomerID()
	{
		return customerID;
	}
	public void setCustomerID(int customerID)
	{
		this.customerID = customerID;
	}
	
	private StringProperty customerName;
	public void setCustomerName(String value) { customerNameProperty().set(value); }
	public String getCustomerName() { return customerNameProperty().get(); }
	public StringProperty customerNameProperty() {
		if (customerName == null) customerName = new SimpleStringProperty(this, "customerName");
		return customerName;
	}
	
	private String licensePlate;
	public String getLicensePlate()
	{
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate)
	{
		this.licensePlate = licensePlate;
	}
	
	private StringProperty vehicleName;
	public void setVehicleName(String value) { vehicleNameProperty().set(value); }
	public String getVehicleName() { return vehicleNameProperty().get(); }
	public StringProperty vehicleNameProperty() {
		if (vehicleName == null) vehicleName = new SimpleStringProperty(this, "vehicleName");
		return vehicleName;
	}
}

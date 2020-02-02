package carRentalSystem;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Rent
{
	Rent(String startDate, String finishDate, String startLocation, String finishLocation, String returnState,
		 int customerID, String customerName, String licensePlate, String vehicleName, long employeeIRSNumber,  String employeeName, String paymentDetails)
	{
		setStartDate(startDate);
		setFinishDate(finishDate);
		setStartLocation(startLocation);
		setFinishLocation(finishLocation);
		setReturnState(returnState);
		
		setCustomerID(customerID);
		setCustomerName(customerName);
		
		setLicensePlate(licensePlate);
		setVehicleName(vehicleName);
		
		setEmployeeIRSNumber(employeeIRSNumber);
		setEmployeeName(employeeName);
		
		setPaymentDetails(paymentDetails);
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
	
	private StringProperty returnState;
	public void setReturnState(String value) { returnStateProperty().set(value); }
	public String getReturnState() { return returnStateProperty().get(); }
	public StringProperty returnStateProperty() {
		if (returnState == null) returnState = new SimpleStringProperty(this, "returnState");
		return returnState;
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
	
	private long employeeIRSNumber;
	public long getEmployeeIRSNumber()
	{
		return employeeIRSNumber;
	}
	public void setEmployeeIRSNumber(long employeeIRSNumber)
	{
		this.employeeIRSNumber = employeeIRSNumber;
	}
	
	private StringProperty employeeName;
	public void setEmployeeName(String value) { employeeNameProperty().set(value); }
	public String getEmployeeName() { return employeeNameProperty().get(); }
	public StringProperty employeeNameProperty() {
		if (employeeName == null) employeeName = new SimpleStringProperty(this, "employeeName");
		return employeeName;
	}
	
	private StringProperty paymentDetails;
	public void setPaymentDetails(String value) { paymentDetailsProperty().set(value); }
	public String getpaymentDetails() { return paymentDetailsProperty().get(); }
	public StringProperty paymentDetailsProperty() {
		if (paymentDetails == null) paymentDetails = new SimpleStringProperty(this, "paymentDetails");
		return paymentDetails;
	}
}

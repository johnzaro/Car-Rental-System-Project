package carRentalSystem;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Vehicle
{
	Vehicle(String make, String model, String irsNumber, String type,
			 String year, String cylinderCapacity, String horsePower,
			 String kilometers, String damages, String malfunctions,
			 String lastService, String nextService, String insuranceExpDate,
			 String city)
	{
		setMake(make);
		setModel(model);
		setLicensePlate(irsNumber);
		setType(type);
		setYear(year);
		setCylinderCapacity(cylinderCapacity);
		setHorsePower(horsePower);
		setKilometers(kilometers);
		setDamages(damages);
		setMalfunctions(malfunctions);
		setLastService(lastService);
		setNextService(nextService);
		setInsuranceExpDate(insuranceExpDate);
		setCity(city);
	}
	
	private StringProperty make;
	public void setMake(String value) { makeProperty().set(value); }
	public String getMake() { return makeProperty().get(); }
	public StringProperty makeProperty() {
		if (make == null) make = new SimpleStringProperty(this, "make");
		return make;
	}
	
	private StringProperty model;
	public void setModel(String value) { modelProperty().set(value); }
	public String getModel() { return modelProperty().get(); }
	public StringProperty modelProperty() {
		if (model == null) model = new SimpleStringProperty(this, "model");
		return model;
	}
	
	private StringProperty licensePlate;
	public void setLicensePlate(String value) { licensePlateProperty().set(value); }
	public String getLicensePlate() { return licensePlateProperty().get(); }
	public StringProperty licensePlateProperty() {
		if (licensePlate == null) licensePlate = new SimpleStringProperty(this, "licensePlate");
		return licensePlate;
	}
	
	private StringProperty type;
	public void setType(String value) { typeProperty().set(value); }
	public String getType() { return typeProperty().get(); }
	public StringProperty typeProperty() {
		if (type == null) type = new SimpleStringProperty(this, "type");
		return type;
	}
	
	private StringProperty year;
	public void setYear(String value) { yearProperty().set(value); }
	public String getYear() { return yearProperty().get(); }
	public StringProperty yearProperty() {
		if (year == null) year = new SimpleStringProperty(this, "year");
		return year;
	}
	
	private StringProperty cylinderCapacity;
	public void setCylinderCapacity(String value) { cylinderCapacityProperty().set(value); }
	public String getCylinderCapacity() { return cylinderCapacityProperty().get(); }
	public StringProperty cylinderCapacityProperty() {
		if (cylinderCapacity == null) cylinderCapacity = new SimpleStringProperty(this, "cylinderCapacity");
		return cylinderCapacity;
	}
	
	private StringProperty horsePower;
	public void setHorsePower(String value) { horsePowerProperty().set(value); }
	public String getHorsePower() { return horsePowerProperty().get(); }
	public StringProperty horsePowerProperty() {
		if (horsePower == null) horsePower = new SimpleStringProperty(this, "horsePower");
		return horsePower;
	}
	
	private StringProperty kilometers;
	public void setKilometers(String value) { kilometersProperty().set(value); }
	public String getKilometers() { return kilometersProperty().get(); }
	public StringProperty kilometersProperty() {
		if (kilometers == null) kilometers = new SimpleStringProperty(this, "kilometers");
		return kilometers;
	}
	
	private StringProperty damages;
	public void setDamages(String damages) { damagesProperty().set(damages); }
	public String getDamages() { return damagesProperty().get(); }
	public StringProperty damagesProperty() {
		if (damages == null) damages = new SimpleStringProperty(this, "damages");
		return damages;
	}
	
	private StringProperty malfunctions;
	public void setMalfunctions(String malfunctions) { malfunctionsProperty().set(malfunctions); }
	public String getMalfunctions() { return malfunctionsProperty().get(); }
	public StringProperty malfunctionsProperty() {
		if (malfunctions == null) malfunctions = new SimpleStringProperty(this, "malfunctions");
		return malfunctions;
	}
	
	private StringProperty lastService;
	public void setLastService(String lastService) { lastServiceProperty().set(lastService); }
	public String getLastService() { return lastServiceProperty().get(); }
	public StringProperty lastServiceProperty() {
		if (lastService == null) lastService = new SimpleStringProperty(this, "lastService");
		return lastService;
	}
	
	private StringProperty nextService;
	public void setNextService(String nextService) { nextServiceProperty().set(nextService); }
	public String getNextService() { return nextServiceProperty().get(); }
	public StringProperty nextServiceProperty() {
		if (nextService == null) nextService = new SimpleStringProperty(this, "nextService");
		return nextService;
	}
	
	private StringProperty insuranceExpDate;
	public void setInsuranceExpDate(String insuranceExpDate) { insuranceExpDateProperty().set(insuranceExpDate); }
	public String getInsuranceExpDate() { return insuranceExpDateProperty().get(); }
	public StringProperty insuranceExpDateProperty() {
		if (insuranceExpDate == null) insuranceExpDate = new SimpleStringProperty(this, "insuranceExpDate");
		return insuranceExpDate;
	}
	
	private StringProperty city;
	public void setCity(String value) { cityProperty().set(value); }
	public String getCity() { return cityProperty().get(); }
	public StringProperty cityProperty() {
		if (city == null) city = new SimpleStringProperty(this, "city");
		return city;
	}
}

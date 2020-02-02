package carRentalSystem;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainScreenController
{
	@FXML private AnchorPane anchorPane;
	@FXML private TabPane tabPane;
	@FXML private Tab reservesTab, rentsTab, employeesTab, employmentDataTab, customerstab, storesTab, vehiclesTab;
	
	@FXML private TableView<Reserve> reservesTable;
	@FXML private TableColumn<Reserve, String> startDateReservesColumn, finishDateReservesColumn, vehicleReservesColumn, customerNameReservesColumn,
			startLocationReservesColumn, finishLocationReservesColumn;
	@FXML private TableColumn<Reserve, Boolean> paidReservesColumn;
	
	@FXML private TableView<Rent> rentsTable;
	@FXML private TableColumn<Rent, String> startDateRentsColumn, finishDateRentsColumn, vehicleRentsColumn, customerRentsColumn, employeeRentsColumn,
			startLocationRentsColumn, finishLocationRentsColumn, returnStateRentsColumn, paymentDetailsRentsColumn;
	
	@FXML private TableView<Employee> employeesTable;
	@FXML private TableColumn<Employee, String> irsNumberEmployeeColumn, socialSecurityNumberEmployeeColumn, firstNameEmployeeColumn, lastNameEmployeeColumn,
	driverLicenseEmployeeColumn, cityEmployeeColumn, postalCodeEmployeeColumn, streetEmployeeColumn, streetNumberEmployeeColumn;
	
	@FXML private TableView<EmploymentData> employmentDataTable;
	@FXML private TableColumn<EmploymentData, String> firstNameEmploymentDataColumn, lastNameEmploymentDataColumn, irsNumberEmploymentDataColumn, startDateEmploymentDataColumn,
			finishDateEmploymentDataColumn, positionEmploymentDataColumn, cityEmploymentDataColumn;
	
	@FXML private TableView<Customer> customersTable;
	@FXML private TableColumn<Customer, String> irsNumberCustomersColumn, socialSecurityNumberCustomersColumn, firstNameCustomersColumn, lastNameCustomersColumn,
			driverLicenseCustomersColumn, firstRegistrationCustomersColumn, cityCustomersColumn, postalCodeCustomersColumn, streetCustomersColumn, streetNumberCustomersColumn;
	
	@FXML private TableView<Store> storesTable;
	@FXML private TableColumn<Store, String> cityStoreColumn, postalCodeStoreColumn, streetStoreColumn, streetNumberStoreColumn, phoneNumbersStoreColumn, emailAddressesStoreColumn;
	
	@FXML private TableView<Vehicle> vehiclesTable;
	@FXML private TableColumn<Vehicle, String> makeVehicleColumn, modelVehicleColumn, licensePlateVehicleColumn, typeVehicleColumn, yearVehicleColumn, cylinderCapacityVehicleColumn,
			horsePowerVehicleColumn, kilometersVehicleColumn, damagesVehicleColumn, malfunctionsVehicleColumn, lastServiceVehicleColumn, nextServiceVehicleColumn, insuranceExpDateVehicleColumn,
			cityVehicleColumn;
	
	@FXML private TableColumn editCustomersColumn, deleteCustomersColumn, editReservesColumn, deleteReservesColumn, editRentsColumn, deleteRentsColumn;
	
	@FXML private GridPane customerEditGridPane, reserveEditGridPane, rentEditGridPane;
	
	@FXML private Button saveChangesCustomersButton, saveChangesReservesButton, saveChangesRentsButton,
			submitCustomerButton, cancelCustomerButton, addCustomerButton, resetCustomersButton, refreshCustomersButton,
			addReserveButton, resetReservesButton, refreshReservesButton, submitReserveButton, cancelReserveButton,
			addRentButton, resetRentsButton, refreshRentsButton, submitRentButton, cancelRentButton;
	
	@FXML private CustomTextField firstNameCustomersTextfield, lastNameCustomersTextfield, irsNumberCustomersTextfield, socialSecurityNumberCustomersTextfield,
			driverLicenseCustomersTextfield, cityCustomersTextfield, postalCodeCustomersTextfield, streetCustomersTextfield, streetNumberCustomersTextfield,
			searchCustomersTextfield, searchReservesTextfield, startLocationReservesTextfield, finishLocationReservesTextfield,
			searchRentsTextfield, startLocationRentsTextfield, finishLocationRentsTextfield, paymentAmountRentsTextfield, paymentMethodRentsTextfield;
	
	@FXML private Label numOfCustomersLabel, numOfEmployeesLabel, numOfEmploymentDataLabel, numOfReservesLabel, numOfRentsLabel, numOfStoresLabel, numOfVehiclesLabel,
			mostUsedVehiclesLabel, top3CustomersLabel;
	
	@FXML private ComboBox<String> searchCustomersCombobox, searchReservesCombobox, searchRentsCombobox, chooseCustomerReserveComboBox, chooseLicensePlateReserveComboBox,
			chooseCustomerRentComboBox, chooseLicensePlateRentComboBox, returnStateRentsComboBox, showHistoryOrCurrentRentsComboBox, chooseOrderRentsComboBox;
	
	@FXML private DatePicker startDateReserveDatePicker, finishDateReserveDatePicker, finishDateRentDatePicker;
	
	@FXML private RadioButton paidYesReserveRadioButton, paidNoReserveRadioButton;
	
	private Tooltip max45Chars, max10Chars, numberRequiredTooltip;
	
	private Image editIcon, deleteIcon;
	
	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
	private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private ObservableList<Reserve> reservesObservableList;
	private ObservableList<Rent> rentsObservableList;
	private ObservableList<Employee> employeesObservableList;
	private ObservableList<EmploymentData> employmentDataObservableList;
	private ObservableList<Customer> customersObservableList;
	private ObservableList<Store> storesObservableList;
	private ObservableList<Vehicle> vehiclesObservableList;
	
	DecimalFormat decimalFormatter = new DecimalFormat("#,###.##");
	
	private ObservableList<String> customerSearchList, reservesSearchList, rentsSearchList, 
			chooseCustomerReservesObservableList, chooseLicensePlateReservesObservableList,
			chooseCustomerRentsObservableList, chooseLicensePlateRentsObservableList,
			returnStateObservableList, showHistoryOrCurrentRentsObservableList, chooseOrderRentsObservableList;
	
	private Font font = Font.font("Calibri", FontWeight.NORMAL, 18);
	
	private boolean customerAdd, customerAddOrEdit, reserveAdd, reserveAddOrEdit, rentAdd, rentAddOrEdit;
	private int currentEditCustomerIndex, currentEditReserveIndex, currentEditRentIndex;
	
	private Statement batchCustomersStatement, batchReservesStatement, batchRentsStatement;
	
	private long currentEmployeeIRSNumber;
	private String currentEmployeeName, currentUsername;
	
	private Image backgroundColorsImage;
	private PixelReader pr;
	private int backgroundColorProgress;
	
	private void initializeReservesGUI()
	{
		reserveAdd = false;
		reserveAddOrEdit = false;
		currentEditReserveIndex = -1;
		
		resetBatchReservesEdit();
		
		saveChangesReservesButton.setTooltip(new Tooltip("Save all pending changes to the database"));
		saveChangesReservesButton.getTooltip().setFont(font);
		
		resetReservesButton.setTooltip(new Tooltip("Reset all pending changes to the database"));
		resetReservesButton.getTooltip().setFont(font);
		
		refreshReservesButton.setTooltip(new Tooltip("Refresh the table with latest data from the database"));
		refreshReservesButton.getTooltip().setFont(font);
		
		searchReservesTextfield.setTooltip(new Tooltip());
		searchReservesTextfield.getTooltip().setFont(font);
		searchReservesTextfield.getTooltip().setMaxWidth(400);
		searchReservesTextfield.getTooltip().setWrapText(true);
		
		searchReservesCombobox.valueProperty().addListener((observable ->
		{
			switch(searchReservesCombobox.getSelectionModel().getSelectedIndex())
			{
				case 0: searchReservesTextfield.getTooltip().setText("Write a date in the format: \"d/M/yyyy\". Write '_' to match all values of one of the properties"); break;
				case 1: searchReservesTextfield.getTooltip().setText("Write a date in the format: \"d/M/yyyy\". Write '_' to match all values of one of the properties"); break;
				case 2: searchReservesTextfield.getTooltip().setText("Write: \"firstName,lastName,irsNumber\" divided by commas. Write '_' to match all values of one of the two properties"); break;
				case 3: searchReservesTextfield.getTooltip().setText("Write a vehicle in the format: \"make,model,licensePlate\" divided by commas. Write '_' to match all values of one of the two properties"); break;
				case 4: searchReservesTextfield.getTooltip().setText("Write a location without using commas"); break;
				case 5: searchReservesTextfield.getTooltip().setText("Write a location without using commas"); break;
			}
		}));
		
		reservesSearchList = FXCollections.observableArrayList();
		reservesSearchList.add("Start Date");
		reservesSearchList.add("Finish Date");
		reservesSearchList.add("Customer");
		reservesSearchList.add("Vehicle");
		reservesSearchList.add("Start Location");
		reservesSearchList.add("Finish Location");
		searchReservesCombobox.setItems(reservesSearchList);
		searchReservesCombobox.getSelectionModel().select(0);
		
		startDateReservesColumn.setId("centerColumnText");
		finishDateReservesColumn.setId("centerColumnText");
		
		editReservesColumn.setCellFactory(param ->
				new TableCell<Reserve, ImageView>()
				{
					protected void updateItem(ImageView item, boolean empty)
					{
						super.updateItem(item, empty);
						
						if (empty) setItem(null);
						else
						{
							this.setGraphic(new ImageView(editIcon));
							this.setAlignment(Pos.CENTER);
							((ImageView)this.getGraphic()).setPreserveRatio(true);
							((ImageView)this.getGraphic()).setFitWidth(20);
							
							if(!reserveAddOrEdit)
							{
								setTooltip(null);
								setCursor(Cursor.HAND);
							}
							else
							{
								setTooltip(new Tooltip("Finish the current reserve add/edit operation first"));
								getTooltip().setFont(font);
								setCursor(Cursor.DEFAULT);
							}
							
							setOnMouseClicked(e ->
							{
								if(!reserveAddOrEdit) editReserve(this.getIndex());
							});
						}
					}
				});
		
		deleteReservesColumn.setCellFactory(param ->
				new TableCell<Reserve, ImageView>()
				{
					protected void updateItem(ImageView item, boolean empty)
					{
						super.updateItem(item, empty);
						
						if (empty) setItem(null);
						else
						{
							this.setGraphic(new ImageView(deleteIcon));
							this.setAlignment(Pos.CENTER);
							((ImageView)this.getGraphic()).setPreserveRatio(true);
							((ImageView)this.getGraphic()).setFitWidth(20);
							
							if(!reserveAddOrEdit)
							{
								setTooltip(null);
								setCursor(Cursor.HAND);
							}
							else
							{
								setTooltip(new Tooltip("Finish the current reserve add/edit operation first"));
								getTooltip().setFont(font);
								setCursor(Cursor.DEFAULT);
							}
							
							setOnMouseClicked(e ->
							{
								if(!reserveAddOrEdit) deleteReserve(this.getIndex());
							});
						}
					}
				});
		
		startDateReservesColumn.setCellValueFactory(new PropertyValueFactory("startDate"));
		finishDateReservesColumn.setCellValueFactory(new PropertyValueFactory("finishDate"));
		vehicleReservesColumn.setCellValueFactory(new PropertyValueFactory("vehicleName"));
		customerNameReservesColumn.setCellValueFactory(new PropertyValueFactory("customerName"));
		startLocationReservesColumn.setCellValueFactory(new PropertyValueFactory("startLocation"));
		finishLocationReservesColumn.setCellValueFactory(new PropertyValueFactory("finishLocation"));
		paidReservesColumn.setCellValueFactory(new PropertyValueFactory("paid"));
		paidReservesColumn.setCellFactory(param ->
				new TableCell<Reserve, Boolean>()
				{
					protected void updateItem(Boolean item, boolean empty)
					{
						super.updateItem(item, empty);
						
						if (item == null || empty) setText(null);
						else
						{
							if(item) setText("\u2713");
							else setText("\u274C");
							setAlignment(Pos.CENTER);
						}
					}
				});
		
		reservesTable.setItems(reservesObservableList);
		
		submitReserveButton.setTooltip(new Tooltip("Press 'Save Changes' button to finalize changes"));
		submitReserveButton.setFont(font);
		
		startLocationReservesTextfield.setTooltip(max45Chars);
		finishLocationReservesTextfield.setTooltip(max45Chars);
		
		chooseCustomerReservesObservableList = FXCollections.observableArrayList();
		chooseLicensePlateReservesObservableList = FXCollections.observableArrayList();
		
		chooseCustomerReserveComboBox.setItems(chooseCustomerReservesObservableList);
		chooseLicensePlateReserveComboBox.setItems(chooseLicensePlateReservesObservableList);
		
		finishDateReserveDatePicker.setValue(LocalDate.now());
		startDateReserveDatePicker.setValue(LocalDate.now());
		
		BooleanBinding bb = new BooleanBinding()
		{
			{
				super.bind(startDateReserveDatePicker.valueProperty(), finishDateReserveDatePicker.valueProperty(), chooseCustomerReserveComboBox.valueProperty(),
						chooseLicensePlateReserveComboBox.valueProperty(), startLocationReservesTextfield.textProperty(), finishLocationReservesTextfield.textProperty());
			}
			
			@Override
			protected boolean computeValue()
			{
				return (finishDateReserveDatePicker.getValue().isBefore(startDateReserveDatePicker.getValue()) ||
						startLocationReservesTextfield.getText().isEmpty() || finishLocationReservesTextfield.getText().isEmpty() ||
						chooseCustomerReserveComboBox.getSelectionModel().getSelectedIndex() == -1 ||
						chooseLicensePlateReserveComboBox.getSelectionModel().getSelectedIndex() == -1 ||
						startLocationReservesTextfield.getText().length() > 45 || finishLocationReservesTextfield.getText().length() > 45);
			}
		};
		
		startDateReserveDatePicker.valueProperty().addListener(observable ->
		{
			if(startDateReserveDatePicker.getValue().isAfter(finishDateReserveDatePicker.getValue()))
			{
				finishDateReserveDatePicker.setValue(startDateReserveDatePicker.getValue().plusDays(1));
			}
			
			refreshLicensePlates(true);
		});
		finishDateReserveDatePicker.valueProperty().addListener(observable -> refreshLicensePlates(true));
		
		submitReserveButton.disableProperty().bind(bb);
	}
	
	private void initializeRentsGUI()
	{
		rentAdd = false;
		rentAddOrEdit = false;
		currentEditRentIndex = -1;
		
		resetBatchRentsEdit();
		
		showHistoryOrCurrentRentsObservableList = FXCollections.observableArrayList();
		showHistoryOrCurrentRentsObservableList.add("All Rents");
		showHistoryOrCurrentRentsObservableList.add("Current Rents");
		showHistoryOrCurrentRentsObservableList.add("Archived Rents");
		showHistoryOrCurrentRentsComboBox.setItems(showHistoryOrCurrentRentsObservableList);
		showHistoryOrCurrentRentsComboBox.getSelectionModel().select(1);
		showHistoryOrCurrentRentsComboBox.valueProperty().addListener(observable -> setupRentsTab());
		
		chooseOrderRentsObservableList = FXCollections.observableArrayList();
		chooseOrderRentsObservableList.add("Start Date");
		chooseOrderRentsObservableList.add("Finish Date");
		chooseOrderRentsObservableList.add("Customers (Surname)");
		chooseOrderRentsObservableList.add("Vehicle (Make)");
		chooseOrderRentsObservableList.add("Start Location");
		chooseOrderRentsObservableList.add("Finish Location");
		chooseOrderRentsObservableList.add("Employee (Surname)");
		chooseOrderRentsObservableList.add("Return State");
		chooseOrderRentsComboBox.setItems(chooseOrderRentsObservableList);
		chooseOrderRentsComboBox.getSelectionModel().select(0);
		chooseOrderRentsComboBox.valueProperty().addListener(observable -> setupRentsTab());
		
		returnStateObservableList = FXCollections.observableArrayList();
		returnStateObservableList.add("-");
		returnStateObservableList.add("Perfect");
		returnStateObservableList.add("Slight Damages");
		returnStateObservableList.add("Heavily Damaged");
		returnStateRentsComboBox.setItems(returnStateObservableList);
		
		saveChangesRentsButton.setTooltip(new Tooltip("Save all pending changes to the database"));
		saveChangesRentsButton.getTooltip().setFont(font);
		
		resetRentsButton.setTooltip(new Tooltip("Reset all pending changes to the database"));
		resetRentsButton.getTooltip().setFont(font);
		
		refreshRentsButton.setTooltip(new Tooltip("Refresh the table with latest data from the database"));
		refreshRentsButton.getTooltip().setFont(font);
		
		searchRentsTextfield.setTooltip(new Tooltip());
		searchRentsTextfield.getTooltip().setFont(font);
		searchRentsTextfield.getTooltip().setMaxWidth(400);
		searchRentsTextfield.getTooltip().setWrapText(true);
		
		searchRentsCombobox.valueProperty().addListener((observable ->
		{
			switch(searchRentsCombobox.getSelectionModel().getSelectedIndex())
			{
				case 0: searchRentsTextfield.getTooltip().setText("Write a date in the format: \"dd/MM/yyyy\". write '_' to match all values of one of the properties"); break;
				case 1: searchRentsTextfield.getTooltip().setText("Write a date in the format: \"dd/MM/yyyy\". write '_' to match all values of one of the properties"); break;
				case 2: searchRentsTextfield.getTooltip().setText("Write: \"firstName,lastName,irsNumber\" and divided by commas, write '_' to match all values of one of the two properties"); break;
				case 3: searchRentsTextfield.getTooltip().setText("Write: \"VehicleName,licensePlate\" and divided by commas, write '_' to match all values of one of the two properties"); break;
				case 4: searchRentsTextfield.getTooltip().setText("Write a location without using commas"); break;
				case 5: searchRentsTextfield.getTooltip().setText("Write a location without using commas"); break;
			}
		}));
		
		rentsSearchList = FXCollections.observableArrayList();
		rentsSearchList.add("Start Date");
		rentsSearchList.add("Finish Date");
		rentsSearchList.add("Customer");
		rentsSearchList.add("Vehicle");
		rentsSearchList.add("Start Location");
		rentsSearchList.add("Finish Location");
		searchRentsCombobox.setItems(rentsSearchList);
		searchRentsCombobox.getSelectionModel().select(0);
		
		startDateRentsColumn.setId("centerColumnText");
		finishDateRentsColumn.setId("centerColumnText");
		returnStateRentsColumn.setId("centerColumnText");
		startLocationRentsColumn.setId("centerColumnText");
		finishLocationRentsColumn.setId("centerColumnText");
		
		editRentsColumn.setCellFactory(param ->
				new TableCell<Rent, ImageView>()
				{
					protected void updateItem(ImageView item, boolean empty)
					{
						super.updateItem(item, empty);
						
						if (empty) setItem(null);
						else
						{
							this.setGraphic(new ImageView(editIcon));
							this.setAlignment(Pos.CENTER);
							((ImageView)this.getGraphic()).setPreserveRatio(true);
							((ImageView)this.getGraphic()).setFitWidth(20);
							
							if(!rentAddOrEdit)
							{
								setTooltip(null);
								setCursor(Cursor.HAND);
							}
							else
							{
								setTooltip(new Tooltip("Finish the current rent add/edit operation first"));
								getTooltip().setFont(font);
								setCursor(Cursor.DEFAULT);
							}
							
							setOnMouseClicked(e ->
							{
								if(!rentAddOrEdit) editRent(this.getIndex());
							});
						}
					}
				});
		
		deleteRentsColumn.setCellFactory(param ->
				new TableCell<Rent, ImageView>()
				{
					protected void updateItem(ImageView item, boolean empty)
					{
						super.updateItem(item, empty);
						
						if (empty) setItem(null);
						else
						{
							this.setGraphic(new ImageView(deleteIcon));
							this.setAlignment(Pos.CENTER);
							((ImageView)this.getGraphic()).setPreserveRatio(true);
							((ImageView)this.getGraphic()).setFitWidth(20);
							
							if(!rentAddOrEdit)
							{
								setTooltip(null);
								setCursor(Cursor.HAND);
							}
							else
							{
								setTooltip(new Tooltip("Finish the current rent add/edit operation first"));
								getTooltip().setFont(font);
								setCursor(Cursor.DEFAULT);
							}
							
							setOnMouseClicked(e ->
							{
								if(!rentAddOrEdit) deleteRent(this.getIndex());
							});
						}
					}
				});
		
		customerRentsColumn.setCellFactory(param ->
				new TableCell<Rent, String>()
				{
					protected void updateItem(String item, boolean empty)
					{
						super.updateItem(item, empty);
						
						if (empty) setItem(null);
						else
						{
							Text text = new Text(item);
							text.setStyle(
									" -fx-font-family: \"Calibri\";" +
											" -fx-font-size: 18px;" +
											" -fx-text-wrap: true;" +
											" -fx-text-alignment: center;");
							text.setWrappingWidth(customerRentsColumn.getPrefWidth() - 35);
							this.setPrefHeight(text.getLayoutBounds().getHeight() + 10);
							this.setGraphic(text);
						}
					}
				});
		
		vehicleRentsColumn.setCellFactory(param ->
				new TableCell<Rent, String>()
				{
					protected void updateItem(String item, boolean empty)
					{
						super.updateItem(item, empty);
						
						if (empty) setItem(null);
						else
						{
							Text text = new Text(item);
							text.setStyle(
									" -fx-font-family: \"Calibri\";" +
											" -fx-font-size: 18px;" +
											" -fx-text-wrap: true;" +
											" -fx-text-alignment: center;");
							text.setWrappingWidth(vehicleRentsColumn.getPrefWidth() - 35);
							this.setPrefHeight(text.getLayoutBounds().getHeight() + 10);
							this.setGraphic(text);
						}
					}
				});
		
		employeeRentsColumn.setCellFactory(param ->
				new TableCell<Rent, String>()
				{
					protected void updateItem(String item, boolean empty)
					{
						super.updateItem(item, empty);
						
						if (empty) setItem(null);
						else
						{
							Text text = new Text(item);
							text.setStyle(
									" -fx-font-family: \"Calibri\";" +
											" -fx-font-size: 18px;" +
											" -fx-text-wrap: true;" +
											" -fx-text-alignment: center;");
							text.setWrappingWidth(employeeRentsColumn.getPrefWidth() - 35);
							this.setPrefHeight(text.getLayoutBounds().getHeight() + 10);
							this.setGraphic(text);
						}
					}
				});
		
		paymentDetailsRentsColumn.setCellFactory(param ->
				new TableCell<Rent, String>()
				{
					protected void updateItem(String item, boolean empty)
					{
						super.updateItem(item, empty);
						
						if (empty) setItem(null);
						else
						{
							Text text = new Text(item);
							text.setStyle(
									" -fx-font-family: \"Calibri\";" +
											" -fx-font-size: 18px;" +
											" -fx-text-wrap: true;" +
											" -fx-text-alignment: center;");
							text.setWrappingWidth(paymentDetailsRentsColumn.getPrefWidth() - 35);
							this.setPrefHeight(text.getLayoutBounds().getHeight() + 10);
							this.setGraphic(text);
						}
					}
				});
		
		startDateRentsColumn.setCellValueFactory(new PropertyValueFactory("startDate"));
		finishDateRentsColumn.setCellValueFactory(new PropertyValueFactory("finishDate"));
		vehicleRentsColumn.setCellValueFactory(new PropertyValueFactory("vehicleName"));
		customerRentsColumn.setCellValueFactory(new PropertyValueFactory("customerName"));
		startLocationRentsColumn.setCellValueFactory(new PropertyValueFactory("startLocation"));
		finishLocationRentsColumn.setCellValueFactory(new PropertyValueFactory("finishLocation"));
		returnStateRentsColumn.setCellValueFactory(new PropertyValueFactory("returnState"));
		employeeRentsColumn.setCellValueFactory(new PropertyValueFactory("employeeName"));
		paymentDetailsRentsColumn.setCellValueFactory(new PropertyValueFactory("paymentDetails"));
		
		rentsTable.setItems(rentsObservableList);
		
		submitRentButton.setTooltip(new Tooltip("Press 'Save Changes' button to finalize changes"));
		submitRentButton.setFont(font);
		
		startLocationRentsTextfield.setTooltip(max45Chars);
		finishLocationRentsTextfield.setTooltip(max45Chars);
		
		chooseCustomerRentsObservableList = FXCollections.observableArrayList();
		chooseLicensePlateRentsObservableList = FXCollections.observableArrayList();
		
		chooseCustomerRentComboBox.setItems(chooseCustomerRentsObservableList);
		chooseLicensePlateRentComboBox.setItems(chooseLicensePlateRentsObservableList);
		
		finishDateRentDatePicker.setValue(LocalDate.now().plusDays(1));
		
		BooleanBinding bb = new BooleanBinding()
		{
			{
				super.bind(finishDateRentDatePicker.valueProperty(), chooseCustomerRentComboBox.valueProperty(),
						chooseLicensePlateRentComboBox.valueProperty(), startLocationRentsTextfield.textProperty(), finishLocationRentsTextfield.textProperty(),
						returnStateRentsComboBox.valueProperty(), paymentAmountRentsTextfield.textProperty(), paymentMethodRentsTextfield.textProperty());
			}
			
			@Override
			protected boolean computeValue()
			{
				return (finishDateRentDatePicker.getValue().isBefore(LocalDate.now()) ||
						startLocationRentsTextfield.getText().isEmpty() || finishLocationRentsTextfield.getText().isEmpty() ||
						chooseCustomerRentComboBox.getSelectionModel().getSelectedIndex() == -1 ||
						chooseLicensePlateRentComboBox.getSelectionModel().getSelectedIndex() == -1 ||
						startLocationRentsTextfield.getText().length() > 45 || finishLocationRentsTextfield.getText().length() > 45
						|| returnStateRentsComboBox.getSelectionModel().getSelectedIndex() == -1
						|| !isDouble(paymentAmountRentsTextfield.getText()) || paymentMethodRentsTextfield.getText().isEmpty() || paymentMethodRentsTextfield.getText().length() > 45);
			}
		};
		
		finishDateRentDatePicker.valueProperty().addListener(observable -> refreshLicensePlates(false));
		
		submitRentButton.disableProperty().bind(bb);
	}
	
	private void initializeEmployeesGUI()
	{
		firstNameEmployeeColumn.setCellValueFactory(new PropertyValueFactory("firstName"));
		lastNameEmployeeColumn.setCellValueFactory(new PropertyValueFactory("lastName"));
		irsNumberEmployeeColumn.setCellValueFactory(new PropertyValueFactory("irsNumber"));
		socialSecurityNumberEmployeeColumn.setCellValueFactory(new PropertyValueFactory("socialSecurityNumber"));
		driverLicenseEmployeeColumn.setCellValueFactory(new PropertyValueFactory("driverLicense"));
		cityEmployeeColumn.setCellValueFactory(new PropertyValueFactory("city"));
		postalCodeEmployeeColumn.setCellValueFactory(new PropertyValueFactory("postalCode"));
		streetEmployeeColumn.setCellValueFactory(new PropertyValueFactory("street"));
		streetNumberEmployeeColumn.setCellValueFactory(new PropertyValueFactory("streetNumber"));
		
		irsNumberEmployeeColumn.setId("centerColumnText");
		socialSecurityNumberEmployeeColumn.setId("centerColumnText");
		driverLicenseEmployeeColumn.setId("centerColumnText");
		cityEmployeeColumn.setId("centerColumnText");
		postalCodeEmployeeColumn.setId("centerColumnText");
		streetEmployeeColumn.setId("centerColumnText");
		streetNumberEmployeeColumn.setId("centerColumnText");
		
		employeesTable.setItems(employeesObservableList);
	}
	
	private void initializeEmploymentDataGUI()
	{
		firstNameEmploymentDataColumn.setCellValueFactory(new PropertyValueFactory("firstName"));
		lastNameEmploymentDataColumn.setCellValueFactory(new PropertyValueFactory("lastName"));
		irsNumberEmploymentDataColumn.setCellValueFactory(new PropertyValueFactory("irsNumber"));
		startDateEmploymentDataColumn.setCellValueFactory(new PropertyValueFactory("startDate"));
		finishDateEmploymentDataColumn.setCellValueFactory(new PropertyValueFactory("finishDate"));
		positionEmploymentDataColumn.setCellValueFactory(new PropertyValueFactory("position"));
		cityEmploymentDataColumn.setCellValueFactory(new PropertyValueFactory("city"));
		
		irsNumberEmploymentDataColumn.setId("centerColumnText");
		startDateEmploymentDataColumn.setId("centerColumnText");
		finishDateEmploymentDataColumn.setId("centerColumnText");
		positionEmploymentDataColumn.setId("centerColumnText");
		cityEmploymentDataColumn.setId("centerColumnText");
		
		employmentDataTable.setItems(employmentDataObservableList);
	}
	
	private void initializeCustomersGUI()
	{
		customerAdd = false;
		customerAddOrEdit = false;
		currentEditCustomerIndex = -1;
		
		resetBatchCustomersEdit();
		
		saveChangesCustomersButton.setTooltip(new Tooltip("Save all pending changes to the database"));
		saveChangesCustomersButton.getTooltip().setFont(font);
		
		resetCustomersButton.setTooltip(new Tooltip("Reset all pending changes to the database"));
		resetCustomersButton.getTooltip().setFont(font);
		
		refreshCustomersButton.setTooltip(new Tooltip("Refresh the table with latest data from the database"));
		refreshCustomersButton.getTooltip().setFont(font);
		
		searchCustomersTextfield.setTooltip(new Tooltip());
		searchCustomersTextfield.getTooltip().setFont(font);
		searchCustomersTextfield.getTooltip().setMaxWidth(400);
		searchCustomersTextfield.getTooltip().setWrapText(true);
		
		searchCustomersCombobox.valueProperty().addListener((observable ->
		{
			switch(searchCustomersCombobox.getSelectionModel().getSelectedIndex())
			{
				case 0: searchCustomersTextfield.getTooltip().setText("Write: \"firstName,lastName\" and " +
						"divided by commas, write '_' to match all values of one of the two properties"); break;
				case 1: searchCustomersTextfield.getTooltip().setText("Write an IRS number"); break;
				case 2: searchCustomersTextfield.getTooltip().setText("Write a social security number"); break;
				case 3: searchCustomersTextfield.getTooltip().setText("Write a driver license"); break;
				case 4: searchCustomersTextfield.getTooltip().setText("Write a date in the format: \"dd/MM/yyyy\". write '_' to match all values of one of the properties"); break;
				case 5: searchCustomersTextfield.getTooltip().setText("Write: \"city,street,streetNumber,postalCode\" and " +
						"divided by commas, write '_' to match all values of one of the properties"); break;
			}
		}));
		
		customerSearchList = FXCollections.observableArrayList();
		customerSearchList.add("Full Name");
		customerSearchList.add("IRS Number");
		customerSearchList.add("Social Security Number");
		customerSearchList.add("Driver License");
		customerSearchList.add("First Registration");
		customerSearchList.add("Full Address");
		searchCustomersCombobox.setItems(customerSearchList);
		searchCustomersCombobox.getSelectionModel().select(0);
		
		irsNumberCustomersColumn.setId("centerColumnText");
		driverLicenseCustomersColumn.setId("centerColumnText");
		socialSecurityNumberCustomersColumn.setId("centerColumnText");
		firstRegistrationCustomersColumn.setId("centerColumnText");
		cityCustomersColumn.setId("centerColumnText");
		streetCustomersColumn.setId("centerColumnText");
		streetNumberCustomersColumn.setId("centerColumnText");
		postalCodeCustomersColumn.setId("centerColumnText");
		
		editCustomersColumn.setCellFactory(param ->
				new TableCell<Customer, ImageView>()
				{
					protected void updateItem(ImageView item, boolean empty)
					{
						super.updateItem(item, empty);
						
						if (empty) setItem(null);
						else
						{
							this.setGraphic(new ImageView(editIcon));
							this.setAlignment(Pos.CENTER);
							((ImageView)this.getGraphic()).setPreserveRatio(true);
							((ImageView)this.getGraphic()).setFitWidth(20);
							
							if(!customerAddOrEdit)
							{
								setTooltip(null);
								setCursor(Cursor.HAND);
							}
							else
							{
								setTooltip(new Tooltip("Finish the current customer add/edit operation first"));
								getTooltip().setFont(font);
								setCursor(Cursor.DEFAULT);
							}
							
							setOnMouseClicked(e ->
							{
								if(!customerAddOrEdit) editCustomer(this.getIndex());
							});
						}
					}
				});
		
		deleteCustomersColumn.setCellFactory(param ->
				new TableCell<Customer, ImageView>()
				{
					protected void updateItem(ImageView item, boolean empty)
					{
						super.updateItem(item, empty);
						
						if (empty) setItem(null);
						else
						{
							this.setGraphic(new ImageView(deleteIcon));
							this.setAlignment(Pos.CENTER);
							((ImageView)this.getGraphic()).setPreserveRatio(true);
							((ImageView)this.getGraphic()).setFitWidth(20);
							
							if(!customerAddOrEdit)
							{
								setTooltip(null);
								setCursor(Cursor.HAND);
							}
							else
							{
								setTooltip(new Tooltip("Finish the current customer add/edit operation first"));
								getTooltip().setFont(font);
								setCursor(Cursor.DEFAULT);
							}
							
							setOnMouseClicked(e ->
							{
								if(!customerAddOrEdit) deleteCustomer(this.getIndex());
							});
						}
					}
				});
		
		irsNumberCustomersColumn.setCellValueFactory(new PropertyValueFactory("irsNumber"));
		socialSecurityNumberCustomersColumn.setCellValueFactory(new PropertyValueFactory("socialSecurityNumber"));
		firstNameCustomersColumn.setCellValueFactory(new PropertyValueFactory("firstName"));
		lastNameCustomersColumn.setCellValueFactory(new PropertyValueFactory("lastName"));
		driverLicenseCustomersColumn.setCellValueFactory(new PropertyValueFactory("driverLicense"));
		firstRegistrationCustomersColumn.setCellValueFactory(new PropertyValueFactory("firstRegistration"));
		cityCustomersColumn.setCellValueFactory(new PropertyValueFactory("city"));
		postalCodeCustomersColumn.setCellValueFactory(new PropertyValueFactory("postalCode"));
		streetCustomersColumn.setCellValueFactory(new PropertyValueFactory("street"));
		streetNumberCustomersColumn.setCellValueFactory(new PropertyValueFactory("streetNumber"));
		
		customersTable.setItems(customersObservableList);
		
		submitCustomerButton.setTooltip(new Tooltip("Press 'Save Changes' button to finalize changes"));
		submitCustomerButton.setFont(font);
		
		firstNameCustomersTextfield.setTooltip(max45Chars);
		lastNameCustomersTextfield.setTooltip(max45Chars);
		irsNumberCustomersTextfield.setTooltip(numberRequiredTooltip);
		socialSecurityNumberCustomersTextfield.setTooltip(numberRequiredTooltip);
		driverLicenseCustomersTextfield.setTooltip(numberRequiredTooltip);
		cityCustomersTextfield.setTooltip(max45Chars);
		postalCodeCustomersTextfield.setTooltip(max10Chars);
		streetCustomersTextfield.setTooltip(max45Chars);
		streetNumberCustomersTextfield.setTooltip(max10Chars);
		
		BooleanBinding bb = new BooleanBinding()
		{
			{
				super.bind(firstNameCustomersTextfield.textProperty(), lastNameCustomersTextfield.textProperty(), irsNumberCustomersTextfield.textProperty(),
						socialSecurityNumberCustomersTextfield.textProperty(), driverLicenseCustomersTextfield.textProperty(), cityCustomersTextfield.textProperty(),
						postalCodeCustomersTextfield.textProperty(), streetCustomersTextfield.textProperty(), streetNumberCustomersTextfield.textProperty());
			}
			
			@Override
			protected boolean computeValue()
			{
				return ((firstNameCustomersTextfield.getText().isEmpty() || lastNameCustomersTextfield.getText().isEmpty() || irsNumberCustomersTextfield.getText().isEmpty() ||
						socialSecurityNumberCustomersTextfield.getText().isEmpty() || driverLicenseCustomersTextfield.getText().isEmpty() || cityCustomersTextfield.getText().isEmpty() ||
						postalCodeCustomersTextfield.getText().isEmpty() || streetCustomersTextfield.getText().isEmpty() || streetNumberCustomersTextfield.getText().isEmpty()) ||
						!isLong(irsNumberCustomersTextfield.getText()) || !isLong(socialSecurityNumberCustomersTextfield.getText()) || !isLong(driverLicenseCustomersTextfield.getText()) ||
						streetNumberCustomersTextfield.getText().length() > 10 || postalCodeCustomersTextfield.getText().length() > 10 || firstNameCustomersTextfield.getText().length() > 45 ||
						lastNameCustomersTextfield.getText().length() > 45 || cityCustomersTextfield.getText().length() > 45 || streetCustomersTextfield.getText().length() > 45);
			}
		};
		
		submitCustomerButton.disableProperty().bind(bb);
	}
	
	private void initializeStoresGUI()
	{
		cityStoreColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
		postalCodeStoreColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
		streetStoreColumn.setCellValueFactory(new PropertyValueFactory<>("street"));
		streetNumberStoreColumn.setCellValueFactory(new PropertyValueFactory<>("streetNumber"));
		
		cityStoreColumn.setId("centerColumnText");
		postalCodeStoreColumn.setId("centerColumnText");
		streetStoreColumn.setId("centerColumnText");
		streetNumberStoreColumn.setId("centerColumnText");
		
		storesTable.setItems(storesObservableList);
		
		phoneNumbersStoreColumn.setCellFactory(param ->
				new TableCell<Store, String>()
				{
					protected void updateItem(String item, boolean empty)
					{
						super.updateItem(item, empty);
						
						if (empty) setItem(null);
						else
						{
							StringBuilder sb = new StringBuilder();
							String[] phones = storesObservableList.get(this.getIndex()).getPhoneNumbers();
							for(int i = 0; i < phones.length; i++)
							{
								sb.append(phones[i]);
								if(i != phones.length - 1) sb.append(System.lineSeparator());
							}
							setText(sb.toString());
							
							setAlignment(Pos.CENTER);
						}
					}
				});
		
		emailAddressesStoreColumn.setCellFactory(param ->
				new TableCell<Store, String>()
				{
					protected void updateItem(String item, boolean empty)
					{
						super.updateItem(item, empty);
						
						if (empty) setItem(null);
						else
						{
							StringBuilder sb = new StringBuilder();
							String[] emails = storesObservableList.get(this.getIndex()).getEmailAddresses();
							for(int i = 0; i < emails.length; i++)
							{
								sb.append(emails[i]);
								if(i != emails.length - 1) sb.append(System.lineSeparator());
							}
							setText(sb.toString());
							
							setAlignment(Pos.CENTER);
						}
					}
				});
	}
	
	private void initializeVehiclesGUI()
	{
		makeVehicleColumn.setCellValueFactory(new PropertyValueFactory("make"));
		modelVehicleColumn.setCellValueFactory(new PropertyValueFactory("model"));
		licensePlateVehicleColumn.setCellValueFactory(new PropertyValueFactory("licensePlate"));
		typeVehicleColumn.setCellValueFactory(new PropertyValueFactory("type"));
		yearVehicleColumn.setCellValueFactory(new PropertyValueFactory("year"));
		cylinderCapacityVehicleColumn.setCellValueFactory(new PropertyValueFactory("cylinderCapacity"));
		horsePowerVehicleColumn.setCellValueFactory(new PropertyValueFactory("horsePower"));
		kilometersVehicleColumn.setCellValueFactory(new PropertyValueFactory("kilometers"));
		damagesVehicleColumn.setCellValueFactory(new PropertyValueFactory("damages"));
		malfunctionsVehicleColumn.setCellValueFactory(new PropertyValueFactory("malfunctions"));
		lastServiceVehicleColumn.setCellValueFactory(new PropertyValueFactory("lastService"));
		nextServiceVehicleColumn.setCellValueFactory(new PropertyValueFactory("nextService"));
		insuranceExpDateVehicleColumn.setCellValueFactory(new PropertyValueFactory("insuranceExpDate"));
		cityVehicleColumn.setCellValueFactory(new PropertyValueFactory("city"));
		
		makeVehicleColumn.setId("centerColumnText");
		modelVehicleColumn.setId("centerColumnText");
		licensePlateVehicleColumn.setId("centerColumnText");
		typeVehicleColumn.setId("centerColumnText");
		yearVehicleColumn.setId("centerColumnText");
		cylinderCapacityVehicleColumn.setId("centerColumnText");
		horsePowerVehicleColumn.setId("centerColumnText");
		kilometersVehicleColumn.setId("centerColumnText");
		damagesVehicleColumn.setId("centerColumnText");
		malfunctionsVehicleColumn.setId("centerColumnText");
		lastServiceVehicleColumn.setId("centerColumnText");
		nextServiceVehicleColumn.setId("centerColumnText");
		insuranceExpDateVehicleColumn.setId("centerColumnText");
		cityVehicleColumn.setId("centerColumnText");
		
		cylinderCapacityVehicleColumn.setCellFactory(param ->
				new TableCell<Vehicle, String>()
				{
					protected void updateItem(String item, boolean empty)
					{
						super.updateItem(item, empty);
						
						if (empty) setItem(null);
						else
						{
							setText(decimalFormatter.format(Integer.parseInt(item)));
						}
					}
				});
		
		kilometersVehicleColumn.setCellFactory(param ->
				new TableCell<Vehicle, String>()
				{
					protected void updateItem(String item, boolean empty)
					{
						super.updateItem(item, empty);
						
						if (empty) setItem(null);
						else
						{
							setText(decimalFormatter.format(Integer.parseInt(item)));
						}
					}
				});
		
		damagesVehicleColumn.setCellFactory(param ->
				new TableCell<Vehicle, String>()
				{
					protected void updateItem(String item, boolean empty)
					{
						super.updateItem(item, empty);
						
						if (empty) setItem(null);
						else
						{
							Text text = new Text(item);
							text.setStyle(
									" -fx-font-family: \"Calibri\";" +
									" -fx-font-size: 18px;" +
									" -fx-text-wrap: true;" +
									" -fx-text-alignment: center;");
							text.setWrappingWidth(damagesVehicleColumn.getPrefWidth() - 35);
							this.setPrefHeight(text.getLayoutBounds().getHeight() + 10);
							this.setGraphic(text);
						}
					}
				});
		
		malfunctionsVehicleColumn.setCellFactory(param ->
				new TableCell<Vehicle, String>()
				{
					protected void updateItem(String item, boolean empty)
					{
						super.updateItem(item, empty);
						
						if (empty) setItem(null);
						else
						{
							Text text = new Text(item);
							text.setStyle(
									" -fx-font-family: \"Calibri\";" +
									" -fx-font-size: 18px;" +
									" -fx-text-wrap: true;" +
									" -fx-text-alignment: center;");
							text.setWrappingWidth(malfunctionsVehicleColumn.getPrefWidth() - 35);
							this.setPrefHeight(text.getLayoutBounds().getHeight() + 10);
							this.setGraphic(text);
						}
					}
				});
		
		vehiclesTable.setItems(vehiclesObservableList);
	}
	
	@FXML
	public void initialize()
	{
		editIcon = new Image("/resources/images/edit.png");
		deleteIcon = new Image("/resources/images/delete.png");
		
		max45Chars = new Tooltip("Max 45 characters");
		max45Chars.setFont(font);
		max10Chars = new Tooltip("Max 10 characters");
		max10Chars.setFont(font);
		numberRequiredTooltip = new Tooltip("Number required");
		numberRequiredTooltip.setFont(font);
		
		reservesObservableList = FXCollections.observableArrayList();
		rentsObservableList = FXCollections.observableArrayList();
		employeesObservableList = FXCollections.observableArrayList();
		employmentDataObservableList = FXCollections.observableArrayList();
		customersObservableList = FXCollections.observableArrayList();
		storesObservableList = FXCollections.observableArrayList();
		vehiclesObservableList = FXCollections.observableArrayList();
		
		initializeReservesGUI();
		initializeRentsGUI();
		initializeEmployeesGUI();
		initializeEmploymentDataGUI();
		initializeCustomersGUI();
		initializeStoresGUI();
		initializeVehiclesGUI();
		
		try { batchReservesStatement = Main.getConn().createStatement(); }catch(SQLException e) { e.printStackTrace(); }
		setupReservesTab();
		
		try { batchRentsStatement = Main.getConn().createStatement(); }catch(SQLException e) { e.printStackTrace(); }
		setupRentsTab();
		
		setupEmployeesTab();
		
		setupEmploymentDataTab();
		
		try { batchCustomersStatement = Main.getConn().createStatement(); }catch(SQLException e) { e.printStackTrace(); }
		setupCustomersTab();
		
		setupStoresTab();
		
		setupVehiclesTab();
		
		backgroundColorsImage = new Image("/resources/images/bgColors.jpg");
		pr = backgroundColorsImage.getPixelReader();
		backgroundColorProgress = new Random().nextInt(1800) + 2400;
		anchorPane.setStyle("-fx-background-color: #" + pr.getColor(backgroundColorProgress, 0).toString().replace("0x", "") + ";");
	}
	
	@FXML
	private void searchReserves()
	{
		if(!searchReservesTextfield.getText().trim().isEmpty())
		{
			try
			{
				Statement searchStatement = Main.getConn().createStatement();
				String searchQuery;
				
				String[] input;
				
				int index = searchReservesCombobox.getSelectionModel().getSelectedIndex();
				
				if(index < 2) input = searchReservesTextfield.getText().toLowerCase().split("/");
				else input = searchReservesTextfield.getText().toLowerCase().split(",");
				
				if(index < 4 && input.length != 3 || index >= 4 && input.length != 1)
				{
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Not valid search input");
					
					alert.showAndWait();
				}
				else
				{
					addReserveButton.setDisable(true);
					
					if(index == 0)
					{
						StringBuilder sb = new StringBuilder();
						sb.append("SELECT * FROM reserves_with_customers_and_vehicles");
						
						if(!input[0].equals("_") || !input[1].equals("_") || !input[2].equals("_")) sb.append(" WHERE");
						
						if(!input[0].equals("_")) sb.append(" day(start_date)='").append(input[0]).append("'");
						
						if(!input[0].equals("_") && !input[1].equals("_") || !input[0].equals("_") && input[1].equals("_") && !input[2].equals("_")) sb.append(" and");
						if(!input[1].equals("_")) sb.append(" month(start_date)='").append(input[1]).append("'");
						
						if(!input[1].equals("_") && !input[2].equals("_")) sb.append(" and");
						if(!input[2].equals("_")) sb.append(" year(start_date)='").append(input[2]).append("'");
						
						sb.append(";");
						
						searchQuery = sb.toString();
					}
					else if(index == 1)
					{
						StringBuilder sb = new StringBuilder();
						sb.append("SELECT * FROM reserves_with_customers_and_vehicles");
						
						if(!input[0].equals("_") || !input[1].equals("_") || !input[2].equals("_")) sb.append(" WHERE");
						
						if(!input[0].equals("_")) sb.append(" day(finish_date)='").append(input[0]).append("'");
						
						if(!input[0].equals("_") && !input[1].equals("_") || !input[0].equals("_") && input[1].equals("_") && !input[2].equals("_")) sb.append(" and");
						if(!input[1].equals("_")) sb.append(" month(finish_date)='").append(input[1]).append("'");
						
						if(!input[1].equals("_") && !input[2].equals("_")) sb.append(" and");
						if(!input[2].equals("_")) sb.append(" year(finish_date)='").append(input[2]).append("'");
						
						sb.append(";");
						
						searchQuery = sb.toString();
					}
					else if(index == 2)
					{
						StringBuilder sb = new StringBuilder();
						sb.append("SELECT * FROM reserves_with_customers_and_vehicles");
						
						if(!input[0].equals("_") || !input[1].equals("_") || !input[2].equals("_")) sb.append(" WHERE");
						
						if(!input[0].equals("_")) sb.append(" LOWER(customer_first_name) LIKE LOWER('%").append(input[0]).append("%')");
						
						if(!input[0].equals("_") && !input[1].equals("_") || !input[0].equals("_") && input[1].equals("_") && !input[2].equals("_")) sb.append(" and");
						if(!input[1].equals("_")) sb.append(" LOWER(customer_last_name) LIKE LOWER('%").append(input[1]).append("%')");
						
						if(!input[1].equals("_") && !input[2].equals("_")) sb.append(" and");
						if(!input[2].equals("_")) sb.append(" LOWER(customer_irs_number) LIKE LOWER('%").append(input[2]).append("%')");
						
						sb.append(";");
						
						searchQuery = sb.toString();
					}
					else if(index == 3)
					{
						searchQuery = "SELECT * FROM reserves_with_customers_and_vehicles WHERE " +
								"LOWER(vehicle_make) LIKE LOWER('%" + input[0] + "%') and LOWER(vehicle_model) LIKE LOWER('%" + input[1] + "%') and LOWER(vehicle_license_plate) LIKE LOWER('%" + input[2] + "%');";
					}
					else if(index == 4)
						searchQuery = "SELECT * FROM reserves_with_customers_and_vehicles WHERE LOWER(start_location) LIKE LOWER('%" + input[0] + "%');";
					else
						searchQuery = "SELECT * FROM reserves_with_customers_and_vehicles WHERE LOWER(finish_location) LIKE LOWER('%" + input[0] + "%');";
					
					ResultSet rs = searchStatement.executeQuery(searchQuery);
					
					reservesObservableList.clear();
					while(rs.next())
					{
						String start_date = rs.getDate("start_date").toLocalDate().format(dateFormatter);
						String finish_date = rs.getDate("finish_date").toLocalDate().format(dateFormatter);
						String start_location = rs.getString("start_location");
						String finish_location = rs.getString("finish_location");
						boolean paid = rs.getBoolean("paid");
						
						int customer_id = rs.getInt("customer_id");
						String customer_name = rs.getString("customer_first_name") + " " + rs.getString("customer_last_name") + " " + rs.getString("customer_irs_number");
						
						String license_plate = rs.getString("vehicle_license_plate");
						String vehicle_name = rs.getString("vehicle_make") + " " + rs.getString("vehicle_model") + " " + license_plate;
						
						Reserve reserve = new Reserve(start_date, finish_date, start_location, finish_location, paid, customer_id, customer_name, license_plate, vehicle_name);
						
						reservesObservableList.add(reserve);
					}
					
					numOfReservesLabel.setText("Found " + reservesObservableList.size() + " reserves");
					
					reservesTable.refresh();
					
					rs.close();
					searchStatement.close();
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		else setupReservesTab();
	}
	
	@FXML
	private void searchRents()
	{
		if(!searchRentsTextfield.getText().trim().isEmpty())
		{
			try
			{
				Statement searchStatement = Main.getConn().createStatement();
				String searchQuery;
				
				String[] input;
				
				int index = searchRentsCombobox.getSelectionModel().getSelectedIndex();
				
				if(index < 2) input = searchRentsTextfield.getText().toLowerCase().split("/");
				else input = searchRentsTextfield.getText().toLowerCase().split(",");
				
				if(index == 0 && input.length != 3 || index == 1 && input.length != 3 || index == 2 && input.length != 3 || index == 3 && input.length != 2 || index >= 4 && input.length != 1)
				{
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Not valid search input");
					
					alert.showAndWait();
				}
				else
				{
					addRentButton.setDisable(true);
					
					if(index == 0)
					{
						StringBuilder sb = new StringBuilder();
						sb.append("SELECT * FROM rents_with_payments_customers_vehicles_and_employees");
						
						if(!input[0].equals("_") || !input[1].equals("_") || !input[2].equals("_")) sb.append(" WHERE");
						
						if(!input[0].equals("_")) sb.append(" day(start_date)='").append(input[0]).append("'");
						
						if(!input[0].equals("_") && !input[1].equals("_") || !input[0].equals("_") && input[1].equals("_") && !input[2].equals("_")) sb.append(" and");
						if(!input[1].equals("_")) sb.append(" month(start_date)='").append(input[1]).append("'");
						
						if(!input[1].equals("_") && !input[2].equals("_")) sb.append(" and");
						if(!input[2].equals("_")) sb.append(" year(start_date)='").append(input[2]).append("'");
						
						searchQuery = sb.toString();
					}
					else if(index == 1)
					{
						StringBuilder sb = new StringBuilder();
						sb.append("SELECT * FROM rents_with_payments_customers_vehicles_and_employees");
						
						if(!input[0].equals("_") || !input[1].equals("_") || !input[2].equals("_")) sb.append(" WHERE");
						
						if(!input[0].equals("_")) sb.append(" day(finish_date)='").append(input[0]).append("'");
						
						if(!input[0].equals("_") && !input[1].equals("_") || !input[0].equals("_") && input[1].equals("_") && !input[2].equals("_")) sb.append(" and");
						if(!input[1].equals("_")) sb.append(" month(finish_date)='").append(input[1]).append("'");
						
						if(!input[1].equals("_") && !input[2].equals("_")) sb.append(" and");
						if(!input[2].equals("_")) sb.append(" year(finish_date)='").append(input[2]).append("'");
						
						searchQuery = sb.toString();
					}
					else if(index == 2)
					{
						StringBuilder sb = new StringBuilder();
						sb.append("SELECT * FROM rents_with_payments_customers_vehicles_and_employees");
						
						if(!input[0].equals("_") || !input[1].equals("_") || !input[2].equals("_")) sb.append(" WHERE");
						
						if(!input[0].equals("_")) sb.append(" LOWER(customer_first_name) LIKE LOWER('%").append(input[0]).append("%')");
						
						if(!input[0].equals("_") && !input[1].equals("_") || !input[0].equals("_") && input[1].equals("_") && !input[2].equals("_")) sb.append(" and");
						if(!input[1].equals("_")) sb.append(" LOWER(customer_last_name) LIKE LOWER('%").append(input[1]).append("%')");
						
						if(!input[1].equals("_") && !input[2].equals("_")) sb.append(" and");
						if(!input[2].equals("_")) sb.append(" LOWER(customer_irs_number) LIKE LOWER('%").append(input[2]).append("%')");
						
						searchQuery = sb.toString();
					}
					else if(index == 3)
					{
						searchQuery = "SELECT * FROM rents_with_payments_customers_vehicles_and_employees WHERE " +
								"LOWER(vehicle_name) LIKE LOWER('%" + input[0] + "%') and LOWER(vehicle_license_plate) LIKE LOWER('%" + input[1] + "%')";
					}
					else if(index == 4)
						searchQuery = "SELECT * FROM rents_with_payments_customers_vehicles_and_employees WHERE LOWER(start_location) LIKE LOWER('%" + input[0] + "%')";
					else
						searchQuery = "SELECT * FROM rents_with_payments_customers_vehicles_and_employees WHERE LOWER(finish_location) LIKE LOWER('%" + input[0] + "%')";
					
					String which;
					if(!searchQuery.contains("WHERE")) which = " WHERE ";
					else which = " and ";
					
					int index1 = showHistoryOrCurrentRentsComboBox.getSelectionModel().getSelectedIndex();
					if(index1 == 0) which = "";
					else if(index1 == 1) which = which + " finish_date >= CURDATE()";
					else which = which + " finish_date < CURDATE()";
					
					index1 = chooseOrderRentsComboBox.getSelectionModel().getSelectedIndex();
					String order;
					if(index1 == 0) order = " order by start_date;";
					else if(index1 == 1) order = " order by finish_date;";
					else if(index1 == 2) order = " order by customer_last_name;";
					else if(index1 == 3) order = " order by vehicle_make;";
					else if(index1 == 4) order = " order by start_location;";
					else if(index1 == 5) order = " order by finish_location;";
					else if(index1 == 6) order = " order by employee_last_name;";
					else order = " order by return_state;";
					
					searchQuery = searchQuery + which + order;
					
					ResultSet rs = searchStatement.executeQuery(searchQuery);
					
					rentsObservableList.clear();
					while(rs.next())
					{
						String start_date = rs.getDate("start_date").toLocalDate().format(dateFormatter);
						String finish_date = rs.getDate("finish_date").toLocalDate().format(dateFormatter);
						String start_location = rs.getString("start_location");
						String finish_location = rs.getString("finish_location");
						String return_state = rs.getString("return_state");
						
						int customer_id = rs.getInt("customer_id");
						String customer_name = rs.getString("customer_first_name") + " " + rs.getString("customer_last_name") + " - " + rs.getString("customer_irs_number");
						
						String license_plate = rs.getString("vehicle_license_plate");
						String vehicle_name = rs.getString("vehicle_name") + " - " + license_plate;
						
						long irs_number = rs.getLong("employee_irs_number");
						String employee_name = rs.getString("employee_first_name") + " " + rs.getString("employee_last_name") + " - " + irs_number;
						
						String payment_details = String.valueOf(rs.getBigDecimal("amount")) + " - " + rs.getString("method");
						
						Rent rent = new Rent(start_date, finish_date, start_location, finish_location, return_state, customer_id, customer_name, license_plate, vehicle_name, irs_number, employee_name, payment_details);
						
						rentsObservableList.add(rent);
					}
					
					numOfRentsLabel.setText("Found " + rentsObservableList.size() + " rents");
					
					rentsTable.refresh();
					
					rs.close();
					searchStatement.close();
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		else setupRentsTab();
	}
	
	@FXML
	private void searchCustomers()
	{
		if(!searchCustomersTextfield.getText().trim().isEmpty())
		{
			try
			{
				Statement searchStatement = Main.getConn().createStatement();
				String searchQuery;
				
				String[] input;
				
				int index = searchCustomersCombobox.getSelectionModel().getSelectedIndex();
				
				if(index == 4) input = searchCustomersTextfield.getText().toLowerCase().split("/");
				else input = searchCustomersTextfield.getText().toLowerCase().split(",");
				
				if(index == 0 && input.length != 2 || index == 1 && input.length != 1 || index == 2 && input.length != 1 ||
						index == 3 && input.length != 1 || index == 4 && input.length != 3 || index == 5 && input.length != 4)
				{
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Not valid search input");
					
					alert.showAndWait();
				}
				else
				{
					addCustomerButton.setDisable(true);
					
					if(index == 0)
					{
						if(input[0].equals("_") && !input[1].equals("_")) searchQuery = "SELECT * FROM customers_view WHERE LOWER(last_name) LIKE '%" + input[1] + "%';";
						else if(!input[0].equals("_") && input[1].equals("_")) searchQuery = "SELECT * FROM customers_view WHERE LOWER(first_name) LIKE '%" + input[0] + "%';";
						else if(input[0].equals("_") && input[1].equals("_")) searchQuery = "SELECT * FROM customers_view;";
						else searchQuery = "SELECT * FROM customers_view WHERE LOWER(first_name) LIKE '%" + input[0] + "%' AND LOWER(last_name) LIKE '%" + input[1] + "%';";
					}
					else if(index == 1)
						searchQuery = "SELECT * FROM customers_view WHERE irs_number LIKE '" + input[0] + "%';";
					else if(index == 2)
						searchQuery = "SELECT * FROM customers_view WHERE social_security_number LIKE '" + input[0] + "%';";
					else if(index == 3)
						searchQuery = "SELECT * FROM customers_view WHERE driver_license LIKE '" + input[0] + "%';";
					else if(index == 4)
					{
						StringBuilder sb = new StringBuilder();
						sb.append("SELECT * FROM customers_view");
						
						if(!input[0].equals("_") || !input[1].equals("_") || !input[2].equals("_")) sb.append(" WHERE");
						
						if(!input[0].equals("_")) sb.append(" day(first_registration)='").append(input[0]).append("'");
						
						if(!input[0].equals("_") && !input[1].equals("_") || !input[0].equals("_") && input[1].equals("_") && !input[2].equals("_")) sb.append(" and");
						if(!input[1].equals("_")) sb.append(" month(first_registration)='").append(input[1]).append("'");
						
						if(!input[1].equals("_") && !input[2].equals("_")) sb.append(" and");
						if(!input[2].equals("_")) sb.append(" year(first_registration)='").append(input[2]).append("'");
						
						sb.append(";");
						
						System.out.println(sb.toString());
						
						searchQuery = sb.toString();
					}
					else
					{
						String q1, q2, q3, q4;
						if(!input[0].equals("_")) q1 = "SELECT * FROM customers_view WHERE LOWER(city) LIKE '%" + input[0] + "%' AND customer_id IN(";
						else q1 = "SELECT * FROM customers_view WHERE customer_id IN(";
						if(!input[1].equals("_")) q2 = "SELECT customer_id FROM customers_view WHERE LOWER(street) LIKE '%" + input[1] + "%' AND customer_id IN(";
						else q2 = "SELECT customer_id FROM customers_view WHERE customer_id IN(";
						if(!input[2].equals("_")) q3 = "SELECT customer_id FROM customers_view WHERE LOWER(street_number) LIKE '" + input[2] + "%' AND customer_id IN(";
						else q3 = "SELECT customer_id FROM customers_view WHERE customer_id IN(";
						if(!input[3].equals("_")) q4 = "SELECT customer_id FROM customers_view WHERE LOWER(postal_code) LIKE '" + input[3] + "%')));";
						else q4 = "SELECT customer_id FROM customers_view)));";
						
						searchQuery = q1 + q2 + q3 + q4;
					}
					
					ResultSet rs = searchStatement.executeQuery(searchQuery);
					
					customersObservableList.clear();
					while(rs.next())
					{
						int customer_id = rs.getInt("customer_id");
						String irs_number = String.valueOf(rs.getLong("irs_number"));
						String social_security_number = String.valueOf(rs.getInt("social_security_number"));
						String first_name = rs.getString("first_name");
						String last_name = rs.getString("last_name");
						String driver_license = String.valueOf(rs.getLong("driver_license"));
						String first_registration = rs.getTimestamp("first_registration").toLocalDateTime().format(dateTimeFormatter);
						String city = rs.getString("city");
						String postal_code = rs.getString("postal_code");
						String street = rs.getString("street");
						String street_number = rs.getString("street_number");
						
						Customer customer = new Customer(customer_id, irs_number, social_security_number, first_name, last_name,
								driver_license, first_registration, city, postal_code, street, street_number);
						
						customersObservableList.add(customer);
					}
					
					numOfCustomersLabel.setText("Found " + customersObservableList.size() + " customers");
					
					customersTable.refresh();
					
					rs.close();
					searchStatement.close();
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		else setupCustomersTab();
	}
	
	@FXML
	private void resetBatchReservesEdit()
	{
		try
		{
			batchReservesStatement = Main.getConn().createStatement();
			
			setupReservesTab();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@FXML
	private void resetBatchRentsEdit()
	{
		try
		{
			batchRentsStatement = Main.getConn().createStatement();
			
			setupRentsTab();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@FXML
	private void resetBatchCustomersEdit()
	{
		try
		{
			batchCustomersStatement = Main.getConn().createStatement();
			
			setupCustomersTab();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@FXML
	private void addNewReserve()
	{
		reserveAdd = true;
		reserveAddOrEdit = true;
		
		addReserveButton.setDisable(true);
		
		startDateReserveDatePicker.setValue(LocalDate.now());
		finishDateReserveDatePicker.setValue(LocalDate.now().plusDays(1));
		
		chooseCustomerReserveComboBox.setDisable(false);
		chooseLicensePlateReserveComboBox.setDisable(false);
		
		startLocationReservesTextfield.setText("");
		finishLocationReservesTextfield.setText("");
		
		chooseCustomerReserveComboBox.getSelectionModel().select(-1);
		chooseLicensePlateReserveComboBox.getSelectionModel().select(-1);
		
		chooseCustomerReservesObservableList.clear();
		chooseLicensePlateReservesObservableList.clear();
		
		try
		{
			Statement stm = Main.getConn().createStatement();
			String query = "select first_name, last_name, irs_number from customers_view;";
			
			ResultSet rs = stm.executeQuery(query);
			
			chooseCustomerReservesObservableList.clear();
			while(rs.next())
			{
				String text = rs.getString("first_name") + " " + rs.getString("last_name") + " " + rs.getLong("irs_number");
				chooseCustomerReservesObservableList.add(text);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		refreshLicensePlates(true);
		
		paidNoReserveRadioButton.setSelected(true);
		
		reserveEditGridPane.setVisible(true);
		submitReserveButton.setVisible(true);
		cancelReserveButton.setVisible(true);
	}
	
	@FXML
	private void addNewRent()
	{
		rentAdd = true;
		rentAddOrEdit = true;
		
		addRentButton.setDisable(true);
		
		finishDateRentDatePicker.setValue(LocalDate.now().plusDays(1));
		
		chooseCustomerRentComboBox.setDisable(false);
		chooseLicensePlateRentComboBox.setDisable(false);
		
		startLocationRentsTextfield.setText("");
		finishLocationRentsTextfield.setText("");
		
		returnStateRentsComboBox.getSelectionModel().select(-1);
		
		chooseCustomerRentComboBox.getSelectionModel().select(-1);
		chooseLicensePlateRentComboBox.getSelectionModel().select(-1);
		
		paymentAmountRentsTextfield.setText("");
		paymentMethodRentsTextfield.setText("");
		
		chooseCustomerRentsObservableList.clear();
		chooseLicensePlateRentsObservableList.clear();
		
		try
		{
			Statement stm = Main.getConn().createStatement();
			String query = "select first_name, last_name, irs_number from customers_view;";
			
			ResultSet rs = stm.executeQuery(query);
			
			chooseCustomerRentsObservableList.clear();
			while(rs.next())
			{
				String text = rs.getString("first_name") + " " + rs.getString("last_name") + " " + rs.getLong("irs_number");
				chooseCustomerRentsObservableList.add(text);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		refreshLicensePlates(false);
		
		rentEditGridPane.setVisible(true);
		submitRentButton.setVisible(true);
		cancelRentButton.setVisible(true);
	}
	
	private void refreshLicensePlates(boolean inReserves)
	{
		try
		{
			Statement stm = Main.getConn().createStatement();
			
			Date start, finish;
			if(inReserves)
			{
				start = Date.valueOf(startDateReserveDatePicker.getValue());
				finish = Date.valueOf(finishDateReserveDatePicker.getValue());
			}
			else
			{
				if(currentEditRentIndex != -1) start = Date.valueOf(LocalDate.parse(rentsObservableList.get(currentEditRentIndex).getStartDate(), dateFormatter));
				else start = Date.valueOf(LocalDate.now());
				
				finish = Date.valueOf(finishDateRentDatePicker.getValue());
			}
			
			String query = String.format("select a.plate, a.make, a.model from (select vehicles.license_plate as plate, vehicles.make as make, vehicles.model as model from vehicles " +
					"where vehicles.license_plate not in(select rents.license_plate from rents where " +
					"'%s' between rents.start_date and rents.finish_date or '%s' between rents.start_date and rents.finish_date or ('%s' <= rents.start_date and '%s' >= rents.finish_date))) a where a.plate not in(select reserves.license_plate from reserves where " +
					"'%s' between reserves.start_date and reserves.finish_date or '%s' between reserves.start_date and reserves.finish_date or ('%s' <= reserves.start_date and '%s' >= reserves.finish_date));", start, finish, start, finish, start, finish, start, finish);
			
			ResultSet rs = stm.executeQuery(query);
			
			if(inReserves)
			{
				chooseLicensePlateReservesObservableList.clear();
				while(rs.next())
				{
					String text = rs.getString("make") + " " + rs.getString("model") + " - " + rs.getString("plate");
					chooseLicensePlateReservesObservableList.add(text);
				}
			}
			else
			{
				chooseLicensePlateRentsObservableList.clear();
				while(rs.next())
				{
					String text = rs.getString("make") + " " + rs.getString("model") + " - " + rs.getString("plate");
					chooseLicensePlateRentsObservableList.add(text);
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@FXML
	private void addNewCustomer()
	{
		customerAdd = true;
		customerAddOrEdit = true;
		
		addCustomerButton.setDisable(true);
		
		firstNameCustomersTextfield.setText("");
		lastNameCustomersTextfield.setText("");
		irsNumberCustomersTextfield.setText("");
		socialSecurityNumberCustomersTextfield.setText("");
		driverLicenseCustomersTextfield.setText("");
		cityCustomersTextfield.setText("");
		postalCodeCustomersTextfield.setText("");
		streetCustomersTextfield.setText("");
		streetNumberCustomersTextfield.setText("");
		
		customerEditGridPane.setVisible(true);
		submitCustomerButton.setVisible(true);
		cancelCustomerButton.setVisible(true);
		
		firstNameCustomersTextfield.requestFocus();
	}
	
	private void editReserve(int index)
	{
		reserveAdd = false;
		reserveAddOrEdit = true;
		
		currentEditReserveIndex = index;
		
		reservesTable.refresh();
		
		startDateReserveDatePicker.setValue(LocalDate.parse(reservesObservableList.get(index).getStartDate(), dateFormatter));
		finishDateReserveDatePicker.setValue(LocalDate.parse(reservesObservableList.get(index).getFinishDate(), dateFormatter));
		
		
		chooseCustomerReservesObservableList.clear();
		chooseCustomerReservesObservableList.add(reservesObservableList.get(index).getCustomerName());
		
		chooseLicensePlateReservesObservableList.clear();
		
		try
		{
			Statement stm = Main.getConn().createStatement();
			
			String q = String.format("select license_plate, make, model from vehicles where license_plate='%s';", reservesObservableList.get(index).getLicensePlate());
			ResultSet rs = stm.executeQuery(q);
			
			rs.next();
			
			chooseLicensePlateReservesObservableList.add(rs.getString("make") + " " + rs.getString("model") + " - " + rs.getString("license_plate"));
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		chooseCustomerReserveComboBox.setDisable(true);
		chooseLicensePlateReserveComboBox.setDisable(true);
		chooseCustomerReserveComboBox.getSelectionModel().select(0);
		chooseLicensePlateReserveComboBox.getSelectionModel().select(0);
		
		startLocationReservesTextfield.setText(reservesObservableList.get(index).getStartLocation());
		finishLocationReservesTextfield.setText(reservesObservableList.get(index).getFinishLocation());
		
		if(reservesObservableList.get(index).getPaid()) paidYesReserveRadioButton.setSelected(true);
		else paidNoReserveRadioButton.setSelected(true);
		
		addReserveButton.setDisable(true);
		
		reserveEditGridPane.setVisible(true);
		submitReserveButton.setVisible(true);
		cancelReserveButton.setVisible(true);
	}
	
	private void editRent(int index)
	{
		rentAdd = false;
		rentAddOrEdit = true;
		
		currentEditRentIndex = index;
		
		rentsTable.refresh();
		
		finishDateRentDatePicker.setValue(LocalDate.parse(rentsObservableList.get(index).getFinishDate(), dateFormatter));
		
		chooseCustomerRentsObservableList.clear();
		chooseCustomerRentsObservableList.add(rentsObservableList.get(index).getCustomerName());
		
		chooseLicensePlateRentsObservableList.clear();
		
		try
		{
			Statement stm = Main.getConn().createStatement();
			
			String q = String.format("select license_plate, make, model from vehicles where license_plate='%s';", rentsObservableList.get(index).getLicensePlate());
			ResultSet rs = stm.executeQuery(q);
			
			rs.next();
			
			chooseLicensePlateRentsObservableList.add(rs.getString("make") + " " + rs.getString("model") + " - " + rs.getString("license_plate"));
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		chooseCustomerRentComboBox.setDisable(true);
		chooseLicensePlateRentComboBox.setDisable(true);
		chooseCustomerRentComboBox.getSelectionModel().select(0);
		chooseLicensePlateRentComboBox.getSelectionModel().select(0);
		
		paymentAmountRentsTextfield.setText(rentsObservableList.get(index).getpaymentDetails().split(" - ")[0]);
		paymentMethodRentsTextfield.setText(rentsObservableList.get(index).getpaymentDetails().split(" - ")[1]);
		
		startLocationRentsTextfield.setText(rentsObservableList.get(index).getStartLocation());
		finishLocationRentsTextfield.setText(rentsObservableList.get(index).getFinishLocation());
		
		if(rentsObservableList.get(index).getReturnState().equals(returnStateObservableList.get(0)))
			returnStateRentsComboBox.getSelectionModel().select(0);
		else if(rentsObservableList.get(index).getReturnState().equals(returnStateObservableList.get(1)))
			returnStateRentsComboBox.getSelectionModel().select(1);
		else
			returnStateRentsComboBox.getSelectionModel().select(2);
		
		addRentButton.setDisable(true);
		
		rentEditGridPane.setVisible(true);
		submitRentButton.setVisible(true);
		cancelRentButton.setVisible(true);
	}
	
	private void editCustomer(int index)
	{
		customerAdd = false;
		customerAddOrEdit = true;
		
		currentEditCustomerIndex = index;
		
		customersTable.refresh();
		
		addCustomerButton.setDisable(true);
		
		customerEditGridPane.setVisible(true);
		submitCustomerButton.setVisible(true);
		cancelCustomerButton.setVisible(true);
		
		firstNameCustomersTextfield.setText(customersObservableList.get(index).getFirstName());
		lastNameCustomersTextfield.setText(customersObservableList.get(index).getLastName());
		irsNumberCustomersTextfield.setText(customersObservableList.get(index).getIrsNumber());
		socialSecurityNumberCustomersTextfield.setText(customersObservableList.get(index).getSocialSecurityNumber());
		driverLicenseCustomersTextfield.setText(customersObservableList.get(index).getDriverLicense());
		cityCustomersTextfield.setText(customersObservableList.get(index).getCity());
		streetCustomersTextfield.setText(customersObservableList.get(index).getStreet());
		streetNumberCustomersTextfield.setText(customersObservableList.get(index).getStreetNumber());
		postalCodeCustomersTextfield.setText(customersObservableList.get(index).getPostalCode());
		
		firstNameCustomersTextfield.requestFocus();
	}
	
	private void deleteReserve(int index)
	{
		try
		{
			String deleteQuery = String.format("DELETE FROM reserves WHERE license_plate='%s' AND start_date='%s';",
					reservesObservableList.get(index).getLicensePlate(), Date.valueOf(LocalDate.parse(reservesObservableList.get(index).getStartDate(), dateFormatter)));
			
			batchReservesStatement.addBatch(deleteQuery);
			
			reservesObservableList.remove(index);
			
			reservesTable.refresh();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void deleteRent(int index)
	{
		try
		{
			String deleteQuery = String.format("DELETE FROM rents WHERE license_plate='%s' AND start_date='%s';",
					rentsObservableList.get(index).getLicensePlate(), Date.valueOf(LocalDate.parse(rentsObservableList.get(index).getStartDate(), dateFormatter)));
			batchRentsStatement.addBatch(deleteQuery);
			
			rentsObservableList.remove(index);
			
			rentsTable.refresh();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void deleteCustomer(int index)
	{
		try
		{
			String deleteQuery = "DELETE FROM customers_view WHERE irs_number='" + customersObservableList.get(index).getIrsNumber() + "';";
			
			batchCustomersStatement.addBatch(deleteQuery);
			
			customersObservableList.remove(index);
			
			customersTable.refresh();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@FXML
	private void closeReserveAddOrEditMenu()
	{
		reserveAddOrEdit = false;
		
		currentEditReserveIndex = -1;
		
		startDateReserveDatePicker.setValue(LocalDate.now());
		finishDateReserveDatePicker.setValue(LocalDate.now().plusDays(1));
		
		chooseCustomerReserveComboBox.setDisable(false);
		chooseLicensePlateReserveComboBox.setDisable(false);
		
		startLocationReservesTextfield.setText("");
		finishLocationReservesTextfield.setText("");
		
		chooseCustomerReserveComboBox.getSelectionModel().select(-1);
		chooseLicensePlateReserveComboBox.getSelectionModel().select(-1);
		
		paidNoReserveRadioButton.setSelected(true);
		
		reserveEditGridPane.setVisible(false);
		submitReserveButton.setVisible(false);
		cancelReserveButton.setVisible(false);
		
		addReserveButton.setDisable(false);
		
		reservesTable.refresh();
	}
	
	@FXML
	private void closeRentAddOrEditMenu()
	{
		rentAddOrEdit = false;
		
		currentEditRentIndex = -1;
		
		finishDateRentDatePicker.setValue(LocalDate.now().plusDays(1));
		
		chooseCustomerRentComboBox.setDisable(false);
		chooseLicensePlateRentComboBox.setDisable(false);
		
		startLocationRentsTextfield.setText("");
		finishLocationRentsTextfield.setText("");
		
		returnStateRentsComboBox.getSelectionModel().select(-1);
		
		chooseCustomerRentComboBox.getSelectionModel().select(-1);
		chooseLicensePlateRentComboBox.getSelectionModel().select(-1);
		
		paymentAmountRentsTextfield.setText("");
		paymentMethodRentsTextfield.setText("");
		
		rentEditGridPane.setVisible(false);
		submitRentButton.setVisible(false);
		cancelRentButton.setVisible(false);
		
		addRentButton.setDisable(false);
		
		rentsTable.refresh();
	}
	
	@FXML
	private void closeCustomerAddOrEditMenu()
	{
		customerAddOrEdit = false;
		
		currentEditCustomerIndex = -1;
		
		firstNameCustomersTextfield.setText("");
		lastNameCustomersTextfield.setText("");
		irsNumberCustomersTextfield.setText("");
		socialSecurityNumberCustomersTextfield.setText("");
		driverLicenseCustomersTextfield.setText("");
		cityCustomersTextfield.setText("");
		postalCodeCustomersTextfield.setText("");
		streetCustomersTextfield.setText("");
		streetNumberCustomersTextfield.setText("");
		
		customerEditGridPane.setVisible(false);
		submitCustomerButton.setVisible(false);
		cancelCustomerButton.setVisible(false);
		
		addCustomerButton.setDisable(false);
		
		customersTable.refresh();
	}
	
	@FXML
	private void submitReserve()
	{
		try
		{
			Statement stm = Main.getConn().createStatement();
			ResultSet rs;
			
			String vehicleName = chooseLicensePlateReserveComboBox.getSelectionModel().getSelectedItem();
			String[] temp = vehicleName.split(" ");
			String licensePLate = temp[temp.length - 1];
			
			temp = chooseCustomerReserveComboBox.getSelectionModel().getSelectedItem().split(" ");
			String temp2 = temp[temp.length - 1];
			String query = String.format("select customer_id, first_name, last_name from customers_view where irs_number='%s';", temp2);
			rs = stm.executeQuery(query);
			rs.next();
			
			Reserve reserve = new Reserve(startDateReserveDatePicker.getValue().format(dateFormatter), finishDateReserveDatePicker.getValue().format(dateFormatter),
					startLocationReservesTextfield.getText().trim(), finishLocationReservesTextfield.getText().trim(),
					paidYesReserveRadioButton.isSelected(), rs.getInt("customer_id"), rs.getString("first_name") + " " + rs.getString("last_name"),
					licensePLate, vehicleName);
			
			if(reserveAdd)
			{
				String sql = String.format("INSERT INTO reserves (license_plate, start_date, start_location, finish_location, finish_date, paid, customer_id) VALUES ('%s', '%s', '%s', '%s', '%s', %s, '%s');",
						reserve.getLicensePlate(), Date.valueOf(LocalDate.parse(reserve.getStartDate(), dateFormatter)), reserve.getStartLocation(),
						reserve.getFinishLocation(), Date.valueOf(LocalDate.parse(reserve.getFinishDate(), dateFormatter)), booleanToInteger(reserve.getPaid()), reserve.getCustomerID());
				
				batchReservesStatement.addBatch(sql);
				
				reservesObservableList.add(reserve);
			}
			else
			{
				String updateQuery = String.format("UPDATE reserves SET license_plate='%s', start_date='%s', start_location='%s', finish_location='%s', finish_date='%s', paid=%s, customer_id='%s' " +
								"WHERE license_plate='%s' and start_date='%s';",
						reserve.getLicensePlate(), Date.valueOf(LocalDate.parse(reserve.getStartDate(), dateFormatter)), reserve.getStartLocation(),
						reserve.getFinishLocation(), Date.valueOf(LocalDate.parse(reserve.getFinishDate(), dateFormatter)), booleanToInteger(reserve.getPaid()), reserve.getCustomerID(),
						reservesObservableList.get(currentEditReserveIndex).getLicensePlate(), Date.valueOf(LocalDate.parse(reservesObservableList.get(currentEditReserveIndex).getStartDate(), dateFormatter)));
				
				batchReservesStatement.addBatch(updateQuery);
				
				reservesObservableList.set(currentEditReserveIndex, reserve);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		closeReserveAddOrEditMenu();
	}
	
	@FXML
	private void submitRent()
	{
		try
		{
			Statement stm = Main.getConn().createStatement();
			ResultSet rs;

			String vehicleName = chooseLicensePlateRentComboBox.getSelectionModel().getSelectedItem();
			String[] temp = vehicleName.split(" ");
			String licensePLate = temp[temp.length - 1];

			temp = chooseCustomerRentComboBox.getSelectionModel().getSelectedItem().split(" ");
			String temp2 = temp[temp.length - 1];
			String query = String.format("select customer_id, first_name, last_name from customers_view where irs_number='%s';", temp2);
			rs = stm.executeQuery(query);
			rs.next();
			
			String start;
			
			if(currentEditRentIndex != -1) start = rentsObservableList.get(currentEditRentIndex).getStartDate();
			else start = LocalDate.now().format(dateFormatter);
			
			Rent rent = new Rent(start, finishDateRentDatePicker.getValue().format(dateFormatter),
					startLocationRentsTextfield.getText().trim(), finishLocationRentsTextfield.getText().trim(), returnStateRentsComboBox.getSelectionModel().getSelectedItem(),
					rs.getInt("customer_id"), rs.getString("first_name") + " " + rs.getString("last_name"),
					licensePLate, vehicleName, currentEmployeeIRSNumber, currentEmployeeName + " - " + currentEmployeeIRSNumber,
					paymentAmountRentsTextfield.getText().trim() + " - " + paymentMethodRentsTextfield.getText().trim());

			if(rentAdd)
			{
				String sql = String.format("INSERT INTO rents (start_date, finish_date, start_location, finish_location, return_state, customer_id, license_plate, irs_number) VALUES ('%s', '%s', '%s', '%s', '%s', %s, '%s', '%s');",
						Date.valueOf(LocalDate.parse(rent.getStartDate(), dateFormatter)), Date.valueOf(LocalDate.parse(rent.getFinishDate(), dateFormatter)), rent.getStartLocation(),
						rent.getFinishLocation(),  rent.getReturnState(), rent.getCustomerID(), rent.getLicensePlate(), rent.getEmployeeIRSNumber());
				batchRentsStatement.addBatch(sql);
				
				
				sql = String.format("INSERT INTO payment_transaction (license_plate, start_date, payment_amount, payment_method) VALUES ('%s', '%s', '%s', '%s');", rent.getLicensePlate(),
						Date.valueOf(LocalDate.parse(rent.getStartDate(), dateFormatter)), paymentAmountRentsTextfield.getText().trim(), paymentMethodRentsTextfield.getText().trim());
				batchRentsStatement.addBatch(sql);
				
				rentsObservableList.add(rent);
			}
			else
			{
				String updateQuery = String.format("UPDATE rents SET license_plate='%s', start_date='%s', start_location='%s', " +
								"finish_location='%s', finish_date='%s', return_state='%s', customer_id='%s', irs_number='%s' " +
								"WHERE license_plate='%s' and start_date='%s';",
						rent.getLicensePlate(), Date.valueOf(LocalDate.parse(rent.getStartDate(), dateFormatter)), rent.getStartLocation(),
						rent.getFinishLocation(), Date.valueOf(LocalDate.parse(rent.getFinishDate(), dateFormatter)), rent.getReturnState(), rent.getCustomerID(), rent.getEmployeeIRSNumber(),
						rentsObservableList.get(currentEditRentIndex).getLicensePlate(), Date.valueOf(LocalDate.parse(rentsObservableList.get(currentEditRentIndex).getStartDate(), dateFormatter)));
				batchRentsStatement.addBatch(updateQuery);
				
				updateQuery = String.format("UPDATE payment_transaction SET license_plate='%s', start_date='%s', payment_amount='%s', payment_method='%s'" +
						" WHERE license_plate='%s' and start_date='%s';",
						rent.getLicensePlate(), Date.valueOf(LocalDate.parse(rent.getStartDate(), dateFormatter)), paymentAmountRentsTextfield.getText().trim(), paymentMethodRentsTextfield.getText().trim(),
						rent.getLicensePlate(), Date.valueOf(LocalDate.parse(rent.getStartDate(), dateFormatter)));
				batchRentsStatement.addBatch(updateQuery);

				rentsObservableList.set(currentEditRentIndex, rent);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		closeRentAddOrEditMenu();
	}
	
	@FXML
	private void submitCustomer()
	{
		if(customerAdd)
		{
			LocalDateTime currentTime = LocalDateTime.now();
			Timestamp sqlTimestamp = Timestamp.valueOf(currentTime);
			
			Customer customer = new Customer(irsNumberCustomersTextfield.getText().trim(), socialSecurityNumberCustomersTextfield.getText().trim(),
					firstNameCustomersTextfield.getText().trim(), lastNameCustomersTextfield.getText().trim(), driverLicenseCustomersTextfield.getText().trim(),
					currentTime.format(dateTimeFormatter).trim(), cityCustomersTextfield.getText().trim(), postalCodeCustomersTextfield.getText().trim(), streetCustomersTextfield.getText().trim(), streetNumberCustomersTextfield.getText().trim());
			
			try
			{
				Statement checkCustomerStatement = Main.getConn().createStatement();
				
				String checkIfCustomerExists = "SELECT irs_number, social_security_number, driver_license FROM customers_view " +
						"WHERE irs_number='" + irsNumberCustomersTextfield.getText().trim() +
						"' OR social_security_number='" + socialSecurityNumberCustomersTextfield.getText().trim() +
						"' OR driver_license='" + driverLicenseCustomersTextfield.getText().trim() + "';";
				
				ResultSet rs = checkCustomerStatement.executeQuery(checkIfCustomerExists);
				if(rs.next()) throw new CustomerAlreadyExistsException();
				
				String sql = String.format("INSERT INTO customers_view (irs_number, social_security_number, first_name, last_name, driver_license, " +
						"first_registration, city, postal_code, street, street_number) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');",
						irsNumberCustomersTextfield.getText().trim(), socialSecurityNumberCustomersTextfield.getText().trim(), firstNameCustomersTextfield.getText().trim(), lastNameCustomersTextfield.getText().trim(),
						driverLicenseCustomersTextfield.getText().trim(), sqlTimestamp.toString().trim(), cityCustomersTextfield.getText().trim(), postalCodeCustomersTextfield.getText().trim(),
						streetCustomersTextfield.getText().trim(), streetNumberCustomersTextfield.getText().trim());
				
				batchCustomersStatement.addBatch(sql);
				
				customersObservableList.add(customer);
			}
			catch(CustomerAlreadyExistsException e)
			{
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText("One of IRS number or social Security number or driver license already exists in the database.");
				
				alert.showAndWait();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			try
			{
				String updateQuery = String.format("UPDATE customers_view SET irs_number='%s', social_security_number='%s'," +
								" first_name='%s', last_name='%s', driver_license='%s', city='%s', postal_code='%s', street='%s', street_number='%s' WHERE irs_number='%s';",
						irsNumberCustomersTextfield.getText().trim(), socialSecurityNumberCustomersTextfield.getText().trim(), firstNameCustomersTextfield.getText().trim(), lastNameCustomersTextfield.getText().trim(),
						driverLicenseCustomersTextfield.getText().trim(), cityCustomersTextfield.getText().trim(), postalCodeCustomersTextfield.getText().trim(),
						streetCustomersTextfield.getText().trim(), streetNumberCustomersTextfield.getText().trim(), customersObservableList.get(currentEditCustomerIndex).getIrsNumber().trim());
				
				batchCustomersStatement.addBatch(updateQuery);
				
				customersObservableList.get(currentEditCustomerIndex).setIrsNumber(irsNumberCustomersTextfield.getText().trim());
				customersObservableList.get(currentEditCustomerIndex).setSocialSecurityNumber(socialSecurityNumberCustomersTextfield.getText().trim());
				customersObservableList.get(currentEditCustomerIndex).setFirstName(firstNameCustomersTextfield.getText().trim());
				customersObservableList.get(currentEditCustomerIndex).setLastName(lastNameCustomersTextfield.getText().trim());
				customersObservableList.get(currentEditCustomerIndex).setDriverLicense(driverLicenseCustomersTextfield.getText().trim());
				customersObservableList.get(currentEditCustomerIndex).setCity(cityCustomersTextfield.getText().trim());
				customersObservableList.get(currentEditCustomerIndex).setPostalCode(postalCodeCustomersTextfield.getText().trim());
				customersObservableList.get(currentEditCustomerIndex).setStreet(streetCustomersTextfield.getText().trim());
				customersObservableList.get(currentEditCustomerIndex).setStreetNumber(streetNumberCustomersTextfield.getText().trim());
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		closeCustomerAddOrEditMenu();
	}
	
	@FXML
	private void runBatchReservesStatement()
	{
		try
		{
			batchReservesStatement.executeBatch();
			
			setupReservesTab();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@FXML
	private void runBatchRentsStatement()
	{
		try
		{
			batchRentsStatement.executeBatch();
			
			setupRentsTab();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@FXML
	private void runBatchCustomersStatement()
	{
		try
		{
			batchCustomersStatement.executeBatch();
			
			setupCustomersTab();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@FXML
	private void setupReservesTab()
	{
		Statement stmt = null;
		
		addReserveButton.setDisable(false);
		
		searchReservesTextfield.setText("");
		
		try
		{
			stmt = Main.getConn().createStatement();
			String sql = "select * from reserves_with_customers_and_vehicles;";
			ResultSet rs = stmt.executeQuery(sql);
			
			reservesObservableList.clear();
			while(rs.next())
			{
				String start_date = rs.getDate("start_date").toLocalDate().format(dateFormatter);
				String finish_date = rs.getDate("finish_date").toLocalDate().format(dateFormatter);
				String start_location = rs.getString("start_location");
				String finish_location = rs.getString("finish_location");
				boolean paid = rs.getBoolean("paid");
				
				int customer_id = rs.getInt("customer_id");
				String customer_name = rs.getString("customer_first_name") + " " + rs.getString("customer_last_name") + " - " + rs.getString("customer_irs_number");
				
				String license_plate = rs.getString("vehicle_license_plate");
				String vehicle_name = rs.getString("vehicle_make") + " " + rs.getString("vehicle_model") + " - " + license_plate;
				
				Reserve reserve = new Reserve(start_date, finish_date, start_location, finish_location, paid, customer_id, customer_name, license_plate, vehicle_name);
				
				reservesObservableList.add(reserve);
			}
			
			numOfReservesLabel.setText("No. of reserves: " + reservesObservableList.size());
			
			rs.close();
			stmt.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(stmt != null)
					stmt.close();
			}
			catch(SQLException se2) {}// nothing we can do
		}
	}
	
	@FXML
	private void setupRentsTab()
	{
		Statement stmt = null;
		
		addRentButton.setDisable(false);
		
		searchRentsTextfield.setText("");
		
		try
		{
			stmt = Main.getConn().createStatement();
			
			int index = showHistoryOrCurrentRentsComboBox.getSelectionModel().getSelectedIndex();
			String which;
			if(index == 0) which = "";
			else if(index == 1) which = " where finish_date >= CURDATE()";
			else which = " where finish_date < CURDATE()";
			
			index = chooseOrderRentsComboBox.getSelectionModel().getSelectedIndex();
			String order;
			if(index == 0) order = "start_date";
			else if(index == 1) order = "finish_date";
			else if(index == 2) order = "customer_last_name";
			else if(index == 3) order = "vehicle_make";
			else if(index == 4) order = "start_location";
			else if(index == 5) order = "finish_location";
			else if(index == 6) order = "employee_last_name";
			else order = "return_state";
			
			String sql = String.format("select * from rents_with_payments_customers_vehicles_and_employees %s order by %s;", which, order);
			ResultSet rs = stmt.executeQuery(sql);
			
			rentsObservableList.clear();
			while(rs.next())
			{
				String start_date = rs.getDate("start_date").toLocalDate().format(dateFormatter);
				String finish_date = rs.getDate("finish_date").toLocalDate().format(dateFormatter);
				String start_location = rs.getString("start_location");
				String finish_location = rs.getString("finish_location");
				String return_state = rs.getString("return_state");
				
				int customer_id = rs.getInt("customer_id");
				String customer_name = rs.getString("customer_first_name") + " " + rs.getString("customer_last_name") + " - " + rs.getString("customer_irs_number");
				
				String license_plate = rs.getString("vehicle_license_plate");
				String vehicle_name = rs.getString("vehicle_name") + " - " + license_plate;
				
				long irs_number = rs.getLong("employee_irs_number");
				String employee_name = rs.getString("employee_first_name") + " " + rs.getString("employee_last_name") + " - " + irs_number;
				
				String payment_details = String.valueOf(rs.getBigDecimal("amount")) + " - " + rs.getString("method");
				
				Rent rent = new Rent(start_date, finish_date, start_location, finish_location, return_state, customer_id, customer_name, license_plate, vehicle_name, irs_number, employee_name, payment_details);
				
				rentsObservableList.add(rent);
			}
			
			numOfRentsLabel.setText("No. of rents: " + rentsObservableList.size());
			
			rs.close();
			stmt.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(stmt != null)
					stmt.close();
			}
			catch(SQLException se2) {}// nothing we can do
		}
	}
	
	private void setupEmployeesTab()
	{
		Statement stmt = null;
		
		try
		{
			stmt = Main.getConn().createStatement();
			String sql = "SELECT * FROM employees";
			ResultSet rs = stmt.executeQuery(sql);
			
			employeesObservableList.clear();
			while(rs.next())
			{
				String irs_number = String.valueOf(rs.getLong("irs_number"));
				String social_security_number = String.valueOf(rs.getInt("social_security_number"));
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String driver_license = String.valueOf(rs.getLong("driver_license"));
				String city = rs.getString("city");
				String postal_code = rs.getString("postal_code");
				String street = rs.getString("street");
				String street_number = rs.getString("street_number");
				
				Employee employee = new Employee(first_name, last_name, irs_number, social_security_number, 
						driver_license, city, postal_code, street, street_number);
				
				employeesObservableList.add(employee);
			}
			
			Statement numOfEmployeesStatement = Main.getConn().createStatement();
			
			String numOfEmployeesQuery = "select count(irs_number) as totalEmployees from employees;";
			rs = numOfEmployeesStatement.executeQuery(numOfEmployeesQuery);
			
			if(rs.next())
				numOfEmployeesLabel.setText("No. of employees: " + rs.getInt("totalEmployees"));
			else numOfEmployeesLabel.setText("No. of employees: 0");
			
			rs.close();
			stmt.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(stmt != null)
					stmt.close();
			}
			catch(SQLException se2) {}// nothing we can do
		}
	}
	
	private void setupEmploymentDataTab()
	{
		Statement stmt = null;
		
		try
		{
			stmt = Main.getConn().createStatement();
			String sql = "SELECT * FROM employment_data";
			ResultSet rs = stmt.executeQuery(sql);
			
			employmentDataObservableList.clear();
			while(rs.next())
			{
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String irs_number = String.valueOf(rs.getLong("irs_number"));
				String start_date = rs.getDate("start_date").toLocalDate().format(dateFormatter);
				String finish_date = rs.getDate("finish_date").toLocalDate().format(dateFormatter);
				String position = rs.getString("position");
				String city = rs.getString("city");
				
				EmploymentData employmentData = new EmploymentData(first_name, last_name, irs_number, start_date,
						finish_date, position, city);
				
				employmentDataObservableList.add(employmentData);
			}
			
			numOfEmploymentDataLabel.setText("No. of employees: " + employmentDataObservableList.size());
			
			rs.close();
			stmt.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(stmt != null)
					stmt.close();
			}
			catch(SQLException se2) {}// nothing we can do
		}
	}
	
	@FXML
	private void setupCustomersTab()
	{
		Statement stmt = null;
		
		addCustomerButton.setDisable(false);
		searchCustomersTextfield.setText("");
		
		try
		{
			stmt = Main.getConn().createStatement();
			String sql = "SELECT * FROM customers_view;";
			ResultSet rs = stmt.executeQuery(sql);
			
			customersObservableList.clear();
			while(rs.next())
			{
				String irs_number = String.valueOf(rs.getLong("irs_number"));
				String social_security_number = String.valueOf(rs.getInt("social_security_number"));
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String driver_license = String.valueOf(rs.getLong("driver_license"));
				String first_registration = rs.getTimestamp("first_registration").toLocalDateTime().format(dateTimeFormatter);
				String city = rs.getString("city");
				String postal_code = rs.getString("postal_code");
				String street = rs.getString("street");
				String street_number = rs.getString("street_number");
				
				Customer customer = new Customer(irs_number, social_security_number, first_name, last_name,
						driver_license, first_registration, city, postal_code, street, street_number);
				
				customersObservableList.add(customer);
			}
			
			Statement numOfCustomersStatement = Main.getConn().createStatement();
			
			String numOfCustomersQuery = "select count(irs_number) as totalCustomers from customers_view;";
			rs = numOfCustomersStatement.executeQuery(numOfCustomersQuery);
			
			if(rs.next())
				numOfCustomersLabel.setText("No. of customers: " + rs.getInt("totalCustomers"));
			else numOfCustomersLabel.setText("No. of customers: 0");
			
			sql = "select concat(customer_first_name, \" \", customer_last_name) as customer_name, count(customer_id) as counter, return_state\n" +
					"from `rents_with_payments_customers_vehicles_and_employees` \n" +
					"group by customer_id \n" +
					"having return_state='Perfect' \n" +
					"order by counter desc;";
			
			Statement st1 = Main.getConn().createStatement();
			ResultSet rs1 = st1.executeQuery(sql);
			
			int i = 1;
			StringBuilder sb = new StringBuilder();
			while(rs1.next())
			{
				sb.append(rs1.getString("customer_name"));
				if(i < 3) sb.append(", ");
				
				i++;
				if(i == 4) break;
			}
			top3CustomersLabel.setText("Top 3 customers: " + sb.toString());
			rs1.close();
			st1.close();
			
			rs.close();
			stmt.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(stmt != null)
					stmt.close();
			}
			catch(SQLException se2) {}// nothing we can do
		}
	}
	
	private void setupStoresTab()
	{
		Statement stmt = null;
		
		try
		{
			stmt = Main.getConn().createStatement();
			String sql = "SELECT * FROM stores";
			ResultSet rs = stmt.executeQuery(sql);
			
			storesObservableList.clear();
			while(rs.next())
			{
				int store_id = rs.getInt("store_id");
				String city = rs.getString("city");
				String postal_code = rs.getString("postal_code");
				String street = rs.getString("street");
				String street_number = rs.getString("street_number");
				
				Statement st1 = Main.getConn().createStatement();
				sql = String.format("SELECT phone_number FROM phone_numbers_stores where store_id='%d';", store_id);
				ResultSet rs1 = st1.executeQuery(sql);
				
				List<String> phoneNumbers = new ArrayList<>();
				while(rs1.next()) phoneNumbers.add(rs1.getString("phone_number"));
				
				rs1.close();
				
				sql = String.format("SELECT email_address FROM email_addresses_stores where store_id='%d';", store_id);
				rs1 = st1.executeQuery(sql);
				
				List<String> emailAddresses = new ArrayList<>();
				while(rs1.next()) emailAddresses.add(rs1.getString("email_address"));
				
				rs1.close();
				st1.close();
				
				Store store = new Store(store_id, city, postal_code, street, street_number, phoneNumbers.toArray(new String[0]), emailAddresses.toArray(new String[0]));
				
				storesObservableList.add(store);
			}
			
			numOfStoresLabel.setText("No. of stores: " + storesObservableList.size());
			
			rs.close();
			stmt.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(stmt != null)
					stmt.close();
			}
			catch(SQLException se2) {}// nothing we can do
		}
	}
	
	private void setupVehiclesTab()
	{
		Statement stmt = null;
		
		try
		{
			stmt = Main.getConn().createStatement();
			String sql = "SELECT * FROM vehicles_view";
			ResultSet rs = stmt.executeQuery(sql);
			
			vehiclesObservableList.clear();
			while(rs.next())
			{
				String make = rs.getString("make");
				String model = rs.getString("model");
				String license_plate = rs.getString("license_plate");
				String type = rs.getString("type");
				String year = rs.getString("year");
				String cylinder_capacity = rs.getString("cylinder_capacity");
				String horse_power = rs.getString("horse_power");
				String kilometers = rs.getString("kilometers");
				String damages = rs.getString("damages");
				String malfunctions = rs.getString("malfunctions");
				String last_service = rs.getString("last_service");
				String next_service = rs.getString("next_service");
				String insurance_exp_date = rs.getString("insurance_exp_date");
				String city = rs.getString("city");
				
				Vehicle vehicle = new Vehicle(make, model, license_plate, type, year,
						cylinder_capacity, horse_power, kilometers, damages, malfunctions,
						last_service, next_service, insurance_exp_date, city);
				
				vehiclesObservableList.add(vehicle);
			}
			
			numOfVehiclesLabel.setText("No. of vehicles: " + vehiclesObservableList.size());
			
			sql = "select rents_with_payments_customers_vehicles_and_employees.vehicle_name, \n" +
					"count(rents_with_payments_customers_vehicles_and_employees.vehicle_name) as counter \n" +
					"from rents_with_payments_customers_vehicles_and_employees \n" +
					"group by rents_with_payments_customers_vehicles_and_employees.vehicle_name \n" +
					"order by counter desc;";
			
			Statement st1 = Main.getConn().createStatement();
			ResultSet rs1 = st1.executeQuery(sql);
			
			int i = 1;
			StringBuilder sb = new StringBuilder();
			while(rs1.next())
			{
				sb.append(rs1.getString("vehicle_name"));
				if(i < 3) sb.append(", ");
				
				i++;
				if(i == 4) break;
			}
			mostUsedVehiclesLabel.setText("Top 3 most used vehicles: " + sb.toString());
			rs1.close();
			st1.close();
			
			rs.close();
			stmt.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(stmt != null)
					stmt.close();
			}
			catch(SQLException se2) {}// nothing we can do
		}
	}
	
	private boolean isLong(String s)
	{
		try
		{
			Long.parseLong(s);
		}
		catch(NumberFormatException | NullPointerException e)
		{
			return false;
		}
		// only got here if we didn't return false
		return true;
	}
	
	private boolean isDouble(String s)
	{
		try
		{
			Double.parseDouble(s);
		}
		catch(NumberFormatException | NullPointerException e)
		{
			return false;
		}
		// only got here if we didn't return false
		return true;
	}
	
	private int booleanToInteger(boolean b)
	{
		if(b) return 1;
		else return 0;
	}
	
	public void setCurrentEmployeeIRSNumber(long currentEmployeeIRSNumber, String currentUsername)
	{
		this.currentEmployeeIRSNumber = currentEmployeeIRSNumber;
		this.currentUsername = currentUsername;
		
		if(!currentUsername.equals("admin"))
		{
			tabPane.getTabs().removeAll(employeesTab, employmentDataTab);
		}
		
		try
		{
			Statement stm = Main.getConn().createStatement();
			ResultSet rs;
			
			String query = String.format("select first_name, last_name from employees where irs_number='%s';", currentEmployeeIRSNumber);
			rs = stm.executeQuery(query);
			rs.next();
			currentEmployeeName = rs.getString("first_name") + " " + rs.getString("last_name");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

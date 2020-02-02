package carRentalSystem;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class LoginScreenController
{
	@FXML private AnchorPane anchorPane;
	@FXML private TextField usernameTextfield;
	@FXML private PasswordField passwordField;
	@FXML private Button loginButton;
	
	private Image backgroundColorsImage;
	private PixelReader pr;
	private int backgroundColorProgress;
	
	private MainScreenController mainScreenController;
	
	@FXML
	public void initialize()
	{
		BooleanBinding bb = new BooleanBinding()
		{
			{
				super.bind(usernameTextfield.textProperty(), passwordField.textProperty());
			}
			
			@Override
			protected boolean computeValue()
			{
				return (usernameTextfield.getText().isEmpty() || passwordField.getText().isEmpty());
			}
		};
		
		loginButton.disableProperty().bind(bb);
		
		loginButton.setOnAction((a) -> login());
		
		usernameTextfield.setOnAction((a) ->
		{
			if(!loginButton.isDisable()) login();
		});
		
		passwordField.setOnAction((a) ->
		{
			if(!loginButton.isDisable()) login();
		});
		
		backgroundColorsImage = new Image("/resources/images/bgColors.jpg");
		pr = backgroundColorsImage.getPixelReader();
		backgroundColorProgress = new Random().nextInt(1800) + 2400;
		anchorPane.setStyle("-fx-background-color: #" + pr.getColor(backgroundColorProgress, 0).toString().replace("0x", "") + ";");
	}
	
	private void login()
	{
		Statement stm = null;
		ResultSet rs = null;
		try
		{
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			
			byte[] hash = digest.digest(passwordField.getText().getBytes(StandardCharsets.UTF_8));
			String hex = bytesToHex(hash);
			
			stm = Main.getConn().createStatement();
			
			String query = String.format("select * from login_data where username='%s';", usernameTextfield.getText());
			
			rs = stm.executeQuery(query);
			
			boolean validLogin = false;
			long irsNumber = -1;
			while(rs.next())
			{
				irsNumber = rs.getLong("irs_number");
				String password = rs.getString("password");
				
				if(password.equals(hex)) validLogin = true;
			}
			
			if(validLogin)
			{
				mainScreenController.setCurrentEmployeeIRSNumber(irsNumber, usernameTextfield.getText());
				
				Main.getStage().setX(Main.getPrimaryScreenBounds().getWidth() / 2.0 - Main.getMainScene().getWidth() / 2.0);
				Main.getStage().setY(Main.getPrimaryScreenBounds().getHeight() / 2.0 - Main.getMainScene().getHeight() / 2.0);
				Main.getStage().setScene(Main.getMainScene());
			}
			else
			{
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText("Invalid login!!! Try again...");
				
				alert.showAndWait();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(stm != null)
					stm.close();
			}
			catch(SQLException se2) {se2.printStackTrace();}
			
			try
			{
				if(rs != null)
					rs.close();
			}
			catch(SQLException se2) {se2.printStackTrace();}
		}
		
		usernameTextfield.requestFocus();
	}
	
	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
	public static String bytesToHex(byte[] bytes)
	{
		char[] hexChars = new char[bytes.length * 2];
		for(int j = 0; j < bytes.length; j++ )
		{
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}
	
	public void setMainScreenController(MainScreenController mainScreenController)
	{
		this.mainScreenController = mainScreenController;
	}
}

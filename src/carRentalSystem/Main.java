package carRentalSystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main extends Application
{
	static ShutdownHook shutdownHook;
	static Connection conn = null;
	
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/rent_a_car_schema?autoReconnect=true&useSSL=false";
	
	//  Database credentials
	static final String USER = "admin";
	static final String PASS = "admin";
	
	private static Scene mainScene;
	private static Stage stage;
	
	private MainScreenController mainScreenController;
	private LoginScreenController loginScreenController;
	
	private static Rectangle2D primaryScreenBounds;
	
	private String styleSheet;
	
    @Override
    public void start(Stage stage) throws Exception
	{
		this.stage = stage;
		
		primaryScreenBounds = Screen.getPrimary().getBounds();
		
		FXMLLoader loginScreenLoader = new FXMLLoader(Main.class.getResource("LoginScreen.fxml"));
		Parent loginScreenRoot = loginScreenLoader.load();
		
		FXMLLoader mainScreenLoader = new FXMLLoader(Main.class.getResource("MainScreen.fxml"));
		Parent mainScreenRoot = mainScreenLoader.load();
		mainScreenController = mainScreenLoader.getController();
		
		Scene loginScene = new Scene(loginScreenRoot, 600, 300);
		mainScene = new Scene(mainScreenRoot, 1600, 1000);
		loginScreenController = loginScreenLoader.getController();
		
		loginScreenController.setMainScreenController(mainScreenController);
		
		stage.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() / 2.0 - loginScene.getWidth() / 2.0);
		stage.setY(primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() / 2.0 - loginScene.getHeight() / 2.0);
		
		styleSheet = Main.class.getResource("/resources/css/styles.css").toExternalForm();
		mainScene.getStylesheets().add(styleSheet);
		
		stage.setTitle("Car Rental System");
		stage.setScene(loginScene);
		stage.setResizable(false);
		stage.getIcons().addAll(
				new Image("/resources/images/icon_128.png"),
				new Image("/resources/images/icon_64.png"),
				new Image("/resources/images/icon_32.png"),
				new Image("/resources/images/icon_16.png"));
		stage.show();
    }

    public static void main(String[] args)
    {
		try
		{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			
			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		shutdownHook = new ShutdownHook();
		Runtime.getRuntime().addShutdownHook(shutdownHook);
		
		launch(args);
    }
	
	public static Rectangle2D getPrimaryScreenBounds()
	{
		return primaryScreenBounds;
	}
	
	public static Stage getStage()
	{
		return stage;
	}
	
	public static Scene getMainScene()
	{
		return mainScene;
	}
	
	public static Connection getConn()
	{
		return conn;
	}
}

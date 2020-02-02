package carRentalSystem;

import java.sql.SQLException;

//helper class to execute some last stuff needed before exiting the game that starts being executed before the game is closed
public class ShutdownHook extends Thread
{
	public void run()
	{
		try
		{
			Main.getConn().close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}
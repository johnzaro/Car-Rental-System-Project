package carRentalSystem;

import javafx.scene.control.TextField;

public class CustomTextField extends TextField
{
	public CustomTextField()
	{
		super();
		
		textProperty().addListener((observable, oldValue, newValue) ->
		{
			if(newValue != null && (newValue.contains("\"") || newValue.contains("'")))
			{
				setText(newValue.replaceAll("\"", "").replaceAll("'", ""));
				requestFocus();
			}
		});
	}
}

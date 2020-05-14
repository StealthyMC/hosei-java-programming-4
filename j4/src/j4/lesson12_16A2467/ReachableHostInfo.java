package j4.lesson12_16A2467;

import java.io.IOException;
import java.net.InetAddress;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReachableHostInfo extends Application
{
	// Labels for the TextField objects
	Label[] label = {new Label("Input Website"), new Label("Host Name"),
			new Label("Canonical Host Name"), new Label("IP Address"),
			new Label("")};
	// Create areas for input/output
	TextField[] field = new TextField[4];
	// Create VBox to store fields and their labels
	VBox vboxFields = new VBox();
	
	// Search button
	Button btnSearch = new Button("Search");
	
	public static void main(String args[])
	{
		launch(args);
	}
	
	public void start(Stage stage) throws IOException
	{
		// Add field labels and fields to the VBox
		for (int i = 0; i < field.length; i++)
		{
			vboxFields.getChildren().add(label[i]);
			
			field[i] = new TextField("");
			vboxFields.getChildren().add(field[i]);
		}
		// Add final label (the error message)
		vboxFields.getChildren().add(label[label.length - 1]);
		// Give listener to the input field
		field[0].setOnAction(new HostInfoEventHandler());
		
		BorderPane bp = new BorderPane();
		bp.setCenter(vboxFields);
		bp.setBottom(btnSearch);
		
		
		// configure scene
		Scene sc = new Scene(bp, 400, 300);
		stage.setScene(sc);
		stage.setTitle("ReachableHostInfo");
		stage.show();
	}
	
	class HostInfoEventHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			try
			{
				label[label.length - 1].setText("");
				InetAddress ia = InetAddress.getByName(field[0].getText());
				field[1].setText(ia.getHostName());
				field[2].setText(ia.getCanonicalHostName());
				field[3].setText(ia.getHostAddress());
			}
			catch(Exception ex)
			{
				label[label.length - 1].setText("Host port isn't reachable!");
				// Wipe all fields
				for (int i = 1; i < field.length; i++)
					field[i].setText("");
				ex.printStackTrace();
			}
		}
	}
}

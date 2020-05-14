package j4.lesson11_16A2467;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.RoundingMode;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CalculateGPA extends Application
{
	// Components for top bar
	private Label label = new Label("Read CSV");
	private Button[] button = {new Button("Read"), 
			new Button("Calculate GPA")};
	private TextField csvField = new TextField("");
	
	// Text area used for showing information
	private TextArea display = new TextArea("");
	
	// Control whether or not to allow "Calculate GPA" option
	private Boolean allow = false;
	
	public static void main(String args[])
	{
		launch(args);
	}
	
	public void start(Stage stage) throws Exception
	{
		// Create border pane
		BorderPane bp = new BorderPane();
		
		// Set listeners for buttons
		for (Button i: button)
			i.setOnAction(new ActionEventHandler());
		
		
		// Create top row for buttons and text field
		HBox actionBar = new HBox();
		actionBar.setSpacing(10);
		// Add label and CSV filename field
		actionBar.getChildren().add(label);
		actionBar.getChildren().add(csvField);
		// Add buttons to top row
		for (Button i : button)
			actionBar.getChildren().add(i);
		// Add top row to main pane
		bp.setTop(actionBar);
		
		// Add display area to main pane
		bp.setCenter(display);
		
		Scene scene = new Scene(bp, 500, 330);
		stage.setScene(scene);
		stage.setTitle("Calculate GPA");
		stage.show();
	}
	
	class ActionEventHandler implements EventHandler<ActionEvent>
	{
		FileChooser fc = new FileChooser();
		public void handle(ActionEvent e)
		{
			// "Read" button
			if (e.getSource() == button[0])
			{
				readAndDisplay(fc);
			}
			
			// "Calculate GPA" button
			else if (e.getSource() == button[1])
			{
				// Don't attempt to calculate if no data is present
				if (!allow)
					return;
				// Otherwise, continue.
				
				// Create ArrayList to store entries from CSV output
				ArrayList<String[]> csvData = new ArrayList<String[]>();
				
				for (String line : display.getText().split("\\n"))
				{
					// Split each entry at the commas
					csvData.add(line.split(","));
				}
				String[][] data = new String[csvData.size()][];
				for (int i = 0; i < csvData.size(); i++)
				{
					data[i] = csvData.get(i);
				}
				
				DecimalFormat df = new DecimalFormat("#.##");
				df.setRoundingMode(RoundingMode.CEILING);
				
				display.appendText("\nGPA: " + df.format(gpaCalc(data)));	
				allow = false;
			}
		}
		
		private void readAndDisplay(FileChooser fc)
		{
			// Open file window, set to working directory
			String workingDir = Paths.get(".").toAbsolutePath().normalize().toString();
			fc.setInitialDirectory(new File(workingDir));
			try		// attempt to open file
			{
				csvField.clear();
				display.clear();
				
				File flr = fc.showOpenDialog(new Stage());	// create open dialog
				if (flr != null)	// if opening the dialog was a success, continue
				{
					csvField.setText(flr.getName());	// set text to name of file selected
					BufferedReader br = new BufferedReader(new FileReader(flr));
					
					// Insert file contents into field
					String readBuffer = null;
					while ((readBuffer = br.readLine()) != null)
						display.appendText(readBuffer + "\n");
					br.close();
					allow = true;
				}
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		
		private double gpaCalc(String[][] data)
		{	
			// data[1][2]~[x] is grade weights
			int sum = 0;
			int divisor = 1;
			
			for (int j = 2; j < data[0].length; j++)
			{
				for (int i = 1; i < data.length; i++)
				{
					sum += Integer.parseInt(data[i][j]);
					divisor++;
				}
			}
			return ((double) sum / (double) divisor);		
		}
		
	}
}

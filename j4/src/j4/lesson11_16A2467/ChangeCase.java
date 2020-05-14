package j4.lesson11_16A2467;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

public class ChangeCase extends Application
{
	private Label label = new Label("Open File");
	private Button[] button = {new Button("Read"), new Button("Write"), new Button("Uppercase")};
	private TextField fileField = new TextField();
	private TextArea textArea = new TextArea();
	private Boolean toLower = false;
	
	public static void main(String args[])
	{
		launch(args);
	}
	
	public void start(Stage stage) throws Exception
	{
		// Set listeners for buttons
		for (Button i : button)
			i.setOnAction(new ActionEventHandler());
		
		// Create new BorderPane
		BorderPane bp = new BorderPane();
		HBox topBar = new HBox();
		topBar.setSpacing(10);
		// Add label and filename field
		topBar.getChildren().add(label);
		topBar.getChildren().add(fileField);
		// Add buttons to top row
		for (Button i : button)
			topBar.getChildren().add(i);
		// Add the horizontal box to pane
		bp.setTop(topBar);
		bp.setCenter(textArea);
		
		// Set up scene
		Scene scene = new Scene(bp, 480, 170);
		stage.setScene(scene);
		stage.setTitle("Read File");
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
				// Open file window, set to working directory
				String workingDir = Paths.get(".").toAbsolutePath().normalize().toString();
				fc.setInitialDirectory(new File(workingDir));
				try		// attempt to open file
				{
					fileField.clear();
					textArea.clear();
					File flr = fc.showOpenDialog(new Stage());	// create open dialog
					if (flr != null)	// if opening the dialog was a success, continue
					{
						fileField.setText(flr.getName());	// set text to name of file selected
						BufferedReader br = new BufferedReader(new FileReader(flr));
						
						// Insert file contents into field
						String readBuffer = null;
						while ((readBuffer = br.readLine()) != null)
							textArea.appendText(readBuffer + "\n");
						br.close();
					}
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}
			// "Write" button
			if (e.getSource() == button[1])
			{
				try
				{
					File flw = fc.showSaveDialog(new Stage());
					fileField.setText(flw.getName());	// creates save window
					BufferedWriter bw = new BufferedWriter(new FileWriter(flw));
					
					String writeBuffer = textArea.getText();
					bw.write(writeBuffer);
					bw.close();
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}
			// "Uppercase"/"Lowercase" Button
			if (e.getSource() == button[2])
			{	
				Pattern pattern = Pattern.compile("\\b[a-zA-Z]");
				Matcher match = pattern.matcher(textArea.getText());
				textArea.clear();
				
				//int wordCount = 0;
				//int wordChange = 0;
				
				/* If toLower is enabled, change to "Uppercase" and change initial letters
				 * to uppercase characters. */
				if (!toLower)
				{
					button[2].setText("Uppercase");
					
					// Create string buffer to hold replaced strings
					StringBuffer sb = new StringBuffer();
					while (match.find() == true)
					{
						match.appendReplacement(sb, match.group().toLowerCase());
					}
					match.appendTail(sb);
					
					// Modify text area
					textArea.setText(sb.toString());
				}
				else
				{
					button[2].setText("Lowercase");
					
					// Create string buffer to hold replaced strings
					StringBuffer sb = new StringBuffer();
					while (match.find() == true)
					{
						match.appendReplacement(sb, match.group().toUpperCase());
					}
					match.appendTail(sb);
					
					// Modify text area
					textArea.setText(sb.toString());
				}
					
				// Switch to opposite mode
				toLower = !toLower;
			}
		}
	}
}

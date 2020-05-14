package j4.lesson11_16A2467;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GenerateInfo extends Application
{
	// Text fields to be filled out by user
	private TextField[] tf = new TextField[6];
	// Labels to accompany text fields
	private Label title = new Label("Fill in information");
	private Label[] tfLabel = {new Label("Name: "), new Label("Email: "), 
			new Label("Address: "), new Label("Organization: "), 
			new Label("Student ID: "), new Label("Age: ")};
	// Gender buttons
	final ToggleGroup group = new ToggleGroup();
	private RadioButton[] btnGender = {new RadioButton("Male"), 
			new RadioButton("Female")};
	// Action buttons
	private Button[] button = {new Button("Clear"), new Button("Generate")};
	
	// Horizontal boxes to hold fields
	private HBox[] field = new HBox[8]; 
	private VBox reg = new VBox();
	
	// Fonts
	private Font fontTitle = new Font("Century", 22);
	private Font fontField = new Font("Century", 14);
	
	public static void main(String args[])
	{
		launch(args);
	}
	
	public void start(Stage stage) throws Exception
	{
		// Create main border pane
		BorderPane bp = new BorderPane();
		
		generateForm();
		
		for (HBox index : field)
		{
			reg.getChildren().add(index);
		}
		reg.setAlignment(Pos.CENTER);
		
		BorderPane.setAlignment(title, Pos.CENTER);
		bp.setCenter(reg);
		Scene scene = new Scene(bp, 350, 280);
		stage.setScene(scene);
		stage.setTitle("GenerateInfo");
		stage.show();
	}
	
	public void generateForm()
	{	
		// Initialize empty components
		for (int i = 0; i < field.length; i++) 
		{
			field[i] = new HBox();
			field[i].setAlignment(Pos.CENTER);
			field[i].setSpacing(1);
			field[i].setPadding(new Insets(3, 0, 3, 0));
			
			if (i < tf.length)
				tf[i] = new TextField("");
		}
		
		// Set default sizes for text fields
		for (TextField t : tf)
			t.setPrefColumnCount(16);
		
		// Add title
		title.setFont(fontTitle);
		field[0].getChildren().add(title);
		
		// Add listeners to action buttons
		for (Button i : button)
			i.setOnAction(new ActionEventHandler());
		// Create horizontal box fields
		int i = 0;
		for (; i < 2; i++)
		{
			// Add name of field
			field[i + 1].getChildren().add(tfLabel[i]);
			tfLabel[i].setFont(fontField);
			// Add text field with it
			field[i + 1].getChildren().add(tf[i]);
		}
		// Add gender buttons
		field[i + 1].getChildren().add(btnGender[0]);
		field[i + 1].getChildren().add(btnGender[1]);
		btnGender[0].setToggleGroup(group);
		btnGender[1].setToggleGroup(group);
		// Continue adding fields
		for (; i < tf.length - 1; i++)
		{
			// Add name of field
			field[i + 2].getChildren().add(tfLabel[i]);
			tfLabel[i].setFont(fontField);
			// Add text field with it
			field[i + 2].getChildren().add(tf[i]);
			
			// Shorten Student ID field
			if (i == 4)
				tf[i].setPrefColumnCount(6);
		}
		// Add name of field
		field[i + 1].getChildren().add(tfLabel[i]);
		tfLabel[i].setFont(fontField);
		// Add text field with it
		field[i + 1].getChildren().add(tf[i]);
		tf[i].setPrefColumnCount(2);
		
		// Add the buttons "Clear" and "Generate"
		i += 2;
		for (Button b : button)
		{
			field[i].getChildren().add(b);
			b.setOnAction(new ActionEventHandler());
		}
	}
	
	class ActionEventHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			// "Clear" button
			if (e.getSource() == button[0])
			{
				for (TextField i : tf)
					i.clear();
				
				for (RadioButton i : btnGender)
					i.setSelected(false);
			}
			// "Generate" button
			if (e.getSource() == button[1])
			{
				// Create file writer
				try
				{
					FileChooser fc = new FileChooser();
					fc.setInitialFileName("myFile.csv");
					File flw = fc.showSaveDialog(new Stage());
					
					flw.getName();	// creates save window
					BufferedWriter csvWriter = new BufferedWriter(
							new FileWriter(flw));					
					
					csvWriter.append("Name");
					csvWriter.append(",");
					csvWriter.append("Email");
					csvWriter.append(",");
					csvWriter.append("Gender");
					csvWriter.append(",");
					csvWriter.append("Address");
					csvWriter.append(",");
					csvWriter.append("Organization");
					csvWriter.append(",");
					csvWriter.append("Student ID");
					csvWriter.append(",");
					csvWriter.append("Age");
					csvWriter.append("\n");
					
					// Write info into .csv
					int i = 0;
					for (; i < 2; i++)
					{
						csvWriter.append(tf[i].getText());
						csvWriter.append(",");
					}
					// Insert gender
					for (RadioButton b : btnGender)
					{
						if (b.isSelected())
						{
							csvWriter.append(b.getText());
							csvWriter.append(",");
						}
					}
					//i++;
					// Insert the rest of the values
					for (; i < tf.length; i++)
					{
						csvWriter.append(tf[i].getText());
						csvWriter.append(",");
					}
					
					csvWriter.close();
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}
		}
	}
}

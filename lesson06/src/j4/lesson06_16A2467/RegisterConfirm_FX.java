package j4.lesson06_16A2467;

import java.util.Vector;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class RegisterConfirm_FX extends Application
{
	// Create array of HBoxes to store registration fields
	HBox[] regFieldArea = new HBox[10];
	
	// All required labels for registration field and titles
	Label[] regFieldLabel = {new Label("Name:"), new Label("Password:"), 
			new Label("Confirm:"), new Label("Email:"), new Label("Birthday:"),
			new Label("Hobby:"), new Label("Biography:"), 
			new Label("Registration List"), new Label("Registration Result"),
			new Label("Register & Display")};
	
	// Input for "Name", "Email"
	Vector<TextField> regFieldInput = new Vector<TextField>();
	// Input for "Password", "Confirm"
	Vector<PasswordField> passwordInput = new Vector<PasswordField>();
	
	@SuppressWarnings("rawtypes")
	// Variables for birthday section
	ComboBox[] birthdayInput = new ComboBox[3];
	
	// Text areas for "Biography" and "Registration Result"
	TextArea bioInput, regOutput;
	
	// "Male" and "Female" radio, "Sports", "Music", "Game" check boxes
	RadioButton[] genderButtons = new RadioButton[2];
	final ToggleGroup genderButtonsGroup = new ToggleGroup();
	CheckBox[] hobbyButtons = new CheckBox[3];
	
	// "Confirm" and "Clear" buttons
	Button[] controlButtons = new Button[2];
	
	// Over-arching VBoxes that will store both sides of registration window
	VBox[] subArea = new VBox[2];
	
	public static void main(String args[])
	{
		launch(args);
	}
	
	public void start(Stage stage) throws Exception
	{
		// Generate Registration List
		createRegistrationList();
		
		// Then add all of the HBoxes to main VBox
		// For left side
		subArea[0] = new VBox();
		for (int i = 0; i < regFieldArea.length; i++)
			subArea[0].getChildren().add(regFieldArea[i]);
		
		// For right side
		subArea[1] = new VBox();
		subArea[1].setFillWidth(false);
		regOutput = new TextArea();
		regOutput.setPrefHeight(300);
		regOutput.setPrefColumnCount(22);
		regFieldLabel[8].setFont(Font.font("Times New Roman", FontWeight.NORMAL, 18));
		subArea[1].getChildren().addAll(regFieldLabel[8], regOutput);
		subArea[1].setAlignment(Pos.TOP_CENTER);
		
		// * === === Set up main stage === === * //
		BorderPane bp = new BorderPane();
		bp.setPrefWidth(0);
		bp.setPadding(new Insets(0, 0, 0, 0));
		bp.setLeft(subArea[0]);
		bp.setCenter(subArea[1]);
		bp.setTop(regFieldLabel[9]);
		regFieldLabel[9].setFont(Font.font("Times New Roman", FontWeight.NORMAL, 24));
		BorderPane.setAlignment(regFieldLabel[9], Pos.CENTER);
		
		Scene sc = new Scene(bp, 650, 400);
		stage.setScene(sc);
		stage.setTitle("Register_FX");
		stage.show();
	}
	
	// Function to generate Registration List and its components
	public void createRegistrationList()
	{
		regFieldArea[0] = new HBox();
		regFieldArea[0].setAlignment(Pos.CENTER);
		regFieldLabel[7].setFont(Font.font("Times New Roman", FontWeight.NORMAL, 18));
		regFieldArea[0].getChildren().add(regFieldLabel[7]);
		// First, create all HBoxes to hold registration fields
		for (int i = 1; i < regFieldArea.length; i++)
		{
			regFieldArea[i] = new HBox();
			regFieldArea[i].setSpacing(15);
			regFieldArea[i].setPadding(new Insets(4, 8, 4, 8));
			regFieldArea[i].setAlignment(Pos.BASELINE_RIGHT);
			// For Name, Email fields
			if (i == 1 || i == 4)
			{
				regFieldInput.add(new TextField());
				regFieldInput.lastElement().setPrefWidth(230);
				regFieldArea[i].getChildren().addAll(regFieldLabel[i - 1], regFieldInput.lastElement());
			}
			// For Password and Confirm fields
			else if (i == 2 || i == 3)
			{
				passwordInput.add(new PasswordField());
				passwordInput.lastElement().setPrefWidth(230);
				regFieldArea[i].getChildren().addAll(regFieldLabel[i - 1], passwordInput.lastElement());
			}
			// For Gender radio buttons
			else if (i == 5)
			{
				genderButtons[0] = new RadioButton("Male");
				genderButtons[0].setToggleGroup(genderButtonsGroup);
				genderButtons[1] = new RadioButton("Female");
				genderButtons[1].setToggleGroup(genderButtonsGroup);
				regFieldArea[i].setAlignment(Pos.CENTER);
				regFieldArea[i].getChildren().addAll(genderButtons[0], genderButtons[1]);
			}
			// For Birthday ComboBoxes
			else if (i == 6)
			{
				// Create vector to hold generated strings
				Vector<String> string = new Vector<String>();
				// Create year ComboBox strings
				for (int j = 1990; j <= 2009; j++)
				{
					string.add(Integer.toString(j));
				}
				birthdayInput[0] = new ComboBox<String>(FXCollections.observableArrayList(string));
				string.clear();
				// Create month ComboBox strings
				for (int j = 1; j <= 12; j++)
				{
					if (j <= 9)
						string.add("0" + Integer.toString(j));
					else
						string.add(Integer.toString(j));
				}
				birthdayInput[1] = new ComboBox<String>(FXCollections.observableArrayList(string));
				string.clear();
				// Create day ComboBox strings
				for (int j = 1; j <= 31; j++)
				{
					if (j <= 9)
						string.add("0" + Integer.toString(j));
					else
						string.add(Integer.toString(j));
				}
				birthdayInput[2] = new ComboBox<String>(FXCollections.observableArrayList(string));
				string.clear();
			
				
				regFieldArea[i].getChildren().addAll(regFieldLabel[4], birthdayInput[0], birthdayInput[1], 
												birthdayInput[2]);
			}
			// For Hobby CheckBoxes
			else if (i == 7)
			{
				
				hobbyButtons[0] = new CheckBox("Sports");
				hobbyButtons[1] = new CheckBox("Music");
				hobbyButtons[2] = new CheckBox("Game");
				regFieldArea[i].getChildren().addAll(regFieldLabel[5], hobbyButtons[0], hobbyButtons[1], 
												hobbyButtons[2]);
			}
			// For Biography field
			else if (i == 8)
			{
				bioInput = new TextArea();
				bioInput.setPrefWidth(230);
				bioInput.setPrefHeight(70);
				regFieldArea[i].getChildren().addAll(regFieldLabel[6], bioInput);
			}
			// Control buttons (Confirm and Clear)
			else
			{
				controlButtons[0] = new Button("Confirm");
				controlButtons[1] = new Button("Clear");
				controlButtons[0].setOnAction(new ControlButtonEventHandler());
				controlButtons[1].setOnAction(new ControlButtonEventHandler());
				regFieldArea[i].setSpacing(60);
				regFieldArea[i].getChildren().addAll(controlButtons[0], controlButtons[1]);
			}
		}
	}
	
	private class ControlButtonEventHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			Button src = (Button) e.getSource();
			// handle confirm button
			if (src.equals(controlButtons[0]))
			{
				regOutput.setText("");
				// if field check succeeds with no errors, print registration info
				if (checkFields() == true)
				{
					regOutput.setText("Registration Succeeded!\n"
							+ "\nName: " + regFieldInput.get(0).getText()
							+ "\nEmail: " + regFieldInput.get(1).getText()
							+ "\nGender: " + ((RadioButton) genderButtonsGroup.getSelectedToggle()).getText()
							+ "\nBirthday: " + birthdayInput[0].getValue().toString() + "."
							+ birthdayInput[1].getValue().toString() + "."
							+ birthdayInput[2].getValue().toString() 
							+ "\nHobby: " + (hobbyButtons[0].isSelected() ? hobbyButtons[0].getText() + ", " : "")
							+ (hobbyButtons[1].isSelected() ? hobbyButtons[1].getText() + ", " : "")
							+ (hobbyButtons[2].isSelected() ? hobbyButtons[2].getText() : "")
							+ "\n\nBiography:\n" + bioInput.getText()
							);
				}
			}
			// handle clear button
			else if (src.equals(controlButtons[1]))
			{
				clearFields();
			}
		}
		
		@SuppressWarnings("unchecked")
		private void clearFields()
		{
			// Clear text fields
			for (int i = 0; i < regFieldInput.size(); i++)
				regFieldInput.get(i).setText("");
			// Clear password fields
			for (int i = 0; i < passwordInput.size(); i++)
				passwordInput.get(i).setText("");
			// Clear radio button selection
			for (int i = 0; i < genderButtons.length; i++)
				genderButtons[i].setSelected(false);
			// Clear combo box selections
			for (int i = 0; i < birthdayInput.length; i++)
				birthdayInput[i].setValue(null);
			// Clear check box selections
			for (int i = 0; i < hobbyButtons.length; i++)
				hobbyButtons[i].setSelected(false);
			// Clear biography section text
			bioInput.setText("");
			// Clear registration result text
			regOutput.setText("");
		}
		
		private Boolean checkFields()
		{
			Boolean success = true, flag = false;
			
			// Check if name is numeric or not
			Pattern pattern = Pattern.compile("[0-9]*");
			if (pattern.matcher(regFieldInput.get(0).getText()).matches() && 
					regFieldInput.get(0).getText().equals("") == false)
			{
				regOutput.appendText("User Name can't be numeric\n");
				success = false;
			}
			
			// Check user name and password lengths
			if (regFieldInput.get(0).getText().length() <= 4
					|| regFieldInput.get(0).getText().length() >= 16
					|| passwordInput.get(0).getText().length() <= 4
					|| passwordInput.get(0).getText().length() >= 16
					|| passwordInput.get(1).getText().length() <= 4
					|| passwordInput.get(1).getText().length() >= 16
					)
			{
				regOutput.appendText("User Name or Password is too short or too long\n");
				success = false;
			}
			
			// Check if passwords match
			if (passwordInput.get(0).getText().contentEquals(passwordInput.get(1).getText()) == false)
			{
				regOutput.appendText("Passwords do not match!\n");
				success = false;
			}
			
			// Check if email meets requirements
			pattern = Pattern.compile("^(.+)@(.+)$");	// change regex pattern
			if (pattern.matcher(regFieldInput.get(1).getText()).matches() == false)
			{
				regOutput.appendText("Email does not meet the specification!\n");
				success = false;
			}
			
			// Check if gender is selected
			flag = false;
			for (int i = 0; i < genderButtons.length; i++)
			{
				if (genderButtons[i].isSelected() == true)
				{
					flag = true;
				}
			}
			if (!flag)
			{
				regOutput.appendText("Gender is not selected!\n");
				success = false;
			}
			
			// Check if birthday is set
			flag = false;
			for (int i = 0; i < birthdayInput.length; i++)
			{
				if (birthdayInput[i].getSelectionModel().isEmpty())
					flag = true;
			}
			if (flag)
			{
				regOutput.appendText("Birthday is not selected!");
				success = false;
			}
			
			return success;
		}
		
	}
}
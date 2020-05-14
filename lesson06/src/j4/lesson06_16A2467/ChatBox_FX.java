package j4.lesson06_16A2467;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ChatBox_FX extends Application
{
	TextArea chatDisplay;
	TextField userA, userB;

	RadioButton[] colorOptions = new RadioButton[3];
	RadioButton[] sizeOptions = new RadioButton[3];

	MenuBar menuBar;
	Menu menuBGColor;
	MenuItem[] menuBGColorItems = new MenuItem[3];

	public static void main(String args[])
	{
		launch(args);
	}

	public void start(Stage stage) throws Exception
	{
		// --- --- --- ---

		// Create chat display text area
		chatDisplay = new TextArea();
		chatDisplay.setPrefHeight(380);
		chatDisplay.setFont(Font.font("Century", FontWeight.BOLD, 18));
		chatDisplay.setWrapText(true);

		// Create HBox area for user A and B input
		userA = new TextField();
		userA.setPrefColumnCount(22);
		userB = new TextField();
		userB.setPrefColumnCount(22);
		userA.setOnAction(new UserInputEventHandler());
		userB.setOnAction(new UserInputEventHandler());

		HBox inputArea = new HBox(userA, userB);
		inputArea.setSpacing(10);
		inputArea.setPadding(new Insets(10, 10, 0, 10));

		// === === Controls configuration === === //

		// Create radio group for color options
		final ToggleGroup colorOptionsGroup = new ToggleGroup();
		// Create buttons for color options
		colorOptions[0] = new RadioButton("Black");
		colorOptions[0].setSelected(true);	// "Black" is selected by default
		colorOptions[1] = new RadioButton("Blue");
		colorOptions[2] = new RadioButton("Red");
		// Add color buttons to group
		colorOptions[0].setToggleGroup(colorOptionsGroup);
		colorOptions[1].setToggleGroup(colorOptionsGroup);
		colorOptions[2].setToggleGroup(colorOptionsGroup);
		// Create HBox for color options
		HBox colorOptionsArea = new HBox(colorOptions[0], colorOptions[1],
									colorOptions[2]);
		colorOptionsArea.setSpacing(20);
		// Add listeners to all radios in group
		colorOptions[0].setOnAction(new ColorOptionsEventHandler());
		colorOptions[1].setOnAction(new ColorOptionsEventHandler());
		colorOptions[2].setOnAction(new ColorOptionsEventHandler());

		// Create radio group for text size options
		final ToggleGroup sizeOptionsGroup = new ToggleGroup();
		// Create buttons for size options
		sizeOptions[0] = new RadioButton("Small");
		sizeOptions[0].setSelected(true);	// "Small" is selected by default
		sizeOptions[1] = new RadioButton("Medium");
		sizeOptions[2] = new RadioButton("Large");
		// Add size options to group
		sizeOptions[0].setToggleGroup(sizeOptionsGroup);
		sizeOptions[1].setToggleGroup(sizeOptionsGroup);
		sizeOptions[2].setToggleGroup(sizeOptionsGroup);
		// Create Hbox for size options
		HBox sizeOptionsArea = new HBox(sizeOptions[0], sizeOptions[1],
									sizeOptions[2]);
		sizeOptionsArea.setSpacing(20);
		// Add listeners to all radios in group
		sizeOptions[0].setOnAction(new SizeOptionsEventHandler());
		sizeOptions[1].setOnAction(new SizeOptionsEventHandler());
		sizeOptions[2].setOnAction(new SizeOptionsEventHandler());

		// Combine both control groups into single Hbox
		HBox controlsArea = new HBox(colorOptionsArea, sizeOptionsArea);
		controlsArea.setSpacing(90);

		// Add control areas to single VBox
		VBox inputAndControls = new VBox(inputArea, controlsArea);
		inputAndControls.setSpacing(15);

		// === === === === === === === === === === //

		// Create menu bar
		menuBar = new MenuBar();

		menuBGColor = new Menu("Background Color");

		menuBGColorItems[0] = new MenuItem("White");
		menuBGColorItems[1] = new MenuItem("Yellow");
		menuBGColorItems[2] = new MenuItem("Gray");

		menuBGColor.getItems().addAll(menuBGColorItems[0], menuBGColorItems[1],
									menuBGColorItems[2]);
		menuBar.getMenus().addAll(menuBGColor);

		for (int i = 0; i < menuBGColorItems.length; i++)
		{
			menuBGColorItems[i].setOnAction(new MenuBGColorEventHandler());
		}
		
		// Set up scene, bring it all together
		BorderPane bp = new BorderPane();
		bp.setPadding(new Insets(10, 20, 10, 20));
		VBox vBox = new VBox(menuBar, bp);
		Scene sc = new Scene(vBox, 600, 500);
		stage.setScene(sc);
		stage.setTitle("ChatBox_FX");
		stage.show();

		bp.setTop(chatDisplay);
		bp.setBottom(inputAndControls);
	}

	private class UserInputEventHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			TextField inputSource = (TextField) e.getSource();

			if (inputSource.equals(userA))
			{
				chatDisplay.appendText("A: " + userA.getText());
				userA.setText("");
			}
			else if (inputSource.equals(userB))
			{
				chatDisplay.appendText("B: " + userB.getText());
				userB.setText("");
			}

			chatDisplay.appendText("\n");
		}
	}

	private class ColorOptionsEventHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			RadioButton src = (RadioButton) e.getSource();

			if (src.equals(colorOptions[0]))
			{
				chatDisplay.setStyle("-fx-text-inner-color: black");
			}
			else if (src.equals(colorOptions[1]))
			{
				chatDisplay.setStyle("-fx-text-inner-color: blue");
			}
			else if (src.equals(colorOptions[2]))
			{
				chatDisplay.setStyle("-fx-text-inner-color: red");
			}
		}
	}

	private class SizeOptionsEventHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			RadioButton src = (RadioButton) e.getSource();

			if (src.equals(sizeOptions[0]))
			{
				chatDisplay.setFont(Font.font("Century", FontWeight.BOLD, 18));
			}
			else if (src.equals(sizeOptions[1]))
			{
				chatDisplay.setFont(Font.font("Century", FontWeight.BOLD, 24));
			}
			else if (src.equals(sizeOptions[2]))
			{
				chatDisplay.setFont(Font.font("Century", FontWeight.BOLD, 32));
			}
		}
	}

	private class MenuBGColorEventHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			MenuItem tmp = (MenuItem) e.getSource();
			String str = tmp.getText();

			Region content = (Region) chatDisplay.lookup(".content");


			if (str.equalsIgnoreCase("White"))
			{
				content.setStyle("-fx-background-color:white;");
			}
			else if (str.equalsIgnoreCase("Yellow"))
			{
				content.setStyle("-fx-background-color:yellow;");
			}
			else if (str.equalsIgnoreCase("Gray"))
			{
				content.setStyle("-fx-background-color:gray;");
			}
		}
	}
}

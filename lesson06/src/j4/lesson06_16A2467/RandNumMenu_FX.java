package j4.lesson06_16A2467;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class RandNumMenu_FX extends Application
{
	// Menu components
	MenuBar menuBar;
	Menu[] menuSection = new Menu[3];
	MenuItem[][] menuItem = new MenuItem[3][3];
	
	// Main area components
	Label label = new Label("Random Number Generator");
	Canvas drawing;
	GraphicsContext gc;
	
	// Statistics
	int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
	int avg = 0;
	
	public static void main(String args[])
	{
		launch(args);
	}
	
	public void start(Stage stage) throws Exception
	{
		
		// === === Create menu bar === === //
		// Initiate all MenuItem objects to empty (fixes seg-fault)
		for (int i = 0; i < menuItem.length; i++)
		{
			for (int j = 0; j < menuItem.length; j++)
			{
				menuItem[i][j] = new MenuItem("");
			}
		}
		
		menuBar = new MenuBar();
		// Create individual menus
		menuSection[0] = new Menu("Operation");
		menuSection[1] = new Menu("Function");
		// Create their children
		menuSection[2] = new Menu("Generation");	// "Generation" is another menu
													// under "Operation" menu
		menuItem[0][0] = new MenuItem("Exit");
		menuItem[1][0] = new MenuItem("20 Points");
		menuItem[1][1] = new MenuItem("40 Points");
		menuItem[2][0] = new MenuItem("Minimum");
		menuItem[2][1] = new MenuItem("Maximum");
		menuItem[2][2] = new MenuItem("Average");
		
		// Add elements to "Operation" menu
		menuSection[0].getItems().addAll(menuSection[2], menuItem[0][0]);
		// Add elements to "Function" menu
		menuSection[1].getItems().addAll(menuItem[2][0], menuItem[2][1],
										menuItem[2][2]);
		// Add elements to "Generation" sub-menu
		menuSection[2].getItems().addAll(menuItem[1][0], menuItem[1][1]);
		// Add everything to the main menu bar
		menuBar.getMenus().addAll(menuSection[0], menuSection[1]);
		
		// Add listeners to all menu items
		for (int i = 0; i < menuItem.length; i++)
		{
			for (int j = 0; j < menuItem.length; j++)
			{
				menuItem[i][j].setOnAction(new MenuItemEventHandler());
			}
		}
		
		// === === === === === === === === //
		
		// === === Add canvas for drawing points === === //
		drawing = new Canvas(500, 100);
		gc = drawing.getGraphicsContext2D();
		
		drawLine();
		
		// === === === === === === === === === === === //
		
		// Add everything to BorderPane element
		BorderPane bp = new BorderPane();
		
		// Add label
		label.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 18));
		bp.setTop(label);
		bp.setCenter(drawing);
		BorderPane.setAlignment(label, Pos.CENTER);
		
		// Pull the entire scene together
		VBox vBox = new VBox(menuBar, bp);
		vBox.setSpacing(10);
		Scene sc = new Scene(vBox, 500, 150);
		stage.setScene(sc);
		stage.setTitle("RandNumMenu_FX");
		stage.show();
	}
	
	public void drawLine()
	{
		// Draw base line
		gc.setFill(Color.BLUE);
		gc.strokeLine(30, 50, 470, 50);
		// Draw indents on the base line at 0, 50, 100
		gc.strokeLine(30, 50, 30, 40);		// @ 0
		gc.strokeLine(250, 50, 250, 40);	// @ 50
		gc.strokeLine(470, 50, 470, 40);	// @ 100
		// Draw numbers under line indents
		gc.strokeText("0", 30 - 4, 68);
		gc.strokeText("50", 250 - 4, 68);
		gc.strokeText("100", 470 - 4, 68);
	}
	
	private class MenuItemEventHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			MenuItem src = (MenuItem) e.getSource();
			
			// "Exit" option
			if (src.equals(menuItem[0][0]))
			{
				System.exit(0);
			}
			// Options inside "Generation" menu
			else if (src.equals(menuItem[1][0]) || src.equals(menuItem[1][1]))
			{
				min = Integer.MAX_VALUE; 
				max = Integer.MIN_VALUE;
				avg = 0;
				gc.clearRect(10, 40, 490, 60);
				drawLine();
				
				int itemCount = 0;
				int sum = 0;
				// "Generation" -> "20 Points"
				if (src.equals(menuItem[1][0]))
					itemCount = 20;
				// "Generation" -> "40 Points"
				else if (src.equals(menuItem[1][1]))
					itemCount = 40;
				for (int i = 0; i < itemCount; i++)
				{
					int red = (int) (Math.random() * 256);
					int blue = (int) (Math.random() * 256);
					int green = (int) (Math.random() * 256);
					int x = (int) (Math.random() * 440 + 30);
					gc.setFill(Color.rgb(red, green, blue));
					gc.fillOval(x, 47, 5, 5);
					
					// Find correct x position for calculations
					int offset = (int) (Math.floor(((double)(x) / (470)) * 100));
					
					// Check for new min and max
					if (offset < min)
						min = offset;
					if (offset > max)
						max = offset;
					// Calculate average
					sum += offset;
				}
				avg = sum / itemCount;
			}
			// "Minimum" option
			else if (src.equals(menuItem[2][0]))
			{
				label.setText("Minimum Number: " + min);
			}
			// "Maximum" option
			else if (src.equals(menuItem[2][1]))
			{
				label.setText("Maximum Number: " + max);
			}
			// "Average" option
			else if (src.equals(menuItem[2][2]))
			{
				label.setText("Average Number: " + avg);
			}
		}
	}
}

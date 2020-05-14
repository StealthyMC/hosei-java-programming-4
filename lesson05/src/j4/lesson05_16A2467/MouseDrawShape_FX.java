package j4.lesson05_16A2467;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MouseDrawShape_FX extends Application
{
	// create set of color modes
	Boolean fillMode = true;
	static final String[] color = {"Color.BLUE", "Color.BLACK", "Color.RED"};
	
	Button buttonFill, buttonNoFill;		// buttons for fill/no fill
	
	ObservableList<String> colorStrings;		// color selection box
	ComboBox<String> colorMode;
	
	Label label;		// label that sits at top of frame
	Canvas drawingArea;	// area for drawing shape
	GraphicsContext gc;
	
	// create variables to hold coordinates of all corners
	double x, y, w, h;

	public static void main(String args[])
	{
		launch(args);
	}
	
	public void start(Stage stage) throws Exception
	{
		// first set up main window
		BorderPane bp = new BorderPane();
		Scene sc = new Scene(bp, 500, 500);
		stage.setScene(sc);
		stage.setTitle("MouseDrawShape_FX");
		stage.show();
		
		// create combo box for color modes
		colorStrings = FXCollections.observableArrayList("Blue", "Black", "Red");
		colorMode = new ComboBox<String>();
		colorMode.setItems(colorStrings);
		colorMode.setOnAction(new ComboBoxEventHandler());
		colorMode.setValue("Blue");
		
		// create images, and set dimensions for them
		ImageView ivFill = new ImageView(new Image(getClass().getResourceAsStream("/squareFill.png")));
		ivFill.setFitWidth(50);	ivFill.setFitHeight(50);
		ImageView ivNoFill = new ImageView(new Image(getClass().getResourceAsStream("/squareNoFill.png")));
		ivNoFill.setFitWidth(50);	ivNoFill.setFitHeight(50);
		// create buttons, set labels and images for them
		buttonFill = new Button("Fill");
		buttonFill.setGraphic(ivFill);
		buttonNoFill = new Button("NoFill");
		buttonNoFill.setGraphic(ivNoFill);
		// provide listeners for the new buttons
		buttonFill.setOnAction(new ButtonEventHandler());
		buttonNoFill.setOnAction(new ButtonEventHandler());
		
		// add bottom hbox for controls
		HBox controls = new HBox(buttonFill, buttonNoFill, colorMode);
		controls.setSpacing(50);
		controls.setAlignment(Pos.CENTER);
		
		// create drawing area
		drawingArea = new Canvas(500, 400);
		drawingArea.setOnMousePressed(new MouseEventHandlerPressed());
		drawingArea.setOnMouseReleased(new MouseEventHandlerReleased());
		gc = drawingArea.getGraphicsContext2D();
		
		// create label for top of window
		label = new Label("Click mouse to draw a square");
		label.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
		label.setTextFill(Color.BLUE);
		BorderPane.setAlignment(label, Pos.CENTER);
		
		// add all elements to the main window pane
		bp.setTop(label);
		bp.setCenter(drawingArea);
		bp.setBottom(controls);
	}
	
	private class ButtonEventHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			Button tmp = (Button) e.getSource();
			
			if (tmp.getText().equals("Fill"))
			{
				fillMode = true;
			}
			else
			{
				fillMode = false;
			}
		}
	}
	
	private class MouseEventHandlerPressed implements EventHandler<MouseEvent>
	{
		public void handle(MouseEvent e)
		{		
			gc.clearRect(0, 0, 500, 400);
			// get current points of mouse to find start position
			x = e.getX();
			y = e.getY() - 25;
			w = e.getX();
			h = e.getY();
		}
	}
	private class MouseEventHandlerReleased implements EventHandler<MouseEvent>
	{
		public void handle(MouseEvent e)
		{	
			// calculate width and height
			w = Math.abs(e.getX() - x);
			h = Math.abs(e.getY() - y);
			
			// make adjustments if mouse position is to the left of starting point
			if (e.getX() < x)
				x = e.getX();
			if (e.getY() < y)
				y = e.getY();
			
			gc.setLineWidth(6);
			
			if (colorMode.getValue().toString().contentEquals("Blue"))
			{
				gc.setStroke(Color.BLUE);
				gc.setFill(Color.BLUE);
			}
			if (colorMode.getValue().toString().contentEquals("Black"))
			{
				gc.setStroke(Color.BLACK);
				gc.setFill(Color.BLACK);
			}
			if (colorMode.getValue().toString().contentEquals("Red"))
			{
				gc.setStroke(Color.RED);
				gc.setFill(Color.RED);
			}
			if (fillMode == true)
				gc.fillRect(x, y, w, h);
			else
				gc.strokeRect(x, y, w, h);
		}
	}
	
	private class ComboBoxEventHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			;
		}
	}
}

package j4.lesson05_16A2467;

import java.util.Random;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.input.MouseEvent;

public class RandNumGenerator_FX extends Application
{
	private Canvas cv;
	private Label lb;
	private Button bt[] = new Button[2];
	int cv_width = 450, cv_height = 400;
	
	public static void main(String args[])
	{
		launch(args);
	}
	
	public void start(Stage stage) throws Exception
	{
		// create scene with border layout
		BorderPane bp = new BorderPane();
		Scene sc = new Scene(bp, 500, 500);
		stage.setScene(sc);
		stage.setTitle("RandNumGenerator_FX");
		stage.show();
				
		// === create canvas for drawing the dots === //
		cv = new Canvas(cv_width, cv_height);
		
		// give it mouse listeners
		cv.setOnMousePressed(new MouseEventHandler());
		cv.setOnMouseReleased(new MouseEventHandler());
		// === === === //
		
		// === create graphics object to draw graph === //
		GraphicsContext gc = cv.getGraphicsContext2D();
		
		// draw outer rectangle
		gc.setLineWidth(3);
		gc.strokeRect(0, 0, cv_width, cv_height);
		// === === === //
		
		// create horizontal box to store buttons
		HBox hb = new HBox();
		hb.setPrefSize(cv_width, 50);
		hb.setSpacing(50);
		// assign names to buttons, then add them
		bt[0] = new Button("Normal Distribution");
		bt[0].setOnAction(new ButtonEventHandler());
		bt[1] = new Button("Uniform Distribution");
		bt[1].setOnAction(new ButtonEventHandler());
		hb.getChildren().add(bt[0]);
		hb.getChildren().add(bt[1]);
		hb.setAlignment(Pos.CENTER);
		
		// create label for the top
		lb = new Label("Random Number Generator");
		lb.setFont(Font.font("Century", FontWeight.NORMAL, 18));
		lb.setAlignment(Pos.CENTER);
		lb.setPadding(new Insets(10, 0, 0, 0));
		BorderPane.setAlignment(lb, Pos.CENTER);
		
		// add canvas object to the scene
		bp.setCenter(cv);
		// add hbox containing buttons to the bottom of scene
		bp.setBottom(hb);
		// add label to the top
		bp.setTop(lb);
	}
	
	private class MouseEventHandler implements EventHandler<MouseEvent>
	{
		public void handle(MouseEvent e)
		{
			// handle mouse pressed event
			if (e.getEventType().toString().equals("MOUSE_PRESSED"))
			{
				
			}
			// handle mouse released event
			if (e.getEventType().toString().equals("MOUSE_RELEASED"))
			{
				
			}
		}
	}
	
	private class ButtonEventHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			// handle "Normal Distribution" event
			if (e.getSource().equals(bt[0]))
			{
				Random r = new Random();
				GraphicsContext gc = cv.getGraphicsContext2D();
				gc.clearRect(3, 3, cv_width - 5, cv_height - 5);
				for (int i = 0; i < 1000; i++)
				{
					// calculate random color
					int red = (int) (Math.random() * 256);
					int green = (int) (Math.random() * 256);
					int blue = (int) (Math.random() * 256);
					// calculate position
					int x = (int) Math.round(r.nextGaussian() * 40 + 230);
					int y = (int) Math.round(r.nextGaussian() * 40 + 200);
					// then draw circle
					gc.setFill(Color.rgb(red, green, blue, 1.0));
					gc.fillOval(x, y, 6, 6);
				}
			}
			// handle "Uniform Distribution" event
			if (e.getSource().equals(bt[1]))
			{
				Random r = new Random();
				GraphicsContext gc = cv.getGraphicsContext2D();
				gc.clearRect(3, 3, cv_width - 5, cv_height - 5);
				for (int i = 0; i < 1000; i++)
				{
					// calculate random color
					int red = (int) (Math.random() * 256);
					int green = (int) (Math.random() * 256);
					int blue = (int) (Math.random() * 256);
					// calculate position
					int x = (int) Math.round(r.nextDouble() * (cv_width - 15) + 5);
					int y = (int) Math.round(r.nextDouble() * (cv_height - 15) + 5);
					// then draw circle
					gc.setFill(Color.rgb(red, green, blue, 1.0));
					gc.fillOval(x, y, 6, 6);
				}
			}
		}
	}
}

package j4.lesson13_16A2467;

import java.util.Random;

import javafx.application.*;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.*;

public class RabbitTortoiseRace extends Application
{
	FlowPane fp = new FlowPane();
	
	final int RABBIT_Y = 55;
	final int TORTOISE_Y = 120;
	
	// ======= GUI elements
	
	// Status text (shows winner at game over)
	Label status = new Label("");
	// Canvas to draw track and animal sprites
	final Canvas canvas = new Canvas(400, 180);
	GraphicsContext gc = canvas.getGraphicsContext2D();
	Canvas sprites = new Canvas(400, 180);
	GraphicsContext sgc = sprites.getGraphicsContext2D();
	Image imgRabbit;
	Image imgTortoise;
	// Button for controlling race
	Button button = new Button("Start");
	// VBox to hold all components
	VBox components = new VBox();
	// Default font
	Font font = new Font("Times New Roman", 20);
	
	// ======== Thread stuff
	
	// Create thread objects
	Thread rabbitThread;
	Thread tortoiseThread;
	
	Player rabbit, tortoise;
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	public void start(Stage stage) throws Exception
	{
		addComponents();
		
		Scene sc = new Scene(fp, 400, 280);
		stage.setScene(sc);
		stage.setTitle("RabbitTortoiseRace");
		stage.show();
	}
	
	public void addComponents()
	{
		// Set status font
		status.setFont(font);
		// Draw title label
		gc.setFont(font);
		gc.setFill(Color.BLACK);
		gc.fillText("The Race Between Rabbit and Tortoise", 42, 30);
		// Draw rabbit's track
		gc.setFill(Color.BLUE);
		gc.fillRect(0, 55, 400, 50);
		// Draw tortoise's track
		gc.setFill(Color.YELLOW);
		gc.fillRect(0, 125, 400, 50);
		
		// Draw the rabbit
		imgRabbit = new Image(this.getClass().getResourceAsStream("/rabbit.png"));
		sgc.drawImage(imgRabbit, 0, 55, 50, 50);
		// Then draw the tortoise
		imgTortoise = new Image(this.getClass().getResourceAsStream("/tortoise.png"));
		sgc.drawImage(imgTortoise, 0, 120, 50, 50);
		
		// Add event listener for button
		button.setOnAction(new ButtonEventHandler());
		
		// Create stack pane to place graphics canvas in
		StackPane sp = new StackPane();
		sp.getChildren().add(canvas);
		sp.getChildren().add(sprites);

		// Add the canvas' to the box
		components.setAlignment(Pos.CENTER);
		components.setSpacing(15);
		components.getChildren().add(sp);
		components.getChildren().add(button);
		components.getChildren().add(status);
		
		// Add the box to the actual frame
		fp.getChildren().add(components);
	}

	public class ButtonEventHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			status.setText("");
			button.setDisable(true);	// Disable after starting
			
			// Clear track
			sgc.clearRect(0, RABBIT_Y, 400, 50);
			sgc.clearRect(0, TORTOISE_Y, 400, 50);
			
			// Create objects for the players
			rabbit = new Player("Rabbit", 0, RABBIT_Y, imgRabbit);
			tortoise = new Player("Tortoise", 0, TORTOISE_Y, imgTortoise);
			// Then add to thread
			rabbitThread = new Thread(rabbit);
			tortoiseThread = new Thread(tortoise);
			// Start threads
			rabbitThread.start();
			tortoiseThread.start();
		}
	}
	
	public class Player implements Runnable
	{
		// Variables for player
		String name;
		double x;
		double y;
		Image img;
		Random r = new Random();
		Boolean enable;
		
		long startTime, endTime;
		
		int steps;
		
		public Player(String name, double x, double y, Image img)
		{
			this.name = name;
			this.x = x;
			this.y = y;
			this.img = img;
			this.steps = 0;
			this.enable = true;
			this.endTime = 0;
		}
		
		@SuppressWarnings("deprecation")
		public void run()
		{
			startTime = System.currentTimeMillis();
			while (x < 350 && enable)
			{
				try
				{
					// Sleep for 0~100ms if rabbit
					if (name == "Rabbit")
						Thread.sleep(r.nextInt(100));
					// Sleep for 0~200ms if rabbit
					if (name == "Tortoise")
						Thread.sleep(r.nextInt(200));
				} catch (InterruptedException e) {}
				
				// Move to the right
				x += 3.5;
				steps++;
				
				// If the player is a rabbit, create a possibility of pausing
				// (this is a 10% chance)
				if (name == "Rabbit")
				{
					if ((r.nextInt(100)) < 10)
					{
						try 
						{
							Thread.sleep(r.nextInt(1000));
						} catch (InterruptedException e) {}
					}
				}
				
				// Update graphic canvas with updated positions
				Platform.runLater(
				() -> 
					{
						sgc.clearRect(x - 10, y, 60, 50);
						sgc.drawImage(img, x, y, 50, 50);
					}
				);
			}
			
			// If the code reaches this point, then this player has won
			endTime = System.currentTimeMillis();
			rabbit.enable = false;
			tortoise.enable = false;
			Platform.runLater(
			() -> 
				{
					rabbitThread.stop();
					tortoiseThread.stop();
					status.setText("Winner: " + name + "\t" + "Cost: " 
							  + ((endTime - startTime) / 1000) + "s "
							  + ((endTime - startTime) % 1000) + "ms");
					button.setDisable(false);
				}
			);
		}
	}
}

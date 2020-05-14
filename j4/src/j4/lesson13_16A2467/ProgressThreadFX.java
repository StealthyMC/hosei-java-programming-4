package j4.lesson13_16A2467;

import javax.swing.Timer;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class ProgressThreadFX extends Application
{
	// ** UI elements
	// Display label for player names
	Label[] names = {new Label("Ma: "), new Label("Guo: "),
			new Label("Yuki: ")
	};
	// Winner label used when the game finishes
	Label status = new Label("");
	// Buttons for application functions
	Button[] button = {new Button("Start"), new Button("Stop"),
			new Button("Suspend"), new Button("Resume")
	};
	// Progress bars for player progress
	ProgressBar[] playerBar = {new ProgressBar(), new ProgressBar(), 
			new ProgressBar()};
	// HBox to store buttons and player stat components
	HBox hboxButtons = new HBox();
	HBox[] hboxPlayer = new HBox[3];
	// VBox to store player stats
	VBox vboxPlayers = new VBox();
	
	// Create player objects...
	Player[] player = {new Player("Ma", playerBar[0], status), 
			new Player("Guo", playerBar[1], status), 
			new Player("Yuki", playerBar[2], status)};
	// ... And the threads they will be used in
	Thread[] playerThread = new Thread[3];
	
	BorderPane bp = new BorderPane();
	
	// Controls game flow
	Boolean gameOver = false;
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	public void start(Stage stage) throws Exception
	{
		// Create main window
		createWindow();
		
		Scene sc = new Scene(bp, 400, 240);
		stage.setScene(sc);
		stage.setTitle("ProgressThreadFX");
		stage.show();
	}
	
	public void createWindow() 
	{
		// Add buttons to upper HBox to create buttons on top
		for (Button i : button) 
		{
			i.setOnAction(new ButtonEventHandler());
			hboxButtons.getChildren().add(i);
			hboxButtons.setSpacing(16);
		}
		hboxButtons.setAlignment(Pos.CENTER);
		
		// Create player label + bar pairs
		for (int i = 0; i < hboxPlayer.length; i++) 
		{
			hboxPlayer[i] = new HBox();
			hboxPlayer[i].getChildren().add(names[i]);
			hboxPlayer[i].getChildren().add(playerBar[i]);
			playerBar[i].setProgress(0);
			playerBar[i].setPrefWidth(320);
			hboxPlayer[i].setSpacing(12);
		}
		
		// Then insert the pairs into the main VBox, where player
		// info is held.
		for (HBox i : hboxPlayer) 
		{
			vboxPlayers.getChildren().add(i);
		}
		vboxPlayers.setSpacing(24);

		bp.setTop(hboxButtons);
		bp.setCenter(vboxPlayers);
		bp.setBottom(status);
		
		button[1].setDisable(true);
		button[2].setDisable(true);
		button[3].setDisable(true);

		BorderPane.setMargin(hboxButtons, new Insets(8, 0, 12, 0));
		BorderPane.setMargin(vboxPlayers, new Insets(24, 0, 24, 0));
		BorderPane.setMargin(status, new Insets(0, 0, 24, 0));
		BorderPane.setAlignment(status, Pos.CENTER);
	}
	
	class ButtonEventHandler implements EventHandler<ActionEvent>
	{
		@SuppressWarnings("deprecation")
		public void handle(ActionEvent e)
		{
			Button src = (Button) e.getSource();
			
			if (src.getText().equals("Start"))
			{
				for (ProgressBar i : playerBar)
					i.setProgress(0);
				
				for (Player i : player)
					i.progress = 0;
				
				status.setText("");
				
				// Start threads
				for (int i = 0; i < playerThread.length; i++)
				{
					playerThread[i] = new Thread(player[i]);
					playerThread[i].start();
					player[i].enable = true;
				}
				
				button[0].setDisable(true);
				button[1].setDisable(false);
				button[2].setDisable(false);
				button[3].setDisable(true);
			}
			
			else if (src.getText().equals("Stop"))
			{
				// Stop all threads
				for (int i = 0; i < playerThread.length; i++)
				{
					playerThread[i].stop();
				}
				
				button[0].setDisable(false);
				button[1].setDisable(true);
				button[2].setDisable(true);
				button[3].setDisable(true);
			}
			
			else if (src.getText().equals("Suspend"))
			{
				// Suspend threads
				for (int i = 0; i < playerThread.length; i++)
				{
					playerThread[i].suspend();
				}
				
				button[0].setDisable(true);
				button[1].setDisable(false);
				button[2].setDisable(true);
				button[3].setDisable(false);
			}
			
			else if (src.getText().equals("Resume"))
			{
				// Resume threads
				for (int i = 0; i < playerThread.length; i++)
				{
					playerThread[i].resume();
				}
				
				button[0].setDisable(true);
				button[1].setDisable(false);
				button[2].setDisable(false);
				button[3].setDisable(true);
			}
		}
	}
	
	// Threaded object "Player"
	public class Player implements Runnable
	{
		String name;
		double progress;
		Timer timer;
		ProgressBar bar;
		Boolean enable;
		Label label;
		
		long startTime;
		long stopTime;
		
		public Player(String player_name, ProgressBar player_bar, Label label)
		{
			progress = 0;
			name = player_name;
			bar = player_bar;
			enable = true;
			this.label = label;
		}

		public void run()
		{
			startTime = System.currentTimeMillis();
			// Delay the player for a random amount of time
			try 
			{
				Thread.sleep((int) (Math.random() * 1000));
			} catch (InterruptedException e) {}
			// Increase progress bar
			while (progress < 1 && enable)
			{
				try 
				{
					Thread.sleep(30);
				} catch (InterruptedException e) {}
				progress += 0.01;
				bar.setProgress(progress);
				stopTime = System.currentTimeMillis();
			}
			// If won, disable other bars
			if (progress >= 1)
			{
				for (Player i : player)
					i.enable = false;
				
				Platform.runLater(
				  () -> {
				    status.setText("Winner: " + name + "\t" + "Cost: " 
				  + ((stopTime - startTime) / 1000) + "s "
				  + ((stopTime - startTime) % 1000) + "ms");
				    button[0].setDisable(false);
				    button[1].setDisable(true);
					button[2].setDisable(true);
					button[3].setDisable(true);
				  }
				);
			}
		}
	}
}

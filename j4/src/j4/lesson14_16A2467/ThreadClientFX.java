package j4.lesson14_16A2467;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/* Implement "Runnable" so that this class may be threaded in 
   ThreadServerFX.java */
public class ThreadClientFX extends Application implements Runnable
{
	/* 		GUI components		*/
	// Shows all feedback from server
	public TextArea display;
	// For "connect" and "disconnect" buttons
	public Button[] button = new Button[2];
	// Labels for session info boxes
	public Label[] sessionInputLabel = new Label[3];
	// Input for session info
	public TextField[] sessionInput = new TextField[3];
	// Label for user input text field
	public Label userInputLabel;
	// User input text field
	public TextField userInput;
	
	// HBox to store input and controls for session info
	public HBox[] hboxSessionInput = new HBox[2];
	// HBox to store user input label and field
	public HBox hboxUserInput;
	// VBox to store all input information together
	public VBox vboxInput;
	
	// Store all GUI components inside BorderPane
	BorderPane bp;
	
	/*		Networking variables		*/
	private String HOST = "localhost";
	private int PORT = 12345;
	private Thread thread;
	private String username = "";
	private Socket socket;
	private BufferedReader br;
	private PrintWriter pw;
	
	public void generateGUI()
	{
		bp = new BorderPane();
		
		display = new TextArea("");
		
		// Create session info area
		hboxSessionInput[0] = new HBox();
		hboxSessionInput[1] = new HBox();
		// Row 1
		Region[] spacer = new Region[2];	// temp object used for spacing
		spacer[0] = new Region(); spacer[1] = new Region();
		spacer[1].setPrefWidth(50);
		HBox.setHgrow(spacer[0], Priority.ALWAYS);
		HBox.setHgrow(spacer[1], Priority.ALWAYS);
		sessionInputLabel[0] = new Label("IP Address");
		sessionInputLabel[1] = new Label("Port");
		sessionInputLabel[2] = new Label("Name");
		hboxSessionInput[0].getChildren().addAll(
				spacer[0], sessionInputLabel[0], sessionInputLabel[1], 
				sessionInputLabel[2], spacer[1]);
		hboxSessionInput[0].setPadding(new Insets(3));
		hboxSessionInput[0].setSpacing(35);
		hboxSessionInput[0].setAlignment(Pos.CENTER);
		// Row 2
		button[0] = new Button("Connect");
		button[0].setOnAction(new ConnectEventHandler());
		button[1] = new Button("Disconnect");
		button[1].setOnAction(new ConnectEventHandler());
		button[1].setDisable(true);
		sessionInput[0] = new TextField(HOST);
		sessionInput[0].setPrefColumnCount(6);
		sessionInput[1] = new TextField(Integer.toString(PORT));
		sessionInput[1].setPrefColumnCount(4);
		sessionInput[2] = new TextField("");
		sessionInput[2].setPrefColumnCount(5);
		hboxSessionInput[1].getChildren().addAll(button[0],
				sessionInput[0], sessionInput[1], sessionInput[2], button[1]);
		hboxSessionInput[1].setSpacing(6);
		hboxSessionInput[1].setAlignment(Pos.CENTER);
		
		// And then create user input area
		hboxUserInput = new HBox();
		userInputLabel = new Label("Text:");
		userInput = new TextField("");
		userInput.setOnAction(new MsgEventHandler());
		userInput.setPrefColumnCount(30);
		userInput.setDisable(true);
		hboxUserInput.getChildren().addAll(userInputLabel, userInput);
		hboxUserInput.setAlignment(Pos.CENTER);
		hboxUserInput.setSpacing(4);
		
		// Create main VBox to hold controls
		vboxInput = new VBox();
		vboxInput.getChildren().addAll(hboxSessionInput[0], hboxSessionInput[1],
				hboxUserInput);
		vboxInput.setAlignment(Pos.CENTER);
		vboxInput.setSpacing(8);
	
		// Add all GUI components to BorderPane
		bp.setTop(display);
		bp.setCenter(vboxInput);
	}
	
	public void startThread()
	{
		// create thread
		thread = new Thread(this);
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	public void start(Stage stage) throws Exception
	{
		// Generate interface
		generateGUI();
		
		Scene sc = new Scene(bp, 450, 300);
		stage.setScene(sc);
		stage.setTitle("ThreadClientFX");
		stage.show();
		
		// Start thread
		startThread();
	}
	
	// Required for "Runnable" classes
	public void run()
	{
		try
		{
			socket = new Socket(HOST, PORT);
			br = new BufferedReader
		            (new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(new BufferedWriter
					(new OutputStreamWriter(socket.getOutputStream())));

			// Stop user from modifying server info input while connected
			button[0].setDisable(true);
			button[1].setDisable(false);
			sessionInput[0].setDisable(true);
			sessionInput[1].setDisable(true);
			sessionInput[2].setDisable(true);
			userInput.setDisable(false);
			
			// Send user name to client
			try
			{
				pw.println(username);
				pw.flush();
				display.appendText("Connected\n");
				display.appendText(br.readLine() + "\n");
			}
			catch (Exception ex) 
			{
				ex.printStackTrace();
				button[0].setDisable(false);
				button[1].setDisable(true);
				sessionInput[0].setDisable(false);
				sessionInput[1].setDisable(false);
				sessionInput[2].setDisable(false);
				userInput.setDisable(true);
			}
			
		}
		catch (Exception e) {e.printStackTrace();}
	}
	
	// Event handling classes
	class ConnectEventHandler implements EventHandler<ActionEvent>
	{
		@SuppressWarnings("deprecation")
		public void handle(ActionEvent e)
		{
			// "Connect" button
			if (e.getSource() == button[0])
			{
				// Check if fields have been filled
				for (TextField i : sessionInput)
				{
					if (i.getText().equalsIgnoreCase(""))
					{
						throw new IllegalArgumentException("None of the fields"
								+ " may be null.");
					}
				}
				// Start new thread
				thread.start();
				// Set appropriate networking variables
				HOST = sessionInput[0].getText();
				PORT = Integer.parseInt(sessionInput[1].getText());
				username = sessionInput[2].getText();
			}
			// "Disconnect" button
			if (e.getSource() == button[1])
			{
				try
				{
					socket.close();
					br.close();
					pw.close();
					button[0].setDisable(false);
					button[1].setDisable(true);
					sessionInput[0].setDisable(false);
					sessionInput[1].setDisable(false);
					sessionInput[2].setDisable(false);
					userInput.setDisable(true);
					
					thread.stop();
					// Start from the beginning again
					startThread();
				}
				catch (Exception ex) {ex.printStackTrace();}
			}
		}
	}
	
	class MsgEventHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			if (socket.isConnected())
			{	
				try
				{
					pw.println(userInput.getText());
					pw.flush();
					display.appendText(br.readLine() + "\n");
					userInput.setText("");
				}
				catch (Exception ex) {ex.printStackTrace();}
			}
		}
	}
}

package j4.lesson12_16A2467;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class MyBrowser extends Application
{
	// Create elements for Load, Back, and Forward buttons
	HBox[] hboxControls = new HBox[2];
	VBox vboxControlArea = new VBox();
	Button[] btnControls = {new Button("Load"), new Button("Back"), new Button("Forward")};
	
	// Address bar
	TextField addressBar = new TextField("");
	
	// WebView objects
	private WebView browser = new WebView();
	WebEngine engine = browser.getEngine();
	WebHistory history = engine.getHistory();
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	public void start(Stage stage) throws IOException
	{
		// Load blank page at start
		engine.load("");
		
		// Add buttons upper HBox
		hboxControls[0] = new HBox();
		for (Button i : btnControls)
		{
			hboxControls[0].getChildren().add(i); 	// add to HBox
			i.setOnAction(new URLEventHandler());
		}
		// Then add TextField to lower HBox
		hboxControls[1] = new HBox();
		hboxControls[1].getChildren().add(addressBar);
		addressBar.setOnAction(new URLEventHandler());
		// Add HBox's into VBox control pane
		vboxControlArea = new VBox();
		for (HBox i : hboxControls)
			vboxControlArea.getChildren().add(i);
		addressBar.setPrefWidth(600);
		
		// Create pane for program
		BorderPane bp = new BorderPane();
		bp.setTop(vboxControlArea);
		bp.setCenter(browser);
		
		// Set up stage area
		Scene sc = new Scene(bp, 600, 600);
		stage.setScene(sc);
		stage.setTitle("MyBrowser");
		stage.show();
		
	}
	
	class URLEventHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			// "Load" button
			if (e.getSource() == btnControls[0] || 
					e.getSource() == addressBar)
			{
				try
				{
					engine.load(addressBar.getText());
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}
			
			// "Back" button
			if (e.getSource() == btnControls[1])
			{
				goBack();
			}
			
			// "Forward" button
			if (e.getSource() == btnControls[2])
			{
				goForward();
			}
		}
		
		void goBack()
		{
			int index = history.getCurrentIndex();
			
			if (index > 0) 
            {
				history.go(-1);
            }
		}
		
		void goForward()
		{
			int index = history.getCurrentIndex();
			
			if (index < history.getEntries().size() - 1) 
            {
				history.go(1);
            }
		}
	}
}

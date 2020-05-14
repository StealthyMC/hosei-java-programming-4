package j4.lesson05_16A2467;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PhoneCallPanelEvt_FX extends Application
{
	Label status;
	Button button[] = new Button[13];
	GridPane gridButton;
	
	public static void main(String args[])
	{
		launch(args);
	}
	
	public void start(Stage stage) throws Exception
	{
		// create main scene
		BorderPane bp = new BorderPane();
		Scene sc = new Scene(bp, 260, 300);
		stage.setScene(sc);
		stage.setTitle("PhoneCallPanelEvt_FX");
		stage.show();
		
		// create top label for status message
		status = new Label("");
		status.setFont(Font.font("Century", FontWeight.BOLD, 24));
		
		HBox hbStatus = new HBox();
		hbStatus.setPrefSize(260, 50);
		hbStatus.setAlignment(Pos.CENTER);
		hbStatus.setStyle("-fx-background-color: white;");
		hbStatus.getChildren().add(status);
		
		// create button area
		// first, create pane to store buttons in
		gridButton = new GridPane();
		gridButton.setHgap(8);
		gridButton.setVgap(8);
		gridButton.setBackground(new Background(new BackgroundFill(Color.BLACK, 
				CornerRadii.EMPTY, Insets.EMPTY)));
		// next, create buttons for pane, then add to pane
		for (int i = 1; i <= 12; i++)
		{
			// check for normal digit
			if (i <= 9)
				button[i] = new Button(Integer.toString(i));
			// check for '0' key
			else if (i == 10)
				button[i] = new Button("0");
			// check for phone call button
			else if (i == 11)
			{
				Image im = new Image(getClass().getResourceAsStream("/phoneCall.png"));
				ImageView iv = new ImageView(im);
				iv.setFitWidth(iv.getImage().getWidth() * .25);
				iv.setFitHeight(iv.getImage().getHeight() * .25);
				button[i] = new Button("");
				button[i].setGraphic(iv);
			}
			// check for hang up button
			else
			{
				Image im = new Image(getClass().getResourceAsStream("/phoneHangUp.png"));
				ImageView iv = new ImageView(im);
				iv.setFitWidth(iv.getImage().getWidth() * .18);
				iv.setFitHeight(iv.getImage().getHeight() * .18);
				button[i] = new Button("");
				button[i].setGraphic(iv);
			}
			button[i].setFont(Font.font("Century", FontWeight.BOLD, 24));
			button[i].setOnAction(new ButtonEventHandler());
			button[i].setPrefSize(80, 55);
			gridButton.add(button[i], (i - 1) % 3, (i - 1) / 3);
		}
		
		// add elements to main stage area
		bp.setTop(hbStatus);
		bp.setCenter(gridButton);
	}
	
	private class ButtonEventHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			// if phoneCall button is pressed
			if (e.getSource().equals(button[11]) && 
					(status.getText().contains("HangUp")) == false)
			{
				status.setText("Calling " + status.getText());
			}
			// if phoneHangUp button is pressed
			else if (e.getSource().equals(button[12]))
			{
				status.setText("HangUp");
			}
			else 
			// otherwise, add numbers into line
			// wipe out line if it the phone is currently calling or displaying 
			// 'HangUp'
			{
				if ((status.getText().contains("Calling")) || 
						(status.getText().contains("HangUp")) )
				{
					status.setText("");
				}
				String out = status.getText() + ((Button)e.getSource()).getText();
				status.setText(out);
			}
		}
	}
}

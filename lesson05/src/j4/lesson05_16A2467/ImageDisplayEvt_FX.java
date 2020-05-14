package j4.lesson05_16A2467;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class ImageDisplayEvt_FX extends Application
{
	Label[] imageButtons = new Label[3];
	String[] imageFilenames = {"/sakura1.jpg","/sakura2.jpg","/sakura3.png"};
	Label selectedImage;
	
	public static void main(String args[])
	{
		launch(args);
	}
	
	public void start(Stage stage) throws Exception
	{
		// set up main frame with BorderPane layout
		BorderPane bp = new BorderPane();
	    bp.setPadding(new Insets(5, 30, 20, 15));
		Scene sc = new Scene(bp, 600, 500);
		stage.setScene(sc);
		stage.setTitle("ImageDisplayEvt_FX");
		stage.show();
		
		// * === create image selection area === * //
		FlowPane paneImageSelect = new FlowPane();
		paneImageSelect.setHgap(15);
		paneImageSelect.setVgap(15);
		paneImageSelect.setAlignment(Pos.CENTER);
		
		// create the three elements to be selected from
		for (int i = 0; i < imageButtons.length; i++)
		{
			// get image in array, create object for it
			Image im = new Image(getClass().getResourceAsStream(imageFilenames[i]));
			ImageView iv = new ImageView(im);
			// scale the image to uniform size
			iv.setFitWidth(170);
			iv.setFitHeight(110);		
			// set the label to have the picture that was fetched
			imageButtons[i] = new Label();
			imageButtons[i].setGraphic(iv);
			// provide listener event
			imageButtons[i].setOnMouseClicked(new MouseEventHandler());
			// then add it to the FlowPane
			paneImageSelect.getChildren().add(imageButtons[i]);
		}
		// * === === === * //
		
		// initiate image preview component with sakura1.jpg		
		Image im = new Image(getClass().getResourceAsStream(imageFilenames[0]));
		ImageView iv = new ImageView(im);
		iv.setFitHeight(350);	// limit height of preview
		selectedImage = new Label();
		selectedImage.setGraphic(iv);
		selectedImage.setAlignment(Pos.CENTER);
		
		// create pane to place preview in, and then center it
		FlowPane paneSelectedImage = new FlowPane();
		paneSelectedImage.getChildren().add(selectedImage);
		paneSelectedImage.setAlignment(Pos.CENTER);
		
		// finally, add panes to main BorderPane
		bp.setTop(paneSelectedImage);
		bp.setBottom(paneImageSelect);
	}
	
	private class MouseEventHandler implements EventHandler<MouseEvent>
	{
		public void handle(MouseEvent e)
		{
			if (e.getSource().equals(imageButtons[0]))
			{
				Image im = new Image(getClass().getResourceAsStream(imageFilenames[0]));
				ImageView iv = new ImageView(im);
				iv.setFitHeight(350);	// limit height of preview
				selectedImage.setGraphic(iv);
			}
			else if (e.getSource().equals(imageButtons[1]))
			{
				Image im = new Image(getClass().getResourceAsStream(imageFilenames[1]));
				ImageView iv = new ImageView(im);
				iv.setFitWidth(iv.getImage().getWidth() * .8);
				iv.setFitHeight(350);	// limit height of preview
				selectedImage.setGraphic(iv);
			}
			else if (e.getSource().equals(imageButtons[2]))
			{
				Image im = new Image(getClass().getResourceAsStream(imageFilenames[2]));
				ImageView iv = new ImageView(im);
				iv.setFitHeight(350);	// limit height of preview
				selectedImage.setGraphic(iv);
			}
		}
	}
}

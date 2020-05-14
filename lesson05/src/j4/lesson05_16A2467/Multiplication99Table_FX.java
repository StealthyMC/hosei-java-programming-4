package j4.lesson05_16A2467;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Multiplication99Table_FX extends Application
{
	public static void main(String args[])
	{
		launch(args);
	}
	
	public void start(Stage stage) throws Exception
	{
		// build the initial window
		GridPane bp = new GridPane();
		// set gaps in between grid elements
		bp.setHgap(12);
		bp.setVgap(12);
		// create scene
		Scene sc = new Scene(bp, 380, 400);
		stage.setScene(sc);
		stage.setTitle("Multiplication99Table_FX");
		stage.show();
		
		// create labels for the multiplication table
		int product = 0;
		for (int i = 1; i < 10; i++)
		{
			for (int j = 1; j < 10; j++)
			{
				product = i * j;
				Label lb = new Label(Integer.toString(product));
				lb.setFont(Font.font("Century", FontWeight.BOLD, 24));
				bp.add(lb, i, j);
			}
		}
	}
}

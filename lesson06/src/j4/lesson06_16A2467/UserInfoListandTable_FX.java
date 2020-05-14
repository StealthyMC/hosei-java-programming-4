package j4.lesson06_16A2467;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class UserInfoListandTable_FX extends Application
{
	Label label;
	ListView<String> studentInfo;
	TableView<RowData> studentTable;
	
	
	public static void main(String args[])
	{
		launch(args);
	}
	
	public void start(Stage stage) throws Exception
	{
		// Create top label
		label = new Label("Select list to show result.");
		
		// === === Create the student table === === // 
		// Create columns for table
		studentTable = new TableView<RowData>();
		TableColumn<RowData, String> colName = new TableColumn<RowData, String>("Name");
		TableColumn<RowData, String> colID = new TableColumn<RowData, String>("ID");
		TableColumn<RowData, String> colGender = new TableColumn<RowData, String>("Gender");
		TableColumn<RowData, String> colYear = new TableColumn<RowData, String>("Entrance Year");
		TableColumn<RowData, String> colHobby = new TableColumn<RowData, String>("Hobby");
		
		colName.setCellValueFactory(new PropertyValueFactory<RowData, String>("name"));
		colID.setCellValueFactory(new PropertyValueFactory<RowData, String>("id"));
		colGender.setCellValueFactory(new PropertyValueFactory<RowData, String>("gender"));
		colYear.setCellValueFactory(new PropertyValueFactory<RowData, String>("year"));
		colHobby.setCellValueFactory(new PropertyValueFactory<RowData, String>("hobby"));
		
		// Add entries to table
		ObservableList<RowData> studentList = FXCollections.observableArrayList();
		studentList.add(new RowData("GUO Ao", "16T9802", "Male", 2016, "Music"));
		studentList.add(new RowData("Yuki Miyazawa", "17T0022", "Male", 2016, "Game"));
		
		// Add columns to table
		studentTable.getColumns().add(colName);
		studentTable.getColumns().add(colID);
		studentTable.getColumns().add(colGender);
		studentTable.getColumns().add(colYear);
		studentTable.getColumns().add(colHobby);
		
		// Set contents of table to created instances
		studentTable.setItems(studentList);
		// Adjust size of table
		studentTable.setPrefWidth(450);
		// Add listener to table
		studentTable.setOnMouseClicked(new TableViewHandler());
		
		// === === === === === === === === === //
		
		// Create ListView component to display student info
		studentInfo = new ListView<String>();
		studentInfo.setPrefWidth(185);
		
		// Add elements to horizontal area for student table and info
		HBox studentArea = new HBox(studentInfo, studentTable);
		studentArea.setSpacing(8);
		studentArea.setPadding(new Insets(10, 10, 10, 10));
		
		// Create primary layout to store components
		BorderPane bp = new BorderPane();
		bp.setTop(label);
		bp.setCenter(studentArea);
		
		Scene sc = new Scene(bp, 600, 200);
		stage.setScene(sc);
		stage.setTitle("UserInfoListandTable_FX");
		stage.show();
	}
	
	public class RowData
	{
		private final SimpleStringProperty name;
		private final SimpleStringProperty id;
		private final SimpleStringProperty gender;
		private final SimpleIntegerProperty year;
		private final SimpleStringProperty hobby;
		
		public RowData(String p_name, String p_id, String p_gender,
					Integer p_year, String p_hobby)
		{
			this.name = new SimpleStringProperty(p_name);
			this.id = new SimpleStringProperty(p_id);
			this.gender = new SimpleStringProperty(p_gender);
			this.year = new SimpleIntegerProperty(p_year);
			this.hobby = new SimpleStringProperty(p_hobby);
		}
		
		public StringProperty nameProperty()	{return name;}
		public StringProperty idProperty()		{return id;}
		public StringProperty genderProperty()	{return gender;}
		public IntegerProperty yearProperty()	{return year;}
		public StringProperty hobbyProperty()	{return hobby;}
	}
	
	private class TableViewHandler implements EventHandler<MouseEvent>
	{
		public void handle(MouseEvent e)
		{
			@SuppressWarnings("unchecked")
			TableView<RowData> tmp = (TableView<RowData>) e.getSource();
			RowData data = tmp.getSelectionModel().getSelectedItem();
			
			// Create strings to be displayed in ListView
			String name = "Name: " + data.nameProperty().getValue();
			String id = "ID: " + data.idProperty().getValue();
			String gender = "Gender: " + data.genderProperty().getValue();
			String year = "Entrance Year: " + Integer.toString(data.yearProperty().getValue());
			String hobby = "Hobby: " + data.hobbyProperty().getValue();
			
			// First, clear list of pre-existing info
			studentInfo.getItems().clear();
			// Then add new items to list view
			studentInfo.getItems().addAll(name, id, gender, year, hobby);
		}
	}
}

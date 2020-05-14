package j4.lesson12_16A2467;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FTPClient extends Application
{
	// Network configuration macros
	public static final String HOST = "localhost";
	public static final int PORT = 10000;
	static final int NEWLINE = 10;
	
	// GUI Components
	TextArea output = new TextArea();
	Button[] btnControls = {new Button("Choose File"), new Button("Upload File"),
			new Button("Read Directory"), new Button("Download File")};
	Label labelFileName = new Label("File Name: ");
	TextField tfFileName = new TextField("");
	HBox hboxControls = new HBox();
	
	// File I/O settings
	String dir = "MyFile_client/";
	FileChooser fileChooser = new FileChooser();
	File uploadFile;
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	public void start(Stage stage) throws Exception
	{	
		// Add items to HBox for application controls
		hboxControls.setSpacing(5);
		for (Button i : btnControls)
		{
			hboxControls.getChildren().add(i);
			i.setOnAction(new FTPActionHandler());
		}
		hboxControls.getChildren().addAll(labelFileName, tfFileName);
		
		
		BorderPane bp = new BorderPane();
		bp.setCenter(output);
		bp.setBottom(hboxControls);
		
		// Set up stage
		Scene sc = new Scene(bp, 680, 300);
		stage.setScene(sc);
		stage.setTitle("TFPClient");
		stage.show();
	}
	
	class FTPActionHandler implements EventHandler<ActionEvent>
	{	
		public void handle(ActionEvent e)
		{
			// "Choose File" button
			if (e.getSource() == btnControls[0])
			{
				// Set initial directory for opening file
				fileChooser.setInitialDirectory(new File(dir));
				
				try
				{
					// Opens a new window to select file
					uploadFile = fileChooser.showOpenDialog(new Stage());
					if (uploadFile != null)
					{
						// Insert filename into text field
						tfFileName.setText(uploadFile.getName());
						// At this point, uploadFile is no longer null.
					}
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
			
			// "Upload File" button
			if (e.getSource() == btnControls[1])
			{
				Socket socket = null;
				BufferedInputStream buffInputStream = null;
				
				try
				{	
					// If no file has been selected, cancel connection, throw exception
					if (uploadFile == null)
					{
						System.out.println("error: uploadFile is null.");
						throw new RuntimeException();
					}
					// Otherwise, prepare connection to send the file
					else
					{
						// First, try to establish connection to server
						socket = new Socket(HOST, PORT);						
						
						// Create buffer for information storage
						byte[] buffer = new byte[(int) uploadFile.length()];
						buffInputStream = new BufferedInputStream(new FileInputStream(uploadFile));
						// Insert file contents into buffer
						buffInputStream.read(buffer, 0, buffer.length);
						
						// Create output stream in socket
						OutputStream outputStream = socket.getOutputStream();
						// Write command type
						outputStream.write("send\n".getBytes());
						// Write filename
						outputStream.write((tfFileName.getText() + "\n").getBytes());
						// Write contents
						outputStream.write(buffer, 0, buffer.length);
						outputStream.flush();
						
						// Indicate success
						output.appendText("File " + (tfFileName.getText() + " uploaded.\n"));
					}
					
					// Close the socket at the end of the transaction
					socket.close();
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
			
			// "Read Directory" button
			if (e.getSource() == btnControls[2])
			{
				Socket socket = null;
				try
				{
					// First, try to establish connection to server
					socket = new Socket(HOST, PORT);						
					
					// Create output stream in socket
					OutputStream outputStream = socket.getOutputStream();
					// Write command type
					outputStream.write("show\n".getBytes());
					
					// Set up input stream for directory info
					InputStream inputStream = socket.getInputStream();
					// Sets up input stream inside socket
					byte[] buffer = new byte[8192];
					inputStream.read(buffer, 0, buffer.length);
					
					output.appendText("Directory info:\n\n" + new String(buffer, 0, buffer.length)
							+ "\n\n");
					
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
			
			// "Download File" button
			if (e.getSource() == btnControls[3])
			{
				Socket socket = null;
				try
				{
					byte[] buffer = new byte[8192];
					// First, try to establish connection to server
					socket = new Socket(HOST, PORT);						
					
					// Create output stream in socket
					OutputStream outputStream = socket.getOutputStream();
					// Write command type
					outputStream.write("download\n".getBytes());
					
					// Send filename inside textfield to server
					outputStream.write((tfFileName.getText() + "\n").getBytes());
					
					// Start reading contents in
					InputStream inputStream = socket.getInputStream();
					int bytesRead = inputStream.read(buffer, 0, buffer.length);
					
					// Receive filename
					int counter = 0;	// offset for buffer
					for (byte b : buffer)
					{
						if (b == NEWLINE)
							break;
						counter++;
					}
					// Create filename using buffer
					String downloadName = new String(buffer, 0, counter);
					
					// Read again
					inputStream.read(buffer, 0, buffer.length);
					System.out.println("Contents: " + Arrays.toString(buffer));
					
					// Create new file with new downloadName, and insert contents from buffer
					FileOutputStream fileStream = new FileOutputStream("Myfile_client/" 
					+ downloadName);
					BufferedOutputStream buffOutputStream = new BufferedOutputStream(fileStream);
					
					// Start writing from buffer after filename
					buffOutputStream.write(buffer, 0, bytesRead);
					
					// Close the file stream
					buffOutputStream.close();
					fileStream.close();
					
					// Success
					output.appendText("Downloaded the file: " + downloadName + "\n\n");
					
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		}
	}
}

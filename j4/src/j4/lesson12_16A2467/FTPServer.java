package j4.lesson12_16A2467;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Paths;

public class FTPServer 
{
	static final int PORT = 10000;
	static final int NEWLINE = 10;
	
	public static void main(String[] args)
	{
		while (true)
		{
			try
			{
				ServerSocket serverSocket = new ServerSocket(PORT);
				System.out.println("Waiting for incoming connection...");
				String fileName = null;
				while (true)
				{
					// * program waits here for incoming connection *
					Socket socket = serverSocket.accept();
					InputStream inputStream = socket.getInputStream();
					
					// First, retrieve command
					String command = getCommand(socket, inputStream);
					System.out.println("command: " + command);
					
					// Receive file from client
					if (command.equalsIgnoreCase("send"))
						sendFile(socket, inputStream, fileName);
					// Show directory information to client
					if (command.equalsIgnoreCase("show"))
						showDirectory(socket, inputStream, fileName);
					// Send file to client
					if (command.equalsIgnoreCase("download"))
						downloadFile(socket, inputStream, fileName);
					
					// Close the server socket connection and wait for next connection
					serverSocket.close();
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static String getCommand(Socket socket, InputStream inputStream)
	{
		String command = null;
		try
		{
			// Sets up input stream inside socket
			byte[] buffer = new byte[1024];
			inputStream.read(buffer, 0, buffer.length);
			
			// Iterate through until
			int counter = 0;
			for (byte b : buffer)
			{
				if (b == NEWLINE)
					break;
				counter++;
			}
			command = new String(buffer, 0, counter);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return command;
	}
	
	/* 
	 * Method to receive a file from a client
	 */
	public static void sendFile(Socket socket, InputStream inputStream, String fileName)
	{
		try
		{
			// create buffer to hold info
			byte[] buffer = new byte[1024];
			// Start reading contents in
			int bytesRead = inputStream.read(buffer, 0, buffer.length);

			// Send bytes to file
			int counter = 0;
			for (byte b : buffer)
			{
				if (b == NEWLINE)
					break;
				counter++;
			}
			// Create filename using buffer
			fileName = new String(buffer, 0, counter);
	
			// Set up output stream so that information can be sent to a file
			FileOutputStream outputStream = new FileOutputStream("Myfile_server/" + fileName);
			// Set up buffered output stream for file
			BufferedOutputStream buffOutputStream = new BufferedOutputStream(outputStream);
			// Start writing from buffer after the filename
			buffOutputStream.write(buffer, counter + 1, bytesRead);
			
			// Close the buffer
			buffOutputStream.close();
			// Write stuff into output stream
			outputStream.flush();
			
			// Success
			System.out.println("File '" + fileName + "' downloaded from client.");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	/* 
	 * Obtains directory information, then sends to client.
	 */
	public static void showDirectory(Socket socket, InputStream inputStream, String filename)
	{
		try
		{	
			// Create output stream to send info out on 
			OutputStream outputStream = socket.getOutputStream();
			
			// Get current working directory
			String workingDir = Paths.get(".").toAbsolutePath().normalize().toString();
			File folder = new File(workingDir + "/MyFile_server");
						
			// Get list of files in directory
			File[] listOfFiles = folder.listFiles();
			
			// Create string builder so we can combine all File[] things together
			StringBuilder info = new StringBuilder();
			for (File i : listOfFiles)
			{
				info.append(i);
				info.append("\n");
			}
			// Write out information to client
			outputStream.write(info.toString().getBytes());
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	/* 
	 * Sends file from server folder to the client's client folder.
	 */
	public static void downloadFile(Socket socket, InputStream inputStream, String filename)
	{
		try
		{
			// Get current working directory
			String workingDir = Paths.get(".").toAbsolutePath().normalize().toString();
			File folder = new File(workingDir + "/MyFile_server");
			
			// create buffer to hold info
			byte[] buffer = new byte[1024];
			inputStream.read(buffer, 0, buffer.length);
			// Start reading contents in
			String fileName = new String(buffer, 0, buffer.length);
							
			// Get list of files in directory
			File[] listOfFiles = folder.listFiles();
			
			// Search to see if filename exists
			Boolean found = false;
			for (File i : listOfFiles)
			{
				if (i.toString().trim().equalsIgnoreCase(
						(workingDir + "/MyFile_server/" + fileName + "\n").trim() ))
					found = true;
			}
			
			// If file is found, send to client
			if (found == true)
			{
				// Set up file objects
				File uploadFile = null;
				// Set directory, set up file to be sent
				uploadFile = new File("Myfile_Server/" + fileName.trim());
				
				// Create buffer for information storage
				buffer = new byte[(int) uploadFile.length()];
				BufferedInputStream buffInputStream = new BufferedInputStream(new FileInputStream(uploadFile));
				buffInputStream.read(buffer, 0, buffer.length);
				
				// Create output stream in socket
				OutputStream outputStream = socket.getOutputStream();
				// Write filename
				outputStream.write((fileName + "\n").getBytes());
				// Insert file contents into buffer
				outputStream.write(buffer, 0, buffer.length);
				buffInputStream.close();
				outputStream.flush();
			}
			else
			{
				throw new RuntimeException();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}

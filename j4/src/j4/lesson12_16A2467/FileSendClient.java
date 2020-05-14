package j4.lesson12_16A2467;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import j4.lesson12.EchoServer;

// Use "EchoServer.java" with this client program

public class FileSendClient 
{
	public final static String FILE = "Myfile_client/file1.txt";
	public final static int BUFFER_SIZE = 8192;
	
	public static void main(String[] args) throws IOException
	{	
		InetAddress addr = InetAddress.getByName("localhost");
		
		// Create socket so that we can connect to server
		Socket socket = new Socket(addr, EchoServer.PORT);
		System.out.println("Client is connecting to the Server ---");
		
		File file;
		BufferedReader in = null;
		PrintWriter out = null;
		BufferedReader reader = null;
		
		// Try to send file through socket
		try
		{
			// Get the input stream
			in = new BufferedReader( 
				new InputStreamReader(socket.getInputStream()));
			// Get the output stream 	
			out = new PrintWriter( 
				new BufferedWriter( 
					new OutputStreamWriter(
						socket.getOutputStream())), true); 
			
			// Open up the file
			file = new File(FILE);
			reader = new BufferedReader(new FileReader(file));
			
			// Read line from file, then send to server
			String line;
			while ((line = reader.readLine()) != null)
			{
				// Send line to server
				out.println(line);
				
				// wait for response
				line = in.readLine();
				// then print response
				System.out.println("Server=>Client: " + line);	// print response
			}
			System.out.println("Client has exited normally. ---");
		}
		finally
		{
			reader.close();
			in.close();
			socket.close();
			System.out.println("Socket closed.");
		}
	}
}

package j4.lesson14_16A2467;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ThreadServerFX 
{
	// Server constants
	static final int PORT = 12345;
	static int clientCount = 0;
	
	static ServerSocket serverSocket;
	
	public static void main(String[] args)
	{
		try
		{
			serverSocket = new ServerSocket(PORT);
			System.out.println("Waiting for connection...");
			while (true)
			{
				// Server waits here until client connects
				Socket socket = serverSocket.accept();
				// Session for client starts here
				clientCount++;
				// Get client info
				String clientInfo = socket.getRemoteSocketAddress().toString();
				// Create new thread to handle new client
				ClientThread clientThread = new ClientThread(socket, clientCount, clientInfo);
				clientThread.start();
			}
		}
		catch (Exception e) {e.printStackTrace();}
	}
	
}

class ClientThread extends Thread
{
	private Socket socket;
	private String clientID;
	private BufferedReader br;
	private PrintWriter pw;
	
	private String clientName;
	private String clientInfo;
	
	private Boolean socketClosed = false;
	
	// Main constructor for thread
	public ClientThread(Socket socket, int id, String info)
	{
		this.socket = socket;
		this.clientID = "Client" + id;
		this.clientInfo = info;
	}
	
	// Code that is run when thread starts
	public void run()
	{
		// First, attempt instantiating BufferedReader and PrintWriter
		try
		{
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(new BufferedWriter
					(new OutputStreamWriter(socket.getOutputStream())), true);
			// Wait for username from user
			clientName = br.readLine();
			System.out.println(clientID + " " + clientName + " Info: " + clientInfo);
			// Send server output to user
			pw.println(clientID + " " + clientName + " Info: " + clientInfo);
			pw.flush();
		}
		catch (Exception e) {e.printStackTrace();}
		
		// If successful, run session for client on loop
		while (socketClosed == false)
		{
			try
			{		
				// Wait once again for input, this time it's a message
				String clientMsg = br.readLine();
				if (clientMsg.equalsIgnoreCase(null))
					throw new SocketException();
				// Once input is received, generate display message
				String displayMsg = clientID + " " + clientName + "=> " + clientMsg;
				// Print to server console
				System.out.println(displayMsg);
				// Send display message back to user
				pw.println(displayMsg);
				// Flush PrintWriter buffer
				pw.flush();
			}
			catch (Exception e)
			{
				try
				{
					// Close all readers and writers
					br.close();
					pw.close();	
					// Close socket with client
					socket.close();
					System.out.println("Bye " + clientID + " " + clientName);
					socketClosed = true;
				}
				catch (Exception ex) {ex.printStackTrace();}
			}
		}
	}
}

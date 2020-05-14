package j4.lesson12;

import java.net.*; 
import java.io.*; 
import java.util.Scanner;

public class EchoClient { 

  public static void main(String[] args) throws IOException 
  {
	InetAddress addr = InetAddress.getByName("localhost");

	// Create a socket and connect to remote server
	Socket socket = new Socket(addr, EchoServer.PORT); 
	System.out.println("Client is connecting to the Server ---");
	System.out.println("Type in console; Type END for closing connection");
	
	try { 
		// Get the input stream
		BufferedReader in = new BufferedReader( 
			new InputStreamReader(socket.getInputStream())); 
		// Get the output stream 	
		PrintWriter out = new PrintWriter( 
			new BufferedWriter( 
				new OutputStreamWriter(
					socket.getOutputStream())), true); 

		while (true) { 
			System.out.print("Client=>Server: ");
	        @SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
	        String line = input.nextLine();
			out.println(line);
			String str = in.readLine();
			System.out.println("Server=>Client: " + str);
			if (line.equalsIgnoreCase("END")) break;
		}
		System.out.println("Client is closed ---");
	} finally { socket.close(); } 
  }
}


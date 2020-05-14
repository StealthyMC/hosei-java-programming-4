package j4.lesson12;

import java.io.*;
import java.net.*; 

public class EchoServer  { 

	public static final int PORT = 9999; 

	public static void main(String[] args) throws IOException  {
		ServerSocket listen_sock = new ServerSocket(PORT); 
		System.out.println("Server runs and waits for connection ..."); 
		try { 
		    // Block until a connection occurs: 
			Socket socket = listen_sock.accept(); 
			try { 
				// Connection accepted: 
				BufferedReader in = new BufferedReader( 
						new InputStreamReader( socket.getInputStream()) );

				//Output is automatically flushed by PrintWriter: 
				PrintWriter out = new PrintWriter( 
						new BufferedWriter(
								new OutputStreamWriter( socket.getOutputStream())), true); 

				//echo back and forth
				while (true) { 
					String str = in.readLine(); 
					if (str.equalsIgnoreCase("END")) {
						out.println("BYE");
						break; }
					out.println(str.toUpperCase()); 
				}
			} finally { 
				System.out.println("Server has closed ..."); 
				socket.close(); 
			} 
		} finally { listen_sock.close(); } 
	}
}

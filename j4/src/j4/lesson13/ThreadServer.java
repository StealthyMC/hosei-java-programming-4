package j4.lesson13;

import java.io.*;
import java.net.*;

public class ThreadServer
{
   static final int PORT = 12345;
   static int clientCount = 0;

   public static void main(String[] args)
   {
      try{
         ServerSocket ss = new ServerSocket(PORT);
         System.out.println("Waiting for connection ...");
         while(true){
               Socket sc = ss.accept();
               clientCount++;
               String clientInfo = sc.getRemoteSocketAddress().toString();
               System.out.println("Client" + clientCount + " Info: " + clientInfo);
               ClientThread ct = new ClientThread(sc, clientCount);
               ct.start();
          }
      }
      catch(Exception e){
         e.printStackTrace();
      }
   }
}

class ClientThread extends Thread {
	   private Socket sc;
	   private String clientID;
	   private BufferedReader br;
	   private PrintWriter pw;

	   public ClientThread(Socket s, int id)
	   {
	      sc = s;
	      clientID = "Client" + id;
	   }
	   public void run()
	   {
	      try{
	         br = new BufferedReader
	            (new InputStreamReader(sc.getInputStream()));
	         pw = new PrintWriter
	            (new BufferedWriter(new OutputStreamWriter(sc.getOutputStream())), true);
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }
	      
	      while(true){
	    	  try {  
	               String clientMsg = br.readLine();
	               String DisplayMsg = clientID + "=> " + clientMsg;
	               System.out.println(DisplayMsg);	               
	               pw.println(DisplayMsg);
	               pw.flush();
	               }
	    	  catch(Exception e){
	    		  try{
	                  br.close();
	                  pw.close();
	                  sc.close();
	                  System.out.println("Bye");
	                  break;
	                 }
	               catch(Exception ex){
	                  ex.printStackTrace();
	               }
	          }
	      }
	 }
}
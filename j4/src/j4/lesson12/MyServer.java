package j4.lesson12;

import java.io.*;
import java.net.*;

public class MyServer
{
   static final int PORT = 10000;
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
   
               BufferedReader br = new BufferedReader( 
						new InputStreamReader( sc.getInputStream()) ); 
               String clientMsg = br.readLine();
               
               System.out.println("Client " + clientCount + " Info: " + clientInfo);
               System.out.println("Client Message:  " + clientMsg);
               
               PrintWriter pw = new PrintWriter
                  (new BufferedWriter
                  (new OutputStreamWriter(sc.getOutputStream())));
               pw.println("Welcome:  " + sc.getRemoteSocketAddress());
               pw.println("Your Msg: " + clientMsg);
               pw.flush();
               pw.close();
               sc.close();
          }
      }
      catch(Exception e){
         e.printStackTrace();
      }
   }
}


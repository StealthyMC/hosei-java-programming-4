package j4.lesson13;

import java.io.*;
import java.net.*;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.*;

public class ThreadClient extends Application implements Runnable
{
   public static final String HOST = "localhost";
   public static final int PORT = 12345;
	
   private TextField tf;
   private TextArea ta;
   private Button bt;
   
   private Thread th;

   private Socket sc;
   private BufferedReader br;
   private PrintWriter pw;

   public static void main(String[] args)
   {
      launch(args);
   }
   public void start(Stage stage)throws Exception
   {
      ta = new TextArea();
      bt = new Button("接続");
	  tf = new TextField();
	  tf.setPrefWidth(300);
      
      HBox hb = new HBox();
      hb.getChildren().add(bt);
      hb.getChildren().add(tf);
      hb.setSpacing(10);

      BorderPane bp = new BorderPane();
       
      bp.setCenter(ta);
      bp.setBottom(hb);
      
      bt.setOnAction(new ConnectEventHandler());
      tf.setOnAction(new MsgEventHandler());

      Scene sc = new Scene(bp, 400, 400);
      stage.setScene(sc);
      stage.setTitle("ThreadClient");
      stage.show();
      //Create a thread
      th = new Thread(this);
   }
   
   public void run()
   {
      try{
         sc = new Socket(HOST, PORT);
         ta.appendText("Connect to server ("+HOST+":"+PORT+")\n");
         br = new BufferedReader
            (new InputStreamReader(sc.getInputStream()));
         pw = new PrintWriter
            (new BufferedWriter(new OutputStreamWriter(sc.getOutputStream())));

         while(true){
            try{
               String str = br.readLine();
               ta.appendText(str + "\n");
            }
            catch(Exception e){
               br.close();
               pw.close();
               sc.close();
               break;
            }
         }
      }
      catch(Exception e){
         e.printStackTrace();
      }
   }
   
   //イベントハンドラクラス
   class ConnectEventHandler implements EventHandler<ActionEvent>
   {
      public void handle(ActionEvent e)
      {
          //接続
          th.start();
      }
   }
   
   class MsgEventHandler implements EventHandler<ActionEvent>
   {
      public void handle(ActionEvent e)
      {
         if (th.isAlive()){
           try {
             String str = tf.getText();
             pw.println(str);
             pw.flush();
             tf.setText("");
            }
           catch(Exception ex){
            ex.printStackTrace();
           }
         }
         else ta.appendText("Not connect to server! Click <接続>\n");
      }
   }
}

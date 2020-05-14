package j4.lesson12;

import java.io.*;
import java.net.*;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.event.*;

public class MyClient extends Application
{
   public static final String HOST = "localhost";
   public static final int PORT = 10000;

   private TextArea ta;
   private Button bt;
   private Label lb;
   private TextField tf;
   

   public static void main(String[] args)
   {
      launch(args);
   }
   public void start(Stage stage)throws Exception
   {
	   //コントロールの作成
	   ta = new TextArea("Click button 接続 to connect to Server \n");
	   bt = new Button("接続");
       lb = new Label("メッセージ");
       tf = new TextField("Type message here");
       tf.setPrefColumnCount(15);
         
       HBox hb = new HBox();
       hb.getChildren().add(bt);
       hb.getChildren().add(lb);
       hb.getChildren().add(tf);
       hb.setSpacing(10);

       //ペインの作成
       BorderPane bp = new BorderPane();

       //ペインへの追加
       bp.setCenter(ta);
       bp.setBottom(hb);

       //イベントハンドラの登録
       bt.setOnAction(new ConnectEventHandler());

       Scene sc = new Scene(bp, 400, 300);
       stage.setScene(sc);
       stage.setTitle("MyClient");
       stage.show();
   }

   //イベントハンドラクラス
   class ConnectEventHandler implements EventHandler<ActionEvent>
   {
      public void handle(ActionEvent e)
      {
         try{
            Socket sc = new Socket(HOST, PORT);
            PrintWriter pw = new PrintWriter
                    (new BufferedWriter
                    (new OutputStreamWriter(sc.getOutputStream())));
            pw.println(tf.getText());
            pw.flush();
            BufferedReader  br = new BufferedReader
                 (new InputStreamReader(sc.getInputStream()));
            String str = null;
            while((str = br.readLine()) != null)
              ta.appendText(str + "\n");
            ta.appendText("------- \n");
            ta.appendText("Cnnection closed, click 接続 to reconnect \n");
            tf.setText("");
            br.close();
            sc.close();
         }
         catch(Exception ex){
            ex.printStackTrace();
         }
      }
   }
}


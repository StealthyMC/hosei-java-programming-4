package j4.lesson12;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.web.*;
import javafx.event.*;

public class BrowseWeb extends Application
{
   private Button bt;
   private TextField tf;
   private WebView wv;

   public static void main(String[] args)
   {
      launch(args);
   }
   public void start(Stage stage)throws Exception
   {
      //コントロールの作成
	  bt = new Button("読込");
      tf = new TextField("http://www.hosei.ac.jp/");
      wv = new WebView();

      //ペインの作成
      BorderPane bp = new BorderPane();
      VBox vb = new VBox();
 
      //ペインへの追加
      vb.getChildren().addAll(bt, tf);

      bp.setTop(vb);
      bp.setCenter(wv);
      
      //イベントハンドラの登録
      bt.setOnAction(new URLEventHandler());
      tf.setOnAction(new URLEventHandler());

      //シーンの作成
      Scene sc = new Scene(bp, 600, 600);
      
      //ステージへの追加
      stage.setScene(sc);

      //ステージの表示
      stage.setTitle("BrowseWeb");
      stage.show();
   }

   //イベントハンドラクラス
   class URLEventHandler implements EventHandler<ActionEvent>
   {
      public void handle(ActionEvent e)
      {
         try{
            WebEngine we = wv.getEngine();
            we.load(tf.getText());
         }
         catch(Exception ex){
            ex.printStackTrace();
         }
      }
   }
}


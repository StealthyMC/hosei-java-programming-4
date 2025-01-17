package j4.lesson13;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CountThreadFX extends Application {
	Label lb1, lb2;
	TextArea ta1, ta2;
	Button start, stop, suspend, resume;
	Count ct1, ct2;
	Thread ct1Thread, ct2Thread;
	ProgressBar bar1, bar2;
	static int aliveThreadCount;

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) throws Exception {
		lb1 = new Label();
		lb2 = new Label();
		lb1.setFont(Font.font("Century", FontWeight.BOLD, 20));
		lb2.setFont(Font.font("Century", FontWeight.BOLD, 20));

		ta1 = new TextArea();
		ta2 = new TextArea();
		ta1.setMinHeight(290);
		ta2.setMinHeight(290);

		bar1 = new ProgressBar();
		bar2 = new ProgressBar();
		bar1.setProgress(0);
		bar2.setProgress(0);
		bar1.setMinWidth(280); //pixel
		bar2.setMinWidth(280); //pixel

		start = new Button("Start Count");
		stop = new Button("Stop Count");
		suspend = new Button("Suspend");
		resume = new Button("Resume");

		start.setOnAction(new ButtonEventHandler());
		stop.setOnAction(new ButtonEventHandler());
		suspend.setOnAction(new ButtonEventHandler());
		resume.setOnAction(new ButtonEventHandler());
		stop.setDisable(true);
		suspend.setDisable(true);
		resume.setDisable(true);

		ct1 = new Count(ta1, "Count-up Thread", bar1, lb1);
		ct2 = new Count(ta2, "Count-down Thread", bar2, lb2);

		VBox vb1 = new VBox();
		vb1.getChildren().addAll(ta1, bar1, lb1);
		vb1.setAlignment(Pos.CENTER);
		vb1.setSpacing(5);
		VBox vb2 = new VBox();
		vb2.getChildren().addAll(ta2, bar2, lb2);
		vb2.setAlignment(Pos.CENTER);
		vb2.setSpacing(5);
		BorderPane bp = new BorderPane();
		HBox hb1 = new HBox();
		hb1.getChildren().addAll(vb1, vb2);
		hb1.setSpacing(20);

		HBox hb2 = new HBox();
		hb2.getChildren().addAll(start, stop, suspend, resume);
		hb2.setSpacing(20);
		hb2.setAlignment(Pos.CENTER);

		bp.setBottom(hb1);
		bp.setCenter(hb2);

		Scene sc = new Scene(bp, 650, 400);
		stage.setScene(sc);
		stage.setTitle("CountThreadFX");
		stage.show();
	}

	class ButtonEventHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			Button tmp = (Button) e.getSource();

			if (tmp.getText().equals("Start Count")) {
				ta1.setText("Count-up Thread" + " is starting\n");
				ta2.setText("Count-down Thread" + " is starting\n");
				bar1.setProgress(0);
				bar2.setProgress(0);
				lb1.setText("");
				lb2.setText("");

				ct1Thread = new Thread(ct1);
				ct2Thread = new Thread(ct2);
				ct1Thread.start();
				ct2Thread.start();
				aliveThreadCount = 2;
				start.setDisable(true);
				stop.setDisable(false);
				suspend.setDisable(false);

			} else if (tmp.getText().equals("Stop Count")) {
				ct1Thread.stop();
				ct2Thread.stop();
				stop.setDisable(true);
				start.setDisable(false);
				suspend.setDisable(true);
				resume.setDisable(true);

			} else if (tmp.getText().equals("Suspend")) {
				ct1Thread.suspend();
				ct2Thread.suspend();
				suspend.setDisable(true);
				resume.setDisable(false);
			} else {
				ct1Thread.resume();
				ct2Thread.resume();
				suspend.setDisable(false);
				resume.setDisable(true);
			}

		}
	}

	public class Count implements Runnable {
		TextArea ta_tmp;
		String s_tmp;
		ProgressBar bar;
		Label lb;
		int i = 0;
		long ms;

		public Count(TextArea ta, String s, ProgressBar bar, Label lb) {
			ta_tmp = ta;
			s_tmp = s;
			this.bar = bar;
			this.lb = lb;
		}

		@Override
		public void run() {
			ms = System.currentTimeMillis();
			for (i = 0; i < 10; i++) {
				try {
					Thread.sleep((int) (Math.random() * 1000));
				} catch (InterruptedException e) {
				}
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						double progress = ((double) i) / 10;
						bar.setProgress(progress);
						long ms_tmp = System.currentTimeMillis() - ms;
						lb.setText(ms_tmp / 1000 + "s " + ms_tmp % 1000 + "ms");

						if (s_tmp.equalsIgnoreCase("Count-up Thread"))
							ta_tmp.appendText(s_tmp + "  i = " + i + "\n");
						else
							ta_tmp.appendText(s_tmp + "  j = " + (10 - i) + "\n");
						if (i == 10) {
							ta_tmp.appendText("\n" + s_tmp + " is finished");
							aliveThreadCount--;
							if(aliveThreadCount == 0){
								start.setDisable(false);
								stop.setDisable(true);
								suspend.setDisable(true);
							}
						}
					}
				});
			}

		}
	}
}


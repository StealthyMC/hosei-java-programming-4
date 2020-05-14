package j4.lesson13;


import javax.swing.*;
import java.awt.Font;

public class CountThreadSwingRun {
	public static void main(String[] args) {
		ThreadJFrameRun cttf1 = new    
				ThreadJFrameRun("Count-up Thread");
		ThreadJFrameRun cttf2 = new 
				ThreadJFrameRun("Count-down Thread");
		Thread ctt1 = new Thread(cttf1);
	    Thread ctt2 = new Thread(cttf2);
		ctt1.start();
		ctt2.start();
	}
}

class ThreadJFrameRun extends JFrame implements Runnable {
   JTextArea ta;
   String threadName; 

   ThreadJFrameRun(String s) {
	   super(s);
	   threadName = s;
	   ta = new JTextArea(50, 50);
	   ta.setFont(new Font("Century", Font.PLAIN, 20));
	   add(ta);
	   setSize(350,410);
	   if (threadName.equalsIgnoreCase("Count-up Thread"))
	        this.setLocation(600, 200);
	   else this.setLocation(1000, 200);
	   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   setVisible(true);
	   }

   public void run() {
	   ta.append(threadName + " is starting\n\n");
	   for (int i = 0; i < 10; i++) {  
	       try {Thread.sleep((int)(Math.random()* 1000));}
	       catch(InterruptedException e ) {};
	       if(threadName.equalsIgnoreCase("Count-up Thread"))
	            ta.append(threadName + "  i = " + i + "\n");
	       else ta.append(threadName + "  j = " + (9-i) + "\n"); 
	   }
	   ta.append("\n"+threadName + " is finished\n");
	}
}
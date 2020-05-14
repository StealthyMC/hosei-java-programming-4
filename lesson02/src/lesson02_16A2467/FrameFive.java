package lesson02_16A2467;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class FrameFive extends JFrame
{
	static Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
	static int width = (int)screensize.getWidth();
	static int height = (int)screensize.getHeight();
	
	FrameFive(String s, int x, int y, int w, int h)
	{
		setTitle(s);
		setLocation(x, y);
		setSize(w, h);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public static void main(String args[])
	{
		FrameFive fm1 = new FrameFive("Frame1", 0, 0, (int)(width * 0.33), (int)(height * 0.33)); 
		fm1.getContentPane().setBackground(Color.YELLOW);
		
		FrameFive fm2 = new FrameFive("Frame2", (int)(width * 0.66), 0, (int)(width * 0.33), (int)(height * 0.33)); 
		fm2.getContentPane().setBackground(Color.GREEN);
		
		FrameFive fm3 = new FrameFive("Frame3", (int)(width * 0.33), (int)(height * 0.33), (int)(width * 0.33), (int)(height * 0.33)); 
		fm3.getContentPane().setBackground(Color.RED);
		
		FrameFive fm4 = new FrameFive("Frame4", 0, (int)(height * 0.66), (int)(width * 0.33), (int)(height * 0.33)); 
		fm4.getContentPane().setBackground(Color.BLUE);

		FrameFive fm5 = new FrameFive("Frame5", (int)(width * 0.66), (int)(height * 0.66), (int)(width * 0.33), (int)(height * 0.33)); 
		fm5.getContentPane().setBackground(Color.BLACK);
	}
}

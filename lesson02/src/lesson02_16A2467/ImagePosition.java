package lesson02_16A2467;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class ImagePosition 
{
	public static String[] colorImgList = {"img/Red.png", "img/Green.png", "img/Blue.png",
										"img/Yellow.png", "img/Black.png"};
	public static final String[] printOrder = {BorderLayout.CENTER, BorderLayout.LINE_END,
										BorderLayout.PAGE_END, BorderLayout.LINE_START,
										BorderLayout.PAGE_START};
	public static void showColors(int start)
	{
		JFrame fm = new JFrame("ImagePosition");
		fm.setSize(400, 400);
		fm.setLayout(new BorderLayout(20, 20));
		fm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int centerColorIndex = start;
		for (int i = 0; i < 5; i++, centerColorIndex++)
		{
		    ImageIcon colorImgIcon = new ImageIcon(colorImgList[centerColorIndex]);
		    JLabel colorImgLabel = new JLabel(colorImgIcon);
			fm.add(colorImgLabel, printOrder[i]);
			// reset color index to prevent out-of-bounds error
			if (centerColorIndex > 3)
				centerColorIndex = -1;
		}
		// finishing up
		fm.setLocationRelativeTo(null);
		fm.setVisible(true);
	}
	
	public static void main(String args[])
	{
		String inputCenterColor = JOptionPane.showInputDialog("Center Color: r, g, b, y, or bk?");
		int centerColor = 0;
		switch (inputCenterColor)
		{
		case "r": 	centerColor = 0; break;
		case "g": 	centerColor = 1; break;
		case "b": 	centerColor = 2; break;
		case "y": 	centerColor = 3; break;
		case "bk": 	centerColor = 4; break;
		default:
			JOptionPane.showMessageDialog(null, inputCenterColor + " is wrong input!\nPlease enter r, g, b, y or bk.\n"
										+ "Re-execute the program and input again.");
			return;
		}
		showColors(centerColor);
	}
}

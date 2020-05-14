package lesson02_16A2467;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Multiplication99Table 
{
	public static void main(String[] args)
	{
		// set up main frame
		JFrame fm = new JFrame();
		fm.setTitle("Multiplication99Table");
		fm.setSize(400, 400);
		fm.setLayout(new GridLayout(9, 9, 1, 1));
		fm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		// add labels
		JLabel lb[] = new JLabel[81];
		int row = 1;
		int value = 0;
		for (int i = 0; i < 9; i++)
		{
			for (int j = 1; j < 10; j++)
			{
				value = j * row;
				lb[i] = new JLabel(Integer.toString(value));
				lb[i].setFont(new Font("Times New Roman", Font.BOLD, 24));
				fm.add(lb[i]);
			}
			row++;
		}
		// finishing up
		fm.setLocationRelativeTo(null);
		fm.setVisible(true);
	}
}

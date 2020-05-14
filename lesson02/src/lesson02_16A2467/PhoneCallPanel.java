package lesson02_16A2467;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class PhoneCallPanel 
{
	public static void main(String args[])
	{
		// set up main frame
		JFrame fm = new JFrame();
		fm.setTitle("PhoneCallPanel");
		fm.setSize(300, 300);
		fm.setFont(new Font("Times New Roman", Font.BOLD, 24));
		fm.setLayout(new GridLayout(4, 3, 6, 6));
		fm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btn[] = new JButton[13];
		for (int i = 1; i <= 12; i++)
		{
			if (i <= 9)
			{
				btn[i] = new JButton(Integer.toString(i));
			}
			else if (i == 10)
			{
				btn[i] = new JButton("0");
			}
			else if (i == 11)	// phone call button
			{
				ImageIcon imgSrc = new ImageIcon("img/phoneCall.png");
				Image imgScale = imgSrc.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
				ImageIcon imgFinal = new ImageIcon(imgScale);
				btn[i] = new JButton(imgFinal);
			}
			else	// hang up button
			{
				ImageIcon imgSrc = new ImageIcon("img/phoneHangUp.png");
				Image imgScale = imgSrc.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
				ImageIcon imgFinal = new ImageIcon(imgScale);
				btn[i] = new JButton(imgFinal);
			}
			btn[i].setFont(new Font("Times New Roman", Font.BOLD, 24));
			fm.add(btn[i]);
		}
		// finishing up
		fm.setLocationRelativeTo(null);
		fm.setVisible(true);
	}
}

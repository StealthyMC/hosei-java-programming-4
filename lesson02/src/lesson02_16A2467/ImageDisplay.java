package lesson02_16A2467;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class ImageDisplay 
{
	public static void main(String args[])
	{
		// set up main frame
		JFrame fm = new JFrame();
		fm.setTitle("ImageDisplay");
		fm.setSize(600, 600);
		fm.setLayout(new FlowLayout(FlowLayout.LEFT));
		fm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// create un-scaled picture
	    ImageIcon p1 = new ImageIcon("img/sakura1.jpg");
	    JLabel img1 = new JLabel(p1);
		fm.add(img1);
		// then make scaled one next
		Image p2ScaledInstance = p1.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
		ImageIcon p2Scaled = new ImageIcon(p2ScaledInstance);
	    JLabel img2 = new JLabel(p2Scaled);
		fm.add(img2);
		
		// create other pictures now
		
	    ImageIcon p3 = new ImageIcon("img/sakura2.jpg");
		Image p3ScaledInstance = p3.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
		ImageIcon p3Scaled = new ImageIcon(p3ScaledInstance);
	    JLabel img3 = new JLabel(p3Scaled);
		fm.add(img3);
	    
	    ImageIcon p4 = new ImageIcon("img/sakura3.png");
		Image p4ScaledInstance = p4.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
		ImageIcon p4Scaled = new ImageIcon(p4ScaledInstance);
	    JLabel img4 = new JLabel(p4Scaled);
		fm.add(img4);

		// finishing up
		fm.setLocationRelativeTo(null);
		fm.setVisible(true);
	}
}

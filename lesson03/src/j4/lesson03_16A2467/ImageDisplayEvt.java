package j4.lesson03_16A2467;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImageDisplayEvt extends JFrame implements ActionListener
{
	// create necessary parts for contents of frame
	JPanel panelPreview, panelIcons;
	JButton button1, button2, button3;
	JLabel labelPreview;
	
	ImageIcon pic1Src = new ImageIcon("img/sakura1.jpg");
	ImageIcon pic2Src = new ImageIcon("img/sakura2.jpg");
	ImageIcon pic3Src = new ImageIcon("img/sakura3.png");
	
	ImageIcon pic1 = new ImageIcon(pic1Src.getImage().getScaledInstance(525, 350, Image.SCALE_SMOOTH));
	ImageIcon pic2 = new ImageIcon(pic2Src.getImage().getScaledInstance(525, 350, Image.SCALE_SMOOTH));
	ImageIcon pic3 = new ImageIcon(pic3Src.getImage().getScaledInstance(525, 350, Image.SCALE_SMOOTH));

	ImageIcon picCurrent = pic1;
	
	ImageDisplayEvt(String title)
	{
		// set up main frame first
		setTitle(title);
		setSize(640, 600);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Rectangle window = getBounds();
		
		// === create preview panel === //
		panelPreview = new JPanel();
		panelPreview.setLayout(new BorderLayout());
		panelPreview.setPreferredSize(new Dimension((int)(window.width * .90), (int)(window.height * .70)));
		
		
		labelPreview = new JLabel(pic1);
		panelPreview.add(labelPreview);
		// === === === //
		
		// === create image buttons === //
		panelIcons = new JPanel();
		panelIcons.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelIcons.setPreferredSize(new Dimension((int)(window.width * .70), (int)(window.height * .25)));
		
		ImageIcon pic1Scaled = new ImageIcon(pic1.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH));
		button1 = new JButton(pic1Scaled);
		button1.setBorderPainted(false);
		button1.addActionListener(this);
		panelIcons.add(button1);
		
		ImageIcon pic2Scaled = new ImageIcon(pic2.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH));
		button2 = new JButton(pic2Scaled);
		button2.setBorderPainted(false);
		button2.addActionListener(this);
		panelIcons.add(button2);
		
		ImageIcon pic3Scaled = new ImageIcon(pic3.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH));
		button3 = new JButton(pic3Scaled);
		button3.setBorderPainted(false);
		button3.addActionListener(this);
		panelIcons.add(button3);

		// === === === //
		
		// add all panels to the main frame
		add(panelPreview, "North");
		add(panelIcons, "South");
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource().equals(button1))
		{
			picCurrent = pic1;
		}
		if (e.getSource().equals(button2))
		{
			picCurrent = pic2;
		}
		if (e.getSource().equals(button3))
		{
			picCurrent = pic3;
		}
		labelPreview.setIcon(picCurrent);
	}
	
	public static void main(String args[])
	{
		JFrame frame = new ImageDisplayEvt("ImageDisplayEvt");
		frame.setVisible(true);
	}
}
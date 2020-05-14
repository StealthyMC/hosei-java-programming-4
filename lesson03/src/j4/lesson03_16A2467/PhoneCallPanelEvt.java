package j4.lesson03_16A2467;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PhoneCallPanelEvt extends JFrame implements ActionListener
{
	// initiate required panels
	JPanel panelButtons, panelScreen;
	JLabel labelScreen;
	Font font = new Font("Times New Roman", Font.BOLD, 24);
	String labelDisplay = new String("");
	
	String phoneStatus = new String("input");
	
	PhoneCallPanelEvt(String title)
	{
		// set up primary frame
		setTitle(title);
		setSize(300, 300);
		setFont(font);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// === create button panel, add buttons to it === //
		panelButtons = new JPanel(new GridLayout(4, 3, 6, 6));
		Rectangle r = getBounds();

		panelButtons.setPreferredSize(new Dimension(r.width, (int) (r.height * .7)));
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
				btn[i].setActionCommand("call");
			}
			else	// hang up button
			{
				ImageIcon imgSrc = new ImageIcon("img/phoneHangUp.png");
				Image imgScale = imgSrc.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
				ImageIcon imgFinal = new ImageIcon(imgScale);
				btn[i] = new JButton(imgFinal);
				btn[i].setActionCommand("hang");
			}
			btn[i].setFont(new Font("Times New Roman", Font.BOLD, 24));
			btn[i].addActionListener(this);
			panelButtons.add(btn[i]);
		}
		// === === === //
		
		// === initiate screen for input === //
		// first, make the label
		labelScreen = new JLabel(labelDisplay, SwingConstants.CENTER);
		labelScreen.setFont(font);
		// then make the panel
		panelScreen = new JPanel(new BorderLayout());
		panelScreen.setBackground(Color.WHITE);
		panelScreen.setPreferredSize(new Dimension(r.width, (int) (r.height * .20)));
		// then insert label into new panel
		panelScreen.add(labelScreen, "North");
		// === === === //
		add(panelScreen, "North");
		add(panelButtons, "South");
	}
	
	public static void main(String args[])
	{		
		JFrame frame = new PhoneCallPanelEvt("PhoneCallPanelEvt");
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		JButton button = (JButton) e.getSource();
		String command = button.getActionCommand();
		
		if (!command.equalsIgnoreCase("call") && !command.equalsIgnoreCase("hang") 
				&& phoneStatus != "call")
		{
			labelDisplay += command;
			labelScreen.setText(labelDisplay);
		}
		else if (command.equalsIgnoreCase("call") && phoneStatus != "call")
		{
			phoneStatus = "call";
			labelDisplay = "Calling " + labelDisplay;
			labelScreen.setText(labelDisplay);
		}
		else if (command.equalsIgnoreCase("hang") && phoneStatus == "call")
		{
			phoneStatus = "input";
			labelDisplay = "HangUp";
			labelScreen.setText(labelDisplay);
			labelDisplay = "";
		}
		else if (command.equalsIgnoreCase("hang"))
		{
			labelDisplay = "";
			labelScreen.setText(labelDisplay);
		}
	}
}

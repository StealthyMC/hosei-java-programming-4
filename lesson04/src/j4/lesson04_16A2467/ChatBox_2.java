package j4.lesson04_16A2467;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class ChatBox_2 extends JFrame implements ActionListener, ItemListener
{
	JTextField jtfUserA, jtfUserB;
	JTextArea jtaChat;
	JMenuBar menuBar;
	
	// create radio groups
	JRadioButton[] radioButtonColor;
	JRadioButton[] radioButtonSize;
	ButtonGroup buttonGroupColor, buttonGroupSize;
	
	// create separate panel to store related components
	JPanel panelInput, panelRadios;
	
	Color[] colorSet;
	int[] sizeSet;
	
	ChatBox_2(String title)
	{
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		// initialize font
		Font fontMain = new Font("Century", Font.PLAIN, 24);
		
		// create input boxes for users A & B
		jtfUserA = new JTextField("", 15);
		jtfUserA.setFont(fontMain);
		jtfUserA.addActionListener(this);
		jtfUserA.setActionCommand("User A");
		
		jtfUserB = new JTextField("", 15);
		jtfUserB.setFont(fontMain);
		jtfUserB.addActionListener(this);
		jtfUserB.setActionCommand("User B");
		
		// then add the input boxes to their respective panel
		panelInput = new JPanel(new FlowLayout());
		panelInput.add(jtfUserA);
		panelInput.add(jtfUserB);
		
		// create chat box area
		jtaChat = new JTextArea("", 14, 20);
		jtaChat.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		jtaChat.setFont(new Font("Century", Font.PLAIN, 18));
		// create scroll bar for chat area
		JScrollPane chatScrollPane = new JScrollPane(jtaChat);
				
		// create menu bar component
		menuBar = new JMenuBar();
		
		// create the menus for the menu bar
		JMenu menuBarBG = new JMenu("Background Color");
		menuBarBG.setFont(fontMain);
		menuBarBG.addActionListener(this);
		JMenuItem menuItemWhite = new JMenuItem("White");
		JMenuItem menuItemYellow = new JMenuItem("Yellow");
		JMenuItem menuItemGray = new JMenuItem("Gray");
		menuItemWhite.addActionListener(this);
		menuItemYellow.addActionListener(this);
		menuItemGray.addActionListener(this);
		menuBarBG.add(menuItemWhite);
		menuBarBG.add(menuItemYellow);
		menuBarBG.add(menuItemGray);
		// then add menu to menu bar component
		menuBar.add(menuBarBG);
		
		// create panel to store radio buttons
		panelRadios = new JPanel(new FlowLayout());
		panelRadios.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		// create button group for color radios
		buttonGroupColor = new ButtonGroup();
		radioButtonColor = new JRadioButton[]
					{
							new JRadioButton("Black", true),
							new JRadioButton("Blue"),
							new JRadioButton("Red")
					};
		colorSet = new Color[] {Color.BLACK, Color.BLUE, Color.RED};
		for (int i = 0; i < radioButtonColor.length; i++)
		{
			radioButtonColor[i].setFont(fontMain);
			buttonGroupColor.add(radioButtonColor[i]);
			panelRadios.add(radioButtonColor[i]);
			radioButtonColor[i].addItemListener(this);
		}
		
		// create button group for size radios
		buttonGroupSize = new ButtonGroup();
		radioButtonSize = new JRadioButton[]
					{
							new JRadioButton("Small", true),
							new JRadioButton("Medium"),
							new JRadioButton("Large")
					};
		sizeSet = new int[] {18, 24, 32};
		for (int i = 0; i < radioButtonColor.length; i++)
		{
			radioButtonSize[i].setFont(fontMain);
			buttonGroupSize.add(radioButtonSize[i]);
			panelRadios.add(radioButtonSize[i]);
			radioButtonSize[i].addItemListener(this);
		}
		
		// finally, add and pack all elements
		setJMenuBar(menuBar);
		add(chatScrollPane, "North");
		add(panelInput, "Center");
		add(panelRadios, "South");
		pack();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String chatOutput = "";
		Object obj = e.getSource();
		
		// if the action performed was by an object of type JTextField
		if (obj instanceof JTextField)
		{
			if (e.getActionCommand().equals("User A"))
			{
				chatOutput = chatOutput + "A: " + jtfUserA.getText();
				jtfUserA.setText("");
			}
			else if (e.getActionCommand().equals("User B"))
			{
				chatOutput = chatOutput + "B: " + jtfUserB.getText();
				jtfUserB.setText("");
			}
			if ((jtaChat.getText().equals("")) == false)
				jtaChat.append("\n");
			jtaChat.append(chatOutput);
		}
		// if the action was performed by the following menu items...
		else if (e.getActionCommand().equals("White"))
			jtaChat.setBackground(Color.WHITE);
		else if (e.getActionCommand().equals("Yellow"))
			jtaChat.setBackground(Color.YELLOW);
		else if (e.getActionCommand().equals("Gray"))
			jtaChat.setBackground(Color.GRAY);
	}
	
	public void itemStateChanged(ItemEvent e)
	{
		// go through each radio, check which is selected
		
		// check colors
		for (int i = 0; i < radioButtonColor.length; i++)
		{
			if (radioButtonColor[i].isSelected())
			{
				jtaChat.setForeground(colorSet[i]);
				jtaChat.setText(jtaChat.getText());
			}
		}
		// check sizes
		for (int i = 0; i < radioButtonSize.length; i++)
		{
			if (radioButtonSize[i].isSelected())
			{
				// create new font to match selected size
				Font font = new Font("Century", Font.PLAIN, sizeSet[i]);
				jtaChat.setFont(font);
				jtaChat.setText(jtaChat.getText());
			}
		}
	}
	
	public static void main(String args[])
	{
		JFrame frame = new ChatBox_2("ChatBox_2");
		frame.setVisible(true);
	}
}

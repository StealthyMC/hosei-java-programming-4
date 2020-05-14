package j4.lesson04_16A2467;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class ChatBox_1 extends JFrame implements ActionListener
{
	JTextField jtfUserA, jtfUserB;
	JTextArea jtaChat;
	
	// create separate panel to store related components
	JPanel panelInput;
	
	ChatBox_1(String title)
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
		// then, add the panel to the main panel/window
		add(panelInput, "South");
		
		// create chat box area
		jtaChat = new JTextArea("", 10, 20);
		jtaChat.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		jtaChat.setFont(fontMain);
		// create scroll bar for chat area
		JScrollPane chatScrollPane = new JScrollPane(jtaChat);
		// add chat box to the main panel/window
		add(chatScrollPane, "Center");
		
		
		// finally, pack all elements
		pack();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String chatOutput = "";
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
	
	public static void main(String args[])
	{
		JFrame frame = new ChatBox_1("ChatBox_1");
		frame.setVisible(true);
	}
}

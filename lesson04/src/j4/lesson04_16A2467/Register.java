package j4.lesson04_16A2467;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Register extends JFrame implements ActionListener, KeyListener
{
	JPanel panelInput, panelDisplay;
	JTextField jtfName;
	JTextArea jtaDisplay;
	JPasswordField jpfPassword, jpfConfirm;
	JLabel labelName, labelPassword, labelConfirm;
	
	Register(String title)
	{
		// first, initialize window
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 100, 600, 200);
		setLayout(new FlowLayout());
		
		// create panel for input fields
		panelInput = new JPanel();
		panelInput.setLayout(new GridLayout(3, 2, 10, 10));
		// create components to be added to panel
		labelName = new JLabel("Name:");
		labelPassword = new JLabel("Password:");
		labelConfirm = new JLabel("Confirm:");
		jtfName = new JTextField("", 10);
		jtfName.addKeyListener(this);
		jpfPassword = new JPasswordField("", 10);
		jpfPassword.addKeyListener(this);
		jpfConfirm = new JPasswordField("", 10);
		jpfConfirm.addKeyListener(this);
		// then initiate the panel, add components to it
		panelInput.add(labelName);
		panelInput.add(jtfName);
		panelInput.add(labelPassword);
		panelInput.add(jpfPassword);
		panelInput.add(labelConfirm);
		panelInput.add(jpfConfirm);
		
		// now create text area
		jtaDisplay = new JTextArea("", 10, 25);
		
		// add elements to the main frame
		add(panelInput);
		add(jtaDisplay);
	}
	
	public void actionPerformed(ActionEvent e){}
	
	
	public void keyPressed(KeyEvent e) 
	{
		jtaDisplay.setText("");
		if (e.getKeyCode() == 10)
		{
			Pattern pattern = Pattern.compile("[0-9]*");
			
			if (pattern.matcher(jtfName.getText()).matches() && jtfName.getText().equals("") == false)
			{
				jtaDisplay.append("User Name can't be numeric\n");
			}
			
			if (jtfName.getText().length() <= 4
					|| jtfName.getText().length() >= 16
					|| jpfPassword.getPassword().length <= 4
					|| jpfPassword.getPassword().length >= 16
					|| jpfConfirm.getPassword().length <= 4
					|| jpfConfirm.getPassword().length >= 16
					)
			{
				jtaDisplay.append("User Name or Password overlength or too short!\n");
			}
			
			if (Arrays.equals(jpfPassword.getPassword(), jpfConfirm.getPassword()) == false)
			{
				jtaDisplay.append("Password don't match!\n");
			}
		}
	}
	
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	
	public static void main(String args[])
	{
		JFrame frame = new Register("Register");
		frame.setVisible(true);
	}
}

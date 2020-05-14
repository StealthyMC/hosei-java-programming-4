package j4.lesson04_16A2467;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

public class ChatBox_3 extends JFrame implements ActionListener, KeyListener, ItemListener 
{
	// Create three primary components for frame
	JPanel panelCheckBox;
	JTextArea jtaInput;
	JTextArea jtaDisplay;
	// create radio buttons for radio panel
	JCheckBox jcbItalic, jcbBold;
	
	// create fonts that will be used
	Font fontPlain = new Font("Century", Font.PLAIN, 24);
	Font fontItalic = new Font("Century", Font.ITALIC, 24);
	Font fontBold = new Font("Century", Font.BOLD, 24);
	Font fontBoth = new Font("Century", Font.ITALIC | Font.BOLD, 24);
	
	ChatBox_3(String title)
	{
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		// Create input text field
		jtaInput = new JTextArea("", 10, 20);
		jtaInput.setFont(fontPlain);
		jtaInput.addKeyListener(this);
		
		JScrollPane jcpInput = new JScrollPane(jtaInput);
		jtaInput.setLineWrap(true);
		
		// Create display text area
		jtaDisplay = new JTextArea("", 10, 20);
		jtaDisplay.setFont(fontPlain);
		jtaDisplay.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		
		JScrollPane jcpDisplay = new JScrollPane(jtaDisplay);
		jtaDisplay.setLineWrap(true);
		
		// Create panel for check box area
		panelCheckBox = new JPanel();
		panelCheckBox.setLayout(new BorderLayout());
		jcbItalic = new JCheckBox("Italic");
		jcbItalic.setFont(fontItalic);
		jcbItalic.addItemListener(this);
		jcbBold = new JCheckBox("Bold");
		jcbBold.setFont(fontBold);
		jcbBold.addItemListener(this);
		
		panelCheckBox.add(jcbItalic, "North");
		panelCheckBox.add(jcbBold, "Center");
		
		// Finally, add all components to stage area
		add(jcpInput, "West");
		add(jcpDisplay, "Center");
		add(panelCheckBox, "East");
		pack();
	}
	
	public void actionPerformed(ActionEvent e) {}
	
	public void keyTyped(KeyEvent e) 
	{
		jtaDisplay.setText(jtaInput.getText());
		
	}
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	
	public void itemStateChanged(ItemEvent e)
	{
		// Italic only
		if (jcbItalic.isSelected() && jcbBold.isSelected() == false)
			jtaDisplay.setFont(fontItalic);
		// Bold only
		else if (jcbBold.isSelected() && jcbItalic.isSelected() == false)
			jtaDisplay.setFont(fontBold);
		// Both
		else if (jcbItalic.isSelected() && jcbBold.isSelected())
			jtaDisplay.setFont(fontBoth);
		// Normal font
		else
			jtaDisplay.setFont(fontPlain);
	}
	
	public static void main(String args[])
	{
		JFrame frame = new ChatBox_3("ChatBox_3");
		frame.setVisible(true);
	}
}

package j4.lesson04_16A2467;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MouseDrawShapeComboBox extends JFrame 
implements ActionListener, MouseListener, ItemListener
{
	// initiate necessary components for window
	// instruction label
	JLabel jlLabel;
	// create combo boxes
	String[] stringModes = {"Line", "Circle", "Rect"};
	String[] stringColors = {"Blue", "Red", "Green", "Yellow", "Black"};
	JComboBox<String> jcbShapeType = new JComboBox<String>(stringModes); 
	JComboBox<String> jcbShapeColor = new JComboBox<String>(stringColors);
	JPanel panelComboBox;
	
	// create graphic object
	DrawShape shape;
	// variables to maintain settings for graphics object
	int mode = 0, color = 0;
	int x = 0, y = 0, w = 0, h = 0;
	
	MouseDrawShapeComboBox(String title)
	{
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setSize(650, 500);
		addMouseListener(this);
		
		// create shape that will be drawn
		shape = new DrawShape();
		
		// finally, create the check boxes in their own panel
		panelComboBox = new JPanel(new FlowLayout());
		panelComboBox.add(jcbShapeType);
		panelComboBox.add(jcbShapeColor);
		jcbShapeType.addItemListener(this);
		jcbShapeColor.addItemListener(this);
		
		// add all components to frame
		add(shape, "Center");
		add(panelComboBox, "South");	
	}
	
	public static void main(String args[])
	{
		JFrame frame = new MouseDrawShapeComboBox("MouseDrawShapeComboBoxOption");
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
	}
	
	public void mousePressed(MouseEvent e)
	{
		x = e.getPoint().x;
		if (mode == 2)		// if the mode is set to 'Rect'
			y = e.getPoint().y - 25;
		else
			y = e.getPoint().y;
		w = e.getPoint().x;
		h = e.getPoint().y;
	}
	public void mouseReleased(MouseEvent e)
	{
		if (mode != 0)	// if the mode is set to either 'Circle' or 'Rect'
		{
			// calculate width and height
			w = Math.abs(e.getPoint().x - x);
			h = Math.abs(e.getPoint().y - y);
			// fix the x and y position if the mouse doesn't draw from left to right
			if (e.getPoint().x < x)
				x = e.getPoint().x;
			if (e.getPoint().y < y)
				y = e.getPoint().y;
		}
		else
		{
			w = e.getPoint().x;
			h = e.getPoint().y;
		}
		shape.drawShape(mode, color, x, y, w, h);
	}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	public void itemStateChanged(ItemEvent e)
	{
		mode = jcbShapeType.getSelectedIndex();
		color = jcbShapeColor.getSelectedIndex();
	}
	
	class DrawShape extends JPanel
	{
		String eventSource = "";
		
		// store current shape and color in integers
		int mode = 0, color = 0;
		
		int x, y, w, h;
		
		public DrawShape()
		{
			setBackground(Color.WHITE);
		}
		
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.setFont(new Font("Arial", Font.PLAIN, 24));
			g.drawString("Click mouse to draw a shape", 170, 40);
			
			// set color for shape to be drawn
			switch (color)
			{
			case 0: g.setColor(Color.BLUE); break;
			case 1:	g.setColor(Color.RED); break;
			case 2: g.setColor(Color.GREEN); break;
			case 3: g.setColor(Color.YELLOW); break;
			case 4: g.setColor(Color.BLACK); break;
			}
			
			// switch shape to draw based on current mode
			switch (mode)
			{
			case 0:
				g.drawLine(x, y, w, h); 
				break;
			case 1:
				g.drawOval(x, y, w, h);
				break;
			case 2:
				g.drawRect(x, y, w, h);
				break;
			}
		}
		
		public void drawShape(int mode, int color, int x, int y, int w, int h)
		{
			this.mode = mode;
			this.color = color;
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
			repaint();
		}
	}
}

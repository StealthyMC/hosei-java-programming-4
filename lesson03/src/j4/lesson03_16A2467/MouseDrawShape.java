package j4.lesson03_16A2467;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MouseDrawShape extends JFrame implements MouseListener, ActionListener
{
	// square that will be drawn
	DrawSquare square;
	// main panel for program
	JPanel btnPanel;
	// label for "click mouse to ..."
	JLabel labelGuide;

	// buttons for selecting fill / no fill
	JButton btnFill, btnNoFill;
	// images for buttons
	ImageIcon iconSrcFill = new ImageIcon("img/squareFill.png");
	ImageIcon iconSrcNoFill = new ImageIcon("img/squareNoFill.png");
	Image imgScaleFill, imgScaleNoFill;

	// variables to hold properties of square
	int x, y, w, h;
	// hold command for drawing in string
	String btnCommand;

	public MouseDrawShape()
	{
		setTitle("MouseDrawShape");
		setSize(500, 500);
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		btnCommand = "";

		addMouseListener(this);
		setLayout(new BorderLayout());	// set layout of main panel

		// create square to draw, then add to panel
		square = new DrawSquare();
		add(square, "Center");

		// === set up button panel === //
		btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout());
		btnPanel.setPreferredSize(new Dimension(100, 70));
		btnPanel.setBackground(Color.WHITE);
		// scale images, then make buttons
		imgScaleFill = iconSrcFill.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		imgScaleNoFill = iconSrcNoFill.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		btnFill = new JButton("Fill", new ImageIcon(imgScaleFill));
		btnNoFill = new JButton("NoFill", new ImageIcon(imgScaleNoFill));
		// set properties for buttons
		btnFill.setOpaque(false);
		btnFill.setContentAreaFilled(false);
		btnFill.setBorderPainted(false);
		btnNoFill.setOpaque(false);
		btnNoFill.setContentAreaFilled(false);
		btnNoFill.setBorderPainted(false);
		// give listeners, then add to button panel
		btnFill.addActionListener(this);
		btnNoFill.addActionListener(this);
		btnPanel.add(btnFill);
		btnPanel.add(btnNoFill);
		// finally, add button panel to main panel
		add(btnPanel, "South");
		// === === === //

	}

	public static void main(String args[])
	{
		JFrame frame = new MouseDrawShape();
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		btnCommand = e.getActionCommand();
	}

	public void mousePressed(MouseEvent e)
	{
		x = e.getPoint().x;
		y = e.getPoint().y - 25;
		w = e.getPoint().x;
		h = e.getPoint().y;
	}

	public void mouseReleased(MouseEvent e)
	{
		w = Math.abs(e.getPoint().x - x);
		h = Math.abs(e.getPoint().y - y) - 20;
		square.drawSquare(btnCommand, x, y, w, h);
	}

	public void mouseClicked(MouseEvent e)
	{

	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}


	// === Drawing graphics stuff === //
	class DrawSquare extends JPanel
	{
		String eventSource = "";
		int x = 250, y = 250, w = 0, h = 0;

		public DrawSquare()
		{
			setBackground(Color.WHITE);
		}
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.setFont(new Font("Arial", Font.PLAIN, 24));
			g.drawString("Click mouse to draw a square", 80, 30);
			g.setColor(Color.BLUE);
			if(eventSource.equalsIgnoreCase("Fill"))
				g.fillRect(x, y, w, h);
			else if (eventSource.equalsIgnoreCase("NoFill"))
				g.drawRect(x, y, w, h);
		}
		public void drawSquare(String st, int x, int y, int w, int h)
		{
			eventSource = st;
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
			repaint();
		}
	}
}

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

public class MouseDrawShapeColor extends JFrame implements MouseListener, ActionListener
{
	// objects for each shape
	DrawShape shape;
	// panel to hold buttons
	JPanel btnPanel;
	// label for instructions at top of screen
	JLabel labelGuide;
	
	// JButtons and their images
	JButton btnLine, btnCircle, btnSquare;
	JButton btnRed, btnBlue, btnGreen, btnYellow, btnBlack;
	
	ImageIcon iconSrcLine = new ImageIcon("img/Line.png");
	ImageIcon iconSrcCircle = new ImageIcon("img/Square.png");
	ImageIcon iconSrcSquare = new ImageIcon("img/Rect.png");
	
	ImageIcon iconSrcRed = new ImageIcon("img/Red.png");
	ImageIcon iconSrcBlue = new ImageIcon("img/Blue.png");
	ImageIcon iconSrcGreen = new ImageIcon("img/Green.png");
	ImageIcon iconSrcYellow = new ImageIcon("img/Yellow.png");
	ImageIcon iconSrcBlack = new ImageIcon("img/Black.png");
	
	Image imgScaleLine, imgScaleCircle, imgScaleSquare;
	Image imgScaleRed, imgScaleBlue, imgScaleGreen, imgScaleYellow, imgScaleBlack;
	
	// variables to hold shape properties
	int x, y, w, h;
	
	// hold command for current action
	String cmd, colorSelect = "red";
	
	public MouseDrawShapeColor()
	{
		setTitle("MouseDrawShapeColor");
		setSize(800, 600);
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cmd = "";
		addMouseListener(this);
		
		// set layout of main panel
		setLayout(new BorderLayout());
		
		// create shape drawer, then add to panel
		shape = new DrawShape();
		add(shape, "Center");
		
		// === set up button panel === //
		btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout());
		btnPanel.setPreferredSize(new Dimension(700, 70));
		btnPanel.setBackground(Color.WHITE);
		
		imgScaleLine = iconSrcLine.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		imgScaleCircle = iconSrcCircle.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		imgScaleSquare = iconSrcSquare.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		imgScaleRed = iconSrcRed.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		imgScaleBlue = iconSrcBlue.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		imgScaleGreen = iconSrcGreen.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		imgScaleYellow = iconSrcYellow.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		imgScaleBlack = iconSrcBlack.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		
		btnLine = new JButton(new ImageIcon(imgScaleLine));
		btnLine.setOpaque(false);
		btnLine.setBorderPainted(false);
		btnLine.setActionCommand("line");
		btnLine.addActionListener(this);
		btnCircle = new JButton(new ImageIcon(imgScaleCircle));
		btnCircle.setOpaque(false);
		btnCircle.setBorderPainted(false);
		btnCircle.setActionCommand("circle");
		btnCircle.addActionListener(this);
		btnSquare = new JButton(new ImageIcon(imgScaleSquare));
		btnSquare.setOpaque(false);
		btnSquare.setBorderPainted(false);
		btnSquare.setActionCommand("square");
		btnSquare.addActionListener(this);
		
		btnRed = new JButton(new ImageIcon(imgScaleRed));
		btnRed.setOpaque(false);
		btnRed.setBorderPainted(false);
		btnRed.setActionCommand("red");
		btnRed.addActionListener(this);
		btnBlue = new JButton(new ImageIcon(imgScaleBlue));
		btnBlue.setOpaque(false);
		btnBlue.setBorderPainted(false);
		btnBlue.setActionCommand("blue");
		btnBlue.addActionListener(this);
		btnGreen = new JButton(new ImageIcon(imgScaleGreen));
		btnGreen.setOpaque(false);
		btnGreen.setBorderPainted(false);
		btnGreen.setActionCommand("green");
		btnGreen.addActionListener(this);
		btnYellow = new JButton(new ImageIcon(imgScaleYellow));
		btnYellow.setOpaque(false);
		btnYellow.setBorderPainted(false);
		btnYellow.setActionCommand("yellow");
		btnYellow.addActionListener(this);
		btnBlack = new JButton(new ImageIcon(imgScaleBlack));
		btnBlack.setOpaque(false);
		btnBlack.setBorderPainted(false);
		btnBlack.setActionCommand("black");
		btnBlack.addActionListener(this);
		
		btnPanel.add(btnLine); btnPanel.add(btnCircle); btnPanel.add(btnSquare);
		btnPanel.add(btnRed); btnPanel.add(btnBlue); btnPanel.add(btnGreen);
		btnPanel.add(btnYellow); btnPanel.add(btnBlack);
		
		add(btnPanel, "South");
		// === === === //
		
	}
	
	public static void main(String args[])
	{
		JFrame frame = new MouseDrawShapeColor();
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource().equals(btnLine) || e.getSource().equals(btnCircle) || 
				e.getSource().equals(btnSquare))
		{
			cmd = e.getActionCommand();
		}
		else
		{
			colorSelect = e.getActionCommand();
		}
	}
	
	// === Mouse listening events === //
	public void mousePressed(MouseEvent e) 
	{
		x = e.getPoint().x;
		if (cmd.equalsIgnoreCase("square"))
			y = e.getPoint().y - 25;
		else
			y = e.getPoint().y;
		w = e.getPoint().x;
		h = e.getPoint().y;
	}
	public void mouseReleased(MouseEvent e)
	{
		if (cmd.equalsIgnoreCase("square") || cmd.equalsIgnoreCase("circle"))
		{
			w = Math.abs(e.getPoint().x - x);
			h = Math.abs(e.getPoint().y - y) - 20;
		}
		else
		{
			w = e.getPoint().x;
			h = e.getPoint().y - 20;
		}
		shape.drawShape(cmd, colorSelect, x, y, w, h);
	}
	public void mouseClicked(MouseEvent e) 
	{
		
	}
	
	
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	// === === === //
	
	// === Shape classes === //
	
	class DrawShape extends JPanel
	{
		String eventSource = "";
		String color = "";
		int x, y, w, h;
		
		public DrawShape()
		{
			setBackground(Color.WHITE);
		}
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.setFont(new Font("Arial", Font.PLAIN, 24));
			g.drawString("Click mouse to draw a shape", 240, 40);
			
			switch (color)
			{
			case "red":		g.setColor(Color.RED); break;
			case "blue":	g.setColor(Color.BLUE); break;
			case "green":	g.setColor(Color.GREEN); break;
			case "yellow":	g.setColor(Color.YELLOW); break;
			case "black":	g.setColor(Color.BLACK); break;
			}
			
			System.out.println("Event: " + eventSource + " Color: " + color);
			
			if (eventSource.equalsIgnoreCase("line"))
			{
				g.drawLine(x, y, w, h);
			}
			if (eventSource.equalsIgnoreCase("circle"))
			{
				g.drawOval(x, y, w, h);
			}
			if (eventSource.equalsIgnoreCase("square"))
			{
				g.drawRect(x, y, w, h);
			}
		}
		public void drawShape(String st, String clr, int x, int y, int w, int h)
		{
			eventSource = st;
			color = clr;
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
			repaint();
		}
	}

}

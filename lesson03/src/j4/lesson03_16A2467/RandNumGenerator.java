package j4.lesson03_16A2467;

import javax.swing.*;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel; 

public class RandNumGenerator extends JFrame implements ActionListener
{
	// initiate label
	JLabel labelGraphTitle;
	static JButton btNormal, btUniform;
	// initiate panels
	GraphPanel panelGraph;
	JPanel panelButtons;
	// used for listening events
	String eventSource;
	
	int x, y;
	
	RandNumGenerator(String title)
	{
		setTitle(title);
		setBounds(0, 0, 500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout()); 	// master layout of frame
		
		// establish button section
		panelButtons = new JPanel(new FlowLayout());
		panelButtons.setBackground(Color.green);
		btNormal = new JButton("Normal Distribution");
		btUniform = new JButton("Uniform Distribution");
		btNormal.addActionListener(this);
		btUniform.addActionListener(this);
		panelButtons.add(btNormal);
		panelButtons.add(btUniform);
		
		// establish graph panel
		panelGraph = new GraphPanel();
		panelGraph.setPreferredSize(new Dimension(getWidth(), 437));
		
		
		add(panelGraph, "North");
		add(panelButtons, "South");
		
	}
	
	public static void main(String[] args)
	{
		JFrame frame = new RandNumGenerator("RandNumGenerator");
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		eventSource = e.getActionCommand();
		if (e.getSource().equals(btNormal))
		{
			panelGraph.drawShape("normal");
		}
		if (e.getSource().equals(btUniform))
		{
			panelGraph.drawShape("uniform");
		}
	}
	
	class GraphPanel extends JPanel
	{
		String eventSource = "";
		public GraphPanel()
		{
			setBackground(Color.white);
		}
		
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.setColor(Color.blue);
			g.drawRect(100, 80, 300, 300);
			g.setFont(new Font("Times New Roman", Font.PLAIN, 24));
			g.drawString("Random Number Generator", 120, 50);
			g.setColor(Color.red);
			
			if (eventSource.equalsIgnoreCase("normal"))
			{
				Random r = new Random();
				for (int i = 0; i < 1000; i++)
				{
					x = (int) Math.round(r.nextGaussian() * 40 + 250);
					y = (int) Math.round(r.nextGaussian() * 40 + 250);
					// if you get a negative number, add iteration
					if ((x < 100) || (x > 400) || (y < 80) || (y > 380))
					{
						i -= 1;
						continue;
					}
					g.fillOval(x, y, 5, 5);
				}
			}
			
			if (eventSource.equalsIgnoreCase("uniform"))
			{
				Random r = new Random();
				for (int i = 0; i < 1000; i++)
				{
					x = (int) Math.round(r.nextDouble() * (400 - 100 + 1) + 100);
					y = (int) Math.round(r.nextDouble() * (380 - 80 + 1) + 80);
					g.fillOval(x, y, 5, 5);
				}
			}
		}
		
		public void drawShape(String st)
		{
			eventSource = st;
			repaint();
		}
	}
}

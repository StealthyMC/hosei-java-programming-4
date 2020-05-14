package j4_lesson01_16A2467;
import java.util.Scanner;

interface Shape
{
	public float area();
}

class Rectangle implements Shape
{
	public float m_length;
	public float m_width;
	
	Rectangle(float length, float width)
	{
		m_length = length;
		m_width = width;
	}
	
	public float area()
	{
		return (m_length * m_width);
	}
}
class Circle implements Shape
{
	public float m_radius;
	
	Circle(float radius)
	{
		m_radius = radius;
	}
	
	public float area()
	{
		return (float)((m_radius * m_radius) * Math.PI);
	}
}
class Cuboid extends Rectangle implements Shape
{
	public float m_depth;
	
	Cuboid(float length, float width, float depth)
	{
		super(length, width);
		m_depth = depth;
	}
	
	public float area()
	{
		return (float)(2 * (m_length * m_width) +
						2 * (m_length * m_depth) + 
						2 * (m_depth * m_width));
	}
}

public class ShapeTest 
{
	public static void main(String[] args)
	{
		// Create new scanner object
		Scanner reader = new Scanner(System.in);
		System.out.println("Shape Creation\nRectangle -- 1  Circle -- 2  Cuboid -- 3 ");
		
		// First, get desired shape
		int input = reader.nextInt();
		
		if (input < 1 || input > 3)
		{
			System.out.println("Error in input.");
			reader.close();
			return;
		}
		
		float length, width, depth, radius;
		
		switch (input)
		{
		case 1:		// rectangle
			// Get values for length and width.
			System.out.println("Input Length:");
			length = reader.nextInt();
			System.out.println("Input Width:");
			width = reader.nextInt();
			
			Rectangle rectangle = new Rectangle(length, width);
			System.out.println("Rectangle: length = " + rectangle.m_length 
					+ " width = " + rectangle.m_width + " area = " + rectangle.area());
		break;
		case 2:		// circle
			System.out.println("Input Radius:");
			radius = reader.nextInt();
			
			Circle circle = new Circle(radius);
			System.out.println("Circle: radius = " + circle.m_radius
					+ " area = " + circle.area());
		break;
		case 3:		// cuboid
			// Get values for length, width, height.
			System.out.println("Input Length:");
			length = reader.nextInt();
			System.out.println("Input Width:");
			width = reader.nextInt();
			System.out.println("Input Depth:");
			depth = reader.nextInt();
			
			Cuboid cuboid = new Cuboid(length, width, depth);
			System.out.println("Rectangle: length = " + cuboid.m_length 
					+ " width = " + cuboid.m_width + " depth = " + cuboid.m_depth
					+ " area = " + cuboid.area());
		break;
		}
		reader.close();
	}
}

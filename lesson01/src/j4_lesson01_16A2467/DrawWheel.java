package j4_lesson01_16A2467;
import processing.core.PApplet;

public class DrawWheel extends PApplet
{
	public void setup()
	{
		size(500, 500);
	}
	public void draw()
	{
		background(255);
		strokeWeight(7);	
		fill(200, 200, 200);	// large filled circle color
		stroke(250, 180, 0);	// large circle edge color

		ellipse(250, 250, 400, 400);
		
		// all following values are based on initial ellipse values
		strokeWeight(3);
		stroke(255, 0, 0);
		// vertical line
		line((float)(500 * 0.5), (float)(500 * 0.11), (float)(500 * 0.5), (float)(500 * 0.89));
		// horizontal line
		line((float)(500 * 0.11), (float)(500 * 0.5), (float)(500 * 0.89), (float)(500 * 0.5));
		// diagonal lines (left to right)
		line((float)(500 * 0.3), (float)(500 * 0.16), (float)(500 * 0.7), (float)(500 * 0.84));
		line((float)(500 * 0.15), (float)(500 * 0.33), (float)(500 * 0.85), (float)(500 * 0.67));
		// diagonal lines (right to left)
		line((float)(500 * 0.7), (float)(500 * 0.16), (float)(500 * 0.3), (float)(500 * 0.84));
		line((float)(500 * 0.85), (float)(500 * 0.33), (float)(500 * 0.15), (float)(500 * 0.67));
		
		// draw inner circle
		fill(130, 200, 70);	// small filled circle color
		stroke(80, 120, 140);	// small circle edge color
		ellipse(250, 250, (float)(400 * 0.18), (float)(400 * 0.18));
	}
	
	public static void main(String[] args)
	{
		PApplet.main(DrawWheel.class.getName());
	}
}

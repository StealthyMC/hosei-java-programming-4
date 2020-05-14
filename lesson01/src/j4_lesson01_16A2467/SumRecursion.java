package j4_lesson01_16A2467;
import javax.swing.JOptionPane;

public class SumRecursion {
	
	static int sum(int n)
	{
		if (n <= 0)
			return 0;
		else
		{
			return n + sum(n - 2);
		}
	}
	
	public static void main(String[] args)
	{
		String input_n = JOptionPane.showInputDialog("Please input an integer n(n > 9):");
		int n = Integer.parseInt(input_n);
		
		if (n <= 9)
		{
			JOptionPane.showMessageDialog(null, "Error in input.");
			return;
		}
		
		String count_msg = null;
		if ((n % 2) > 0)	// odd 
			count_msg = "1 + 3 + ... + ";
		else				// even 
			count_msg = "2 + 4 + ... + ";

		String final_output = count_msg + Integer.toString(n) + " = " + Integer.toString(sum(n));
		JOptionPane.showMessageDialog(null, final_output);
		
	}

}

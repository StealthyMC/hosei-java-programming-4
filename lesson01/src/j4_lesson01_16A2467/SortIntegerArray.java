package j4_lesson01_16A2467;

import javax.swing.JOptionPane;

public class SortIntegerArray {
	void Sort(int nums[])
	{
		int temp;
		boolean isSorted = false;
		while (isSorted == false)
		{
			isSorted = true;
			for (int i = 0; i < nums.length - 1; i++)
			{
				if (nums[i] > nums[i + 1])	// if prev is bigger than next
				{
					temp = nums[i + 1];
					nums[i + 1] = nums[i];
					nums[i] = temp;
					isSorted = false;
				}
			}
		}	// done
	}
	
	static String makeIntString(int nums[])
	{
		String int_list = "";
		for (int i = 0; i < nums.length; i++)
		{
			if (i < nums.length - 1)
				int_list = int_list + Integer.toString(nums[i]) + ":";
			else
				int_list = int_list + Integer.toString(nums[i]);
		}	
		return int_list;
	}
	
	public static void main(String[] args)
	{
		String input = JOptionPane.showInputDialog("整数値の列？ (i1:i2: ...:in)");
		String[] inputs = input.split(":");
		int[] nums = new int[inputs.length];
		for (int i = 0; i < inputs.length; i++)
		{
			nums[i] = Integer.parseInt(inputs[i]);
		}
		SortIntegerArray obj = new SortIntegerArray();
		obj.Sort(nums);
		
		// Finally display integers by making string for final message
		String output_msg = "Original Integers: " + input
		+ "\nSorted Integers: " + makeIntString(nums);
		JOptionPane.showMessageDialog(null, output_msg);
	}
}

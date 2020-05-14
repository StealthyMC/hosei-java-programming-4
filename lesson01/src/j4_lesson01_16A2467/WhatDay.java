package j4_lesson01_16A2467;
import java.util.Calendar;

import javax.swing.JOptionPane;


public class WhatDay {
	
	static void printDate(int month, int day)
	{
		// Figure out what day of the week it is
		String[] youbi_s = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, month, day);
		int youbi_i = c.get(Calendar.DAY_OF_WEEK);
		System.out.println(youbi_i);
		
		JOptionPane.showMessageDialog(null, "2019年" + month + "月" + day + "日: " + youbi_s[youbi_i]);
	}
	static void printError(int month, int day)
	{
		JOptionPane.showMessageDialog(null, "2019年" + month + "月" + day + "日: Error in date");
	}
	
	public static void main(String[] args)
	{
		String input_month = JOptionPane.showInputDialog("2019年何月？");
		String input_day = JOptionPane.showInputDialog(input_month + " 月の何日？");
		int month = Integer.parseInt(input_month);
		int day = Integer.parseInt(input_day);
		
		if (((month >= 1) && (month <= 12) && (day >= 1)) == true)
		{
			switch (month)
			{
			case 2:
				if (day > 28)
					printError(month, day);
				else
					printDate(month, day);
			break;
			case 1: case 3: case 5: case 7: case 8: case 10: case 12:
				if (day > 31)
					printError(month, day);
				else
					printDate(month, day);
			break;
			case 4: case 6: case 9: case 11:
				if (day > 30)
					printError(month, day);
				else
					printDate(month, day);
			}
		}
		else
		{
			printError(month, day);
		}
		return;
	}
}

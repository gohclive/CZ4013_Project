package client;

import java.util.Scanner;

//to scan and get the user input 
public class GetUserInput {
	// Using Scanner for Getting Input from User
	static Scanner in = new Scanner(System.in);

	public GetUserInput() {
	}

	// to scan for user integer input
	public static int userInputInt() {
		try {
			int a = Integer.parseInt(in.nextLine());
			System.out.println("You entered: " + a);
			return a;
		} catch (Exception e) {
			System.out.println("Invalid integer input, Please try again");
			return userInputInt();
		}
	}

	// scan and get string input from user
	public static String userInputString() {
		try {
			String stg = in.nextLine();
			while (stg.equals("")) {
				System.out.println("Empty input. Please try again.");
				stg = in.nextLine();
			}
			System.out.println("You entered: " + stg);
			return stg;
		} catch (Exception e) {
			System.out.println("Invalid string input, Please try again.");
			return userInputString();
		}

	}

	// float input from user
	public static float userInputFloat() {
		try {
			float fl = in.nextFloat();
			System.out.println("You entered: " + fl);
			return fl;
		} catch (Exception e) {
			System.out.println("Invalid float input, Please try again.");
			return userInputFloat();
		}

	}

	// double input from user
	public static double userInputDouble() {
		try {
			double dI = Double.parseDouble(in.nextLine());
			System.out.println("You entered: " + dI);
			return dI;
		} catch (Exception e) {
			System.out.println("Invalid double input, Please try again.");
			return userInputDouble();
		}
	}

	// close scanner if the client closes
	public static void closeUserInputScanner() {
		in.close();
	}
}

package challengeConnectFour;

import java.util.Scanner;
/**
 * Various methods for getting numbers from the user
 * @author Peter Marley
 *
 */
public class GetNum {
	/**
	 * Scans an int from user. Prints errorMessage String if not an int
	 * 
	 * @param errorMessage A string
	 * @return an int
	 */
	@SuppressWarnings("resource")
	public static int get_int(String errorMessage) {
		Scanner scanner = new Scanner(System.in);
		boolean isAccepted = false;
		int selection = 0;
		System.out.println();
		while (!isAccepted) {
			try {
				selection = Integer.valueOf(scanner.nextLine());
				isAccepted = true;
			} catch (NumberFormatException e) {
				System.out.println(errorMessage);
				//System.out.println(e);
				isAccepted = false;
			} catch (Exception e) {
				System.out.println(errorMessage);
				System.out.println("WARNING! This is a general Exception catch!");
				System.out.println(e);
				isAccepted = false;
			}
		}
		return selection;
	}

	/**
	 * Scans an int between start and end (inclusive) from user
	 * 
	 * @return An int in range start to end inclusive
	 */
	public static int get_ranged_int(int start, int end) {
		int selection = 0;
		while (selection < start || selection > end) {
			selection = get_int("Invalid input. Please enter a number from " + start + " to " + end + ".");
			if (selection < start || selection > end)
				System.out.println("Invalid input. Please enter a number from " + start + " to " + end + ".");
		}
		return selection;
	}
}

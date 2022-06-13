package util;

import java.util.Scanner;

public class ScanUtil {
	
	private static Scanner scanner = new Scanner(System.in);
	
	public static String nextLine() {
		return scanner.nextLine();
	}
	
	public static int nextInt() {
		return Integer.parseInt(scanner.nextLine());
	}

}

package prob04;

import java.util.Scanner;

public class Sol04 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		String str = scanner.nextLine();
		System.out.println(str);
		
		// 첫번쨰 단어 출력 
		System.out.println(str.charAt(1));
		// 길이
		System.out.println(str.length());

		
		scanner.close();
	}
}
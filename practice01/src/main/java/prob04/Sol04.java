package prob04;

import java.util.Scanner;

public class Sol04 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("문자열을 입력하세요 : ");
		String str = scanner.nextLine();

		for (int i = 1; i < str.length() + 1; i++) {
			for (int j = 0; j < i; j++) {
				System.out.print(str.charAt(j));
			}
			System.out.println();
		}
		scanner.close();
	}
}
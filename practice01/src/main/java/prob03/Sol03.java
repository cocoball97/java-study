package prob03;

import java.util.Scanner;

public class Sol03 {
// 삼단논법
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.print("수를 입력하세요 : ");
			int number = scanner.nextInt();
			int sum = 0;
			// 짝수인 경우
			if (number % 2 == 0) {
				for (int i = 1; i <= number; i++) {
					if (i % 2 == 0) {
						sum += i;
					}
				}
				System.out.print("결과값:");
				System.out.println(sum);
			}
			// 홀수
			else {
				for (int j = 1; j <= number; j++) {
					if (j % 2 == 1) {
						sum += j;
					}
				}
				System.out.print("결과값:");
				System.out.println(sum);
			}
		}
	}
}

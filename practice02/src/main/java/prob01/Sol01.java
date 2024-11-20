package prob01;

import java.util.Scanner;

public class Sol01 {
	public static void main(String[] args) {
		
//		// 배열사용법, 배열은 같은 타입
//		int[] as = new int[5];
//		int[] as2 = {10,20,30,40,50};
//		int[] as3 = new int[]{10,20,30,40,50};
		
//		Book[] books = new Book[4];
//		books[0] = new Book();     // 생성하고 나서 출력 가능
//		System.out.println(book[0].title);
		
		Scanner scanner = new Scanner(System.in);
		final int[] MONEYS = {50000, 10000, 5000, 1000, 500, 100, 50, 10, 5, 1};
		int amount = scanner.nextInt();
		
		for (int i = 0; i < MONEYS.length; i++) {
			int count = 0;
			count = amount / MONEYS[i];
			amount -= (MONEYS[i]*count);
			
			System.out.print(MONEYS[i]);
			System.out.print("원 : ");
			System.out.print(count);
			System.out.println("개");
		}
		scanner.close();
 	}
}
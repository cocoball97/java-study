package prob02;

import java.util.Scanner;

public class GoodsTest {
	// 왜 static final이여? static 아니면 내부 애들은 값을 모르나?
	private static final int COUNT_GOODS = 3;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		// 객체 선언이 아닌 배열 선언이라고 생각?
		Goods[] goods = new Goods[COUNT_GOODS];

		for (int i = 0; i < 3; i++) {
			// 상품 입력
			String line = scanner.nextLine();
			String[] info = line.split(" ");

			goods[i] = new Goods();
			goods[i].setName(info[0]);
			goods[i].setPrice(Integer.parseInt(info[1]));
			goods[i].setStock(Integer.parseInt(info[2]));
		}

		// 상품 출력
		for (int i = 0; i < 3; i++) {
			goods[i].printInfo();
		}

		scanner.close();
	}
}
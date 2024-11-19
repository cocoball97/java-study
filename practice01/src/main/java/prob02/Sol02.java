package prob02;

public class Sol02 {
	public static void main(String[] args) {
		for (int i = 10; i < 19; i++) {
			for (int j = 1; j <= i; j++) {
				System.out.print(j);
				System.out.print(" "); // 작은 따옴표는 한글자만 들어감
			}
			System.out.println();
		}
	}
}
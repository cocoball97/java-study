package prob05;

public class Sol05 {
	public static void main(String[] args) {
		for (int i = 1; i <= 100; i++) {
			// 숫자를 string 변환
			String number = String.valueOf(i);

			for (int j = 0; j < number.length(); j++) {
				if (number.charAt(j) == '3'||number.charAt(j) == '6'||number.charAt(j) == '9') {
					System.out.print(number);
					System.out.println(" 짝");
				}
			}
		}
	}
}

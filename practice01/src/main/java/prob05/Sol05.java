package prob05;

public class Sol05 {
	public static void main(String[] args) {
		
		for(int i = 1; i <= 100; i++) {

			String number = String.valueOf(i);
			System.out.println(i);
			
			System.out.print(" ");
			System.out.println(number.length());
			System.out.print("\n");
		}
		
		char c = '6';
		System.out.println(c == '7');
	}
}

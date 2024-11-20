package prob04;

public class Sol04 {

	public static void main(String[] args) {
		char[] c1 = reverse("Hello World");
		printCharArray(c1);

		char[] c2 = reverse("Java Programming!");
		printCharArray(c2);
	}

	public static char[] reverse(String str) {
		char lst[] = new char[str.length()];

		for (int i = 0; i < str.length(); i++) {
			lst[str.length() - 1 - i] = str.charAt(i);
		}
		return lst;
	}

	public static void printCharArray(char[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
		}
		System.out.println();
	}
}
package exception;

import java.io.IOException;

public class MyClassTest {

	public static void main(String[] args) {
		try {
			new Mycalss().danger();
		} catch (IOException e) {
			System.out.println("error:" + e);
		} catch (MyException e) {
			System.out.println("error:" + e);
		}

	}

}

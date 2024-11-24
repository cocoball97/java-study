package exception;

import java.io.IOException;

public class Mycalss {
	public void danger() throws MyException, IOException {
		System.out.println("some code1...");
		System.out.println("some code2...");
		
		
//		// throw 구문이 있으면 위에 함수에도 명시해야함
//		throw new IOException();
		
		if(1-1==0) {
			throw new MyException();
		}
		if(2-2==0) {
			// throw 구문이 있으면 위에 함수에도 명시해야함
			throw new IOException();
			
		}
		
		
		System.out.println("some code3...");
		System.out.println("some code4...");
		
	}
}

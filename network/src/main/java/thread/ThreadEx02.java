package thread;

public class ThreadEx02 {

	public static void main(String[] args) {
		Thread thread1 = new DigitThread();
		Thread thread2 = new AlphabetThread();
		
		// 쓰레드 시작
		thread1.start();
		thread2.start();

	}

}

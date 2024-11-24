package prob03;

public class SoundableTest {

	public static void main(String[] args) {
		printSound(new Cat());
		printSound(new Dog());
		printSound(new Sparrow());
		printSound(new Duck());
	}

	private static void printSound(Soundable soundable) {
		// 프린트 return 값을 넘겨줘야 함..  근데 인터페이스 함수  추상클래스랑 뭔차이냐
		System.out.println(soundable.sound());
	}
}
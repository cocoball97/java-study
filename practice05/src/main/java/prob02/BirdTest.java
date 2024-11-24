package prob02;

public class BirdTest {
	public static void main(String[] args) {
		Bird bird01 = new Duck();
		bird01.setName("꽥꽥이");
		bird01.fly();
		bird01.sing();
		// tostring 오버라이딩에 대해 공부하기
		System.out.println(bird01);
		

		Bird bird02 = new Sparrow();
		bird02.setName("짹짹이");
		bird02.fly();
		bird02.sing();
		System.out.println(bird02);
	}
}

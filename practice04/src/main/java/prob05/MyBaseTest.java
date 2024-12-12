package prob05;

public class MyBaseTest {

	public static void main(String[] args) {
		// 실제 생성된 인스턴스를 확인
		Base base = new MyBase();

		base.service("낮");
		base.service("밤");
		base.service("오후"); 
	}
}
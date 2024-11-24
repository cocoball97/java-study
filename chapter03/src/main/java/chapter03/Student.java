package chapter03;

public class Student extends Person {
	public Student() {
		// 자식 생성자에서 부모 생성자를 명시적으로 호출 하지 않으면 
		// 자동으로 기본 생성자를 호출하게 된다
		// 부모가 먼저 실행됨. 아래 코드가 생략되어있음
		// super();
		System.out.println("Student() Called");
	}
}

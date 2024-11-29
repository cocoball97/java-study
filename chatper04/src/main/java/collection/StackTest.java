package collection;

import java.util.Stack;

public class StackTest {

	public static void main(String[] args) {
		Stack<String> s = new Stack<>();
		
		
		s.push("둘리");
		s.push("마이콜");
		s.push("또치");
		
		while(!s.empty()) {
			String str = s.pop();
			System.out.println(str);
		}
		
		s.push("둘리");
		s.push("마이콜");
		s.push("또치");
		
		System.out.println(s.pop());
		// pop하지 않고 맨위 확인
		System.out.println(s.peek());
		System.out.println(s.pop());
	}

}

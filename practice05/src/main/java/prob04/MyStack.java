package prob04;
// resize : 배열 크기 변경
public class MyStack {
	private int top;
	private String[] buffer;

	public MyStack(int capacity) {
		this.buffer = new String[capacity];
		// 생성자에서 -1인 이유가 뭐지  (구글링)
		this.top = -1;
	}

	public void push(String s) {
		// (구글링)
		if(top == buffer.length-1) {
			resize();
		}
		
		top++;
		buffer[top]=s;
	}

	// throws 란 예외처리 책임을 떠넘김
	// throws, throw 다시 공부하기
	public String pop() throws MyStackException {
		return buffer[top--];
	}

	public boolean isEmpty() {
		// 구글링
		// top이 -1이라면 수정된게 없다는 의미
		// boolean이므로 returun 값이 true, false 인지
		return top == -1;
	}
	// 다른 함수에서 선언된 배열 길이를 어케 늘려요...
	// -> 새로운 배열을 만들어라
	private void resize() {
		String temp[] = new String[buffer.length*2];
		for(int i=0; i<top;i++) {
			temp[i]=buffer[i];
		}
		buffer = temp;
	}	
}
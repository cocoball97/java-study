package prob03;

public class Book {
	private int no;
	private String title;
	private String author;
	private int stateCode;
	
	public int getNo() {
		return no;
	}
	
	public Book(int no, String title, String author) {
		this.no = no;
		this.title = title;
		this.author = author;
		this.stateCode = 1;
	}
	
	public void rent(int num) {
		if(this.no==num) {
			this.stateCode = 0;
			System.out.println(this.title+"이(가) 대여됐습니다.");
		}
	}
	
	public void print() {
		String stock = "재고있음";
		if(this.stateCode==0) {
			stock="대여중";
		}
		System.out.println("책 제목:"+this.title+", 작가:"+this.author+"대여 유무:"+stock);
	}
}

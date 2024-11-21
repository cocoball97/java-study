package prob05;

public class Account {
	private String accountNo;
	private int balance;
	
	public Account(String accountNo, int balance) {
		this.accountNo = accountNo;
		this.balance = balance;
		
		System.out.println(accountNo + " 계좌가 개설 되었습니다.");
		
//		//생성자가 있어야함
//		this(0, 0);
	}
	
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
	public void save(int i) {
		this.balance += i;
		System.out.println(accountNo + " 계좌에 " + balance + "만원이 입금되었습니다.");
		
	}
	
	public void deposit(int i) {
		this.balance -= i;
		System.out.println(accountNo + " 계좌에 " + balance + "만원이 출금되었습니다.");
		
	}
}
package prob06;

public class Money {
	private int amount;
	
	public Money(int amount) {
		setAmount(amount);
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public Money add(Money money) {
		return new Money(this.amount + money.amount);
	}
	
	public Money minus(Money money) {
		return new Money(this.amount - money.amount);
	}
	
	public Money multiply(Money money) {
		return new Money(this.amount * money.amount);
	}
	
	public Money divide(Money money) {
		return new Money(this.amount / money.amount);
	}

	@Override
	public boolean equals(Object obj) {
		// 1. 머니타입 확인
		if (obj instanceof Money) {
			// 2. 금액 동일여부 확인
			if(((Money) obj).amount==this.amount) {
				return true;
			}
		}
		return false;
	}
}

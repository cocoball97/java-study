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

//	@Override
//	public boolean equals(Object obj) {
//		// 1. 머니타입 확인
//      // istanceof 는 서브클래스에서도 true를 반환하기에 상황에 맞게 사용
//		if (obj instanceof Money) {
//			// 2. 금액 동일여부 확인
//          // 값 비교만 한다면 if문을 쓰기보다 return에 boolean 형태로 작성	
//			if(((Money) obj).amount==this.amount) {
//				return true;
//			}
//		}
//		return false;
//	}
	
	@Override
	public boolean equals(Object obj) {
		// this가 객체를 지칭
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Money other = (Money) obj;
		return amount == other.amount;
	}
}

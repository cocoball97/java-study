package prob02;

public class Duck extends Bird {
	
	@Override
	public String toString() {
		return "오리의 이름은 "+this.name+" 입니다.";
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void fly() {
		System.out.println("오리(꽥꽥이)는 날지 않습니다");
	}

	@Override
	public void sing() {
		System.out.println("오리(꽥꽥이)는 소리내어 웁니다");
	}
	
}

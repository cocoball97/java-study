package prob02;

public class Sparrow extends Bird {
	
	@Override
	public String toString() {
		return "참새의 이름은 "+this.name+" 입니다.";
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void fly() {
		System.out.println("참새(짹짹)가 날아다닙니다.");
	}

	@Override
	public void sing() {
		System.out.println("참새(쨱짹)가 소리내어 웁니다");
	}
	
}

package prob06;

public class Sub {
	private int lValue;
	private int rValue;

	public int getlValue() {
		return lValue;
	}

	public void setlValue(int lValue) {
		this.lValue = lValue;
	}

	public int getrValue() {
		return rValue;
	}

	public void setrValue(int rValue) {
		this.rValue = rValue;
	}

	public void setValue(int lValue, int rValue) {
		this.lValue = lValue;
		this.rValue = rValue;
	}

	public int calculate() {
		int result = lValue - rValue;
		return result;
	}
}

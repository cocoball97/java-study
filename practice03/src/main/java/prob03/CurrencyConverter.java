// static 문제
package prob03;

public class CurrencyConverter {
	private static double rate;
	// static 붙어있어 쓸 수 있음
	public static void setRate(double rate) {
		CurrencyConverter.rate = rate;
	}

	public static double toDollar(double krw) {
		return krw / rate;
	}

	public static double toKRW(double dollar) {
		return dollar * rate;
	}
}
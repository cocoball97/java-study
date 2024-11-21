package prob04;

public class StringUtil {
	public static String concatenate(String[] str) {
		
		String result = null;
		
		// 결합하는 stringBuilder, Buffer 공부하기
		StringBuffer sbf = new StringBuffer();
		
		for(int i=0;i<3;i++) {
			sbf.append(str[i]);
			result = sbf.toString();
		}
		return result;
	}

}

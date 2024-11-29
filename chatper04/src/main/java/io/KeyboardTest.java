package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KeyboardTest {

	public static void main(String[] args) {
		BufferedReader br = null;
		try {
			// 1. 기반스트림(표준입력, stdin, System.in)

			// 2. 보조스트림(byte|byte|byte -> char)
			// 이건 왜 안닫음?
			// 데코레이터
			InputStreamReader isr = new InputStreamReader(System.in, "utf-8");

			// 3. 보조스트림(char1|char2|char3|\n -> "char1char2char3")
			// 자원느낌
			br = new BufferedReader(isr);

			String line = null;
			while((line=br.readLine()) != null) {
				if("quit".equals(line)) {
					break;
				}
				System.out.println(line);
				
			};
		} catch (IOException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				// 일어날일없다면 없어도 되는거아님?
				if (br != null) {
					br.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}

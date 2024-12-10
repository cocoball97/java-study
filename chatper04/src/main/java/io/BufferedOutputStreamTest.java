package io;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedOutputStreamTest {

	public static void main(String[] args) {
		BufferedOutputStream bos = null;
		try {
			// 기반 스트림
			// 1바이트씩 출력
			FileOutputStream fos = new FileOutputStream("hello.txt");

			// 보조 스트림
			// 효율적으로 한번에 출력
			bos = new BufferedOutputStream(fos);

			// for(int i = 'A'; i<'Z';i++)
			for (int i = 65; i <= 90; i++) {
				bos.write(i);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found:" + e);
		} catch (IOException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				System.out.println("error:" + e);
			}
		}
	}
}

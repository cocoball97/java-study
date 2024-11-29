package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileCopy {
	public static void main(String[] args) {
		// finally 에서 변수 활용하려면 선언 해야함
		InputStream is = null;
		OutputStream os = null;
		
		try {
			// FileNotFoundException 발동
			is = new FileInputStream("dog.png");
			os = new FileOutputStream("dog.copy.png");
			
			// .read 에서 IOexception 발동
			int data = -1;
			while((data=is.read())!=-1) {
				os.write(data);
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("file not found:"+e);
		} catch (IOException e) {
			System.out.println("error:"+e);
		} finally {
			try {
				if(is != null) {
					is.close();
				}
				if(is != null) {
					os.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

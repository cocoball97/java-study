package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ChatClientThread extends Thread {
	private BufferedReader bufferedReader;

	public ChatClientThread(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
	}

	@Override
	public void run() {
		try {
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				line = decode(line);
				line = line.replace("=", ":");
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	private String decode(String encoded) {
		byte[] decodedBytes = Base64.getDecoder().decode(encoded);
		return new String(decodedBytes, StandardCharsets.UTF_8);
	}
}

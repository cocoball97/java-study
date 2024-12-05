package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
	// 127.0.0.1 왜 안되지
	private static final String SERVER_IP = "192.168.35.199";

	public static void main(String[] args) {
		Scanner scanner = null;
		Socket socket = null;
		try {
			// 1. 키보드 연결
			scanner = new Scanner(System.in, "utf-8");

			// 2. socket 생성
			socket = new Socket();

			// 3. 연결
			socket.connect(new InetSocketAddress(SERVER_IP, ChatServer.PORT));

			// 4. reader/writer 생성 여기가 맞나? 새로 생성은 아니겠지
			// 여기서 만든 소켓 하나로 충분? 클라이언트라?
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));

			// 5. join 프로토콜
			System.out.print("닉네임>>");
			String nickname = scanner.nextLine();
			pw.println("join:" + nickname);
			// print, println, write 등 PrintWriter 버퍼 데이터 전송
			pw.flush();

			// 6. ChatClientReceiveThread 시작
			new ChatClientThread(br).start();
			while (true) {
				String msg = scanner.nextLine();
				// 얘는 소켓이 없나???????? - 이유가 맞는건가...
				// 이건 수신해야한대 왜지

				// 여기들어가면 인스턴스 계속 생성

				// 8. quit 프로토콜 처리
				if ("quit".equals(msg) == true) {
					pw.println("quit");
					pw.flush();
					break;
				} else {
					// 9. 메시지 처리
					pw.println("msg:" + msg);
					pw.flush();
				}
			}

		} catch (IOException ex) {
			consoleLog("error:" + ex);
		} finally {
			try {
				if (scanner != null) {
					scanner.close();
				}
				if (socket != null && !socket.isClosed()) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void consoleLog(String message) {
		System.out.println("[Chat Client]" + message);
	}
}

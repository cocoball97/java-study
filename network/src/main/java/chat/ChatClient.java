package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

public class ChatClient {
	private static final String SERVER_IP = "192.168.0.13";

	public static void main(String[] args) {
		Scanner scanner = null;
		Socket socket = null;
		
		try {
			scanner = new Scanner(System.in, "Euc-kr");

			socket = new Socket();
			socket.connect(new InetSocketAddress(SERVER_IP, ChatServer.PORT));

			// 4. reader/writer 생성
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));

			// 5. join 프로토콜
			System.out.print("닉네임>>");
			String nickname = scanner.nextLine();
			
			// 입력 텍스트가 2번 나오는게 싫어서 ANSI 이스케이프 시퀀스 이용
	        System.out.print("\033[F"); // 커서를 이전 줄로 이동
	        System.out.print("\033[K"); // 현재 줄 내용 지우기
	        
			// : 따옴표는 base64 인코딩이 안되서 임의로 = 으로 변경해서 데이터 전송 후 다시 : 변경
			String encodedname = encode("join" + "=" + nickname);
			pw.println(encodedname);
			// print, println, write 등 PrintWriter 버퍼 데이터 전송
			pw.flush();

			// 6. ChatClientReceiveThread 시작
			new ChatClientThread(br).start();
			while (true) {
				String message = scanner.nextLine();
				
				// 엔터를 누른 직후 텍스트 지우기
		        System.out.print("\033[F"); // 커서를 이전 줄로 이동
		        System.out.print("\033[K"); // 현재 줄 내용 지우기

				// 8. quit 프로토콜 처리
				if ("quit".equals(message) == true) {
					pw.println("quit");
					pw.flush();
					break;
				} else {
					// 9. 메시지 처리
					String encodedmsg = encode("msg" + "=" + message);
					// : 따옴표는 base64 인코딩이 안되서 임의로 = 으로 변경해서 데이터 전송 후 다시 : 변경
					pw.println(encodedmsg);
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
	
	private static String encode(String message) {
		return Base64.getEncoder().encodeToString(message.getBytes(StandardCharsets.UTF_8));
	}

	public static void consoleLog(String message) {
		System.out.println("[Chat Client]" + message);
	}
}

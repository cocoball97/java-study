package chat;

import java.io.IOException;
import java.io.Writer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
	public static final int PORT = 9999;

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		List<Writer> listWriters = new ArrayList<Writer>();

		try {
			// 1. 서버 소켓 생성
			serverSocket = new ServerSocket();

			// 2. 바인딩
			String hostAddress = InetAddress.getLocalHost().getHostAddress();
			serverSocket.bind(new InetSocketAddress(hostAddress, PORT));
//			serverSocket.bind(new InetSocketAddress("0.0.0.0", PORT));
			consoleLog("starts...[port:" + PORT + "]");

			// 3. 요청 대기
			while (true) {
				Socket socket = serverSocket.accept();
				// 멀티 쓰레드를 위해서는 run()이 아닌 start()
				new ChatServerThread(socket, listWriters).start();
			}
		} catch (IOException e) {
			consoleLog("error:" + e);
		} finally {
			try {
				if (serverSocket != null && !serverSocket.isClosed()) {
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void consoleLog(String message) {
		System.out.println("[chat server]" + message);
	}

}
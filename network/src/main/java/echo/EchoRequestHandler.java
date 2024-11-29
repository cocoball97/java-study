package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class EchoRequestHandler extends Thread {
	private Socket socket;

	public EchoRequestHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			// InetSocketAddress는 IP 소켓 주소(IP 주소 + 포트 넘버)
			InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
			int remotePort = inetRemoteSocketAddress.getPort();
			EchoServer.log("connected by client[" + remoteHostAddress + ":" + remotePort + "]");

			// Object 형태의 데이터를 java 파일에 텍스트 형식으로 출력
			// PrintWirter 인자 : (보조스트림(주스트림),오토플러시)
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			// InputStreamReader + 버퍼링 기능 + 엔터 입력
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));

			while (true) {
				String data = br.readLine();
				// 들어오는 데이터가 없다면 끝
				if (data == null) {
					EchoServer.log("closed by client");
					break;
				}
				EchoServer.log("received:" + data);
				pw.println(data);
			}
		} catch (SocketException e) {
			EchoServer.log("Socket Exception" + e);
		} catch (IOException e) {
			EchoServer.log("error:" + e);
		} finally {
			try {
				// socket == null : 객체 생성x
				if (socket != null && !socket.isClosed()) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

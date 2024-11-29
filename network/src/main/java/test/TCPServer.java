package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TCPServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			// 1. 서버소켓 생성
			serverSocket = new ServerSocket();

			// 2. 바인딩(binding)
			// Socket에 InetSocketAddres [InetAddress(IPAddress) + port번호] 바인딩
			// port번호가 연결 포트는 아님
			// IPAddress : 0.0.0.0 -> 특정 호스트 IP를 바인딩 하지 않는다.
			serverSocket.bind(new InetSocketAddress("0.0.0.0", 50000));

			// 3. accept
			// 얘가 연결됨
			Socket socket = serverSocket.accept(); // blocking

			try {
				// SocketAddress는 추상클래스이므로 다운캐스팅
				// getAddress : 객체변환
				// getHostAddress : IP주소 문자열형식 반환
				InetSocketAddress inetRemoteSocketAddres = (InetSocketAddress) socket.getRemoteSocketAddress();
				String remoteHostAddres = inetRemoteSocketAddres.getAddress().getHostAddress();
				int remotePort = inetRemoteSocketAddres.getPort();
				System.out.println("[server] connected by client[" + remoteHostAddres + ":" + remotePort + "]");

				// 4. IOstream 받아오기
				// 반환값이 InputStream 
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();
				
				while (true) {
					// 5. 데이터 읽기
					byte[] buffer = new byte[256];
					int readByteCount = is.read(buffer); // blocking
					// 스트림의 끝(EOF) 인 경우 : -1
					if (readByteCount == -1) {
						// closed by client
						System.out.println("[server] closed by client");
						break;
					}
					// inputstream 역할, 0번 인덱스부터 시작 , 바이트 수
					String data = new String(buffer, 0, readByteCount, "utf-8");
					System.out.println("[server] receive:" + data);

					// 6. 데이터 쓰기  바이트변경, 캐릭터변경?
					os.write(data.getBytes("utf-8"));
				}

			} catch (SocketException e) {
				System.out.println("[server] Socket Exception:" + e);
			} catch (IOException e) {
				System.out.println("[server] error:" + e);
			} finally {
				try {
					if (socket != null && !socket.isClosed()) {
						socket.close();
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			System.out.println("[server] error :" + e);
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

}

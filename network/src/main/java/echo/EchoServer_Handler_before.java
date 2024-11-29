package echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer_Handler_before {
	public static final int PORT = 60000;
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress("0.0.0.0", PORT));
			log("strats...[port:"+PORT+"]");

			while(true) {
				Socket socket = serverSocket.accept();
				new EchoRequestHandler(socket).start();
			}
			
//			try {
//				InetSocketAddress inetRemoteSocketAddres = (InetSocketAddress) socket.getRemoteSocketAddress();
//				String remoteHostAddres = inetRemoteSocketAddres.getAddress().getHostAddress();
//				int remotePort = inetRemoteSocketAddres.getPort();
//				log("connected by client[" + remoteHostAddres + ":" + remotePort + "]");
//
//				// PrintWirter 인자 : (보조스트림(주스트림),오토플러시) 
//				PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"),true);
//				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//				
//				while (true) {
//					String data = br.readLine();
//					if (data == null) {
//						log("closed by client");
//						break;
//					}
//					
//					log("received:"+data);					
//					pw.println(data);
//				}
//
//			} catch (SocketException e) {
//				log("Socket Exception:"+e);
//			} catch (IOException e) {
//				log("error:"+e);
//			} finally {
//				try {
//					if (socket != null && !socket.isClosed()) {
//						socket.close();
//					}
//
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}

		} catch (IOException e) {
			log("error:"+e);
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
	public static void log(String message) {
		System.out.println("[Echo Server]" + message);
	}
}

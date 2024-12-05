package chat.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

public class ChatServerThread extends Thread {
	private String nickname;
	private Socket socket;
	private List<Writer> listWriters;

	public ChatServerThread(Socket socket, List<Writer> listWriters) {
		this.socket = socket;
		this.listWriters = listWriters;
	}

	private void doJoin(String nickName, Writer writer) {
		this.nickname = nickName;

		String data = nickName + "님이 참여하였습니다.";
		broadcast(data);
		addWriter(writer);
	}

	private void broadcast(String data) {
		synchronized (listWriters) {
			for (Writer writer : listWriters) {
				PrintWriter printWriter = (PrintWriter) writer;
				data = encode(data);
				printWriter.println(data);
				printWriter.flush();
			}
		}
	}

	private void addWriter(Writer writer) {
		synchronized (listWriters) {
			listWriters.add(writer);
		}
	}

	private void doMessage(String string) {
		synchronized (listWriters) {
			for (Writer writer : listWriters) {
				PrintWriter printWriter = (PrintWriter) writer;
				// base64 인코딩을 위해 =로 변경
				String encodedStr = encode(nickname + "=" + string);
				printWriter.println(encodedStr);
				printWriter.flush();
			}
		}
	}

	private void doQuit(Writer writer) {
		removeWriter(writer);
		String data = nickname + "님이 퇴장하였습니다.";
		broadcast(data);
	}

	private void removeWriter(Writer writer) {
		synchronized (listWriters) {
			listWriters.remove(writer);
		}
	}

	@Override
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);

			while (true) {
				String request = br.readLine();
				String decoded = decode(request);
				String[] tokens = decoded.split("=");

				if ("join".equals(tokens[0])) {
					doJoin(tokens[1], pw);
				} else if ("msg".equals(tokens[0])) {
					doMessage(tokens[1]);
				} else if ("quit".equals(tokens[0])) {
					doQuit(pw);
					break;
				} else if (tokens[0] == null) {
					ChatServer.consoleLog("클라이언트로부터 연결 끊김");
					doQuit(pw);
					break;
				}
			}
		} catch (SocketException e) {
			ChatServer.consoleLog("Socket Exception" + e);
		} catch (IOException e) {
			ChatServer.consoleLog("IO Excpetion:" + e);
		}
	}

	private String encode(String message) {
		return Base64.getEncoder().encodeToString(message.getBytes(StandardCharsets.UTF_8));
	}

	private String decode(String encoded) {
		byte[] decodedBytes = Base64.getDecoder().decode(encoded);
		return new String(decodedBytes, StandardCharsets.UTF_8);
	}
}

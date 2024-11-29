package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.SocketException;
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

		// write pool에 저장
		addWriter(writer);

		// ack 틀린거같은데? - 다시 공부
		// 내맘대로 했음
		// Writer는 추상클래스
		PrintWriter printWriter = (PrintWriter) writer;
		printWriter.println("join:ok");
		printWriter.flush();
	}

	// 동기화 및 출력
	// 동시에 데이터 접근하여 수정하거나 읽으면 불일치 발생
	private void broadcast(String data) {
		synchronized (listWriters) {
			for (Writer writer : listWriters) {
				PrintWriter printWriter = (PrintWriter) writer;
				printWriter.println(data);
				printWriter.flush();
			}
		}
	}

	// 동기화 및 입력
	private void addWriter(Writer writer) {
		// 동기화 보장
		synchronized (listWriters) {
			listWriters.add(writer);
		}

	}

	private void doMessage(String string) {
		synchronized (listWriters) {
			for (Writer writer : listWriters) {
				PrintWriter printWriter = (PrintWriter) writer;
				printWriter.println(nickname + ":" + string);
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
			// 1. Remote Host information

			// 2. 스트림 얻기
			// 읽는 스트림
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			// 쓰는 스트림
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);

			// 3. 요청 처리
			// 클라이언트에서 보낸 것을 읽어들여서 분리후 진행
			while (true) {
				String request = br.readLine();
				String[] tokens = request.split(":");

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

		} catch (

		SocketException e) {
			ChatServer.consoleLog("Socket Exception" + e);
		} catch (IOException e) {
			ChatServer.consoleLog("IO Excpetion:" + e);
		}

	}

}

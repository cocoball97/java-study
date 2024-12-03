package chat.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Base64;

import chat.ChatServer;

public class ChatWindow {
	private Frame frame;
	private Panel pannel;
	private Button buttonSend;
	private TextField textField;
	private TextArea textArea;

	private Socket socket;
	private String name;
	private PrintWriter pw;
	private String encodedStr;

	private static final String SERVER_IP = "192.168.35.127";

	public ChatWindow(String name) {
		frame = new Frame(name);
		pannel = new Panel();
		buttonSend = new Button("Send");
		textField = new TextField();
		textArea = new TextArea(30, 80);

		this.name = name;

	}

	// 소켓생성을 메인스레드X, 생성자X 여기서 하기

	public void show() {
		// Button
		buttonSend.setBackground(Color.GRAY);
		buttonSend.setForeground(Color.WHITE);

		// ActionListern : 인터페이스
		// 어나니머스 클래스 - 클래스 이름이 없음, 한번 쓰고 버려질 객체, 일회용
		// java는 파라미터 인자로 메서드가 들어갈 수 없기 때문에 functional interface 사용한듯?

		// buttonSend.addActionListener(actionEvent -> {});
		// 버튼 클릭
		buttonSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				sendMessage();
			}
		});

		try {
			// 1. 서버 연결 작업, 소켓 생성
			socket = new Socket();
			socket.connect(new InetSocketAddress(SERVER_IP, ChatServer.PORT));

			// 2. IO Stream Setting
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));

			// 3. join Protocol
			encodedStr = encode("join="+name);
			pw.println(encodedStr);
			// print, println, write 등 PrintWriter 버퍼 데이터 전송
			pw.flush();

			// 4. ChatClientThread 생성
			new ChatClientThread(br).start();

		} catch (IOException e) {
			System.out.println("에러1번");
			e.printStackTrace();
		}

		// Textfield
		textField.setColumns(80);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if (keyChar == KeyEvent.VK_ENTER) {
					sendMessage();
				}
			}

		});

		// Pannel
		pannel.setBackground(Color.LIGHT_GRAY);
		pannel.add(textField);
		pannel.add(buttonSend);
		frame.add(BorderLayout.SOUTH, pannel);

		// TextArea
		textArea.setEditable(false);
		frame.add(BorderLayout.CENTER, textArea);

		// Frame
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				finish();
			}
		});
		frame.setVisible(true);
		frame.pack();
	}

	private void sendMessage() {
		String message = textField.getText();
		System.out.println("메시지를 보내는 프로토콜 구현:" + message);

		textField.setText("");
		textField.requestFocus();

		if ("quit".equals(message) == true) {
			finish();
		} else {
			// 9. 메시지 처리
			message = "msg=" + message;
			
			encodedStr = encode(message);
			pw.println(encodedStr);
			pw.flush();
		}
	}

	private void updateTextArea(String message) {
		textArea.append(message);
		textArea.append("\n");
	}

	private void finish() {
		encodedStr = encode("quit");
		pw.println(encodedStr);
		pw.flush();
		System.exit(0);
	}

	private String encode(String message) {
		String encodedStr = Base64.getEncoder().encodeToString(message.getBytes());
		return encodedStr;
	}

	// 내부클래스(inner class)
	private class ChatClientThread extends Thread {
		private BufferedReader bufferedReader;

		public ChatClientThread(BufferedReader bufferedReader) {
			this.bufferedReader = bufferedReader;
		}

		@Override
		public void run() {
			String line = null;
			try {
				while ((line = bufferedReader.readLine()) != null) {
					System.out.println(line);
					updateTextArea(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}

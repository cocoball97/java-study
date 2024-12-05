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
import java.nio.charset.StandardCharsets;
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

	private static final String SERVER_IP = "192.168.0.13";

	public ChatWindow(String name) {
		frame = new Frame(name);
		pannel = new Panel();
		buttonSend = new Button("Send");
		textField = new TextField();
		textArea = new TextArea(30, 80);

		this.name = name;
	}

	// 여기서 소켓생성
	public void show() {
		// Button
		buttonSend.setBackground(Color.GRAY);
		buttonSend.setForeground(Color.WHITE);

		// 버튼 클릭
		buttonSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				sendMessage();
			}
		});

		try {
			socket = new Socket();
			socket.connect(new InetSocketAddress(SERVER_IP, ChatServer.PORT));

			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));

			// : 따옴표는 base64 인코딩이 안되서 임의로 = 으로 변경해서 데이터 전송 후 다시 : 변경
			encodedStr = encode("join=" + name);
			pw.println(encodedStr);
			pw.flush();

			new ChatClientThread(br).start();
		} catch (IOException e) {
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
		textField.setText("");
		textField.requestFocus();

		if ("quit".equals(message) == true) {
			finish();
		} else {
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
		return Base64.getEncoder().encodeToString(message.getBytes(StandardCharsets.UTF_8));
	}

	private String decode(String encoded) {
		byte[] decodedBytes = Base64.getDecoder().decode(encoded);
		return new String(decodedBytes, StandardCharsets.UTF_8);
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
					line = decode(line);
					line = line.replace("=", ":");
					System.out.println(line);
					updateTextArea(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}

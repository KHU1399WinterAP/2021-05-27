package main.java.socket;

import main.java.app.AppManager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread {
	public final Socket SOCKET;
	public final int ID;
	public final DataInputStream DATA_INPUT_STREAM;
	public final DataOutputStream DATA_OUTPUT_STREAM;
	
	public ClientHandler(Socket socket, int id) throws IOException {
		this.SOCKET = socket;
		this.ID = id;
		
		System.out.println("Connected to " + socket.getRemoteSocketAddress());
		
		this.DATA_INPUT_STREAM = new DataInputStream(SOCKET.getInputStream());
		this.DATA_OUTPUT_STREAM = new DataOutputStream(SOCKET.getOutputStream());
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				var command = DATA_INPUT_STREAM.readUTF();
				AppManager.sendMessage(ID, command);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

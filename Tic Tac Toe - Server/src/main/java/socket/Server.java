package main.java.socket;

import main.java.app.AppManager;

import java.io.IOException;
import java.net.ServerSocket;

public class Server extends Thread {
	private final ServerSocket SERVER_SOCKET;
	private final int PORT;
	
	public Server(int port) throws IOException {
		this.PORT = port;
		this.SERVER_SOCKET = new ServerSocket(port);
	}
	
	@Override
	public void run() {
		try {
			System.out.println("Listening on port " + PORT + " ...");
			AppManager.addSocket(SERVER_SOCKET.accept(), 0);
			AppManager.addSocket(SERVER_SOCKET.accept(), 1);
			
			AppManager.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

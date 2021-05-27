package main.java.app;

import main.java.socket.ClientHandler;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class AppManager {
	private static final ArrayList<ClientHandler> CLIENT_HANDLERS = new ArrayList<>();
	
	public static void addSocket(Socket socket, int id) throws IOException {
		var clientHandler = new ClientHandler(socket, id);
		CLIENT_HANDLERS.add(clientHandler);
	}
	
	public static void start() {
		sendMessage(0, "1");
		sendMessage(1, "0");
		sendMessage(0, "START");
		sendMessage(1, "START");
		
		CLIENT_HANDLERS.get(0).start();
		CLIENT_HANDLERS.get(1).start();
	}
	
	public static void sendMessage(int id, String command) {
		try {
			CLIENT_HANDLERS.get(id == 0 ? 1 : 0).DATA_OUTPUT_STREAM.writeUTF(command);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

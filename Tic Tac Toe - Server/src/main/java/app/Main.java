package main.java.app;

import main.java.socket.Server;

import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		try {
			Server server = new Server(5000);
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

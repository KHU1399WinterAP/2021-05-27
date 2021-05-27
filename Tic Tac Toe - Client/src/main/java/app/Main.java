package main.java.app;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		AppManager.init();
		AppManager.start();
		System.out.println("STARTED!");
	}
}

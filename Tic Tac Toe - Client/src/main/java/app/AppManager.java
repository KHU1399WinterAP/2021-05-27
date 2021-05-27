package main.java.app;

import main.java.config.GuiConfig;
import main.java.gui.CellMouseListener;
import main.java.gui.MainFrame;
import main.java.socket.Client;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AppManager {
	public static Client client;
	public static JLabel[][] cells = new JLabel[3][3];
	public static CellMouseListener[][] listeners = new CellMouseListener[3][3];
	public static boolean isFirstPlayerTurn = true;
	
	private static MainFrame mainFrame;
	
	public static void init() throws IOException {
		client = new Client();
	}
	
	public static void start() {
		mainFrame = new MainFrame();
		mainFrame.setVisible(true);
		
		if (!isMyTurn())
			new WaitForOpponentMoveThread().start();
	}
	
	public static void clickedOnCell(int i, int j) {
		client.sendRequest(i + "" + j);
		
		if (checkRowAndCol() || checkDiagonal())
			gameOver();
		
		isFirstPlayerTurn = !isFirstPlayerTurn;
		
		new WaitForOpponentMoveThread().start();
	}
	
	public static boolean isMyTurn() {
		return (client.ID == 0 && isFirstPlayerTurn) || (client.ID == 1 && !isFirstPlayerTurn);
	}
	
	public static String playerString() {
		return isFirstPlayerTurn ? "X" : "O";
	}
	
	public static boolean checkRowAndCol() {
		for (int i = 0; i < 3; i++) {
			var rowCounter = 0;
			var colCounter = 0;
			
			for (int j = 0; j < 3; j++) {
				if (compare(i, j))
					rowCounter++;
				
				if (compare(j, i))
					colCounter++;
			}
			
			if (rowCounter == 3 || colCounter == 3)
				return true;
		}
		
		return false;
	}
	
	public static boolean checkDiagonal() {
		var primaryDiagonal = compare(0, 0) && compare(1, 1) && compare(2, 2);
		var secondaryDiagonal = compare(0, 2) && compare(1, 1) && compare(2, 0);
		
		return primaryDiagonal || secondaryDiagonal;
	}
	
	public static void gameOver() {
		JOptionPane.showMessageDialog(mainFrame, playerString() + " won!", "Game Over!", JOptionPane.INFORMATION_MESSAGE);
		mainFrame.dispose();
		System.exit(0);
	}
	
	private static boolean compare(int i, int j, String text) {
		return cells[i][j].getText().compareToIgnoreCase(text) == 0;
	}
	
	private static boolean compare(int i, int j) {
		return cells[i][j].getText().compareToIgnoreCase(playerString()) == 0;
	}
	
	private static boolean compare(JLabel cell, String text) {
		return cell.getText().compareToIgnoreCase(text) == 0;
	}
}

class WaitForOpponentMoveThread extends Thread {
	@Override
	public void run() {
		var move = AppManager.client.getResponse();
		
		var i = move.charAt(0) - 48;
		var j = move.charAt(1) - 48;
		
		System.out.println(move);
		System.out.println(i);
		System.out.println(j);
		
		var cell = AppManager.cells[i][j];
		
		cell.setText(AppManager.playerString());
		cell.setBackground(GuiConfig.COLOR_CELL_IDLE);
		cell.setForeground(AppManager.isFirstPlayerTurn ? GuiConfig.COLOR_X : GuiConfig.COLOR_O);
		cell.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		
		cell.removeMouseListener(AppManager.listeners[i][j]);
		
		if (AppManager.checkRowAndCol() || AppManager.checkDiagonal())
			AppManager.gameOver();
		
		AppManager.isFirstPlayerTurn = !AppManager.isFirstPlayerTurn;
	}
}

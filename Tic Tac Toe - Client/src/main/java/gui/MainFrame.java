package main.java.gui;

import main.java.app.AppManager;
import main.java.config.GuiConfig;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
	private JPanel mainPanel;
	
	public MainFrame() {
		super("Tic Tac Toe | Player " + (AppManager.client.ID + 1));
		
		setContentPane(mainPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		pack();
		setLocationRelativeTo(null);
		
		initCustomComponents();
		initListeners();
	}
	
	private void createUIComponents() {
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
	}
	
	private void initCustomComponents() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				addCell(i, j, (3 * i + j) + "");
	}
	
	private void addCell(int i, int j, String text) {
		var cell = new JLabel(text, SwingConstants.CENTER);
		
		cell.setBackground(GuiConfig.COLOR_BLACK);
		cell.setForeground(GuiConfig.COLOR_WHITE);
		cell.setOpaque(true);
		
		cell.setLocation(j * GuiConfig.CELL_SIZE, i * GuiConfig.CELL_SIZE);
		cell.setSize(GuiConfig.CELL_SIZE, GuiConfig.CELL_SIZE);
		cell.setBorder(GuiConfig.CELL_BORDER);
		
		cell.setFont(new Font("Consolas", Font.BOLD, 36));
		
		cell.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		AppManager.cells[i][j] = cell;
		mainPanel.add(cell);
	}
	
	private void initListeners() {
		initCellListeners();
	}
	
	private void initCellListeners() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				AppManager.listeners[i][j] = new CellMouseListener(i, j);
				AppManager.cells[i][j].addMouseListener(AppManager.listeners[i][j]);
			}
		}
	}
}

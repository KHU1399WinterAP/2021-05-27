package main.java.gui;

import main.java.app.AppManager;
import main.java.config.GuiConfig;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CellMouseListener implements MouseListener {
	private final int i;
	private final int j;
	private boolean isMouseExited = false;
	
	public CellMouseListener(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
	
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		var cell = AppManager.cells[i][j];
		
		if (!AppManager.isMyTurn() || isMouseExited || isFilled())
			return;
		
		cell.setText(AppManager.playerString());
		cell.setBackground(GuiConfig.COLOR_CELL_IDLE);
		cell.setForeground(AppManager.isFirstPlayerTurn ? GuiConfig.COLOR_X : GuiConfig.COLOR_O);
		cell.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		
		AppManager.clickedOnCell(i, j);
		cell.removeMouseListener(this);
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		AppManager.cells[i][j].setBackground(GuiConfig.COLOR_CELL_HOVER);
		isMouseExited = false;
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		AppManager.cells[i][j].setBackground(GuiConfig.COLOR_CELL_IDLE);
		isMouseExited = true;
	}
	
	private boolean isFilled() {
		var text = AppManager.cells[i][j].getText();
		return text.compareToIgnoreCase("x") == 0 || text.compareToIgnoreCase("o") == 0;
	}
}

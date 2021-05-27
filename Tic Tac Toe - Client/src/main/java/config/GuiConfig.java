package main.java.config;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public class GuiConfig {
	public static final Color COLOR_WHITE = new Color(250, 250, 250);
	public static final Color COLOR_GRAY_LIGHTER = new Color(204, 204, 204);
	public static final Color COLOR_GRAY_LIGHT = new Color(179, 179, 179);
	public static final Color COLOR_GRAY_MEDIUM = new Color(128, 128, 128);
	public static final Color COLOR_GRAY_DARK = new Color(77, 77, 77);
	public static final Color COLOR_GRAY_DARKER = new Color(51, 51, 51);
	public static final Color COLOR_BLACK = new Color(26, 26, 26);
	
	public static final Color COLOR_X = new Color(255, 99, 71);
	public static final Color COLOR_O = new Color(255, 230, 16);
	public static final Color COLOR_CELL_IDLE = COLOR_BLACK;
	public static final Color COLOR_CELL_HOVER = COLOR_GRAY_DARKER;
	
	public static final int CELL_SIZE = 200;
	public static final Border CELL_BORDER = new CompoundBorder(BorderFactory.createEmptyBorder(), BorderFactory.createLineBorder(COLOR_GRAY_DARK, 4));
}

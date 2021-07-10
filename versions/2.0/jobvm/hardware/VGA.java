package jobvm.hardware;

import java.awt.*;

import javax.swing.JPanel;

import jobvm.OSLoader;
/*
 * Virtual VGA
 * 
 */
public class VGA {
	public String StrGraphic = "";
	public static final short WHITE = 0;
	public static final short LIGHTGRAY = 1;
	public static final short GRAY = 2;
	public static final short DARKGRAY = 3;
	public static final short BLACK = 4;
	public boolean InInputState = false;
	public boolean InKeyState = false;
	private boolean mode = false; //Graphic Mode
	char CursorCharacter = '_';
	int y = 20;
	public short[][] display = new short[320][200]; //VGA. 320x200  
	public char InKey = ' ';
	public boolean CursorVisible = false;
	public String InputStr = "";
	public void Clear() {
		StrGraphic = "";
	}
	public void setPixel(int x, int y, short color) {
		display[x][y] = color;
	}
	public void GraphicMode() {
		OSLoader.Instance.CursorVisible = false;
		mode = true;
	}
	public void Draw(Graphics g) {
		g.setColor(Color.white);
		short x = 0;
		short y = 0;
		for (short[] yvec : display) {
			for (short ymeta : yvec) {
				//System.out.println("x: " + x + " y: " + y);
				g.setColor(ConvertColor(ymeta));
				g.drawLine(x, y, x, y);
				y++;
			}
			x++;
			y = 0;
		}
	}
	private Color ConvertColor(short classic) {
		if (classic == 0) {
			return Color.WHITE;
		}
		else if (classic == 1) {
			return Color.LIGHT_GRAY;
		}
		else if (classic == 2) {
			return Color.GRAY;
		}
		else if (classic == 3) {
			return Color.DARK_GRAY;
		}
		else if (classic == 4) {
			return Color.BLACK;
		}
		else {
			return Color.black;
		}
	}
	public void ConsoleMode() {
		OSLoader.Instance.CursorVisible = true;
		mode = false;
	}
	public boolean isConsoleMode() {
		return OSLoader.Instance.CursorVisible;
	}
	public void Show(Graphics g,Color c, Memory memoryInstance) {
		if (!mode) {
			g.setFont(new Font("Arial",Font.BOLD, 15));
			g.setColor(c);
			int i = 0;
			for (String line : StrGraphic.split("\n")) {
				if (y > 400) {
					StrGraphic = StrGraphic.substring(StrGraphic.indexOf('\n')+1);
				}
				try { 
					if (OSLoader.Instance.CursorVisible && memoryInstance.getAddressValue(10) == 0 && StrGraphic.split("\n").length - 1 == i) {
						g.drawString(line+CursorCharacter, 5, y);
					}
					else {
						g.drawString(line, 5, y);
					}
				}
				catch (MemoryAllocationException e) { Println(e.getMessage());}


				i++;
				y += 20;
			}
			y = 20;
		}
	}
	public void Println(String STR) {
		StrGraphic += STR + "\n";
	}
	public void Print(String STR) {
		StrGraphic += STR;
	}
	public String GetInput() {
		InInputState = true;
		while (InInputState) {
			System.out.print("");
		}
		String tmp = InputStr + "";
		InputStr = "";
		return tmp;
	}
	public char readKey() {
		InKeyState = true;
		while (InKeyState) {
			System.out.print("");
		}
		char tmp = InKey;
		InKey = ' ';
		return tmp;
	}
}

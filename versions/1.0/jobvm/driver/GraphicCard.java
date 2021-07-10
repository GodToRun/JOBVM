package jobvm.driver;

import java.awt.*;

public class GraphicCard {
	public String StrGraphic = "";
	public boolean InInputState = false;
	public boolean InKeyState = false;
	char CursorCharacter = '_';
	int y = 20;
	public char InKey = ' ';
	public boolean CursorVisible = false;
	public String InputStr = "";
	public void Clear() {
		StrGraphic = "";
	}
	public void Show(Graphics g,Color c, Memory memoryInstance) {
		g.setFont(new Font("Lucida",Font.BOLD, 15));
		g.setColor(c);
		int i = 0;
		for (String line : StrGraphic.split("\n")) {
			if (y > 400) {
				StrGraphic = StrGraphic.substring(StrGraphic.indexOf('\n')+1);
			}
				try { 
				if (memoryInstance.getAddressValue(10) == 0 && StrGraphic.split("\n").length - 1 == i) {
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

package jobvm.hardware;

import java.awt.Point;

import jobvm.OSLoader;

public class Interrupt {
	public static void interrupt(char type) {
		if (type == 0x10 && OSLoader.Instance.cpu.ah == 0x0E) {
			OSLoader.Instance.graphic.Print(OSLoader.Instance.cpu.si.toString()); //WRITE STRING
		}
		else if (type == 0x10 && OSLoader.Instance.cpu.ah == 0x02) {
			OSLoader.Instance.graphic.CursorLocation = new Point(OSLoader.Instance.cpu.dl,OSLoader.Instance.cpu.dh);
		}
		else if (type == 0x13) { //16BIT REAL MODE FLOPPY DRIVE HANDLING
			
		}
		else if (type == 0x16) {
			char key = OSLoader.Instance.graphic.readKey();
			OSLoader.Instance.cpu.ah = (byte)key;
			OSLoader.Instance.cpu.al = (byte)key;
		}
	}
}

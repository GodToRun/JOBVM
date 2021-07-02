package jobvm.driver;

import java.util.Scanner;

import jobvm.OSLoader;
import jobvm.driver.MemoryAllocationException;

public class CPU {
	//Number registers
	public byte ia;
	public byte is;
	public byte id;
	public byte ie;
	//String registers
	public String sa;
	public String sz;
	public String sd;
	public String si;
	public void LoadToRAM(String str,int location) {
		int loc = location;
			for (char c : str.toCharArray()) {
				try {
					OSLoader.Instance.memory.setAddressValue(loc, (byte)c);
				} catch (MemoryAllocationException e) {
					OSLoader.Instance.graphic.Println(e.getMessage());
				}
				loc++;
			}
		
	}
	public int toByteLiteral(int i) {
		return i * 1024;
	}
	public void request(byte type) { //Request CPU API
		if (type == 1) {
			//Print text
			//IN: sa (text) OUT: nothing
			OSLoader.Instance.graphic.Println(sa);
		}
		else if (type == 2) {
			//Add
			//IN: ia,is OUT: return ia
			ia += is;
		}
		else if (type == 3) {
			//Increase
			//IN: nothing OUT: add 1 from ia
			ia++;
		}
		else if (type == 4) {
			//Decrease
			//IN: nothing OUT: substract 1 from ia
			ia--;
		}
		else if (type == 5) {
			//Get input
			//IN: nothing OUT: sa is input value
			sa = OSLoader.Instance.graphic.GetInput();
		}
		else {
			//FAILED
			is = -1;
		}
	}
}


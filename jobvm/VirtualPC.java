package jobvm;

import java.util.*;
import jobvm.driver.*;

import jobvm.fs.VirtualDisk;

public class VirtualPC {
	public String name;
	public HashMap<Character,VirtualDisk> DiskList = new HashMap<Character,VirtualDisk>();
	public VirtualPC(String PCNAME) {
		name = PCNAME;
	}
	public void Save() {
		
	}
	public void Load() {
		
	}
}

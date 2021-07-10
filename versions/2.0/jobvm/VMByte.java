package jobvm;

import jobvm.hardware.MemoryAllocationException;

/* Integer of JOBVM */
public class VMByte {
	int location;
	public VMByte(byte value) {
		int i = 0;
		for (byte b : OSLoader.Instance.memory.getMap()) {
			if (b == -128 && i > 32767) {
				location = i;
			}
			i++;
		}
		try { OSLoader.Instance.memory.setAddressValue(location, value); } catch (MemoryAllocationException e)
		{ OSLoader.Instance.graphic.Println(e.getMessage());}
	}
}

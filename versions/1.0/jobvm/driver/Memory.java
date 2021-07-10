package jobvm.driver;
import java.util.*;
public class Memory {
	private byte[] map;
	private static byte[] buffer = new byte[4096]; //4KB Buffer
	public static byte[] getBuffer() {
		return buffer;
	}
	public static void setBuffer(byte[] buf) {
		buffer = buf;
	}
	private int size;
	public void setMaxAddress(int size) {
		map = new byte[size];
		this.size = size;
	}
	public void setAddressValue(int address, byte value) throws MemoryAllocationException {
		if (address > size)
			throw new MemoryAllocationException("Attempted to allocate more than memory.");
		else
			map[address] = value;
	}
	public byte getAddressValue(int address) throws MemoryAllocationException {
		if (address > size)
			throw new MemoryAllocationException("Attempted to allocate more than memory.");
		else
			return map[address];
	}
	public int getMaxAddress() {
		return this.size;
	}
	public Memory(int size) {
		map = new byte[size];
		this.size = size;
	}
	public byte[] getMap() {
		return map;
	}
	public Memory() {
		
	}
}
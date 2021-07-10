package jobvm.hardware;
import java.util.*;
public class Memory {
	private byte[] map;
	private int heap;
	private int stack;
	private int stack_top;
	private int size;
	public void setMaxAddress(int size) {
		map = new byte[size];
		this.size = size;
		heap = size/2;
		stack = heap+size/2;
	}
	public void push(byte dat) {
		stack_top++;
		map[heap+stack_top] = dat;
	}
	public void pop() {
		free(heap+stack_top);
		if (stack_top > -1) {
			stack_top--;			
		}
	}
	public byte[] getStackMap() {
		return Arrays.copyOfRange(map, heap,stack);
	}
	public int get_free() {
		int memloc = 0;
		for (byte b : map) {
			if (b == -128) {
				return memloc;
			}
			memloc++;
		}
		return -1;
	}
	public int malloc() { //WIP
		int addr = get_free();
		map[addr] = 0;
		return addr;
	}
	public void free(int address) {
		map[address] = -128;
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
		heap = size/2;
		stack = heap + size/2;
	}
	public byte[] getMap() {
		return map;
	}
	public Memory() {
		
	}
}
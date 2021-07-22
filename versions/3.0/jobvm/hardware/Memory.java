//32BIT MEMORY
package jobvm.hardware;
import java.util.*;
public class Memory {
	private int[] map;
	private int heap;
	private int option;
	private int stack;
	private int stack_top;
	private int size;
	public static int READ_ONLY = 2;
	public static int RANDOM_ACCESS = 0;
	public static int WRITE_ONLY = 1;
	public void setMaxAddress(int size) {
		map = new int[size];
		this.size = size;
		heap = size/2;
		stack = heap+size/2;
	}
	public void push(int dat) {
		if (option != READ_ONLY) {
			stack_top++;
			map[heap+stack_top] = dat;
		}
	}
	public void pop() {
		free(heap+stack_top);
		if (stack_top > -1) {
			stack_top--;			
		}
	}
	public int[] getStackMap() {
		return Arrays.copyOfRange(map, heap,stack);
	}
	public int get_free() {
		int memloc = 0;
		for (int b : map) {
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
		if (option != READ_ONLY)
			map[address] = -128;
	}
	public void setAddressValue(int address, int value) throws MemoryAllocationException {
		if (option != READ_ONLY) {
			if (address > size)
				throw new MemoryAllocationException("Attempted to allocate more than memory.");
			else
				map[address] = value;
		}
		else
			throw new MemoryAllocationException("Attempted to allocate read-only memory.");
	}
	public int getAddressValue(int address) throws MemoryAllocationException {
		if (option != WRITE_ONLY) {
			if (address > size)
				throw new MemoryAllocationException("Attempted to load more than memory.");
			else
				return map[address];
		}
		else throw new MemoryAllocationException("Attempted to load address on write-only memory.");
	}
	public int getMaxAddress() {
		return this.size;
	}
	public Memory(int size) {
		map = new int[size];
		this.size = size;
		heap = size/2;
		stack = heap + size/2;
		this.option = RANDOM_ACCESS;
	}
	public Memory(int size, int OPTION) {
		this(size);
		this.option = OPTION;
	}
	public Memory(int size, int OPTION, int[] INITIAL_VALUE) {
		this(size);
		try {
			for (int i : INITIAL_VALUE)
				setAddressValue(this.get_free(),i);
		} catch (MemoryAllocationException e) {
			
		}
		this.option = OPTION;
	}
	public int[] getMap() {
		return map;
	}
	public Memory() {
		
	}
}
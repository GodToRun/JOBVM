package jobvm.example; //please edit valid package name.
import jobvm.OSLoader;
public class SimpleOS extends OSLoader {
	public SimpleOS() {
		super("BOOT.IMG"); //Never forget you must call super constructor.
	}
	public static void main(String[] args) {
		new SimpleOS(); //Create instance
	}
	@Override
	public void Booted() { //Active on booted
		
	}
	@Override
	public void Run() { //Main loop
	}
}
//End of Kernel
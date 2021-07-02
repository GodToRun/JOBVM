# JOBVM
Java Object-Based Virtual Machine
Beta Build #1
# LICENSE
GPL 3.0
See 'LICENSE'.
# BUILDING
export a runnable jar file.
# BUILD REQUIRES
[PWLibrary](https://github.com/GodToRun/PWLibrary)
# DEVELOPMENT
this is a how to make simple jobvm-os.
this is a code:

package jobvm.example; //please edit valid package name.
import jobvm.OSLoader;
public class SimpleOS extends OSLoader {
	public SimpleOS() {
		super(); //Never forget you must call super constructor.
	}
	public static void main(String[] args) {
		new SimpleOS(); //Create instance
	}
	@Override
	public void Booted() { //Active on booted
		graphic.Clear(); //Clear screen
		graphic.Println("This is my a Simple OS!"); //Print
	}
	@Override
	public void Run() { //Main loop
	}
}
//End of Kernel

then run, you can see a window created and screen in text 'This is my a Simple OS!'.
if you want more, see jobvm/example/Kernel.java. (example of Disk Operating System. )

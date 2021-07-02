package jobvm;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.*;

import pw.common.*;
import pw.kit.*;
import pw.component.*;
import jobvm.driver.*;
import jobvm.fs.VirtualDisk;

public abstract class OSLoader extends GameManager implements KeyListener {
	public GraphicCard graphic = new GraphicCard();
	public Memory memory = new Memory();
	public VirtualPC vp = new VirtualPC("JOBVM PC");
	public VirtualDisk disk;
	public VMDriver vmdriver = new VMDriver();
	public FSManager configfs = new FSManager();
	public static OSLoader Instance;
	public CPU cpu = new CPU();
	public Color FontColor = Color.LIGHT_GRAY;
	public Color BackgroundColor = Color.black;
	public int maxMemory = 65535; //Default: 64KB Memory
	public OSLoader() {
		super("JOBVM", new Point(750,450));
		getPanel().setBackground(BackgroundColor);
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    cursorImg, new Point(0, 0), "blank cursor");
		this.getContentPane().setCursor(blankCursor);
		Instance = this;
		disk = new VirtualDisk("Default disk",'A');
		memory.setMaxAddress(maxMemory);
 		//memory.setAddressValue(10, (byte)1);
		try {
			configfs.LoadAllData();
		} catch (RuntimeException e) { } //PASS
		new CursorShow().start();
		if (configfs.GetValue("maxmem") == null) {
			Configuration();
			disk.setMaximumSize(Integer.parseInt(configfs.GetValue("maxdisk").replace("\n", "")) * 1024);
			memory.setMaxAddress(Integer.parseInt(configfs.GetValue("maxmem").replace("\n", "")) * 1024);
		}
		else {
			disk.setMaximumSize(Integer.parseInt(configfs.GetValue("maxdisk").replace("\n", "")) * 1024);
			memory.setMaxAddress(Integer.parseInt(configfs.GetValue("maxmem").replace("\n", "")) * 1024);
		}
		maxMemory = Integer.parseInt(configfs.GetValue("maxmem").replace("\n", "")) * 1024;
		graphic.Println("Loading disk data..");
		VirtualDisk.Load(vp);
		disk.setMaximumSize(Integer.parseInt(configfs.GetValue("maxdisk").replace("\n", "")) * 1024);
		vp.DiskList.forEach((key,value)->{
			graphic.Println(value.DriveLetter + " Drive    OK");
		});
		try {
			graphic.Println("Loading Memory...");
			int i = 0;
			//Allocate all memory space
			for (byte b : memory.getMap()) {
				memory.setAddressValue(i, (byte)-128);
				i++;
			}
		}
		catch (MemoryAllocationException e) { }
		graphic.Println((maxMemory+1) / 1024 + "KB    OK");
		Runtime.getRuntime().addShutdownHook(new Thread()
		{
		    @Override
		    public void run()
		    {
		    	VirtualDisk.Save(vp);
		    }
		});
		Booted();
		
		
	}
	public void Configuration() {
		graphic.Println("JOBVM Configuration");
		graphic.Println("");
		graphic.Println("Harddisk Settings");
		graphic.Print("Maximum Space (KB)>");
		int value = Integer.parseInt(graphic.GetInput());
		graphic.Println("");
		graphic.Println("");
		graphic.Println("Memory Settings");
		graphic.Print("Maximum Space (KB)>");
	    int maxmem = Integer.parseInt(graphic.GetInput());
	    configfs.SetKey("maxdisk", value + "");
	    configfs.SetKey("maxmem", maxmem + "");
	    configfs.SaveAllData();
 		graphic.Clear();
	}
	public void Shutdown() {
		graphic.Println("Shutdown requested.\nShutting down...");
		VirtualDisk.Save(vp);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
	}
	@Override
	protected void MainLoop() {
		Run();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		if (graphic.InInputState) {
			if (e.getKeyChar() == e.VK_BACK_SPACE) {
				try {
					StringBuilder sb = new StringBuilder(graphic.InputStr);
					sb.deleteCharAt(sb.length() - 1);
					StringBuilder str = new StringBuilder(graphic.StrGraphic);
					str.deleteCharAt(str.length() - 1);
					graphic.InputStr = sb.toString();
					graphic.StrGraphic = str.toString();
				}
				catch (StringIndexOutOfBoundsException se) { }
				return;
			}
			else if (e.getKeyChar() == e.VK_ENTER) {
				graphic.InInputState = false;
				return;
			}
			else {
				graphic.InputStr += e.getKeyChar();
				graphic.Print(e.getKeyChar() + "");
			}
		}
		else if (graphic.InKeyState) {
			graphic.InKey = e.getKeyChar();
			graphic.InKeyState = false;
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	@Override
	protected void DrawScreen(Graphics g) {
		try { graphic.Show(g,FontColor,memory); } catch (NullPointerException e) { }
	}
	
	public abstract void Booted();
	public abstract void Run();
	class CursorShow extends Thread {
		@Override
		public void run() {
			while(true) {
				try {
				if (memory.getAddressValue(10) == 0) {
					memory.setAddressValue(10, (byte)1);
				}
				else {
					memory.setAddressValue(10, (byte)0);
				}
					Thread.sleep(300);
				} catch (Exception e) {
					OSLoader.Instance.graphic.Println(e.getMessage());
				}
			}
			
		}
	}
}

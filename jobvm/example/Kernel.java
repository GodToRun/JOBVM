package jobvm.example;
import java.util.*;

import jobvm.OSLoader;
import jobvm.VirtualPC;
import jobvm.fs.VirtualDirectory;
import jobvm.fs.VirtualDisk;
import jobvm.fs.VirtualFile;
@SuppressWarnings("serial")
public class Kernel extends OSLoader {
	static VirtualDirectory CurrentDirectory = null;
	static VirtualDirectory vdir = null;
	static Scanner sc = new Scanner(System.in);
	VirtualDisk CurrentDisk;
	static String value = null;
	static boolean booted = true;
	static Exception e;
	public static void main(String[] args) {
		new Kernel();
	}
	public Kernel() {
		super();
		CurrentDisk = vp.DiskList.get('A'); 
	}
	public void Transfer(String line) {
		String[] arg = line.split(" ");
		try {
		if (arg[0].equals("new")) {
			try {
				if (arg[2].equals("disk")) {
					VirtualFile vf = new VirtualFile(CurrentDisk,arg[1]);
				}
			}
			catch (Exception e) {
				if (CurrentDirectory == null) {
					VirtualFile vf = new VirtualFile(CurrentDisk,arg[1]);
				}
				else {
					VirtualFile vf = new VirtualFile(CurrentDirectory,arg[1]);
				}
				
			}
			graphic.Println("File " + arg[1] + " was successfully created.");	
		}
		else if (arg[0].equals("calc")) {
			if (arg[1].equals("add")) {
				Calc c = new Calc((byte)Integer.parseInt(arg[2]),(byte)Integer.parseInt(arg[3]));
				graphic.Println(c.Add() + "");
			}
			else if (arg[1].equals("sub")) {
				Calc c = new Calc((byte)Integer.parseInt(arg[2]),(byte)Integer.parseInt(arg[3]));
				graphic.Println(c.Substract() + "");
			}
			else if (arg[1].equals("mul")) {
				Calc c = new Calc((byte)Integer.parseInt(arg[2]),(byte)Integer.parseInt(arg[3]));
				graphic.Println(c.Multiply() + "");
			}
			else if (arg[1].equals("div")) {
				Calc c = new Calc((byte)Integer.parseInt(arg[2]),(byte)Integer.parseInt(arg[3]));
				graphic.Println(c.Division() + "");
			}
			else
				graphic.Println("Unknown type. (type: add,sub,mul,div)");
		}
		else if (arg[0].equals("read")) {
			try { graphic.Println(CurrentDisk.FileList.get(arg[1]).content); }
			catch (NullPointerException e) { graphic.Println("File does not exist.");}
		}
		else if (arg[0].equals("memset")) {
			memory.setAddressValue(Integer.parseInt(arg[1]), (byte)Integer.parseInt(arg[2]));
		}
		else if (arg[0].equals("cpureq")) {
			cpu.request((byte)Integer.parseInt(arg[1]));
		}
		else if (arg[0].equals("fdisk")) {
			VirtualDisk vdisk = new VirtualDisk(arg[1], arg[1].toCharArray()[0]);
			vp.DiskList.put(arg[1].toCharArray()[0],vdisk);
		}
		else if (arg[0].equals("diskdir")) {
			int i = 0;
			graphic.Println(vp.name);
			vp.DiskList.forEach((key,val)
			-> graphic.Println(i + " " + key));
		}
		else if (arg[0].equals("dm")) {
			CurrentDisk = vp.DiskList.get(arg[1].toCharArray()[0]);
		}
		else if (arg[0].equals("mem")) {
			int i = 0;
			for (byte b : memory.getMap()) {
				if (b >= Integer.parseInt(arg[1]))
					break;
				graphic.Println("0x" + Integer.toHexString(i) + "    " + b);
				i++;
			}
				
		}
		else if (arg[0].equals("mkdir")) {
			try {
			if (arg[2].equals("disk")) {
				VirtualDirectory vd = new VirtualDirectory(CurrentDisk,arg[1]);
				}
			}
			catch (Exception e) {
				if (CurrentDirectory == null) {
					VirtualDirectory vd = new VirtualDirectory(CurrentDisk,arg[1]);
				}
				else {
					VirtualDirectory vd = new VirtualDirectory(CurrentDirectory,arg[1]);
				}
				
			}
			graphic.Println("Directory " + arg[1] + " was successfully created.");	
		}
		else if (arg[0].equals("del")) {
			if (CurrentDirectory == null) {
				CurrentDisk.DeleteFile(arg[1]);
				
			}
			else {
				CurrentDirectory.DeleteFile(arg[1]);
			}
		}
		else if (arg[0].equals("deldir")) {
			if (CurrentDirectory == null) {
				CurrentDisk.DeleteDirectory(arg[1]);
				
			}
			else {
				CurrentDirectory.DeleteDirectory(arg[1]);
			}
		}
		else if (arg[0].equals("write")) {
			VirtualFile vf = CurrentDisk.FileList.get(arg[1]);
			vf.content = line.split("\"")[1];
			vf.content = vf.content.replace("\\","\n");
			CurrentDisk.FileList.put(arg[1], vf);
		}
		else if (arg[0].equals("append")) {
			VirtualFile vf = CurrentDisk.FileList.get(arg[1]);
			vf.content = vf.content + line.split("\"")[1];
			vf.content = vf.content.replace("\\","\n");
			CurrentDisk.FileList.put(arg[1], vf);
		}
		else if (arg[0].toCharArray()[0] == '*')
			;
		else if (arg[0].equals("dir")) {
			try {
			if (arg[1].equals("disk")) {
				CurrentDisk.DirectoryList.forEach((key,val)->{
					graphic.Println("DIR " + key);
				});
				CurrentDisk.FileList.forEach((key,val)->{
					graphic.Println(key);
				});
			}
			}
			catch(Exception e) {
				if (CurrentDirectory != null) {
					int i = 0;
					CurrentDirectory.DirectoryList.forEach((key,val)->{
						graphic.Println("DIR " + key);
					});
					i = 0;
					CurrentDirectory.FileList.forEach((key,val)->{
						graphic.Println(key);
					});
				}
				else {
					CurrentDisk.DirectoryList.forEach((key,val)->{
						graphic.Println("DIR " + key);
					});
					CurrentDisk.FileList.forEach((key,val)->{
						graphic.Println(key);
					});
				}
				
			}
		}
		else if (arg[0].equals("com")) {
			//.EXC
			for (String li : CurrentDisk.FileList.get(arg[1]).content.split("\n")) {
				Transfer(li);
			}
		}
		else if (arg[0].equals("setreg")) {
			if (arg[1].equals("si")) {
				cpu.si = line.split("\"")[1];
			}
			else if (arg[1].equals("ia")) {
				cpu.ia = (byte)Integer.parseInt(arg[2]);
			}
			else if (arg[1].equals("is")) {
				cpu.is = (byte)Integer.parseInt(arg[2]);
			}
			else if (arg[1].equals("sa")) {
				cpu.sa = line.split("\"")[1];
			}
		}
		else if (arg[0].equals("shutdown")) {
			Shutdown();
		}
		else if (arg[0].equals(""))
			; //do nothing
		else if (arg[0].equals("cd")) {
			if (!arg[1].equals("disk")) {
				if (CurrentDirectory == null) {
					
					vdir = CurrentDisk.DirectoryList.get(arg[1]);
					CurrentDirectory = vdir;
				}
				else if (CurrentDirectory != null) {
					CurrentDirectory = vdir.DirectoryList.get(arg[1]);
				}
				
			}
			else {
				CurrentDirectory = null;
				vdir = null;
			}
			
		}
		else {
			graphic.Println("Command not found.");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			graphic.Print("Error: ");
			graphic.Println(e.getMessage());
			graphic.Println("Press any key to exit..");
			graphic.readKey();
			Shutdown();
		}
	}
	@Override
	public void Booted() {
		
			if (booted) {
			booted = false;
			CurrentDisk = vp.DiskList.get("A".toCharArray()[0]);
		while(true) {
			graphic.Print(">");
			value = graphic.GetInput();
			graphic.Println("");
			Transfer(value);
				}
			}
	}
	@Override
	public void Run() {
		// TODO Auto-generated method stub
		
	}

}
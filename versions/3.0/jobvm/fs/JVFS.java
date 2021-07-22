/* 
 * JOB Virtual File system (JVFS)
 * Version 0.4
 * 
 * CHANGES:
 * 
 * Temporally removed Directory System. 
 * 
 * */
package jobvm.fs;

public class JVFS {
	public static char[] VOLUME_LABEL = new char[11];
	public static final double Version = 0.4;
	public static void CreateVFS(char[] label) {
		VOLUME_LABEL = label;
	}
}

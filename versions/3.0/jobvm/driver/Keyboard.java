package jobvm.driver;

import java.awt.event.KeyEvent;

import jobvm.OSLoader;

public class Keyboard {
	public KeyEvent key;
	public void sendKeyRequest(KeyEvent k) {
		key = k;
		OSLoader.Instance.keyTyped(k);
	}
}

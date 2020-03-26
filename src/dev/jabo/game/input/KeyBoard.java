package dev.jabo.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard implements KeyListener {
	
	// Predefined Key Codes
	public static final int KEY_LEFT = 37;
	public static final int KEY_UP = 38;
	public static final int KEY_RIGHT = 39;
	public static final int KEY_DOWN = 40;
	
	private boolean keys[] = new boolean[256];
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	/**
	 * 
	 * @param keyCode Defines the KeyCode
	 * 
	 * @return Returns a boolean depending on the state of the key with given KeyCode
	 * 
	 * */
	public boolean isKeyPressed(int keyCode) {
		return keys[keyCode];
	}

}

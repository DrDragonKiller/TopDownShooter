package com.topdown.shooter.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class Keyboard implements KeyListener {
	
	private boolean[]	  keysTriggered	= new boolean[525];
	Map<Integer, Boolean> keyTypedTests	= new HashMap<Integer, Boolean>() {
											private static final long serialVersionUID = 2298572452421699418L;
											
											{
												put(KeyEvent.VK_1, false);
												put(KeyEvent.VK_2, false);
											}
										};
	public boolean		  up, down, left, right, space, number1, number2;

	public void update() {
		up = keysTriggered[KeyEvent.VK_UP] || keysTriggered[KeyEvent.VK_W];
		down = keysTriggered[KeyEvent.VK_DOWN] || keysTriggered[KeyEvent.VK_S];
		left = keysTriggered[KeyEvent.VK_LEFT] || keysTriggered[KeyEvent.VK_A];
		right = keysTriggered[KeyEvent.VK_RIGHT] || keysTriggered[KeyEvent.VK_D];
		space = keysTriggered[KeyEvent.VK_SPACE];
		number1 = keyTypedTests.get(KeyEvent.VK_1);
		number2 = keyTypedTests.get(KeyEvent.VK_2);
		// resetTypedKeys();
		
		for (int i = 0; i < keysTriggered.length; i++) {
			if (keysTriggered[i]) {
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		keysTriggered[e.getKeyCode()] = true;
	}
	
	public void keyReleased(KeyEvent e) {
		keysTriggered[e.getKeyCode()] = false;
		final int charKey = e.getKeyChar();
		if (keyTypedTests.containsKey(charKey)) {
			keyTypedTests.put(charKey, true);
		}
	}
	
	public void keyTyped(KeyEvent e) {
		
	}

	public void resetTypedKeys() {
		for (Entry<Integer, Boolean> entry : keyTypedTests.entrySet()) {
			keyTypedTests.put(entry.getKey(), false);
		}
	}


}
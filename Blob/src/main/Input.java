package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {

	static boolean LEFT = false;
	static boolean RIGHT = false;
	static boolean UP = false;
	static boolean DOWN = false;
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			Input.LEFT = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Input.RIGHT = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			Input.UP = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			Input.DOWN = true;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			Input.LEFT = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Input.RIGHT = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			Input.UP = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			Input.DOWN = false;
		}
	}
	
	
}

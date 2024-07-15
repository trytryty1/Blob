package main;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Main {
	
	static JFrame frame;
	static World world;
	
	static boolean running = false;
	
	public static void main(String[] args) {
		world = new World(50,50,25);
		startFrame();
		
		start();
	}
	
	public static void start() {
		running = true;
		while(running) {
			world.update();
			frame.repaint();
			
			// Check if the simulation needs to be reset
			if (Input.RESET) {
				world = new World(50,50,25);
				Input.RESET = false;
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				System.err.println("Bro, how did the thread not sleep correctly?!?!");
				e.printStackTrace();
			}
		}
	}
	
	public static void startFrame() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(750, 750);
		frame.setContentPane(new WorldRenderer());
		frame.addKeyListener(Input.getInput());
		frame.setJMenuBar(createJMenuBar());
		frame.setVisible(true);
	}
	
	public static JMenuBar createJMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		
		///////////////
		// Create menus
		///////////////
		
		// File menu
		JMenu fileMenu = new JMenu("File");
		
		JMenuItem resetMenuItem = new JMenuItem("Reset");
		resetMenuItem.addActionListener(Input.getInput());
		fileMenu.add(resetMenuItem);
		
		// Add menus to the menu bar
		menuBar.add(fileMenu);
		
		return menuBar;
	}
	
	public static float distance(Transform t1, Transform t2) {
		return (float) Math.hypot(t1.x-t2.x, t1.y-t2.y);
	}

}
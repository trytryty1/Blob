package main;

import javax.swing.JFrame;

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
		frame.addKeyListener(new Input());
		frame.setVisible(true);
	}
	
	public static float distance(Transform t1, Transform t2) {
		return (float) Math.hypot(t1.x-t2.x, t1.y-t2.y);
		
	}

}
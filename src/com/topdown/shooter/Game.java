package com.topdown.shooter;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.topdown.shooter.graphics.Screen;


public class Game extends Canvas implements Runnable {
	
	private static final long	serialVersionUID	= -3894242138547079386L;
	public static int			width				= 300;
	public static int			height				= width / 16 * 9;
	public static int			scale				= 3;
	
	private Thread	thread;
	private JFrame	frame;
	private boolean	running	= false;
	
	private Screen screen;

	private BufferedImage	image	= new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[]			pixels	= ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	public Game() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		
		screen = new Screen(width, height);

		frame = new JFrame();
	}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	public void run() { // called by thread start
		while (running) {
			update(); // Method 60/s
			render(); // Method as often as possible
		}
	}

	public void update() {

	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3); // trippleBuffering
			return;
		}

		Graphics g = bs.getDrawGraphics();
		
		// all graphics we need, need to get here
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		
		g.dispose(); // render image
		bs.show(); // show next buffer
	}
	
	
	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle("TopDownShooter");
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.start();
	}
}

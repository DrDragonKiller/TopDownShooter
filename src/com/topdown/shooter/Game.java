package com.topdown.shooter;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.topdown.shooter.entity.mob.Player;
import com.topdown.shooter.graphics.Screen;
import com.topdown.shooter.input.Keyboard;
import com.topdown.shooter.level.Level;
import com.topdown.shooter.level.RandomLevel;


public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = -3894242138547079386L;
	public static int		  width			   = 300;
	public static int		  height		   = width / 16 * 9;
	public static int		  scale			   = 3;
	public static String	  title			   = "TopDownShooter";
	
	private Thread	 thread;
	private JFrame	 frame;
	private Keyboard key;
	private Level	 level;
	private Player	 player;
	private boolean	 gameRunning = false;
	
	private Screen screen;

	private BufferedImage image	 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[]		  pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	public Game() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		
		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		level = new RandomLevel(32, 32);
		player = new Player(key);

		addKeyListener(key);
		
	}
	
	public synchronized void start() {
		gameRunning = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	
	public synchronized void stop() {
		gameRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	public void run() { // called by thread start
		long lasttime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;

		requestFocus();
		
		while (gameRunning) {
			long now = System.nanoTime();
			delta += (now - lasttime) / ns;
			lasttime = now;

			while (delta >= 1) {
				update(); // Method calls 60/s
				updates++;
				delta--;
			}
			render(); // Method calls as often as possible
			frames++;

			if (System.currentTimeMillis() - timer > 1000) { // activates once a second
				timer += 1000; // System.currentTimeMillis() increased by 1000 and so timer decreased by 1000 to reset the subtraction to 0
				System.out.println(updates + " ups, " + frames + " fps");
				frame.setTitle(title + "  |  " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;


			}
		}
		stop();
	}


	public void update() {
		key.update();
		player.update();
	}


	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3); // trippleBuffering
			return;
		}
		
		screen.clear();
		int xScroll = player.x - screen.getWidth() / 2;
		int yScroll = player.y - screen.getHeight() / 2;
		level.render(xScroll, yScroll, screen);
		player.render(screen);

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.getPixels()[i];
		}

		Graphics g = bs.getDrawGraphics();

		// all graphics we need, need to get here:
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

		// g.setColor(Color.WHITE);
		// g.setFont(new Font("Verdana", 0, 50));
		// g.drawString("X: " + player.x + ", Y: " + player.y, 450, 400);
		
		g.dispose(); // render image
		bs.show(); // show next buffer
	}
	
	
	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(Game.title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.start();
	}
}

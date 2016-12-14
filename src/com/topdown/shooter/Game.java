package com.topdown.shooter;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import com.topdown.shooter.entity.Detect;
import com.topdown.shooter.entity.Entity;
import com.topdown.shooter.entity.Item;
import com.topdown.shooter.entity.mob.ItemMob;
import com.topdown.shooter.entity.mob.Player;
import com.topdown.shooter.entity.mob.Tree;
import com.topdown.shooter.entity.projectile.Projectile;
import com.topdown.shooter.graphics.Screen;
import com.topdown.shooter.graphics.Sprite;
import com.topdown.shooter.graphics.SpriteSheet;
import com.topdown.shooter.graphics.Overlay.Overlay;
import com.topdown.shooter.input.Keyboard;
import com.topdown.shooter.level.Level;
import com.topdown.shooter.level.LoadLevel;
import com.topdown.shooter.level.RandomLevel;
import com.topdown.shooter.level.tile.Tile;
import com.topdown.shooter.entity.Detect;


public class Game extends Canvas implements Runnable {
	
	private final int ups	   = 60;
	final boolean	  finalMap = false;

	private final boolean debug = false;
	
	private static final long serialVersionUID = -3894242138547079386L;
	public static int		  width			   = 300;
	public static int		  height		   = width / 16 * 9;
	public static int		  scale			   = 3;
	public static String	  title			   = "TopDownShooter";
	
	private Thread		 thread;
	private JFrame		 frame;
	private Keyboard	 key;
	public static Player player;
	private boolean		 gameIsRunning = false;
	public Detect		 detect;
	
	public LoadLevel currentLevel;
	
	private static Screen screen;
	
	private BufferedImage image	   = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[]		  pixels   = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private List<Overlay> overlays = new ArrayList<Overlay>();
	
	
	public Player getPlayer() {
		return player;
	}
	
	public static Screen getScreen() {
		return screen;
	}
	
	public Game() {
		if (finalMap)
			currentLevel = LoadLevel.mainMap;
		else
			currentLevel = LoadLevel.levelMap;

		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		
		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		// player = new Player(8 + 16 * 7, 8 + 16 * 8, key);
		if (finalMap)
			player = new Player(16 * 39, 16 * 68, key);
		else
			player = new Player(18, 42, key);
		player.init(currentLevel);
		
		addKeyListener(key);
		
		// for (int y = 0; y < 5; y++) {
		// for (int x = 0; x < 5; x++) {
		// currentLevel.add(new Tree(23 * x + 100, 285 + 40 * y));
		// }
		// }
		
		
		overlays.add(Overlay.slectedStuff);
		overlays.add(Overlay.achievement);
		overlays.add(Overlay.quest);
		if (finalMap) {
			currentLevel.add(new ItemMob(16 * 33, 16 * 71, Item.jonas));
			currentLevel.add(new ItemMob(16 * 54, 16 * 75, Item.sven));
			currentLevel.add(new ItemMob(16 * 76, 16 * 86, Item.martin));
			currentLevel.add(new ItemMob(16 * 72, 16 * 67, Item.kenneth));
			for (int i = 0; i < 5000; i++) {
				int xz = (int) (Math.random() * 257);
				int yz = (int) (Math.random() * 257);
				if (!(currentLevel.getTile(xz, yz).equals(Tile.grass) || currentLevel.getTile(xz, yz).equals(Tile.pathInnercornerN) || currentLevel.getTile(xz, yz).equals(Tile.pathInnercornerO) || currentLevel.getTile(xz, yz).equals(Tile.pathInnercornerS) || currentLevel.getTile(xz, yz).equals(Tile.pathInnercornerW) || currentLevel.getTile(xz, yz).equals(Tile.pathMiddle) || currentLevel.getTile(xz, yz).equals(Tile.pathOuttercornerN) || currentLevel.getTile(xz, yz).equals(Tile.pathOuttercornerO) || currentLevel.getTile(xz, yz).equals(Tile.pathOuttercornerS) || currentLevel.getTile(xz, yz).equals(Tile.pathOuttercornerW) || currentLevel.getTile(xz, yz).equals(Tile.pathSideN) || currentLevel.getTile(xz, yz).equals(Tile.pathSideO) || currentLevel.getTile(xz, yz).equals(Tile.pathSideS) || currentLevel.getTile(xz, yz).equals(Tile.pathSideW) || currentLevel.getTile(xz, yz).equals(Tile.water))) {
					currentLevel.add(new Tree(xz * 16, yz * 16));
				}
			}

		} else {
			currentLevel.add(new Tree(207, 43));
			currentLevel.add(new Tree(66, 66));
			currentLevel.add(new ItemMob(30, -2, Item.jonas));
			currentLevel.add(new ItemMob(160, 59, Item.sven));
			currentLevel.add(new ItemMob(126, 131, Item.martin));
			currentLevel.add(new ItemMob(49, 162, Item.kenneth));
		}
		
		
	}
	
	public synchronized void start() {
		gameIsRunning = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	
	public synchronized void stop() {
		gameIsRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void run() { // called by thread start
		long lasttime = System.nanoTime(); // computertime
		long oneSecondTimer = System.currentTimeMillis();
		double deltaTime = 0;
		int frames = 0; // misst FPS
		int updates = 0; // misst UPS
		
		requestFocus();
		
		// int temp = 0;
		player.updateentityRenderOrder(player);
		while (gameIsRunning) {
			// temp++;
			long nowTime = System.nanoTime();
			deltaTime += (nowTime - lasttime) / (1_000_000_000.0 / ups); // Addieren Zeitunterschied als Verhältnis zu einer Sekunde
			lasttime = nowTime;
			
			while (deltaTime >= 1) {
				update(); // Method calls 60/s
				updates++;
				deltaTime--;
			}
			render(); // Method calls as often as possible
			frames++;
			
			if (System.currentTimeMillis() - oneSecondTimer > 1000) { // activates once a second
				oneSecondTimer += 1000; // System.currentTimeMillis() increased by 1000 and so timer decreased by 1000 to reset the subtraction to 0
				// System.out.println(updates + " ups, " + frames + " fps");
				frame.setTitle(title + "  |  " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
				
				
			}
			// System.out.println(temp);
			// if (temp > 20000) {
			// currentLevel = LoadLevel.mainMap;
			// }
		}
		stop();
	}
	
	
	public void update() {
		key.update();
		player.setLevel(currentLevel);

		currentLevel.update();
		for (int i = 0; i < overlays.size(); i++) {
			overlays.get(i).update();
		}
		for (int i = 0; i < currentLevel.getEntities().size(); i++) {
			Entity entity = currentLevel.getEntities().get(i);
			if (entity.isRemoved()) currentLevel.remove(entity);
			entity.updateInventory();
		}


		key.resetTypedKeys();


		// System.out.println(screen.xOffsetToMapOrigin + ", " + screen.yOffsetToMapOrigin);
	}
	
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3); // trippleBuffering
			return;
		}
		
		screen.clear();
		int xScreenTopLeft = player.x - screen.getWidth() / 2;
		int yTopLeft = player.y - screen.getHeight() / 2;
		currentLevel.renderTiles(xScreenTopLeft, yTopLeft, screen);
		
		// currentLevel.renderEntitiesBehindPlayer(screen, player);
		// player.render(screen);
		// currentLevel.renderEntitiesInfronOfPlayer(screen, player);
		
		currentLevel.render(screen, player);
		
		// for (int i = 0; i < items.size(); i++) {
		// items.get(i).render(screen);
		// }
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
		for (int i = 0; i < overlays.size(); i++) {
			overlays.get(i).render(screen, g);
		}
		
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.getPixels()[i];
		}


		// all graphics we need, need to get here:
		g.setColor(Color.WHITE);
		g.setFont(new Font("Verdana", 0, 50));
		if (debug) {
			g.drawString("X: " + player.x + ", Y: " + player.y + "dir: " + player.getDir(), 400, 400);
		}


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
	
	// public void render() {
	// BufferStrategy bs = getBufferStrategy();
	// if (bs == null) {
	// createBufferStrategy(3); // trippleBuffering for performance
	// return;
	// }
	//
	// screen.clear(); //deletes old image
	// int xScreenTopLeft = player.x - screen.getWidth() / 2; //X and Y Coordinate of top left Pixel on Screen
	// int yTopLeft = player.y - screen.getHeight() / 2;
	// currentLevel.renderTiles(xScreenTopLeft, yTopLeft, screen); //renders Background
	//
	// currentLevel.render(screen, player); //renders entities
	//
	// Graphics g = bs.getDrawGraphics();
	// g.drawImage(image, 0, 0, getWidth(), getHeight(), null); //creates image of rendered information
	//
	// for (int i = 0; i < overlays.size(); i++) {
	// overlays.get(i).render(screen, g); //renders all overlays
	// }
	//
	// for (int i = 0; i < pixels.length; i++) {
	// pixels[i] = screen.getPixels()[i]; //puts image in screen
	// }
	//
	// g.setColor(Color.WHITE);
	// g.setFont(new Font("Verdana", 0, 50));
	// if (debug) { //debug information
	// g.drawString("X: " + player.x + ", Y: " + player.y + "dir: " + player.getDir(), 400, 400); //X and Y Player
	// }
	//
	//
	// g.dispose(); // render image
	// bs.show(); // show next buffer
	// }
}

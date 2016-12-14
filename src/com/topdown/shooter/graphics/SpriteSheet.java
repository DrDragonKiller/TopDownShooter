package com.topdown.shooter.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class SpriteSheet {

	private String	 path;
	public final int SIZE;
	public int[]	 pixels;
	public int		 width, height;

	public static SpriteSheet tiles						   = new SpriteSheet("/textures/spritesheet.png", 256);
	public static SpriteSheet player_tiles				   = new SpriteSheet("/textures/playerNEW.png", 160);
	public static SpriteSheet projectiles				   = new SpriteSheet("/textures/projectiles/Projectiles.png", 32, 16);
	public static SpriteSheet overlayTest				   = new SpriteSheet("/textures/overlay.png", 300);
	public static SpriteSheet tree1						   = new SpriteSheet("/textures/things/tree1.png", 32, 64);
	public static SpriteSheet tree2						   = new SpriteSheet("/textures/things/tree2.png", 32, 64);
	public static SpriteSheet tree3						   = new SpriteSheet("/textures/things/tree3.png", 32, 64);
	public static SpriteSheet overlay_ProjectileBackground = new SpriteSheet("/textures/overlay/projectileBackground.png", 32, 32);
	public static SpriteSheet squad						   = new SpriteSheet("/textures/items/squad.png", 68, 33);
	public static SpriteSheet achievement				   = new SpriteSheet("/textures/overlay/achievement.png", 90, 30);
	// public static SpriteSheet quest = new SpriteSheet("/textures/overlay/quest.png", 300, 168);
	public static SpriteSheet quest = new SpriteSheet("/textures/overlay/quest.png", 300, 168);
	
	public SpriteSheet(String path, int size) {
		this.path = path;
		width = size;
		height = size;
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		loadImage();
	}

	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		this.width = width;
		this.height = height;
		SIZE = -1;
		pixels = new int[width * height];
		loadImage();
	}
	
	private void loadImage() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = width;
			int h = height;
			image.getRGB(0, 0, w, h, pixels, 0, w); // puts loaded Image into pixels[]
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

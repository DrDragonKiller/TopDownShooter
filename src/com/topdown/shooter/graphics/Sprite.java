package com.topdown.shooter.graphics;

import java.util.Arrays;


public class Sprite {

	public final int	SIZE;
	private int			x, y;
	public int[]		pixels;
	private SpriteSheet	sheet;
	private int			width, height;


	public static Sprite voidSprite	 = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite mapBorder	 = new Sprite(16, 16, 0, SpriteSheet.tiles);
	public static Sprite grass		 = new Sprite(16, 0, 16, SpriteSheet.tiles);
	public static Sprite grassFlower = new Sprite(16, 16, 16, SpriteSheet.tiles);
	public static Sprite grassStone	 = new Sprite(16, 32, 16, SpriteSheet.tiles);

	public static Sprite path_innercorner  = new Sprite(16, 0, 32, SpriteSheet.tiles);
	public static Sprite path_middle	   = new Sprite(16, 16, 32, SpriteSheet.tiles);
	public static Sprite path_side		   = new Sprite(16, 32, 32, SpriteSheet.tiles);
	public static Sprite path_outtercorner = new Sprite(16, 48, 32, SpriteSheet.tiles);

	public static Sprite water = new Sprite(16, 0, 48, SpriteSheet.tiles);
	
	public static Sprite stoneWall = new Sprite(16, 0, 64, SpriteSheet.tiles);
	
	
	public static Sprite player_forward	  = new Sprite(32, 0, 0, SpriteSheet.player_tiles);
	public static Sprite player_forward_1 = new Sprite(32, 0, 32, SpriteSheet.player_tiles);
	public static Sprite player_forward_2 = new Sprite(32, 0, 64, SpriteSheet.player_tiles);

	public static Sprite player_back   = new Sprite(32, 32, 0, SpriteSheet.player_tiles);
	public static Sprite player_back_1 = new Sprite(32, 32, 32, SpriteSheet.player_tiles);
	public static Sprite player_back_2 = new Sprite(32, 32, 64, SpriteSheet.player_tiles);

	public static Sprite player_left   = new Sprite(32, 64, 0, SpriteSheet.player_tiles);
	public static Sprite player_left_1 = new Sprite(32, 64, 32, SpriteSheet.player_tiles);
	public static Sprite player_left_2 = new Sprite(32, 64, 64, SpriteSheet.player_tiles);

	public static Sprite player_right	= new Sprite(32, 96, 0, SpriteSheet.player_tiles);
	public static Sprite player_right_1	= new Sprite(32, 96, 32, SpriteSheet.player_tiles);
	public static Sprite player_right_2	= new Sprite(32, 96, 64, SpriteSheet.player_tiles);
	
	public static Sprite player_attack_start_forward = new Sprite(32, 0, 32, SpriteSheet.player_tiles);
	public static Sprite player_attack_start_back	 = new Sprite(32, 0, 32, SpriteSheet.player_tiles);
	public static Sprite player_attack_start_left	 = new Sprite(32, 0, 32, SpriteSheet.player_tiles);
	public static Sprite player_attack_start_right	 = new Sprite(32, 0, 32, SpriteSheet.player_tiles);
	
	public static Sprite player_attack_forward_fired = new Sprite(32, 0, 96, SpriteSheet.player_tiles);
	public static Sprite player_attack_back_fired	 = new Sprite(32, 32, 96, SpriteSheet.player_tiles);
	public static Sprite player_attack_left_fired	 = new Sprite(32, 64, 96, SpriteSheet.player_tiles);
	public static Sprite player_attack_right_fired	 = new Sprite(32, 96, 96, SpriteSheet.player_tiles);

	public static Sprite player_attack_forward_cooldown	= new Sprite(32, 0, 128, SpriteSheet.player_tiles);
	public static Sprite player_attack_back_cooldown	= new Sprite(32, 32, 128, SpriteSheet.player_tiles);
	public static Sprite player_attack_left_cooldown	= new Sprite(32, 64, 128, SpriteSheet.player_tiles);
	public static Sprite player_attack_right_cooldown	= new Sprite(32, 96, 128, SpriteSheet.player_tiles);
	
	
	public static Sprite red_wizard_projectile	= new Sprite(16, 16, 0, 0, SpriteSheet.projectiles);
	public static Sprite blue_wizard_projectile	= new Sprite(16, 16, 16, 0, SpriteSheet.projectiles);
	
	
	public static Sprite overlay_projectile_background = new Sprite(32, 0, 0, SpriteSheet.overlay_ProjectileBackground);
	public static Sprite achievement				   = new Sprite(90, 30, 0, 0, SpriteSheet.achievement);
	public static Sprite quest						   = new Sprite(300, 168, 0, 0, SpriteSheet.quest);
	
	
	public static Sprite tree1 = new Sprite(32, 64, 0, 0, SpriteSheet.tree1, true);
	public static Sprite tree2 = new Sprite(32, 64, 0, 0, SpriteSheet.tree2, true);
	public static Sprite tree3 = new Sprite(32, 64, 0, 0, SpriteSheet.tree3, true);

	public static Sprite jonas	 = new Sprite(16, 20, 0, 0, SpriteSheet.squad);
	public static Sprite sven	 = new Sprite(18, 24, 16, 0, SpriteSheet.squad);
	public static Sprite martin	 = new Sprite(16, 24, 34, 0, SpriteSheet.squad);
	public static Sprite kenneth = new Sprite(18, 33, 50, 0, SpriteSheet.squad);
	
	
	public Sprite(int i, int j, int k, int l, SpriteSheet tree12, boolean b) {
		this(i, j, k, l, tree12);
	}
	
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		this(size, size, x, y, sheet);
	}
	
	public Sprite(int width, int height, int x, int y, SpriteSheet sheet) {
		SIZE = -1;
		pixels = new int[width * height];
		// this.x = x * width; // *size wegen der sprite size
		// this.y = y * height;
		this.x = x; // *size wegen der sprite size
		this.y = y;
		this.sheet = sheet;
		this.width = width;
		this.height = height;
		load();
	}

	private void load() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int stelle = (y + this.y) * sheet.width + this.x + x;
				pixels[x + y * width] = sheet.pixels[stelle];
			}

		}
	}
	
	public Sprite(int width, int height, int color) { // to construct not only square textures
		SIZE = -1;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		setColor(color);
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Sprite(int size, int color) {
		SIZE = size;
		width = size;
		height = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}


	private void setColor(int color) {
		Arrays.fill(pixels, color);
	}


}

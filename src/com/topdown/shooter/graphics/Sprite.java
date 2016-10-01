package com.topdown.shooter.graphics;

public class Sprite {
	
	public final int	SIZE;
	private int			x, y;
	public int[]		pixels;
	private SpriteSheet	sheet;
	
	public static Sprite voidSprite	 = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite grass		 = new Sprite(16, 0, 1, SpriteSheet.tiles);
	public static Sprite grassFlower = new Sprite(16, 1, 1, SpriteSheet.tiles);
	public static Sprite grassStone	 = new Sprite(16, 2, 1, SpriteSheet.tiles);

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size; // *size wegen der sprite size
		this.y = y * size;
		this.sheet = sheet;
		load();
	}

	public Sprite(int size, int color) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}

	private void setColor(int color) {
		for (int i = 0; i < SIZE * SIZE; i++) {
			pixels[i] = color;
		}
	}
	
	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(this.x + x) + (this.y + y) * sheet.SIZE];
			}
		}
	}
	
}

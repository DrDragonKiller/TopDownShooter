package com.topdown.shooter.graphics;

import java.awt.Color;
import java.util.Random;


public class Screen {
	
	public final int MAP_SIZE	   = 32;
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;
	public final int PIXEL_SIZE	   = 4;			  // 2^PixelSize
	
	
	private int	 width, height;
	public int[] pixels;
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
	
	private Random random = new Random();

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		
		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			tiles[i] = random.nextInt(0xffffff);
		}
		tiles[1] = tiles[2] = tiles[MAP_SIZE + 1] = 0x000000;
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void render(int xOffset, int yOffset) {
		for (int y = 0; y < height; y++) {
			int yp = y + yOffset;
			if (yp < 0 || yp >= height) continue;
			for (int x = 0; x < width; x++) {
				int xp = x + xOffset;
				if (xp < 0 || xp >= width) continue;
				pixels[(xp) + (yp) * width] = Sprite.grass.pixels[(x & 15) + (y & 15) * Sprite.grass.SIZE];
			}

		}
	}
}

// int tileIndex = ((xx >> PIXEL_SIZE) & MAP_SIZE_MASK) + ((yy >> PIXEL_SIZE) & MAP_SIZE_MASK) * MAP_SIZE; // x >> 4 == x / 16 BitOperator, schnellere Berechnung da auf binärer Ebene
// pixels[x + y * width] = tiles[tileIndex];
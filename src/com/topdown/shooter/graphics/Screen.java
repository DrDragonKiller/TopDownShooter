package com.topdown.shooter.graphics;

import java.awt.Color;
import java.util.Random;


public class Screen {
	
	public final int MAP_Size	   = 32;
	public final int MAP_Size_MASK = MAP_Size - 1;
	public final int Pixel_Size	   = 4;			  // 2^PixelSize
	
	
	private int	 width, height;
	public int[] pixels;
	public int[] tiles = new int[MAP_Size * MAP_Size];
	
	private Random random = new Random();

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		
		for (int i = 0; i < MAP_Size * MAP_Size; i++) {
			tiles[i] = random.nextInt(0xffffff);
		}
		tiles[1] = tiles[2] = tiles[MAP_Size + 1] = 0x000000;
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void render(int xOffset, int yOffset) {
		for (int y = 0; y < height; y++) {
			int yy = y + yOffset;
			// if (yy < 0 || yy >= height) {
			// break;
			// }
			for (int x = 0; x < width; x++) {
				int xx = x + xOffset;
				// if (xx < 0 || xx >= width) {
				// break;
				// }
				int tileIndex = ((xx >> Pixel_Size) & MAP_Size_MASK) + ((yy >> Pixel_Size) & MAP_Size_MASK) * MAP_Size; // x >> 4 == x / 16 BitOperator, schnellere Berechnung da auf binärer Ebene
				pixels[x + y * width] = tiles[tileIndex];
			}

		}
	}
}
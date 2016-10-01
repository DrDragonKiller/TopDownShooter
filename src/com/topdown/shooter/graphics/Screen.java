package com.topdown.shooter.graphics;

import java.awt.Color;
import java.util.Random;

import com.topdown.shooter.level.tile.Tile;


public class Screen {
	
	private int		  width, height;
	private int[]	  pixels;
	public final int  MAP_SIZE		= 32;
	public final int  MAP_SIZE_MASK	= MAP_SIZE - 1;
	private final int PIXEL_SIZE	= 4;		   // 2^PixelSize

	public int xOFfset, yOffset;
	
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
	
	private Random random = new Random();
	
	public int[] getPixels() {
		return pixels;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getPIXEL_SIZE() {
		return PIXEL_SIZE;
	}
	
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = (new int[width * height]);
		
		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			tiles[i] = random.nextInt(0xffffff);
		}
		tiles[1] = tiles[2] = tiles[MAP_SIZE + 1] = 0x000000;
	}
	
	public void clear() {
		for (int i = 0; i < getPixels().length; i++) {
			getPixels()[i] = 0;
		}
	}
	
	
	public void renderTile(int xp, int yp, Tile tile) {
		xp -= xOFfset;
		yp -= yOffset;
		for (int y = 0; y < tile.sprite.SIZE; y++) {
			int ya = y + yp;
			for (int x = 0; x < tile.sprite.SIZE; x++) {
				int xa = x + xp;
				if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break; // renders only tiles on screen
				if (xa < 0) xa = 0;
				getPixels()[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
			}
		}
	}
	
	public void setOffset(int xOffset, int yOffset) {
		this.yOffset = yOffset;
		xOFfset = xOffset;
	}
}


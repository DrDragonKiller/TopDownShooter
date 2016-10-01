package com.topdown.shooter.level;

import com.topdown.shooter.graphics.Screen;
import com.topdown.shooter.level.tile.Tile;


public class Level {
	
	protected int	width, height; // for randoom generation
	protected int[]	tiles;
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
		generateLevel();
	}

	public Level(String path) {
		loadLevel(path);
	}


	protected void generateLevel() {
		
		
	}
	
	private void loadLevel(String path) {
		
	}
	
	public void update() {
		
	}
	
	private void time() {
		
	}
	
	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> screen.getPIXEL_SIZE();
		int x1 = (xScroll + screen.getWidth() + 16) >> screen.getPIXEL_SIZE();
		int y0 = yScroll >> screen.getPIXEL_SIZE();
		int y1 = (yScroll + screen.getHeight() + 16) >> screen.getPIXEL_SIZE();
		
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
	}

	public Tile getTile(int x, int y) {
		// if(tiles[x+y*width] == 0) return Tile.grass;
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		switch (tiles[x + y * width]) {
			case 0:
			case 1:
			case 2:
				return Tile.grass;
			case 3:
			case 4:
				return Tile.grassFlower;
			case 5:
				return Tile.grassStone;
			default:
				return Tile.voidTile;
		}
	}
}

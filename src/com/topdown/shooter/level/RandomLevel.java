package com.topdown.shooter.level;

import java.util.Random;

import com.topdown.shooter.level.tile.Tile;


public class RandomLevel extends Level {

	private static final Random random = new Random();
	
	public RandomLevel(int width, int height) {
		super(width, height);
		
	}
	
	protected void generateLevel() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < height; x++) {
				tiles[x + y * width] = random.nextInt(7);
			}
			
		}
	}
	
}

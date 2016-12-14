package com.topdown.shooter.level;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.topdown.shooter.graphics.Sprite;
import com.topdown.shooter.graphics.SpriteSheet;
import com.topdown.shooter.level.tile.Tile;


public class LoadLevel extends Level {
	
	
	public int size;
	
	
	public static LoadLevel	levelMap = new LoadLevel("/levels/levelMap.png", 32, 32);
	public static LoadLevel	spawnMap = new LoadLevel("/levels/spawnMap.png", 16, 32);
	public static LoadLevel	mainMap	 = new LoadLevel("/levels/mainMap.png", 256, 256);

	public LoadLevel(String path, int width, int height) {
		super(width, height, path);
		pixels = new int[width * height];
		loadImage(path, pixels);
		fillColorDictionary();
	}

	private void fillColorDictionary() {
		int[] imageColorMapDictionary = new int[16 * 16];
		loadImage("/textures/ColorDictonary.png", imageColorMapDictionary);
		for (int x = 0; x < 16; x++) {
			for (int y = 0; y < 16; y++) {
				// if (!(imageColorMapDictionary[16*x+y]==-1)) {colourDic.put(imageColorMapDictionary[16*x+y], tileDic[16*x+y].equals(null)? Tile.voidTile : tileDic[16*x+y]);}
				if (!colourDic.containsKey(imageColorMapDictionary[16 * x + y]) && imageColorMapDictionary[16 * x + y] != -1 && !tileDic[16 * x + y].equals(null)) { // wenn Farbwert noch nicht im Dictionary enthalten
					colourDic.put(imageColorMapDictionary[16 * x + y], tileDic[16 * x + y]);
				}

			}
		}
	}
	
	private void loadImage(String imagePath, int[] imageArray) {
		try {
			BufferedImage image = ImageIO.read(LoadLevel.class.getResource(imagePath));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, imageArray, 0, w); // puts loaded Image into array[]

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void generateLevel() {
		pixels = new int[width * height];
		loadImage(path, pixels);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < height; x++) {
				switch (Integer.toHexString(pixels[x + y * width]).toUpperCase()) {
				}
			}
		}
	}
}

package com.topdown.shooter.graphics;

import java.util.Arrays;

import com.topdown.shooter.entity.mob.Player;
import com.topdown.shooter.entity.projectile.Projectile;
import com.topdown.shooter.level.tile.Tile;


public class Screen {

	private int width, height;
	
	private int[]	  pixels;
	private final int PIXEL_SIZE = 4; // 2^PixelSize
	
	public int xOffsetToMapOrigin, yOffsetToMapOrigin;


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
	}

	public void clear() {
		Arrays.fill(pixels, 0);
	}

	public void renderTile(int xTrueCoordinate, int yTrueCoordinate, Tile tile, String tileRotationNOSW) {
		xTrueCoordinate -= xOffsetToMapOrigin;
		yTrueCoordinate -= yOffsetToMapOrigin;
		
		switch (tileRotationNOSW) {
			case "O":
				Sprite spriteO = tile.sprite;
				int spriteOHeight = spriteO.getHeight();
				int spriteOWidth = spriteO.getWidth();

				for (int y = 0; y < spriteOHeight; y++) {
					int yCurrent = y + yTrueCoordinate;
					for (int x = 0; x < spriteOWidth; x++) {
						int xCurrent = x + xTrueCoordinate;
						if (xCurrent < -spriteOWidth || xCurrent >= getWidth() || yCurrent < 0 || yCurrent >= getHeight()) break; // renders only tiles on screen
						if (xCurrent < 0) xCurrent = 0;
						getPixels()[xCurrent + yCurrent * getWidth()] = spriteO.pixels[(spriteOHeight - 1) * spriteOWidth - (((y * spriteOWidth + x) % spriteOHeight) * spriteOWidth) + (y * spriteOWidth + x) / spriteOHeight];
					}
				}
				break;
			case "S":
				Sprite spriteS = tile.sprite;
				int spriteSHeight = spriteS.getHeight();
				int spriteSWidth = spriteS.getWidth();

				for (int y = 0; y < spriteSHeight; y++) {
					int yCurrent = y + yTrueCoordinate;
					for (int x = 0; x < spriteSWidth; x++) {
						int xCurrent = x + xTrueCoordinate;
						if (xCurrent < -spriteSWidth || xCurrent >= getWidth() || yCurrent < 0 || yCurrent >= getHeight()) break; // renders only tiles on screen
						if (xCurrent < 0) xCurrent = 0;
						getPixels()[xCurrent + yCurrent * getWidth()] = spriteS.pixels[spriteS.pixels.length - (y * spriteSWidth + x) - 1];
					}
				}
				break;
			case "W":
				Sprite spriteW = tile.sprite;
				int spriteWHeight = spriteW.getHeight();
				int spriteWWidth = spriteW.getWidth();

				for (int y = 0; y < spriteWHeight; y++) {
					int yCurrent = y + yTrueCoordinate;
					for (int x = 0; x < spriteWWidth; x++) {
						int xCurrent = x + xTrueCoordinate;
						if (xCurrent < -spriteWWidth || xCurrent >= getWidth() || yCurrent < 0 || yCurrent >= getHeight()) break; // renders only tiles on screen
						if (xCurrent < 0) xCurrent = 0;
						getPixels()[xCurrent + yCurrent * getWidth()] = spriteW.pixels[(spriteWWidth - 1) + (((y * spriteWWidth + x) % spriteWHeight) * spriteWWidth) - (y * spriteWWidth + x) / spriteWHeight];
					}
				}
				break;
			case "N":
			default:
				Sprite spriteN = tile.sprite;
				int spriteNHeight = spriteN.getHeight();
				int spriteNWidth = spriteN.getWidth();

				for (int y = 0; y < spriteNHeight; y++) {
					int yCurrent = y + yTrueCoordinate;
					for (int x = 0; x < spriteNWidth; x++) {
						int xCurrent = x + xTrueCoordinate;
						if (xCurrent < -spriteNWidth || xCurrent >= getWidth() || yCurrent < 0 || yCurrent >= getHeight()) break; // renders only tiles on screen
						if (xCurrent < 0) xCurrent = 0;
						getPixels()[xCurrent + yCurrent * getWidth()] = spriteN.pixels[x + y * spriteNWidth];
					}
				}
		}
	}

	public void renderSprite(int xTrueCoordinate, int yTrueCoordinate, Sprite sprite, boolean fixedPosition) {
		if (fixedPosition) {
			xTrueCoordinate -= xOffsetToMapOrigin;
			yTrueCoordinate -= yOffsetToMapOrigin;
		}
		for (int y = 0; y < sprite.getHeight() - 1; y++) {
			int yCurrent = y + yTrueCoordinate;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xCurrent = x + xTrueCoordinate;
				if (xCurrent < 0 || xCurrent >= getWidth() || yCurrent < 0 || yCurrent >= getHeight()) continue; // renders only tiles on screen
				int color = sprite.pixels[x + y * sprite.getWidth()];
				if (color != 0xFFFF00FF) getPixels()[xCurrent + yCurrent * getWidth()] = color;
			}
		}
	}

	public void renderProjectile(int xTrueCoordinate, int yTrueCoordinate, Projectile p) {
		xTrueCoordinate -= xOffsetToMapOrigin;
		yTrueCoordinate -= yOffsetToMapOrigin;
		for (int y = 0; y < p.getSprite().getHeight(); y++) {
			int yCurrent = y + yTrueCoordinate;
			for (int x = 0; x < p.getSprite().getWidth(); x++) {
				int xCurrent = x + xTrueCoordinate;
				if (xCurrent < -p.getSprite().getWidth() || xCurrent >= getWidth() || yCurrent < 0 || yCurrent >= getHeight()) break; // renders only tiles on screen
				if (xCurrent < 0) xCurrent = 0;
				int color = p.getSprite().pixels[x + y * p.getSprite().getHeight()];
				if (color != 0xFFFF00FF) getPixels()[xCurrent + yCurrent * getWidth()] = color;
			}
		}
	}


	public void renderPlayer(int xTrueCoordinate, int yTrueCoordinate, Sprite sprite, boolean xFlip, boolean yFlip) {
		xTrueCoordinate -= xOffsetToMapOrigin;
		yTrueCoordinate -= yOffsetToMapOrigin;
		for (int y = 0; y < 32; y++) {
			int yCurrent = y + yTrueCoordinate;
			int yFlipFixed = y;
			if (yFlip) yFlipFixed = 31 - y;

			for (int x = 0; x < 32; x++) {
				int xCurrent = x + xTrueCoordinate;
				int xFlipFixed = x;
				if (xFlip) xFlipFixed = 31 - x;

				if (xCurrent < -32 || xCurrent >= getWidth() || yCurrent < 0 || yCurrent >= getHeight()) break; // renders only tiles on screen
				if (xCurrent < 0) xCurrent = 0;
				int color = sprite.pixels[xFlipFixed + yFlipFixed * 32];
				if (color != 0xFFFF00FF) getPixels()[xCurrent + yCurrent * getWidth()] = color; // test for transparent violett => transparent
			}
		}
	}
	
	public void setOffset(int xOffset, int yOffset) {
		xOffsetToMapOrigin = xOffset;
		yOffsetToMapOrigin = yOffset;
	}
}

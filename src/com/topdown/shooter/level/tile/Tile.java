package com.topdown.shooter.level.tile;

import com.topdown.shooter.graphics.Screen;
import com.topdown.shooter.graphics.Sprite;


public class Tile {

	public int	  x, y;
	public Sprite sprite;

	public static Tile voidTile	   = new voidTile(Sprite.voidSprite);
	public static Tile grass	   = new GrassTile(Sprite.grass);
	public static Tile grassFlower = new GrassFlowerTile(Sprite.grassFlower);
	public static Tile grassStone  = new GrassStoneTile(Sprite.grassStone);

	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	public void render(int x, int y, Screen screen) {

	}

	public boolean solid() {
		return false;
	}
}

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
	public static Tile pathTop  = new GrassStoneTile(Sprite.path_top);
	public static Tile pathRight  = new GrassStoneTile(Sprite.path_right);
	public static Tile pathLeft  = new GrassStoneTile(Sprite.path_left);
	public static Tile pathBottom  = new GrassStoneTile(Sprite.path_bottom);
	public static Tile pathInnercorner  = new GrassStoneTile(Sprite.path_innercorner);
	public static Tile pathOuttercorner  = new GrassStoneTile(Sprite.path_outtercorner);
	public static Tile pathMiddle  = new GrassStoneTile(Sprite.path_middle);

	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	public void render(int x, int y, Screen screen) {

	}

	public boolean solid() {
		return false;
	}
}

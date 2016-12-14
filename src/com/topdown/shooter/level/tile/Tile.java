package com.topdown.shooter.level.tile;

import com.topdown.shooter.graphics.Screen;
import com.topdown.shooter.graphics.Sprite;


public class Tile {

	public int	  x, y;
	public Sprite sprite;

	public static Tile voidTile	 = new voidTile(Sprite.voidSprite);
	public static Tile mapBorder = new voidTile(Sprite.mapBorder);
	
	public static Tile grass	   = new GrassTile(Sprite.grass);
	public static Tile grassFlower = new GrassFlowerTile(Sprite.grassFlower);
	public static Tile grassStone  = new GrassStoneTile(Sprite.grassStone);
	
	public static Tile pathSideN = new pathSideN(Sprite.path_side);
	public static Tile pathSideO = new pathSideO(Sprite.path_side);
	public static Tile pathSideS = new pathSideS(Sprite.path_side);
	public static Tile pathSideW = new pathSideW(Sprite.path_side);
	
	public static Tile pathInnercornerN	= new pathInnercornerN(Sprite.path_innercorner);
	public static Tile pathInnercornerO	= new pathInnercornerO(Sprite.path_innercorner);
	public static Tile pathInnercornerS	= new pathInnercornerS(Sprite.path_innercorner);
	public static Tile pathInnercornerW	= new pathInnercornerW(Sprite.path_innercorner);
	
	public static Tile pathOuttercornerN = new pathOuttercornerN(Sprite.path_outtercorner);
	public static Tile pathOuttercornerO = new pathOuttercornerO(Sprite.path_outtercorner);
	public static Tile pathOuttercornerS = new pathOuttercornerS(Sprite.path_outtercorner);
	public static Tile pathOuttercornerW = new pathOuttercornerW(Sprite.path_outtercorner);
	public static Tile pathMiddle		 = new pathMiddle(Sprite.path_middle);

	public static Tile stoneWall = new StoneWallTile(Sprite.stoneWall);
	
	public static Tile water = new water(Sprite.water);


	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	public void render(int x, int y, Screen screen) {

	}
	
	
	public boolean solid() {
		return false;
	}
}

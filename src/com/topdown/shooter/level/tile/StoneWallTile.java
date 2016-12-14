package com.topdown.shooter.level.tile;

import com.topdown.shooter.graphics.Screen;
import com.topdown.shooter.graphics.Sprite;


public class StoneWallTile extends Tile {
	public StoneWallTile(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << screen.getPIXEL_SIZE(), y << screen.getPIXEL_SIZE(), this, "N");
	}

	public boolean solid() {
		return true;
	}
}

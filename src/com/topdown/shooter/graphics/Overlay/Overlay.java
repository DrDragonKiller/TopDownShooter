package com.topdown.shooter.graphics.Overlay;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Arrays;

import com.topdown.shooter.Game;
import com.topdown.shooter.entity.Item;
import com.topdown.shooter.graphics.Screen;
import com.topdown.shooter.graphics.Sprite;


public abstract class Overlay {

	private int			x, y;
	private Object[][]	spritesArray;	   // [Sprite], {x Verschiebung, Y Verschiebung}
	boolean				visible		= true;
	protected int		currentTime	= -1;
	protected final int	timeDelay;
	
	// public static Tile pathSideN = new pathSideN(Sprite.path_side);
	// public static Overlay logo = new SelectedStaff(Game.getScreen().getWidth() / 2 - 8, Game.getScreen().getHeight() / 2 - 38, new Object[][] { { Sprite.overlay_projectile_background, -8, -8 }, { Sprite.blue_wizard_projectile, 0, 0 } }, false, 90);
	public static Overlay slectedStuff = new SelectedStaff(10, 10, new Object[][] { { Sprite.overlay_projectile_background, -8, -8 }, { Sprite.blue_wizard_projectile, 0, 0 } }, false);
	public static Overlay achievement  = new achievement(10, Game.getScreen().getHeight() - 10 - Sprite.achievement.getHeight(), new Object[][] { { Sprite.achievement, 0, 0 }, { "hallo", 28, 65 }, { null, 0, 0 } }, false, 30);
	public static Overlay quest		   = new achievement(0, 0, Sprite.quest, false, 600);


	public Overlay(int x, int y, Object[][] spritesArray, boolean visible) {
		this(x, y, spritesArray, visible, 0);
	}
	
	public Overlay(int x, int y, Object[][] spritesArray, boolean visible, int timeDelay) {
		this.x = x;
		this.y = y;
		this.spritesArray = spritesArray;
		this.visible = visible;
		this.timeDelay = timeDelay;
	}

	public Overlay(int x, int y, Sprite sprite, boolean visible) {
		this(x, y, new Object[][] { { sprite, 0, 0 } }, visible);
	}
	
	public Overlay(int x, int y, Sprite sprite, boolean visible, int timeDelay) {
		this(x, y, new Object[][] { { sprite, 0, 0 } }, visible, timeDelay);
	}
	
	public Overlay(int x, int y, Sprite sprite, int xOffset, int yOffset, boolean visible) {
		this(x, y, new Object[][] { { sprite, xOffset, yOffset } }, visible);
	}
	
	public Overlay(int x, int y, Sprite sprite, int xOffset, int yOffset, boolean visible, int timeDelay) {
		this(x, y, new Object[][] { { sprite, xOffset, yOffset } }, visible, timeDelay);
	}
	
	public void render(Screen screen, Graphics g) {
		if (visible) {
			for (int i = 0; i < spritesArray.length; i++) {
				if (spritesArray[i][0] instanceof String) {
					
					
					g.setColor(Color.WHITE);
					g.setFont(new Font("Verdana", 0, 50));
					g.drawString((String) spritesArray[i][0], x * 3 + (int) spritesArray[i][1], y * 3 + (int) spritesArray[i][2]);
					continue;
				}
				if (spritesArray[i][0] != null) screen.renderSprite(x + (int) spritesArray[i][1], y + (int) spritesArray[i][2], (Sprite) spritesArray[i][0], false);
				
				
			}
		}
	}

	public void trigger() {
		currentTime = timeDelay;
	}

	public void trigger(Item item) {
	}
	
	public void update() {
		
	}

	protected void setSprite(Object object) {
		spritesArray = new Object[][] { { object, 0, 0 } };
	}

	protected void setSprite(Object[][] objects) {
		spritesArray = objects;
	}
	
	protected void setSprite(Object object, int xOffset, int yOffset, int layerPosition) {
		// Arrays.fill(spritesArray, i, i + 1, new Object[][] { { sprite, xOffset, yOffset } });
		spritesArray[layerPosition] = new Object[] { object, xOffset, yOffset };
	}

	protected Object[] getSprite(int layerPosition) {
		return spritesArray[layerPosition];
	}
	
}

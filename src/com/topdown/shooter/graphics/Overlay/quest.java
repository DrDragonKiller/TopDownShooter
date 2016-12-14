package com.topdown.shooter.graphics.Overlay;

import com.topdown.shooter.graphics.Sprite;
import com.topdown.shooter.Game;
import com.topdown.shooter.entity.Item;


public class quest extends Overlay {
	public quest(int x, int y, Sprite sprite, boolean visible) {
		super(x, y, sprite, visible);
	}

	public quest(int x, int y, Sprite sprite, boolean visible, int timeDelay) {
		super(x, y, sprite, visible, timeDelay);
	}

	public quest(int x, int y, Object[][] spritesArray, boolean visible) {
		super(x, y, spritesArray, visible);
	}

	public quest(int x, int y, Object[][] spritesArray, boolean visible, int timeDelay) {
		super(x, y, spritesArray, visible, timeDelay);
	}
	
	public void update() {
		if (timeDelay > 0) currentTime--;
		visible = currentTime > 0 || timeDelay == 0;
	}

	public void trigger() {
		currentTime = timeDelay;
	}
}

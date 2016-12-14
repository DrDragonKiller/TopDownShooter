package com.topdown.shooter.graphics.Overlay;

import com.topdown.shooter.graphics.Sprite;
import com.topdown.shooter.Game;


public class SelectedStaff extends Overlay {
	public SelectedStaff(int x, int y, Sprite sprite, boolean visible) {
		super(x, y, sprite, visible);
	}

	public SelectedStaff(int x, int y, Sprite sprite, boolean visible, int timeDelay) {
		super(x, y, sprite, visible, timeDelay);
	}

	public SelectedStaff(int x, int y, Object[][] spritesArray, boolean visible) {
		super(x, y, spritesArray, visible);
	}

	public SelectedStaff(int x, int y, Object[][] spritesArray, boolean visible, int timeDelay) {
		super(x, y, spritesArray, visible, timeDelay);
	}
	
	public void update() {
		if (Game.player.getProjectileKind() == "RedWizardProjectile") setSprite(Sprite.red_wizard_projectile, 0, 0, 1);
		if (Game.player.getProjectileKind() == "BlueWizardProjectile") setSprite(Sprite.blue_wizard_projectile, 0, 0, 1);
		if (timeDelay > 0) currentTime--;
		if (Game.player.getInput().space) {
			currentTime = timeDelay;
		}
		visible = currentTime > 0 || timeDelay == 0;
	}


}

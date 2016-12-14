package com.topdown.shooter.entity.projectile;

import com.topdown.shooter.graphics.Sprite;


public class BlueWizardProjectile extends PlayerProjectile {
	
	
	public BlueWizardProjectile(int x, int y, int xMovement, int yMovement) {
		super(x, y, xMovement, yMovement, 120, 50, 0.9);
		range = 100 + random.nextInt(100);
		speed = 2;
		damage = 40;
		setSprite(Sprite.blue_wizard_projectile);
		
	}
}

// public static final int FIREDELAY = 20;
// public static final int ATTACKUSETIME = 5;
// public static final int showAttackAnimationTime = (int) (0.9 * ATTACKUSETIME);
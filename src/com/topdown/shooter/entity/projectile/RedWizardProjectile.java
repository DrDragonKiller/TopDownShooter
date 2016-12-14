package com.topdown.shooter.entity.projectile;


import com.topdown.shooter.graphics.Sprite;


public class RedWizardProjectile extends PlayerProjectile {
	
	public RedWizardProjectile(int x, int y, int xMovement, int yMovement) {
		super(x, y, xMovement, yMovement, 60, 15, 0.65);
		range = 100 + random.nextInt(100);
		speed = 3;
		damage = 20;
		setSprite(Sprite.red_wizard_projectile);

	}


}

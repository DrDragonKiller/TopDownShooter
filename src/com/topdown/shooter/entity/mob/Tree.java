package com.topdown.shooter.entity.mob;

import com.topdown.shooter.entity.projectile.Projectile;
import com.topdown.shooter.graphics.Screen;
import com.topdown.shooter.graphics.Sprite;


public class Tree extends Mob {
	
	public Tree(int x, int y) {
		this.x = x;
		this.y = y;
		invulnurable = true;
		sprite = Sprite.tree3;
		hoverOverGround = 1;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void render(Screen screen) {
		screen.renderSprite(x, y, sprite, true);
	}

	@Override
	public Projectile generateProjectile(int x, int y, int xMovement, int yMovement) {
		return null;
	}
}

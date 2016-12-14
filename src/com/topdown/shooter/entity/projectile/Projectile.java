package com.topdown.shooter.entity.projectile;

import com.topdown.shooter.entity.Entity;
import com.topdown.shooter.entity.mob.Mob;
import com.topdown.shooter.entity.mob.Player;
import com.topdown.shooter.graphics.Sprite;


public abstract class Projectile extends Entity {

	protected final int	xOrigin, yOrigin;
	protected int		xMovement, yMovement;
	private Sprite		sprite;
	protected int		distanceTraveled = 0;
	protected double	speed, range, damage;
	protected int		hoverOverGround;

	public Projectile(int x, int y, int xa, int ya) {
		xOrigin = x;
		yOrigin = y;
		xMovement = xa;
		yMovement = ya;
		this.x = x;
		this.y = y;
		hoverOverGround = 0;

	}
	
	public int getHoverOverGround() {
		return hoverOverGround;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void updateProjectile() {
		updateentityRenderOrder(this);
	}
	
}

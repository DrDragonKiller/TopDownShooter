package com.topdown.shooter.entity.projectile;


import com.topdown.shooter.graphics.Screen;


public abstract class PlayerProjectile extends Projectile {

	protected final int	FIREDELAY;
	protected final int	ATTACKUSETIME;
	protected final int	showAttackAnimationTime;
	
	
	public int getFIREDELAY() {
		return FIREDELAY;
	}
	
	public int getATTACKUSETIME() {
		return ATTACKUSETIME;
	}
	
	public int getShowAttackAnimationTime() {
		return showAttackAnimationTime;
	}
	
	
	public PlayerProjectile(int x, int y, int xMovement, int yMovement, int FIREDELAY, int ATTACKUSETIME, double showAttackAnimationTime) {
		super(x, y, xMovement, yMovement);
		range = 100 + random.nextInt(100);
		hoverOverGround = 6;

		this.FIREDELAY = FIREDELAY;
		this.ATTACKUSETIME = ATTACKUSETIME;
		this.showAttackAnimationTime = (int) showAttackAnimationTime * ATTACKUSETIME;
		
	}

	public void update() {
		if (level.tilecollision(x, y, xMovement, yMovement, 10)) remove();
		move();
		updateProjectile();
	}
	
	protected void move() {
		int diagonal = (xMovement != 0 && yMovement != 0 && speed > 2) ? 1 : 0;
		x += xMovement * (speed - diagonal);
		y += yMovement * (speed - diagonal);
		if (distanceTraveled++ > range) remove();
	}
	
	
	public void render(Screen screen) {
		screen.renderProjectile(x, y, this);
	}


}

package com.topdown.shooter.entity.mob;

import com.topdown.shooter.entity.Entity;
import com.topdown.shooter.graphics.Sprite;


public abstract class Mob extends Entity {
	
	protected Sprite  sprite;
	protected int	  dir	 = -1;	 // 0=North 1=East 2=South 3=West
	protected boolean moving = false;

	public void move(int xa, int ya) {
		if (xa > 0) dir = 1;
		if (xa < 0) dir = 3;
		if (ya > 0) dir = 2;
		if (ya < 0) dir = 0;
		
		if (!collision())
		    x += xa;
		y += ya;
	}
	
	public void update() {
	}

	private boolean collision() {
		return false;
	}

	public void render() {

	}
}

package com.topdown.shooter.entity;

import com.topdown.shooter.entity.mob.Player;
import com.topdown.shooter.Game;


public abstract class Detect extends Entity {
	public Player  player;
	public int	   range;
	public int	   xPlayer;
	public int	   yPlayer;
	public boolean playerIsInRange;
	
	public static TreeDetect detect = new TreeDetect(0, 0, 10);
	
	public Detect(int x, int y, int range) {
		this.x = x;
		this.y = y;
		this.range = range;
	}
	
	public void update(int x, int y) {
		xPlayer = x - 8;
		yPlayer = y + 8;
		playerIsInRange = detectPlayerInRange();
		System.out.println("Player: " + playerIsInRange + " (" + xPlayer + ", " + yPlayer + ")");
	}
	
	public boolean detectPlayerInRange() {
		boolean isRange = false;
		if (x + range + 8 >= xPlayer && x - range + 8 <= xPlayer) {
			if (y + range + 8 >= yPlayer && y - range - 8 <= yPlayer) {
				isRange = true;
			}
		}
		return isRange;
	}
}

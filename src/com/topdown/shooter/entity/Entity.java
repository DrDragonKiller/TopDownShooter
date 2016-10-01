package com.topdown.shooter.entity;

import java.util.Random;

import com.topdown.shooter.graphics.Screen;
import com.topdown.shooter.level.Level;


public abstract class Entity {
	
	public int			   x, y;
	private boolean		   removed = false;
	protected Level		   level;
	protected final Random random  = new Random();
	
	public void update() {
	}
	
	public void render(Screen screen) {
		
	}
	
	public void remove() {
		// remove from level
		removed = true;
	}
	
	public boolean isRemoved() {
		return removed;
	}
}
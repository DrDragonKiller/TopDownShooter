package com.topdown.shooter.entity.mob;

import java.util.Random;

import com.topdown.shooter.entity.Entity;
import com.topdown.shooter.entity.Item;
import com.topdown.shooter.entity.projectile.Projectile;
import com.topdown.shooter.graphics.Screen;
import com.topdown.shooter.graphics.Sprite;


public class ItemMob extends Mob {
	
	private int				 xUpdated, yUpdated;
	static private final int MAXHOVER		= 50;
	static private final int HOVERMOVEDELAY	= 10;
	private boolean			 hoverDir;			 // true wenn up false wenn down
	private int				 currentHover;
	private Item			 item;
	
	public ItemMob(int x, int y, Item item) {
		this.x = xUpdated = x;
		this.y = yUpdated = y;
		invulnurable = true;
		this.item = item;
		sprite = item.getSprite();
		currentHover = MAXHOVER > 0 ? new Random().nextInt(MAXHOVER) + 1 : 0;
		hoverDir = new Random().nextBoolean();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getHoverMoveDelay() {
		return HOVERMOVEDELAY;
	}
	
	public Item getItem() {
		return item;
	}

	public void update() {
		if (MAXHOVER > 0) {
			if (currentHover <= 0 || currentHover >= MAXHOVER) {
				hoverDir = !hoverDir;
			}
			currentHover = hoverDir ? currentHover + 1 : currentHover - 1;
			yUpdated = y - currentHover / HOVERMOVEDELAY;
		}

	}

	public void render(Screen screen) {
		screen.renderSprite(xUpdated, yUpdated, sprite, true);
	}
	
	@Override
	public Projectile generateProjectile(int x, int y, int xMovement, int yMovement) {
		return null;
	}
	
}

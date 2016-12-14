package com.topdown.shooter.entity.mob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.topdown.shooter.entity.Entity;
import com.topdown.shooter.entity.Item;
import com.topdown.shooter.entity.projectile.PlayerProjectile;
import com.topdown.shooter.entity.projectile.Projectile;
import com.topdown.shooter.graphics.Sprite;
import com.topdown.shooter.graphics.Overlay.Overlay;
import com.topdown.shooter.level.tile.Tile;


public abstract class Mob extends Entity {
	
	protected Sprite  sprite;
	protected int	  dir			  = -1;		  // 0=North 1=East 2=South 3=West
	protected boolean moving		  = false;
	protected int	  maxHealth		  = 100;
	protected int	  currentHealth	  = maxHealth;
	protected boolean invulnurable	  = false;
	protected int	  hoverOverGround = 0;

	protected List<Projectile> projectiles = new ArrayList<Projectile>();
	
	
	protected int gunCoolDown = 0;
	
	public void move(int xMovement, int yMovement) {
		if (xMovement != 0 && yMovement != 0) {
			move(xMovement, 0);
			move(0, yMovement);
			return;
		}
		if (xMovement > 0) dir = 1;
		if (xMovement < 0) dir = 3;
		if (yMovement > 0) dir = 2;
		if (yMovement < 0) dir = 0;
		
		if (x + xMovement >= 0 && x + xMovement <= level.width * 16 && (y + yMovement + (this instanceof Player ? 16 : 0)) >= 0 && y + yMovement + (this instanceof Player ? 16 : 0) <= level.height * 16) {
			if (!collision(xMovement, 0)) {
				x += xMovement;
			}
			if (!collision(0, yMovement)) {
				y += yMovement;
			}
		}
	}
	
	public void update() {
	}
	
	private boolean collision(int xa, int ya) {
		boolean solid = false;
		for (int i = 0; i < 4; i++) {
			int xNextTile = ((x + xa) + i % 2 * 14 - 8) / 16;
			int yNextTile = ((y + ya) + i / 2 * 0 + 15) / 16;
			if (level.getTile(xNextTile, yNextTile).solid()) solid = true;
		}
		return solid;
	}
	
	public void render() {
	}
	
	
	public void shoot(int x, int y, int xMovement, int yMovement) {
		// System.out.println("shoot");
		Projectile mn = (Projectile) generateProjectile(x, y, xMovement, yMovement);
		projectiles.add(mn);
		level.add(mn);
	}
	
	public abstract Entity generateProjectile(int x, int y, int xMovement, int yMovement);
	
	public int getHoverOverGround() {
		return hoverOverGround;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public void damageIncome(int amountOfDamageTaken) {
		currentHealth -= amountOfDamageTaken;
	}

	public int getDir() {
		return dir;
	}

	public Sprite getSprite() {
		return sprite;
	}
	
	protected void checkItem(Entity entityOrigin, int radius) {
		boolean isPlayer = entityOrigin instanceof Player;
		List<Entity> itemsInRange = level.getEntitesInRange(entityOrigin, radius, level.getItems());
		for (int i = 0; i < itemsInRange.size(); i++) {
			ItemMob item = (ItemMob) itemsInRange.get(i);
			entityOrigin.inventoryAdd(item.getItem(), 1);
			item.remove();
			level.remove(item);
			if (isPlayer) Overlay.achievement.trigger(item.getItem());
		}
	}
	
}

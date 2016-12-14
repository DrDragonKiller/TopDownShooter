package com.topdown.shooter.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.topdown.shooter.entity.mob.ItemMob;
import com.topdown.shooter.entity.mob.Mob;
import com.topdown.shooter.entity.mob.Player;
import com.topdown.shooter.entity.projectile.Projectile;
import com.topdown.shooter.graphics.Screen;
import com.topdown.shooter.graphics.Sprite;
import com.topdown.shooter.level.Level;
import com.topdown.shooter.level.tile.Tile;
import com.topdown.shooter.level.tile.voidTile;


public abstract class Entity {

	public int x, y;
	
	private boolean		   removed = false;
	protected Level		   level;
	protected final Random random  = new Random();
	
	protected Map<Item, Integer> inventory = new HashMap<Item, Integer>(); // String ist Itemname und Integer ist die Anzahl
	
	public void update() {
	}

	public void render(Screen screen) {
	}
	
	public void init(Level level) {
		this.level = level;
	}


	public void updateentityRenderOrder(Projectile p) {
		level.remove(p);
		level.add(p);
	}
	
	public void updateentityRenderOrder(Mob m) {
		level.remove(m);
		level.add(m);
	}
	
	public void updateentityRenderOrder(Player p) {
		level.remove(p);
		level.add(findRenderPosition(p), p);
	}

	public int findRenderPosition(Projectile p) {
		return findRenderPosition(p.getX(), p.getY() + p.getHoverOverGround(), p.getSprite().getWidth(), p.getSprite().getHeight());
	}
	
	public int findRenderPosition(Mob m) {
		return findRenderPosition(m.getX(), m.getY() - m.getHoverOverGround(), m.getSprite().getWidth(), m.getSprite().getHeight());
	}

	public int findRenderPosition(Player p) {
		return findRenderPosition(p.getX(), p.getY() - p.getHoverOverGround() - 16, p.getSprite().getWidth(), p.getSprite().getHeight());
	}

	public int findRenderPosition(int entityX, int entityY, int entityWidth, int entityHeight) {
		List<Entity> ALLentities = level.getEntities();
		int returnInt = ALLentities.size();


		for (int i = 0; i < ALLentities.size(); i++) {
			Entity entityN = ALLentities.get(i);
			if (entityN instanceof Mob) {
				if (entityN.y + ((Mob) entityN).getSprite().getHeight() - (((Mob) entityN).getHoverOverGround()) > entityY + entityHeight) {

					if (entityN.y + ((Mob) entityN).getSprite().getHeight() - ((Mob) entityN).getHoverOverGround() > entityY + entityHeight) {
						returnInt = i;
						break;
					}
					for (int n = i; n < ALLentities.size(); n++) {
						Entity entityI = ALLentities.get(n);
						if (entityI instanceof Mob) {
							if (entityI.x + ((Mob) entityI).getSprite().getWidth() >= entityX) {
								returnInt = n;
								break;
							}
						}
					}
					returnInt = ALLentities.size();


					break;
				}
			} else if (entityN instanceof Projectile) {
				if (entityN.y + ((Projectile) entityN).getSprite().getHeight() - ((Projectile) entityN).getHoverOverGround() >= entityY + entityHeight) {

					if (entityN.y + ((Projectile) entityN).getSprite().getHeight() - ((Projectile) entityN).getHoverOverGround() > entityY + entityHeight) {
						returnInt = i;
						break;
					}
					for (int n = i; n < ALLentities.size(); n++) {
						Entity entityI = ALLentities.get(n);
						if (entityI instanceof Projectile) {
							if (entityI.x + ((Projectile) entityI).getSprite().getWidth() >= entityX) {
								returnInt = n;
								break;
							}
						}
					}
					returnInt = ALLentities.size();


					break;
				}
			}
		}
		return returnInt;
	}
	
	public void inventoryAdd(Item item, int amount) {
		if (inventory.containsKey(item)) {
			inventory.put(item, inventory.get(item) + amount);
		} else
			inventory.put(item, amount);
	}
	
	public void updateInventory() {
		for (Map.Entry<Item, Integer> entry : inventory.entrySet()) {
			if (entry.getValue() <= 0) inventory.remove(entry.getKey());
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public boolean isRemoved() {
		return removed;
	}

	public void remove() {
		removed = true;
	}
}
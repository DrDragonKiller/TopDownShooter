package com.topdown.shooter.entity;

import java.util.List;
import java.util.Random;

import com.topdown.shooter.entity.mob.Mob;
import com.topdown.shooter.entity.mob.Player;
import com.topdown.shooter.entity.projectile.Projectile;
import com.topdown.shooter.graphics.Screen;
import com.topdown.shooter.graphics.Sprite;
import com.topdown.shooter.level.Level;
import com.topdown.shooter.level.tile.Tile;
import com.topdown.shooter.level.tile.voidTile;


public class Item {

	protected String name;
	protected Sprite sprite;
	
	public static Item jonas   = new Item("Jonas", Sprite.jonas);
	public static Item sven	   = new Item("Sven", Sprite.sven);
	public static Item martin  = new Item("Martin", Sprite.martin);
	public static Item kenneth = new Item("Kenneth", Sprite.kenneth);

	public Item(String name, Sprite sprite) {
		this.name = name;
		this.sprite = sprite;
	}
	
	public String getName() {
		return name;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
}
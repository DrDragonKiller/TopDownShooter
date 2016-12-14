package com.topdown.shooter.level;


import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.topdown.shooter.entity.Entity;
import com.topdown.shooter.entity.mob.ItemMob;
import com.topdown.shooter.entity.mob.Mob;
import com.topdown.shooter.entity.mob.Player;
import com.topdown.shooter.entity.projectile.PlayerProjectile;
import com.topdown.shooter.entity.projectile.Projectile;
import com.topdown.shooter.graphics.Screen;
import com.topdown.shooter.level.tile.Tile;
import com.topdown.shooter.level.tile.voidTile;


public class Level {
	
	public int				 width, height;										 // for randoom generation
	public int[]			 tiles;
	public String			 path;
	public Tile[]			 tileDic						 = new Tile[16 * 16];
	private int				 entityCountRenderInfronOfPlayer = 0;				 // Speichert, welches das letzte Entity in der Entity Liste ist, welches über dem Spieler ist
	private final static int GRAYBORDERRIGHT				 = 10;
	private final static int GRAYBORDERBOTTUM				 = 5;
	private final static int GRAYBORDERTOP					 = 7;
	private final static int GRAYBORDERLEFT					 = 10;
	
	
	Map<Integer, Tile> colourDic = new HashMap<Integer, Tile>();
	
	public int[] pixels;

	private List<Entity> entities = new LinkedList<Entity>();
	private List<Entity> items	  = new ArrayList<Entity>();
	
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		// tiles = new int[width * height];
		generateLevel();
	}

	public Level(int width, int height, String path) {
		this.path = path;
		this.width = width;
		this.height = height;
		// tiles = new int[width * height];
		fillTileDic();

		// generateLevel();
		
	}

	private void fillTileDic() {
		tileDic[16 * 0 + 0] = Tile.voidTile;
		tileDic[16 * 1 + 0] = Tile.grass;
		tileDic[16 * 1 + 1] = Tile.grassFlower;
		tileDic[16 * 1 + 2] = Tile.grassStone;
		
		tileDic[16 * 2 + 0] = Tile.pathInnercornerN;
		tileDic[16 * 2 + 1] = Tile.pathMiddle;
		tileDic[16 * 2 + 2] = Tile.pathSideN;
		tileDic[16 * 2 + 3] = Tile.pathOuttercornerN;
		tileDic[16 * 2 + 4] = Tile.pathInnercornerO;
		tileDic[16 * 2 + 5] = Tile.pathInnercornerS;
		tileDic[16 * 2 + 6] = Tile.pathInnercornerW;
		tileDic[16 * 2 + 7] = Tile.pathSideO; //
		tileDic[16 * 2 + 8] = Tile.pathSideS;
		tileDic[16 * 2 + 9] = Tile.pathSideW; //
		tileDic[16 * 2 + 10] = Tile.pathOuttercornerO;
		tileDic[16 * 2 + 11] = Tile.pathOuttercornerS;
		tileDic[16 * 2 + 12] = Tile.pathOuttercornerW;
		
		tileDic[16 * 3 + 0] = Tile.water;

		tileDic[16 * 4 + 0] = Tile.stoneWall;
		
		
	}

	public Level(String path) {
		loadLevel(path);
	}


	protected void generateLevel() {
		
		
	}
	
	private void loadLevel(String path) {
		
	}
	
	public void update() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
	}
	
	
	public void renderTiles(int xTopLeft, int yTopLeft, Screen screen) {
		screen.setOffset(xTopLeft, yTopLeft);
		// x >> y = x / 2^y auf BitEbene
		int x0 = xTopLeft >> screen.getPIXEL_SIZE();
		int x1 = (xTopLeft + screen.getWidth() + 16) >> screen.getPIXEL_SIZE();
		int y0 = yTopLeft >> screen.getPIXEL_SIZE();
		int y1 = (yTopLeft + screen.getHeight() + 16) >> screen.getPIXEL_SIZE();
		
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
	}
	
	
	public void renderEntitiesBehindPlayer(Screen screen, Player player) {
		int entityX = player.getX() - 16;
		int entityY = player.getY();
		int entityHeight = 16;
		List<Entity> ALLentities = getEntities();
		entityCountRenderInfronOfPlayer = 0;

		for (int i = 0; i < ALLentities.size(); i++) {
			Entity currentEntity = ALLentities.get(i);
			if (currentEntity instanceof Mob) {
				if (currentEntity.y + ((Mob) currentEntity).getSprite().getHeight() < entityY + entityHeight) {
					currentEntity.render(screen);
					entityCountRenderInfronOfPlayer = i + 1;
				}
			} else if (currentEntity instanceof Projectile) {
				if (currentEntity.y + ((Projectile) currentEntity).getSprite().getHeight() < entityY + entityHeight) {
					currentEntity.render(screen);
					entityCountRenderInfronOfPlayer = i + 1;
				}
			}
		}
		// for (int i = entityCountRenderInfronOfPlayer + 1; i < ALLentities.size(); i++) {
		// if (ALLentities.get(i) instanceof Mob) {
		// if (ALLentities.get(i).y > ALLentities.get(entityCountRenderInfronOfPlayer).y) {
		// entityCountRenderInfronOfPlayer = i;
		// }
		// } else {
		// entities.get(i).render(screen);
		// }
		// }
		
		
	}
	
	public void render(Screen screen, Player player) {
		List<Entity> ALLentities = getEntities();
		for (int i = 0; i < ALLentities.size(); i++) {
			Entity currentEntity = ALLentities.get(i);
			if (currentEntity instanceof Mob || currentEntity instanceof Projectile || currentEntity instanceof Player) {
				entities.get(i).render(screen);
			}
		}
	}
	
	// for (int i = 0; i < entities.size(); i++) {
	//
	// if (entities.get(i) instanceof Mob) {
	// if (entities.get(i).y + ((Mob) entities.get(i)).getSprite().getHeight() < player.getY() + 16) {
	// entities.get(i).render(screen);
	// if (i == entities.size() - 1) {
	// entityCountRenderInfronOfPlayer = i + 1;
	// }
	// } else {
	// entityCountRenderInfronOfPlayer = i;
	// break;
	// }
	// } else if (entities.get(i) instanceof Projectile) {
	// if (entities.get(i).y + ((Projectile) entities.get(i)).getSprite().getHeight() < player.getY() + 16) {
	// entities.get(i).render(screen);
	// if (i == entities.size() - 1) {
	// entityCountRenderInfronOfPlayer = i + 1;
	// }
	// } else {
	// entityCountRenderInfronOfPlayer = i;
	// break;
	// }
	// }
	// }
	// }
	
	
	public void renderEntitiesInfronOfPlayer(Screen screen, Player player) {

		for (int i = entityCountRenderInfronOfPlayer; i < entities.size(); i++) {
			Entity currentEntity = entities.get(i);
			if (currentEntity instanceof Mob) {
				((Mob) currentEntity).render(screen);
			} else if (currentEntity instanceof Projectile) {
				((Projectile) currentEntity).render(screen);
			}
		}
	}

	public void add(Entity entity) {
		entity.init(this);
		if (entity instanceof ItemMob) items.add(entity);
		if (entity instanceof Mob) {
			add(entity.findRenderPosition((Mob) entity), entity);
			return;
		}
		if (entity instanceof Projectile) {
			add(entity.findRenderPosition((Projectile) entity), entity);
			return;
		}
	}
	
	public void add(int position, Entity entity) {
		entities.add(position, entity);
	}
	
	public void remove(Entity entity) {
		entities.remove(entity);
		items.remove(entity);
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public List<Entity> getItems() {
		return items;
	}
	
	public Tile getTile(int x, int y) {
		// if(tiles[x+y*width] == 0) return Tile.grass;
		// if (((x > -GRAYBORDER && x < 0) || (x > width && x < width + GRAYBORDER) && ((y > -GRAYBORDER && y < height + GRAYBORDER)))
		// || ((y > -GRAYBORDER && y < 0) || (y > height && y < height + GRAYBORDER) && ((x > -GRAYBORDER && x < width + GRAYBORDER)))) return Tile.water;
		if ((((x > -GRAYBORDERLEFT - 1 && x < 0) || (x > width - 1 && x < GRAYBORDERRIGHT + width)) && y > -GRAYBORDERTOP - 1 && y < height + GRAYBORDERBOTTUM) || (((y > -GRAYBORDERTOP - 1 && y < 0) || (y > width - 1 && y < GRAYBORDERBOTTUM + width)) && x > -GRAYBORDERLEFT - 1 && x < height + GRAYBORDERRIGHT)) return Tile.mapBorder;

		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		// return tileDic[tiles[x + y * width]].equals(null) ? Tile.voidTile : tileDic[tiles[x + y * width]];
		// return [tiles[x + y * width]].equals(null) ? Tile.voidTile : tileDic[tiles[x + y * width]];
		return (!colourDic.containsKey(pixels[x + y * width])) ? Tile.voidTile : colourDic.get(pixels[x + y * width]);
	}
	
	public boolean tilecollision(int x, int y, int xMovement, int yMovement, int size) { // x,y = position of entity xa,ya direction of heading
		boolean solid = false;
		for (int i = 0; i < 4; i++) {
			int xNextTile = ((x + xMovement) + i % 2 * size / 10 + 4) / 16;
			int yNextTile = ((y + yMovement) + i / 2 * size / 3 + 4) / 16;
			solid = getTile(xNextTile, yNextTile).solid();
		}
		return solid;
	}
	
	public List<Entity> getEntitesInRange(Entity entityOrigin, int radius, List<Entity> entityCheckList) {
		List<Entity> inRange = new ArrayList<Entity>();
		for (int i = 0; i < entityCheckList.size(); i++) {
			Entity entityTarget = entityCheckList.get(i);
			if (entityOrigin instanceof Player && entityTarget instanceof Player) continue;
			int x = entityTarget.getX() + (entityTarget instanceof Mob ? ((Mob) entityTarget).getSprite().getWidth() / 2 : 0);
			int y = entityTarget.getY() + (entityTarget instanceof Mob ? ((Mob) entityTarget).getSprite().getHeight() : 0);

			int distanceX = Math.abs(x - entityOrigin.getX());
			int distanceY = Math.abs(y - entityOrigin.getY() - (entityOrigin instanceof Player ? 16 : 0));

			double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
			if (distance <= radius) inRange.add(entityTarget);
		}
		return inRange;
	}

}

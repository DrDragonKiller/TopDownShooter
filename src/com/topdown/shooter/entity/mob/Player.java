package com.topdown.shooter.entity.mob;

import java.util.Random;

import javax.accessibility.AccessibleHyperlink;

import com.topdown.shooter.entity.Item;
import com.topdown.shooter.entity.projectile.PlayerProjectile;
import com.topdown.shooter.entity.projectile.BlueWizardProjectile;
import com.topdown.shooter.entity.projectile.Projectile;
import com.topdown.shooter.entity.projectile.RedWizardProjectile;
import com.topdown.shooter.graphics.Screen;
import com.topdown.shooter.graphics.Sprite;
import com.topdown.shooter.graphics.Overlay.Overlay;
import com.topdown.shooter.input.Keyboard;


public class Player extends Mob {
	
	private Keyboard input;
	private int		 animation		= 0;
	private boolean	 walking		= false;
	boolean			 lockMovement	= false;
	protected String projectileKind	= "RedWizardProjectile"; // or BlueWizardProjectile
	
	private PlayerProjectile lastProjectile = new RedWizardProjectile(0, 0, 0, 0);

	private final int[][] directionSummonPositionProjectile = { { 4, -9, -23, -9 }, { -8, -5, -9, -6 } };
	
	private boolean achievement = false;
	
	
	public Player(Keyboard input) {
		this.input = input;
		sprite = Sprite.player_back;
	}

	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.player_back;
		gunCoolDown = 0;
	}
	
	public Keyboard getInput() {
		return input;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void update() {
		updateentityRenderOrder(this);
		if (gunCoolDown > 0) gunCoolDown--;
		lockMovement = false;

		int xMovement = 0, yMovement = 0;
		if (animation < 10000)
			animation++;
		else
			animation = 0;
		if (input.number1) setProjectileKind("RedWizardProjectile");
		if (input.number2) setProjectileKind("BlueWizardProjectile");
		if (input.up) yMovement--;
		if (input.down) yMovement++;
		if (input.left) xMovement--;
		if (input.right) xMovement++;
		if (input.space && (xMovement != 0 || yMovement != 0) && gunCoolDown == 0) {
			if (xMovement > 0) dir = 1;// -4 -6
			if (xMovement < 0) dir = 3;// -9 -5
			if (yMovement > 0) dir = 2;// -23 -9
			if (yMovement < 0) dir = 0; // 4 -8
			shoot(x + directionSummonPositionProjectile[0][dir], y + directionSummonPositionProjectile[1][dir], xMovement, yMovement);
			lockMovement = true;
			gunCoolDown = lastProjectile.getFIREDELAY(); // =0 wenn nicht greade gefeuert
		}
		if (gunCoolDown > lastProjectile.getFIREDELAY() - lastProjectile.getATTACKUSETIME()) {
			lockMovement = true;
		}

		walking = false;
		if ((xMovement != 0 || yMovement != 0) && !lockMovement) {
			move(xMovement, yMovement);
			checkItem(this, 16);
			checkFullInventory();
			walking = true;
		}
		clear();

	}

	private void checkFullInventory() {
		if (!achievement && inventory.containsKey(Item.jonas) && inventory.containsKey(Item.kenneth) && inventory.containsKey(Item.martin) && inventory.containsKey(Item.sven)) {
			System.out.println("Kenneth digga was los");
			Overlay.quest.trigger();
			achievement = !achievement;
		}
		
	}

	private void clear() {
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = projectiles.get(i);
			if (p.isRemoved()) {
				level.remove(p);
				projectiles.remove(i);
			}
		}
	}

	public void render(Screen screen) {
		boolean yFlip = false;
		boolean xFlip = false;

		if (dir == 0) {
			sprite = Sprite.player_forward;
			if (walking) {
				if (animation % 20 > 10) {
					sprite = Sprite.player_forward_1;
				} else {
					sprite = Sprite.player_forward_2;
				}
			} else if (gunCoolDown <= lastProjectile.getFIREDELAY() && gunCoolDown >= lastProjectile.getFIREDELAY() - (lastProjectile.getShowAttackAnimationTime())) {
				sprite = Sprite.player_attack_forward_fired;
			} else if (gunCoolDown > lastProjectile.getFIREDELAY() - lastProjectile.getATTACKUSETIME()) {
				sprite = Sprite.player_attack_forward_cooldown;
			}
			
		}
		if (dir == 1) {
			sprite = Sprite.player_right;
			if (walking) {
				if (animation % 20 > 10) {
					sprite = Sprite.player_right_1;
				} else {
					sprite = Sprite.player_right_2;
				}
			} else if (gunCoolDown <= lastProjectile.getFIREDELAY() && gunCoolDown >= lastProjectile.getFIREDELAY() - (lastProjectile.getShowAttackAnimationTime())) {
				sprite = Sprite.player_attack_right_fired;
			} else if (gunCoolDown > lastProjectile.getFIREDELAY() - lastProjectile.getATTACKUSETIME()) {
				sprite = Sprite.player_attack_right_cooldown;
			}
		}
		if (dir == 2) {
			sprite = Sprite.player_back;
			if (walking) {
				if (animation % 20 > 10) {
					sprite = Sprite.player_back_1;
				} else {
					sprite = Sprite.player_back_2;
				}
			} else if (gunCoolDown <= lastProjectile.getFIREDELAY() && gunCoolDown >= lastProjectile.getFIREDELAY() - (lastProjectile.getShowAttackAnimationTime())) {
				sprite = Sprite.player_attack_back_fired;
			} else if (gunCoolDown > lastProjectile.getFIREDELAY() - lastProjectile.getATTACKUSETIME()) {
				sprite = Sprite.player_attack_back_cooldown;
			}
		}
		if (dir == 3) {
			sprite = Sprite.player_left;
			if (walking) {
				if (animation % 20 > 10) {
					sprite = Sprite.player_left_1;
				} else {
					sprite = Sprite.player_left_2;
				}
			} else if (gunCoolDown <= lastProjectile.getFIREDELAY() && gunCoolDown >= lastProjectile.getFIREDELAY() - (lastProjectile.getShowAttackAnimationTime())) {
				sprite = Sprite.player_attack_left_fired;
			} else if (gunCoolDown > lastProjectile.getFIREDELAY() - lastProjectile.getATTACKUSETIME()) {
				sprite = Sprite.player_attack_left_cooldown;
			}
		}
		screen.renderPlayer(x - 16, y - 16, sprite, xFlip, yFlip);
	}
	
	@Override
	public Projectile generateProjectile(int x, int y, int xMovement, int yMovement) {
		switch (projectileKind) {
			case "BlueWizardProjectile":
				lastProjectile = new BlueWizardProjectile(x, y, xMovement, yMovement);
				break;
			case "RedWizardProjectile":
			default:
				lastProjectile = new RedWizardProjectile(x, y, xMovement, yMovement);
				break;
		}
		return lastProjectile;
	}

	public void setProjectileKind(String newProjectileKind) {
		projectileKind = newProjectileKind;
	}
	
	public String getProjectileKind() {
		return projectileKind;
	}
}

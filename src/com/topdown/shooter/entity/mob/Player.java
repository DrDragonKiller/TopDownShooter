package com.topdown.shooter.entity.mob;

import com.topdown.shooter.graphics.Screen;
import com.topdown.shooter.graphics.Sprite;
import com.topdown.shooter.input.Keyboard;


public class Player extends Mob {
	
	private Keyboard input;
	private Sprite	 sprite;
	private int		 animation = 0;
	private boolean	 walking   = false;
	
	
	public Player(Keyboard input) {
		this.input = input;
		sprite = sprite.player_back;
	}

	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
	}
	
	public void update() {
		// if (input.up) y--;
		// if (input.down) y++;
		// if (input.right) x++;
		// if (input.left) x--;
		
		int xa = 0, ya = 0;
		if (animation < 10000)
			animation++;
		else
			animation = 0;
		if (input.up) ya--;
		if (input.down) ya++;
		if (input.left) xa--;
		if (input.right) xa++;
		
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
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
			}
		}
		screen.renderPlayer(x - 16, y - 16, sprite, xFlip, yFlip);
	}
}

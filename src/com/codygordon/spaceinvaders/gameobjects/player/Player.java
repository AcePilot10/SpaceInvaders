package com.codygordon.spaceinvaders.gameobjects.player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import com.codygordon.spaceinvaders.controllers.GameController;
import com.codygordon.spaceinvaders.game.GameContainer;
import com.codygordon.spaceinvaders.gameobjects.GameObject;
import com.codygordon.spaceinvaders.gameobjects.projectiles.PlayerProjectile;

public class Player extends GameObject {


	public static final int MOVE_LEFT = 1;
	public static final int MOVE_RIGHT = 2;
	public static final int PROJECTILE_SPEED = 10;
	public static final long SHOOT_DELAY = 800;
	
	private int speed;
	private boolean canShoot = true;
	
	public Player() {
		location = new Point();
		collider = new Rectangle();
		collider.setBounds(0, 0, 50, 50);
		int height = GameContainer.getInstance().getMainFrame().getHeight();
		location.y = height - (int)(collider.getHeight() * 2);
		speed = 5;
	}
	
	@Override
	public void update(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(location.x,
				location.y,
				(int)collider.getWidth(),
				(int)collider.getHeight());
	}

	public void move(int direction) {
		if(direction == MOVE_LEFT) {
			location.x -= speed;
		} else if(direction == MOVE_RIGHT) {
			location.x += speed;
		}
	}
	
	public void shoot() {
		if(canShoot) {
			GameController controller = GameContainer.getInstance().getController();
			int spawnX = getLocation().x + (25 / 2);
			int spawnY = getLocation().y - (50 / 2) - 5;
			PlayerProjectile projectile = new PlayerProjectile(PROJECTILE_SPEED);
			projectile.setLocation(new Point(spawnX, spawnY));
			controller.initGameObject(projectile);
			startShootDelay();
		} 
	}
	
	private void startShootDelay() {
		canShoot = false;
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				canShoot = true;
			}
		}, SHOOT_DELAY);
	}
	
	
	public int getSpeed() {
		return this.speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
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
import com.codygordon.spaceinvaders.gameobjects.enemies.Enemy;
import com.codygordon.spaceinvaders.gameobjects.projectiles.PlayerProjectile;

public class Player extends GameObject {

	public static final int MOVE_LEFT = 1;
	public static final int MOVE_RIGHT = 2;
	
	private long shootDelay = 800;
	private int projectileSpeed = 10;
	private int speed = 5;
	private Color color;
	
	private boolean canShoot = true;
	public boolean isAlive = true;
	
	public Player() {
		location = new Point();
		collider = new Rectangle();
		collider.setBounds(0, 0, 50, 50);
		int height = GameContainer.getInstance().getMainFrame().getHeight();
		location.y = height - (int)(collider.getHeight() * 2);
		speed = 5;
	}
	
	@Override
	public void onCollision(GameObject col) {
		if(col instanceof Enemy) {
			//GameContainer.getInstance().getController().endGame();
		}
	}
	
	@Override
	public void draw(Graphics g) {
		if(!isAlive) return;
		g.setColor(color);
		g.fillRect(location.x,
				location.y,
				(int)collider.getWidth(),
				(int)collider.getHeight());
		
		g.setColor(Color.GREEN);
		g.drawLine(location.x + collider.width / 2, location.y, location.x + collider.width / 2, 0);
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
			int spawnX = getLocation().x + (collider.width / 2);
			int spawnY = getLocation().y - (collider.height / 2);
			PlayerProjectile projectile = new PlayerProjectile(projectileSpeed);
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
		}, shootDelay);
	}
	
	
	public int getSpeed() {
		return this.speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setWidth(int width) {
		collider.width = width;
	}
	
	public void setHeight(int height) {
		collider.height = height;
	}
	
	public void setProjectileSpeed(int speed) {
		this.projectileSpeed = speed;
	}
	
	public void setShootDelay(long delay) {
		this.shootDelay = delay;
	}
	
	public int getWidth() {
		return collider.width;
	}
	
	public int getHeight() {
		return collider.height;
	}
}
package com.codygordon.spaceinvaders.gameobjects.enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.codygordon.spaceinvaders.game.GameContainer;
import com.codygordon.spaceinvaders.gameobjects.projectiles.EnemyProjectile;

public class EnemyShooter extends Enemy {
	
	private int projectileSpeed = -15;
	private long shootDelay = 1000;
	private float shootChance = 0.1f;
	
	private Timer shootTimer;
	
	@Override
	public void init() {
		beginShootingTimer();
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(location.x, 
				location.y, 
				collider.width, 
				collider.height);
		
		g.setColor(Color.RED);
		g.drawRect(collider.x,
				collider.y,
				collider.width, 
				collider.height);
	}
	
	public void beginShootingTimer() {
		shootTimer = new Timer();
		shootTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if(tryToShoot()) {
					shoot();
				}
			}
		}, shootDelay, shootDelay);
	}
	
	private boolean tryToShoot() {
		Random rdm = new Random();
		float rdmNumber = rdm.nextFloat();
		if(shootChance >= rdmNumber) {
			return true;
		} else {
			return false;
		}
	}
	
	private void shoot() {
		EnemyProjectile projectile = new EnemyProjectile(projectileSpeed);
		int x = getLocation().x + collider.width / 2;
		int y = getLocation().y + collider.height;
		projectile.setLocation(new Point(x, y));
		GameContainer.getInstance().getController().initGameObject(projectile);
	}
	
	@Override
	public void die() {
		super.die();
		shootTimer.cancel();
	}
	
	public long getShootDelay() {
		return this.shootDelay;
	}
	
	public float getShootChance() {
		return this.shootChance;
	}
	
	public void setShootDelay(long shootDelay) {
		this.shootDelay = shootDelay;
	}
	
	public void setShootChance(float shootChance) {
		this.shootChance = shootChance;
	}
	
	public Timer getShootTimer() {
		return this.shootTimer;
	}
	
	public void setProjectileSpeed(int projectileSpeed) {
		this.projectileSpeed = projectileSpeed;
	}
	
	public int getProjectileSpeed() {
		return this.projectileSpeed;
	}
}
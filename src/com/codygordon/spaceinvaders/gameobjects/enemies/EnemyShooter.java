package com.codygordon.spaceinvaders.gameobjects.enemies;

import java.awt.Point;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.codygordon.spaceinvaders.game.GameContainer;
import com.codygordon.spaceinvaders.gameobjects.projectiles.EnemyProjectile;

public class EnemyShooter extends Enemy {

	public static final int PROJECTILE_SPEED = -5;
	
	private long shootDelay;
	private float shootChance;
	
	private Timer shootTimer;

	public EnemyShooter() {
		super();
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
		EnemyProjectile projectile = new EnemyProjectile(PROJECTILE_SPEED);
		int x = getLocation().x + collider.width * 2 - projectile.getCollider().width;
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
}
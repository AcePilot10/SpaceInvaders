package com.codygordon.spaceinvaders.gameobjects.enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import com.codygordon.spaceinvaders.game.GameContainer;
import com.codygordon.spaceinvaders.gameobjects.GameObject;

public class Enemy extends GameObject implements Cloneable {
	
	public int destroyReward;
	private boolean isAlive = true;
	
	public Enemy() {
		super();
		collider.width = 35;
		collider.height = 35;
		setLocation(new Point(50, 50));
	}
	
	public void init() { }
	
	@Override
	public void update(Graphics g) {
		super.update(g);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(location.x, 
				location.y, 
				collider.width, 
				collider.height);
	}
	
	public void die() {
		GameContainer.getInstance().getController().destroyGameObject(this);
		isAlive = false;
		GameContainer.getInstance().getController().getCurrentEnemyWave().enemyKilled();
	}
	
	public int getDestroyReward() {
		return this.destroyReward;
	}
	
	public boolean isAlive() {
		return this.isAlive;
	}
	
	@Override
	public Enemy clone() {
		try {
			return (Enemy) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
}
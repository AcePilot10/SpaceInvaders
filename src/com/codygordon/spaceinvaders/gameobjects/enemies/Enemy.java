package com.codygordon.spaceinvaders.gameobjects.enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import com.codygordon.spaceinvaders.game.GameContainer;
import com.codygordon.spaceinvaders.gameobjects.GameObject;

public class Enemy extends GameObject {
	
	public int destroyReward;
	private boolean isAlive = true;
	private int width;
	private int height;
	private int pointValue = 25;
	
	public Enemy() {
		super();
		collider.width = width;
		collider.height = height;
		setLocation(new Point(50, 50));
	}
	
	public void init() { }
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(location.x, 
				location.y, 
				collider.width, 
				collider.height);
	}
	
	@Override
	public Enemy clone() {
		try {
			Enemy enemy = (Enemy)super.clone();
			enemy.setCollider(new Rectangle(width, height));
			return enemy;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void die() {
		GameContainer.getInstance().getController().destroyGameObject(this);
		isAlive = false;
		GameContainer.getInstance().getController().addToScore(pointValue);
		GameContainer.getInstance().getController().getCurrentEnemyWave().enemyKilled();
	}
	
	public int getDestroyReward() {
		return this.destroyReward;
	}
	
	public boolean isAlive() {
		return this.isAlive;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getPointValue() {
		return this.pointValue;
	}
	
	public void setPointValue(int value) {
		this.pointValue = value;
	}
}
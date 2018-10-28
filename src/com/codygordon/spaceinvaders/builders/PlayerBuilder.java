package com.codygordon.spaceinvaders.builders;

import java.awt.Color;
import java.awt.Point;

import com.codygordon.spaceinvaders.gameobjects.player.Player;

public class PlayerBuilder {

	private int moveSpeed;
	private long shootDelay;
	private int projectileSpeed;
	private int width;
	private int height;
	private Point startLocation;
	private Color color;
	
	public PlayerBuilder setMoveSpeed(int speed) {
		this.moveSpeed = speed;
		return this;
	}
	
	public PlayerBuilder setShootDelay(long shootDelay) {
		this.shootDelay = shootDelay;
		return this;
	}
	
	public PlayerBuilder setProjectileSpeed(int projectileSpeed) {
		this.projectileSpeed = projectileSpeed;
		return this;
	}
	
	public PlayerBuilder setWidth(int width) {
		this.width = width;
		return this;
	}
	
	public PlayerBuilder setHeight(int height) {
		this.height = height;
		return this;
	}
	
	public PlayerBuilder setStartingLocation(Point location) {
		this.startLocation = location;
		return this;
	}
	
	public PlayerBuilder setColor(Color color) {
		this.color = color;
		return this;
	}
	
	public Player build() {
		Player player = new Player();
		player.setColor(color);
		player.setLocation(startLocation);
		player.setWidth(width);
		player.setHeight(height);
		player.setSpeed(moveSpeed);
		player.setProjectileSpeed(projectileSpeed);
		player.setShootDelay(shootDelay);
		return player;
	}
}
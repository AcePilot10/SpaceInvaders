package com.codygordon.spaceinvaders.enemies;

import java.awt.Point;
import java.util.HashMap;

import com.codygordon.spaceinvaders.game.GameContainer;
import com.codygordon.spaceinvaders.gameobjects.enemies.Enemy;

public class EnemyWave {

	public static final int Y_PADDING = 10;
	public static final int X_PADDING = 5;
	
	private int rows;
	private int enemiesPerRow;

	private HashMap<Integer, Enemy> enemies = new HashMap<Integer, Enemy>();
	
	public EnemyWave(int rows, int enemiesPerRow) {
		this.rows = rows;
		this.enemiesPerRow = enemiesPerRow;
	}
	
	public int getRows() {
		return this.rows;
	}
	
	public void setRows(int rows) {
		this.rows = rows;
	}

	public void createEnemies() {
		for(int row = 1; row <= rows; row++) {
			for(int col = 1; col <= enemiesPerRow; col++) {
				int y = Y_PADDING * row;
				int x = X_PADDING * col;
				Enemy enemy = new Enemy();
				enemy.setLocation(new Point(x, y));
				GameContainer.getInstance().getController().initGameObject(enemy);
			}
		}
	}
	
	public HashMap<Integer, Enemy> getEnemeis() {
		return this.enemies;
	}
}
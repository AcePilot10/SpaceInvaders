package com.codygordon.spaceinvaders.enemies;

import java.awt.Point;
import java.util.HashMap;

import com.codygordon.spaceinvaders.controllers.GameController;
import com.codygordon.spaceinvaders.game.GameContainer;
import com.codygordon.spaceinvaders.gameobjects.enemies.Enemy;

public class EnemyWave {

	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	
	public static final int Y_PADDING = 50;
	public static final int X_PADDING = 50;
	
	private int rows;
	private int enemiesPerRow;

	private Enemy[][] enemies;
	
	public EnemyWave(int rows, int enemiesPerRow) {
		this.rows = rows;
		this.enemiesPerRow = enemiesPerRow;
		enemies = new Enemy[rows][enemiesPerRow];
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
				GameController controller = GameContainer.getInstance().getController();
				controller.initGameObject(enemy);
			}
		}
	}
	
	public void move(int direction) {
		if(direction == LEFT) {
			
		} else if(direction == RIGHT) {
			
		}
		
		
	}
}
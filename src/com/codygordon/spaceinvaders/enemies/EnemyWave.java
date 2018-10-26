package com.codygordon.spaceinvaders.enemies;

import java.awt.Point;

import com.codygordon.spaceinvaders.controllers.GameController;
import com.codygordon.spaceinvaders.game.GameContainer;
import com.codygordon.spaceinvaders.gameobjects.enemies.Enemy;
import com.codygordon.spaceinvaders.util.ScreenUtil;

public class EnemyWave {

	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	
	public static final int Y_PADDING = 50;
	public static final int X_PADDING = 50;
	
	private int rows;
	private int enemiesPerRow;
	
	private int horizontalDirection = LEFT;

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
				enemies[row-1][col-1] = enemy;
				GameController controller = GameContainer.getInstance().getController();
				controller.initGameObject(enemy);
			}
		}
	}

	public void moveHorizontal() {
		int deltaX = 0;
		switch(horizontalDirection) {
		case LEFT:
			deltaX -= X_PADDING;
			break;
		case RIGHT:
			deltaX += X_PADDING;
			break;
		}
		for(int row = 1; row <= rows; row++) {
			for(int enemyColumn = 1; enemyColumn <= enemiesPerRow; enemyColumn++) {
				Enemy enemy = enemies[row - 1][enemyColumn - 1];
				enemy.setLocation(new Point(enemy.getLocation().x + deltaX, enemy.getLocation().y)); 
				if(horizontalDirection == RIGHT) {
					System.out.println("Moved right");
				}
			}
		}
		handleDirectionChange();
	}
	
	private void handleDirectionChange() {
		if(horizontalDirection == LEFT) {
			for(int row = 1; row <= rows; row++) {
				for(int column = 1; column <= enemiesPerRow; column++) {
					Enemy enemy = enemies[row - 1][column - 1];
					if(enemy != null) {
						
						Point enemyLocation = enemy.getLocation();
						int x = enemyLocation.x - X_PADDING;
						int y = enemyLocation.y;
						
						if(ScreenUtil.isOffScreen(new Point(x, y))) {
							horizontalDirection = RIGHT;
						}
						break;
					}
				}
			}
		} else {
			for(int row = 1; row <= rows; rows++) {
				for(int column = enemiesPerRow; column <= 1; column--) {
					Enemy enemy = enemies[row - 1][column - 1];
					if(enemy != null) {
						
						Point enemyLocation = enemy.getLocation();
						int x = enemyLocation.x + X_PADDING;
						int y = enemyLocation.y;
						if(ScreenUtil.isOffScreen(new Point(x, y))) {
							horizontalDirection = LEFT;
						}
						break;
					}
				}
			}
		}
		
		System.out.println("Next direction is: " + horizontalDirection);
	}
	
	public void moveDown() {
		for(int row = 1; row <= rows; row++) {
			for(int enemyColumn = 1; enemyColumn <= enemiesPerRow; enemyColumn++) {
				Enemy enemy = enemies[row - 1][enemyColumn - 1];
				enemy.setLocation(new Point(enemy.getLocation().x, enemy.getLocation().y + Y_PADDING)); 
			}
		}
	}
}
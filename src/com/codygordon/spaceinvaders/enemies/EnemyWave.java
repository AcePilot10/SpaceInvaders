package com.codygordon.spaceinvaders.enemies;

import java.awt.Point;

import com.codygordon.spaceinvaders.controllers.GameController;
import com.codygordon.spaceinvaders.game.GameContainer;
import com.codygordon.spaceinvaders.gameobjects.enemies.Enemy;
import com.codygordon.spaceinvaders.gameobjects.enemies.EnemyShooter;
import com.codygordon.spaceinvaders.util.ScreenUtil;

public class EnemyWave {

	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	
	public static final int Y_PADDING = 50;
	public static final int X_PADDING = 50;
	
	public long moveHorizontalDelay;
	
	private int rows;
	private int enemiesPerRow;
	private Thread moveHorizontalThread;
	private int horizontalDirection = RIGHT;

	private Enemy[][] enemies;
	
	public EnemyWave(int rows, int enemiesPerRow) {
		this.rows = rows;
		this.enemiesPerRow = enemiesPerRow;
		enemies = new Enemy[rows][enemiesPerRow];
	}
	
	public void init() {
		createEnemies();
		beginMoving();
	}

	public void enemyKilled() {
		if(allEnemiesAreDead()) {
			GameContainer.getInstance().getController().spawnNextWave();
		}
	}
	
	private void createEnemies() {
		for(int row = 1; row <= rows; row++) {
			for(int col = 1; col <= enemiesPerRow; col++) {
				int y = Y_PADDING * row; 
				int x = X_PADDING * col;
				EnemyShooter enemy = new EnemyShooter();
				enemy.setShootChance(1f);
				enemy.setShootDelay(1500);
				enemy.setLocation(new Point(x, y));
				enemies[row-1][col-1] = enemy;
				GameController controller = GameContainer.getInstance().getController();
				controller.initGameObject(enemy);
				enemy.beginShootingTimer();
			}
		}
	}
		
	private void beginMoving() {
		IEnemyMove move = new IEnemyMove() {
			@Override
			public void move() {
				moveHorizontal();
			}
		};
		MoveRunnable runnable = new MoveRunnable(move, moveHorizontalDelay);
		moveHorizontalThread = new Thread(runnable);
		moveHorizontalThread.setName("Move Horizontal Thread");
		moveHorizontalThread.start();
	}
	
	private void moveHorizontal() {
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
			}
		}
		handleDirectionChange();
	}
	
	private void handleDirectionChange() {
		if(horizontalDirection == LEFT) {
			outerloop: 
			for(int row = 1; row <= rows; row++) {
				for(int column = 1; column <= enemiesPerRow; column++) {
					Enemy enemy = enemies[row - 1][column - 1];
					if(enemy.isAlive()) {
						
						Point enemyLocation = enemy.getLocation();
						int x = enemyLocation.x - X_PADDING / 2;
						int y = enemyLocation.y;
						
						if(ScreenUtil.isOffScreen(new Point(x, y))) {
							horizontalDirection = RIGHT;
							moveDown();
						}
						break outerloop;
					}
				}
			}
		} else if(horizontalDirection == RIGHT) {
			outerloop: 
			for(int row = 1; row <= rows; rows++) {
				for(int column = enemiesPerRow; column >= 1; column--) {
					Enemy enemy = enemies[row - 1][column - 1];
					if(enemy.isAlive()) {
						
						Point enemyLocation = enemy.getLocation();
						int x = enemyLocation.x + X_PADDING * 2;
						int y = enemyLocation.y;
						if(ScreenUtil.isOffScreen(new Point(x, y))) {
							horizontalDirection = LEFT;
							moveDown();
						}
						break outerloop;
					}
				}
			}
		}
	}
	
	private void moveDown() {
		for(int row = 1; row <= rows; row++) {
			for(int enemyColumn = 1; enemyColumn <= enemiesPerRow; enemyColumn++) {
				Enemy enemy = enemies[row - 1][enemyColumn - 1];
				enemy.setLocation(new Point(enemy.getLocation().x, enemy.getLocation().y + Y_PADDING)); 
			}
		}
	}

	private boolean allEnemiesAreDead() {
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < enemiesPerRow; col++) {
				Enemy enemy = enemies[row][col];
				if(enemy.isAlive()) {
					return false;
				}
			}
		}
		return true;
	}
	
	public int getRows() {
		return this.rows;
	}
	
	public void setRows(int rows) {
		this.rows = rows;
	}
}